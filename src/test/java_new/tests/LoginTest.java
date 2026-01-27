package tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.InventoryPage;
import pages.LoginPage1;
import utils.ConfigManager;

import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

//@Listeners({AllureListener.class})
public class LoginTest {

    LoginPage1 loginPage;
    InventoryPage inventoryPage;
    WebDriver webdriver;

    @BeforeMethod
    public void setUp() {
        webdriver = new ChromeDriver();
    }

    @Test(priority = 1)
    public void shouldLoginSuccessfully() {
        webdriver.get(Objects.requireNonNull(ConfigManager.baseUrl(), "base_url is missing"));
        loginPage = new LoginPage1(webdriver);
        loginPage.login(ConfigManager.username(), ConfigManager.password());
        inventoryPage = new InventoryPage(webdriver);
        assertThat("Inventory container is not present in the inventory page", inventoryPage.isInventoryContainerPresent(), is(true));

    }

    @AfterMethod
    public void tearDown() {
        webdriver.quit();
    }


}
