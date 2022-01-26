package tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import utils.StringHelper;
import io.github.bonigarcia.wdm.WebDriverManager;


import java.util.concurrent.TimeUnit;

import static utils.Logging.*;

public class RegistrationTest {
    private WebDriver driver = null;
    MainPage mainPage = null;
    LoginPage loginPage = null;
    RegistrationPage registrationPage = null;

    @BeforeSuite
    public void beforeTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920, 1650));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost:3000");
        LOGGER.info("Opening main page");
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
    }

    @Test
    public void proceedToLoginPage() {
        mainPage.dismissWelcomeBanner();
        mainPage.acceptCookies();
        LOGGER.info("Proceeding to the login page");
        mainPage.proceedToLoginPage();
        assertThat("Wrong Login page url", driver.getCurrentUrl(), equalTo("http://localhost:3000/#/login"));
        loginPage.clickRegisterButton();
        LOGGER.info("Choose new registration");
        assertThat("Wrong Registration page url", driver.getCurrentUrl(), equalTo("http://localhost:3000/#/register"));
        String email = StringHelper.getAlphanumericStringWithLength(7) + "@gmail.com";
        String password = StringHelper.getAlphanumericStringWithLength(8);
        registrationPage.register(email, password, 1, "asd");
        LOGGER.info("New user with [{}] was registered", email);
    }

    @AfterSuite
    public void afterTest() {
        driver.close();
        if (driver != null) {
            driver.quit();
        }
    }
}
