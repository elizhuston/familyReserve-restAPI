package com.family.familyReserve.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table (name = "recipes")

public class Recipe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@JsonView(View.Summary.class)
	private int id;

	@ManyToOne
	@JoinColumn(name = "familyId")
	@JsonView(View.Summary.class)
	private Family family;
	
	@ManyToOne
	@JoinColumn(name = "personId")
	@JsonView(View.Summary.class)
	private Person postedBy;
	
	@NotEmpty
	@JsonView(View.Summary.class)
	private String title;
	@JsonView(View.Summary.class)
	private String ingredients;
	@JsonView(View.Summary.class)		
	private String directions;
	@JsonView(View.Summary.class)
	private String preps;
	@JsonView(View.Summary.class)
	private String cookTime;
	@JsonView(View.Summary.class)
	private int serves;
	
	// Constructors
	
	public Recipe() {
		
	}
	
	public Recipe(String title, String ingredients, String directions, String preps, String cookTime)  {
			this.title = title;
			this.ingredients = ingredients;
			this.directions = directions;
			this.preps = preps;
			this.cookTime = cookTime;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public String getPreps() {
		return preps;
	}

	public void setPreps(String preps) {
		this.preps = preps;
	}

	public String getCookTime() {
		return cookTime;
	}

	public void setCookTime(String cookTime) {
		this.cookTime = cookTime;
	}

	public int getServes() {
		return serves;
	}

	public void setServes(int serves) {
		this.serves = serves;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
