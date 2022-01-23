package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage{
    private WebDriver driver = null;
    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//button[@aria-label='Close Welcome Banner']")
    WebElement dismissWelcomeBannerButton;
    @FindBy(xpath="//a[@aria-label='dismiss cookie message']")
    WebElement cookieAcceptButton;
    @FindBy(css="button#navbarAccount")
    WebElement navigationAccountButton;
    @FindBy(xpath="//button[@aria-label='Go to login page']")
    WebElement goToLoginPageButton;



    public void dismissWelcomeBanner()  {
        dismissWelcomeBannerButton.click();
    }

    public void acceptCookies()  {
        cookieAcceptButton.click();
    }

    public void proceedToLoginPage()  {
        navigationAccountButton.click();
        goToLoginPageButton.click();
    }


}
