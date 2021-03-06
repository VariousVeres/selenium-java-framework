package pages.pages_object_models_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageObjectLoginPage extends PageObjectBasePage {
    private WebDriver driver = null;

    public PageObjectLoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    By registerButton = By.xpath("//a[@href='#/register']");


    public PageObjectRegistrationPage clickRegisterButton() {
        driver.findElement(registerButton).click();
        return new PageObjectRegistrationPage(driver);
    }

}
