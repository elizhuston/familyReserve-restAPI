package com.family.familyReserve.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.family.familyReserve.config.LocalDateTimeAttributeConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "story")
public class Story {
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
	
	@JsonView(View.SummaryWithPeople.class)
	@ManyToMany
	@JoinTable(name = "person_story", joinColumns = @JoinColumn(name = "personId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "storyId", referencedColumnName = "id"))
	private List<Person> people;
	
	@JsonView(View.Summary.class)
	private String story;
	
	@Column
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@JsonFormat(pattern="yyyy-MM-dd")
	@JsonView(View.Summary.class)
	private LocalDateTime postDate;
	
	
	public Story() {
		// TODO Auto-generated constructor stub
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
	public Person getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(Person postedBy) {
		this.postedBy = postedBy;
	}
	
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	public LocalDateTime getPostDate() {
		return postDate;
	}
	public void setPostDate(LocalDateTime postDate) {
		this.postDate = postDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<Person> getPeople() {
		return people;
	}
	
	public void setPeople(List<Person> people) {
		this.people = people;
	}

	public void addPeople(Person person) {
		this.people.add(person);
	}
	
	public void removePeople(Person person) {
		this.people.remove(person);
	}
	
}
