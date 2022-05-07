package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DeliveryPage {
    public enum DeliveryMethod {
        ONE_DAY, FAST, STANDART;
    }

    WebDriver driver;

    public DeliveryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//app-delivery-method//mat-row")
    List<WebElement> deliverySelectionList;
    @FindBy(xpath = "//app-delivery-method//button[contains(@aria-label,'Proceed')]")
    WebElement continueWithSelectedDelivery;

    public void selectDelivery(DeliveryMethod deliveryMethod) {
        deliverySelectionList.get(deliveryMethod.ordinal()).click();
    }

    public PaymentPage proceedWithSelectedDelivery() {
        continueWithSelectedDelivery.click();
        return new PaymentPage(driver);
    }

}
