package tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage1;
import utils.ConfigManager;
import java.util.Objects;

//@Listeners({AllureListener.class})
public class MainTest1 {

    LoginPage1 loginPage;
    WebDriver webdriver;

    @BeforeClass
    public void setUp() {
        webdriver = new ChromeDriver();
    }

    @Test(priority = 1)
    public void login() {
        webdriver.get(Objects.requireNonNull(ConfigManager.baseUrl(), "base_url is missing"));
        loginPage = new LoginPage1(webdriver);
        loginPage.login(ConfigManager.username(), ConfigManager.password());

    }

    @AfterClass
    public void tearDown() {
        webdriver.quit();
    }

//    @Test(priority = 2)
//    public void addReviewToSecondProduct() {
//        mainPage = new MainPage(driver);
//        mainPage.clickOnNthProduct(2);
//        mainPage.addProductReview("The best product I've ever used");
//        mainPage.expandReviews();
//        assertThat("Authors email is not present", mainPage.getReviewsAuthorsNamesList().contains(Dictionary.EMAIL), equalTo(true));
//        assertThat("Authors review is not present", mainPage.getReviewsTextsList().contains("The best product I've ever used"), equalTo(true));
//        mainPage.closeReviewBlock();
//    }

//    @Test(priority = 3, dependsOnMethods = "checkTokenInLocalStorage")
//    public void addReviewToSecondProductViaAPI(ITestContext iTestContext) {
//        mainPage = new MainPage(driver);
//        mainPage.clickOnNthProduct(2);
//        mainPage.expandReviews();
//        int reviewsAmount = mainPage.getReviewsAmount();
//        mainPage.closeReviewBlock();
//        given().when().header("Authorization", "Bearer " + token).body("{\"message\":\"My review text\",\"author\":\"test_mail@eleks.com\"}")
//                .put(Helper.getProperty("host.url") + "/rest/products/24/reviews")
//                .then().statusCode(HttpStatus.SC_CREATED);
//        mainPage.clickOnNthProduct(2);
//        assertThat("Review wasn't added by API request", mainPage.getReviewsAmount(), equalTo(reviewsAmount + 1));
//        mainPage.closeReviewBlock();
//    }
//
//    @Test(priority = 4)
//    @Parameters({"product1Value"})
//    public void orderWithProduct() {
//        mainPage = new MainPage(driver);
//        mainPage.addProductToBasket("Carrot Juice");
//        BasketPage basketPage = mainPage.openBasket();
//        AddressPage addressPage = basketPage.clickCheckout();
//        addressPage.clickAddNewAddressButton();
//        Contact.ContactBuilder cb = new Contact.ContactBuilder();
//        Contact c = cb.buildContact();
//        addressPage.fillAddressData(c);
//        addressPage.chooseNthAddressFromSeection(1);
//        DeliveryPage deliveryPage = addressPage.continueWithSelectedAddress();
//        deliveryPage.selectDelivery(DeliveryPage.DeliveryMethod.STANDART);
//        PaymentPage paymentPage = deliveryPage.proceedWithSelectedDelivery();
//        paymentPage.addCreditCard(PaymentPage.PaymentMethod.CREDIT_CARD);
//        OrderSummaryPage summaryPage = paymentPage.submitNthCardAndContinue(1);
//        OrderCompletionPage completionPage = summaryPage.completePurchase();
//        assertThat("Wrong product name in order completion page", completionPage.getProductName(), containsString("Carrot Juice"));
//        completionPage.returnToMainPage();
//
//    }
//
//    @Test(priority = 5, dataProvider = "product-names", dataProviderClass = DataProviders.class)
//    public void orderWithProductByName(String name) {
//        mainPage = new MainPage(driver);
//        mainPage.addProductToBasket(name);
//        BasketPage basketPage = mainPage.openBasket();
//        AddressPage addressPage = basketPage.clickCheckout();
//        addressPage.clickAddNewAddressButton();
//        Contact.ContactBuilder cb = new Contact.ContactBuilder();
//        Contact c = cb.buildContact();
//        addressPage.fillAddressData(c);
//        addressPage.chooseNthAddressFromSeection(1);
//        DeliveryPage deliveryPage = addressPage.continueWithSelectedAddress();
//        deliveryPage.selectDelivery(DeliveryPage.DeliveryMethod.STANDART);
//        PaymentPage paymentPage = deliveryPage.proceedWithSelectedDelivery();
//        paymentPage.addCreditCard(PaymentPage.PaymentMethod.CREDIT_CARD);
//        OrderSummaryPage summaryPage = paymentPage.submitNthCardAndContinue(1);
//        OrderCompletionPage completionPage = summaryPage.completePurchase();
//        assertThat("Wrong product name in order completion page", completionPage.getProductName(), containsString(name));
//        completionPage.returnToMainPage();


}
