package tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import utils.Dictionary;
import utils.Helper;
import io.github.bonigarcia.wdm.WebDriverManager;


import java.util.concurrent.TimeUnit;

import static utils.Logging.LOGGER;

public class RegistrationTest {
    public WebDriver driver = null;

    @BeforeSuite
    public void driverInitialization() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920, 1650));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @BeforeSuite
    public void registration() {
        driver.get("http://localhost:3000");
        LOGGER.info("Opening main page");
        MainPage mainPage = new MainPage(driver);
        mainPage.dismissWelcomeBanner();
        mainPage.acceptCookies();
        LoginPage loginPage = mainPage.proceedToLoginPage();
        LOGGER.info("Proceeding to the login page");
        assertThat("Wrong Login page url", driver.getCurrentUrl(), equalTo("http://localhost:3000/#/login"));
        RegistrationPage registrationPage = loginPage.clickRegisterButton();
        LOGGER.info("Choose new registration");
        assertThat("Wrong Registration page url", driver.getCurrentUrl(), equalTo("http://localhost:3000/#/register"));
        String email = Helper.getAlphanumericStringWithLength(7) + "@gmail.com";
        String password = Helper.getAlphanumericStringWithLength(8);
        Dictionary.setEMAIL(email);
        Dictionary.setPASSWORD(password);
        registrationPage.register(email, password, 1, "asd");
        LOGGER.info("New user with [{}] was registered", email);
    }

    @AfterSuite
    public void afterTests() {
        driver.close();
        if (driver != null) {
            driver.quit();
        }
    }

}
