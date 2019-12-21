package com.jun.dao;

import java.util.HashMap;
import java.util.List;

import com.jun.mapper.RowMapper;

public interface GenericDAO<T> {
	Long save(String sql, Object... parameters);
	@SuppressWarnings("hiding")
	<T> List<T> findByProperty(String sql, RowMapper<T> rowMapper, Object param);
	void saveList(String sql);
	List<String> findAllString(String sql);
	HashMap<String, Integer> findAllNameAndId(String sql);
	HashMap<String, String> findAllNameAndId(String sql, HashMap<String, String> companyRepeat, int locationId);
	HashMap<String, String> findAllStringByProperty(String sql, Object param);
	HashMap<String, String> findAllNameAndTaxNumber(String sql);
}
