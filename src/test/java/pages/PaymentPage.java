package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.Helper;

import java.util.List;

import static utils.Logging.LOGGER;

public class PaymentPage {
    public enum PaymentMethod  {
        CREDIT_CARD
    }
    WebDriver driver;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//mat-cell[contains(@class,'Expiry')]/ancestor::mat-row")
    WebElement alreadySavedCard;
    @FindBy (xpath = "//mat-cell[contains(@class,'Selection')]//label")
    List<WebElement> cardsListRadioButtonsList;
    @FindBy (xpath = "//app-payment-method/div//mat-expansion-panel")
    WebElement expandAddNewCardPanel;
    @FindBy (xpath = "//app-payment-method/div//mat-form-field[1]//input")
    WebElement creditCardNameInput;
    @FindBy (xpath = "//app-payment-method/div//mat-form-field[2]//input")
    WebElement creditCardNumberInput;
    @FindBy (xpath = "//app-payment-method/div//mat-form-field[3]//select")
    WebElement creditCardExpirationMonthSelect;
    @FindBy (xpath = "//app-payment-method/div//mat-form-field[4]//select")
    WebElement creditCardExpirationYearSelect;
    @FindBy (xpath = "//app-payment-method/div//button[@type='submit']")
    WebElement creditCardSubmitButton;
    @FindBy (xpath = "//button[@aria-label='Proceed to review']")
    WebElement continueButton;

    public void addCreditCard(PaymentMethod paymentMethod) {
        switch (paymentMethod) {
            case CREDIT_CARD:
                expandAddNewCardPanel.click();
                creditCardNameInput.sendKeys(Helper.getProperty("card.name"));
                creditCardNumberInput.sendKeys(Helper.getProperty("card.number"));
                Select monthSelect = new Select(creditCardExpirationMonthSelect);
                monthSelect.selectByValue(Helper.getProperty("card.month"));
                Select yearSelect = new Select(creditCardExpirationYearSelect);
                yearSelect.selectByValue(Helper.getProperty("card.year"));
                creditCardSubmitButton.click();
                alreadySavedCard.click();
                break;
            default:
                LOGGER.info("Wrong payment method");
        }
    }

    public OrderSummaryPage submitNthCardAndContinue(int i)  {
        cardsListRadioButtonsList.get(i-1).click();
        continueButton.click();
        return new OrderSummaryPage(driver);
    }
}
