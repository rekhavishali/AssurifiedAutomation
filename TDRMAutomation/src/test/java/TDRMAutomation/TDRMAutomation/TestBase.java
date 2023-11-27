package TDRMAutomation.TDRMAutomation;

import static org.testng.Assert.fail;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TestBase {
	protected static WebDriver driver;
	  public String baseUrl;
	  private static JavascriptExecutor js;
	  private boolean acceptNextAlert = true;
	  protected static StringBuffer verificationErrors = new StringBuffer();
	  public ExtentReports report;
	  public ExtentTest logger;
	  
	  @BeforeSuite
	  public void setUpSuite()
	  {
		  ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/Reports.html"));
		  report=new ExtentReports();
		  report.attachReporter(extent);
		  
	  }
	  
	  
	@BeforeMethod(alwaysRun = true)
	  public void setUp() throws Exception {
	    //System.setProperty("webdriver.chrome.driver", "");
	    driver = new ChromeDriver();
	    baseUrl = "https://app-dev.assurified.com";
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    js = (JavascriptExecutor) driver;
	  }
	
	@AfterMethod(alwaysRun = true)
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	    
	    report.flush();
	  }
}




