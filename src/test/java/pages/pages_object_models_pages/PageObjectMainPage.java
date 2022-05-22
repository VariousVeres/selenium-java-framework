package pages.pages_object_models_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjectMainPage extends PageObjectBasePage {
    private WebDriver driver;
    public PageObjectMainPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    By dismissWelcomeBannerButton = By.xpath("//button[@aria-label='Close Welcome Banner']");
    By cookieAcceptButton = By.xpath("//a[@aria-label='dismiss cookie message']");
    By navigationAccountButton = By.cssSelector("button#navbarAccount");
    By goToLoginPageButton = By.xpath("//button[@aria-label='Go to login page']");

    public WebDriverWait explicitWait() {
        return new WebDriverWait(driver, 10);
    }

    public void dismissWelcomeBanner()  {
        driver.findElement(dismissWelcomeBannerButton).click();
    }

    public void acceptCookies()  {
        driver.findElement(cookieAcceptButton).click();
    }

    public PageObjectLoginPage proceedToLoginPage()  {
        driver.findElement(navigationAccountButton).click();
        driver.findElement(goToLoginPageButton).click();
        return new PageObjectLoginPage(driver);
    }

}
