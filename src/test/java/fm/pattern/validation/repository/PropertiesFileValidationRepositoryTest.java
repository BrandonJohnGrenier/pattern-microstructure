package fm.pattern.validation.repository;

import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import fm.pattern.validation.repository.PropertiesFileValidationRepository;
import fm.pattern.validation.repository.ValidationRepository;

public class PropertiesFileValidationRepositoryTest {

    private ValidationRepository repository;

    @Before
    public void before() {
        this.repository = new PropertiesFileValidationRepository();
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
        assertThat(exception).isEqualTo("fm.pattern.microstructure.UnprocessableEntityException");
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
        assertThat(repository.getException("address.city.size")).isEqualTo("fm.pattern.microstructure.UnprocessableEntityException");
    }

}
