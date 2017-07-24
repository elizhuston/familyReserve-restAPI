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
	
	@JsonView(View.Summary.class)
	@ApiOperation(value = "Find Families", notes = "Returns and array of all Families")
	@RequestMapping(path = "/api/family", method = RequestMethod.GET)
	public ResponseEntity<List<Family>> findAllFamilies() {
		System.out.println("/api/family GET ");
		List<Family> families = familyRepository.findAllFamilies();
		return new ResponseEntity<List<Family>>(families, HttpStatus.OK);

	}
	
	@ApiOperation(value = "Find family members", notes = "Returns family members for given family id")
	@RequestMapping(path = "/api/family/{id}/members", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> findFamilyMembers(@PathVariable(name = "id", required = true) Integer id) {
		System.out.println("/family/members/{id GET " + id);
	
		List<Person> people = familyRepository.findFamilyMembers(id);
		return new ResponseEntity<List<Person>>(people, HttpStatus.OK);

	}
	
	
	
	public FamilyController() {
		// TODO Auto-generated constructor stub
	}

}
