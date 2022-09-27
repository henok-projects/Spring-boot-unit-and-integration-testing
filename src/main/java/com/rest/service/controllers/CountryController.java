package com.rest.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.service.beans.Country;
import com.rest.service.services.CountryService;

@RestController
public class CountryController {
	
	@Autowired  //dependency injection
	CountryService countryService;
	
	@GetMapping("/getCountries")
	public ResponseEntity<List<Country>> getCountries() {
		try {
		   List<Country> countries = countryService.getAllCountries();
			return new ResponseEntity<List<Country>>(countries, HttpStatus.FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/getCountries/{id}")  // id is path variable
	public ResponseEntity<Country> getCountrybyId(@PathVariable(value="id")int id) {
		try {
			Country country = countryService.getCountrybyID(id);
			return new ResponseEntity(country,HttpStatus.FOUND);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		}
	
	
	@GetMapping("/getCountries/countryname")  //name is request parameter
	public ResponseEntity<Country> getCountrybyName(@RequestParam(value="name")String name) {
		try {
			Country country = countryService.getCountrybyName(name);
			return new ResponseEntity(country,HttpStatus.FOUND);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
	}

	@PostMapping("/addCountry")
	public ResponseEntity<Country> addCountry(@RequestBody Country country)
	{
		try {
			country = countryService.addCountry(country);
			return new ResponseEntity<Country>(country,HttpStatus.CREATED);
		} 
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.CONFLICT);
			
		}
		
	}
	
	@PutMapping("/updateCountry/{id}")
	public ResponseEntity<Country> updateCountry(@PathVariable(value="id")int id,@RequestBody Country country)
	{
		try {
			
			Country existCountry= countryService.getCountrybyID(id);
			existCountry.setCountryName(country.getCountryName());
			existCountry.setCountryCapital(country.getCountryCapital());
						
			Country update_country = countryService.updateCountry(existCountry);
			
			return new ResponseEntity<Country>(update_country,HttpStatus.OK);
			
			
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
			
	}
	
	
	@DeleteMapping("/deleteCountry/{id}")
	public ResponseEntity<Country> DeleteCountry(@PathVariable(value="id") int id){
		Country country=null;
		try {
			 country = countryService.getCountrybyID(id);
			countryService.deleteCountry(id);
			
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Country>(country,HttpStatus.OK);
		
	}
	
	
	
}
  