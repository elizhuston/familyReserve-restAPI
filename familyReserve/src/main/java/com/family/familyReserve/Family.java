package com.family.familyReserve;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "family")
public class Family implements Serializable {

//	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	@Column(unique = true)
	private String name;


	public Family() {
		// TODO Auto-generated constructor stub
	}
	public Family(String name) {
		this.name=name;
	}
	public long getId() {
		return id;
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
