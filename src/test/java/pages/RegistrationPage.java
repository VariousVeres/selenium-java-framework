package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class RegistrationPage extends BasePage{
    WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy (id="emailControl")
    WebElement emailInput;
    @FindBy (id="passwordControl")
    WebElement passwordInput;
    @FindBy (id="repeatPasswordControl")
    WebElement passwordRepeatInput;
    @FindBy (xpath="//div[@class='security-container']/mat-form-field[1]//div[contains(@class,'field-flex')]")
    WebElement secretQuestionBlock;
    @FindBy (id="repeatPasswordControl")
    List<WebElement> secretQuestionAnswersList;
    @FindBy (id="securityAnswerControl")
    WebElement secretQuestionAnswerInput;
    @FindBy (id="registerButton")
    WebElement submitRegistrationData;

    public void register(String email, String password, int questionType, String answer) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        passwordRepeatInput.sendKeys(password);
        secretQuestionBlock.click();
        secretQuestionAnswersList.get(questionType - 1).click();
        secretQuestionAnswerInput.sendKeys(answer);
        submitRegistrationData.click();
        myWait(3).until(ExpectedConditions.invisibilityOf(submitRegistrationData));
        assertEquals("Wrong login page url", driver.getCurrentUrl(), "http://localhost:3000/#/login");
    }
}
