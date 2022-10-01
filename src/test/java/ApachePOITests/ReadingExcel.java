package ApachePOITests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.*;

public class ReadingExcel {
	public static void main(String[] args) throws IOException {
		
		String excelFilePath = "/Users/rishimalani/eclipse-ee/user-api-samples/TestIds.xlsx";
		
		//using file input stream class to open file in reading mode
		FileInputStream inputstream = new FileInputStream(excelFilePath);
		
		XSSFWorkbook workbook = new XSSFWorkbook(inputstream);
		
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		
		//Reading data Using For Loop
		
		//we need to find how many rows and columns are there
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(1).getLastCellNum();
		
		for(int r=0; r<= rows; r++) {
			XSSFRow row = sheet.getRow(r);
			for(int c=0; c<=cols; c++) {
				XSSFCell cell = row.getCell(c);
				
				//to store data we need to find the type of cell
				switch(cell.getCellType()) {
				case STRING: System.out.print(cell.getStringCellValue());
				break;
				case NUMERIC: System.out.print(cell.getNumericCellValue());
				break;
				case BOOLEAN: System.out.print(cell.getBooleanCellValue());
				break;
				 
				}	
				System.out.print(" | ");
			}
			System.out.println();
		}
		
		//Reading data using iterator
		
		//rowIterator function returns all the rows & we can iterate those rows
		Iterator iterator =  sheet.rowIterator();
		
		while(iterator.hasNext()) {
			XSSFRow row =  (XSSFRow) iterator.next();//typecasting returned row object
			
			Iterator cellIterator = row.cellIterator();
			
			while(cellIterator.hasNext()) {
				XSSFCell cell = (XSSFCell) cellIterator.next();
				
				switch(cell.getCellType()) {
				case STRING: System.out.print(cell.getStringCellValue());
				break;
				case NUMERIC: System.out.print(cell.getNumericCellValue());
				break;
				case BOOLEAN: System.out.print(cell.getBooleanCellValue());
				break;
				
				}	
				System.out.print(" | ");
			}
			System.out.println();
		}
	}
}
