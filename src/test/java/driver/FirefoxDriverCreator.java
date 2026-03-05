package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverCreator extends DriverCreator {
    @Override
    public WebDriver createDriver() {
        return new FirefoxDriver();
    }
}
