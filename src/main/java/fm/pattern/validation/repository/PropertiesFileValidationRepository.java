package fm.pattern.validation.repository;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesFileValidationRepository implements ValidationRepository {

    private static final Logger log = LoggerFactory.getLogger(PropertiesFileValidationRepository.class);
    private static final String FILENAME = "ValidationMessages.properties";

    private static final Properties properties = new Properties();
    private static boolean available = false;

    public PropertiesFileValidationRepository() {
        load(FILENAME);
    }

    public PropertiesFileValidationRepository(String filename) {
        load(filename);
    }

    private void load(String filename) {
        InputStream inputStream = PropertiesFileValidationRepository.class.getClassLoader().getResourceAsStream(filename);
        if (inputStream == null) {
            available = false;
            log.warn("Unable to find " + filename + " on the classpath.");
            return;
        }

        try {
            properties.load(inputStream);
            available = true;
        }
        catch (Exception e) {
            available = false;
            throw new PatternConfigurationException("Failed to parse " + filename + ":", e);
        }
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public String getMessage(String key) {
        return StringUtils.isBlank(key) ? null : properties.getProperty(key);
    }

    @Override
    public String getCode(String key) {
        return StringUtils.isBlank(key) ? null : properties.getProperty(key + ".code");
    }

    @Override
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
