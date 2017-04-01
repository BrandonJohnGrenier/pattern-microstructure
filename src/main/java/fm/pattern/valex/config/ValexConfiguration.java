/*
 * Copyright 2012-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fm.pattern.valex.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public final class ValexConfiguration {

    private static ValexConfigurationFile explicitConfiguration = null;

    private static final ValexConfigurationFile PROPERTY_FILE = new PropertyConfigurationFile();
    private static final ValexConfigurationFile YAML_FILE = new YamlConfigurationFile();

    private ValexConfiguration() {

    }

    public static void use(ValexConfigurationFile configuration) {
        explicitConfiguration = configuration;
    }

    public static String getCode(String key) {
        return getConfiguration().getCode(key);
    }

    public static String getMessage(String key) {
        return getConfiguration().getMessage(key);
    }

    public static String getException(String key) {
        return getConfiguration().getException(key);
    }

    public static Map<String, String> getProperties(String key) {
        Map<String, String> map = getConfiguration().getProperties(key);
        if (map == null) {
            return new HashMap<String, String>();
        }

        Map<String, String> properties = new HashMap<String, String>(map);
        properties.remove("exception");
        return properties;
    }

    public static ValexConfigurationFile getConfiguration() {
        if (explicitConfiguration != null) {
            return explicitConfiguration;
        }

        String filename = System.getProperty("valex.config");
        if (StringUtils.isBlank(filename)) {
            return YAML_FILE.isAvailable() ? YAML_FILE : PROPERTY_FILE;
        }

        if (filename.endsWith(".yml")) {
            return new YamlConfigurationFile(filename);
        }
        if (filename.endsWith(".properties")) {
            return new PropertyConfigurationFile(filename);
        }

        throw new ValexConfigurationException("Invalid Valex configuration file '" + filename + "' - file must be a .yml or .properties file");
    }

}
