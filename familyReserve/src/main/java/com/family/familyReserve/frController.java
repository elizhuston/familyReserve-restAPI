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

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@SpringBootApplication
@RestController
@Api(value = "Family ")

public class frController {

	@Autowired
	private FamilyRepository familyRepository;
	
	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private RelationTypeRepository relationTypeRepository;

	@Autowired
	private AddressRepository addressRepository;
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
	
	@ApiOperation(value = "Find People", notes = "Returns and array of all People")
	@RequestMapping(path = "/api/people", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> findAllPeople() {
		List<Person> people = personRepository.findAllPeople();
		return new ResponseEntity<List<Person>>(people, HttpStatus.OK);

	}
	
	
	@ApiOperation(value = "Find Relation Types", notes = "Returns and array of all Relation Types")
	@RequestMapping(path = "/api/relationType", method = RequestMethod.GET)
	public ResponseEntity<List<RelationType>> findAllRelationTypes() {
		List<RelationType> types = relationTypeRepository.findAllRelationTypes();
		return new ResponseEntity<List<RelationType>>(types, HttpStatus.OK);

	}
	

	@JsonView(View.Summary.class)
		@ApiOperation(value = "Find Families", notes = "Returns and array of all Families")
		@RequestMapping(path = "/api/family", method = RequestMethod.GET)
		public ResponseEntity<List<Family>> findAllFamilies() {
			System.out.println("/api/family GET ");
			List<Family> families = familyRepository.findAllFamilies();
			return new ResponseEntity<List<Family>>(families, HttpStatus.OK);

		}
		
		
	@ApiOperation(value = "Find Relatives", notes = "Returns relatives for a given person id")
	@RequestMapping(path = "/api/relatives/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> findRelativesForPerson(@PathVariable(name = "id", required = true) Integer id) {
		System.out.println("/api/relatives/{id} GET " + id);
	
		List<Person> people = personRelationshipRepo.findRelativesForPerson(id);
		return new ResponseEntity<List<Person>>(people, HttpStatus.OK);

	}
	

	@ApiOperation(value = "Find family members", notes = "Returns family members for given family id")
		@RequestMapping(path = "/api/family/members/{id}", method = RequestMethod.GET)
		public ResponseEntity<List<Person>> findFamilyMembers(@PathVariable(name = "id", required = true) Integer id) {
			System.out.println("/family/members/{id GET " + id);
		
			List<Person> people = familyRepository.findFamilyMembers(id);
			return new ResponseEntity<List<Person>>(people, HttpStatus.OK);

		}
	
	@ApiOperation(value = "Get person addresses", notes = "Returns addresses for given person id")
	@RequestMapping(path = "/api/address/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Address>> findPersonAddress(@PathVariable(name = "id", required = true) Integer id) {
		System.out.println("/api/address/{id} GET " + id);
	
		List<Address> residences = addressRepository.findPersonAddress(id);
		return new ResponseEntity<List<Address>>(residences, HttpStatus.OK);

	}
	
	@ApiOperation(value = "Get person by id", notes = "Returns person object for given id")
	@RequestMapping(path = "/api/person/{id}", method = RequestMethod.GET)
	public ResponseEntity<Person> findPersonById(@PathVariable(name = "id", required = true) Integer id) {
		System.out.println("/api/person/{id} GET " + id);
	
		Person person = personRepository.findPersonById(id);
		return new ResponseEntity<Person>(person, HttpStatus.OK);

	}
	
	
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
		

		@ApiOperation(value = "Add new Relationship between two people in a famil", notes = "Creates family relationship and returns id")
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
				
				
				@ApiOperation(value = "Add address for person", notes = "Adds address for person")
				@RequestMapping(path = "/api/address", method = RequestMethod.POST)
				public ResponseEntity<Address> createPersonAddress(@Validated @RequestBody Address r) {
					System.out.println("/api/address POST ");
					String fullAddress = r.getStreetAddress() + " "+ r.getCity() 
					+ " , " + r.getState() + " , " + r.getZipCode(); 
					
					/*
					 * 1. Make a call to Google API for lng, lat for above address
					 * 2. Populate r object with longitude and latitude
					 *
					 */
					
//					r.setLatitude(38.8950017);
//					r.setLongitude(-77.0291484);
					//populate address object with associated lng and lat
					Address adr = new ConsumeResults().getLngLatFromGoogle(r);				
					
					System.out.println(fullAddress);
					addressRepository.save(adr);
					return new ResponseEntity<Address>(adr, HttpStatus.CREATED);
				}
	public frController() {

	}

}
