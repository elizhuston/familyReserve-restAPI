package com.family.familyReserve;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	private Person person;
	
	private String streetAddress;

	private String city;

	private String state;

	private int zipCode;
	
	private Double latitude;
	
	private Double longitude;
	
	// Constructors
		public Address() {
		}
		
		public Address(String streetAddress, String city, String state, int zipCode, Double latitude, Double longitude ) {
			this.setStreetAddress(streetAddress);
			this.setCity(city);
			this.setState(state);
			this.setZipCode(zipCode);
			this.setLatitude(latitude);
			this.setLongitude(longitude);
			
		}
		public Address(String streetAddress, String city, String state, int zipCode) {
			this.setStreetAddress(streetAddress);
			this.setCity(city);
			this.setState(state);
			this.setZipCode(zipCode);	
		}
		public String getStreetAddress() {
			return streetAddress;
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
		
