package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.pages_object_models_pages.AddressPage;

public class BasketPage extends BasePage{
    WebDriver driver;

    public BasketPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button#checkoutButton")
    WebElement checkoutButton;


    public AddressPage clickCheckout() {
        checkoutButton.click();
        myWait(3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Add a new address']")));
        return new AddressPage(driver);
    }

}
