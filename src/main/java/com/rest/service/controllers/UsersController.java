package com.rest.service.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@GetMapping        //Query parameter to filter data
	public String getUsers(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
		
		return "Get request for page: "+ page + "and limit is:"+ limit;
	}
	
	@GetMapping(path="/{userID}") //path parameter
	public String getUser(@PathVariable String userID) {
		
		return "Get By ID"+userID;
	}
	@PostMapping
	public String createUser() {
		
		return "Post";
	}
	
	@PutMapping
	public String updateUser() {
		
		return "Update";
	}
	
	@DeleteMapping
	public String deleteUser() {
		
		return "Delet";
	}
}
