package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.pages_object_models_pages.AddressPage;

public class BasketPage {
    WebDriver driver;

    public BasketPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button#checkoutButton")
    WebElement checkoutButton;


    public AddressPage clickCheckout() {
        checkoutButton.click();
        return new AddressPage(driver);
    }

}
