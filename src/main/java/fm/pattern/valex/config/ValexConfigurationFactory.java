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

import org.apache.commons.lang3.StringUtils;

public final class ValexConfigurationFactory {

    private static ValexConfiguration explicitConfiguration = null;

    private static final ValexConfiguration propertiesFileConfiguration = new ValexPropertiesConfiguration();
    private static final ValexConfiguration yamlFileConfiguration = new ValexYamlConfiguration();

    private ValexConfigurationFactory() {

    }

    public static void use(ValexConfiguration configuration) {
        explicitConfiguration = configuration;
    }

    public static ValexConfiguration getRepository() {
        String filename = System.getProperty("valex.config");
        if (StringUtils.isBlank(filename)) {
            return explicitConfiguration != null ? explicitConfiguration : getDefaultRepository();
        }

        if (filename.endsWith(".yml")) {
            return new ValexYamlConfiguration(filename);
        }

        if (filename.endsWith(".properties")) {
            return new ValexPropertiesConfiguration(filename);
        }

        throw new ValexConfigurationException("Invalid Valex configuration file '" + filename + "' - file must be a .yml or .properties file");
    }

    private static ValexConfiguration getDefaultRepository() {
        return propertiesFileConfiguration.isAvailable() ? propertiesFileConfiguration : yamlFileConfiguration;
    }

}
