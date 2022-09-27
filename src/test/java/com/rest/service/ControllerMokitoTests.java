package com.rest.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rest.service.beans.Country;
import com.rest.service.controllers.CountryController;
import com.rest.service.services.CountryService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes=ControllerMokitoTests.class)
public class ControllerMokitoTests {
	
	@Mock
	CountryService countryService;
	
	@InjectMocks
	CountryController countryController;
	
	List<Country> myCountries;
	
	Country country;
	
	@Test
	@Order(1)
	public void test_getAllCountries() {
		myCountries = new ArrayList<Country>();
		myCountries.add(new Country(1,"Kanada","Toronto"));
		myCountries.add(new Country(2,"Ethiopia","Addis Ababa"));
		
		when(countryService.getAllCountries()).thenReturn(myCountries); //Moking
		
		ResponseEntity<List<Country>> res=countryController.getCountries();
		assertEquals(HttpStatus.FOUND,res.getStatusCode());
		assertEquals(2,res.getBody().size());
		
	}
	
	@Test
	@Order(2)
	public void test_getCountryById() {
		country= new Country(3,"Kenya","Nirobi");
		
		int contryId =3;
		when(countryService.getCountrybyID(contryId)).thenReturn(country); //Moking
		
		ResponseEntity<Country> res=countryController.getCountrybyId(contryId);
		assertEquals(HttpStatus.FOUND,res.getStatusCode());
		assertEquals(contryId,res.getBody().getId());
		
	}
	
	@Test
	@Order(3)
	public void test_getCountryByName() {
		country= new Country(3,"Kenya","Nirobi");
		
		String contryName ="Kenya";
		when(countryService.getCountrybyName(contryName)).thenReturn(country); //Moking
		
		ResponseEntity<Country> res=countryController.getCountrybyName(contryName);
		assertEquals(HttpStatus.FOUND,res.getStatusCode());
		assertEquals(contryName,res.getBody().getCountryName());
		
	}
	
	@Test
	@Order(4)
	public void test_addCountry() {
		country= new Country(4,"France","Paris");
		
		when(countryService.addCountry(country)).thenReturn(country); //Moking
		
		ResponseEntity<Country> res=countryController.addCountry(country);
		assertEquals(HttpStatus.CREATED,res.getStatusCode());
		assertEquals(country,res.getBody());
		
	}
	
	@Test
	@Order(5)
	public void test_updateCountry() {
		country= new Country(4,"Japan","Tokiyo");
		int countryId=4;
		
		when(countryService.getCountrybyID(countryId)).thenReturn(country); //Moking
		when(countryService.updateCountry(country)).thenReturn(country); //Moking
		
		ResponseEntity<Country> res=countryController.updateCountry(countryId, country);
		assertEquals(HttpStatus.OK,res.getStatusCode());
		assertEquals(countryId,res.getBody().getId());
		assertEquals("Japan",res.getBody().getCountryName());
		assertEquals(country,res.getBody());
		
		
	}
	
	@Test
	@Order(6)
	public void test_deleteCountry() {
		country= new Country(4,"Japan","Tokiyo");
		int countryId=4;
		
		when(countryService.getCountrybyID(countryId)).thenReturn(country); //Moking
		
		
		ResponseEntity<Country> res=countryController.DeleteCountry(countryId);
		assertEquals(HttpStatus.OK,res.getStatusCode());
		assertEquals(countryId,res.getBody().getId());
		assertEquals("Japan",res.getBody().getCountryName());
		
		
		
	}
	
	
	
}
