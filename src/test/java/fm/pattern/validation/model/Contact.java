package fm.pattern.validation.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import fm.pattern.validation.annotations.MobileNumber;
import fm.pattern.validation.annotations.PhoneNumber;
import fm.pattern.validation.sequence.CreateLevel1;
import fm.pattern.validation.sequence.CreateLevel2;
import fm.pattern.validation.sequence.UpdateLevel1;
import fm.pattern.validation.sequence.UpdateLevel2;

public class Contact {

    @NotBlank(message = "{contact.name.required}", groups = { CreateLevel1.class, UpdateLevel1.class })
    @Size(max = 50, message = "{contact.name.size}", groups = { CreateLevel2.class, UpdateLevel2.class })
    private String name;

    @PhoneNumber(message = "{contact.phoneNumber.format}", groups = { CreateLevel2.class, UpdateLevel2.class })
    private String phoneNumber;

    @MobileNumber(message = "{contact.mobileNumber.format}", groups = { CreateLevel2.class, UpdateLevel2.class })
    private String mobileNumber;

    @Size(max = 80, message = "{contact.emailAddress.size}", groups = { CreateLevel1.class, UpdateLevel1.class })
    @Email(message = "{contact.emailAddress.format}", groups = { CreateLevel2.class, UpdateLevel2.class })
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
