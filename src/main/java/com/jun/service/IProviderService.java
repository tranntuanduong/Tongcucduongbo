package com.jun.service;

import java.util.HashMap;
import java.util.List;

import com.jun.model.Provider;

public interface IProviderService {
	void saveList(List<Provider> providerList);

	Provider findByName(String stringCellValue);

	Integer save(Provider provider);

	HashMap<String, Integer> findAllNameAndId();

}
