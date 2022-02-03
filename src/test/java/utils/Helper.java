package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.security.SecureRandom;
import java.util.Properties;


public class Helper {

    public static String getAlphanumericStringWithLength(int length) {
        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public static String getTokenFromLocalStorage(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return window.localStorage.getItem('token');");
    }

    public static String getProperty(String propName) {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("src/test/resources/config.properties")) {
            prop.load(input);
        } catch (IOException io) {
            io.printStackTrace();
        }
        return prop.getProperty(propName);
    }

}
