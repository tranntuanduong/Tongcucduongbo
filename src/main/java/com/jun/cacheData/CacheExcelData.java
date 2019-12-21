package com.jun.cacheData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CacheExcelData {
	HashMap<String, Integer> locationListStr = new HashMap<String, Integer>();
	HashMap<String, Integer> transportationListStr = new HashMap<String, Integer>();
	HashMap<String, String> companyListStr = new HashMap<String, String>();
	HashMap<String, Integer> providerListStr = new HashMap<String, Integer>();
	List<String> licensePlateListStr = new ArrayList<String>();
	
	public HashMap<String, Integer> getLocationListStr() {
		return locationListStr;
	}
	public void setLocationListStr(HashMap<String, Integer> locationListStr) {
		this.locationListStr = locationListStr;
	}
	public HashMap<String, Integer> getTransportationListStr() {
		return transportationListStr;
	}
	public void setTransportationListStr(HashMap<String, Integer> transportationListStr) {
		this.transportationListStr = transportationListStr;
	}
	public HashMap<String, String> getCompanyListStr() {
		return companyListStr;
	}
	public void setCompanyListStr(HashMap<String, String> companyListStr) {
		this.companyListStr = companyListStr;
	}
	public HashMap<String, Integer> getProviderListStr() {
		return providerListStr;
	}
	public void setProviderListStr(HashMap<String, Integer> providerListStr) {
		this.providerListStr = providerListStr;
	}
	public List<String> getLicensePlateListStr() {
		return licensePlateListStr;
	}
	public void setLicensePlateListStr(List<String> licensePlateListStr) {
		this.licensePlateListStr = licensePlateListStr;
	}
	
	
}
