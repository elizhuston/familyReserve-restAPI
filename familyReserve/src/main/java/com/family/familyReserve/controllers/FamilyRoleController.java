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

import com.family.familyReserve.domain.FamilyRole;
import com.family.familyReserve.domain.FamilyRoleRepository;
import com.family.familyReserve.domain.Person;
import com.family.familyReserve.domain.PersonRelationship;
import com.family.familyReserve.domain.PersonRelationshipRepo;
import com.family.familyReserve.domain.View;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@SpringBootApplication
@RestController
@Api(value = "Family Role")
public class FamilyRoleController {
	@Autowired
	private FamilyRoleRepository familyRoleRepository;
	
	@JsonView(View.Summary.class)
	@ApiOperation(value = "Add Admin role for family member", notes = "Add Admin role for person in Family")
	@RequestMapping(path = "/api/family/Admin", method = RequestMethod.POST)
	public ResponseEntity<Void> addFamilyAdmin(@Validated @RequestBody FamilyRole f) {	
	System.out.println("/api/family/Admin POST ");
		if (f.getPerson()== null){
			System.out.println("f.getPerson is null ");
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		if (f.getFamily()== null){
			System.out.println("f.getFamily is null ");
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

        f.setRole("ADMIN");
		familyRoleRepository.save(f);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@JsonView(View.Summary.class)
	@ApiOperation(value = "Find Family Admins", notes = "Returns Admins for a given family id")
	@RequestMapping(path = "/api/family/{id}/admins", method = RequestMethod.GET)
	public ResponseEntity<List<FamilyRole>> findAdminsForFamily(@PathVariable(name = "id", required = true) Integer id) {
		System.out.println("/api/family/{id}/admins GET " + id);
	
		List<FamilyRole> roles = familyRoleRepository.findAdminsForFamily(id);
		return new ResponseEntity<List<FamilyRole>>(roles, HttpStatus.OK);

	}
	
	public FamilyRoleController() {
		// TODO Auto-generated constructor stub
	}

}
