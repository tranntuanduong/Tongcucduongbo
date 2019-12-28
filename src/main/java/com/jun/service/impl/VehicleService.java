package com.jun.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.jun.dao.IVehicleDAO;
import com.jun.dao.impl.VehicleDAOImpl;
import com.jun.model.Vehicle;
import com.jun.service.IVehicleService;


public class VehicleService  implements IVehicleService {
	
	private IVehicleDAO vehicleDAO;

	public VehicleService() {
		if(vehicleDAO == null)
			vehicleDAO = new VehicleDAOImpl();
	}

	@Override
	public Vehicle save(Vehicle newVehicle) {
		if(newVehicle.getId() != null) {
			vehicleDAO.save(newVehicle);
		} else {			
			 vehicleDAO.update(newVehicle);
		}
		return newVehicle;
	}

	@Override
	public void saveList(List<Vehicle> vehicleList) {
		ArrayList<Vehicle> newVehicleList = new ArrayList<>();
		for(Vehicle vehicle : vehicleList) {
			if(vehicle.getId() == null) {
				newVehicleList.add(vehicle);
			} else {
				 vehicleDAO.update(vehicle);
			}
		}
		vehicleDAO.saveList(newVehicleList);
	}


	@Override
	public Vehicle update(Vehicle newVehicle) {
		Long vehicleId = vehicleDAO.update(newVehicle);
		return vehicleDAO.findById(vehicleId);
	}

	@Override
	public Vehicle findByLicensePlate(String licensePlace) {
		Vehicle vehicle =  vehicleDAO.findByLicensePlate(licensePlace);
		return vehicle;
	}

	@Override
	public List<Vehicle> findAll() {
		return vehicleDAO.findAll();
	}

	@Override
	public List<Vehicle> findByProvincialId(Object param) {
		return vehicleDAO.findByProvincial(param);
	}

	@Override
	public List<String> findAllLicensePlate() {
		return vehicleDAO.findAllLicensePlate();
	}
}
