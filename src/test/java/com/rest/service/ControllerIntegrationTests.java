package com.rest.service;

import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.Assert.assertEquals;

import org.json.JSONException;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.rest.service.beans.Country;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ControllerIntegrationTests {
	
	@Test
	@Order(1)
	void getAllCountriesIntegrationTest() throws JSONException {
		String expected="[\n"
				+ "    {\n"
				+ "        \"id\": 1,\n"
				+ "        \"countryName\": \"ET\",\n"
				+ "        \"countryCapital\": \"addis\"\n"
				+ "    }\n"
				+ "]";
		TestRestTemplate restTemplate = new TestRestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8083/getCountries",String.class );
		System.out.print(response.getStatusCode());
		System.out.print(response.getBody());
		
		JSONAssert.assertEquals(expected, response.getBody(), false);
		
	}
	
	@Test
	@Order(2)
	void getCountryByIdIntegrationTest() throws JSONException {
		
		String expected="{\n"
				+ "        \"id\": 1,\n"
				+ "        \"countryName\": \"ET\",\n"
				+ "        \"countryCapital\": \"addis\"\n"
				+ "    }";
		
		TestRestTemplate rest = new TestRestTemplate();
		ResponseEntity<String> response = rest.getForEntity("http://localhost:8083/getCountries/1",String.class );
		System.out.print(response.getStatusCode());
		System.out.print(response.getBody());
		
		JSONAssert.assertEquals(expected, response.getBody(), false);
			
	}
	
	@Test
	@Order(3)
	void getCountryByNameIntegrationTest() throws JSONException {
		
		String expected="{\n"
				+ "        \"id\": 1,\n"
				+ "        \"countryName\": \"ET\",\n"
				+ "        \"countryCapital\": \"addis\"\n"
				+ "    }";
		
		TestRestTemplate rest = new TestRestTemplate();
		ResponseEntity<String> response = rest.getForEntity("http://localhost:8083/getCountries/countryname?name=ET",String.class );
		System.out.print(response.getStatusCode());
		System.out.print(response.getBody());
		
		JSONAssert.assertEquals(expected, response.getBody(), false);
			
	}
	
	@Test
	@Order(4)
	void addCountryIntegrationTest() throws JSONException {
		
		Country country= new Country(9,"Canada","Toronto");
		
		String expected="{\n"
				+ "        \"id\": 9,\n"
				+ "        \"countryName\": \"Canada\",\n"
				+ "        \"countryCapital\": \"Toronto\"\n"
				+ "    }";
		
		TestRestTemplate rest = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Country> request = new HttpEntity<Country>(country,headers);
		ResponseEntity<String> response = rest.postForEntity("http://localhost:8083/addCountry",request, String.class );
		System.out.print(response.getBody());
		
		assertEquals(HttpStatus.CREATED,response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), false);
		
	}
	
	@Test
	@Order(5)
	void updateCountryIntegrationTest() throws JSONException {
		
		Country country= new Country(4,"Indian","Delhi");
		
		String expected="{\n"
				+ "        \"id\": 4,\n"
				+ "        \"countryName\": \"Indian\",\n"
				+ "        \"countryCapital\": \"Delhi\"\n"
				+ "    }";
		
		TestRestTemplate rest = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Country> request = new HttpEntity<Country>(country,headers);
		ResponseEntity<String> response = rest.exchange("http://localhost:8083//updateCountry/4",HttpMethod.PUT,request, String.class );
		System.out.print(response.getBody());
		
		assertEquals(HttpStatus.OK,response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), false);
		
	}
	
	@Test
	@Order(6)
	void deleteCountryIntegrationTest() throws JSONException {
		
		Country country= new Country(3,"Indian","Delhi");
		
		String expected="{\n"
				+ "        \"id\": 3,\n"
				+ "        \"countryName\": \"Indian\",\n"
				+ "        \"countryCapital\": \"Delhi\"\n"
				+ "    }";
		
		TestRestTemplate rest = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Country> request = new HttpEntity<Country>(country,headers);
		ResponseEntity<String> response = rest.exchange("http://localhost:8083//deleteCountry/3",HttpMethod.DELETE,request, String.class );
		System.out.print(response.getBody());
		
		assertEquals(HttpStatus.OK,response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), false);
		
	}
	
}