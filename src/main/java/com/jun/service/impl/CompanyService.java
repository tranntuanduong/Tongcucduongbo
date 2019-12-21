package com.jun.service.impl;

import java.util.HashMap;
import java.util.List;

import com.jun.dao.ICompanyDAO;
import com.jun.dao.impl.CompanyDAOImpl;
import com.jun.model.Company;
import com.jun.service.ICompanyService;

public class CompanyService implements ICompanyService {

	private ICompanyDAO companyDAO;

	public CompanyService() {
		if (companyDAO == null)
			companyDAO = new CompanyDAOImpl();
	}

	@Override
	public Integer save(Company newCompany) {
		Integer companyId = companyDAO.save(newCompany);
		return companyId;
	}

	@Override
	public void saveList(List<Company> companyList) {
		companyDAO.saveList(companyList);
	}

	@Override
	public Company findByName(String name) {
		return companyDAO.findByName(name);
	}

	@Override
	public Long update(Company newCompany) {
		return companyDAO.update(newCompany);
	}

	@Override
	public Company findById(Long id) {
		return companyDAO.findById(id);
	}

	@Override
	public List<Company> findByCode(String code) {
		return companyDAO.findByCode(code);
	}

	@Override
	public HashMap<String, String> findAllNameAndId(int locationId) {
		HashMap<String, String>  companyRepeat = companyDAO.findAllNameRepeat(locationId);
		return companyDAO.findAllNameAndId(companyRepeat, locationId);
	}

	@Override
    public HashMap<String, String> findAllNameAndTaxtNumber() {
        return companyDAO.findAllNameAndTaxNumber();
    }
}
