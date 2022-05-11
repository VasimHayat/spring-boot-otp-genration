package com.app.auth.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.auth.beans.EOUser;
import com.app.auth.exceptions.GlbException;
import com.app.auth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/users")
@CrossOrigin(origins = "*")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	

	
	@GetMapping(path="")
	public List<EOUser> findAllUsers() {
		log.info("Retrieving all employee");
		return userService.findAllUsers();
	}
	
	@GetMapping(path="/{id}")
	public EOUser getUser(@PathVariable("id") String  id) {
		log.info("Getting Employee with employee id :: "+id);
		return userService.findUserByEmail(id);
	}
	
	@PostMapping(path="/add")
	@ResponseBody
	public EOUser addUser(@RequestBody EOUser user) {
		log.info("Adding user...");
		EOUser user1 =  userService.addUser(user);
		log.info("User has been added successfully!! :: "+user1);
		return user1;
	}
	
	@PutMapping(path="/{id}")
	public EOUser updateUser(@RequestBody EOUser employee, @PathVariable long id) {
		log.info("Updating employee :: "+employee);
		return userService.updateUser(employee, id);
	}
	
	@DeleteMapping(path="/{id}")
	public EOUser deleteUser(@PathVariable long id) {
		log.info("Deleting Employee with id :: "+id);
		return userService.deleteUser(id);
	}
	
	@ExceptionHandler(value= GlbException.class)
	public ResponseEntity<Object> exception(GlbException ex){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", true);
		map.put("message", ex.getMessage());
		map.put("exception", "Could not perform action");
		return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
	}
	
}













