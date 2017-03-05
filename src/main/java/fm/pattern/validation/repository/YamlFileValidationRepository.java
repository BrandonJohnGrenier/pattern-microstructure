package fm.pattern.validation.repository;

import java.io.InputStream;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yaml.snakeyaml.Yaml;

@SuppressWarnings("unchecked")
public class YamlFileValidationRepository implements ValidationRepository {

    private static final Log log = LogFactory.getLog(YamlFileValidationRepository.class);

    private static Map<String, Map<String, String>> properties;
    private static boolean available = false;

    static {
        InputStream inputStream = YamlFileValidationRepository.class.getClassLoader().getResourceAsStream("ValidationMessages.yml");

        try {
            if (inputStream != null) {
                properties = (Map<String, Map<String, String>>) new Yaml().load(inputStream);
                available = true;
            }
        }
        catch (Exception e) {
            log.error("Unable to load validation yaml file:", e);
        }
    }

    public boolean isAvailable() {
        return available;
    }

    @Override
    public String getCode(String key) {
        return StringUtils.isBlank(key) ? null : getValue(properties.get(key), "code");
    }

    @Override
    public String getMessage(String key) {
        return StringUtils.isBlank(key) ? null : getValue(properties.get(key), "message");
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

    private static String getValue(Map<String, String> attributes, String key) {
        return (StringUtils.isBlank(key) || attributes == null || attributes.isEmpty()) ? null : attributes.get(key);
    }

}