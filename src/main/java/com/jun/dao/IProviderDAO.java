package com.jun.dao;

import java.util.HashMap;

import com.jun.model.Provider;

public interface IProviderDAO extends GenericDAO<Provider>{
	Integer save(Provider provider);

	Provider findByName(String providerName);

	HashMap<String, Integer> findAllNameAndId(); 
}
