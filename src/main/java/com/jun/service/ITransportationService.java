package com.jun.service;

import java.util.HashMap;

import com.jun.model.Transportation;

public interface ITransportationService {
	Transportation findByName(String stringCellValue);
	Integer save(Transportation transportation);
	HashMap<String, Integer> findAllNameAndId();
}
