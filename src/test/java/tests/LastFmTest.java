package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;


public class LastFmTest {
    private WebDriver driver = null;
    private ExtentHtmlReporter htmlReporter = null;
    private ExtentReports extent = null;

    @BeforeSuite
    private void beforeTest() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920, 1650));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        htmlReporter = new ExtentHtmlReporter("target/extent_report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

    }

    @BeforeTest
    private void logIn() {
        driver.get("https://www.last.fm/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn();
    }

    @Test
    private void test() {
        ExtentTest test1 = extent.createTest("Test for Last fm profile");
        test1.info("Starting test");
        test1.log(Status.INFO, "Info");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnProfileLogo();
        test1.info("Profile logo clicked");
        test1.log(Status.PASS, "Text passed");
    }

    @AfterTest
    private void afterTest() {
        extent.flush();
        driver.close();
        driver.quit();
    }
}
