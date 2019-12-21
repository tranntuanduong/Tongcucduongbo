package com.jun.dao;

import java.util.HashMap;

import com.jun.model.Transportation;

public interface ITransportationDAO extends GenericDAO<Transportation> {

	Integer save(Transportation transpotation);

	Transportation findByName(String name);

	HashMap<String, Integer> findAllNameAndId();
		
}
