package fm.pattern.microstructure;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class ValidationMessages {

    private static final Properties properties = new Properties();

    static {
        InputStream inputStream = ValidationMessages.class.getClassLoader().getResourceAsStream("ValidationMessages.properties");
        try {
            if (inputStream != null) {
                properties.load(inputStream);
            }
        }
        catch (Exception e) {

        }
    }

    public static String getCode(String key) {
        return StringUtils.isBlank(key) ? null : properties.getProperty(key + ".code");
    }
    
    public static String getMessage(String key) {
        return StringUtils.isBlank(key) ? null : properties.getProperty(key);
    }

}
