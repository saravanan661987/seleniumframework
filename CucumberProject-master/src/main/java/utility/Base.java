package utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.LogStatus;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Base {
	public static WebDriver driver;
	static WebDriverWait wait;
	static File f1 = new File("./JSON/Configuration.json");
	static String objfile = "./objRepo/AEM.xml";
	
//	static ExtentTest Logger = extent.startTest("passTest");
	public static WebDriver getDriver() {
		JSONObject jsonObject = JSONReadFromFile();
		String browser = (String) jsonObject.get("browser");

		File f = new File("./driver");
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", f.getAbsolutePath() + "/chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", f.getAbsolutePath() + "/geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (browser.equals("ie")) {
			System.setProperty("webdriver.ie.driver", f.getAbsolutePath() + "/IEDriverServer.exe");
			driver = new InternetExplorerDriver();

		}

		driver.manage().window().maximize();
		driver.get((String) jsonObject.get("url"));
		return driver;
	}

	public static boolean elementToBeVisible(WebDriver driver, int time,
			WebElement element) {
		boolean flag = false;
		try {
			wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.visibilityOf(element));
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static boolean alertIsPresent(WebDriver driver, int time) {
		boolean flag = false;
		try {
			wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.alertIsPresent());
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static boolean elementToBeClickable(WebDriver driver, int time,
			WebElement element) {
		boolean flag = false;
		try {
			wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static boolean elementFound(WebDriver driver, int time,
			WebElement element) {
		boolean res = false;
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		try {

			res = element.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return res;

	}

	public static boolean elementFound(WebElement element) {
		boolean res = false;
		try {
			res = element.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return res;
	}

	public static void setText(WebElement element, String name) {
		if (name != null && elementFound(element)) {
			element.clear();
			element.sendKeys(name);
		}

	}

	public static String getText(WebElement element) {
		String name = null;
		if (elementFound(element)) {
			name = element.getAttribute("value");
		}
		return name;

	}

	public static void clickElement(WebElement element, ExtentTest logger) {
		try
		{
		if (elementFound(element)) {
			String Eletext = element.getText();
			element.click();
			logger.log(LogStatus.PASS, "Element Click", Eletext + " link is clicked successfully");
		}
		else
		{
			logger.log(LogStatus.FAIL, "Element Click", "link is not clicked");
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static JSONObject JSONReadFromFile() {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {

			Object obj = parser.parse(new FileReader(f1.getAbsoluteFile()));

			jsonObject = (JSONObject) obj;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	public static String ObjIdentifier(String objname) {
		String ObjIdentifier = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();			
			Document doc = builder.parse(new File(objfile));			
			Element rootElement = doc.getDocumentElement();	        
	        NodeList list = rootElement.getElementsByTagName(objname);	 
	        for (int i = 0; i < list.getLength(); i++) {	          
	            Node childNode = list.item(i);	            
	            System.out.println("Object xpath : " + childNode.getTextContent());
	            ObjIdentifier = childNode.getTextContent();
	            return ObjIdentifier;
	        }

			} catch (Exception e) {
				e.printStackTrace();
			}
		return ObjIdentifier;		
		
		
	}

	public static void getScreenShot(String screenShotFileName) {
		File screenShotLocation = new File("./screenshot/" + screenShotFileName
				+ ".png");
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File file = screenshot.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, screenShotLocation);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
