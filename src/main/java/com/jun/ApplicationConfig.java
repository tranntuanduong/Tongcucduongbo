package com.jun;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.jun.cacheData.CacheExcelData;
import com.jun.service.ICompanyService;
import com.jun.service.ILocationService;
import com.jun.service.IProviderService;
import com.jun.service.ITransportationService;
import com.jun.service.IVehicleService;
import com.jun.service.impl.CompanyService;
import com.jun.service.impl.LocationService;
import com.jun.service.impl.ProviderService;
import com.jun.service.impl.TransportationService;
import com.jun.service.impl.VehicleService;
import com.jun.shareData.ShareData;
import com.jun.utils.ThreadCheckNewFile;
import com.jun.utils.ThreadWriteData;

public class ApplicationConfig {

	public static void main(String[] args) throws FileNotFoundException, IOException {
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
