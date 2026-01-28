package tests;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.pages_object_models_pages.PageObjectLoginPage;
import pages.pages_object_models_pages.PageObjectMainPage;
import pages.pages_object_models_pages.PageObjectRegistrationPage;
import utils.Helper;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PageObjectRegistrationTest {
    private WebDriver driver = null;
    PageObjectMainPage pageObjectMainPage = null;
    PageObjectLoginPage pageObjectLoginPage = null;
    PageObjectRegistrationPage pageObjectRegistrationPage = null;

    @BeforeTest
    public void beforeTest() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920, 1650));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(Helper.getProperty("host.url"));
    }

    @Test
    public void register() {
        pageObjectMainPage = new PageObjectMainPage(driver);
        pageObjectMainPage.dismissWelcomeBanner();
        pageObjectMainPage.acceptCookies();
        pageObjectLoginPage = pageObjectMainPage.proceedToLoginPage();
        assertThat("Wrong Login page url", driver.getCurrentUrl(), equalTo("http://localhost:3000/#/login"));
        pageObjectRegistrationPage = pageObjectLoginPage.clickRegisterButton();
        assertThat("Wrong Registration page url", driver.getCurrentUrl(), equalTo("http://localhost:3000/#/register"));
        pageObjectRegistrationPage.register(Helper.getAlphanumericStringWithLength(7) + "@gmail.com",
                "azAZ09@-", 1, "asd");
    }

    @AfterTest
    public void afterTest() {
        driver.close();
        if (driver != null) {
            driver.quit();
        }
    }
}
