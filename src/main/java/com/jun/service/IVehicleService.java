package com.jun.service;

import java.util.List;

import com.jun.model.Vehicle;

public interface IVehicleService {
	Vehicle save(Vehicle newVehicle);
	void saveList(List<Vehicle> vehicleList);
	
	Vehicle findByLicensePlate(String licensePlace);
	Vehicle update(Vehicle newVehicle);
	
	List<Vehicle> findByProvincialId(Object param);
	List<Vehicle> findAll();
	List<String> findAllLicensePlate();
}

