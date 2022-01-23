package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.pages_object_models_pages.PageObjectLoginPage;
import pages.pages_object_models_pages.PageObjectMainPage;
import pages.pages_object_models_pages.PageObjectRegistrationPage;
import utils.StringHelper;

import static org.junit.Assert.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class PageObjectRegistrationTest {
    private WebDriver driver = null;
    private ExtentHtmlReporter htmlReporter = null;
    private ExtentReports extent = null;
    PageObjectMainPage pageObjectMainPage = null;
    PageObjectLoginPage pageObjectLoginPage = null;
    PageObjectRegistrationPage pageObjectRegistrationPage = null;

    @Before
    public void beforeTest() {
        String userDir = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", userDir + File.separator + "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920, 1650));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        htmlReporter = new ExtentHtmlReporter("target/extent_report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
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
        assertEquals("Wrong Login page url", driver.getCurrentUrl(), "http://localhost:3000/#/login");
        pageObjectLoginPage.clickRegisterButton();
        assertEquals("Wrong Registration page url", driver.getCurrentUrl(), "http://localhost:3000/#/register");
        pageObjectRegistrationPage.register(StringHelper.getAlphanumericStringWithLength(7) + "@gmail.com",
                "azAZ09@-", 1, "asd");
    }

    @After
    public void afterTest() {
        extent.flush();
        driver.close();
        driver.quit();
    }
}
