package TDRMAutomation.TDRMAutomation;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;

public class AddCustomer extends TestBase {


  @Test (dataProvider= "excelData")
  public void testAdd(String Username, String Password, String CustomerName, String HubId, String Address, String City, String State, String Zip) throws Exception {
	
	    driver.get(baseUrl);
	    driver.manage().window().maximize();
	    driver.findElement(By.id("username")).click();
	    driver.findElement(By.id("username")).clear();
	    driver.findElement(By.id("username")).sendKeys(Username);
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys(Password);
	    driver.findElement(By.xpath("//button[@class='c64e877fc c55256a89 c283fdb19 c26d7a986 cebfbdd72']")).click();
	    Thread.sleep(3000);
    
   
    driver.findElement(By.xpath("//div[@class='apps-list']//div[@class='large-menu-item-name'][normalize-space()='admin']")).click(); //click Admin
    Thread.sleep(3000);
    driver.findElement(By.xpath("//div[@class='menu-item']")).click(); //Click  Customers
    
    driver.findElement(By.xpath("//div[@data-title='Add Customer']")).click(); // Click Add Customer
    driver.findElement(By.xpath("//div[@id='sideMenu']/div[2]/div[2]")).click();
    
    Thread.sleep(3000);

    driver.findElement(By.xpath("//input[@placeholder='Customer Name']")).sendKeys(CustomerName); 
    
    driver.findElement(By.xpath("//input[@placeholder='Hubspot ID']")).sendKeys(HubId);
    driver.findElement(By.xpath("//input[@placeholder='Address']")).sendKeys(Address);
    driver.findElement(By.xpath("//input[@placeholder='City']")).sendKeys(City);
    driver.findElement(By.xpath("//input[@placeholder='State']")).sendKeys(State);
    driver.findElement(By.xpath("//input[@placeholder='Zip']")).sendKeys(Zip);
    
    Thread.sleep(3000);

	 try {
		 WebElement chatBox = driver.findElement(By.xpath("//div[contains(@class,'Twilio-MessagingCanvas-default')]"));
		if(chatBox.isDisplayed())
		 driver.findElement(By.xpath("//div[contains(@class,'Twilio-Icon-Close')]")).click();
	 }
	 catch(Exception e)
	 {
    
    Thread.sleep(3000);
  
    driver.findElement(By.xpath("//button[normalize-space()='Add Customer']")).click();
   
	 }
    
    try 
    {
      assertTrue(isElementPresent(By.xpath("//div[contains(@class, 'customer-name')]")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
       
       driver.close();
    
	 }

private boolean isElementPresent(By xpath) {
	// TODO Auto-generated method stub
	return false;
}

@DataProvider(name ="excelData")
public Object[][] readExcelData() 
{
     ExcelDataConfig config  = new ExcelDataConfig("./TestData/TestData.XLSX");
     int rows = config.getRowCount(2);

    
    Object[][] data = new Object[rows][8] ;

    for (int i = 0; i < rows; i++) 
    {
      data[i][0] = config.getData(2,i,0);
      data[i][1] = config.getData(2,i,1);
      data[i][2] = config.getData(2,i,2);
      data[i][3] = config.getData(2,i,3);
      data[i][4] = config.getData(2,i,4);
      data[i][5] = config.getData(2,i,5);
      data[i][6] = config.getData(2,i,6);
      data[i][7] = config.getData(2,i,7);
      
      
        }
    

   return data;
}


}
