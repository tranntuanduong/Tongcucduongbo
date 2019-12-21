package com.jun.service.impl;

import java.util.HashMap;

import com.jun.dao.ITransportationDAO;
import com.jun.dao.impl.TransportationDAOImpl;
import com.jun.model.Transportation;
import com.jun.service.ITransportationService;

public class TransportationService implements ITransportationService {

	private ITransportationDAO transportationDAO;

	public TransportationService() {
		if(transportationDAO == null)
			transportationDAO = new TransportationDAOImpl();
	}

	@Override
	public Transportation findByName(String name) {
		return transportationDAO.findByName(name);
	}

	@Override
	public Integer save(Transportation transpotation) {
		return transportationDAO.save(transpotation);
	}

	@Override
	public HashMap<String, Integer> findAllNameAndId() {
		return transportationDAO.findAllNameAndId();
	}

}
