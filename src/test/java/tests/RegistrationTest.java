package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import utils.StringHelper;

import static utils.Logging.LOGGER;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class RegistrationTest {
    private WebDriver driver = null;
    private ExtentHtmlReporter htmlReporter = null;
    private ExtentReports extent = null;
    MainPage mainPage = null;
    LoginPage loginPage = null;
    RegistrationPage registrationPage = null;

    @Before
    public void beforeTest() {
        String userDir = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", userDir + File.separator + "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920, 1650));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        htmlReporter = new ExtentHtmlReporter("target/extent_report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
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
        assertEquals("Wrong Login page url", driver.getCurrentUrl(), "http://localhost:3000/#/login");
        loginPage.clickRegisterButton();
        LOGGER.info("Choose new registration");
        assertEquals("Wrong Registration page url", driver.getCurrentUrl(), "http://localhost:3000/#/register");
        String email = StringHelper.getAlphanumericStringWithLength(7) + "@gmail.com";
        String password = StringHelper.getAlphanumericStringWithLength(8);
        registrationPage.register(email, password, 1, "asd");
        LOGGER.info("New user with [{}] was registerd", email);
    }

    @After
    public void afterTest() {
        extent.flush();
        driver.close();
        driver.quit();
    }
}
