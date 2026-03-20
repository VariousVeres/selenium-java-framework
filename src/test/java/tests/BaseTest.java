package tests;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseTest {
    protected final Logger LOGGER = LoggerFactory.getLogger(LoginTest.class);

    private static final ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return webDriverThreadLocal.get();
    }

    public static void setDriver(WebDriver driver) {
        webDriverThreadLocal.set(driver);
    }

    public ThreadLocal<WebDriver> getThreadLocal() {
        return webDriverThreadLocal;
    }

}
