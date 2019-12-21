package com.jun.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.jun.dao.IProviderDAO;
import com.jun.mapper.ProviderMapper;
import com.jun.model.Provider;

public class ProviderDAOImpl extends AbstractDAO<Provider> implements IProviderDAO{
	private static final Logger log = Logger.getLogger(ProviderDAOImpl.class);
	@Override
	public Integer save(Provider provider) {
		String sql = "INSERT INTO provider(name, code, phone_number) VALUES(?, ?, ?)";
		log.info(sql);
		Long id	 =  save(sql, provider.getName(), provider.getCode(), provider.getPhoneNumber());
		return id.intValue();
	}

	@Override
	public Provider findByName(String providerName) {
		String sql = "SELECT * FROM provider WHERE name = ?";
		log.info(sql);
		List<Provider> providerList =  findByProperty(sql, new ProviderMapper(), providerName);
		return providerList.isEmpty() ? null : providerList.get(0);
	}

	@Override
	public HashMap<String, Integer> findAllNameAndId() {
		String sql = "SELECT * FROM provider";
		log.info(sql);
		return findAllNameAndId(sql);
	}
}
