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

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class YamlConfigurationFileTest {

    private ValexConfigurationFile file;

    @Before
    public void before() {
        this.file = new YamlConfigurationFile();
    }

    @Test
    public void theRepositoryShouldBeAvailableWhenAYamlFileIsLocatedAndLoadedSuccessfully() {
        assertThat(file.isAvailable()).isTrue();
    }

    @Test
    public void theRepositoryShouldNotBeAvailableWhenAYamlFileCannotBeFound() {
        ValexConfigurationFile unavailable = new YamlConfigurationFile("sadlfisj.yaml");
        assertThat(unavailable.isAvailable()).isFalse();
    }

    @Test(expected = ValexConfigurationException.class)
    public void theRepositoryShouldThrowAnExceptionWhenAYamlFileCannotBeParsed() {
        new YamlConfigurationFile("invalid.yml");
    }

    @Test
    public void shouldBeAbleToResolveAMessageForTheSpecifiedValidationKey() {
        String description = file.getMessage("contact.name.required");
        assertThat(description).isEqualTo("A contact name is required.");
    }

    @Test
    public void shouldReturnANullAMessageIfTheSpecifiedValidationKeyIsNullOrEmpty() {
        assertThat(file.getMessage(null)).isNull();
        assertThat(file.getMessage("")).isNull();
        assertThat(file.getMessage("   ")).isNull();
    }

    @Test
    public void shouldReturnTheKeyNameAsTheMessageIfTheSpecifiedValidationKeyCannotBeResolved() {
        assertThat(file.getMessage("invalid.key.name")).isEqualTo("invalid.key.name");
    }

    @Test
    public void shouldBeAbleToResolveACodeForTheSpecifiedValidationKey() {
        String code = file.getCode("contact.name.required");
        assertThat(code).isEqualTo("CON-1000");
    }

    @Test
    public void shouldReturnANullCodeIfTheSpecifiedValidationKeyIsNullOrEmpty() {
        assertThat(file.getCode(null)).isNull();
        assertThat(file.getCode("")).isNull();
        assertThat(file.getCode("   ")).isNull();
    }

    @Test
    public void shouldReturnTheKeyNameAsTheCodeIfTheSpecifiedValidationKeyCannotBeResolved() {
        assertThat(file.getCode("invalid.key.name")).isEqualTo("invalid.key.name");
    }

    @Test
    public void shouldBeAbleToResolveAnExceptionClassForTheSpecifiedValidationKey() {
        String exception = file.getException("contact.name.required");
        assertThat(exception).isEqualTo("fm.pattern.valex.UnprocessableEntityException");
    }

    @Test
    public void shouldReturnANullExceptionClassIfTheSpecifiedValidationKeyIsNullOrEmpty() {
        assertThat(file.getException(null)).isNull();
        assertThat(file.getException("")).isNull();
        assertThat(file.getException("   ")).isNull();
    }

    @Test
    public void shouldReturnANullExceptionClassIfTheSpecifiedValidationKeyCannotBeResolved() {
        assertThat(file.getException("invalid.key.name")).isNull();
    }

    @Test
    public void shouldReturnTheDefaultExceptionClassIfAnExceptionClassIsNotDefinedForTheValidationKey() {
        assertThat(file.getException("address.city.size")).isEqualTo("fm.pattern.valex.UnprocessableEntityException");
    }


    @Test
    public void shouldReturnNullIfAnExceptionClassIsNotDefinedForTheValidationKeyAndNoDefaultExceptionIsConfigured() {
        ValexConfiguration.use(null);
        System.setProperty("valex.config", "validation-nde.yml");
        
        ValexConfigurationFile configurationWithNoDefaultException = ValexConfiguration.getConfiguration();
        assertThat(configurationWithNoDefaultException.getException("address.city.size")).isNull();
    }
    
}
