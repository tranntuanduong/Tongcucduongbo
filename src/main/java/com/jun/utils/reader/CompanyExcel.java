package com.jun.utils.reader;

import com.jun.cacheData.CacheExcelData;
import com.jun.model.Company;
import com.jun.service.ICompanyService;
import com.jun.service.impl.CompanyService;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class CompanyExcel {
    private static final Logger log = Logger.getLogger(CompanyExcel.class);
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("excelforlder");
    private ICompanyService companyService;

    public CompanyExcel() {
        if (companyService == null)
            companyService = new CompanyService();
    }

    public List<Company> readExcelFile(String fileName, CacheExcelData cacheExcelData) {
        List<Company> result = new ArrayList<>();
        String SAMPLE_XLSX_FILE_PATH = resourceBundle.getString("excelFile") + "/" + fileName;
        FileInputStream inputStream = null;
        File file = new File(SAMPLE_XLSX_FILE_PATH);
        Workbook workbook = null;
        try {
            inputStream = new FileInputStream(file);
            workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            result = company(sheet, fileName, cacheExcelData);
        } catch (IOException e) {
            removeFileError(fileName, file);
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                log.info("close inputStream and workbook");
            } catch (IOException e) {
                log.info(e.getMessage());
            }
        }
        return result;
    }

    private List<Company> company(Sheet sheet, String fileName, CacheExcelData cacheExcelData) {
        List<Company> result = new ArrayList<Company>();
        //de kiem tra xem ten va code da ton tai hay chua
        //HashMap<String, String> companyList = new HashMap<>();
        HashMap<String, String> companyList = companyService.findAllNameAndTaxtNumber();
        for (int i = 7; i < sheet.getLastRowNum() + 1; i++) {
            Row row = sheet.getRow(i);
            Company company = new Company();
            for (int j = 1; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                Object cellValue = readCellValue(cell);
                if (cell == null || cellValue == "" || cellValue == null)
                    continue;
                int companyTitle = companyTitle(j);
                switch (companyTitle) {
                    case 1:
                        // ..ten
                        company.setName(cell.getStringCellValue().replace("\"", "").replace("\'", ""));
                        break;
                    case 2:
                        // ..ma so thue
                        String taxNumber = String.valueOf(readCellValue(cell)).replace("\'", "")
                                .replace(".", "").replace(" ", "")
                                .replace(",", "");
                        // repalace . ->
                        company.setTaxNumber(taxNumber);
                        break;
                    case 3:
                        // .. location
                        Integer locationId = cacheExcelData.getLocationListStr().get(cell.getStringCellValue());
                        company.setLocationId(locationId);
                        break;
                    case 4:
                        String phoneNumber = String.valueOf(readCellValue(cell));
                        company.setPhoneNumber(phoneNumber.replace(".", "").replace(" ", "").replace("\'", "")
                                .replace(";", "-").replace("Fax", "").replace("E9", "").replace("E8", ""));
                        break;
                    default:
                        break;
                }
            }
            //..kiem tra name va taxtNumber co trung giong nhau ko
            String taxtNumber1 = company.getTaxNumber() == null ? "" : company.getTaxNumber();
            String value = companyList.get(company.getName() + "-LocationId" + company.getLocationId());
            String taxtNumber2 = value == null ? "" : value;
            if (!taxtNumber2.contains(taxtNumber1)) {
                result.add(company);
                if (!taxtNumber2.equals("")) {
                    taxtNumber2 = "," + taxtNumber2;
                }
                taxtNumber2 = taxtNumber1 + taxtNumber2;
            }
            //ex: trung dung do moc-LocationId1
            companyList.put(company.getName() + "-LocationId" + company.getLocationId(), taxtNumber2);
        }
        return result;
    }

    private Object readCellValue(Cell cell) {
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

    private void removeFileError(String fileName, File file) {
        // TODO Auto-generated method stub
    }

    private static Integer companyTitle(int cellNum) {
        Integer vehicleColumn = null;
        if (cellNum == 1)
            vehicleColumn = 1;
        if (cellNum == 4)
            vehicleColumn = 2;
        if (cellNum == 6)
            vehicleColumn = 3;
        if (cellNum == 7)
            vehicleColumn = 4;
        return vehicleColumn;
    }

}
