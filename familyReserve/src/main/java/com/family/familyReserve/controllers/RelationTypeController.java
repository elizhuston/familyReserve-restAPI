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

import com.family.familyReserve.domain.RelationType;
import com.family.familyReserve.domain.RelationTypeRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@SpringBootApplication
@RestController
@Api(value = "Relation Type")
public class RelationTypeController {

	@Autowired
	private RelationTypeRepository relationTypeRepository;
	
	@ApiOperation(value = "Add new Relation Type", notes = "Creates relation type and returns id")
	@RequestMapping(path = "/api/relationType", method = RequestMethod.POST)
	public ResponseEntity<RelationType> createRelationType(@Validated @RequestBody RelationType t) {
		System.out.println("/api/relationType POST ");
		if (t.getDescription() == null) {
			return new ResponseEntity<RelationType>(HttpStatus.BAD_REQUEST);
		}
		if (relationTypeRepository.findRelationTypeByDesc(t.getDescription()) != null) {
			return new ResponseEntity<RelationType>(HttpStatus.BAD_REQUEST);
		}

		relationTypeRepository.save(t);
		return new ResponseEntity<RelationType>(t, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Find Relation Types", notes = "Returns and array of all Relation Types")
	@RequestMapping(path = "/api/relationType", method = RequestMethod.GET)
	public ResponseEntity<List<RelationType>> findAllRelationTypes() {
		List<RelationType> types = relationTypeRepository.findAllRelationTypes();
		return new ResponseEntity<List<RelationType>>(types, HttpStatus.OK);

	}
	
	public RelationTypeController() {
		// TODO Auto-generated constructor stub
	}

}
