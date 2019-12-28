package com.jun.utils.reader;

import com.jun.model.Provider;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProviderExcel {

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("excelforlder");
    private static final Logger log = Logger.getLogger(ProviderExcel.class);

    public List<Provider> readExcelFile(String fileName) {
        List<Provider> result = new ArrayList<>();
        String SAMPLE_XLSX_FILE_PATH = resourceBundle.getString("excelFile") + "/" + fileName;
        FileInputStream inputStream = null;
        File file = new File(SAMPLE_XLSX_FILE_PATH);
        Workbook workbook = null;
        try {
            inputStream = new FileInputStream(file);
            workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            result = getProviderList(file, sheet, fileName);
        } catch (IOException e) {
            log.info(e.getMessage());
        } finally {
            // ..close file
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

    private List<Provider> getProviderList(File file, Sheet sheet, String fileName) {

        List<Provider> result = new ArrayList<>();
        int columnIndex;
        for (int i = 2; i < sheet.getLastRowNum() + 1; i++) {
            Row row = sheet.getRow(i);
            columnIndex = 0;
            Provider provider = new Provider();
            for (int j = 1; j < row.getLastCellNum() + 1; j++) {
                Cell cell = row.getCell(j);
                if (cell != null) {
                    Object cellValue = readCellValue(cell);
                    if (cell == null || cellValue == "" || cellValue == null)
                        continue;
                    columnIndex++;
                    switch (columnIndex) {
                        case 1:
                            // ..maso
                            provider.setCode((String.valueOf(cell.getNumericCellValue())).replace(".0", ""));
                            break;
                        case 2:
                            // ..ten
                            provider.setName(cell.getStringCellValue());
                            break;
                        case 3:
                            // ..dien thoai
                            String phoneNumber = String.valueOf(readCellValue(cell));

                            if (StringUtils.isNotBlank(phoneNumber) && !phoneNumber.equals("null")) {
                                phoneNumber = phoneNumber.replace(".", "").replace("-", "").replace(" ", "")
                                        .replace("E8", "").replace("E9", "");
                            }
                            provider.setPhoneNumber(phoneNumber);
                            break;
                        default:
                            break;
                    }
                }
            }
            result.add(provider);
        }

        return result;
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
