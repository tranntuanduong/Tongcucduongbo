package com.jun.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jun.model.Record;

public class RecordMapper implements RowMapper<Record> {

	@Override
	public Record mapRow(ResultSet resultSet) {
		Record record = new Record();
		try {
			record.setId(resultSet.getLong("id"));
			record.setVehicleId(resultSet.getInt("vehicle_id"));
			record.setTotalKm(resultSet.getInt("total_km"));
			record.setViolateKm(resultSet.getInt("violate_km"));
			record.setTotalKm(resultSet.getInt("total_time"));
			record.setViolateTime(resultSet.getInt("violate_time"));
			record.setCreatedDate(resultSet.getInt("created_date"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return record;
	}

}
