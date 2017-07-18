package com.family.familyReserve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
					if (r.getPerson1()== null){
						return new ResponseEntity<PersonRelationship>(HttpStatus.BAD_REQUEST);
					}
					if (r.getPerson2()== null){
						return new ResponseEntity<PersonRelationship>(HttpStatus.BAD_REQUEST);
					}
			
					/*if (personRelationshipRepo.findRelativeByName(r.getPerson1().getFirstName()) != null) {
						return new ResponseEntity<PersonRelationship>(HttpStatus.BAD_REQUEST);
					}*/

					personRelationshipRepo.save(r);
					return new ResponseEntity<PersonRelationship>(r, HttpStatus.CREATED);
				}
				
	public frController() {

	}

}
