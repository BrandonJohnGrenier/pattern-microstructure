package fm.pattern.valex;

import static fm.pattern.valex.dsl.ContactDSL.contact;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.junit.Test;

public class ContactValidationTest extends ValidationServiceTest {

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
        assertCreate(contact().withEmailAddress("").build()).accepted();
        assertCreate(contact().withEmailAddress(null).build()).accepted();
    }

    @Test
    public void shouldNotBeAbleToCreateAContactWhenTheMobileNumberIsInvalid() {
        assertCreate(contact().withMobileNumber("0426313313").build()).accepted();

        assertCreate(contact().withMobileNumber("426313313").build()).rejected().withMessage("The mobile number supplied does not appear to be a valid mobile number.");
        assertCreate(contact().withMobileNumber("0326313313").build()).rejected().withMessage("The mobile number supplied does not appear to be a valid mobile number.");
        assertCreate(contact().withMobileNumber("042678768").build()).rejected().withMessage("The mobile number supplied does not appear to be a valid mobile number.");
        assertCreate(contact().withMobileNumber("04267876877").build()).rejected().withMessage("The mobile number supplied does not appear to be a valid mobile number.");
        assertCreate(contact().withMobileNumber("0426 313 313").build()).rejected().withMessage("The mobile number supplied does not appear to be a valid mobile number.");
        assertCreate(contact().withMobileNumber("0426.313.313").build()).rejected().withMessage("The mobile number supplied does not appear to be a valid mobile number.");
        assertCreate(contact().withMobileNumber("0426-313-313").build()).rejected().withMessage("The mobile number supplied does not appear to be a valid mobile number.");
        assertCreate(contact().withMobileNumber("042631331a").build()).rejected().withMessage("The mobile number supplied does not appear to be a valid mobile number.");
    }

    @Test
    public void shouldNotBeAbleToCreateAContactWhenThePhoneNumberIsInvalid() {
        assertCreate(contact().withPhoneNumber("0299345565").build()).accepted();

        assertCreate(contact().withPhoneNumber("029934556").build()).rejected().withMessage("The phone number supplied does not appear to be a valid phone number.");
        assertCreate(contact().withPhoneNumber("02993455655").build()).rejected().withMessage("The phone number supplied does not appear to be a valid phone number.");
        assertCreate(contact().withPhoneNumber("0199345565").build()).rejected().withMessage("The phone number supplied does not appear to be a valid phone number.");
        assertCreate(contact().withPhoneNumber("0499345565").build()).rejected().withMessage("The phone number supplied does not appear to be a valid phone number.");
        assertCreate(contact().withPhoneNumber("0699345565").build()).rejected().withMessage("The phone number supplied does not appear to be a valid phone number.");
        assertCreate(contact().withPhoneNumber("0999345565").build()).rejected().withMessage("The phone number supplied does not appear to be a valid phone number.");
        assertCreate(contact().withPhoneNumber("02-9934-5566").build()).rejected().withMessage("The phone number supplied does not appear to be a valid phone number.");
        assertCreate(contact().withPhoneNumber("02 9934 5566").build()).rejected().withMessage("The phone number supplied does not appear to be a valid phone number.");
        assertCreate(contact().withPhoneNumber("(02) 9934 5566").build()).rejected().withMessage("The phone number supplied does not appear to be a valid phone number.");
    }

    @Test
    public void shouldNotBeAbleToCreateAContactIfTheEmailAddressIsGreaterThanEightyCharacters() {
        assertCreate(contact().withEmailAddress(randomAlphabetic(81)).build()).rejected().withErrorCount(1).withError("CON-1002", "An email address cannot be greater than 80 characters.", UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToCreateAContactIfTheEmailAddressIsInvalid() {
        assertCreate(contact().withEmailAddress("test@email.com").build()).accepted();

        assertCreate(contact().withEmailAddress("notemail").build()).rejected().withMessage("The email address supplied does not appear to be a valid email address.");
        assertCreate(contact().withEmailAddress("notemail@").build()).rejected().withMessage("The email address supplied does not appear to be a valid email address.");
        assertCreate(contact().withEmailAddress("@email.com").build()).rejected().withMessage("The email address supplied does not appear to be a valid email address.");
    }

    @Test
    public void shouldNotBeAbleToCreateAContactIfTheNameIsNotProvided() {
        assertCreate(contact().withName(null).build()).rejected().withError("CON-1000", "A contact name is required.", UnprocessableEntityException.class);
        assertCreate(contact().withName("").build()).rejected().withError("CON-1000", "A contact name is required.", UnprocessableEntityException.class);
        assertCreate(contact().withName("  ").build()).rejected().withError("CON-1000", "A contact name is required.", UnprocessableEntityException.class);
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
        assertUpdate(contact().withEmailAddress("").build()).accepted();
        assertUpdate(contact().withEmailAddress(null).build()).accepted();
    }

    @Test
    public void shouldNotBeAbleToUpdateAContactWhenTheMobileNumberIsInvalid() {
        assertUpdate(contact().withMobileNumber("0426313313").build()).accepted();

        assertUpdate(contact().withMobileNumber("426313313").build()).rejected().withMessage("The mobile number supplied does not appear to be a valid mobile number.");
        assertUpdate(contact().withMobileNumber("0326313313").build()).rejected().withMessage("The mobile number supplied does not appear to be a valid mobile number.");
        assertUpdate(contact().withMobileNumber("042678768").build()).rejected().withMessage("The mobile number supplied does not appear to be a valid mobile number.");
        assertUpdate(contact().withMobileNumber("04267876877").build()).rejected().withMessage("The mobile number supplied does not appear to be a valid mobile number.");
        assertUpdate(contact().withMobileNumber("0426 313 313").build()).rejected().withMessage("The mobile number supplied does not appear to be a valid mobile number.");
        assertUpdate(contact().withMobileNumber("0426.313.313").build()).rejected().withMessage("The mobile number supplied does not appear to be a valid mobile number.");
        assertUpdate(contact().withMobileNumber("0426-313-313").build()).rejected().withMessage("The mobile number supplied does not appear to be a valid mobile number.");
        assertUpdate(contact().withMobileNumber("042631331a").build()).rejected().withMessage("The mobile number supplied does not appear to be a valid mobile number.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAContactWhenThePhoneNumberIsInvalid() {
        assertUpdate(contact().withPhoneNumber("0299345565").build()).accepted();

        assertUpdate(contact().withPhoneNumber("029934556").build()).rejected().withMessage("The phone number supplied does not appear to be a valid phone number.");
        assertUpdate(contact().withPhoneNumber("02993455655").build()).rejected().withMessage("The phone number supplied does not appear to be a valid phone number.");
        assertUpdate(contact().withPhoneNumber("0199345565").build()).rejected().withMessage("The phone number supplied does not appear to be a valid phone number.");
        assertUpdate(contact().withPhoneNumber("0499345565").build()).rejected().withMessage("The phone number supplied does not appear to be a valid phone number.");
        assertUpdate(contact().withPhoneNumber("0699345565").build()).rejected().withMessage("The phone number supplied does not appear to be a valid phone number.");
        assertUpdate(contact().withPhoneNumber("0999345565").build()).rejected().withMessage("The phone number supplied does not appear to be a valid phone number.");
        assertUpdate(contact().withPhoneNumber("02-9934-5566").build()).rejected().withMessage("The phone number supplied does not appear to be a valid phone number.");
        assertUpdate(contact().withPhoneNumber("02 9934 5566").build()).rejected().withMessage("The phone number supplied does not appear to be a valid phone number.");
        assertUpdate(contact().withPhoneNumber("(02) 9934 5566").build()).rejected().withMessage("The phone number supplied does not appear to be a valid phone number.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAContactIfTheEmailAddressIsGreaterThanEightyCharacters() {
        assertCreate(contact().withEmailAddress(randomAlphabetic(81)).build()).rejected().withError("CON-1002", "An email address cannot be greater than 80 characters.", UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToUpdateAContactIfTheEmailAddressIsInvalid() {
        assertUpdate(contact().withEmailAddress("test@email.com").build()).accepted();

        assertUpdate(contact().withEmailAddress("notemail").build()).rejected().withMessage("The email address supplied does not appear to be a valid email address.");
        assertUpdate(contact().withEmailAddress("notemail@").build()).rejected().withMessage("The email address supplied does not appear to be a valid email address.");
        assertUpdate(contact().withEmailAddress("@email.com").build()).rejected().withMessage("The email address supplied does not appear to be a valid email address.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAContactIfTheNameIsNotProvided() {
        assertUpdate(contact().withName(null).build()).rejected().withError("CON-1000", "A contact name is required.", UnprocessableEntityException.class);
        assertUpdate(contact().withName("").build()).rejected().withError("CON-1000", "A contact name is required.", UnprocessableEntityException.class);
        assertUpdate(contact().withName("  ").build()).rejected().withError("CON-1000", "A contact name is required.", UnprocessableEntityException.class);
    }

}
