package utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigManager {
    private static final Properties MAIN = loadProperties("main.properties");

    private static final Properties CREDS = loadProperties("credentials.properties");

    private static Properties loadProperties(String fileName) {
        Properties prop = new Properties();

        String projectRoot =
                System.getProperty("project.root", System.getProperty("user.dir"));
        String configDir =
                System.getProperty("config", "src/test/config");

        Path path = Paths.get(projectRoot, configDir, fileName);

        try (InputStream input = Files.newInputStream(path)) {
            prop.load(input);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Cannot load config file: " + path.toAbsolutePath(), e
            );
        }

        return prop;

    }

    private static String required(Properties p, String key) {
        String value = p.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new RuntimeException("Missing required config key: " + key);
        }
        return value;
    }

    public static String baseUrl() {
        return required(MAIN, "base_url");
    }

    public static String username() {
        return required(CREDS, "user.login");
    }

    public static String password() {
        return required(CREDS, "password.login");
    }
}
