package pages.pages_object_models_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjectBasePage {
    WebDriver driver;
    PageObjectBasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriverWait myWait(int duration) {
        return new WebDriverWait(driver, duration);
    }

}
