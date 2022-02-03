package tests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import utils.Dictionary;
import utils.Helper;

import java.util.Properties;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static utils.Logging.LOGGER;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MainTest extends RegistrationTest {
    MainPage mainPage;
    String token;

    @Test(priority = 1)
    void login() {
        LoginPage loginPage = new LoginPage(driver);
        mainPage = loginPage.logIn(Dictionary.EMAIL, Dictionary.PASSWORD);
        LOGGER.info("User with [{}] was logged in the system", Dictionary.EMAIL);
        assertThat("Wrong search page url", driver.getCurrentUrl(), equalTo("http://localhost:3000/#/search"));
    }

    @Test(dependsOnMethods = {"login"})
    public void checkTokenInLocalStorage() {
        token = Helper.getTokenFromLocalStorage(driver);
        boolean matches = Pattern.matches("^\\w{36}.\\w{555}.+", token);
        assertThat("Wrong token in local storage", matches, equalTo(true));
    }

    @Test(priority = 2)
    public void addReviewToSecondProduct() {
        mainPage.clickOnNthProduct(2);
        mainPage.addProductReview("The best product I've ever used");
        mainPage.expandReviews();
        assertThat("Authors name is not present", mainPage.getReviewsAuthorsNamesList().contains(Dictionary.EMAIL), equalTo(true));
        assertThat("Authors review is not present", mainPage.getReviewsTextsList().contains("The best product I've ever used"), equalTo(true));
        mainPage.closeReviewBlock();
    }

    @Test(priority = 3)
    public void addReviewToSecondProductViaAPI() {
        given().when().header("Authorization", "Bearer " + token).body("{\"message\":\"My review text\",\"author\":\"test_mail@eleks.com\"}")
                .put(Helper.getProperty("host.url") + "/rest/products/ 24 /reviews")
                .then().statusCode(HttpStatus.SC_CREATED).extract().response();
        mainPage.clickOnNthProduct(2);
        mainPage.expandReviews();
        //Add assert for reviews count
    }


}
