package TDRMAutomation.TDRMAutomation;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ExtentReport {
	
	@Test
	public void loginTest()
	{
		System.out.println("Login to Amazon");
		ExtentHtmlReporter reporter = new ExtentHtmlReporter("./Reports/Reports.html");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		ExtentTest logger = extent.createTest("loginTest");
		logger.log(Status.INFO, "Login to Amazon");
		logger.log(Status.PASS, "Title verified");
		ExtentTest logger2=extent.createTest("Logoff Test");
		logger2.log(Status.PASS, "Title Verified");
		extent.flush();
		
		
	}

        }

