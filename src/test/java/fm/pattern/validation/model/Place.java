package fm.pattern.validation.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import fm.pattern.validation.sequence.CreateLevel1;
import fm.pattern.validation.sequence.UpdateLevel1;

public class Place  {

	@Valid
	@NotNull(message = "{place.contact.required}", groups = { CreateLevel1.class, UpdateLevel1.class })
	private Contact contact;

	@Valid
	@NotNull(message = "{place.address.required}", groups = { CreateLevel1.class, UpdateLevel1.class })
	private Address address;

	@Size(max = 250, message = "{place.instructions.size}", groups = { CreateLevel1.class, UpdateLevel1.class })
	private String instructions;

	public Place() {
		
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}



}
