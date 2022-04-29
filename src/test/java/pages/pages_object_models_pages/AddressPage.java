package pages.pages_object_models_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddressPage {
    WebDriver driver;

    public AddressPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@aria-label='Add a new address']")
    WebElement addNewAddressButton;
    //New address panel
    @FindBy(xpath = "//app-address-create//input[contains(@data-placeholder,'country')]")
    WebElement countryInput;
    @FindBy(xpath = "//app-address-create//input[contains(@data-placeholder,'name')]")
    WebElement nameInput;
    @FindBy(xpath = "//app-address-create//input[contains(@data-placeholder,'mobile')]")
    WebElement mobileNumberInput;
    @FindBy(xpath = "//app-address-create//textarea[contains(@data-placeholder,'address')]")
    WebElement addressTextarea;
    @FindBy(xpath = "//app-address-create//input[contains(@data-placeholder,'city')]")
    WebElement cityInput;
    @FindBy(xpath = "//app-address-create//input[contains(@data-placeholder,'state')]")
    WebElement stateInput;
    @FindBy(xpath = "//app-address-create//button[@id='submitButton']")
    WebElement submitAddressData;


    public void clickAddNewAddressButton() {
        addNewAddressButton.click();
        WebDriverWait wait= new WebDriverWait(driver,3);
        wait.withMessage("Address page was not displayed").until(driver->driver.findElement(By.xpath("//app-address-create//input[contains(@data-placeholder,'country')]")));
    }

    public void fillAddressData()  {
        WebDriverWait w = new WebDriverWait(driver, 12);
        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("asd")));
        String s;
    }
}
