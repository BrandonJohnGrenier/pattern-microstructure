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

import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class YamlFileValidationRepositoryTest {

    private ValexConfiguration repository;

    @Before
    public void before() {
        this.repository = new ValexYamlConfiguration();
    }

    @Test
    public void theRepositoryShouldBeAvailableWhenAYamlFileIsLocatedAndLoadedSuccessfully() {
        assertThat(repository.isAvailable()).isTrue();
    }

    @Test
    public void theRepositoryShouldNotBeAvailableWhenAYamlFileCannotBeFound() {
        ValexConfiguration unavailable = new ValexYamlConfiguration("sadlfisj.yaml");
        assertThat(unavailable.isAvailable()).isFalse();
    }

    @Test(expected = ValexConfigurationException.class)
    public void theRepositoryShouldThrowAnExceptionWhenAYamlFileCannotBeParsed() {
        new ValexYamlConfiguration("invalid.yml");
    }

    @Test
    public void shouldBeAbleToResolveAMessageForTheSpecifiedValidationKey() {
        String description = repository.getMessage("contact.name.required");
        assertThat(description).isEqualTo("A contact name is required.");
    }

    @Test
    public void shouldReturnANullAMessageIfTheSpecifiedValidationKeyIsNullOrEmpty() {
        assertThat(repository.getMessage(null)).isNull();
        assertThat(repository.getMessage("")).isNull();
        assertThat(repository.getMessage("   ")).isNull();
    }

    @Test
    public void shouldReturnANullAMessageIfTheSpecifiedValidationKeyCannotBeResolved() {
        assertThat(repository.getMessage("invalid.key.name")).isNull();
    }

    @Test
    public void shouldBeAbleToResolveACodeForTheSpecifiedValidationKey() {
        String code = repository.getCode("contact.name.required");
        assertThat(code).isEqualTo("CON-1000");
    }

    @Test
    public void shouldReturnANullCodeIfTheSpecifiedValidationKeyIsNullOrEmpty() {
        assertThat(repository.getCode(null)).isNull();
        assertThat(repository.getCode("")).isNull();
        assertThat(repository.getCode("   ")).isNull();
    }

    @Test
    public void shouldReturnANullACodeIfTheSpecifiedValidationKeyCannotBeResolved() {
        assertThat(repository.getCode("invalid.key.name")).isNull();
    }

    @Test
    public void shouldBeAbleToResolveAnExceptionClassForTheSpecifiedValidationKey() {
        String exception = repository.getException("contact.name.required");
        assertThat(exception).isEqualTo("fm.pattern.validation.UnprocessableEntityException");
    }

    @Test
    public void shouldReturnANullExceptionClassIfTheSpecifiedValidationKeyIsNullOrEmpty() {
        assertThat(repository.getException(null)).isNull();
        assertThat(repository.getException("")).isNull();
        assertThat(repository.getException("   ")).isNull();
    }

    @Test
    public void shouldReturnANullExceptionClassIfTheSpecifiedValidationKeyCannotBeResolved() {
        assertThat(repository.getException("invalid.key.name")).isNull();
    }

    @Test
    public void shouldReturnTheDefaultExceptionClassIfAnExceptionClassIsNotDefinedForTheValidationKey() {
        assertThat(repository.getException("address.city.size")).isEqualTo("fm.pattern.validation.UnprocessableEntityException");
    }

}
