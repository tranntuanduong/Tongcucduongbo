package com.jun.service.impl;

import java.util.HashMap;
import java.util.List;

import com.jun.dao.IProviderDAO;
import com.jun.dao.impl.ProviderDAOImpl;
import com.jun.model.Provider;
import com.jun.service.IProviderService;

public class ProviderService implements IProviderService {
	private IProviderDAO providerDAO;
	

	public ProviderService() {
		if(providerDAO == null)
			providerDAO = new ProviderDAOImpl();
	}


	@Override
	public void saveList(List<Provider> providerList) {
		for(Provider provider : providerList) {
			providerDAO.save(provider);
		}
		
	}

	@Override
	public Provider findByName(String providerName) {
		return providerDAO.findByName(providerName);
	}


	@Override
	public Integer save(Provider provider) {
		return providerDAO.save(provider);
	}


	@Override
	public HashMap<String, Integer> findAllNameAndId() {
		return providerDAO.findAllNameAndId();
	}



}
