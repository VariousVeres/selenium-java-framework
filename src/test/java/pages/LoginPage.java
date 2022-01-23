package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.pages_object_models_pages.PageObjectMainPage;

public class LoginPage extends PageObjectMainPage {
    private WebDriver driver = null;
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy (xpath = "//a[@href='#/register']")
    WebElement registerButton;

    public void clickRegisterButton() {
        registerButton.click();
    }
}
