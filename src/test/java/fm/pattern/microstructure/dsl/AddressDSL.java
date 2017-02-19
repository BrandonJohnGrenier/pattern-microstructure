package fm.pattern.microstructure.dsl;

import fm.pattern.microstructure.model.Address;
import fm.pattern.microstructure.model.Location;

public class AddressDSL extends AbstractDSL<AddressDSL, Address> {

	private String unit = "2";
	private String number = "121";
	private String street = "Cremorne Road";
	private String suburb = "Cremorne Point";
	private String city = "Sydney";
	private String state = "New South Wales";
	private String country = "Australia";
	private String postCode = "2090";
	private String formattedAddress = "2/121 Cremorne Road, Cremorne Point NSW 2090";
	private Double lat = -33.8396210;
	private Double lon = 151.2277740;

	public static AddressDSL address() {
		return new AddressDSL();
	}

	public AddressDSL withUnit(String unit) {
		this.unit = unit;
		return this;
	}

	public AddressDSL withNumber(String number) {
		this.number = number;
		return this;
	}

	public AddressDSL withStreet(String street) {
		this.street = street;
		return this;
	}

	public AddressDSL withSuburb(String suburb) {
		this.suburb = suburb;
		return this;
	}

	public AddressDSL withCity(String city) {
		this.city = city;
		return this;
	}

	public AddressDSL withState(String state) {
		this.state = state;
		return this;
	}

	public AddressDSL withCountry(String country) {
		this.country = country;
		return this;
	}

	public AddressDSL withPostCode(String postCode) {
		this.postCode = postCode;
		return this;
	}

	public AddressDSL withFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
		return this;
	}

	public AddressDSL withLocation(Double lat, Double lon) {
		this.lat = lat;
		this.lon = lon;
		return this;
	}

	public AddressDSL withLatitude(Double lat) {
		this.lat = lat;
		return this;
	}

	public AddressDSL withLongitude(Double lon) {
		this.lon = lon;
		return this;
	}

	public Address build() {
		Address address = new Address();
		address.setUnit(unit);
		address.setNumber(number);
		address.setStreet(street);
		address.setSuburb(suburb);
		address.setCity(city);
		address.setState(state);
		address.setCountry(country);
		address.setPostCode(postCode);
		address.setFormattedAddress(formattedAddress);
		address.setLocation(new Location(lat, lon));
		return address;
	}

}
