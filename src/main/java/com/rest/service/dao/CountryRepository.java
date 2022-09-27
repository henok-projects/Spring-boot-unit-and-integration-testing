package com.rest.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.service.beans.Country;

public interface CountryRepository extends JpaRepository<Country,Integer> {
	
}
