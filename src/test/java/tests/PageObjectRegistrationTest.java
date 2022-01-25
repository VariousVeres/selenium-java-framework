package tests;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.pages_object_models_pages.PageObjectLoginPage;
import pages.pages_object_models_pages.PageObjectMainPage;
import pages.pages_object_models_pages.PageObjectRegistrationPage;
import utils.StringHelper;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PageObjectRegistrationTest {
    private WebDriver driver = null;
    PageObjectMainPage pageObjectMainPage = null;
    PageObjectLoginPage pageObjectLoginPage = null;
    PageObjectRegistrationPage pageObjectRegistrationPage = null;

    @BeforeSuite
    public void beforeTest() {
        String userDir = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", userDir + File.separator + "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920, 1650));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost:3000");
        pageObjectMainPage = new PageObjectMainPage(driver);
        pageObjectLoginPage = new PageObjectLoginPage(driver);
        pageObjectRegistrationPage = new PageObjectRegistrationPage(driver);
    }

    @Test
    public void proceedToLoginPage() {
        pageObjectMainPage.dismissWelcomeBanner();
        pageObjectMainPage.acceptCookies();
        pageObjectMainPage.proceedToLoginPage();
        assertThat("Wrong Login page url", driver.getCurrentUrl(), equalTo("http://localhost:3000/#/login"));
        pageObjectLoginPage.clickRegisterButton();
        assertThat("Wrong Registration page url", driver.getCurrentUrl(), equalTo("http://localhost:3000/#/register"));
        pageObjectRegistrationPage.register(StringHelper.getAlphanumericStringWithLength(7) + "@gmail.com",
                "azAZ09@-", 1, "asd");
    }

    @AfterSuite
    public void afterTest() {

        driver.close();
        driver.quit();
    }
}
