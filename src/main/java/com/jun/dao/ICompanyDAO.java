package com.jun.dao;

import java.util.HashMap;
import java.util.List;

import com.jun.model.Company;

public interface ICompanyDAO extends GenericDAO<Company>{
	Company findByName(String name);
	Integer save(Company transportUnit);
	Long update(Company transportUnit);
	Company findById(Long id);
	List<Company> findByCode(String name);
	void saveList(List<Company> companyList);
	HashMap<String, String> findAllNameAndId(HashMap<String, String> companyRepeat, int locationId);
	HashMap<String, String> findAllNameAndTaxNumber();
	HashMap<String, String> findAllNameRepeat(int locationId);
}
