package tests;

import models.Contact;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import pages.BasketPage;
import pages.MainPage;
import pages.AddressPage;
import utils.Dictionary;
import utils.Helper;

import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MainTest extends RegistrationTest {
    MainPage mainPage;
    String token;
    int reviewsAmount;


    @Test(priority = 1, alwaysRun = true)
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
        assertThat("Authors name is not present", mainPage.getReviewsAuthorsNamesList().contains(Dictionary.EMAIL), equalTo(true));
        assertThat("Authors review is not present", mainPage.getReviewsTextsList().contains("The best product I've ever used"), equalTo(true));
        reviewsAmount = mainPage.getReviewsAmount();
        mainPage.closeReviewBlock();
    }

    @Test(priority = 3)
    public void addReviewToSecondProductViaAPI() {
        given().when().header("Authorization", "Bearer " + token).body("{\"message\":\"My review text\",\"author\":\"test_mail@eleks.com\"}")
                .put(Helper.getProperty("host.url") + "/rest/products/24/reviews")
                .then().statusCode(HttpStatus.SC_CREATED).extract().response();
        mainPage.clickOnNthProduct(2);
        assertThat("Review wasn't added by API request", mainPage.getReviewsAmount(), equalTo(reviewsAmount + 1));
        mainPage.closeReviewBlock();
    }

    @Test(priority = 4)
    public void orderWithNthProduct() {
        mainPage = new MainPage(driver);
        mainPage.clickAddNthProductToBasket(5);
        BasketPage basketPage = mainPage.openBasket();
        AddressPage addressPage = basketPage.clickCheckout();
        addressPage.clickAddNewAddressButton();
        Contact.ContactBuilder cb = new Contact.ContactBuilder();
        Contact c = cb.buildContact();
        addressPage.fillAddressData(c);
    }


}
