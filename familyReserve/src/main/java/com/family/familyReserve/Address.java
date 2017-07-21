package com.family.familyReserve;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table (name = "address")
public class Address implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne
	@JoinColumn(name = "personId")
	@NotNull(message = "Person required")
	private Person person;
	
	@NotNull(message = "Street Address is required.")
	@Size(min = 5)
	private String streetAddress;
	
	@NotNull(message = "City is required.")
	@Size(min = 2)
	private String city;
	
	@NotNull(message = "State is required.")
	@Size(min = 2)
	private String state;
	
	@NotNull(message = "Zipcode is required")
	private int zipCode;
	
	private Double latitude;
	
	private Double longitude;
	
	// Constructors
		public Address() {
		}
		
		public Address(Person person, String streetAddress, String city, String state, int zipCode, Double latitude, Double longitude ) {
			this.person= person;
			this.streetAddress=streetAddress;
			this.city=city;
			this.state=state;
			this.zipCode=zipCode;
			this.latitude=latitude;
			this.longitude=longitude;
		}
		
		public Address(Person person, String streetAddress, String city, String state, int zipCode) {
			this.person= person;
			this.streetAddress=streetAddress;
			this.city=city;
			this.state=state;
			this.zipCode=zipCode;
		}
		
		public String getStreetAddress() {
			return streetAddress;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public Person getPerson() {
			return person;
		}

		public void setPerson(Person person) {
			this.person = person;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public void setStreetAddress(String streetAddress) {
			this.streetAddress = streetAddress;
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

		public int getZipCode() {
			return zipCode;
		}

		public void setZipCode(int zipCode) {
			this.zipCode = zipCode;
		}

		public Double getLatitude() {
			return latitude;
		}

		public void setLatitude(Double latitude) {
			this.latitude = latitude;
		}

		public Double getLongitude() {
			return longitude;
		}

		public void setLongitude(Double longitude) {
			this.longitude = longitude;
		}
	}
		
