package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import utils.Dictionary;
import utils.Helper;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static utils.Logging.LOGGER;

public class RegistrationTest {
    public WebDriver driver = null;
    MainPage mainPage;

    @BeforeSuite
    public void driverInitialization(ITestContext iTestContext) {
        ChromeOptions options = new ChromeOptions();
        //Uncomment for headless or incognito browser
//        options.addArguments("--headless", "--window-size=1920,1650");
//        options.addArguments("--incognito");
        options.addExtensions(new File(System.getProperty("dataFolder") + File.separator + Helper.getProperty("fake_filler_file_name")));
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);


//        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(options);
        setDriverIntoContext(iTestContext, driver);
        //Uncomment for more complicated driver with options
//        driver = Helper.createDriverWithOptions();
        driver.manage().window().setSize(new Dimension(1920, 1650));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @BeforeSuite
    public void registrationAndLogIn(ITestContext iTestContext) {
        driver.get(Helper.getProperty("host.url"));
        LOGGER.info("Opening main page");
        mainPage = new MainPage(driver);
        mainPage.dismissWelcomeBanner();
        mainPage.acceptCookies();
        LoginPage loginPage = mainPage.proceedToLoginPage();
        LOGGER.info("Proceeding to the login page");
        assertThat("Wrong Login page url", driver.getCurrentUrl(), equalTo("http://localhost:3000/#/login"));
        RegistrationPage registrationPage = loginPage.clickRegisterButton();
        LOGGER.info("Choose new registrationAndLogIn");
        assertThat("Wrong Registration page url", driver.getCurrentUrl(), equalTo("http://localhost:3000/#/register"));
        String email = Helper.getAlphanumericStringWithLength(7) + "@gmail.com";
        String password = Helper.getAlphanumericStringWithLength(8);
        Dictionary.setEMAIL(email);
        Dictionary.setPASSWORD(password);
        registrationPage.register(email, password, 1, "asd");
        LOGGER.info("New user with [{}] was registered", email);
        mainPage = loginPage.logIn(Dictionary.EMAIL, Dictionary.PASSWORD);
        LOGGER.info("User with [{}] was logged in the system", Dictionary.EMAIL);
        assertThat("Wrong search page url", driver.getCurrentUrl(), equalTo("http://localhost:3000/#/search"));
    }

    public static void setDriverIntoContext(ITestContext iTestContext, WebDriver driver)   {
        iTestContext.setAttribute("WebDriver", driver);
    }

    public static WebDriver getDriverFromContext(ITestContext iTestContext)  {
        return (WebDriver) iTestContext.getAttribute("WebDriver");
    }
    public void registrationAndLogInAPI() {
        driver.get("http://localhost:3000");
        String email = Helper.getAlphanumericStringWithLength(7) + "@gmail.com";
        String password = Helper.getAlphanumericStringWithLength(8);
        Response regResponse = given().when().body("{\"email\":\"" + email + "\",\"password\":\"" + password + "\",\"passwordRepeat\":\"" + password + "\",\"securityQuestion\":{\"id\":1,\"question\":\"Your eldest siblings middle name?\",\"createdAt\":\"2022-05-06T15:10:09.398Z\",\"updatedAt\":\"2022-05-06T15:10:09.398Z\"},\"securityAnswer\":\"asd\"}")
                .post(Helper.getProperty("host.url") + "/api/Users")
                .then().statusCode(HttpStatus.SC_CREATED).extract().response();
        LOGGER.info("Registration response: \n " + regResponse.asString());

        int userId = regResponse.path("data.id");
        Response secQuestionResponse = given().when().body("{\"UserId\":" + userId + ",\"answer\":\"asd\",\"SecurityQuestionId\":1}")
                .post(Helper.getProperty("host.url") + "/api/SecurityAnswers/")
                .then().statusCode(HttpStatus.SC_CREATED).extract().response();
        LOGGER.info("SecurityAnswers \n: " + secQuestionResponse.asString());

        Response loginResponse = given().when().body("{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}").post(Helper.getProperty("host.url") + "/rest/user/login")
                .then().extract().response();
        LOGGER.info("Token \n: " + loginResponse);
    }

    @AfterSuite
    public void afterTests() {
        driver.close();
        if (driver != null) {
            driver.quit();
        }
    }

}
