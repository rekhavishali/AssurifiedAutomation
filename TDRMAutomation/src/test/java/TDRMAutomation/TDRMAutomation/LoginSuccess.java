package TDRMAutomation.TDRMAutomation;

import java.util.regex.Pattern;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;

import com.aventstack.extentreports.Status;

import junit.framework.Assert;

//import com.aventstack.extentreports.util.Assert;

import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;

//import org.apache.commons.io.FileUtils;

public class LoginSuccess extends TestBase {

	@Test(dataProvider = "excelData")

	public void testLoginSuccessful(String Username, String Password) throws Exception {
		
		logger = report.createTest("Assurified Login Success");



        // Navigate to the login page
		driver.get(baseUrl);
		
		   driver.manage().window().maximize();
		   
Thread.sleep(5000);
		   logger.info("Login into application");
		   
			Properties properties = new Properties();
	        FileInputStream fis = new FileInputStream("./src/test/java/TDRMAutomation/TDRMAutomation/locaters.properties");
	        properties.load(fis);

	        // Use the XPaths from the properties file
	        String usernameXPath = properties.getProperty("login.username.xpath");
	        String passwordXPath = properties.getProperty("login.password.xpath");
	        String submitButtonXPath = properties.getProperty("login.submit.button.xpath");
	        
	        driver.findElement(By.xpath(usernameXPath)).sendKeys(Username);
	        driver.findElement(By.xpath(passwordXPath)).sendKeys(Password);
	        driver.findElement(By.xpath(submitButtonXPath)).click();



		Thread.sleep(5000);


		String ActualHomescreenTitle = driver.getTitle();
		String ExpectedTitle = "Welcome Back!";
		

		Thread.sleep(3000);
		
		try {
		   
        logger.log(Status.INFO, "Actual Title: " + ActualHomescreenTitle);
        logger.log(Status.INFO, "Expected Title: " + ExpectedTitle);
        
    
    	
        if (ActualHomescreenTitle.equals(ExpectedTitle))
        {
         logger.pass("Page titles Matches. Login Succes. Testcase Pass");
        }
        else
         {
        	logger.fail("Page titles doesnot match. Login unsuccessful.Testcase Fail");
        	Assert.fail("Login Fail");
        	
         }
    	}
		catch (Exception e) {
            // Handle the exception (e.g., print the stack trace)
            e.printStackTrace();
 
		} 
    	
    	    
	
 finally {
	 
	 String ProfilePicxpath =properties.getProperty("login.ProfileText.xpath");
     String AccountSettingxpath = properties.getProperty("login.accountSetting.xpath");
		driver.findElement(By.xpath(ProfilePicxpath)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(AccountSettingxpath)).click();
		Thread.sleep(2000);

		driver.close();
 }
	}

	@DataProvider(name = "excelData")
	public Object[][] readExcelData() {
		ExcelDataConfig config = new ExcelDataConfig("./TestData/TestData.XLSX");
		int rows = config.getRowCount(0);

		Object[][] data = new Object[rows][2];

		for (int i = 0; i < rows; i++) {
			data[i][0] = config.getData(0, i, 0);
			data[i][1] = config.getData(0, i, 1);

		}

		return data;
	}

}
