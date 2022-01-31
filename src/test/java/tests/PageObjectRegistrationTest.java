package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
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
        WebDriverManager.chromedriver().setup();
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
