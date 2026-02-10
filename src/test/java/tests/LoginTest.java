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

//@Listeners({AllureListener.class})
public class LoginTest {

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
        System.out.println(
                "Thread: " + Thread.currentThread().getId()
        );
        getDriver().get(Objects.requireNonNull(ConfigManager.baseUrl(), "base_url is missing"));
        loginPage = new LoginPage1(getDriver());
        loginPage.login(user, password);
        inventoryPage = new InventoryPage(getDriver());
        assertThat("Inventory container is not present in the inventory page", inventoryPage.isInventoryContainerPresent(), is(true));
    }

    @Test
    public void shouldWrongLoginUnsuccessful() {
        System.out.println(
                "Thread: " + Thread.currentThread().getId()
        );
        getDriver().get(Objects.requireNonNull(ConfigManager.baseUrl(), "base_url is missing"));
        loginPage = new LoginPage1(getDriver());
        loginPage.login(ConfigManager.username(), "wrong_password");
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
