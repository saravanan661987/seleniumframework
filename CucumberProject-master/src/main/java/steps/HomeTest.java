package steps;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
//import org.testng.annotations.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utility.Base;
import pages.HomePage;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HomeTest extends Base {
	WebDriver driver;
	HomePage HomePage;
	//ExtentReports extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true);
	//ExtentTest Logger = extent.startTest("passTest");
	
	
	static ExtentReports report = new ExtentReports("C:\\Report\\LearnAutomation.html");
	ExtentTest logger; 

	
	public void OpenBrowser() {		
		
		driver = getDriver();	
		if(driver.getTitle().contains("Chanhassen"))		{	
			
			logger.log(LogStatus.PASS, "verify pageTitle" ,driver.getTitle() + " Page is Opened successfully");
		}else
		{
			logger.log(LogStatus.FAIL, "verify pageTitle",  "The expected page is not opened");
		}
	}
	
	
	@Test
	public void Testcase1() throws SQLException {
		//extent.startTest("TC01.1","This test is a positive login test for ParaBank");
		
		logger=report.startTest("TestCase1");
		OpenBrowser();		
		HomePage objHP = new HomePage();
		//List<Employee> emp = retriveValueFromDataBase();	
		clickElement(objHP.getLnkClasses(), logger);		
		clickElement(objHP.getLnkAllClasses(), logger);
	
		CloseBrowser();		
		
		
	}
	
	@Test
	public void Testcase2() throws SQLException {
		//extent.startTest("TC01.1","This test is a positive login test for ParaBank");
		
		logger=report.startTest("Testcase2");
		OpenBrowser();		
		HomePage objHP = new HomePage();
		//List<Employee> emp = retriveValueFromDataBase();	
		clickElement(objHP.getLnkClasses(), logger);		
		clickElement(objHP.getLnkAllClasses(), logger);
		
		CloseBrowser();
		
		
		
	}

	
	public void CloseBrowser() {				
		//driver.get("C:\\Report\\LearnAutomation.html");
		logger.log(LogStatus.PASS, "verify Browserclose" ,"Browser is closed successfully");
		report.endTest(logger);		
		driver.quit();
			
	}
	
	@AfterClass
	public static void SaveReport() {				
		//driver.get("C:\\Report\\LearnAutomation.html");
		report.flush();
		report.close();
		System.out.println("text");
	}

}
