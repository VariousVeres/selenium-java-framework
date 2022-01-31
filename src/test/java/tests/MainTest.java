package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.Helper;

import java.util.regex.Pattern;

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

    @Test(dependsOnMethods = {"login"})
    public void checkTokenInLocalStorage() {
        String token = Helper.getTokenFromLocalStorage(driver);
        boolean matches = Pattern.matches("^\\w{36}.\\w{555}.+", token);
        assertThat("Wrong token in local storage", matches, equalTo(true));
    }

}
