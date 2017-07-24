package com.family.familyReserve.controllers;

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

import com.family.familyReserve.domain.Person;
import com.family.familyReserve.domain.PersonRelationshipRepo;
import com.family.familyReserve.domain.PersonRepository;
import com.family.familyReserve.domain.View;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@SpringBootApplication
@RestController
@Api(value = "Person")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private PersonRelationshipRepo personRelationshipRepo;
	
	@ApiOperation(value = "Add a new user", notes = "Adds a new user and returns the object containing the id")
	@RequestMapping(path = "/api/user", method = RequestMethod.POST)
	public ResponseEntity<Person> createAppUser(@Validated @RequestBody Person p) {
		System.out.println("/api/user POST ");
		if (p.getUserName() == null) {
			return new ResponseEntity<Person>(HttpStatus.BAD_REQUEST);
		}
		if (personRepository.findPersonByUserName(p.getUserName()) != null) {
			return new ResponseEntity<Person>(HttpStatus.BAD_REQUEST);
		}
        
		personRepository.save(p);
		return new ResponseEntity<Person>(p, HttpStatus.CREATED);
	}
	
	
	@ApiOperation(value = "Add a new person", notes = "Adds a new person and returns the object containing the id")
	@JsonView(View.Individual.class)
	@RequestMapping(path = "/api/person", method = RequestMethod.POST)
	public ResponseEntity<Person> createPerson(@Validated @RequestBody Person newPerson) {
		System.out.println("/api/person POST ");
		if (newPerson.getFirstName() == null) {
			return new ResponseEntity<Person>(HttpStatus.BAD_REQUEST);
		}
		
		personRepository.save(newPerson);
		return new ResponseEntity<Person>(newPerson, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Get person by id", notes = "Returns person object for given id")
	@RequestMapping(path = "/api/person/{id}", method = RequestMethod.GET)
	public ResponseEntity<Person> findPersonById(@PathVariable(name = "id", required = true) Integer id) {
		System.out.println("/api/person/{id} GET " + id);
	
		Person person = personRepository.findPersonById(id);
		return new ResponseEntity<Person>(person, HttpStatus.OK);

	}
	
	@ApiOperation(value = "Find People", notes = "Returns and array of all People")
	@RequestMapping(path = "/api/people", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> findAllPeople() {
		List<Person> people = personRepository.findAllPeople();
		return new ResponseEntity<List<Person>>(people, HttpStatus.OK);

	}
	
	
	@ApiOperation(value = "Find Relatives", notes = "Returns relatives for a given person id")
	@RequestMapping(path = "/api/person/{id}/relatives", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> findRelativesForPerson(@PathVariable(name = "id", required = true) Integer id) {
		System.out.println("/api/person/{id}/relatives GET " + id);
	
		List<Person> people = personRelationshipRepo.findRelativesForPerson(id);
		return new ResponseEntity<List<Person>>(people, HttpStatus.OK);

	}
	
	
	public PersonController() {
		// TODO Auto-generated constructor stub
	}

}