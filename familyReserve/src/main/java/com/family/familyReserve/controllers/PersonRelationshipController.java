package com.family.familyReserve.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.family.familyReserve.domain.PersonRelationship;
import com.family.familyReserve.domain.PersonRelationshipRepo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@SpringBootApplication
@RestController
@Api(value = "Person Relationship")
public class PersonRelationshipController {

	@Autowired
	private PersonRelationshipRepo personRelationshipRepo;
	
	@ApiOperation(value = "Add new Relationship between two people in a family", notes = "Creates family relationship and returns id")
	@RequestMapping(path = "/api/relationship", method = RequestMethod.POST)
	public ResponseEntity<PersonRelationship> createPersonRelationship(@Validated @RequestBody PersonRelationship r) {
		System.out.println("/api/relationship POST ");
		if (r.getPerson()== null){
			return new ResponseEntity<PersonRelationship>(HttpStatus.BAD_REQUEST);
		}
		if (r.getRelative()== null){
			return new ResponseEntity<PersonRelationship>(HttpStatus.BAD_REQUEST);
		}

//		if (personRelationshipRepo.findRelativeByName(r.getPerson().getFirstName()) != null) {
//			return new ResponseEntity<PersonRelationship>(HttpStatus.BAD_REQUEST);
//		}

		personRelationshipRepo.save(r);
		return new ResponseEntity<PersonRelationship>(r, HttpStatus.CREATED);
	}
	
	
	public PersonRelationshipController() {
		// TODO Auto-generated constructor stub
	}

}
