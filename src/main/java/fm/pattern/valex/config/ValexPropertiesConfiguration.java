package fm.pattern.valex.config;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValexPropertiesConfiguration implements ValexConfiguration {

    private static final Logger log = LoggerFactory.getLogger(ValexPropertiesConfiguration.class);
    private static final String default_filename = "ValidationMessages.properties";

    private static final Properties properties = new Properties();
    private static boolean available = false;

    public ValexPropertiesConfiguration() {
        load(default_filename);
    }

    public ValexPropertiesConfiguration(String filename) {
        load(filename);
    }

    private void load(String filename) {
        InputStream inputStream = ValexPropertiesConfiguration.class.getClassLoader().getResourceAsStream(filename);
        if (inputStream == null) {
            available = false;
            log.warn("Unable to find Valex configuration file '" + filename + "' on the classpath.");
            return;
        }

        try {
            properties.load(inputStream);
            available = true;
        }
        catch (Exception e) {
            available = false;
            throw new ValexConfigurationException("Failed to parse " + filename + ":", e);
        }
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public String getMessage(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }

        String message = properties.getProperty(key);
        return StringUtils.isBlank(message) ? key : message;
    }

    @Override
    public String getCode(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }

        String code = properties.getProperty(key + ".code");
        return StringUtils.isBlank(code) ? key : code;
    }

    @Override
    public String getException(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }

        String exception = properties.getProperty(key + ".exception");
        return StringUtils.isBlank(exception) ? properties.getProperty("default.exception") : exception;
    }

}
