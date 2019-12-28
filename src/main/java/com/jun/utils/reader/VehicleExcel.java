package com.jun.utils.reader;

import com.jun.cacheData.CacheExcelData;
import com.jun.constant.Constant;
import com.jun.model.*;
import com.jun.service.*;
import com.jun.service.impl.*;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class VehicleExcel {

    private static final Logger log = Logger.getLogger(VehicleExcel.class);
    private ICompanyService companyService;
    private ILocationService locationService;
    private ITransportationService transportationService;
    private IVehicleService vehicleService;
    private IProviderService providerService;

    public VehicleExcel() {
        if (companyService == null)
            companyService = new CompanyService();
        if (locationService == null)
            locationService = new LocationService();
        if (transportationService == null)
            transportationService = new TransportationService();
        if (vehicleService == null)
            vehicleService = new VehicleService();
        if (providerService == null)
            providerService = new ProviderService();
    }

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("excelforlder");

    public List<Vehicle> readExcelFile(String fileName, CacheExcelData cacheExcelData) {
        List<Vehicle> result = new ArrayList<>();
        String SAMPLE_XLSX_FILE_PATH = resourceBundle.getString("excelFile") + "/" + fileName;
        FileInputStream inputStream = null;
        File file = new File(SAMPLE_XLSX_FILE_PATH);
        XSSFWorkbook workbook = null;
        try {
            inputStream = new FileInputStream(file);
            log.info("Mo workBook:" + fileName);
            workbook = new XSSFWorkbook(inputStream);
            log.info("da mo thanh cong");
            Sheet sheet = workbook.getSheetAt(0);
            sheet.getSheetName();
            result = vehicle(file, sheet, fileName, cacheExcelData);
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

    public List<Vehicle> vehicle(File file, Sheet sheet, String fileName, CacheExcelData cacheExcelData) {
        List<Vehicle> vehicleList = new ArrayList<>();
        int columnIndex;
        boolean checkFormatField = true;
        Row row;
        Cell cell;
        // ..check format filed
        row = sheet.getRow(7);
        columnIndex = -1;
        for (int i = 0; i < row.getLastCellNum(); i++) {
            cell = row.getCell(i);
            if (cell == null || cell.getStringCellValue().equals(""))
                continue;
            columnIndex++;
            if (!cell.getStringCellValue().equals(Constant.VEHICLE_FORMAT_FILE[columnIndex])) {
                checkFormatField = false;
                log.info("dinh dang file " + fileName + " khong dung");
            }
        }
        // get provincical
        row = sheet.getRow(3);
        cell = row.getCell(3);
        String locationName = cell.getStringCellValue().split(": ")[1];
        Location location = null;
        location = locationService.findByName(locationName);
        cacheExcelData.setCompanyListStr(companyService.findAllNameAndId(location.getId()));
        if (location != null && checkFormatField) {
            for (int i = 8; i < sheet.getLastRowNum() + 1; i++) {
                row = sheet.getRow(i);
                Vehicle vehicle = null;
                // ..load cell
                for (int j = 1; j < row.getLastCellNum(); j++) {
                    cell = row.getCell(j);
                    Object cellValue = null;
                    if (cell != null) {
                        cellValue = readCellValue(cell);
                    }
                    if (cellValue == "" || cellValue == null)
                        continue;
                    int vehicleColumnTitle = vehicleColumnTitle(j);
                    switch (vehicleColumnTitle) {
                        case 1:
                            // ..bien kiem soat
                            if (!cacheExcelData.getLicensePlateListStr().contains(cell.getStringCellValue())) {
                                // neu bien so xe khong trong du lieu cache
                                vehicle = new Vehicle();
                                vehicle.setLocationId(location.getId());
                                vehicle.setLicensePlate(cell.getStringCellValue());
                                cacheExcelData.getLicensePlateListStr().add(cell.getStringCellValue());
                            } else {
                                // cap nhat ???? hoac bo qua(doi voi file doc lan dau)
                                vehicle = vehicleService.findByLicensePlate(cell.getStringCellValue());
                                if (vehicle != null) {
                                    vehicle.setLocationId(location.getId());
                                } else {
                                    //file dang doc lan dau
                                   int  a = 4;
                                }
                            }

                            break;
                        case 2:
                            // ..loai hinh
                            if (vehicle != null) {
                                Transportation transportation = null;
                                Integer transportationId = null;
                                if (!cacheExcelData.getTransportationListStr().containsKey(cell.getStringCellValue())) {
                                    transportation = transportationService.findByName(cell.getStringCellValue());
                                    if (transportation == null) {
                                        transportation = new Transportation();
                                        transportation.setName(cell.getStringCellValue());
                                        transportationId = transportationService.save(transportation);
                                    } else {
                                        transportationId = transportation.getId();
                                    }
                                    cacheExcelData.getTransportationListStr().put(cell.getStringCellValue(), transportationId);
                                } else {
                                    transportationId = cacheExcelData.getTransportationListStr().get(cell.getStringCellValue());
                                }
                                vehicle.setTransportationId(transportationId);
                            }
                            break;
                        case 3:
                            // ..don vi van tai
                            if (vehicle != null) {
                                Company company = null;
                                Integer companyId = null;
                                if (!cacheExcelData.getCompanyListStr().containsKey(cell.getStringCellValue())) {
                                    company = new Company();
                                    company.setName(cell.getStringCellValue());
                                    company.setLocationId(location.getId());
                                    companyId = companyService.save(company);
                                    vehicle.setCompanyId(companyId);
                                    cacheExcelData.getCompanyListStr().put(cell.getStringCellValue(), companyId + "");
                                } else {
                                    //neu ten da co trong cache
                                    HashMap<String, String> companyListStr = cacheExcelData.getCompanyListStr();
                                    if (companyListStr.get(cell.getStringCellValue()).contains(",")) {
                                        //truong hop unKown
                                        vehicle.setUnKnown(companyListStr.get(cell.getStringCellValue()));
                                    } else {
                                        //truong hop binh thuong -> luu vao companyId
                                        vehicle.setCompanyId(Integer.parseInt(companyListStr.get(cell.getStringCellValue())));
                                    }
                                }
                            }
                            break;
                        case 4:
                            // ..don vi truyen du lieu
                           if (vehicle != null) {
                               Provider provider = null;
                               Integer providerId = null;
                               if (!cacheExcelData.getProviderListStr().containsKey(cell.getStringCellValue())) {
                                   provider = providerService.findByName(cell.getStringCellValue());
                                   if (provider == null) {
                                       provider = new Provider();
                                       provider.setName(cell.getStringCellValue());
                                       providerId = providerService.save(provider);
                                   } else {
                                       providerId = provider.getId();
                                   }
                                   cacheExcelData.getProviderListStr().put(cell.getStringCellValue(), providerId);
                               } else {
                                   providerId = cacheExcelData.getProviderListStr().get(cell.getStringCellValue());
                               }
                               vehicle.setProviderId(providerId);
                           }
                            break;
                        case 5:
                            // ..tai trong
                            if (vehicle != null) {
                                String weightStr = "";
                                try {
                                    weightStr = String.valueOf(readCellValue(cell));
                                    float weight = Float.parseFloat(weightStr.replace(",", "."));
                                    vehicle.setWeight(weight);
                                } catch (Exception e) {
                                    String[] weightStrArr = weightStr.replace(".", "-").split("-");
                                    float weight = Float.valueOf(weightStrArr[0] + "." + weightStrArr[1]);
                                    vehicle.setWeight(weight);
                                }
                            }
                            break;
                        case 6:
                            // ..so ghe
                            if (vehicle != null) {
                                Integer numberSeat = Integer
                                        .parseInt(cell.getStringCellValue().replace(",", "").replace(".", ""));
                                vehicle.setPersonCarriedNumber(numberSeat);
                            }
                    }

                }
                if (vehicle != null) {
                    vehicleList.add(vehicle);
                }
            }
        } else {
            removeFileError(fileName, file);
        }
        return vehicleList;
    }

    private static void removeFileError(String fileName, File file) {

        log.info("xay ra loi khi doc file: " + fileName);
        if (file.exists()) {
            File destination = new File(resourceBundle.getString("excelError") + "/" + fileName);
            file.renameTo(destination);
            System.out.println("reNameTo: " + fileName);
            file.delete();
        }
    }

    private static Integer vehicleColumnTitle(int cellNum) {
        Integer vehicleColumn = null;
        if (cellNum == 1)
            vehicleColumn = 1;
        if (cellNum == 2)
            vehicleColumn = 2;
        if (cellNum == 4)
            vehicleColumn = 3;
        if (cellNum == 6)
            vehicleColumn = 4;
        if (cellNum == 9)
            vehicleColumn = 5;
        if (cellNum == 10)
            vehicleColumn = 6;
        return vehicleColumn;
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
}
