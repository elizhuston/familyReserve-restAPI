package com.family.familyReserve;

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

import io.swagger.annotations.ApiOperation;

@SpringBootApplication
@RestController
public class frController {

	@Autowired
	private FamilyRepository familyRepository;
	
	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private RelationTypeRepository relationTypeRepository;

	
	@Autowired
	private PersonRelationshipRepo personRelationshipRepo;
	
	// adds a new user 
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
	
	// returns an array of all Person objects
	@RequestMapping(path = "/api/people", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> findAllPeople() {
		List<Person> people = personRepository.findAllPeople();
		return new ResponseEntity<List<Person>>(people, HttpStatus.OK);

	}
	
	// return relatives for a given person id
	@RequestMapping(path = "/api/relatives/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> findRelativesForPerson(@PathVariable(name = "id", required = true) Integer id) {
		System.out.println("/api/relatives/{id} GET " + id);
	
		List<Person> people = personRelationshipRepo.findRelativesForPerson(id);
		return new ResponseEntity<List<Person>>(people, HttpStatus.OK);

	}
	
	// return members for a given family id
		@RequestMapping(path = "/api/family/members/{id}", method = RequestMethod.GET)
		public ResponseEntity<List<Person>> findFamilyMembers(@PathVariable(name = "id", required = true) Integer id) {
			System.out.println("/family/members/{id GET " + id);
		
			List<Person> people = familyRepository.findFamilyMembers(id);
			return new ResponseEntity<List<Person>>(people, HttpStatus.OK);

		}
	
	// adds a new family
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

	// adds a new RelationType
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
		
		// adds a new PersonRelationship
				@RequestMapping(path = "/api/relationship", method = RequestMethod.POST)
				public ResponseEntity<PersonRelationship> createPersonRelationship(@Validated @RequestBody PersonRelationship r) {
					System.out.println("/api/relationship POST ");
					if (r.getPerson()== null){
						return new ResponseEntity<PersonRelationship>(HttpStatus.BAD_REQUEST);
					}
					if (r.getRelative()== null){
						return new ResponseEntity<PersonRelationship>(HttpStatus.BAD_REQUEST);
					}
			
//					if (personRelationshipRepo.findRelativeByName(r.getPerson().getFirstName()) != null) {
//						return new ResponseEntity<PersonRelationship>(HttpStatus.BAD_REQUEST);
//					}

					personRelationshipRepo.save(r);
					return new ResponseEntity<PersonRelationship>(r, HttpStatus.CREATED);
				}
				
	public frController() {

	}

}
