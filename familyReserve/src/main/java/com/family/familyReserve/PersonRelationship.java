package com.family.familyReserve;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "personRelationship")
public class PersonRelationship implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne(optional=false)
	@JoinColumn(name = "personId")
	private Person person1;
	
	@ManyToOne
	@JoinColumn(name = "relativeId",insertable=false, updatable=false)
	private Person person2;
	
	@ManyToOne
	@JoinColumn(name = "relationTypeId")
	private RelationType howRelated;
	
	//Constructors
	
	public PersonRelationship() {}
	
	public PersonRelationship(Person person1, Person person2, RelationType howRelated) {
		this.person1=person1;
		this.person2=person2;
		this.howRelated=howRelated;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Person getPerson1() {
		return person1;
	}

	public void setPerson1(Person person1) {
		this.person1 = person1;
	}

	public Person getPerson2() {
		return person2;
	}

	public void setPerson2(Person person2) {
		this.person2 = person2;
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
