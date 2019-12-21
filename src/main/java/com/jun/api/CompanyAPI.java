//package com.jun.api;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.List;
//
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//
//import com.jun.constant.Constant;
//import com.jun.model.Company;
//import com.jun.service.ICompanyService;
//import com.jun.service.impl.CompanyService;
//import com.jun.utils.WriteDataToExcel;
//
//@Path("/company/api")
//public class CompanyAPI {
//	ICompanyService companyService;
//	WriteDataToExcel wiDataToExcel;
//
//	public CompanyAPI() {
//		if(companyService == null)
//			companyService = new CompanyService();
//		if(wiDataToExcel == null)
//			wiDataToExcel = new WriteDataToExcel();
//	}
//
//	@GET
//	@Path("{fileName}/{companyType}")
//	@Produces("application/json; charset=UTF-8")
//	public String saveCompanyExcelFile(
//			@PathParam("fileName") String fileName,
//			@PathParam("companyType") String companyType) throws FileNotFoundException, IOException {
//		String code = "";
//		String title = "";
//		String sheetName = "";
//		if(companyType.toLowerCase().equals("don vi van tai")) {
//			code = Constant.TRANSPORT_UNIT_CODE;
//			title = Constant.TRANSPORT_UNIT_TITLE;
//			sheetName = Constant.TRANSPORT_UNIT_SHEETNAME;
//		} else if(companyType.toLowerCase().equals("don vi truyen du lieu")) {
//			code = Constant.DATA_TRANSPORT_UNIT_CODE;
//			title = Constant.DATA_TRANSPORT_UNIT_TITLE;
//			sheetName = Constant.DATA_TRANSPORT_UNIT_SHEETNAME;
//		}
//		List<Company> companyList = companyService.findByCode(code);
//		wiDataToExcel.writerCompany(companyList, fileName + ".xlsx", sheetName, code, title);
//		return "write file " + fileName + ".xlsx";
//	}
//}
