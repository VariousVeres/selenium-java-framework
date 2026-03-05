package driver;

import org.openqa.selenium.WebDriver;


public class BrowserFactory {
    public static WebDriver getBrowserDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                return new ChromeDriverManager().getDriver();

            case "firefox":
                return new FirefoxDriverManager().getDriver();

            default:
                throw new IllegalArgumentException("Wrong browser: " + browser);
        }


    }
}
