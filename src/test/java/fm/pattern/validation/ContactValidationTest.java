package fm.pattern.validation;

import static fm.pattern.validation.dsl.ContactDSL.contact;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.junit.Test;

public class ContactValidationTest extends ValidationTest {

    @Test
    public void shouldBeAbleToCreateAContactWhenTheMobileNumberIsNullOrEmpty() {
        create(contact().withMobileNumber("").build()).accepted();
        create(contact().withMobileNumber("   ").build()).accepted();
        create(contact().withMobileNumber(null).build()).accepted();
    }

    @Test
    public void shouldBeAbleToCreateAContactWhenThePhoneNumberIsNullOrEmpty() {
        create(contact().withPhoneNumber("").build()).accepted();
        create(contact().withPhoneNumber("   ").build()).accepted();
        create(contact().withPhoneNumber(null).build()).accepted();
    }

    @Test
    public void shouldBeAbleToCreateAContactIfTheEmailAddressIsNotPresent() {
        update(contact().withEmailAddress(null).build()).accepted();
        update(contact().withEmailAddress("").build()).accepted();
    }

    @Test
    public void shouldNotBeAbleToCreateAContactWhenTheMobileNumberIsInvalid() {
        create(contact().withMobileNumber("0426787687").build()).accepted();
        create(contact().withMobileNumber("4426787687").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        create(contact().withMobileNumber("0326787687").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        create(contact().withMobileNumber("042678768").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        create(contact().withMobileNumber("04267876877").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        create(contact().withMobileNumber("0426 787 687").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        create(contact().withMobileNumber("0426.787.687").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        create(contact().withMobileNumber("0426-787-687").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        create(contact().withMobileNumber("042678768a").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
    }

    @Test
    public void shouldNotBeAbleToCreateAContactWhenThePhoneNumberIsInvalid() {
        create(contact().withPhoneNumber("0299345565").build()).accepted();
        create(contact().withPhoneNumber("029934556").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        create(contact().withPhoneNumber("02993455655").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        create(contact().withPhoneNumber("0199345565").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        create(contact().withPhoneNumber("0499345565").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        create(contact().withPhoneNumber("0699345565").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        create(contact().withPhoneNumber("0999345565").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        create(contact().withPhoneNumber("02-9934-5566").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        create(contact().withPhoneNumber("02 9934 5566").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        create(contact().withPhoneNumber("(02) 9934 5566").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
    }

    @Test
    public void shouldNotBeAbleToCreateAContactIfTheEmailAddressIsGreaterThanEightyCharacters() {
        create(contact().withEmailAddress(randomAlphabetic(81)).build()).rejected().withDescription("An email address cannot be greater than 80 characters.");
    }

    @Test
    public void shouldNotBeAbleToCreateAContactIfTheEmailAddressIsInvalid() {
        create(contact().withEmailAddress("notemail").build()).rejected().withDescription("The email address supplied does not appear to be a valid email address.");
    }

    @Test
    public void shouldNotBeAbleToCreateAContactIfTheNameIsNotProvided() {
        create(contact().withName(null).build()).rejected().withDescription("A contact name is required.");
        create(contact().withName("").build()).rejected().withDescription("A contact name is required.");
        create(contact().withName("  ").build()).rejected().withDescription("A contact name is required.");
    }

    @Test
    public void shouldBeAbleToUpdateAContactWhenTheMobileNumberIsNullOrEmpty() {
        update(contact().withMobileNumber("").build()).accepted();
        update(contact().withMobileNumber("   ").build()).accepted();
        update(contact().withMobileNumber(null).build()).accepted();
    }

    @Test
    public void shouldBeAbleToUpdateAContactWhenThePhoneNumberIsNullOrEmpty() {
        update(contact().withPhoneNumber("").build()).accepted();
        update(contact().withPhoneNumber("   ").build()).accepted();
        update(contact().withPhoneNumber(null).build()).accepted();
    }

    @Test
    public void shouldBeAbleToUpdateAContactIfTheEmailAddressIsNotPresent() {
        update(contact().withEmailAddress(null).build()).accepted();
        update(contact().withEmailAddress("").build()).accepted();
    }

    @Test
    public void shouldNotBeAbleToUpdateAContactWhenTheMobileNumberIsInvalid() {
        update(contact().withMobileNumber("0426787687").build()).accepted();
        update(contact().withMobileNumber("4426787687").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        update(contact().withMobileNumber("0326787687").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        update(contact().withMobileNumber("042678768").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        update(contact().withMobileNumber("04267876877").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        update(contact().withMobileNumber("0426 787 687").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        update(contact().withMobileNumber("0426.787.687").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        update(contact().withMobileNumber("0426-787-687").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        update(contact().withMobileNumber("042678768a").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAContactWhenThePhoneNumberIsInvalid() {
        update(contact().withPhoneNumber("0299345565").build()).accepted();
        update(contact().withPhoneNumber("029934556").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        update(contact().withPhoneNumber("02993455655").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        update(contact().withPhoneNumber("0199345565").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        update(contact().withPhoneNumber("0499345565").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        update(contact().withPhoneNumber("0699345565").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        update(contact().withPhoneNumber("0999345565").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        update(contact().withPhoneNumber("02-9934-5566").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        update(contact().withPhoneNumber("02 9934 5566").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        update(contact().withPhoneNumber("(02) 9934 5566").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAContactIfTheEmailAddressIsGreaterThanEightyCharacters() {
        update(contact().withEmailAddress(randomAlphabetic(81)).build()).rejected().withDescription("An email address cannot be greater than 80 characters.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAContactIfTheEmailAddressIsInvalid() {
        update(contact().withEmailAddress("notemail").build()).rejected().withDescription("The email address supplied does not appear to be a valid email address.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAContactIfTheNameIsNotProvided() {
        update(contact().withName(null).build()).rejected().withDescription("A contact name is required.");
        update(contact().withName("").build()).rejected().withDescription("A contact name is required.");
        update(contact().withName("  ").build()).rejected().withDescription("A contact name is required.");
    }

}
