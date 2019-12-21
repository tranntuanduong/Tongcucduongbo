//package com.jun.utils;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.lang.reflect.Field;
//import java.util.List;
//import java.util.ResourceBundle;
//
//import org.apache.log4j.Logger;
//import org.apache.poi.ss.usermodel.BorderStyle;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.Font;
//import org.apache.poi.ss.usermodel.HorizontalAlignment;
//import org.apache.poi.ss.usermodel.IndexedColors;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.util.CellRangeAddress;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import com.jun.constant.Constant;
//import com.jun.model.BusinessType;
//import com.jun.model.Company;
//import com.jun.model.Location;
//import com.jun.model.Vehicle;
//import com.jun.service.IBusinessTypeService;
//import com.jun.service.ICompanyService;
//import com.jun.service.ILocationService;
//import com.jun.service.IVehicleService;
//import com.jun.service.impl.BusinessTypeService;
//import com.jun.service.impl.CompanyService;
//import com.jun.service.impl.LocationService;
//import com.jun.service.impl.VehicleService;
//
//public class WriteDataToExcel {
//
//	private static final Logger log = Logger.getLogger(ExcelReader.class);
//	private ICompanyService companyService;
//	private ILocationService provincicalService;
//	private IBusinessTypeService businessTypeService;
//	private IVehicleService vehicleService;
//	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("excelforlder");
//
//	public WriteDataToExcel() {
//		if (companyService == null)
//			companyService = new CompanyService();
//		if (provincicalService == null)
//			provincicalService = new LocationService();
//		if (businessTypeService == null)
//			businessTypeService = new BusinessTypeService();
//		if (vehicleService == null)
//			vehicleService = new VehicleService();
//	}
//
//	public void writeVehicle(List<Vehicle> vehicleList, String fileName, Long provincialId)
//			throws FileNotFoundException, IOException {
//		XSSFWorkbook workbook = null;
//		XSSFSheet sheet = null;
//		try {
//			workbook = new XSSFWorkbook();
//			sheet = workbook.createSheet("DanhSachPhuongTien");
//			Row row = null;
//			Cell cell;
//			// .. create row and cell
//			for (int i = 0; i < vehicleList.size() + 8; i++) {
//				row = sheet.createRow(i);
//				for (int j = 0; j < 12; j++) {
//					cell = row.createCell(j);
//					if (i > 6) {
//						cell.setCellStyle(cellBorder(sheet));
//						if (i == 7) {
//							cell.setCellStyle(fontColumn(sheet));
//						}
//					}
//				}
//			}
//			createVehicleHeaderRow(sheet, provincialId);
//			createVehicleTitleRow(sheet);
//			int rowCount = 7;
//			int STT = 1;
//			for (Vehicle vehicle : vehicleList) {
////				row = sheet.createRow(++rowCount);
//				rowCount++;
//				mergeVehicleCell(sheet, rowCount);
//				// ..load field
//				Field[] fields = vehicle.getClass().getDeclaredFields();
//				for (Field field : fields) {
//					String fieldName = field.getName();
//					switch (fieldName) {
//					case "id":
//						// ..STT
////						cell = row.createCell(0);
//						sheet.getRow(rowCount).getCell(0).setCellValue(STT++);
//						break;
//					case "licensePlate":
//						// .. bien so xe
////						cell = row.createCell(1);
//						sheet.getRow(rowCount).getCell(1).setCellValue(vehicle.getLicensePlate());
//						break;
//					case "businessType":
//						// .. loai hinh
////						cell = row.createCell(2);
//						BusinessType businessType = businessTypeService.findById(vehicle.getBusinessType().getId());
//						sheet.getRow(rowCount).getCell(2).setCellValue(businessType.getName());
//						break;
//					case "transportUnit":
//						// .. don vi van tai
////						cell = row.createCell(4);
//						Company transportUnit = companyService.findById(vehicle.getTransportUnit().getId());
//						sheet.getRow(rowCount).getCell(4).setCellValue(transportUnit.getName());
//						break;
//					case "dataTransport":
//						// .. don vi truyen du lieu
////						cell = row.createCell(6);
//						Company dataTransport = companyService.findById(vehicle.getDataTransport().getId());
//						sheet.getRow(rowCount).getCell(6).setCellValue(dataTransport.getName());
//						break;
//					case "provincial":
//						// .. So GTVT: ko su li
//						break;
//					case "weight":
//						// ..tai trong
////						cell = row.createCell(9);
//						sheet.getRow(rowCount).getCell(9)
//								.setCellValue(String.valueOf(vehicle.getWeight()).replace(".", ","));
//						break;
//					case "seat":
//						// ..so ghe
//						sheet.getRow(rowCount).getCell(10).setCellValue(vehicle.getSeat());
//						break;
//					default:
//						break;
//					}
//				}
//			}
//			// .. write file
//			try (FileOutputStream outputStream = new FileOutputStream(
//					resourceBundle.getString("excelWriter") + "/" + fileName)) {
//				workbook.write(outputStream);
//				log.info("write file: " + fileName);
//			}
//		} catch (Exception e) {
//			log.info(e.getMessage());
//		} finally {
//			// .. quan trong, nho dong file
//			if (workbook != null) {
//				workbook.close();
//			}
//		}
//
//	}
//
//	private void createVehicleTitleRow(XSSFSheet sheet) {
//		mergeVehicleCell(sheet, 7);
////		Row row = sheet.createRow(7);
//		CellStyle cellStyle = fontColumn(sheet);
//		int index = 0;
//		for (int i = 0; i < 12; i++) {
//			if (isVehicleColumn(i)) {
//				sheet.getRow(7).getCell(i).setCellStyle(cellStyle);
//				sheet.getRow(7).getCell(i).setCellValue(Constant.VEHICLE_FORMAT_FILE[index++]);
//			}
//		}
//	}
//
//	private void createVehicleHeaderRow(Sheet sheet, Long provincialId) {
//		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 11));
//		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 11));
//		sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 11));
//		sheet.addMergedRegion(new CellRangeAddress(4, 4, 3, 6));
//		sheet.addMergedRegion(new CellRangeAddress(3, 3, 3, 4));
//		sheet.addMergedRegion(new CellRangeAddress(3, 3, 5, 6));
//		sheet.addMergedRegion(new CellRangeAddress(5, 5, 3, 4));
//		sheet.addMergedRegion(new CellRangeAddress(5, 5, 5, 6));
//
//		CellStyle cellStyle = fontMinistryOfTransport(sheet);
//		CellStyle cellStyle1 = fontVietnamRoadAdministration(sheet);
//		CellStyle cellStyle2 = fontTitle(sheet);
//		CellStyle cellStyle3 = fontTitleComplementary(sheet);
//
//		sheet.getRow(0).getCell(0).setCellStyle(cellStyle);
//		sheet.getRow(0).getCell(0).setCellValue("Bộ Giao Thông Vận Tải");
//
//		sheet.getRow(1).getCell(0).setCellStyle(cellStyle1);
//		sheet.getRow(1).getCell(0).setCellValue("Tổng Cục Đường Bộ Việt Nam");
//
//		sheet.getRow(2).getCell(0).setCellStyle(cellStyle2);
//		sheet.getRow(2).getCell(0).setCellValue("Danh Sách Phương Tiện");
//
//		sheet.getRow(3).getCell(3).setCellStyle(cellStyle3);
//		Location provincial = provincicalService.findById(provincialId);
//		sheet.getRow(3).getCell(3).setCellValue("Sở GTVT: " + provincial.getName());
//
//		sheet.getRow(4).getCell(3).setCellStyle(cellStyle3);
//		sheet.getRow(4).getCell(3).setCellValue("Đơn vị cung cấp:");
//
//		sheet.getRow(5).getCell(3).setCellStyle(cellStyle3);
//		sheet.getRow(5).getCell(3).setCellValue("Đơn vị vận tải:");
//
//		sheet.getRow(3).getCell(7).setCellStyle(cellStyle3);
//		sheet.getRow(3).getCell(7).setCellValue("Loại Hình: Tất cả");
//
//		sheet.getRow(5).getCell(7).setCellStyle(cellStyle3);
//		sheet.getRow(5).getCell(7).setCellValue("Biển kiểm soát");
//	}
//
//	public void writerCompany(List<Company> companyList, String fileName, String sheetName, String code, String title)
//			throws FileNotFoundException, IOException {
//		XSSFWorkbook workbook = null;
//		XSSFSheet sheet = null;
//		try {
//			workbook = new XSSFWorkbook();
//			sheet = workbook.createSheet(sheetName);
//			Row row;
//			Cell cell;
//			// .. create row and cell
//			for (int i = 0; i < companyList.size() + 7; i++) {
//				row = sheet.createRow(i);
//				for (int j = 0; j < 9; j++) {
//					cell = row.createCell(j);
//					if (i > 5) {
//						cell.setCellStyle(cellBorder(sheet));
//						if (i == 6) {
//							// .. cellstyle for titleRow
//							cell.setCellStyle(fontColumn(sheet));
//						}
//					}
//				}
//			}
//			createCompanyHeaderRow(sheet, title, code);
//			createCompanyTitleRow(sheet, code);
//			int rowCount = 6;
//			int STT = 1;
//			for (Company company : companyList) {
////				row = sheet.createRow(++rowCount);
//				rowCount++;
//				mergeCompanyCell(sheet, rowCount);
//				Field[] fields = Company.class.getDeclaredFields();
//				for (Field field : fields) {
//					String fieldName = field.getName();
//					switch (fieldName) {
//					case "id":
//						// ..ko luu id ma luu STT
//						sheet.getRow(rowCount).getCell(0).setCellValue(STT++);
//						break;
//					case "name":
//						// .. ten cong ti
//						sheet.getRow(rowCount).getCell(1).setCellValue(company.getName());
//						break;
//					case "taxCode":
//						// .. ma so thue
//						sheet.getRow(rowCount).getCell(4).setCellValue(company.getTaxCode());
//						break;
//					case "provincial":
//						// ..so GTVT
//						Location provincial = provincicalService.findById(company.getProvincial().getId());
//						sheet.getRow(rowCount).getCell(6).setCellValue(provincial.getName());
//						break;
//					case "phoneNumber":
//						// .. dien thoai
//						sheet.getRow(rowCount).getCell(7).setCellValue(company.getPhoneNumber());
//						break;
//					default:
//						break;
//					}
//				}
//			}
//			// .. write file
//			try (FileOutputStream outputStream = new FileOutputStream(
//					resourceBundle.getString("excelWriter") + "/" + fileName)) {
//				workbook.write(outputStream);
//				log.info("write file: " + fileName);
//			}
//		} catch (Exception e) {
//			if (workbook != null) {
//				workbook.close();
//			}
//		}
//	}
//
//	private void createCompanyTitleRow(XSSFSheet sheet, String code) {
////		Row row = sheet.createRow(6); ?? ko tao moi row
//		mergeCompanyCell(sheet, 6);
////		CellStyle cellStyle = fontColumn(sheet);
//		int index = 0;
//		for (int i = 0; i < 9; i++) {
//			if (isCompanyColumn(i)) {
////				sheet.getRow(6).getCell(i).setCellStyle(cellStyle);
//				if (code.equals(Constant.TRANSPORT_UNIT_CODE)) {
//					// ..don vi van tai
//					sheet.getRow(6).getCell(i).setCellValue(Constant.TRANSPORTUNIT_FORMAT_FILE[index++]);
//				} else if (code.equals(Constant.DATA_TRANSPORT_UNIT_CODE)) {
//					// .. don vi truyen du lieu
//					sheet.getRow(6).getCell(i).setCellValue(Constant.DATATRANSPORT_FORMAT_FILE[index++]);
//				}
//			}
//		}
//	}
//
//	private void createCompanyHeaderRow(XSSFSheet sheet, String title, String code) {
//		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
//		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 8));
//		sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 7));
//		sheet.addMergedRegion(new CellRangeAddress(3, 3, 2, 4));
//		sheet.addMergedRegion(new CellRangeAddress(4, 4, 2, 3));
//
//		CellStyle cellStyle = fontMinistryOfTransport(sheet);
//		CellStyle cellStyle1 = fontVietnamRoadAdministration(sheet);
//		CellStyle cellStyle2 = fontTitle(sheet);
//		CellStyle cellStyle3 = fontTitleComplementary(sheet);
//
//		sheet.getRow(0).getCell(0).setCellStyle(cellStyle);
//		sheet.getRow(0).getCell(0).setCellValue("Bộ Giao Thông Vận Tải");
//
//		sheet.getRow(1).getCell(0).setCellStyle(cellStyle1);
//		sheet.getRow(1).getCell(0).setCellValue("Tổng Cục Đường Bộ Việt Nam");
//
//		sheet.getRow(2).getCell(0).setCellStyle(cellStyle2);
//		sheet.getRow(2).getCell(0).setCellValue(title);
//
//		sheet.getRow(3).getCell(2).setCellStyle(cellStyle3);
//		sheet.getRow(3).getCell(2).setCellValue("Sở GTVT: ");
//
//		sheet.getRow(4).getCell(2).setCellStyle(cellStyle3);
//		sheet.getRow(4).getCell(2).setCellValue("Đơn vị vận tải:");
//	}
//
//	public void mergeVehicleCell(Sheet sheet, int rowIndex) {
//		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 2, 3));
//		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 4, 5));
//		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 6, 8));
//		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 10, 11));
//	}
//
//	public void mergeCompanyCell(Sheet sheet, int rowIndex) {
//		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 1, 3));
//		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 4, 5));
//		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 7, 8));
//	}
//
//	public boolean isVehicleColumn(int i) {
//		if (i == 0 || i == 1 || i == 2 || i == 4 || i == 6 || i == 9 || i == 10)
//			return true;
//		return false;
//	}
//
//	public boolean isCompanyColumn(int i) {
//		if (i == 0 || i == 1 || i == 4 || i == 6 || i == 7)
//			return true;
//		return false;
//	}
//
//	// ..bo giao thong van tai
//	public CellStyle fontMinistryOfTransport(Sheet sheet) {
//		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
//		Font font = sheet.getWorkbook().createFont();
//		font.setFontName("Times New Roman");
//		font.setBold(false);
//		font.setFontHeightInPoints((short) 11);
//		cellStyle.setFont(font);
//		return cellStyle;
//	}
//
//	// ..tong cuc duong bo viet nam
//	public CellStyle fontVietnamRoadAdministration(Sheet sheet) {
//		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
//		Font font = sheet.getWorkbook().createFont();
//		font.setFontName("Times New Roman");
//		font.setBold(true);
//		font.setFontHeightInPoints((short) 11);
//		cellStyle.setFont(font);
//		return cellStyle;
//	}
//
//	// ..title excel
//	public CellStyle fontTitle(Sheet sheet) {
//		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
//		cellStyle.setAlignment(HorizontalAlignment.CENTER);
//		Font font = sheet.getWorkbook().createFont();
//		font.setFontName("Times New Roman");
//		font.setBold(true);
//		font.setFontHeightInPoints((short) 16);
//		cellStyle.setFont(font);
//		return cellStyle;
//	}
//
//	// .. font bo xung
//	public CellStyle fontTitleComplementary(Sheet sheet) {
//		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
//		Font font = sheet.getWorkbook().createFont();
//		font.setFontName("Times New Roman");
//		font.setBold(true);
//		font.setFontHeightInPoints((short) 11);
//		cellStyle.setFont(font);
//		return cellStyle;
//	}
//
//	// .. font column
//	public CellStyle fontColumn(Sheet sheet) {
//		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
//		// .. boi vi moi cell chi co the set 1 cellStyle len phai de gop the nay
//		cellStyle.setAlignment(HorizontalAlignment.CENTER);
//		cellStyle.setBorderBottom(BorderStyle.THIN);
//		cellStyle.setBottomBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
//		cellStyle.setBorderLeft(BorderStyle.THIN);
//		cellStyle.setLeftBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
//		cellStyle.setBorderRight(BorderStyle.THIN);
//		cellStyle.setRightBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
//		cellStyle.setBorderTop(BorderStyle.THIN);
//		cellStyle.setTopBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
//		Font font = sheet.getWorkbook().createFont();
//		font.setFontName("Times New Roman");
//		font.setBold(true);
//		font.setFontHeightInPoints((short) 10);
//		cellStyle.setFont(font);
//		return cellStyle;
//	}
//
//	// ..content
//	public CellStyle cellBorder(Sheet sheet) {
//		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
//		cellStyle.setBorderBottom(BorderStyle.THIN);
//		cellStyle.setBottomBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
//		cellStyle.setBorderLeft(BorderStyle.THIN);
//		cellStyle.setLeftBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
//		cellStyle.setBorderRight(BorderStyle.THIN);
//		cellStyle.setRightBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
//		cellStyle.setBorderTop(BorderStyle.THIN);
//		cellStyle.setTopBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
//		return cellStyle;
//	}
//}
