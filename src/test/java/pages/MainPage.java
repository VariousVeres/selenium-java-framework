package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver = null;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    By profileLogo = By.xpath("//nav[@class='masthead']//a[contains(@href,'/user') and (contains(@class,'auth-link'))]");


    public void  clickOnProfileLogo() {
        driver.findElement(profileLogo).click();
    }
}
