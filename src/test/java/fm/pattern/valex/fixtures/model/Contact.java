package fm.pattern.valex.fixtures.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import fm.pattern.valex.fixtures.annotations.MobileNumber;
import fm.pattern.valex.fixtures.annotations.PhoneNumber;
import fm.pattern.valex.sequences.CreateLevel1;
import fm.pattern.valex.sequences.CreateLevel2;
import fm.pattern.valex.sequences.UpdateLevel1;
import fm.pattern.valex.sequences.UpdateLevel2;

public class Contact {

    @NotBlank(message = "{contact.name.required}", groups = { CreateLevel1.class, UpdateLevel1.class })
    @Size(max = 50, message = "{contact.name.size}", groups = { CreateLevel2.class, UpdateLevel2.class })
    private String name;

    @PhoneNumber(message = "{contact.phone_number.format}", groups = { CreateLevel2.class, UpdateLevel2.class })
    private String phoneNumber;

    @MobileNumber(message = "{contact.mobile_number.format}", groups = { CreateLevel2.class, UpdateLevel2.class })
    private String mobileNumber;

    @Size(max = 80, message = "{contact.email_address.size}", groups = { CreateLevel1.class, UpdateLevel1.class })
    @Email(message = "{contact.email_address.format}", groups = { CreateLevel2.class, UpdateLevel2.class })
    private String emailAddress;

    public Contact() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
