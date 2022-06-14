package tests;

import models.Contact;
import org.apache.http.HttpStatus;
import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;
import utils.AllureListener;
import utils.DataProviders;
import utils.Dictionary;
import utils.Helper;

import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


@Listeners({AllureListener.class})
public class MainTest extends RegistrationTest {
    MainPage mainPage;
    String token;

    @Test(priority = 1)
    public void checkTokenInLocalStorage() {
        token = Helper.getTokenFromLocalStorage(driver);
        boolean matches = Pattern.matches("^\\w{36}.\\w{555}.+", token);
        assertThat("Wrong token in local storage", matches, equalTo(true));
    }

    @Test(priority = 2)
    public void addReviewToSecondProduct() {
        mainPage = new MainPage(driver);
        mainPage.clickOnNthProduct(2);
        mainPage.addProductReview("The best product I've ever used");
        mainPage.expandReviews();
        assertThat("Authors email is not present", mainPage.getReviewsAuthorsNamesList().contains(Dictionary.EMAIL), equalTo(true));
        assertThat("Authors review is not present", mainPage.getReviewsTextsList().contains("The best product I've ever used"), equalTo(true));
        mainPage.closeReviewBlock();
    }

    @Test(priority = 3, dependsOnMethods = "checkTokenInLocalStorage")
    public void addReviewToSecondProductViaAPI(ITestContext iTestContext) {
        mainPage = new MainPage(driver);
        mainPage.clickOnNthProduct(2);
        mainPage.expandReviews();
        int reviewsAmount = mainPage.getReviewsAmount();
        mainPage.closeReviewBlock();
        given().when().header("Authorization", "Bearer " + token).body("{\"message\":\"My review text\",\"author\":\"test_mail@eleks.com\"}")
                .put(Helper.getProperty("host.url") + "/rest/products/24/reviews")
                .then().statusCode(HttpStatus.SC_CREATED);
        mainPage.clickOnNthProduct(2);
        assertThat("Review wasn't added by API request", mainPage.getReviewsAmount(), equalTo(reviewsAmount + 1));
        mainPage.closeReviewBlock();
    }

    @Test(priority = 4)
    @Parameters({"product1Value"})
    public void orderWithProduct() {
        mainPage = new MainPage(driver);
        mainPage.addProductToBasket("Carrot Juice");
        BasketPage basketPage = mainPage.openBasket();
        AddressPage addressPage = basketPage.clickCheckout();
        addressPage.clickAddNewAddressButton();
        Contact.ContactBuilder cb = new Contact.ContactBuilder();
        Contact c = cb.buildContact();
        addressPage.fillAddressData(c);
        addressPage.chooseNthAddressFromSeection(1);
        DeliveryPage deliveryPage = addressPage.continueWithSelectedAddress();
        deliveryPage.selectDelivery(DeliveryPage.DeliveryMethod.STANDART);
        PaymentPage paymentPage = deliveryPage.proceedWithSelectedDelivery();
        paymentPage.addCreditCard(PaymentPage.PaymentMethod.CREDIT_CARD);
        OrderSummaryPage summaryPage = paymentPage.submitNthCardAndContinue(1);
        OrderCompletionPage completionPage = summaryPage.completePurchase();
        assertThat("Wrong product name in order completion page", completionPage.getProductName(), containsString("Carrot Juice"));
        completionPage.returnToMainPage();

    }

    @Test(priority = 5, dataProvider = "product-names", dataProviderClass = DataProviders.class)
    public void orderWithProductByName(String name) {
        mainPage = new MainPage(driver);
        mainPage.addProductToBasket(name);
        BasketPage basketPage = mainPage.openBasket();
        AddressPage addressPage = basketPage.clickCheckout();
        addressPage.clickAddNewAddressButton();
        Contact.ContactBuilder cb = new Contact.ContactBuilder();
        Contact c = cb.buildContact();
        addressPage.fillAddressData(c);
        addressPage.chooseNthAddressFromSeection(1);
        DeliveryPage deliveryPage = addressPage.continueWithSelectedAddress();
        deliveryPage.selectDelivery(DeliveryPage.DeliveryMethod.STANDART);
        PaymentPage paymentPage = deliveryPage.proceedWithSelectedDelivery();
        paymentPage.addCreditCard(PaymentPage.PaymentMethod.CREDIT_CARD);
        OrderSummaryPage summaryPage = paymentPage.submitNthCardAndContinue(1);
        OrderCompletionPage completionPage = summaryPage.completePurchase();
        assertThat("Wrong product name in order completion page", completionPage.getProductName(), containsString(name));
        completionPage.returnToMainPage();
    }


}
