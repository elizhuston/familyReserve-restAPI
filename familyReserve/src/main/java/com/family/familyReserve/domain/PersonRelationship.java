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

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "personRelationship")
public class PersonRelationship implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;

	@JsonView(View.Summary.class)
	@ManyToOne
	@JoinColumn(name = "personId")
	@NotNull(message = "person id is required.")
	private Person person;
	
	
	@ManyToOne
	@JoinColumn(name = "relativeId")
	@NotNull(message = "relative Id is required.")
	private Person relative;
	
	@JsonView(View.Summary.class)
	@ManyToOne
	@JoinColumn(name = "relationTypeId")
	@NotNull(message = "relation type Id is required.")
	private RelationType howRelated;
	
	@JsonView(View.Summary.class)
	@ManyToOne
	@JoinColumn(name = "familyId")
	@NotNull(message = "Family Id is required.")
	private Family family;
	
	
	//Constructors
	
	public PersonRelationship() {}
	
	public PersonRelationship(Person person, Person relative, RelationType howRelated,  Family family) {
		this.person=relative;
		this.relative=relative;
		this.howRelated=howRelated;
		this.family=family;
	}
	
	public long getId() {
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

	public Family getFamily() {
		return family;
	}

	public void setFamily(Family family) {
		this.family = family;
	}

	public Person getRelative() {
		return relative;
	}

	public void setRelative(Person relative) {
		this.relative = relative;
	}

	public RelationType getHowRelated() {
		return howRelated;
	}

	public void setHowRelated(RelationType howRelated) {
		this.howRelated = howRelated;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
