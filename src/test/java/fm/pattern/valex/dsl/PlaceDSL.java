package fm.pattern.valex.dsl;

import static fm.pattern.valex.dsl.AddressDSL.address;
import static fm.pattern.valex.dsl.ContactDSL.contact;
import fm.pattern.valex.fixtures.model.Address;
import fm.pattern.valex.fixtures.model.Contact;
import fm.pattern.valex.fixtures.model.Place;

public class PlaceDSL extends AbstractDSL<PlaceDSL, Place> {

    private Contact contact = contact().build();
    private Address address = address().build();
    private String instructions = null;

    public static PlaceDSL place() {
        return new PlaceDSL();
    }

    public PlaceDSL withContact(Contact contact) {
        this.contact = contact;
        return this;
    }

    public PlaceDSL withAddress(Address address) {
        this.address = address;
        return this;
    }

    public PlaceDSL withInstructions(String instructions) {
        this.instructions = instructions;
        return this;
    }

    public Place build() {
        Place place = new Place();
        place.setContact(contact);
        place.setAddress(address);
        place.setInstructions(instructions);
        return place;
    }

    public PlaceDSL withContact(ContactDSL dsl) {
        return withContact(dsl.build());
    }

    public PlaceDSL withoutAddress() {
        return withAddress(null);
    }

    public PlaceDSL withoutContact() {
        return withContact((Contact) null);
    }

}
