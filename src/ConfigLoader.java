import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static ConfigLoader instance;

    public Properties properties;

    private ConfigLoader() {
        properties = new Properties();
        String configPropertiesFileName = "config.properties";

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configPropertiesFileName)) {
            if (input == null) {
                throw new IOException("Unable to find configuration file: " + configPropertiesFileName);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file");
        }
    }

    public static ConfigLoader getInstance() {
        if (instance == null) {
            instance = new ConfigLoader();
        }

        return instance;
    }

}
