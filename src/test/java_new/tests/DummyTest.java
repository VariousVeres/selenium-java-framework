package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage1;
import utils.ChromeOptionsHelper;
import utils.ConfigManager;


public class DummyTest {

    WebDriver webdriver;

    @BeforeClass(groups = "smoke")
    public void setUpAndLogin() {
        webdriver = new ChromeDriver(ChromeOptionsHelper.getChromeOptions());
        webdriver.get(ConfigManager.baseUrl());
        new LoginPage1(webdriver).login(
                ConfigManager.username(),
                ConfigManager.password()
        );
    }

    @Test(priority = 1, groups = "smoke")
    public void dummyTest1() {
        System.out.println(
                "Thread: " + Thread.currentThread().getId()
        );
        System.out.println("This is our dummy test");

    }

    @AfterClass
    public void tearDown() {
        webdriver.quit();
    }
}
