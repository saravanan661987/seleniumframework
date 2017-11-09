package steps;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.junit.*;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
//import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
//import org.testng.annotations.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utility.Base;
import pages.HomePage;

import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class HomeTest extends Base {
	
	WebDriver driver;
	HomePage HomePage;
	//ExtentReports extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true);
	//ExtentTest Logger = extent.startTest("passTest");
	
	static ExtentReports report = new ExtentReports(System.getProperty("user.dir") +"/test-output/STMExtentReport.xml");
	ExtentTest logger; 
	
	private static JSONObject config;
	
  
	
	public void OpenBrowser() throws InterruptedException {		
		
		driver = getDriver();	
		Thread.sleep(1000);
		
		if(driver.getTitle().contains("Chanhassen"))		{	
			
			logger.log(LogStatus.PASS, "verify pageTitle" ,driver.getTitle() + " Page is Opened successfully");
		}else
		{
			logger.log(LogStatus.FAIL, "verify pageTitle",  "The expected page is not opened");
		}
	}
	
	
	@Test
	public void SampleTest1() throws SQLException, InterruptedException {
		//extent.startTest("TC01.1","This test is a positive login test for ParaBank");
		
		logger=report.startTest("TestCase1");
		OpenBrowser();	
		Assert.assertEquals(1, 1);
		//HomePage objHP = new HomePage();
		
		//List<Employee> emp = retriveValueFromDataBase();	
		//clickElement(objHP.getLnkClasses(), logger);		
		//clickElement(objHP.getLnkAllClasses(), logger);
	
		CloseBrowser();		
		
		
	}
	
	@Test
	public void SampleTest2() throws SQLException, InterruptedException {
		//extent.startTest("TC01.1","This test is a positive login test for ParaBank");
		
		logger=report.startTest("Testcase2");
		OpenBrowser();		
		Assert.assertEquals(1, 1);
		//HomePage objHP = new HomePage();
		//List<Employee> emp = retriveValueFromDataBase();	
		//clickElement(objHP.getLnkClasses(), logger);		
		//clickElement(objHP.getLnkAllClasses(), logger);		
		CloseBrowser();	
		
	}

	
	public void CloseBrowser() {				
		//driver.get("C:\\Report\\LearnAutomation.html");
		logger.log(LogStatus.PASS, "verify Browserclose" ,"Browser is closed successfully");
		report.endTest(logger);		
		driver.quit();
			
	}
	
	public static void SaveReport() {				
		//driver.get("C:\\Report\\LearnAutomation.html");
		report.flush();
		report.close();
		System.out.println("text");
	}

}
