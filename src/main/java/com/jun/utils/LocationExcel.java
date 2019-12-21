package com.jun.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jun.model.Location;
import com.jun.service.ILocationService;
import com.jun.service.impl.LocationService;

public class LocationExcel {
	private static final Logger log = Logger.getLogger(LocationExcel.class);
	private ILocationService locationService;
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("excelforlder");

	public LocationExcel() {
		if (locationService == null)
			locationService = new LocationService();
	}

	public List<Location> readExcelFile(String fileName) {
		List<Location> result = new ArrayList<>();
		String SAMPLE_XLSX_FILE_PATH = resourceBundle.getString("excelFile") + "/" + fileName;
		FileInputStream inputStream = null;
		File file = new File(SAMPLE_XLSX_FILE_PATH);
		Workbook workbook = null;
		try {
			inputStream = new FileInputStream(file);
			workbook = new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			result = location(sheet, fileName);
		} catch (IOException e) {
			removeFileError(fileName, file);
		} finally {
			// ..close file
			try {
				workbook.close();
				inputStream.close();
				log.info("close inputStream and workbook");
			} catch (IOException e) {
				log.info(e.getMessage());
			}
		}
		return result;
	}

	private List<Location> location(Sheet sheet, String fileName) {
		List<Location> result = new ArrayList<Location>();
		int columnIndex;
		for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
			Row row = sheet.getRow(i);
			columnIndex = 0;
			Location location = new Location();
			for (int j = 1; j < row.getLastCellNum(); j++) {
				Cell cell = row.getCell(j);
				Object cellValue = readCellValue(cell);
				if (cell == null || cellValue == "" || cellValue == null)
					continue;

				columnIndex++;
				switch (columnIndex) {
				
				case 1:
					// ..ma so 
					//transportUnit.setTaxCode(cell.getStringCellValue().replace("-", ""));
					location.setCode((String.valueOf(readCellValue(cell))).replace(".0", ""));
					break;
				case 2:
					// ..ten
					location.setName(cell.getStringCellValue());
					break;
				case 3:
					// ..dien thoai
					break;
				default:
					break;
				}
			}
			result.add(location);
			
		}
		return result;
	}

	private void removeFileError(String fileName, File file) {
		// TODO Auto-generated method stub
		
	}
	public Object readCellValue(Cell cell) {
		Object cellValue = null;
		switch (cell.getCellTypeEnum()) {
		case BOOLEAN:
			cellValue = cell.getBooleanCellValue();
			break;
		case STRING:
			cellValue = cell.getRichStringCellValue().getString();
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				cellValue = cell.getDateCellValue();
			} else {
				cellValue = cell.getNumericCellValue();
			}
			break;
		case FORMULA:
			cellValue = cell.getCellFormula();
			break;
		case BLANK:
			break;
		default:

		}
		return cellValue;
	}

}
