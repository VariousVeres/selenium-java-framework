package pages;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class RegistrationPage extends BasePage{
    public RegistrationPage(WebDriver driver) {
        super(driver);
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
    @FindBy (xpath="//div[contains(@class,'cdk-overlay-container')]//div[@role='listbox']/mat-option")
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
        myWait(Duration.ofSeconds(3)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("submitRegistrationData")));
        assertThat("Wrong login page url", driver.getCurrentUrl(), equalTo("http://localhost:3000/#/login"));
    }
}
