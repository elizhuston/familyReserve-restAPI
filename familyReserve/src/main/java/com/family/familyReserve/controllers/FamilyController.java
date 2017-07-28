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

import com.family.familyReserve.domain.Family;
import com.family.familyReserve.domain.FamilyRepository;
import com.family.familyReserve.domain.Person;
import com.family.familyReserve.domain.PersonRepository;
import com.family.familyReserve.domain.View;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@SpringBootApplication
@RestController
@Api(value = "Family ")
public class FamilyController {
	
	@Autowired
	private FamilyRepository familyRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@JsonView(View.Summary.class)
	@ApiOperation(value = "Create new family", notes = "Creates family and returns id")
	@RequestMapping(path = "/api/family", method = RequestMethod.POST)
	public ResponseEntity<Family> createFamily(@Validated @RequestBody Family f) {
		System.out.println("/api/family POST ");
		if (f.getName() == null) {
			return new ResponseEntity<Family>(HttpStatus.BAD_REQUEST);
		}
		if (familyRepository.findFamilyByName(f.getName()) != null) {
			return new ResponseEntity<Family>(HttpStatus.BAD_REQUEST);
		}

		familyRepository.save(f);
		return new ResponseEntity<Family>(f, HttpStatus.CREATED);
	}
	
	@JsonView(View.SummaryWithPeople.class)
	@RequestMapping(path = "/api/family/{familyId}/addMember/{personId}", method = RequestMethod.PUT)
	@ApiOperation(value = "Add member to family", notes = "Add a person to a family" + " request\n")
	public ResponseEntity<Void> addFamilyMember(@PathVariable (name="familyId", required=true) Integer familyId, @PathVariable(name="personId", required = true) Integer personId) {
		System.out.println("/api/family/{familyId}/addMember/{personId} ");
		Person p =personRepository.findPersonById(personId);
		Family f= familyRepository.findOne(familyId);
		if (p == null || f == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		f.addMember(p);
		familyRepository.save(f);
      	
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@JsonView(View.SummaryWithPeople.class)
	@RequestMapping(path = "/api/family/{familyId}/removeMember/{personId}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Remove member from family", notes = "Remove a person from a family" + " request\n")
	public ResponseEntity<Void> removeFamilyMember(@PathVariable (name="familyId", required=true) Integer familyId, @PathVariable(name="personId", required = true) Integer personId) {
		System.out.println("/api/family/{familyId}/removeMember/{personId} ");
		Person p =personRepository.findPersonById(personId);
		Family f= familyRepository.findOne(familyId);
		if (p == null || f == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		f.removeMember(p);
		familyRepository.save(f);
      	
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
// photoset id is putting the string "{photoSetId}" into the field, so commenting out
//	@RequestMapping(path = "/api/family/{familyId}/addPhotoSetId/{photoSetId}", method = RequestMethod.PUT)
//	@ApiOperation(value = "Add photoSetId to family", notes = "Add a photoSetId to a family" + " request\n")
//	public ResponseEntity<Void> updateFamilyPhotSetId(
//			@PathVariable(name="familyId", required=true) Integer familyId,
//			@PathVariable(name="photoSetId", required = true) String photoSetId) {
//
//		System.out.println("/api/family/{familyId}/addPhotoSetId/{photoSetId}" + photoSetId);
//
//		Family f= familyRepository.findOne(familyId);
//		if (f == null ) {
//			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
//		}
//		f.setPhotoSetId(photoSetId);
//		familyRepository.save(f);
//      	
//		return new ResponseEntity<Void>(HttpStatus.OK);
//	}
	
	
	@JsonView(View.Summary.class)
	@ApiOperation(value = "Find Families", notes = "Returns and array of all Families")
	@RequestMapping(path = "/api/family", method = RequestMethod.GET)
	public ResponseEntity<List<Family>> findAllFamilies() {
		System.out.println("/api/family GET ");
		List<Family> families = familyRepository.findAllFamilies();
		return new ResponseEntity<List<Family>>(families, HttpStatus.OK);

	}
	
	
	@JsonView(View.Summary.class)
	@ApiOperation(value = "Find Families a Person May Join", notes = "Returns and array of all Families the person is not a member of.")
	@RequestMapping(path = "/api/familiesJoinable/{personId}", method = RequestMethod.GET)
	public ResponseEntity<List<Family>> findFamiliesJoinable(@PathVariable(name = "personId", required = true) Integer personId) {
		System.out.println("/api/familiesJoinable/{personId} GET ");
		List<Family> families = familyRepository.findFamiliesJoinable(personId);
		return new ResponseEntity<List<Family>>(families, HttpStatus.OK);

	}
	
	@JsonView(View.SummaryWithPeople.class)
	@ApiOperation(value = "Find family members", notes = "Returns family members for given family id")
	@RequestMapping(path = "/api/family/{id}/members", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> findFamilyMembers(@PathVariable(name = "id", required = true) Integer id) {
		System.out.println("/family/members/{id GET " + id);
	
		List<Person> people = familyRepository.findFamilyMembers(id);
		System.out.println("Size of people list is " + people.size());
		return new ResponseEntity<List<Person>>(people, HttpStatus.OK);

	}
	

	
	@JsonView(View.Summary.class)
	@ApiOperation(value = "Get family id", notes = "Returns family object for given id")
	@RequestMapping(path = "/api/family/{id}", method = RequestMethod.GET)
	public ResponseEntity<Family> find(@PathVariable(name = "id", required = true) Integer id) {
		System.out.println("/api/family/{id} GET " + id);
	
		Family family = familyRepository.findOne(id);
		return new ResponseEntity<Family>(family, HttpStatus.OK);
	}
	
	public FamilyController() {
		// TODO Auto-generated constructor stub
	}

}
