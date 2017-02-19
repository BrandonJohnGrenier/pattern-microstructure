package fm.pattern.validation;

import static fm.pattern.validation.dsl.PlaceDSL.place;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.junit.Test;

public class PlaceValidationTest extends ValidationTest {

    @Test
    public void shouldBeAbleToCreateALocation() {
        assertCreate(place().build()).accepted();
    }

    @Test
    public void shouldNotBeAbleToCreateALocationWhenTheContactIsNull() {
        assertCreate(place().withoutContact().build()).rejected().withDescription("A contact is required.");
    }

    @Test
    public void shouldNotBeAbleToCreateALocationWhenTheAddressIsNull() {
        assertCreate(place().withAddress(null).build()).rejected().withDescription("An address is required.");
    }

    @Test
    public void shouldNotBeAbleToCreateALocationWhenTheInstructionsAreTooLong() {
        assertCreate(place().withInstructions(randomAlphabetic(251)).build()).rejected().withDescription("Instructions cannot be greater than 250 characters.");
    }

    @Test
    public void shouldBeAbleToUpdateALocation() {
        assertUpdate(place().build()).accepted();
    }

    @Test
    public void shouldNotBeAbleToUpdateALocationWhenTheContactIsNull() {
        assertUpdate(place().withoutContact().build()).rejected().withDescription("A contact is required.");
    }

    @Test
    public void shouldNotBeAbleToUpdateALocationWhenTheAddressIsNull() {
        assertUpdate(place().withAddress(null).build()).rejected().withDescription("An address is required.");
    }

    @Test
    public void shouldNotBeAbleToUpdateALocationWhenTheInstructionsAreTooLong() {
        assertUpdate(place().withInstructions(randomAlphabetic(251)).build()).rejected().withDescription("Instructions cannot be greater than 250 characters.");
    }

}
