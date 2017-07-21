package com.family.familyReserve;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "family")
public class Family implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@JsonView(View.Summary.class)
	private int id;

	@Column(unique = true)
	@JsonView(View.Summary.class)
	private String name;

	@OneToMany
	@JoinColumn(name = "familyId")
	private List<PersonRelationship> members;
	
	public Family() {}
	
	public Family(String name) {
		this.name=name;
	}
	public long getId() {
		return id;
	}
	public List<PersonRelationship> getMembers() {
		return members;
	}
	public void setMembers(List<PersonRelationship> members) {
		this.members = members;
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

	

}
