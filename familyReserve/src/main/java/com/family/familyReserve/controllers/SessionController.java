package com.family.familyReserve.controllers;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.family.familyReserve.domain.Family;
import com.family.familyReserve.domain.Person;
import com.family.familyReserve.domain.PersonRepository;

@Controller
public class SessionController {
	@Autowired
	private PersonRepository personRepository;
	
	@PostMapping({"/session/new"})
	public ResponseEntity<Person> login(@RequestBody Credentials creds) {
		System.out.println("Logging in user " + creds.getUserName());
		System.out.println("/api/family POST ");
		if (creds.getUserName() == null) {
			return new ResponseEntity<Person>(HttpStatus.BAD_REQUEST);
		}
		
		if (creds.getPassword() == null) {
			return new ResponseEntity<Person>(HttpStatus.BAD_REQUEST);
		}
		Person person = personRepository.checkCredentials(creds.getUserName(), creds.getPassword());
		if (person != null){
			return new ResponseEntity<Person>(person, HttpStatus.OK);
		}
		return new ResponseEntity<Person>(HttpStatus.UNAUTHORIZED);
	}
	
	
	static class Credentials {
		private String userName;
		private String password;
		
		public String getUserName() {
			return userName;
		}
		public void setUserName(String username) {
			this.userName = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	}
	
}

//System.out.println(req.queryParams("returnUserName"));
//System.out.println(req.queryParams("returnUserPassword"));
//
//User usr = new User(req.queryParams("returnUserName"),
//		req.queryParams("returnUserPassword"));
//
//if (!FritterDB.checkUser(usr)) {
//	return "Invalid User, password combination";
//
//} else {
////	String sessionId = req.cookie("JSESSIONID");
////	res.cookie("JSESSIONID", sessionId, 3600);
//	req.session().attribute("user", usr);
//	return "logged in";
//}