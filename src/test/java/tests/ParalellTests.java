package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.io.File;

public class ParalellTests {
    public WebDriver driver;
    @Test
    public void ChromeTest() {
        //Initialize the chrome driver
        System.out.println("The thread ID for Chrome is "+ Thread.currentThread().getId());
        driver = new ChromeDriver();
        driver.get("https://www.google.com");
        driver.findElement(By.xpath("//input[@aria-label='Пошук']")).sendKeys("Henlo");
        driver.findElement(By.xpath("//input[@aria-label='Пошук']")).sendKeys(Keys.ENTER);
        driver.quit();
    }

    @Test
    public void FirefoxTest()  {
        //Initializing the firefox driver (Gecko)
        System.out.println("The thread ID for Firefox is "+ Thread.currentThread().getId());
        System.setProperty("webdriver.gecko.driver", System.getProperty("dataFolder") + File.separator + "geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://www.google.com");
        driver.findElement(By.xpath("//input[@aria-label='Пошук']")).sendKeys("Henlo");
        driver.findElement(By.xpath("//input[@aria-label='Пошук']")).sendKeys(Keys.ENTER);
        driver.quit();
    }

}
