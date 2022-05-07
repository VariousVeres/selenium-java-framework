package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderCompletionPage extends BasePage {
    OrderCompletionPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//mat-cell[contains(@class,'mat-column-product')]")
    WebElement productName;

    public String getProductName() {
        return productName.getText();
    }

}