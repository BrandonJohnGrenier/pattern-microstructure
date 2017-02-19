package fm.pattern.validation;

import static fm.pattern.validation.dsl.AddressDSL.address;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.junit.Test;

public class AddressValidationTest extends ValidationTest {

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheUnitNumberIsGreaterThan30Characters() {
        create(address().withUnit(randomAlphabetic(31)).build()).rejected().withDescription("The unit number cannot be greater than 30 characters.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheStreetNumberIsGreaterThan10Characters() {
        create(address().withNumber(randomAlphabetic(11)).build()).rejected().withDescription("The street number cannot be greater than 10 characters.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheStreetNameIsGreaterThan50Characters() {
        create(address().withStreet(randomAlphabetic(51)).build()).rejected().withDescription("The street name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheSuburbNameIsGreaterThan50Characters() {
        create(address().withSuburb(randomAlphabetic(51)).build()).rejected().withDescription("The suburb name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheCityNameIsGreaterThan50Characters() {
        create(address().withCity(randomAlphabetic(51)).build()).rejected().withDescription("The city name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheStateNameIsGreaterThan50Characters() {
        create(address().withState(randomAlphabetic(51)).build()).rejected().withDescription("The state name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheCountryNameIsGreaterThan50Characters() {
        create(address().withCountry(randomAlphabetic(51)).build()).rejected().withDescription("The country name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheCountryIsNotProvided() {
        create(address().withCountry(null).build()).rejected().withDescription("The country is required.");
        create(address().withCountry("").build()).rejected().withDescription("The country is required.");
        create(address().withCountry("   ").build()).rejected().withDescription("The country is required.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfThePostCodeIsGreaterThan10Characters() {
        create(address().withPostCode(randomAlphabetic(11)).build()).rejected().withDescription("The post code cannot be greater than 10 characters.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfThePostCodeIsNotProvided() {
        create(address().withPostCode(null).build()).rejected().withDescription("The post code is required.");
        create(address().withPostCode("").build()).rejected().withDescription("The post code is required.");
        create(address().withPostCode("   ").build()).rejected().withDescription("The post code is required.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheFormattedAddressIsGreaterThan500Characters() {
        create(address().withFormattedAddress(randomAlphabetic(501)).build()).rejected().withDescription("The formatted address cannot be greater than 500 characters.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheLatitudeIsNotProvided() {
        create(address().withLatitude(null).build()).rejected().withDescription("The latitude is required.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheLongitudeIsNotProvided() {
        create(address().withLongitude(null).build()).rejected().withDescription("The longitude is required.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheUnitNumberIsGreaterThan30Characters() {
        update(address().withUnit(randomAlphabetic(31)).build()).rejected().withDescription("The unit number cannot be greater than 30 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheStreetNumberIsGreaterThan10Characters() {
        update(address().withNumber(randomAlphabetic(11)).build()).rejected().withDescription("The street number cannot be greater than 10 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheStreetNameIsGreaterThan50Characters() {
        update(address().withStreet(randomAlphabetic(51)).build()).rejected().withDescription("The street name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheSuburbNameIsGreaterThan50Characters() {
        update(address().withSuburb(randomAlphabetic(51)).build()).rejected().withDescription("The suburb name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheCityNameIsGreaterThan50Characters() {
        update(address().withCity(randomAlphabetic(51)).build()).rejected().withDescription("The city name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheStateNameIsGreaterThan50Characters() {
        update(address().withState(randomAlphabetic(51)).build()).rejected().withDescription("The state name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheCountryNameIsGreaterThan50Characters() {
        update(address().withCountry(randomAlphabetic(51)).build()).rejected().withDescription("The country name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheCountryIsNotProvided() {
        update(address().withCountry(null).build()).rejected().withDescription("The country is required.");
        update(address().withCountry("").build()).rejected().withDescription("The country is required.");
        update(address().withCountry("   ").build()).rejected().withDescription("The country is required.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfThePostCodeIsGreaterThan10Characters() {
        update(address().withPostCode(randomAlphabetic(11)).build()).rejected().withDescription("The post code cannot be greater than 10 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfThePostCodeIsNotProvided() {
        update(address().withPostCode(null).build()).rejected().withDescription("The post code is required.");
        update(address().withPostCode("").build()).rejected().withDescription("The post code is required.");
        update(address().withPostCode("   ").build()).rejected().withDescription("The post code is required.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheFormattedAddressIsGreaterThan500Characters() {
        update(address().withFormattedAddress(randomAlphabetic(501)).build()).rejected().withDescription("The formatted address cannot be greater than 500 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheLatitudeIsNotProvided() {
        update(address().withLatitude(null).build()).rejected().withDescription("The latitude is required.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheLongitudeIsNotProvided() {
        update(address().withLongitude(null).build()).rejected().withDescription("The longitude is required.");
    }

}
