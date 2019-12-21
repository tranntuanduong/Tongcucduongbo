package com.jun.dao;

import java.util.HashMap;
import java.util.List;

import com.jun.model.Location;

public interface ILocationDAO extends GenericDAO<Location> {
	Location findByName(String name);
	Location findById(Long id);
	Long findIdByName(String provincialName);
	Long save(Location location);
	void saveList(List<Location> locationList);
	HashMap<String, Integer> findAllNameAndId();
}
