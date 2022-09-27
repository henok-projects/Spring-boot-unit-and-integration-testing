package com.rest.service.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name="Country")
public class Country {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;
	
	@Column(name = "countryName")
	String countryName;
	
	@Column(name = "countryCapital")
	String countryCapital;
	
	public Country() {}
	
	public Country(int id, String countryName, String countryCapital) {
		this.id = id;
		this.countryName = countryName;
		this.countryCapital = countryCapital;
	}
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCountryCapital() {
		return countryCapital;
	}
	public void setCountryCapital(String countryCapital) {
		this.countryCapital = countryCapital;
	}
	
}
