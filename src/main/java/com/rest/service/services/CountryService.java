package com.rest.service.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.rest.service.beans.Country;
import com.rest.service.controllers.AddResponse;
import com.rest.service.dao.CountryRepository;

@Component
@Service
public class CountryService {
	
	@Autowired
	CountryRepository countryRepo;
	
	
	public List<Country> getAllCountries() {
		List<Country> countries =countryRepo.findAll();
		return countries;
//		return countryRepo.findAll();
	
	}
	
	public Country getCountrybyID(int id) {
		
//		return countryRepo.findById(id).get();
		List<Country> countries= countryRepo.findAll();
		
		Country country=null;
		for(Country con:countries) {
			if(con.getId()==id) {
				country= con;
			}
		}
		
		return country;
	}
	
	public Country getCountrybyName(String countryName) {
		
		List<Country> countries = countryRepo.findAll();
		
		Country country=null;
		
		for(Country con:countries) {
			if(con.getCountryName().equalsIgnoreCase(countryName)) 
				country=con;			
		}
		
		 return country;
		 
	}
	
	public Country addCountry(Country country)
	{
		country.setId(getMaxId());
		countryRepo.save(country);
		return country;
	}
	
	//Utility method to get the max id
	public int getMaxId() {
		
		return countryRepo.findAll().size()+1;
	}
	
	public Country updateCountry(Country country)
	{
		countryRepo.save(country);
		return country;
	}
	
	public void deleteCountry(Country country) {
		countryRepo.delete(country);
	}
	
	public AddResponse deleteCountry(int id)
	{
		countryRepo.deleteById(id);
		AddResponse res = new AddResponse();
		res.setMsg("Country is Deleted");
		res.setId(id);
		return res;
				
	}
}
