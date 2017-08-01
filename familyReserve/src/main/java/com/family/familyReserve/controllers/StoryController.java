package com.family.familyReserve.controllers;

import java.util.List;

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
import com.family.familyReserve.domain.Story;
import com.family.familyReserve.domain.StoryRepository;
import com.family.familyReserve.domain.View;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@SpringBootApplication
@RestController
@Api(value = "Stories")
public class StoryController {
	@Autowired
	private StoryRepository storyRepository;
	
	
	@JsonView(View.Summary.class)
	@ApiOperation(value = "Add a new story", notes = "Adds a new story")
	@RequestMapping(path = "/api/story", method = RequestMethod.POST)
	public ResponseEntity<Story> createStory(@Validated @RequestBody Story newStory) {
		System.out.println("/api/story POST ");
		if (newStory.getStory() == null) {
			return new ResponseEntity<Story>(HttpStatus.BAD_REQUEST);
		}
		
		
		storyRepository.save(newStory);
		return new ResponseEntity<Story>(newStory, HttpStatus.CREATED);
	}

	@JsonView(View.Summary.class)
	@ApiOperation(value = "Find Stories", notes = "Returns an array of all Stories")
	@RequestMapping(path = "/api/story", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Story>> findAllStories() {
		System.out.println("/api/story GET ");
		Iterable<Story> stories = storyRepository.findAll();
		return new ResponseEntity<Iterable<Story>>(stories, HttpStatus.OK);

	}
	public StoryController() {
		// TODO Auto-generated constructor stub
	}

}
