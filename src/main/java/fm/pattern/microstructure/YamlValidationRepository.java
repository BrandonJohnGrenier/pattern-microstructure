package fm.pattern.microstructure;

import java.io.InputStream;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.yaml.snakeyaml.Yaml;

@SuppressWarnings("unchecked")
public class YamlValidationRepository implements ValidationRepository {

    private static Map<String, Map<String, String>> properties;
    private static boolean available = false;

    static {
        InputStream inputStream = YamlValidationRepository.class.getClassLoader().getResourceAsStream("validation.yml");

        try {
            if (inputStream != null) {
                properties = (Map<String, Map<String, String>>) new Yaml().load(inputStream);
                available = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public YamlValidationRepository() {

    }

    public boolean isAvailable() {
        return available;
    }

    public String getCode(String key) {
        return StringUtils.isBlank(key) ? null : getValue(properties.get(key), "code");
    }

    public String getMessage(String key) {
        return StringUtils.isBlank(key) ? null : getValue(properties.get(key), "message");
    }

    public String getException(String key) {
        return StringUtils.isBlank(key) ? null : getValue(properties.get(key), "exception");
    }

    private static String getValue(Map<String, String> attributes, String key) {
        return (StringUtils.isBlank(key) || attributes == null || attributes.isEmpty()) ? null : attributes.get(key);
    }

}
