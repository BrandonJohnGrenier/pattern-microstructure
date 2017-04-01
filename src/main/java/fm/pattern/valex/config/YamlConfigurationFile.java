package fm.pattern.valex.config;

import java.io.InputStream;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

@SuppressWarnings("unchecked")
public class YamlConfigurationFile implements ValexConfigurationFile {

    private static final Logger log = LoggerFactory.getLogger(PropertyConfigurationFile.class);
    private static final String FILENAME = "ValidationMessages.yml";

    private Map<String, Map<String, String>> properties;
    private boolean available = false;

    public YamlConfigurationFile() {
        load(FILENAME);
    }

    public YamlConfigurationFile(String filename) {
        load(filename);
    }

    private void load(String filename) {
        InputStream inputStream = YamlConfigurationFile.class.getClassLoader().getResourceAsStream(filename);
        if (inputStream == null) {
            available = false;
            log.warn("Unable to find " + filename + " on the classpath.");
            return;
        }

        try {
            properties = (Map<String, Map<String, String>>) new Yaml().load(inputStream);
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
    public String getCode(String key) {
        return getValue(key, "code");
    }

    @Override
    public String getMessage(String key) {
        return getValue(key, "message");
    }

    @Override
    public String getException(String key) {
        Map<String, String> attributes = properties.get(key);
        if (attributes == null || attributes.isEmpty()) {
            return null;
        }

        String exception = getValue(attributes, "exception");
        if (!StringUtils.isBlank(exception)) {
            return exception;
        }

        Map<String, String> defaults = properties.get("default");
        return (defaults == null || defaults.isEmpty()) ? null : defaults.get("exception");
    }

    public String getValue(String elementKey, String attributeKey) {
        if (StringUtils.isBlank(elementKey)) {
            return null;
        }
    
        String value = getValue(properties.get(elementKey), attributeKey);
        return StringUtils.isBlank(value) ? elementKey : value;
    }

    public Map<String, String> getProperties(String key) {
        return properties.get("key");
    }

    private static String getValue(Map<String, String> attributes, String key) {
        return (attributes == null || attributes.isEmpty()) ? null : attributes.get(key);
    }

}
