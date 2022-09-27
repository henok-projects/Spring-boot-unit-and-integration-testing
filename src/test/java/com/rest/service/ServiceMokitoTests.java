package com.rest.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

import com.rest.service.beans.Country;
import com.rest.service.dao.CountryRepository;
import com.rest.service.services.CountryService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes= {ServiceMokitoTests.class})
public class ServiceMokitoTests {
	
	@Mock
	CountryRepository countryRepo;
	
	@InjectMocks
	CountryService countryService;
	
	public List<Country> myCountries;
	
	@Test
	@Order(1)
	public void test_getAllCountries() {
		
		List<Country> myCountries= new ArrayList<Country>();
					
		myCountries.add(new Country(1,"Londen","UK"));
		myCountries.add(new Country(2,"USA","DC"));
		myCountries.add(new Country(3,"Polish","Warsaw"));
		
		when(countryRepo.findAll()).thenReturn(myCountries); // Moking statement
		
		assertEquals(3,countryService.getAllCountries().size());
	}
	
	@Test
	@Order(2)
	public void test_getCountryById() {
		
		List<Country> myCountries= new ArrayList<Country>();
		
		myCountries.add(new Country(1,"Londen","UK"));
		myCountries.add(new Country(2,"USA","DC"));
		myCountries.add(new Country(3,"Polish","Warsaw"));
		int countryId=1;
		
		when(countryRepo.findAll()).thenReturn(myCountries); // Moking statement
		assertEquals(countryId,countryService.getCountrybyID(countryId).getId());
		
	}
	
	@Test
	@Order(3)
	public void test_getCountryByName() {
		
		List<Country> myCountries= new ArrayList<Country>();
		
		myCountries.add(new Country(1,"Uk","london"));
		myCountries.add(new Country(2,"USA","DC"));
		myCountries.add(new Country(3,"Polish","Warsaw"));
		String countryName="Uk";
		
		when(countryRepo.findAll()).thenReturn(myCountries); // Moking statement
		assertEquals(countryName, countryService.getCountrybyName(countryName).getCountryName());
		
	}
	
	
	
	@Test 
	@Order(4) 
	public void test_addCountry() {
		
		Country country = new Country(4,"Germeny","Berlin");
	
		when(countryRepo.save(country)).thenReturn(country);
		assertEquals(country, countryService.addCountry(country));
		
		}
	
	@Test 
	@Order(5) 
	public void test_updateCountry() {
		
		Country country = new Country(4,"Germeny","Berlin");
	
		when(countryRepo.save(country)).thenReturn(country);
		assertEquals(country, countryService.updateCountry(country));
		
		}
	
	@Test 
	@Order(6) 
	public void test_deleteCountry() {
		
		Country country = new Country(4,"Germeny","Berlin");
	
		//when(countryRepo.delete(country)).thenReturn(country); I can not use because the service method is void
		countryService.deleteCountry(country);
		verify(countryRepo,times(1)).delete(country);
		
		}
	
	
}
