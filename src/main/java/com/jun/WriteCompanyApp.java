package com.jun;

import com.jun.utils.writer.WriteDataToExcel;
import org.apache.log4j.Logger;

import java.io.IOException;

public class WriteCompanyApp {
    private static final Logger log = Logger.getLogger(WriteCompanyApp.class);
    public static void main(String[] args)  {
        //write data from DB to Excel file
        WriteDataToExcel writeDataToExcel = new WriteDataToExcel();
        try {
            writeDataToExcel.writeAllCompany("Tổng Hợp Danh Sách Phương Tiện.xlsx");
        } catch (IOException e) {
            log.info("---ERROR--- " + e.getMessage());
        }
    }
}
