package fm.pattern.microstructure;

import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.Test;

public class ValidationMessagesTest {

    @Test
    public void shouldBeAbleToResolveMessageDescriptionsFromTheValidationMessagePropertiesFile() {
        String description = ValidationMessages.getMessage("contact.name.required");
        assertThat(description).isEqualTo("A contact name is required.");
    }

    @Test
    public void shouldReturnANullDescriptionIfTheKeyIsNullOrEmpty() {
        assertThat(ValidationMessages.getMessage("")).isNull();
        assertThat(ValidationMessages.getMessage(null)).isNull();
    }

    @Test
    public void shouldReturnANullDescriptionIfTheKeyCannotBeResolved() {
        assertThat(ValidationMessages.getMessage("invalid.key.name")).isNull();
    }

}
