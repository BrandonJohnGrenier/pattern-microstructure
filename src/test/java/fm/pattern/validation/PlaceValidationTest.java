package fm.pattern.validation;

import static fm.pattern.validation.dsl.PlaceDSL.place;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.junit.Test;

public class PlaceValidationTest extends ValidationTest {

    @Test
    public void shouldBeAbleToCreateALocation() {
        create(place().build()).accepted();
    }

    @Test
    public void shouldNotBeAbleToCreateALocationWhenTheContactIsNull() {
        create(place().withoutContact().build()).rejected().withDescription("A contact is required.");
    }

    @Test
    public void shouldNotBeAbleToCreateALocationWhenTheAddressIsNull() {
        create(place().withAddress(null).build()).rejected().withDescription("An address is required.");
    }

    @Test
    public void shouldNotBeAbleToCreateALocationWhenTheInstructionsAreTooLong() {
        create(place().withInstructions(randomAlphabetic(251)).build()).rejected().withDescription("Instructions cannot be greater than 250 characters.");
    }

    @Test
    public void shouldBeAbleToUpdateALocation() {
        update(place().build()).accepted();
    }

    @Test
    public void shouldNotBeAbleToUpdateALocationWhenTheContactIsNull() {
        update(place().withoutContact().build()).rejected().withDescription("A contact is required.");
    }

    @Test
    public void shouldNotBeAbleToUpdateALocationWhenTheAddressIsNull() {
        update(place().withAddress(null).build()).rejected().withDescription("An address is required.");
    }

    @Test
    public void shouldNotBeAbleToUpdateALocationWhenTheInstructionsAreTooLong() {
        update(place().withInstructions(randomAlphabetic(251)).build()).rejected().withDescription("Instructions cannot be greater than 250 characters.");
    }

}
