package driver;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {

    public abstract WebDriver createDriver();

    public WebDriver getDriver() {
        return createDriver();
    }

}
