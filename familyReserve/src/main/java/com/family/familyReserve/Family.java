package com.family.familyReserve;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "family")
public class Family implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Column(unique = true)
	private String name;

	@OneToMany
	@JoinColumn(name = "familyId")
	private List<PersonRelationship> members;
	
	public Family() {
		// TODO Auto-generated constructor stub
	}
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
//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}
	

}
