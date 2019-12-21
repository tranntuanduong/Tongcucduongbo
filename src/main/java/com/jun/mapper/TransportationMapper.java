package com.jun.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jun.model.Transportation;

public class TransportationMapper implements RowMapper<Transportation> {

	@Override
	public Transportation mapRow(ResultSet resultSet) {
		Transportation transportation = new Transportation();
		try {
			transportation.setId(resultSet.getInt("id"));
			transportation.setName(resultSet.getString("name"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transportation;
	}



}
