package com.family.familyReserve.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.family.familyReserve.domain.Family;
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
@Api(value = "Post Family Content")
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
	
	@JsonView(View.Individual.class)
	@ApiOperation(value = "Get family posted content", notes = "Returns an array of posts for family")
	@RequestMapping(path = "/api/post/family/{familyId}", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> getFamilyContent(@PathVariable(name = "familyId", required = true) Integer id) {
		System.out.println("/api/post/family/{familyId} GET ");
		List<Post> familyContent = postRepository.findFamilyPosts(id);

		return new ResponseEntity<List<Post>>(familyContent, HttpStatus.CREATED);
	}
	
	public PostController() {
		// TODO Auto-generated constructor stub
	}

}
