package driver;

import org.openqa.selenium.WebDriver;

public abstract class DriverCreator {

    public abstract WebDriver createDriver();

    public WebDriver getDriver() {
        return createDriver();
    }

}
