package fm.pattern.microstructure;

import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.Test;

public class ResultTest {

    @Test
    public void shouldBeAbleToSubstituteArgumentsInAnErrorMessage() {
        Result<String> result = Result.reject("The username %s cannot be greater than %d characters.", "smithers", 5);
        assertThat(result.getErrors().get(0).getMessage()).isEqualTo("The username smithers cannot be greater than 5 characters.");
    }

    @Test
    public void shouldBeAbleToResolveACodeAndMessageForAnErrorKey() {
        Result<String> result = Result.reject("contact.name.required");
        assertThat(result.getErrors().get(0).getCode()).isEqualTo("CON-1000");
        assertThat(result.getErrors().get(0).getMessage()).isEqualTo("A contact name is required.");
    }

    @Test
    public void shouldBeAbleToResolveACodeAndMessageAndSubstituteArgumentsForAnErrorKey() {
        Result<String> result = Result.reject("username.length", "jim", 2);
        assertThat(result.getErrors().get(0).getCode()).isEqualTo("LOC-NTFD");
        assertThat(result.getErrors().get(0).getMessage()).isEqualTo("The username jim cannot be greater than 2 characters.");
    }

}
