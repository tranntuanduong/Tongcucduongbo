package com.jun.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jun.model.Location;

public class LocationMapper implements RowMapper<Location>{

	@Override
	public Location mapRow(ResultSet resultSet) {
		Location location = new Location();
		try {
			location.setId(resultSet.getInt("id"));
			location.setName(resultSet.getString("name"));
			location.setCode(resultSet.getString("code"));
			location.setParentId(resultSet.getInt("parent_id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return location;
	}

}
