package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import tests.RegistrationTest;

import java.io.ByteArrayInputStream;

import java.util.UUID;

public class AllureListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult iTestResult)  {
        System.out.println("Executing listener on failure");
        WebDriver driver = RegistrationTest.getDriverFromContext(iTestResult.getTestContext());
        Allure.addAttachment(UUID.randomUUID().toString(), new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Allure.addAttachment(driver.getTitle()+" page structure:", driver.getPageSource());
 }
}
