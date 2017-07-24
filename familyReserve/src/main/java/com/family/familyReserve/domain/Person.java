package com.family.familyReserve.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.family.familyReserve.domain.View.Individual;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "person")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@JsonView(View.Individual.class)
	@NotEmpty(message = "First name is required.")
	@Size(min = 2)
	private String firstName;

	@JsonView(View.Individual.class)
	@NotNull(message = "Last name is required.")
	@Size(min = 2)
	private String lastName;

	@Column(unique = true)
	private String userName;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String encPassword;

	@Transient
	private String password;

	@JsonView(View.Individual.class)
	private String email;

	@OneToMany(mappedBy = "person")
	@JsonIgnore
	private List<Address> addresses;

	@OneToMany(mappedBy = "person")
	@JsonIgnore
	private List<PersonRelationship> relatives;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "person_family")
	private List<Family> families;

	// Constructors
	public Person() {
	}

	public Person(String firstName, String lastName, String userName, String password, String email,
			String encPassword) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.encPassword = encPassword;

	}

	public Person(String firstName, String lastName, String userName, String password, String email, String encPassword,
			String role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.encPassword = encPassword;
	}

	public Person(String firstName, String lastName, String email, Address address, Family family) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.addresses.add(address);
		this.families.add(family);
	}
	
	
	public Person(String username, String encryptedPassword) {
		this.userName = username;
		this.encPassword = encryptedPassword;
	}
	
	public List<Family> getFamilies() {
		return families;
	}

	public void setFamilies(List<Family> families) {
		this.families = families;
	}

	public String getEncPassword() {
		return encPassword;
	}

	public void setEncPassword(String encPassword) {
		this.encPassword = encPassword;
	}

	
	public String getUserName() {
		return userName;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public void addAddress(Address address) {
		this.addresses.add(address);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void addRelative(PersonRelationship relationship) {
		this.relatives.add(relationship);
	}

	public void addFamily(Family family) {
		this.families.add(family);
	}

	public List<PersonRelationship> getRelatives() {
		return relatives;
	}

	public void setRelatives(List<PersonRelationship> relatives) {
		this.relatives = relatives;
	}

	public void PasswordEncoderGenerator(String password) {
		password = "123456";
		int i = 0;
		while (i < 10) {

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(password);

			System.out.println(hashedPassword);
			i++;
		}

	}

}
