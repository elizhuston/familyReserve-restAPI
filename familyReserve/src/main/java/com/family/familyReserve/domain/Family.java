package com.family.familyReserve.domain;

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
import javax.persistence.Table;

import com.family.familyReserve.domain.View.Summary;
import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "family")
public class Family implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@JsonView(View.Summary.class)
	private int id;

	@Column(unique = true)
	@JsonView(View.Summary.class)
	private String name;
	

	@JsonView(View.Summary.class)
	private String photoSetId;
	
	@JsonView(View.SummaryWithPeople.class)
	@ManyToMany
	@JoinTable(name = "person_family",
	joinColumns =
	@JoinColumn(name = "familyId", referencedColumnName= "id"),
	inverseJoinColumns=
	@JoinColumn(name="personId", referencedColumnName ="id")
	)
	private List<Person> members;
	
	@JsonView(View.SummaryWithFamilyRoles.class)
	@OneToMany
	@JoinColumn(name = "familyId")
	private List<FamilyRole> roles;
	
	
	//Constructors
	public Family() {}
	
	public Family(String name) {
		this.name=name;
	}
	
	public Family(String name, FamilyRole role) {
		this.name=name;
	}
	
	public void addRole(Person person, FamilyRole role) {
		this.roles.add(role);
	}
	
	public long getId() {
		return id;
	}
	public List<Person> getMembers() {
		return members;
	}
	public void setMembers(List<Person> members) {
		this.members = members;
	}
	
	public void addMember(Person member) {
		this.members.add(member);
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<FamilyRole> getRoles() {
		return roles;
	}

	public void setRoles(List<FamilyRole> roles) {
		this.roles = roles;
	}


	public String getPhotoSetId() {
		return photoSetId;
	}

	public void setPhotoSetId(String photoSetId) {
		this.photoSetId = photoSetId;
	}

}
