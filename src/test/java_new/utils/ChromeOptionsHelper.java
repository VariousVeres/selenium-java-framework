package utils;

import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class ChromeOptionsHelper {

    public static ChromeOptions getChromeOptions()  {
        // Create a HashMap to store the preferences
        Map<String, Object> prefs = new HashMap<>();

        // Disable the password manager and credential services
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        // This specific preference helps disable the "password compromised" pop-up
        prefs.put("profile.password_manager_leak_detection", false);

        ChromeOptions options = new ChromeOptions();

        // Set the experimental options (preferences)
        options.setExperimentalOption("prefs", prefs);
       // Maximize window, disable notifications)
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        return options;
    }
}
