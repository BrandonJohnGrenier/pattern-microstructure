package fm.pattern.microstructure.repository;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class PropertiesFileValidationRepository implements ValidationRepository {

    private static final Properties properties = new Properties();
    private static boolean available = false;

    static {
        InputStream inputStream = PropertiesFileValidationRepository.class.getClassLoader().getResourceAsStream("ValidationMessages.properties");
        try {
            if (inputStream != null) {
                properties.load(inputStream);
                available = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PropertiesFileValidationRepository() {

    }

    public boolean isAvailable() {
        return available;
    }

    public String getMessage(String key) {
        return StringUtils.isBlank(key) ? null : properties.getProperty(key);
    }

    public String getCode(String key) {
        return StringUtils.isBlank(key) ? null : properties.getProperty(key + ".code");
    }

    public String getException(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }

        if (StringUtils.isBlank(getMessage(key))) {
            return null;
        }

        String exception = properties.getProperty(key + ".exception");
        return StringUtils.isBlank(exception) ? properties.getProperty("default.exception") : exception;
    }

}
