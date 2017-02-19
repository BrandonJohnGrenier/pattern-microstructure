package fm.pattern.validation;

import static fm.pattern.validation.dsl.PlaceDSL.place;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import javax.validation.Validation;

import org.junit.Before;
import org.junit.Test;

import fm.pattern.validation.sequence.Create;
import fm.pattern.validation.sequence.Update;

public class PlaceValidationTest {

    private ValidationService validationService;

    @Before
    public void before() {
        this.validationService = new SimpleValidationService(Validation.buildDefaultValidatorFactory().getValidator());
    }

    @Test
    public void shouldBeAbleToCreateALocation() {
        onCreate(place().build()).accepted();
    }

    @Test
    public void shouldNotBeAbleToCreateALocationWhenTheContactIsNull() {
        onCreate(place().withoutContact().build()).rejected().withErrors("A contact is required.");
    }

    @Test
    public void shouldNotBeAbleToCreateALocationWhenTheAddressIsNull() {
        onCreate(place().withAddress(null).build()).rejected().withErrors("An address is required.");
    }

    @Test
    public void shouldNotBeAbleToCreateALocationWhenTheInstructionsAreTooLong() {
        onCreate(place().withInstructions(randomAlphabetic(251)).build()).rejected().withErrors("Instructions cannot be greater than 250 characters.");
    }

    @Test
    public void shouldBeAbleToUpdateALocation() {
        onUpdate(place().build()).accepted();
    }

    @Test
    public void shouldNotBeAbleToUpdateALocationWhenTheContactIsNull() {
        onUpdate(place().withoutContact().build()).rejected().withErrors("A contact is required.");
    }

    @Test
    public void shouldNotBeAbleToUpdateALocationWhenTheAddressIsNull() {
        onUpdate(place().withAddress(null).build()).rejected().withErrors("An address is required.");
    }

    @Test
    public void shouldNotBeAbleToUpdateALocationWhenTheInstructionsAreTooLong() {
        onUpdate(place().withInstructions(randomAlphabetic(251)).build()).rejected().withErrors("Instructions cannot be greater than 250 characters.");
    }

    public <T> ResultAssertions onCreate(T instance) {
        ValidationResult validationResult = validationService.validate(instance, Create.class);
        Result<T> result = validationResult.containsErrors() ? validationResult.reject(instance) : Result.accept(instance);
        return PlatformAssertions.assertThat(result);
    }

    public <T> ResultAssertions onUpdate(T instance) {
        ValidationResult validationResult = validationService.validate(instance, Update.class);
        Result<T> result = validationResult.containsErrors() ? validationResult.reject(instance) : Result.accept(instance);
        return PlatformAssertions.assertThat(result);
    }

}
