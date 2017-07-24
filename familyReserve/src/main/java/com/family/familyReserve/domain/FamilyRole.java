package com.family.familyReserve.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;


@Entity
@Table(name = "familyRole")
public class FamilyRole {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	
	@JsonView(View.Summary.class)
	private String role;
	
	@JsonView(View.Summary.class)
	@ManyToOne
	@JoinColumn(name = "familyId")
	private Family family;
	
	@JsonView(View.Summary.class)
	@ManyToOne
	@JoinColumn(name = "personId")
	private Person person;
	
	//Constructors
	
	public FamilyRole() {}
	
	public FamilyRole( Person person, String role, Family family){
		this.person=person;
		this.role=role;
		this.family=family;
	}
		
	public FamilyRole(int id,String role, Person person, Family family){
		this.id=id;
		this.role=role;
		this.person=person;
		this.family=family;
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


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public Family getFamily() {
		return family;
	}


	public void setFamily(Family family) {
		this.family = family;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	

}
