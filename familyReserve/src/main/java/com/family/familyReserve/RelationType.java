package com.family.familyReserve;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "relationType")
public class RelationType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	@Column(unique = true)
	private String description;

	// Constructors
	public RelationType() {
	}

	public RelationType(String description) {
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
