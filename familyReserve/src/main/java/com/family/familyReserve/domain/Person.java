package com.family.familyReserve.domain;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.family.familyReserve.config.LocalDateTimeAttributeConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonProperty.Access;


@Entity
@Table(name = "person")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonView(View.Summary.class)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@ApiModelProperty(notes = "The auto generated id of the person")
	private int id;

	@JsonView(View.Summary.class)
	@Size(min = 2)
	@ApiModelProperty(notes = "Persons first name")
	private String firstName;

	@JsonView(View.Summary.class)
	@Size(min = 2)
	@ApiModelProperty(notes = "Persons last name")
	private String lastName;

	
	@JsonView(View.Summary.class)
	@ApiModelProperty(notes = "Persons phone number")
	private String phoneNumber;
	
	@JsonView(View.Summary.class)
	@Column(unique = true)
	@ApiModelProperty(notes = "userName used for login, must be unique")
	private String userName;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String encPassword;

	@Transient
	private String password;

	@JsonView(View.Summary.class)
	private String email;
	
	@Column
//	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@JsonFormat(pattern="yyyy-MM-dd")
	@JsonView(View.Summary.class)
	private Date birthDate;
	
	
	@JsonView(View.Summary.class)
	private String photoSetId;
	
	@OneToMany(mappedBy = "person")
	@JsonIgnore
	@JsonView(View.SummaryWithAddresses.class)
	private List<Address> addresses;


	@OneToMany(mappedBy = "person")
	private List<PersonRelationship> relatives;

	@JsonView(View.SummaryWithFamilies.class)
	@ManyToMany
	@JoinTable(name = "person_family", joinColumns = @JoinColumn(name = "personId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "familyId", referencedColumnName = "id"))
	private List<Family> families;

	// Constructors
	public Person() {
	}
	public Person(String firstName, String lastName, String userName, String password, String email,
			Date birthDate, String photoSetId, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.birthDate= birthDate;
		this.phoneNumber=phoneNumber;
		this.photoSetId=photoSetId;
		this.encPassword = PasswordEncoderGenerator(password);

	}
	public Person(String firstName, String lastName, String userName, String password, String email,
			Date birthDate, String encPassword) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.birthDate= birthDate;
		this.encPassword = PasswordEncoderGenerator(password);

	}

	public Person(String firstName, String lastName, String userName, String password, String email, String encPassword,
			String role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.encPassword = PasswordEncoderGenerator(password);
	}

	public Person(String firstName, String lastName, String email, Address address, Family family) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.addresses.add(address);
		this.families.add(family);
	}

	
	public Person(String username, String password) {
		this.userName = username;
		this.encPassword = PasswordEncoderGenerator(password);
	}

	// Methods
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
		this.encPassword = PasswordEncoderGenerator(password);
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

	public String PasswordEncoderGenerator(String password) {
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String hashedPassword = passwordEncoder.encode(password);
//
//		System.out.println(hashedPassword);
			final String SALT = "supercalifragistic";
			String hashedPassword = password + SALT;
			MessageDigest md = null;
			try {
				md = MessageDigest.getInstance("SHA");
			} catch (NoSuchAlgorithmException ex) {
				System.out.println(ex);
			}
			md.update(password.getBytes());
			String digest = new String(md.digest());
			hashedPassword=digest;
		
		return (hashedPassword);
	}

	public void merge(Person person) {
		if (person.firstName != null) {
		this.firstName = person.firstName;
		}
		if (person.lastName != null) {
		this.lastName = person.lastName;
		}
		if (person.email != null) {
		this.email = person.email;
		}
		if (person.birthDate != null) {
			this.birthDate = person.birthDate;
		}
		if (person.photoSetId != null) {
			this.photoSetId = person.photoSetId;
		}
		if (person.phoneNumber != null) {
			this.phoneNumber = person.phoneNumber;
		}

	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhotoSetId() {
		return photoSetId;
	}

	public void setPhotoSetId(String photoSetId) {
		this.photoSetId = photoSetId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
