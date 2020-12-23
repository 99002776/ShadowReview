
package com.boat.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.boat.bean.BoatEvent;
import com.boat.bean.BoatLocation;





@Component
public class BoatUtil {

	@Value("${excelFile.url}") 
	private String excelFileUrl;
	
	public static int rowcount=0;
	public static int rowcount1=0;
	
	
	/**
	 * getRandomNumber provides randomly generated double number. 
	 * 
	 */
	public Double getRandomNumberDouble(int min, int max) {
		DecimalFormat df = new DecimalFormat("###.##");
		Double randomNumber = (Math.random() * (max - min + 1) + min);
		return Double.parseDouble(df.format(randomNumber));
	}
	
	/**
	 * Creating random number for boat data.
	 * @param min
	 * @param max
	 * @return
	 */
	public Integer getRandomNumber(int min, int max) {
		Integer randomNumber = (int)(Math.random() * (max - min + 1) + min);
		return randomNumber;
	}
	
//	private Integer calculateSpeedFromRpm(Double double1,String vin) {
//		Integer diameter =getRandomNumber(29,31) ;	
//  	int speed= (int) (((Math.PI ) * (double1 )* (diameter)/1056) * 1.609344);
//  	if(speed<=200)
//  	{
//  		return speed;
//  	}
//  	else
//  		return getRandomNumber(150,200);
//	}
	
	

//	/**
//	 * readDataFromExcel Method read location data from ExcelFile for latitude
//	 */
//	public Double readLatitude()throws IOException{
//		
//		try {
//			 InputStream file= (InputStream) this.getClass().getResourceAsStream(excelFileUrl);
//	    	  @SuppressWarnings("resource")
//			XSSFWorkbook myExcelBook = new XSSFWorkbook(file);
//	    	   XSSFSheet myExcelSheet =myExcelBook.getSheetAt(0); 
//	    	   if(rowcount<98)
//				{ 
//					Row row = myExcelSheet.getRow(rowcount); 
//					if(row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){ 
//						double latitude = row.getCell(0).getNumericCellValue();
//						return latitude;	
//					}
//				}
//	    	   else
//				{
//					Row row = myExcelSheet.getRow(rowcount); 
//					if(row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){ 
//						double latitude = row.getCell(0).getNumericCellValue(); 
//						return latitude;	
//					}
//					rowcount=0;	
//				}
//				rowcount++;
//			}catch(FileNotFoundException e) {
//				System.out.println("Exception while reading Excel "+e);
//			}
//		return null;
//	}
//	
//	
//	
//	
//	
//	/**
//	 * readDataFromExcel Method read location data from ExcelFile for longitude
//	 */
//	public Double readLongitude()throws IOException{
//		try {
//			 InputStream file= (InputStream) this.getClass().getResourceAsStream(excelFileUrl);
//	    	  @SuppressWarnings("resource")
//			XSSFWorkbook myExcelBook = new XSSFWorkbook(file);
//	    	   XSSFSheet myExcelSheet =myExcelBook.getSheetAt(0); 
//				if(rowcount1<98)
//				{ 
//					Row row = myExcelSheet.getRow(rowcount1); 
//					if(row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
//						double longitude= row.getCell(1).getNumericCellValue(); 
//						return longitude;
//					}
//					
//				}
//				else
//				{
//					Row row = myExcelSheet.getRow(rowcount1); 
//					
//					if(row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
//						double longitude= row.getCell(1).getNumericCellValue(); 
//						return longitude;
//					}
//					rowcount1=0;	
//					
//				}
//				rowcount1++;
//			}catch(FileNotFoundException e) {
//				System.out.println("Exception while reading Excel "+e);
//			}
//		return null;
//		}
//	
	
	/**
	 * readDataFromExcel Method read location data from ExcelFile
	 */
	public void readDataFromExcel(BoatLocation vehicle) throws IOException
	{  
		try {
		 InputStream file= (InputStream) this.getClass().getResourceAsStream(excelFileUrl);
    	  XSSFWorkbook myExcelBook = new XSSFWorkbook(file);
    	   XSSFSheet myExcelSheet =myExcelBook.getSheetAt(0); 
			if(rowcount<98)
			{ 
				Row row = myExcelSheet.getRow(rowcount); 
				if(row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){ 
					double latitude = row.getCell(0).getNumericCellValue(); 
					vehicle.setLatitude(latitude);
				}
				if(row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
					double longitude= row.getCell(1).getNumericCellValue(); 
					vehicle.setLongitude(longitude);
				}
			}
			else
			{
				Row row = myExcelSheet.getRow(rowcount); 
				if(row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){ 
					double latitude = row.getCell(0).getNumericCellValue(); 
					vehicle.setLatitude(latitude);
				}
				if(row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
					double longitude= row.getCell(1).getNumericCellValue(); 
					vehicle.setLongitude(longitude);
				}
				rowcount=0;	
				//logger.info("rowcount of simulation" +rowcount);
			}
			rowcount++;
		}catch(FileNotFoundException e) {
			System.out.println("Exception while reading Excel "+e);
		}
	}
	
	
	/**
	 * readDataFromExcel Method read location data from ExcelFile for event data class
	 */
	public void readDataFromExcel(BoatEvent vehicle) throws IOException
	{  
		try {
		 InputStream file= (InputStream) this.getClass().getResourceAsStream(excelFileUrl);
    	  XSSFWorkbook myExcelBook = new XSSFWorkbook(file);
    	   XSSFSheet myExcelSheet =myExcelBook.getSheetAt(0); 
			if(rowcount<98)
			{ 
				Row row = myExcelSheet.getRow(rowcount); 
				if(row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){ 
					double latitude = row.getCell(0).getNumericCellValue(); 
					vehicle.setLatitude(latitude);
				}
				if(row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
					double longitude= row.getCell(1).getNumericCellValue(); 
					vehicle.setLongitude(longitude);
				}
			}
			else
			{
				Row row = myExcelSheet.getRow(rowcount); 
				if(row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){ 
					double latitude = row.getCell(0).getNumericCellValue(); 
					vehicle.setLatitude(latitude);
				}
				if(row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
					double longitude= row.getCell(1).getNumericCellValue(); 
					vehicle.setLongitude(longitude);
				}
				rowcount=0;	
				//logger.info("rowcount of simulation" +rowcount);
			}
			rowcount++;
		}catch(FileNotFoundException e) {
			System.out.println("Exception while reading Excel "+e);
		}
	}
	
	
	
	/**
	 * creating Random HIN number for boats
	 * @param n
	 * @return
	 */
	static String getAlphaNumericString(int n) 
    { 
  
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
   
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    } 
}
