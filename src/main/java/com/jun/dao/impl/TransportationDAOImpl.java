package com.jun.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.jun.dao.ITransportationDAO;
import com.jun.mapper.TransportationMapper;
import com.jun.model.Transportation;

public class TransportationDAOImpl extends AbstractDAO<Transportation> implements ITransportationDAO{
	private static final Logger log = Logger.getLogger(TransportationDAOImpl.class);

	@Override
	public Integer save(Transportation transpotation) {
		String sql = "INSERT INTO transportation(name) VALUES(?)";
		log.info(sql);
		return save(sql, transpotation.getName()).intValue();
	}

	@Override
	public Transportation findByName(String name) {
		String sql = "SELECT * FROM transportation WHERE name = ?";
		List<Transportation> result = findByProperty(sql, new TransportationMapper(), name);
		log.info(sql);
		return result.isEmpty() ? null : result.get(0);
	}

	@Override
	public HashMap<String, Integer> findAllNameAndId() {
		String sql = "SELECT name, id FROM transportation";
		log.info(sql);
		return findAllNameAndId(sql);
	}


}
