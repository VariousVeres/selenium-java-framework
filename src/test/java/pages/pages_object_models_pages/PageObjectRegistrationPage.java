package pages.pages_object_models_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertEquals;

public class PageObjectRegistrationPage extends PageObjectBasePage {
    WebDriver driver;

    public PageObjectRegistrationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    By emailInput = By.id("emailControl");
    By passwordInput = By.id("passwordControl");
    By passwordRepeatInput = By.id("repeatPasswordControl");
    By secretQuestionBlock = By.xpath("//div[@class='security-container']/mat-form-field[1]//div[contains(@class,'field-flex')]");
    By secretQuestionAnswersList = By.xpath("//div[contains(@class,'cdk-overlay-container')]//div[@role='listbox']/mat-option");
    By secretQuestionAnswerInput = By.id("securityAnswerControl");
    By submitRegistrationData = By.id("registerButton");

    public void register(String email, String password, int questionType, String answer) {
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(passwordRepeatInput).sendKeys(password);
        driver.findElement(secretQuestionBlock).click();
        driver.findElements(secretQuestionAnswersList).get(questionType - 1).click();
        driver.findElement(secretQuestionAnswerInput).sendKeys(answer);
        driver.findElement(submitRegistrationData).click();
        myWait(3).until(ExpectedConditions.invisibilityOf(driver.findElement(submitRegistrationData)));
        assertEquals("Wrong login page url", driver.getCurrentUrl(), "http://localhost:3000/#/login");
    }

}
