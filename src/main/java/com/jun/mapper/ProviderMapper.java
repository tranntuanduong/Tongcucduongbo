package com.jun.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jun.model.Provider;

public class ProviderMapper implements RowMapper<Provider>{

	@Override
	public Provider mapRow(ResultSet resultSet) {
		Provider provider = new Provider();
		try {
			provider.setId(resultSet.getInt("id"));
			provider.setName(resultSet.getString("name"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return provider;
	}

}
