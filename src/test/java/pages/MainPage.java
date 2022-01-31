package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage {
    WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@aria-label='Close Welcome Banner']")
    WebElement dismissWelcomeBannerButton;
    @FindBy(xpath = "//a[@aria-label='dismiss cookie message']")
    WebElement cookieAcceptButton;
    @FindBy(css = "button#navbarAccount")
    WebElement navigationAccountButton;
    @FindBy(xpath = "//button[@aria-label='Go to login page']")
    WebElement goToLoginPageButton;
    @FindBy(xpath = "//mat-grid-tile/div/mat-card")
    List<WebElement> productsList;


    public void dismissWelcomeBanner() {
        dismissWelcomeBannerButton.click();
    }

    public void acceptCookies() {
        cookieAcceptButton.click();
    }

    public LoginPage proceedToLoginPage() {
        navigationAccountButton.click();
        goToLoginPageButton.click();
        return new LoginPage(driver);
    }

    public void clickOnNthProduct(int i) {
        productsList.get(i - 1).click();
    }


}
