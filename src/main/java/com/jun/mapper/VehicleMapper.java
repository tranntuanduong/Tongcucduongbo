package com.jun.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jun.model.Vehicle;

public class VehicleMapper implements RowMapper<Vehicle> {

	@Override
	public Vehicle mapRow(ResultSet resultSet) {
		Vehicle vehicle = new Vehicle();
		try {
			vehicle.setId(resultSet.getInt("id"));
			vehicle.setLicensePlate(resultSet.getString("license_plate"));
			vehicle.setWeight(resultSet.getFloat("weight"));
			vehicle.setPersonCarriedNumber(resultSet.getInt("person_carried_number"));
			vehicle.setCompanyId(resultSet.getInt("company_id"));
			vehicle.setTransportationId(resultSet.getInt("transportation_id"));
			vehicle.setProviderId(resultSet.getInt("provider_id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vehicle;
	}

}
