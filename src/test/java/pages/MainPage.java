package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class MainPage extends BasePage {
    WebDriver driver;

    public MainPage(WebDriver driver) {
        super(driver);
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

    //Review dialog
    @FindBy(xpath = "//mat-dialog-container/app-product-details/mat-dialog-content//textarea")
    WebElement reviewTextArea;
    @FindBy(xpath = "//mat-dialog-container/app-product-details/mat-dialog-content//button[@type='submit']")
    WebElement reviewSubmitButton;
    @FindBy(xpath = "//mat-dialog-container/app-product-details//button[@aria-label='Close Dialog']")
    WebElement reviewBlockCloseButton;
    @FindBy(xpath = "//mat-dialog-container/app-product-details//mat-expansion-panel-header")
    WebElement reviewsExpand;
    @FindBy(xpath = "//mat-dialog-container//mat-panel-title/span[2]")
    WebElement reviewsAmount;
    @FindBy(xpath = "//mat-dialog-container//div[contains(@class,'comment')]//cite")
    List<WebElement> reviewsAuthorsNamesList;
    @FindBy(xpath = "//mat-dialog-container//div[contains(@class,'comment')]//p")
    List<WebElement> reviewsTextsList;


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
        myWait(1).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-dialog-container/app-product-details/mat-dialog-content")));
    }

    public void addProductReview(String review) {
        reviewTextArea.sendKeys(review);
        reviewSubmitButton.click();
    }

    public void expandReviews() {
        reviewsExpand.click();
    }

    public void closeReviewBlock() {
        reviewBlockCloseButton.click();
    }

    public List<String> getReviewsAuthorsNamesList() {
        return reviewsAuthorsNamesList.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getReviewsTextsList() {
        return reviewsTextsList.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public int getReviewsAmount() {
        String reviews = reviewsAmount.getText();
        return Integer.parseInt(reviews.substring(1, reviews.length() - 1));
    }

}
