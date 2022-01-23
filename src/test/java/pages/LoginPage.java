package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.pages_object_models_pages.PageObjectMainPage;

import static org.junit.Assert.assertEquals;

public class LoginPage extends PageObjectMainPage {
    private WebDriver driver = null;
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy (name = "username")
    WebElement userNameInput;
    @FindBy (name = "password")
    WebElement passwordInput;
    @FindBy (xpath = "//button[@type='submit']")
    WebElement submitLoginDataButton;


    public void logIn() {
        userNameInput.sendKeys("Whereisveres@gmail.com");
        passwordInput.sendKeys("Speci@lone86");
        submitLoginDataButton.click();
        assertEquals("Url is incorrect",driver.getCurrentUrl(),"https://qa.nwlegal.org/" );
    }
}
