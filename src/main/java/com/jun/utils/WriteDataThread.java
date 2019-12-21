//package com.jun.utils;
//
//import java.io.File;
//import java.util.List;
//
//import org.apache.log4j.Logger;
//
//import com.jun.model.Company;
//import com.jun.model.ReportTransportDepartement;
//import com.jun.model.Vehicle;
//import com.jun.service.ICompanyService;
//import com.jun.service.IVehicleService;
//
//public class WriteDataThread extends Thread {
//	private static final Logger log = Logger.getLogger(ThreadWriteData.class);
//	private IVehicleService vehicleService;
//	private ICompanyService transportUnitService;
//	private ExcelReader excelReader;
//
//	public WriteDataThread(IVehicleService vehicleService,
//			ICompanyService transportUnitService, ExcelReader excelReader) {
//		this.vehicleService = vehicleService;
//		this.transportUnitService = transportUnitService;
//		this.excelReader = excelReader;
//	}
//
//	@Override
//	public void run() {
//		log.info("Thread -- start");
//		while (true) {
//			try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			File[] files = new File("excelFile").listFiles();
//			if (files != null) {
//				for (File file : files) {
//					if (file.isFile()) {
//						if(file.getName().contains("DSPT")) {
//							List<Vehicle> vehicleList = excelReader.readVehicleExcelFile(file.getName());
//							vehicleService.saveList(vehicleList);
//						} else if(file.getName().contains("DSDVVT")) {
//							List<Company> transportUnitList = excelReader.readTransportExcelFile(file.getName());
//							transportUnitService.saveList(transportUnitList);
//						} else if(file.getName().toUpperCase().contains("BAOCAOTONGHOPSOGIAOTHONGVANTAI")) {
//							List<ReportTransportDepartement>  reportTransportDepartementList = 
//									excelReader.readReportOfTransportDepartment(file.getName());
//							System.out.println("das");
//							//luu xuong database tinh sau
//						}
//					}
//					
//					if(file.exists()) {
//						File destination = new File("excelReaded/" + file.getName());
//						file.renameTo(destination);
//						log.info("reNameTo: " + file.getName());
//						file.delete();
//					}
//				}
//			}
//
//		}
//	}
//}
