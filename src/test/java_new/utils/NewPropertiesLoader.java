package utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class NewPropertiesLoader {

    private final Properties prop = new Properties();

    public NewPropertiesLoader(String configFileName) {
        String projectRoot = System.getProperty("user.dir");
        String configDir = System.getProperty("config", "src/test/config");

        Path path = Paths.get(projectRoot, configDir, configFileName);

        try (InputStream input = Files.newInputStream(path)) {
            prop.load(input);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Cannot load config file: " + path.toAbsolutePath(), e
            );
        }
    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }
}
