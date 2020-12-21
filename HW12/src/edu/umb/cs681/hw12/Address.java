package edu.umb.cs681.hw12;

public final class Address {
	private final String street, city, state;
	private final int zipCode;

	public Address(String street, String city, String state, int zipCode) {// Thread-safe constructor
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public int getZipCode() {
		return zipCode;
	}

	public boolean equals(Address address) {
		if (this.toString().equals(address.toString())) {
			return true;
		} else {
			return false;
		}

		// Multiple steps, but thread-safe
		// String.toString() and String.equals() are thread-safe
		// “this” is immutable. “anotherSSN” is local.
	}

	public String toString() {
		return street + "-" + city + "-" + state + "-" + zipCode;
		// Multiple steps, but thread-safe
		// Reads on final variables are thread-safe.
	}

	// change() to change the current address
	public Address change(String street, String city, String state, int zipCode) {
		return new Address(street, city, state, zipCode);
	}

}
