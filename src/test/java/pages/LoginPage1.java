package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage1 extends BasePage1 {
    public LoginPage1(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@data-test='username']")
    WebElement userNameInput;

    @FindBy(xpath = "//input[@data-test='password']")
    WebElement passwordInput;

    @FindBy(xpath = "//input[@data-test='login-button']")
    WebElement submitLoginButton;

    public void login(String userName, String password)  {
        fillUsername(userName);
        fillPassword(password);
        submitLoginFormButton();
    }

    private void fillUsername(String username) {
        type(userNameInput, username);
    }

    private void fillPassword(String password) {
        type(passwordInput, password);
    }

    private void submitLoginFormButton() {
        click(submitLoginButton);
    }
}
