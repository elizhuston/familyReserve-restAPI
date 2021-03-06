package com.family.familyReserve.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table (name = "address")
public class Address implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonView(View.Summary.class)
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	
	@JsonView(View.Summary.class)
	@ManyToOne
	@JoinColumn(name = "personId")
	@NotNull(message = "Person required")
	private Person person;
	
	@JsonView(View.SummaryWithAddresses.class)
	@NotNull(message = "Street Address is required.")
	@Size(min = 5)
	private String streetAddress;
	
	@JsonView(View.SummaryWithAddresses.class)
	@NotNull(message = "City is required.")
	@Size(min = 2)
	private String city;
	
	@JsonView(View.SummaryWithAddresses.class)
	@NotNull(message = "State is required.")
	@Size(min = 2)
	private String state;
	
	@JsonView(View.SummaryWithAddresses.class)
	private String zipCode;
	
	@JsonView(View.SummaryWithAddresses.class)
	private Double latitude;
	
	@JsonView(View.SummaryWithAddresses.class)
	private Double longitude;
	
	// Constructors
		public Address() {
		}
		
		public Address(Person person, String streetAddress, String city, String state, String zipCode, Double latitude, Double longitude ) {
			this.person= person;
			this.streetAddress=streetAddress;
			this.city=city;
			this.state=state;
			this.zipCode=zipCode;
			this.latitude=latitude;
			this.longitude=longitude;
		}
		
		public Address(Person person, String streetAddress, String city, String state, String zipCode) {
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

		public String getZipCode() {
			return zipCode;
		}

		public void setZipCode(String zipCode) {
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
  
		public void merge(Address r) {
			// TODO Auto-generated method stub
			if (r.streetAddress != null) {
				this.streetAddress = r.streetAddress;
			}
			if (r.city != null)  {
				this.city = r.city;
			}
			if (r.state != null)  {
				this.state = r.state;
			}
			if (r.zipCode != null) {
				this.zipCode = r.zipCode;
			}
//			if (r.latitude !=0) {
//				this.latitude = r.latitude;
//			}
//			if (r.longitude !=0) {
//				this.longitude = r.longitude;
//			}
		}}

		
