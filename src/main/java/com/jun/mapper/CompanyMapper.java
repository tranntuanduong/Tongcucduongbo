package com.jun.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jun.model.Company;

public class CompanyMapper implements RowMapper<Company>{
	
	@Override
	public Company mapRow(ResultSet resultSet) {
		Company company = new Company();
		try {
			company.setId(resultSet.getInt("id"));
			company.setName(resultSet.getString("name"));
			company.setPhoneNumber(resultSet.getNString("phone_number"));
			company.setTaxNumber(resultSet.getString("taxt_number"));
			company.setLocationId(resultSet.getInt("location_id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}

}
