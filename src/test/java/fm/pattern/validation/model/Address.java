package fm.pattern.validation.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import fm.pattern.validation.sequences.CreateLevel1;
import fm.pattern.validation.sequences.CreateLevel2;
import fm.pattern.validation.sequences.UpdateLevel1;
import fm.pattern.validation.sequences.UpdateLevel2;

public class Address {

    @Size(max = 30, message = "{address.unit.size}", groups = { CreateLevel2.class, UpdateLevel2.class })
    private String unit;

    @Size(max = 10, message = "{address.number.size}", groups = { CreateLevel2.class, UpdateLevel2.class })
    private String number;

    @Size(max = 50, message = "{address.street.size}", groups = { CreateLevel2.class, UpdateLevel2.class })
    private String street;

    @Size(max = 50, message = "{address.suburb.size}", groups = { CreateLevel2.class, UpdateLevel2.class })
    private String suburb;

    @Size(max = 50, message = "{address.city.size}", groups = { CreateLevel2.class, UpdateLevel2.class })
    private String city;

    @Size(max = 50, message = "{address.state.size}", groups = { CreateLevel2.class, UpdateLevel2.class })
    private String state;

    @NotBlank(message = "{address.country.required}", groups = { CreateLevel1.class, UpdateLevel1.class })
    @Size(max = 50, message = "{address.country.size}", groups = { CreateLevel2.class, UpdateLevel2.class })
    private String country;

    @NotBlank(message = "{address.post_code.required}", groups = { CreateLevel1.class, UpdateLevel1.class })
    @Size(max = 10, message = "{address.post_code.size}", groups = { CreateLevel2.class, UpdateLevel2.class })
    private String postCode;

    @Size(max = 500, message = "{address.formatted_address.size}", groups = { CreateLevel2.class, UpdateLevel2.class })
    private String formattedAddress;

    @Valid
    @NotNull(message = "{address.location.required}", groups = { CreateLevel1.class, UpdateLevel1.class })
    private Location location;

    public Address() {

    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
