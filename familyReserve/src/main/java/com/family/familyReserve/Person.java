package com.family.familyReserve;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "person")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@JsonView(View.Individual.class)
	@NotNull(message = "First name is required.")
	@Size(min = 2)
	private String firstName;
	
	@JsonView(View.Individual.class)
	@NotNull(message = "Last name is required.")
	@Size(min = 2)
	private String lastName;

	@Column(unique = true)
	private String userName;

	private String password;
	
	@JsonView(View.Individual.class)
	private String email;
	
	@OneToMany(mappedBy = "person")
	private List<Address> addresses;
	
	@OneToMany(mappedBy = "person")
	@JsonIgnore
	private List<PersonRelationship> relatives;

	// Constructors
	public Person() {
	}

	public Person(String firstName, String lastName, String userName, String password, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}

	public Person(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	public String getUserName() {
		return userName;
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

	public List<PersonRelationship> getRelatives() {
		return relatives;
	}

	public void setRelatives(List<PersonRelationship> relatives) {
		this.relatives = relatives;
	}

}
