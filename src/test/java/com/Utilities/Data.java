package com.Utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class Data{
	
	private static final Logger log = LogManager.getLogger(Data.class);
	
	@DataProvider(name="ValidExcelData",parallel=true)
	
	public Object[][] ValidData() throws IOException{
		Object[][] arrObj = getData("src/test/resources/Files.xlsx","Sheet1");
		return arrObj;
	}
	
	@DataProvider(name="InvalidExcelData")
	public Object[][] InValidData() throws IOException{
		Object[][] arrObj = getData("src/test/resources/Files.xlsx","Sheet2");
		return arrObj;
	}
	
	
	public String[][] getData(String fileName,String sheetName){
		
		String[][] data = null;
		
		try {
			
			FileInputStream fis = new FileInputStream(fileName);
			XSSFWorkbook workBook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workBook.getSheet(sheetName);
			XSSFRow row = sheet.getRow(0);
			int noOfRows = sheet.getPhysicalNumberOfRows();
			int noOfCell = row.getLastCellNum();
			
			Cell cell;
			
			data = new String[noOfRows-1][noOfCell];
			
			for(int i=1;i<noOfRows;i++) {
				for(int j=0;j<noOfCell;j++) {
					row = sheet.getRow(i);
					cell = row.getCell(j);
					data[i-1][j] = cell.getStringCellValue();
				}
			}
		}
		catch(Exception e) {
			log.warn("Exception : "+e);
		}
		
		return data;
		
	}
	
	
	

}
