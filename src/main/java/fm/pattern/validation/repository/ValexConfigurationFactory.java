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

package fm.pattern.validation.repository;

public final class ValexConfigurationFactory {

    private static ValexConfiguration validationRepository = null;

    private static final ValexConfiguration propertiesFileValidationRepository = new ValexPropertiesConfiguration();
    private static final ValexConfiguration yamlFileValidationRepository = new ValexYamlConfiguration();

    private ValexConfigurationFactory() {

    }

    public static void use(ValexConfiguration repository) {
        validationRepository = repository;
    }

    public static ValexConfiguration getRepository() {
        return validationRepository != null ? validationRepository : getDefaultRepository();
    }

    private static ValexConfiguration getDefaultRepository() {
        return propertiesFileValidationRepository.isAvailable() ? propertiesFileValidationRepository : yamlFileValidationRepository;
    }

}
