package pages;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class AddressPage extends BasePage {
    WebDriver driver;

    public AddressPage(WebDriver driver) {
        super(driver);
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
    @FindBy(xpath = "//app-address-create//input[contains(@data-placeholder,'code')]")
    WebElement zipCodeInput;
    @FindBy(xpath = "//app-address-create//textarea[contains(@data-placeholder,'address')]")
    WebElement addressTextarea;
    @FindBy(xpath = "//app-address-create//input[contains(@data-placeholder,'city')]")
    WebElement cityInput;
    @FindBy(xpath = "//app-address-create//input[contains(@data-placeholder,'state')]")
    WebElement stateInput;
    @FindBy(xpath = "//app-address-create//button[@id='submitButton']")
    WebElement submitAddressData;

    //New address panel
    @FindBy(xpath = "//app-address//mat-table/mat-row")
    List<WebElement> addressSelectionList;
    @FindBy(xpath = "//app-address//button[contains(@aria-label,'Proceed')]")
    WebElement continueWithSelectedAddressButton;

    public void clickAddNewAddressButton() {
        addNewAddressButton.click();
        Wait<WebDriver> wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .withMessage("Adress form has not appeared");
        //Lambda
        wait.until(driver -> driver.findElement(By.xpath("//app-address-create//input[contains(@data-placeholder,'country')]")));
        //Anonim function
        wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver)  {
                return driver.findElement(By.xpath("//app-address-create//input[contains(@data-placeholder,'country')]"));
            }
        });
    }

    public void chooseNthAddressFromSeection(int i) {
        addressSelectionList.get(i - 1).click();
    }

    public DeliveryPage continueWithSelectedAddress() {
        continueWithSelectedAddressButton.click();
        return new DeliveryPage(driver);
    }

    public void fillAddressData(Contact contact) {
        countryInput.sendKeys(contact.getCountry());
        nameInput.sendKeys(contact.getName());
        mobileNumberInput.sendKeys(String.valueOf(contact.getMobileNumber()));
        zipCodeInput.sendKeys(String.valueOf(contact.getZipCode()));
        addressTextarea.sendKeys(contact.getAddress());
        cityInput.sendKeys(contact.getCity());
        stateInput.sendKeys(contact.getState());
        submitAddressData.click();
    }
}
