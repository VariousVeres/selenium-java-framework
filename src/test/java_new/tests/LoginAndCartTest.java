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


public class LoginAndCartTest {

    private WebDriver webdriver;

    @BeforeClass
    public void setUpAndLogin() {
        webdriver = new ChromeDriver(ChromeOptionsHelper.getChromeOptions());
        webdriver.get(ConfigManager.baseUrl());
        new LoginPage1(webdriver).login(
                ConfigManager.username(),
                ConfigManager.password()
        );
    }

    @Test(priority = 1)
    public void userCanAddProductToCart() {
        System.out.println(
                "Thread: " + Thread.currentThread().getId()
        );
        InventoryPage inventory = new InventoryPage(webdriver);
        inventory.addProductToCart("Sauce Labs Backpack");
        inventory.clickOnShoppingCartLink();
        CartPage cartPage = new CartPage(webdriver);
        assertThat("Shopping cart page is not opened", cartPage.isShoppingCartOpened(), is(true));
        assertThat("Shopping cart is empty", cartPage.isProductPresentInCart("Sauce Labs Backpack"), is(true));
    }

    @Test(priority = 2)
    public void removeProductFromCart() {
        System.out.println(
                "Thread: " + Thread.currentThread().getId()
        );
        CartPage cartPage = new CartPage(webdriver);
        cartPage.removeProductFromCart("Sauce Labs Backpack");
        assertThat("Shopping cart is not empty", cartPage.isProductPresentInCart("Sauce Labs Backpack"), is(false));
    }

    @AfterClass
    public void tearDown() {
        webdriver.quit();
    }
}