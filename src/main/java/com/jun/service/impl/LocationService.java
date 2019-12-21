package com.jun.service.impl;

import java.util.HashMap;
import java.util.List;

import com.jun.dao.ILocationDAO;
import com.jun.dao.impl.LocationDAOImpl;
import com.jun.model.Location;
import com.jun.service.ILocationService;

public class LocationService implements ILocationService {

	private ILocationDAO locationDAO;
	
	public LocationService() {
		if(locationDAO == null)
			locationDAO = new LocationDAOImpl();
	}

	@Override
	public Location findByName(String name) {
		return locationDAO.findByName(name);
	}

	@Override
	public Location findById(Long id) {
		return locationDAO.findById(id);
	}

	@Override
	public Long findIdByName(String provincialName) {
		return locationDAO.findIdByName(provincialName);
	}

	@Override
	public Long save(Location location) {
		return locationDAO.save(location);
	}

	@Override
	public void saveList(List<Location> locationList) {
		for(Location location : locationList) {
			locationDAO.save(location);
		}
	}

	@Override
	public HashMap<String, Integer> findAllNameAndId() {
		return locationDAO.findAllNameAndId();
	}
}
