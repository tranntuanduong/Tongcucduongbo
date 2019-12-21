package com.jun.model;

public class Company {
	private Integer id;
	private String name;
	private String phoneNumber;
	private String taxNumher;
	private Integer locationId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getTaxNumber() {
		return taxNumher;
	}
	public void setTaxNumber(String taxNumher) {
		this.taxNumher = taxNumher;
	}
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	
	
}
