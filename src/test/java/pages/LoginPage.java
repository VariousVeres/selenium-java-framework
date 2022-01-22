package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.Assert.*;

public class LoginPage extends MainPage {
    private WebDriver driver = null;
    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    By userNameInput = By.name("username");
    By passwordInput = By.name("password");
    By submitLoginDataButton = By.xpath("//button[@type='submit']");

    public void logIn() {
        driver.findElement(userNameInput).sendKeys("Whereisveres@gmail.com");
        driver.findElement(passwordInput).sendKeys("Speci@lone86");
        driver.findElement(submitLoginDataButton).click();
        explicitWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        assertEquals("Url is incorrect",driver.getCurrentUrl(),"https://qa.nwlegal.org/" );
    }

}
