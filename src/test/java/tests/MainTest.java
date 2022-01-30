package tests;

import io.restassured.RestAssured;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.hc.core5.http.HttpStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.html5.LocalStorage;
import org.testng.annotations.Test;
import pages.LoginPage;

import static io.restassured.RestAssured.given;
import static utils.Logging.LOGGER;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MainTest extends RegistrationTest {
    LoginPage loginPage = null;

    @Test
    void login() {
        loginPage = new LoginPage(driver);
        loginPage.logIn(email, password);
        LOGGER.info("User with [{}] was logged in the system", email);
        assertThat("Wrong search page url", driver.getCurrentUrl(), equalTo("http://localhost:3000/#/search"));
    }

    @Test
    public void tryREST() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String token = (String) js.executeScript("return window.localStorage.getItem('token');");
//        RequestSpecification reqSpec = RestAssured.given();
//        reqSpec.baseUri("")
//        Response res = given().when().header("Authorization", "Bearer " + token).contentType("application/json")
//                .body("{\"ProductId\":1,\"BasketId\":\"25\",\"quantity\":1}")
//                .post("http://localhost:3000/api/BasketItems/")
//                .then().extract().response();
//
//        LOGGER.info(res.asString());
//        LOGGER.info(res.getHeaders().toString());
//        LOGGER.info(""+res.getStatusCode());

        Response res = given().when().header("Authorization", "Bearer " + token)//
                .get("http://localhost:3000/rest/basket/")
                .then().extract().response();
        LOGGER.info(res.asString());

        given().when().get("https://google.com").then().statusCode(HttpStatus.SC_OK);

    }

}
