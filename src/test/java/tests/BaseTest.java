package tests;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import driver.BrowserFactory;

public abstract class BaseTest {
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @BeforeMethod
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        DriverManager.setDriver(BrowserFactory.getBrowserDriver(browser));
        getDriver().manage().window().maximize();

    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }

    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }

}
