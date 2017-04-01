package fm.pattern.valex.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class PropertyConfigurationFileTest {

    private ValexConfigurationFile configuration;

    @Before
    public void before() {
        this.configuration = new PropertyConfigurationFile();
    }

    @Test
    public void theRepositoryShouldBeAvailableWhenAPropertiesFileIsLocatedAndLoadedSuccessfully() {
        assertThat(configuration.isAvailable()).isTrue();
    }

    @Test
    public void theRepositoryShouldBNotBeAvailableWhenAPropertiesFileCannotBeFound() {
        ValexConfigurationFile unavailable = new PropertyConfigurationFile("invalid.properties");
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
    public void shouldReturnTheCodeAsTheMessageIfTheSpecifiedValidationKeyCannotBeResolved() {
        assertThat(configuration.getMessage("invalid.key.name")).isEqualTo("invalid.key.name");
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
    public void shouldReturnTheKeyAsTheCodeIfTheSpecifiedValidationKeyCannotBeResolved() {
        assertThat(configuration.getCode("invalid.key.name")).isEqualTo("invalid.key.name");
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
    public void shouldReturnTheDefaultExceptionClassIfTheSpecifiedValidationKeyCannotBeResolved() {
        assertThat(configuration.getException("invalid.key.name")).isEqualTo("fm.pattern.valex.UnprocessableEntityException");
    }

    @Test
    public void shouldReturnTheDefaultExceptionClassIfAnExceptionClassIsNotDefinedForTheValidationKey() {
        assertThat(configuration.getException("address.city.size")).isEqualTo("fm.pattern.valex.UnprocessableEntityException");
    }

    @Test
    public void shouldReturnNullIfAnExceptionClassIsNotDefinedForTheValidationKeyAndNoDefaultExceptionIsConfigured() {
        ValexConfiguration.use(null);
        System.setProperty("valex.config", "validation-nde.properties");

        ValexConfigurationFile configurationWithNoDefaultException = ValexConfiguration.getConfiguration();
        assertThat(configurationWithNoDefaultException.getException("address.unit.size")).isNull();
    }

}
