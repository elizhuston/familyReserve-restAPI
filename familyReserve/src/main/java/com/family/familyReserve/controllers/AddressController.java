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

import com.family.familyReserve.domain.Address;
import com.family.familyReserve.domain.AddressRepository;

import com.family.familyReserve.domain.View;
import com.fasterxml.jackson.annotation.JsonView;

import com.family.familyReserve.domain.ConsumeResults;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@SpringBootApplication
@RestController
@Api(value = "Address")
public class AddressController {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@JsonView(View.SummaryWithAddresses.class)
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
		
//		r.setLatitude(38.8950017);
//		r.setLongitude(-77.0291484);
		//populate address object with associated lng and lat
		Address adr = new ConsumeResults().getLngLatFromGoogle(r);				
		
		System.out.println(fullAddress);
		addressRepository.save(adr);
		return new ResponseEntity<Address>(adr, HttpStatus.CREATED);
	}
	
	@JsonView(View.SummaryWithAddresses.class)
	@ApiOperation(value = "Get address by Address id", notes = "Returns address for given address id")
	@RequestMapping(path = "/api/address/{id}", method = RequestMethod.GET)
	public ResponseEntity<Address> findOne(@PathVariable(name = "id", required = true) Integer id) {
		System.out.println("/api/address/{id}/ GET " + id);
	
		Address adr = addressRepository.findOne(id);
		return new ResponseEntity<Address>(adr, HttpStatus.OK);

	}
	
// ========= Returns person address matching ID in the database
	
	@JsonView(View.SummaryWithAddresses.class)
	@ApiOperation(value = "Get person addresses", notes = "Returns addresses for given person id")
	@RequestMapping(path = "/api/address/person/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Address>> findPersonAddress(@PathVariable(name = "id", required = true) Integer id) {
		System.out.println("/api/address/person/{id} GET " + id);
	
		List<Address> residences = addressRepository.findPersonAddress(id);
		return new ResponseEntity<List<Address>>(residences, HttpStatus.OK);

	}
//================== Updates Existing Person Address ============ 	
	@JsonView(View.SummaryWithAddresses.class)
	@ApiOperation(value = "Updates person address", notes = "Updates a person's address with given addressId")	
	@RequestMapping(path = "/api/address/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Address> updateAddress(@RequestBody Address r)  {
			//@PathVariable(name = "id", required = true) Integer id) {
		System.out.println("/api/address/{id} PUT id is" + r.getId() );

		if (r.getId()==0){
			return new ResponseEntity<Address>(r, HttpStatus.BAD_REQUEST);
		}
		
		Address existing = addressRepository.findOne(r.getId());
		if (existing == null) {
			return new ResponseEntity<Address>(r,HttpStatus.BAD_REQUEST); 
		}
		
		existing.merge(r);
		
	String fullAddress = existing.getStreetAddress() + " "+ existing.getCity() 
	+ " , " + existing.getState() + " , " + existing.getZipCode(); 
	
	Address adr = new ConsumeResults().getLngLatFromGoogle(existing);	
		
	System.out.println(fullAddress);
	existing.setLatitude(adr.getLatitude());
	existing.setLongitude(adr.getLongitude());
	
	addressRepository.save(adr);
	return new ResponseEntity<Address>(adr, HttpStatus.OK);

	
}	
//================ Deletes existing Person Address =====================
	
	@ApiOperation(value = "Deletes person's address with given addressId", notes = "Deletes an address from database for addressId")	
	@RequestMapping(path = "/api/address/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteAddress(@PathVariable(name = "id", required = true) Integer id) {
		System.out.println("/api/address/{id} DELETE " + id);
				
	  addressRepository.delete(id);
	  
	return new ResponseEntity<String>("Address deleted", HttpStatus.OK);
}
	
	public AddressController() {
		// TODO Auto-generated constructor stub
	}

}
