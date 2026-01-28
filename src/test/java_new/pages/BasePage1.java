package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage1 {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Duration EXPLICIT_WAIT_DURATION = Duration.ofSeconds(5);

    @FindBy(xpath = "//*[@data-test='error']")
    WebElement dataTestError;

    BasePage1(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, EXPLICIT_WAIT_DURATION);
        PageFactory.initElements(driver, this);
    }

    protected void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void type(WebElement element, String text) {
        waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public boolean isDataTestErrorPresentOnPage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(dataTestError));
            return dataTestError.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
