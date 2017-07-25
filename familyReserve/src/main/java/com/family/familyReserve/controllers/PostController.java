package com.family.familyReserve.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.family.familyReserve.domain.Person;
import com.family.familyReserve.domain.PersonRepository;
import com.family.familyReserve.domain.Post;
import com.family.familyReserve.domain.PostRepository;
import com.family.familyReserve.domain.View;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@SpringBootApplication
@RestController
@Api(value = "Post")
public class PostController {
	@Autowired
	private PostRepository postRepository;
	
	@ApiOperation(value = "Post family content", notes = "Post content for family")
	@RequestMapping(path = "/api/post", method = RequestMethod.POST)
	public ResponseEntity<Post> postContent(@Validated @RequestBody Post post) {
		System.out.println("/api/person POST ");
		if (post.getPostedBy() == null) {
			return new ResponseEntity<Post>(HttpStatus.BAD_REQUEST);
		}
		//post.setPostDate(LocalDate.now());
		postRepository.save(post);
		return new ResponseEntity<Post>(post, HttpStatus.CREATED);
	}
	
	public PostController() {
		// TODO Auto-generated constructor stub
	}

}
