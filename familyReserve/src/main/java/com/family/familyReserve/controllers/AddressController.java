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
	
	@JsonView(View.Individual.class)
	@ApiOperation(value = "Get person addresses", notes = "Returns addresses for given person id")
	@RequestMapping(path = "/api/address/person/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Address>> findPersonAddress(@PathVariable(name = "id", required = true) Integer id) {
		System.out.println("/api/address/{id}/person GET " + id);
	
		List<Address> residences = addressRepository.findPersonAddress(id);
		return new ResponseEntity<List<Address>>(residences, HttpStatus.OK);

	}
	
	
	public AddressController() {
		// TODO Auto-generated constructor stub
	}

}
