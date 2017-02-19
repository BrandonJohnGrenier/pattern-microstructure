package fm.pattern.validation;

import static fm.pattern.validation.dsl.AddressDSL.address;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.junit.Test;

public class AddressValidationTest extends ValidationTest {

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheUnitNumberIsGreaterThan30Characters() {
        assertCreate(address().withUnit(randomAlphabetic(31)).build()).rejected().withDescription("The unit number cannot be greater than 30 characters.");
        assertCreate(address().withUnit(randomAlphabetic(31)).build()).rejected().withCode("address.unit.size");
        assertCreate(address().withUnit(randomAlphabetic(31)).build()).rejected().withType(ErrorType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheStreetNumberIsGreaterThan10Characters() {
        assertCreate(address().withNumber(randomAlphabetic(11)).build()).rejected().withDescription("The street number cannot be greater than 10 characters.");
        assertCreate(address().withNumber(randomAlphabetic(11)).build()).rejected().withCode("address.number.size");
        assertCreate(address().withNumber(randomAlphabetic(11)).build()).rejected().withType(ErrorType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheStreetNameIsGreaterThan50Characters() {
        assertCreate(address().withStreet(randomAlphabetic(51)).build()).rejected().withDescription("The street name cannot be greater than 50 characters.");
        assertCreate(address().withStreet(randomAlphabetic(51)).build()).rejected().withCode("address.street.size");
        assertCreate(address().withStreet(randomAlphabetic(51)).build()).rejected().withType(ErrorType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheSuburbNameIsGreaterThan50Characters() {
        assertCreate(address().withSuburb(randomAlphabetic(51)).build()).rejected().withDescription("The suburb name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheCityNameIsGreaterThan50Characters() {
        assertCreate(address().withCity(randomAlphabetic(51)).build()).rejected().withDescription("The city name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheStateNameIsGreaterThan50Characters() {
        assertCreate(address().withState(randomAlphabetic(51)).build()).rejected().withDescription("The state name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheCountryNameIsGreaterThan50Characters() {
        assertCreate(address().withCountry(randomAlphabetic(51)).build()).rejected().withDescription("The country name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheCountryIsNotProvided() {
        assertCreate(address().withCountry(null).build()).rejected().withDescription("The country is required.");
        assertCreate(address().withCountry("").build()).rejected().withDescription("The country is required.");
        assertCreate(address().withCountry("   ").build()).rejected().withDescription("The country is required.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfThePostCodeIsGreaterThan10Characters() {
        assertCreate(address().withPostCode(randomAlphabetic(11)).build()).rejected().withDescription("The post code cannot be greater than 10 characters.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfThePostCodeIsNotProvided() {
        assertCreate(address().withPostCode(null).build()).rejected().withDescription("The post code is required.");
        assertCreate(address().withPostCode("").build()).rejected().withDescription("The post code is required.");
        assertCreate(address().withPostCode("   ").build()).rejected().withDescription("The post code is required.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheFormattedAddressIsGreaterThan500Characters() {
        assertCreate(address().withFormattedAddress(randomAlphabetic(501)).build()).rejected().withDescription("The formatted address cannot be greater than 500 characters.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheLatitudeIsNotProvided() {
        assertCreate(address().withLatitude(null).build()).rejected().withDescription("The latitude is required.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheLongitudeIsNotProvided() {
        assertCreate(address().withLongitude(null).build()).rejected().withDescription("The longitude is required.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheUnitNumberIsGreaterThan30Characters() {
        assertUpdate(address().withUnit(randomAlphabetic(31)).build()).rejected().withDescription("The unit number cannot be greater than 30 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheStreetNumberIsGreaterThan10Characters() {
        assertUpdate(address().withNumber(randomAlphabetic(11)).build()).rejected().withDescription("The street number cannot be greater than 10 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheStreetNameIsGreaterThan50Characters() {
        assertUpdate(address().withStreet(randomAlphabetic(51)).build()).rejected().withDescription("The street name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheSuburbNameIsGreaterThan50Characters() {
        assertUpdate(address().withSuburb(randomAlphabetic(51)).build()).rejected().withDescription("The suburb name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheCityNameIsGreaterThan50Characters() {
        assertUpdate(address().withCity(randomAlphabetic(51)).build()).rejected().withDescription("The city name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheStateNameIsGreaterThan50Characters() {
        assertUpdate(address().withState(randomAlphabetic(51)).build()).rejected().withDescription("The state name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheCountryNameIsGreaterThan50Characters() {
        assertUpdate(address().withCountry(randomAlphabetic(51)).build()).rejected().withDescription("The country name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheCountryIsNotProvided() {
        assertUpdate(address().withCountry(null).build()).rejected().withDescription("The country is required.");
        assertUpdate(address().withCountry("").build()).rejected().withDescription("The country is required.");
        assertUpdate(address().withCountry("   ").build()).rejected().withDescription("The country is required.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfThePostCodeIsGreaterThan10Characters() {
        assertUpdate(address().withPostCode(randomAlphabetic(11)).build()).rejected().withDescription("The post code cannot be greater than 10 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfThePostCodeIsNotProvided() {
        assertUpdate(address().withPostCode(null).build()).rejected().withDescription("The post code is required.");
        assertUpdate(address().withPostCode("").build()).rejected().withDescription("The post code is required.");
        assertUpdate(address().withPostCode("   ").build()).rejected().withDescription("The post code is required.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheFormattedAddressIsGreaterThan500Characters() {
        assertUpdate(address().withFormattedAddress(randomAlphabetic(501)).build()).rejected().withDescription("The formatted address cannot be greater than 500 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheLatitudeIsNotProvided() {
        assertUpdate(address().withLatitude(null).build()).rejected().withDescription("The latitude is required.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheLongitudeIsNotProvided() {
        assertUpdate(address().withLongitude(null).build()).rejected().withDescription("The longitude is required.");
    }

}
