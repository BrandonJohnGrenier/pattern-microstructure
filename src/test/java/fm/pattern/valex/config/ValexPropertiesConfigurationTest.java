package fm.pattern.valex.config;

import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import fm.pattern.valex.config.ValexConfiguration;
import fm.pattern.valex.config.ValexPropertiesConfiguration;

public class ValexPropertiesConfigurationTest {

    private ValexConfiguration configuration;

    @Before
    public void before() {
        this.configuration = new ValexPropertiesConfiguration();
    }

    @Test
    public void theRepositoryShouldBeAvailableWhenAPropertiesFileIsLocatedAndLoadedSuccessfully() {
        assertThat(configuration.isAvailable()).isTrue();
    }

    @Test
    public void theRepositoryShouldBNotBeAvailableWhenAPropertiesFileCannotBeFound() {
        ValexConfiguration unavailable = new ValexPropertiesConfiguration("invalid.properties");
        assertThat(unavailable.isAvailable()).isFalse();
    }

    @Test
    public void shouldBeAbleToResolveAMessageForTheSpecifiedValidationKey() {
        String description = configuration.getMessage("contact.name.required");
        assertThat(description).isEqualTo("A contact name is required.");
    }

    @Test
    public void shouldReturnANullAMessageIfTheSpecifiedValidationKeyIsNullOrEmpty() {
        assertThat(configuration.getMessage(null)).isNull();
        assertThat(configuration.getMessage("")).isNull();
        assertThat(configuration.getMessage("   ")).isNull();
    }

    @Test
    public void shouldReturnANullAMessageIfTheSpecifiedValidationKeyCannotBeResolved() {
        assertThat(configuration.getMessage("invalid.key.name")).isNull();
    }

    @Test
    public void shouldBeAbleToResolveACodeForTheSpecifiedValidationKey() {
        String code = configuration.getCode("contact.name.required");
        assertThat(code).isEqualTo("CON-1000");
    }

    @Test
    public void shouldReturnANullCodeIfTheSpecifiedValidationKeyIsNullOrEmpty() {
        assertThat(configuration.getCode(null)).isNull();
        assertThat(configuration.getCode("")).isNull();
        assertThat(configuration.getCode("   ")).isNull();
    }

    @Test
    public void shouldReturnANullACodeIfTheSpecifiedValidationKeyCannotBeResolved() {
        assertThat(configuration.getCode("invalid.key.name")).isNull();
    }

    @Test
    public void shouldBeAbleToResolveAnExceptionClassForTheSpecifiedValidationKey() {
        String exception = configuration.getException("contact.name.required");
        assertThat(exception).isEqualTo("fm.pattern.valex.UnprocessableEntityException");
    }

    @Test
    public void shouldReturnANullExceptionClassIfTheSpecifiedValidationKeyIsNullOrEmpty() {
        assertThat(configuration.getException(null)).isNull();
        assertThat(configuration.getException("")).isNull();
        assertThat(configuration.getException("   ")).isNull();
    }

    @Test
    public void shouldReturnANullExceptionClassIfTheSpecifiedValidationKeyCannotBeResolved() {
        assertThat(configuration.getException("invalid.key.name")).isNull();
    }

    @Test
    public void shouldReturnTheDefaultExceptionClassIfAnExceptionClassIsNotDefinedForTheValidationKey() {
        assertThat(configuration.getException("address.city.size")).isEqualTo("fm.pattern.valex.UnprocessableEntityException");
    }
    
}
