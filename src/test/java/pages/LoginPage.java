package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input#email")
    WebElement emailInput;
    @FindBy(css = "input#password")
    WebElement passwordInput;
    @FindBy(css = "button#loginButton")
    WebElement loginButton;
    @FindBy(xpath = "//a[@href='#/register']")
    WebElement registerButton;

    public RegistrationPage clickRegisterButton() {
        registerButton.click();
        return new RegistrationPage(driver);
    }

    public MainPage logIn(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();
        myWait(Duration.ofSeconds(5)).until(ExpectedConditions.urlContains("search"));
        return new MainPage(driver);
    }

}
