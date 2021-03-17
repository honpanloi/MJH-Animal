package com.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Person;
import com.app.service.PersonService;

@RestController(value = "PersonController")
@RequestMapping(path = "/person")
@CrossOrigin(origins = {"http://localhost:4200"})
public class PersonController {

	private PersonService personService;	
		
	@Autowired
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}	
	
	@GetMapping(path="/view", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Person> findByPersonid(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session==null) {
			System.out.println("session is null");
		}
		Person person = (Person) session.getAttribute("person");
		return new ResponseEntity<Person>(this.personService.findByPersonid(person.getPersonid()), HttpStatus.OK);
	}
	
	@GetMapping(path="/update")
	public void updateInformation(@RequestBody Person person, HttpServletRequest request) {
		this.personService.updateInformation(person, request);
	}
	
}
