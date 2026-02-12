package tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.InventoryPage;
import pages.LoginPage1;
import utils.ChromeOptionsHelper;
import utils.ConfigManager;

import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginTest extends BaseTest{

    LoginPage1 loginPage;
    InventoryPage inventoryPage;

    private static final ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return webDriverThreadLocal.get();
    }

    private static void setDriver(WebDriver driver) {
        webDriverThreadLocal.set(driver);
    }


//For parametrized run
//    @BeforeClass
//    @Parameters("browser")
//    public void setUp(String browser) {
//        if (browser.equals("chrome")) {
//            webdriver = new ChromeDriver(ChromeOptionsHelper.getChromeOptions());
//        } else if (browser.equals("firefox")) {
//            webdriver = new FirefoxDriver();
//        }
//    }


    // @BeforeMethod is used to support parallel="methods" execution
    @BeforeMethod
    public void setUp() {
        setDriver(new ChromeDriver(ChromeOptionsHelper.getChromeOptions()));

    }

    @DataProvider(parallel = true)
    public Object[][] correctLoginData() {
        return new Object[][]{
                {ConfigManager.username(), ConfigManager.password()},
                {"visual_user", ConfigManager.password()}
        };
    }

    @Test(dataProvider = "correctLoginData")
    public void shouldCorrectLoginSuccessfully(String user, String password) {
        getDriver().get(Objects.requireNonNull(ConfigManager.baseUrl(), "base_url is missing"));
        loginPage = new LoginPage1(getDriver());
        loginPage.login(user, password);
        inventoryPage = new InventoryPage(getDriver());
        LOGGER.info("Logged into system");
        assertThat("Inventory container is not present in the inventory page", inventoryPage.isInventoryContainerPresent(), is(true));
    }

    @Test
    public void shouldWrongLoginUnsuccessful() {
        getDriver().get(Objects.requireNonNull(ConfigManager.baseUrl(), "base_url is missing"));
        loginPage = new LoginPage1(getDriver());
        loginPage.login(ConfigManager.username(), "wrong_password");
        LOGGER.info("Tried to log into system with wrong credentials");
        assertThat("Login with incorrect data was successful", loginPage.isDataTestErrorPresentOnPage(), is(true));
    }

    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            webDriverThreadLocal.remove();
        }
    }


}
