package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage1;
import utils.ConfigManager;



public class LoginAndCartTest {

    private WebDriver webdriver;

    @BeforeMethod
    public void setUpAndLogin() {
        webdriver = new ChromeDriver();
        webdriver.get(ConfigManager.baseUrl());
        new LoginPage1(webdriver).login(
                ConfigManager.username(),
                ConfigManager.password()
        );
    }

    @Test
    public void addProductToCart() {
        InventoryPage inventory = new InventoryPage(webdriver);
//        inventory.addFirstProductToCart();

        // assert
    }

    @Test
    public void removeProductFromCart() {
        InventoryPage inventory = new InventoryPage(webdriver);
//        inventory.removeFirstProductFromCart();

        // assert
    }

    @AfterMethod
    public void tearDown() {
        webdriver.quit();
    }
}