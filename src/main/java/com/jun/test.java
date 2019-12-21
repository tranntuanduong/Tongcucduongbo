package com.jun;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class test {
	public static void main(String[] args) throws IOException {
		String SAMPLE_XLSX_FILE_PATH = "excelFile/Danh sách phương tiện - Ho CHi Minh.xlsx";
        FileInputStream inputStream = new FileInputStream(new File(SAMPLE_XLSX_FILE_PATH));
        System.out.println("abc");
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        System.out.println(sheet.getLastRowNum());
        System.out.println(sheet.getSheetName());
	}
}
