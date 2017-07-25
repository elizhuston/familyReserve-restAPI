package com.family.familyReserve.domain;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	@JoinColumn(name = "personId")
	private Person postedBy;
	
	private String content;
	
//	@JsonFormat (shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
//	private LocalDate postDate;
	
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

//	public LocalDate getPostDate() {
//		return postDate;
//	}
//
//	public void setPostDate(LocalDate postDate) {
//		this.postDate = postDate;
//	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Post() {
		// TODO Auto-generated constructor stub
	}

}
