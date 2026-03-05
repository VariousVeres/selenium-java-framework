package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.ChromeOptionsHelper;

public class ChromeDriverCreator extends DriverCreator {
    @Override
    public WebDriver createDriver() {
        ChromeOptions options = ChromeOptionsHelper.getChromeOptions();
        return new ChromeDriver(options);
    }
}
