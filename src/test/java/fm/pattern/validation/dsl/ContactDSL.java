package fm.pattern.validation.dsl;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import fm.pattern.validation.model.Contact;

public class ContactDSL extends AbstractDSL<ContactDSL, Contact> {

	private String name = randomAlphabetic(8) + " " + randomAlphabetic(8);
	private String phoneNumber = "0233945565";
	private String mobileNumber = "0415225664";
	private String emailAddress = randomAlphabetic(8) + "." + randomAlphabetic(8) + "@email.com";

	public static ContactDSL contact() {
		return new ContactDSL();
	}

	public ContactDSL withName(String name) {
		this.name = name;
		return this;
	}

	public ContactDSL withPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}

	public ContactDSL withMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
		return this;
	}

	public ContactDSL withEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
		return this;
	}

	public Contact build() {
		Contact contact = new Contact();
		contact.setName(name);
		contact.setPhoneNumber(phoneNumber);
		contact.setMobileNumber(mobileNumber);
		contact.setEmailAddress(emailAddress);
		return contact;
	}

}
