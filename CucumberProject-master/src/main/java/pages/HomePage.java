package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utility.Base;

public class HomePage extends Base {
	
	
//	@FindBy(how = How.XPATH, using = "")
//	private WebElement LnkClasses;
//
//	@FindBy(xpath = "//a[text()='All Classes']")
//	private WebElement LnkAllClasses;
//	
//	public HomePage() {
//		PageFactory.initElements(driver, this);
//	}
	String xpathLnkClasses = ObjIdentifier("LnkClasses");
	private WebElement LnkClasses = driver.findElement(By.xpath(xpathLnkClasses));
	
	String xpathLnkAllClasses = ObjIdentifier("LnkAllClasses");
	private WebElement LnkAllClasses = driver.findElement(By.xpath(xpathLnkAllClasses));
	
	
	public void setLnkClasses(WebElement LnkClasses)
	{
		this.LnkClasses = LnkClasses;
	}

	public WebElement getLnkClasses()
	{
		return LnkClasses;
	}
		
	public void setLnkAllClasses(WebElement LnkAllClasses)
	{
		this.LnkAllClasses = LnkAllClasses;
	}
	
	public WebElement getLnkAllClasses()
	{
		return LnkAllClasses;
	}
	
	

	
	
	

	

}
