package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;


import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage1;
import utils.ChromeOptionsHelper;
import utils.ConfigManager;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class LoginAndCartTest extends BaseTest{

    private static final ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return webDriverThreadLocal.get();
    }

    private static void setDriver(WebDriver driver) {
        webDriverThreadLocal.set(driver);
    }

    @BeforeClass
    public void setUpAndLogin() {
        setDriver(new ChromeDriver(ChromeOptionsHelper.getChromeOptions()));
        getDriver().get(ConfigManager.baseUrl());
        new LoginPage1(getDriver()).login(
                ConfigManager.username(),
                ConfigManager.password()
        );
    }

    @Test(priority = 1)
    public void userCanAddProductToCart() {
        System.out.println(
                "Thread: " + Thread.currentThread().getId()
        );
        InventoryPage inventory = new InventoryPage(getDriver());
        inventory.addProductToCart("Sauce Labs Backpack");
        inventory.clickOnShoppingCartLink();
        CartPage cartPage = new CartPage(getDriver());
        LOGGER.info("Clicked on shopping cart link");
        assertThat("Shopping cart page is not opened", cartPage.isShoppingCartOpened(), is(true));
        assertThat("Shopping cart is empty", cartPage.isProductPresentInCart("Sauce Labs Backpack"), is(true));
    }

    @Test(priority = 2)
    public void removeProductFromCart() {
        System.out.println(
                "Thread: " + Thread.currentThread().getId()
        );
        CartPage cartPage = new CartPage(getDriver());
        cartPage.removeProductFromCart("Sauce Labs Backpack");
        assertThat("Shopping cart is not empty", cartPage.isProductPresentInCart("Sauce Labs Backpack"), is(false));
    }

    @AfterClass
    public void tearDown() {
        getDriver().quit();
    }
}