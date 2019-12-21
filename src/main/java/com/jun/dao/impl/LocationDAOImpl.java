package com.jun.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.jun.dao.ILocationDAO;
import com.jun.mapper.LocationMapper;
import com.jun.model.Location;

public class LocationDAOImpl extends AbstractDAO<Location> implements ILocationDAO {
	private static final Logger log = Logger.getLogger(LocationDAOImpl.class);
	@Override
	public Location findByName(String name) {
		String sql = "SELECT * FROM location WHERE name = ?";
		List<Location> provincials = findByProperty(sql, new LocationMapper(), name);
		log.info(sql);
		return provincials.isEmpty() ? null : provincials.get(0);
	}
	@Override
	public Location findById(Long id) {
		String sql = "SELECT * FROM provincial WHERE id = ?";
		List<Location> provincials = findByProperty(sql, new LocationMapper(), id);
		log.info(sql);
		return provincials.isEmpty() ? null : provincials.get(0);
	}
//	@Override
//	public Long findIdByName(String name) {
//		String sql = "SELECT * FROM provincial WHERE name = ?";
//		List<Location> provincials = findByProperty(sql, new LocationMapper(), name);
//		log.info(sql);
//		return provincials.isEmpty() ? null : provincials.get(0).getId();
//	}
	@Override
	public Long save(Location location) {
		String sql = "INSERT INTO location(name, code, parent_id) VALUES(?, ?, ?)";
		log.info(sql);
		return save(sql, location.getName(), location.getCode(), location.getParentId());
	}
	@Override
	public void saveList(List<Location> locationList) {
		
		
	}
	@Override
	public Long findIdByName(String provincialName) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public HashMap<String, Integer> findAllNameAndId() {
		String sql = "SELECT * FROM location";
		log.info(sql);
		return findAllNameAndId(sql);
	}
}
