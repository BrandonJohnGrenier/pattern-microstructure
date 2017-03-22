package fm.pattern.valex;

import static fm.pattern.valex.dsl.PlaceDSL.place;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.junit.Test;

public class PlaceValidationTest extends ValidationServiceTest {

    @Test
    public void shouldBeAbleToCreateALocation() {
        assertCreate(place().build()).accepted();
    }

    @Test
    public void shouldNotBeAbleToCreateALocationWhenTheContactIsNull() {
        assertCreate(place().withoutContact().build()).rejected().withMessage("A contact is required.");
        assertCreate(place().withoutContact().build()).rejected().withCode("PLC-1001");
        assertCreate(place().withoutContact().build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToCreateALocationWhenTheAddressIsNull() {
        assertCreate(place().withAddress(null).build()).rejected().withMessage("An address is required.");
        assertCreate(place().withAddress(null).build()).rejected().withCode("PLC-1002");
        assertCreate(place().withAddress(null).build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToCreateALocationWhenTheInstructionsAreTooLong() {
        assertCreate(place().withInstructions(randomAlphabetic(251)).build()).rejected().withMessage("Instructions cannot be greater than 250 characters.");
        assertCreate(place().withInstructions(randomAlphabetic(251)).build()).rejected().withCode("PLC-1003");
        assertCreate(place().withInstructions(randomAlphabetic(251)).build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldBeAbleToUpdateALocation() {
        assertUpdate(place().build()).accepted();
    }

    @Test
    public void shouldNotBeAbleToUpdateALocationWhenTheContactIsNull() {
        assertUpdate(place().withoutContact().build()).rejected().withMessage("A contact is required.");
        assertUpdate(place().withoutContact().build()).rejected().withCode("PLC-1001");
        assertUpdate(place().withoutContact().build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToUpdateALocationWhenTheAddressIsNull() {
        assertUpdate(place().withAddress(null).build()).rejected().withMessage("An address is required.");
        assertUpdate(place().withAddress(null).build()).rejected().withCode("PLC-1002");
        assertUpdate(place().withAddress(null).build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToUpdateALocationWhenTheInstructionsAreTooLong() {
        assertUpdate(place().withInstructions(randomAlphabetic(251)).build()).rejected().withMessage("Instructions cannot be greater than 250 characters.");
        assertUpdate(place().withInstructions(randomAlphabetic(251)).build()).rejected().withCode("PLC-1003");
        assertUpdate(place().withInstructions(randomAlphabetic(251)).build()).rejected().withException(UnprocessableEntityException.class);
    }

}
