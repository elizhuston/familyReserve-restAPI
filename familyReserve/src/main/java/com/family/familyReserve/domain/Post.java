package com.family.familyReserve.domain;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.family.familyReserve.config.LocalDateTimeAttributeConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "post")
public class Post {
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
	@JsonView(View.Summary.class)
	private String content;
	
	@Column
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@JsonFormat(pattern="yyyy-MM-dd")
	@JsonView(View.Summary.class)
	private LocalDateTime postDate;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(Person postedBy) {
		this.postedBy = postedBy;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Family getFamily() {
		return family;
	}

	public void setFamily(Family family) {
		this.family = family;
	}

	public Post() {
		// TODO Auto-generated constructor stub
	}

}
