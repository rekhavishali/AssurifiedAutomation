
package TDRMAutomation.TDRMAutomation;

import java.util.regex.Pattern;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.*;
import static org.testng.Assert.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

public class LoginFailure extends TestBase {

	@Test(dataProvider = "excelData")

	public void testLoginUnsuccessful(String Username, String Password) throws Exception {

		logger = report.createTest("Assurified Login Failure");

		driver.get(baseUrl);
		driver.manage().window().maximize();

		logger.info("Login into application");
		
		
		Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("./src/test/java/TDRMAutomation/TDRMAutomation/locaters.properties");
        properties.load(fis);

        // Use the XPaths from the properties file
        String usernameXPath = properties.getProperty("login.username.xpath");
        String passwordXPath = properties.getProperty("login.password.xpath");
        String submitButtonXPath = properties.getProperty("login.submit.button.xpath");

        // Navigate to the login page
        driver.get(baseUrl);

        // Perform actions using the XPaths
        driver.findElement(By.xpath(usernameXPath)).sendKeys(Username);
        driver.findElement(By.xpath(passwordXPath)).sendKeys(Password);
        driver.findElement(By.xpath(submitButtonXPath)).click();


		
		Thread.sleep(5000);
		
		try {
			List<WebElement> errorMessage = driver.findElements(By.id("error-element-password"));
			if (!(errorMessage.isEmpty())&&errorMessage.get(0).isDisplayed())
			{
				logger.pass("Login Failed as the crendentails not valid.Testcase Pass");
				System.out.println("Error message is displayed and Testcase Pass");
			
			
			}
			else
			{
				logger.fail("Error message is not displayed. Testcase Failed");
				Assert.fail();
				System.out.println("Error message is not displayed. Testcase Failed");
			}
		}
			catch (NoSuchElementException e) 
		{
					// Handle the case where the element is not found
					System.out.println("Element not found: " + e.getMessage());
		}
		
		

		
		driver.close();
	}
	
	
	
	@DataProvider(name = "excelData")
	public Object[][] readExcelData() {
		ExcelDataConfig config = new ExcelDataConfig("./TestData/TestData.XLSX");
		int rows = config.getRowCount(1);

		Object[][] data = new Object[rows][2];

		for (int i = 0; i < rows; i++) {
			data[i][0] = config.getData(1, i, 0);
			data[i][1] = config.getData(1, i, 1);

		}

		return data;
	}

}
