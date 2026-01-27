package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

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
        myWait(Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='checkoutButton' and not(@disabled)]")));
        checkoutButton.click();
        return new AddressPage(driver);
    }

}
