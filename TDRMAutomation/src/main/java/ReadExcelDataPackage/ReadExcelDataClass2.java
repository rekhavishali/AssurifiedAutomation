package ReadExcelDataPackage;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadExcelDataClass2 {

    @DataProvider(name = "excelData")
    public Object[][] readExcelData() throws IOException {
        String excelFilePath = "C:\\TDRM\\Test Data\\TestData.XLSX";
        String sheetName = "FailureLogin";

        FileInputStream inputStream = new FileInputStream(excelFilePath);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rowCount - 1][colCount]; // Assuming the first row is header

        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
                data[i - 1][j] = cell.toString();
            }
        }

        workbook.close();
        inputStream.close();

        return data;
    }

    @Test(dataProvider = "excelData")
    public void excelDataDrivenTest(String username, String password) {
        // Your test logic here
        System.out.println("Username: " + username + ", Password: " + password);
        // Perform login and assertions
    }
}

