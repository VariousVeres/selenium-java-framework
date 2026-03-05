package tests;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.InventoryPage;
import pages.LoginPage1;
import utils.ChromeOptionsHelper;
import utils.ConfigManager;

import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginTest extends BaseTest{

    @DataProvider()
    public Object[][] correctLoginData() {
        return new Object[][]{
                {ConfigManager.username(), ConfigManager.password()},
                {"visual_user", ConfigManager.password()}
        };
    }

    @Test(dataProvider = "correctLoginData")
    @Step("Login with user: {user}")
    public void shouldCorrectLoginSuccessfully(String user, String password) {
        getDriver().get(Objects.requireNonNull(ConfigManager.baseUrl(), "base_url is missing"));
        LoginPage1 loginPage = new LoginPage1(getDriver());;
        loginPage.login(user, password);
        InventoryPage inventoryPage  = new InventoryPage(getDriver());
        LOGGER.info("Logged into system");
        assertThat("Inventory container is not present in the inventory page", inventoryPage.isInventoryContainerPresent(), is(true));
    }

    @Test
    @Step("Login with wrong user")
    public void shouldWrongLoginUnsuccessful() {
        getDriver().get(Objects.requireNonNull(ConfigManager.baseUrl(), "base_url is missing"));
        LoginPage1 loginPage = new LoginPage1(getDriver());
        loginPage.login(ConfigManager.username(), "wrong_password");
        LOGGER.info("Tried to log into system with wrong credentials");
        assertThat("Login with incorrect data was successful", loginPage.isDataTestErrorPresentOnPage(), is(true));
    }




}
