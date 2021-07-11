package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver = null;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    By registerButton = By.xpath("//div[@id='content']//a[@href='/join' and (contains(@class,'site-auth'))]");
    By loginButton = By.xpath("//a[@class='site-auth-control']");
    By emailInput = By.xpath("//input[@name='username_or_email']");
    By passwordInput = By.xpath("//input[@type='password']");
    By submitLoginDataButton = By.xpath("//div[@id='mantle_skin']//button[@type='submit']");



    public void logIn() {
        driver.findElement(loginButton).click();
        driver.findElement(emailInput).sendKeys("Whereisveres@gmail.com");
        driver.findElement(passwordInput).sendKeys("Speci@lone1488");
        driver.findElement(submitLoginDataButton).click();
    }

}
