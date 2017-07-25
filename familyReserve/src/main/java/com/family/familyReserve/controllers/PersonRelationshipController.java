package com.family.familyReserve.controllers;

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
import com.family.familyReserve.domain.FamilyRepository;
import com.family.familyReserve.domain.Person;
import com.family.familyReserve.domain.PersonRelationship;
import com.family.familyReserve.domain.PersonRelationshipRepo;
import com.family.familyReserve.domain.PersonRepository;
import com.family.familyReserve.domain.RelationType;
import com.family.familyReserve.domain.RelationTypeRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@SpringBootApplication
@RestController
@Api(value = "Person Relationship")
public class PersonRelationshipController {

	@Autowired
	private PersonRelationshipRepo personRelationshipRepo;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private FamilyRepository familyRepository;
	
	@Autowired
	private RelationTypeRepository relationTypeRepository;
	
	@RequestMapping(path = "/api/person/{personId}/family/{familyId}/addRelative/{relativeId}/howRelated/{relationTypeId}", method = RequestMethod.PUT)
	@ApiOperation(value = "Add family relationship between two people", notes = "Add a relative to a person for family" + " request\n")
	public ResponseEntity<Void> addPersonRelative(
			@PathVariable (name="personId", required=true) Integer personId,
			@PathVariable(name="familyId", required = true) Integer familyId,
			@PathVariable(name="relativeId", required = true) Integer relativeId,
			@PathVariable(name="relationTypeId", required = true) Integer relationTypeId)
			
	{
		System.out.println("/api/person/{personId}/addRelative/{relativeId}/howRelated/{relationTypeId} ");
		Person person =personRepository.findPersonById(personId);
		System.out.println("person id is " + person.getId());
		Family family = familyRepository.findOne(familyId);
		Person relative= personRepository.findPersonById(relativeId);
		System.out.println("relative id is " + relative.getId());
		RelationType rt = relationTypeRepository.findOne(relationTypeId);
		if (person == null || relative == null || family ==null || rt ==null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		PersonRelationship relationship = new PersonRelationship();
		relationship.setPerson(person);
		relationship.setRelative(relative);
		relationship.setHowRelated(rt);
		relationship.setFamily(family);
		personRelationshipRepo.saveAndFlush(relationship);
		
		PersonRelationship inverseRelation = new PersonRelationship();
		inverseRelation.setPerson(relative);
		inverseRelation.setRelative(person);
		inverseRelation.setHowRelated(rt);
		inverseRelation.setFamily(family);
		personRelationshipRepo.saveAndFlush(inverseRelation);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
//	@ApiOperation(value = "Add new Relationship between two people in a family", notes = "Creates family relationship and returns id")
//	@RequestMapping(path = "/api/relationship", method = RequestMethod.POST)
//	public ResponseEntity<PersonRelationship> createPersonRelationship(@Validated @RequestBody PersonRelationship r) {
//		System.out.println("/api/relationship POST ");
//		if (r.getPerson()== null){
//			return new ResponseEntity<PersonRelationship>(HttpStatus.BAD_REQUEST);
//		}
//		if (r.getRelative()== null){
//			return new ResponseEntity<PersonRelationship>(HttpStatus.BAD_REQUEST);
//		}

//		if (personRelationshipRepo.findRelativeByName(r.getPerson().getFirstName()) != null) {
//			return new ResponseEntity<PersonRelationship>(HttpStatus.BAD_REQUEST);
//		}

//		personRelationshipRepo.save(r);
//		return new ResponseEntity<PersonRelationship>(r, HttpStatus.CREATED);
//	}
//	
	
	public PersonRelationshipController() {
		// TODO Auto-generated constructor stub
	}

}
