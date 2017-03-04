package fm.pattern.microstructure;

import static fm.pattern.microstructure.dsl.AddressDSL.address;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.junit.Test;

public class AddressValidationTest extends ValidationServiceTest {

    @Test
    public void shouldBeAbleToCreateAnAddress() {
        assertCreate(address().withUnit(randomAlphabetic(5)).build()).accepted();
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheUnitNumberIsGreaterThan30Characters() {
        assertCreate(address().withUnit(randomAlphabetic(31)).build()).rejected().withMessage("The unit number cannot be greater than 30 characters.");
        assertCreate(address().withUnit(randomAlphabetic(31)).build()).rejected().withCode("ADD-1000");
        assertCreate(address().withUnit(randomAlphabetic(31)).build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheStreetNumberIsGreaterThan10Characters() {
        assertCreate(address().withNumber(randomAlphabetic(11)).build()).rejected().withMessage("The street number cannot be greater than 10 characters.");
        assertCreate(address().withNumber(randomAlphabetic(11)).build()).rejected().withCode("ADD-1001");
        assertCreate(address().withNumber(randomAlphabetic(11)).build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheStreetNameIsGreaterThan50Characters() {
        assertCreate(address().withStreet(randomAlphabetic(51)).build()).rejected().withMessage("The street name cannot be greater than 50 characters.");
        assertCreate(address().withStreet(randomAlphabetic(51)).build()).rejected().withCode("ADD-1002");
        assertCreate(address().withStreet(randomAlphabetic(51)).build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheSuburbNameIsGreaterThan50Characters() {
        assertCreate(address().withSuburb(randomAlphabetic(51)).build()).rejected().withMessage("The suburb name cannot be greater than 50 characters.");
        assertCreate(address().withSuburb(randomAlphabetic(51)).build()).rejected().withCode("ADD-1003");
        assertCreate(address().withSuburb(randomAlphabetic(51)).build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheCityNameIsGreaterThan50Characters() {
        assertCreate(address().withCity(randomAlphabetic(51)).build()).rejected().withMessage("The city name cannot be greater than 50 characters.");
        assertCreate(address().withCity(randomAlphabetic(51)).build()).rejected().withCode("ADD-1004");
        assertCreate(address().withCity(randomAlphabetic(51)).build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheStateNameIsGreaterThan50Characters() {
        assertCreate(address().withState(randomAlphabetic(51)).build()).rejected().withMessage("The state name cannot be greater than 50 characters.");
        assertCreate(address().withState(randomAlphabetic(51)).build()).rejected().withCode("ADD-1005");
        assertCreate(address().withState(randomAlphabetic(51)).build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheCountryNameIsGreaterThan50Characters() {
        assertCreate(address().withCountry(randomAlphabetic(51)).build()).rejected().withMessage("The country name cannot be greater than 50 characters.");
        assertCreate(address().withCountry(randomAlphabetic(51)).build()).rejected().withCode("ADD-1006");
        assertCreate(address().withCountry(randomAlphabetic(51)).build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheCountryIsNotProvided() {
        assertCreate(address().withCountry(null).build()).rejected().withMessage("The country is required.");
        assertCreate(address().withCountry(null).build()).rejected().withCode("ADD-1007");
        assertCreate(address().withCountry(null).build()).rejected().withException(UnprocessableEntityException.class);

        assertCreate(address().withCountry("").build()).rejected().withMessage("The country is required.");
        assertCreate(address().withCountry("").build()).rejected().withCode("ADD-1007");
        assertCreate(address().withCountry("").build()).rejected().withException(UnprocessableEntityException.class);

        assertCreate(address().withCountry("   ").build()).rejected().withMessage("The country is required.");
        assertCreate(address().withCountry("   ").build()).rejected().withCode("ADD-1007");
        assertCreate(address().withCountry("   ").build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfThePostCodeIsGreaterThan10Characters() {
        assertCreate(address().withPostCode(randomAlphabetic(11)).build()).rejected().withMessage("The post code cannot be greater than 10 characters.");
        assertCreate(address().withPostCode(randomAlphabetic(11)).build()).rejected().withCode("ADD-1009");
        assertCreate(address().withPostCode(randomAlphabetic(11)).build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfThePostCodeIsNotProvided() {
        assertCreate(address().withPostCode(null).build()).rejected().withMessage("The post code is required.");
        assertCreate(address().withPostCode(null).build()).rejected().withCode("ADD-1008");
        assertCreate(address().withPostCode(null).build()).rejected().withException(UnprocessableEntityException.class);

        assertCreate(address().withPostCode("").build()).rejected().withMessage("The post code is required.");
        assertCreate(address().withPostCode("").build()).rejected().withCode("ADD-1008");
        assertCreate(address().withPostCode("").build()).rejected().withException(UnprocessableEntityException.class);

        assertCreate(address().withPostCode("   ").build()).rejected().withMessage("The post code is required.");
        assertCreate(address().withPostCode("   ").build()).rejected().withCode("ADD-1008");
        assertCreate(address().withPostCode("   ").build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheFormattedAddressIsGreaterThan500Characters() {
        assertCreate(address().withFormattedAddress(randomAlphabetic(501)).build()).rejected().withMessage("The formatted address cannot be greater than 500 characters.");
        assertCreate(address().withFormattedAddress(randomAlphabetic(501)).build()).rejected().withCode("ADD-1010");
        assertCreate(address().withFormattedAddress(randomAlphabetic(501)).build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheLatitudeIsNotProvided() {
        assertCreate(address().withLatitude(null).build()).rejected().withMessage("The latitude is required.");
        assertCreate(address().withLatitude(null).build()).rejected().withCode("LOC-1001");
        assertCreate(address().withLatitude(null).build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheLongitudeIsNotProvided() {
        assertCreate(address().withLongitude(null).build()).rejected().withMessage("The longitude is required.");
        assertCreate(address().withLongitude(null).build()).rejected().withCode("LOC-1002");
        assertCreate(address().withLongitude(null).build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldBeAbleToUpdateAnAddress() {
        assertUpdate(address().withUnit(randomAlphabetic(5)).build()).accepted();
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheUnitNumberIsGreaterThan30Characters() {
        assertUpdate(address().withUnit(randomAlphabetic(31)).build()).rejected().withMessage("The unit number cannot be greater than 30 characters.");
        assertUpdate(address().withUnit(randomAlphabetic(31)).build()).rejected().withCode("ADD-1000");
        assertUpdate(address().withUnit(randomAlphabetic(31)).build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheStreetNumberIsGreaterThan10Characters() {
        assertUpdate(address().withNumber(randomAlphabetic(11)).build()).rejected().withMessage("The street number cannot be greater than 10 characters.");
        assertUpdate(address().withNumber(randomAlphabetic(11)).build()).rejected().withCode("ADD-1001");
        assertUpdate(address().withNumber(randomAlphabetic(11)).build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheStreetNameIsGreaterThan50Characters() {
        assertUpdate(address().withStreet(randomAlphabetic(51)).build()).rejected().withMessage("The street name cannot be greater than 50 characters.");
        assertUpdate(address().withStreet(randomAlphabetic(51)).build()).rejected().withCode("ADD-1002");
        assertUpdate(address().withStreet(randomAlphabetic(51)).build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheSuburbNameIsGreaterThan50Characters() {
        assertUpdate(address().withSuburb(randomAlphabetic(51)).build()).rejected().withMessage("The suburb name cannot be greater than 50 characters.");
        assertUpdate(address().withSuburb(randomAlphabetic(51)).build()).rejected().withCode("ADD-1003");
        assertUpdate(address().withSuburb(randomAlphabetic(51)).build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheCityNameIsGreaterThan50Characters() {
        assertUpdate(address().withCity(randomAlphabetic(51)).build()).rejected().withMessage("The city name cannot be greater than 50 characters.");
        assertUpdate(address().withCity(randomAlphabetic(51)).build()).rejected().withCode("ADD-1004");
        assertUpdate(address().withCity(randomAlphabetic(51)).build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheStateNameIsGreaterThan50Characters() {
        assertUpdate(address().withState(randomAlphabetic(51)).build()).rejected().withMessage("The state name cannot be greater than 50 characters.");
        assertUpdate(address().withState(randomAlphabetic(51)).build()).rejected().withCode("ADD-1005");
        assertUpdate(address().withState(randomAlphabetic(51)).build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheCountryNameIsGreaterThan50Characters() {
        assertUpdate(address().withCountry(randomAlphabetic(51)).build()).rejected().withMessage("The country name cannot be greater than 50 characters.");
        assertUpdate(address().withCountry(randomAlphabetic(51)).build()).rejected().withCode("ADD-1006");
        assertUpdate(address().withCountry(randomAlphabetic(51)).build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheCountryIsNotProvided() {
        assertUpdate(address().withCountry(null).build()).rejected().withMessage("The country is required.");
        assertUpdate(address().withCountry(null).build()).rejected().withCode("ADD-1007");
        assertUpdate(address().withCountry(null).build()).rejected().withException(UnprocessableEntityException.class);

        assertUpdate(address().withCountry("").build()).rejected().withMessage("The country is required.");
        assertUpdate(address().withCountry("").build()).rejected().withCode("ADD-1007");
        assertUpdate(address().withCountry("").build()).rejected().withException(UnprocessableEntityException.class);

        assertUpdate(address().withCountry("   ").build()).rejected().withMessage("The country is required.");
        assertUpdate(address().withCountry("   ").build()).rejected().withCode("ADD-1007");
        assertUpdate(address().withCountry("   ").build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfThePostCodeIsGreaterThan10Characters() {
        assertUpdate(address().withPostCode(randomAlphabetic(11)).build()).rejected().withMessage("The post code cannot be greater than 10 characters.");
        assertUpdate(address().withPostCode(randomAlphabetic(11)).build()).rejected().withCode("ADD-1009");
        assertUpdate(address().withPostCode(randomAlphabetic(11)).build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfThePostCodeIsNotProvided() {
        assertUpdate(address().withPostCode(null).build()).rejected().withMessage("The post code is required.");
        assertUpdate(address().withPostCode(null).build()).rejected().withCode("ADD-1008");
        assertUpdate(address().withPostCode(null).build()).rejected().withException(UnprocessableEntityException.class);

        assertUpdate(address().withPostCode("").build()).rejected().withMessage("The post code is required.");
        assertUpdate(address().withPostCode("").build()).rejected().withCode("ADD-1008");
        assertUpdate(address().withPostCode("").build()).rejected().withException(UnprocessableEntityException.class);

        assertUpdate(address().withPostCode("   ").build()).rejected().withMessage("The post code is required.");
        assertUpdate(address().withPostCode("   ").build()).rejected().withCode("ADD-1008");
        assertUpdate(address().withPostCode("   ").build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheFormattedAddressIsGreaterThan500Characters() {
        assertUpdate(address().withFormattedAddress(randomAlphabetic(501)).build()).rejected().withMessage("The formatted address cannot be greater than 500 characters.");
        assertUpdate(address().withFormattedAddress(randomAlphabetic(501)).build()).rejected().withCode("ADD-1010");
        assertUpdate(address().withFormattedAddress(randomAlphabetic(501)).build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheLatitudeIsNotProvided() {
        assertUpdate(address().withLatitude(null).build()).rejected().withMessage("The latitude is required.");
        assertUpdate(address().withLatitude(null).build()).rejected().withCode("LOC-1001");
        assertUpdate(address().withLatitude(null).build()).rejected().withException(UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheLongitudeIsNotProvided() {
        assertUpdate(address().withLongitude(null).build()).rejected().withMessage("The longitude is required.");
        assertUpdate(address().withLongitude(null).build()).rejected().withCode("LOC-1002");
        assertUpdate(address().withLongitude(null).build()).rejected().withException(UnprocessableEntityException.class);
    }

}
