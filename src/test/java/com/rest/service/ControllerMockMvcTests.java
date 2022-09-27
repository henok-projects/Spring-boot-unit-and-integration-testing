package com.rest.service;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.rest.service.beans.Country;
import com.rest.service.controllers.CountryController;
import com.rest.service.services.CountryService;


@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages="com.rest.service")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes= {ControllerMokitoTests.class})
public class ControllerMockMvcTests {
	
	@Autowired
	 MockMvc mockMvc;
	 
	@Mock
	CountryService countryService;
		
	@InjectMocks
	CountryController countryController;
	
	List<Country> myCountries;
	
	Country country;
	
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(countryController).build();
	}
	
	
	@Test
	@Order(1)
	public void test_getAllCountries() throws Exception {
		
		myCountries = new ArrayList<Country>();
		myCountries.add(new Country(1,"Kanada","Toronto"));
		myCountries.add(new Country(2,"Ethiopia","Addis Ababa"));
		
		when(countryService.getAllCountries()).thenReturn(myCountries); //Moking
		mockMvc.perform(get("/getCountries"))
			.andExpect(status().isFound())
			.andDo(print());		
	}
	
	@Test
	@Order(2)
	public void test_getCountryById(int Id) throws Exception {
		
		country=new Country(4,"Canada","Toronto");
		int countryId=4;
		
		when(countryService.getCountrybyID(countryId)).thenReturn(country); //Moking
		mockMvc.perform(get("/getCountries/{id}",countryId))
			.andExpect(status().isFound())
			.andExpectAll(MockMvcResultMatchers.jsonPath(".id").value(4))
			.andExpectAll(MockMvcResultMatchers.jsonPath(".countryName").value("Canada"))
			.andExpectAll(MockMvcResultMatchers.jsonPath(".countryCapital").value("Toronto"))
			.andDo(print());		
	}
	
	@Test
	@Order(3)
	public void test_getCountryByName() throws Exception {
		
		country=new Country(4,"Canada","Toronto");
		String countryName="Canada";
		
		when(countryService.getCountrybyName(countryName)).thenReturn(country); //Moking
		mockMvc.perform(get("/getCountries/countryname").param("name", "Canada"))
			.andExpect(status().isFound())
			.andExpectAll(MockMvcResultMatchers.jsonPath(".id").value(4))
			.andExpectAll(MockMvcResultMatchers.jsonPath(".countryName").value("Canada"))
			.andExpectAll(MockMvcResultMatchers.jsonPath(".countryCapital").value("Toronto"))
			.andDo(print());	
		
	}	
	
	@Test
	@Order(4)
	public void test_addCountry(){
		
		
	}
	
	@Test
	@Order(5)
	public void test_updateCountry(){
		
		
	}
	
	@Test
	@Order(6)
	public void test_deleteCountry() {
		
		
	}
}
