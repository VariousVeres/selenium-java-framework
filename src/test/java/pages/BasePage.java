package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    WebDriver driver;
    BasePage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//app-navbar//mat-toolbar//button[@aria-label=\"Back to homepage\"]")
    WebElement back2HomepageButton;

    public WebDriverWait myWait(int duration) {
        return new WebDriverWait(driver, duration);
    }

    public void returnToMainPage() {
        back2HomepageButton.click();
    }
}
