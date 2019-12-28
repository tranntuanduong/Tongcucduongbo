package com.jun;

import com.jun.cacheData.CacheExcelData;
import com.jun.model.Company;
import com.jun.service.*;
import com.jun.service.impl.*;
import com.jun.shareData.ShareData;
import com.jun.utils.reader.ThreadCheckNewFile;
import com.jun.utils.reader.ThreadWriteData;
import com.jun.utils.writer.WriteDataToExcel;

import java.io.IOException;
import java.util.List;

public class ApplicationConfig {

	public static void main(String[] args) {
		ICompanyService companyService = new CompanyService();
		ILocationService locationService = new LocationService();
		IVehicleService vehicleService = new VehicleService();
		IProviderService providerService = new ProviderService();
		ITransportationService transportationService = new TransportationService();

		CacheExcelData cacheExcelData = new CacheExcelData();
		cacheExcelData.setTransportationListStr(transportationService.findAllNameAndId());
		cacheExcelData.setProviderListStr(providerService.findAllNameAndId());
		cacheExcelData.setLocationListStr(locationService.findAllNameAndId());
		cacheExcelData.setLicensePlateListStr(vehicleService.findAllLicensePlate());

		ShareData shareData = new ShareData();
		ThreadCheckNewFile threadCheckNewFile = new ThreadCheckNewFile(shareData);
		ThreadWriteData threadWriteData = new ThreadWriteData(shareData, cacheExcelData);

		threadCheckNewFile.start();
		threadWriteData.start();
	}
}
