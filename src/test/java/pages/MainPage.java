package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver = null;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriverWait explicitWait() {
        return new WebDriverWait(driver, 10);
    }
}
