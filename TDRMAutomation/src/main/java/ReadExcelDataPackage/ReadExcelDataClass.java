package ReadExcelDataPackage;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelDataClass {
	
	public static void main (String args[]) throws Exception 
	{
		File src = new File("C:\\TDRM\\Test Data\\TestData.XLSX");
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb= new XSSFWorkbook(fis);
		
		XSSFSheet sheet1= wb.getSheetAt(0);
		
		String Username1 =sheet1.getRow(1).getCell(0).getStringCellValue();
		System.out.println("Data from Excel is " +Username1);
		

		String Password1 =sheet1.getRow(1).getCell(1).getStringCellValue();
		System.out.println("Data from Excel2 is " +Password1);
		
		wb.close();
		
	}


}
