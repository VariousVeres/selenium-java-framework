package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderSummaryPage extends BasePage {
    OrderSummaryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@aria-label='Complete your purchase']")
    WebElement completePurchaseButton;


    public OrderCompletionPage completePurchase() {
        completePurchaseButton.click();
        myWait(3).until(driver->driver.getCurrentUrl().contains("order-completion"));
        return new OrderCompletionPage(driver);
    }
}
