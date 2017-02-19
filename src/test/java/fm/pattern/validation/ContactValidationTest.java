package fm.pattern.validation;

import static fm.pattern.validation.dsl.ContactDSL.contact;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.junit.Test;

public class ContactValidationTest extends ValidationTest {

    @Test
    public void shouldBeAbleToCreateAContactWhenTheMobileNumberIsNullOrEmpty() {
        assertCreate(contact().withMobileNumber("").build()).accepted();
        assertCreate(contact().withMobileNumber("   ").build()).accepted();
        assertCreate(contact().withMobileNumber(null).build()).accepted();
    }

    @Test
    public void shouldBeAbleToCreateAContactWhenThePhoneNumberIsNullOrEmpty() {
        assertCreate(contact().withPhoneNumber("").build()).accepted();
        assertCreate(contact().withPhoneNumber("   ").build()).accepted();
        assertCreate(contact().withPhoneNumber(null).build()).accepted();
    }

    @Test
    public void shouldBeAbleToCreateAContactIfTheEmailAddressIsNotPresent() {
        assertUpdate(contact().withEmailAddress(null).build()).accepted();
        assertUpdate(contact().withEmailAddress("").build()).accepted();
    }

    @Test
    public void shouldNotBeAbleToCreateAContactWhenTheMobileNumberIsInvalid() {
        assertCreate(contact().withMobileNumber("0426787687").build()).accepted();
        assertCreate(contact().withMobileNumber("4426787687").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        assertCreate(contact().withMobileNumber("0326787687").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        assertCreate(contact().withMobileNumber("042678768").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        assertCreate(contact().withMobileNumber("04267876877").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        assertCreate(contact().withMobileNumber("0426 787 687").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        assertCreate(contact().withMobileNumber("0426.787.687").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        assertCreate(contact().withMobileNumber("0426-787-687").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        assertCreate(contact().withMobileNumber("042678768a").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
    }

    @Test
    public void shouldNotBeAbleToCreateAContactWhenThePhoneNumberIsInvalid() {
        assertCreate(contact().withPhoneNumber("0299345565").build()).accepted();
        assertCreate(contact().withPhoneNumber("029934556").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertCreate(contact().withPhoneNumber("02993455655").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertCreate(contact().withPhoneNumber("0199345565").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertCreate(contact().withPhoneNumber("0499345565").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertCreate(contact().withPhoneNumber("0699345565").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertCreate(contact().withPhoneNumber("0999345565").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertCreate(contact().withPhoneNumber("02-9934-5566").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertCreate(contact().withPhoneNumber("02 9934 5566").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertCreate(contact().withPhoneNumber("(02) 9934 5566").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
    }

    @Test
    public void shouldNotBeAbleToCreateAContactIfTheEmailAddressIsGreaterThanEightyCharacters() {
        assertCreate(contact().withEmailAddress(randomAlphabetic(81)).build()).rejected().withDescription("An email address cannot be greater than 80 characters.");
    }

    @Test
    public void shouldNotBeAbleToCreateAContactIfTheEmailAddressIsInvalid() {
        assertCreate(contact().withEmailAddress("notemail").build()).rejected().withDescription("The email address supplied does not appear to be a valid email address.");
    }

    @Test
    public void shouldNotBeAbleToCreateAContactIfTheNameIsNotProvided() {
        assertCreate(contact().withName(null).build()).rejected().withDescription("A contact name is required.");
        assertCreate(contact().withName("").build()).rejected().withDescription("A contact name is required.");
        assertCreate(contact().withName("  ").build()).rejected().withDescription("A contact name is required.");
    }

    @Test
    public void shouldBeAbleToUpdateAContactWhenTheMobileNumberIsNullOrEmpty() {
        assertUpdate(contact().withMobileNumber("").build()).accepted();
        assertUpdate(contact().withMobileNumber("   ").build()).accepted();
        assertUpdate(contact().withMobileNumber(null).build()).accepted();
    }

    @Test
    public void shouldBeAbleToUpdateAContactWhenThePhoneNumberIsNullOrEmpty() {
        assertUpdate(contact().withPhoneNumber("").build()).accepted();
        assertUpdate(contact().withPhoneNumber("   ").build()).accepted();
        assertUpdate(contact().withPhoneNumber(null).build()).accepted();
    }

    @Test
    public void shouldBeAbleToUpdateAContactIfTheEmailAddressIsNotPresent() {
        assertUpdate(contact().withEmailAddress(null).build()).accepted();
        assertUpdate(contact().withEmailAddress("").build()).accepted();
    }

    @Test
    public void shouldNotBeAbleToUpdateAContactWhenTheMobileNumberIsInvalid() {
        assertUpdate(contact().withMobileNumber("0426787687").build()).accepted();
        assertUpdate(contact().withMobileNumber("4426787687").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        assertUpdate(contact().withMobileNumber("0326787687").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        assertUpdate(contact().withMobileNumber("042678768").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        assertUpdate(contact().withMobileNumber("04267876877").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        assertUpdate(contact().withMobileNumber("0426 787 687").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        assertUpdate(contact().withMobileNumber("0426.787.687").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        assertUpdate(contact().withMobileNumber("0426-787-687").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        assertUpdate(contact().withMobileNumber("042678768a").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAContactWhenThePhoneNumberIsInvalid() {
        assertUpdate(contact().withPhoneNumber("0299345565").build()).accepted();
        assertUpdate(contact().withPhoneNumber("029934556").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertUpdate(contact().withPhoneNumber("02993455655").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertUpdate(contact().withPhoneNumber("0199345565").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertUpdate(contact().withPhoneNumber("0499345565").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertUpdate(contact().withPhoneNumber("0699345565").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertUpdate(contact().withPhoneNumber("0999345565").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertUpdate(contact().withPhoneNumber("02-9934-5566").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertUpdate(contact().withPhoneNumber("02 9934 5566").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertUpdate(contact().withPhoneNumber("(02) 9934 5566").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAContactIfTheEmailAddressIsGreaterThanEightyCharacters() {
        assertUpdate(contact().withEmailAddress(randomAlphabetic(81)).build()).rejected().withDescription("An email address cannot be greater than 80 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAContactIfTheEmailAddressIsInvalid() {
        assertUpdate(contact().withEmailAddress("notemail").build()).rejected().withDescription("The email address supplied does not appear to be a valid email address.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAContactIfTheNameIsNotProvided() {
        assertUpdate(contact().withName(null).build()).rejected().withDescription("A contact name is required.");
        assertUpdate(contact().withName("").build()).rejected().withDescription("A contact name is required.");
        assertUpdate(contact().withName("  ").build()).rejected().withDescription("A contact name is required.");
    }

}
