package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    WebDriver driver;
    BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriverWait myWait(int duration) {
        return new WebDriverWait(driver, duration);
    }
}
