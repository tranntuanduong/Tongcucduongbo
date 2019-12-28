package com.jun.utils.reader;

import com.jun.cacheData.CacheExcelData;
import com.jun.model.Company;
import com.jun.model.Location;
import com.jun.model.Provider;
import com.jun.model.Vehicle;
import com.jun.service.ICompanyService;
import com.jun.service.ILocationService;
import com.jun.service.IProviderService;
import com.jun.service.IVehicleService;
import com.jun.service.impl.CompanyService;
import com.jun.service.impl.LocationService;
import com.jun.service.impl.ProviderService;
import com.jun.service.impl.VehicleService;
import com.jun.shareData.ShareData;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.List;
import java.util.ResourceBundle;

//..chi can 1 thread
public class ThreadWriteData extends Thread {
    private ShareData shareData;
    private CacheExcelData cacheExcelData;

    public ThreadWriteData(ShareData shareData, CacheExcelData cacheExcelData) {
        this.shareData = shareData;
        this.cacheExcelData = cacheExcelData;
    }

    private static final Logger log = Logger.getLogger(ThreadWriteData.class);
    private IVehicleService vehicleService = new VehicleService();
    private LocationExcel locationExcel = new LocationExcel();
    private ProviderExcel providerExcel = new ProviderExcel();
    private ILocationService locationService = new LocationService();
    private IProviderService providerService = new ProviderService();
    private VehicleExcel vehicleExcel = new VehicleExcel();
    private CompanyExcel companyExcel = new CompanyExcel();
    private ICompanyService companyService = new CompanyService();
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("excelforlder");

    @Override
    public void run() {

        log.info("thread write data -- start ");
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (shareData.getNewList() != null) {
                String fileName = shareData.getNewList().poll();
                File file = new File(resourceBundle.getString("excelFile") + "/" + fileName);
                if (fileName != null) {
                    if (fileName.toUpperCase().contains("DANH SÁCH TỈNH")) {
                        List<Location> locationList = locationExcel.readExcelFile(fileName);
                        locationService.saveList(locationList);
                        // ..doc de day da
                    } else if (fileName.toUpperCase().contains("DS ĐƠN VỊ TRUYỀN TẢI")) {
                        List<Provider> providerList = providerExcel.readExcelFile(fileName);
                        providerService.saveList(providerList);
                    } else if (fileName.toUpperCase().contains("DANH SÁCH PHƯƠNG TIỆN")) {
                        List<Vehicle> vehicleList = vehicleExcel.readExcelFile(fileName, cacheExcelData);
                        vehicleService.saveList(vehicleList);
                    } else if (fileName.toUpperCase().contains("DANH SÁCH ĐƠN VỊ VẬN TẢI")) {
                        List<Company> companyList = companyExcel.readExcelFile(fileName, cacheExcelData);
                        companyService.saveList(companyList);
                    } else {
                        log.info("Error: file dat ten khong dung chuan: " + fileName);
                    }
                    if (file.exists()) {
                        File destination = new File(resourceBundle.getString("excelReaded") + "/" + fileName);
                        file.renameTo(destination);
                        log.info("reNameTo: " + fileName);
                        file.delete();
                        shareData.getCheckList().poll();
                    }
                }
            }
        }
    }
}
