package com.jun.service;

import java.util.HashMap;
import java.util.List;

import com.jun.model.Company;

public interface ICompanyService {
	Long update(Company newTransportUnit);
	void saveList(List<Company> transportList);
	Company findByName(String name);
	Company findById(Long id);
	List<Company> findByCode(String code);
	Integer save(Company newCompany);
	HashMap<String, String> findAllNameAndId(int locationId);
	HashMap<String, String> findAllNameAndTaxtNumber();
    List<Company> findAll();
    List<Company> findByLocationId(Integer locationId);
}
