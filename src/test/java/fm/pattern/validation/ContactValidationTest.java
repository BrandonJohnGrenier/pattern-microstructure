package fm.pattern.validation;

import static fm.pattern.validation.dsl.ContactDSL.contact;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import javax.validation.Validation;

import org.junit.Before;
import org.junit.Test;

import fm.pattern.validation.sequence.Create;
import fm.pattern.validation.sequence.Update;

public class ContactValidationTest {

    private ValidationService validationService;

    @Before
    public void before() {
        this.validationService = new SimpleValidationService(Validation.buildDefaultValidatorFactory().getValidator());
    }

    @Test
    public void shouldBeAbleToCreateAContactWhenTheMobileNumberIsNullOrEmpty() {
        onCreate(contact().withMobileNumber("").build()).accepted();
        onCreate(contact().withMobileNumber("   ").build()).accepted();
        onCreate(contact().withMobileNumber(null).build()).accepted();
    }

    @Test
    public void shouldBeAbleToCreateAContactWhenThePhoneNumberIsNullOrEmpty() {
        onCreate(contact().withPhoneNumber("").build()).accepted();
        onCreate(contact().withPhoneNumber("   ").build()).accepted();
        onCreate(contact().withPhoneNumber(null).build()).accepted();
    }

    @Test
    public void shouldBeAbleToCreateAContactIfTheEmailAddressIsNotPresent() {
        onUpdate(contact().withEmailAddress(null).build()).accepted();
        onUpdate(contact().withEmailAddress("").build()).accepted();
    }

    @Test
    public void shouldNotBeAbleToCreateAContactIfTheMobileNumberAndPhoneNumberAreNotProvided() {
        onCreate(contact().withMobileNumber("").withPhoneNumber("").build()).rejected().withErrors("Please provide a mobile number or phone number.");
        onCreate(contact().withMobileNumber("  ").withPhoneNumber("  ").build()).rejected().withErrors("Please provide a mobile number or phone number.");
    }

    @Test
    public void shouldNotBeAbleToCreateAContactWhenTheMobileNumberIsInvalid() {
        onCreate(contact().withMobileNumber("0426787687").build()).accepted();
        onCreate(contact().withMobileNumber("4426787687").build()).rejected().withErrors("The mobile number supplied does not appear to be a valid mobile number.");
        onCreate(contact().withMobileNumber("0326787687").build()).rejected().withErrors("The mobile number supplied does not appear to be a valid mobile number.");
        onCreate(contact().withMobileNumber("042678768").build()).rejected().withErrors("The mobile number supplied does not appear to be a valid mobile number.");
        onCreate(contact().withMobileNumber("04267876877").build()).rejected().withErrors("The mobile number supplied does not appear to be a valid mobile number.");
        onCreate(contact().withMobileNumber("0426 787 687").build()).rejected().withErrors("The mobile number supplied does not appear to be a valid mobile number.");
        onCreate(contact().withMobileNumber("0426.787.687").build()).rejected().withErrors("The mobile number supplied does not appear to be a valid mobile number.");
        onCreate(contact().withMobileNumber("0426-787-687").build()).rejected().withErrors("The mobile number supplied does not appear to be a valid mobile number.");
        onCreate(contact().withMobileNumber("042678768a").build()).rejected().withErrors("The mobile number supplied does not appear to be a valid mobile number.");
    }

    @Test
    public void shouldNotBeAbleToCreateAContactWhenThePhoneNumberIsInvalid() {
        onCreate(contact().withPhoneNumber("0299345565").build()).accepted();
        onCreate(contact().withPhoneNumber("029934556").build()).rejected().withErrors("The phone number supplied does not appear to be a valid phone number.");
        onCreate(contact().withPhoneNumber("02993455655").build()).rejected().withErrors("The phone number supplied does not appear to be a valid phone number.");
        onCreate(contact().withPhoneNumber("0199345565").build()).rejected().withErrors("The phone number supplied does not appear to be a valid phone number.");
        onCreate(contact().withPhoneNumber("0499345565").build()).rejected().withErrors("The phone number supplied does not appear to be a valid phone number.");
        onCreate(contact().withPhoneNumber("0699345565").build()).rejected().withErrors("The phone number supplied does not appear to be a valid phone number.");
        onCreate(contact().withPhoneNumber("0999345565").build()).rejected().withErrors("The phone number supplied does not appear to be a valid phone number.");
        onCreate(contact().withPhoneNumber("02-9934-5566").build()).rejected().withErrors("The phone number supplied does not appear to be a valid phone number.");
        onCreate(contact().withPhoneNumber("02 9934 5566").build()).rejected().withErrors("The phone number supplied does not appear to be a valid phone number.");
        onCreate(contact().withPhoneNumber("(02) 9934 5566").build()).rejected().withErrors("The phone number supplied does not appear to be a valid phone number.");
    }

    @Test
    public void shouldNotBeAbleToCreateAContactIfTheEmailAddressIsGreaterThanEightyCharacters() {
        onCreate(contact().withEmailAddress(randomAlphabetic(81)).build()).rejected().withErrors("An email address cannot be greater than 80 characters.");
    }

    @Test
    public void shouldNotBeAbleToCreateAContactIfTheEmailAddressIsInvalid() {
        onCreate(contact().withEmailAddress("notemail").build()).rejected().withErrors("The email address supplied does not appear to be a valid email address.");
    }

    @Test
    public void shouldNotBeAbleToCreateAContactIfTheNameIsNotProvided() {
        onCreate(contact().withName(null).build()).rejected().withErrors("A contact name is required.");
        onCreate(contact().withName("").build()).rejected().withErrors("A contact name is required.");
        onCreate(contact().withName("  ").build()).rejected().withErrors("A contact name is required.");
    }

    @Test
    public void shouldBeAbleToUpdateAContactWhenTheMobileNumberIsNullOrEmpty() {
        onUpdate(contact().withMobileNumber("").build()).accepted();
        onUpdate(contact().withMobileNumber("   ").build()).accepted();
        onUpdate(contact().withMobileNumber(null).build()).accepted();
    }

    @Test
    public void shouldBeAbleToUpdateAContactWhenThePhoneNumberIsNullOrEmpty() {
        onUpdate(contact().withPhoneNumber("").build()).accepted();
        onUpdate(contact().withPhoneNumber("   ").build()).accepted();
        onUpdate(contact().withPhoneNumber(null).build()).accepted();
    }

    @Test
    public void shouldBeAbleToUpdateAContactIfTheEmailAddressIsNotPresent() {
        onUpdate(contact().withEmailAddress(null).build()).accepted();
        onUpdate(contact().withEmailAddress("").build()).accepted();
    }

    @Test
    public void shouldNotBeAbleToUpdateAContactIfTheMobileNumberAndPhoneNumberAreNotProvided() {
        onUpdate(contact().withMobileNumber("").withPhoneNumber("").build()).rejected().withErrors("Please provide a mobile number or phone number.");
        onUpdate(contact().withMobileNumber("  ").withPhoneNumber("  ").build()).rejected().withErrors("Please provide a mobile number or phone number.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAContactWhenTheMobileNumberIsInvalid() {
        onUpdate(contact().withMobileNumber("0426787687").build()).accepted();
        onUpdate(contact().withMobileNumber("4426787687").build()).rejected().withErrors("The mobile number supplied does not appear to be a valid mobile number.");
        onUpdate(contact().withMobileNumber("0326787687").build()).rejected().withErrors("The mobile number supplied does not appear to be a valid mobile number.");
        onUpdate(contact().withMobileNumber("042678768").build()).rejected().withErrors("The mobile number supplied does not appear to be a valid mobile number.");
        onUpdate(contact().withMobileNumber("04267876877").build()).rejected().withErrors("The mobile number supplied does not appear to be a valid mobile number.");
        onUpdate(contact().withMobileNumber("0426 787 687").build()).rejected().withErrors("The mobile number supplied does not appear to be a valid mobile number.");
        onUpdate(contact().withMobileNumber("0426.787.687").build()).rejected().withErrors("The mobile number supplied does not appear to be a valid mobile number.");
        onUpdate(contact().withMobileNumber("0426-787-687").build()).rejected().withErrors("The mobile number supplied does not appear to be a valid mobile number.");
        onUpdate(contact().withMobileNumber("042678768a").build()).rejected().withErrors("The mobile number supplied does not appear to be a valid mobile number.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAContactWhenThePhoneNumberIsInvalid() {
        onUpdate(contact().withPhoneNumber("0299345565").build()).accepted();
        onUpdate(contact().withPhoneNumber("029934556").build()).rejected().withErrors("The phone number supplied does not appear to be a valid phone number.");
        onUpdate(contact().withPhoneNumber("02993455655").build()).rejected().withErrors("The phone number supplied does not appear to be a valid phone number.");
        onUpdate(contact().withPhoneNumber("0199345565").build()).rejected().withErrors("The phone number supplied does not appear to be a valid phone number.");
        onUpdate(contact().withPhoneNumber("0499345565").build()).rejected().withErrors("The phone number supplied does not appear to be a valid phone number.");
        onUpdate(contact().withPhoneNumber("0699345565").build()).rejected().withErrors("The phone number supplied does not appear to be a valid phone number.");
        onUpdate(contact().withPhoneNumber("0999345565").build()).rejected().withErrors("The phone number supplied does not appear to be a valid phone number.");
        onUpdate(contact().withPhoneNumber("02-9934-5566").build()).rejected().withErrors("The phone number supplied does not appear to be a valid phone number.");
        onUpdate(contact().withPhoneNumber("02 9934 5566").build()).rejected().withErrors("The phone number supplied does not appear to be a valid phone number.");
        onUpdate(contact().withPhoneNumber("(02) 9934 5566").build()).rejected().withErrors("The phone number supplied does not appear to be a valid phone number.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAContactIfTheEmailAddressIsGreaterThanEightyCharacters() {
        onUpdate(contact().withEmailAddress(randomAlphabetic(81)).build()).rejected().withErrors("An email address cannot be greater than 80 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAContactIfTheEmailAddressIsInvalid() {
        onUpdate(contact().withEmailAddress("notemail").build()).rejected().withErrors("The email address supplied does not appear to be a valid email address.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAContactIfTheNameIsNotProvided() {
        onUpdate(contact().withName(null).build()).rejected().withErrors("A contact name is required.");
        onUpdate(contact().withName("").build()).rejected().withErrors("A contact name is required.");
        onUpdate(contact().withName("  ").build()).rejected().withErrors("A contact name is required.");
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
