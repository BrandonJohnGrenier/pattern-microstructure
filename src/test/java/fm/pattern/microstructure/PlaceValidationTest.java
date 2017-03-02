package fm.pattern.microstructure;

import static fm.pattern.microstructure.dsl.PlaceDSL.place;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.junit.Test;

public class PlaceValidationTest extends ValidationTest {

    @Test
    public void shouldBeAbleToCreateALocation() {
        assertCreate(place().build()).accepted().withType(ResultType.CREATED);
    }

    @Test
    public void shouldNotBeAbleToCreateALocationWhenTheContactIsNull() {
        assertCreate(place().withoutContact().build()).rejected().withMessage("A contact is required.");
        assertCreate(place().withoutContact().build()).rejected().withCode("place.contact.required");
        assertCreate(place().withoutContact().build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToCreateALocationWhenTheAddressIsNull() {
        assertCreate(place().withAddress(null).build()).rejected().withMessage("An address is required.");
        assertCreate(place().withAddress(null).build()).rejected().withCode("place.address.required");
        assertCreate(place().withAddress(null).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToCreateALocationWhenTheInstructionsAreTooLong() {
        assertCreate(place().withInstructions(randomAlphabetic(251)).build()).rejected().withMessage("Instructions cannot be greater than 250 characters.");
        assertCreate(place().withInstructions(randomAlphabetic(251)).build()).rejected().withCode("place.instructions.size");
        assertCreate(place().withInstructions(randomAlphabetic(251)).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldBeAbleToUpdateALocation() {
        assertUpdate(place().build()).accepted().withType(ResultType.UPDATED);
    }

    @Test
    public void shouldNotBeAbleToUpdateALocationWhenTheContactIsNull() {
        assertUpdate(place().withoutContact().build()).rejected().withMessage("A contact is required.");
        assertUpdate(place().withoutContact().build()).rejected().withCode("place.contact.required");
        assertUpdate(place().withoutContact().build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToUpdateALocationWhenTheAddressIsNull() {
        assertUpdate(place().withAddress(null).build()).rejected().withMessage("An address is required.");
        assertUpdate(place().withAddress(null).build()).rejected().withCode("place.address.required");
        assertUpdate(place().withAddress(null).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToUpdateALocationWhenTheInstructionsAreTooLong() {
        assertUpdate(place().withInstructions(randomAlphabetic(251)).build()).rejected().withMessage("Instructions cannot be greater than 250 characters.");
        assertUpdate(place().withInstructions(randomAlphabetic(251)).build()).rejected().withCode("place.instructions.size");
        assertUpdate(place().withInstructions(randomAlphabetic(251)).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

}
