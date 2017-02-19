package fm.pattern.validation;

import static fm.pattern.validation.dsl.AddressDSL.address;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import javax.validation.Validation;

import org.junit.Before;
import org.junit.Test;

import fm.pattern.validation.sequence.Create;
import fm.pattern.validation.sequence.Update;

public class AddressValidationTest {

    private ValidationService validationService;

    @Before
    public void before() {
        this.validationService = new SimpleValidationService(Validation.buildDefaultValidatorFactory().getValidator());
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheUnitNumberIsGreaterThan30Characters() {
        onCreate(address().withUnit(randomAlphabetic(31)).build()).rejected().withErrors("The unit number cannot be greater than 30 characters.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheStreetNumberIsGreaterThan10Characters() {
        onCreate(address().withNumber(randomAlphabetic(11)).build()).rejected().withErrors("The street number cannot be greater than 10 characters.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheStreetNameIsGreaterThan50Characters() {
        onCreate(address().withStreet(randomAlphabetic(51)).build()).rejected().withErrors("The street name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheSuburbNameIsGreaterThan50Characters() {
        onCreate(address().withSuburb(randomAlphabetic(51)).build()).rejected().withErrors("The suburb name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheCityNameIsGreaterThan50Characters() {
        onCreate(address().withCity(randomAlphabetic(51)).build()).rejected().withErrors("The city name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheStateNameIsGreaterThan50Characters() {
        onCreate(address().withState(randomAlphabetic(51)).build()).rejected().withErrors("The state name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheCountryNameIsGreaterThan50Characters() {
        onCreate(address().withCountry(randomAlphabetic(51)).build()).rejected().withErrors("The country name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheCountryIsNotProvided() {
        onCreate(address().withCountry(null).build()).rejected().withErrors("The country is required.");
        onCreate(address().withCountry("").build()).rejected().withErrors("The country is required.");
        onCreate(address().withCountry("   ").build()).rejected().withErrors("The country is required.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfThePostCodeIsGreaterThan10Characters() {
        onCreate(address().withPostCode(randomAlphabetic(11)).build()).rejected().withErrors("The post code cannot be greater than 10 characters.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfThePostCodeIsNotProvided() {
        onCreate(address().withPostCode(null).build()).rejected().withErrors("The post code is required.");
        onCreate(address().withPostCode("").build()).rejected().withErrors("The post code is required.");
        onCreate(address().withPostCode("   ").build()).rejected().withErrors("The post code is required.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheFormattedAddressIsGreaterThan500Characters() {
        onCreate(address().withFormattedAddress(randomAlphabetic(501)).build()).rejected().withErrors("The formatted address cannot be greater than 500 characters.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheLatitudeIsNotProvided() {
        onCreate(address().withLatitude(null).build()).rejected().withErrors("The latitude is required.");
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheLongitudeIsNotProvided() {
        onCreate(address().withLongitude(null).build()).rejected().withErrors("The longitude is required.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheUnitNumberIsGreaterThan30Characters() {
        onUpdate(address().withUnit(randomAlphabetic(31)).build()).rejected().withErrors("The unit number cannot be greater than 30 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheStreetNumberIsGreaterThan10Characters() {
        onUpdate(address().withNumber(randomAlphabetic(11)).build()).rejected().withErrors("The street number cannot be greater than 10 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheStreetNameIsGreaterThan50Characters() {
        onUpdate(address().withStreet(randomAlphabetic(51)).build()).rejected().withErrors("The street name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheSuburbNameIsGreaterThan50Characters() {
        onUpdate(address().withSuburb(randomAlphabetic(51)).build()).rejected().withErrors("The suburb name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheCityNameIsGreaterThan50Characters() {
        onUpdate(address().withCity(randomAlphabetic(51)).build()).rejected().withErrors("The city name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheStateNameIsGreaterThan50Characters() {
        onUpdate(address().withState(randomAlphabetic(51)).build()).rejected().withErrors("The state name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheCountryNameIsGreaterThan50Characters() {
        onUpdate(address().withCountry(randomAlphabetic(51)).build()).rejected().withErrors("The country name cannot be greater than 50 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheCountryIsNotProvided() {
        onUpdate(address().withCountry(null).build()).rejected().withErrors("The country is required.");
        onUpdate(address().withCountry("").build()).rejected().withErrors("The country is required.");
        onUpdate(address().withCountry("   ").build()).rejected().withErrors("The country is required.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfThePostCodeIsGreaterThan10Characters() {
        onUpdate(address().withPostCode(randomAlphabetic(11)).build()).rejected().withErrors("The post code cannot be greater than 10 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfThePostCodeIsNotProvided() {
        onUpdate(address().withPostCode(null).build()).rejected().withErrors("The post code is required.");
        onUpdate(address().withPostCode("").build()).rejected().withErrors("The post code is required.");
        onUpdate(address().withPostCode("   ").build()).rejected().withErrors("The post code is required.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheFormattedAddressIsGreaterThan500Characters() {
        onUpdate(address().withFormattedAddress(randomAlphabetic(501)).build()).rejected().withErrors("The formatted address cannot be greater than 500 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheLatitudeIsNotProvided() {
        onUpdate(address().withLatitude(null).build()).rejected().withErrors("The latitude is required.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheLongitudeIsNotProvided() {
        onUpdate(address().withLongitude(null).build()).rejected().withErrors("The longitude is required.");
    }

    public <T> ResultAssertions onCreate(T instance) {
        Result<T> result = validationService.validate(instance, Create.class);
        return PlatformAssertions.assertThat(result);
    }

    public <T> ResultAssertions onUpdate(T instance) {
        Result<T> result = validationService.validate(instance, Update.class);
        return PlatformAssertions.assertThat(result);
    }

}
