package com.stock_accounting_purchases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.bcel.generic.Select;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.PropertyFileUtil;


public class FunctionLibrary {
	 static WebDriver driver;
	//startBrowser
	public static WebDriver startBrowser() throws Throwable
	{
		if (PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\harika.g\\Downloads\\geckodriver.exe");
		driver=new FirefoxDriver();
		}else
			if (PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", "D:\\Harika_Stock\\MavenStock\\chromedriver.exe");
				driver=new ChromeDriver();
				}
		return driver;
	}
	
	//openurl
	public static void openApp() throws Throwable
	{
		System.out.println(PropertyFileUtil.getValueForKey("url"));
		driver.get(PropertyFileUtil.getValueForKey("url"));
		driver.manage().window().maximize();
	}
	//clickAction
	public static void clickAction(WebDriver driver,String locatortype,String locatorvalue)
	{
		if (locatortype.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorvalue)).click();
		}else 
			if (locatortype.equalsIgnoreCase("name"))
			{
			driver.findElement(By.name(locatorvalue)).click();
		}else 
			if (locatortype.equalsIgnoreCase("xpath"))
			{
				driver.findElement(By.xpath(locatorvalue)).click();
			
		}
	
	}
	//typeAction
	public static void typeAction(WebDriver driver,String locatortype,String locatorvalue,String data)

	{
		if (locatortype.equalsIgnoreCase("id"))
		{
		driver.findElement(By.id(locatorvalue)).clear();
		driver.findElement(By.id(locatorvalue)).sendKeys(data);
		}else 
			if (locatortype.equalsIgnoreCase("name"))
			{
			driver.findElement(By.name(locatorvalue)).clear();
			driver.findElement(By.name(locatorvalue)).sendKeys(data);
		}else 
			if (locatortype.equalsIgnoreCase("xpath"))
			{
			driver.findElement(By.xpath(locatorvalue)).clear();
			driver.findElement(By.xpath(locatorvalue)).sendKeys(data);
		}
	}
	//closeBrowser
	public static void closeBrowser(WebDriver driver)
		
		{
			driver.close();
			
		}
	

//wait
public static void waitforElement(WebDriver driver,String locatortype,String locatorvalue, String waittime)
{
	WebDriverWait wait=new WebDriverWait(driver, Integer.parseInt(waittime));
	if (locatortype.equalsIgnoreCase("id")) 
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorvalue)));
	}else
	
		if(locatortype.equalsIgnoreCase("name"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorvalue)));
			
		}else 
			if (locatortype.equalsIgnoreCase("xpath"))
			{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorvalue)));	
			}
	}


//mouseactions
public static void mouseActions(WebDriver driver)
{
	Actions action =new Actions( driver);
	action.sendKeys(Keys.PAGE_DOWN).perform();
	}



//mouse click
public static void mouseClick(WebDriver driver)
{
	Actions action=new Actions(driver);
	action.moveToElement(driver.findElement(By.xpath("//*[@id='mi_a_stock_items']"))).build().perform();
	action.moveToElement(driver.findElement(By.xpath("//*[@id='mi_a_stock_categories']"))).click().build().perform();
}


//capturing data

public static void captureData(WebDriver driver,String locatortype,String locatorvalue) throws IOException
{
	
String data="";
	if (locatortype.equalsIgnoreCase("id"))
	{
		data=driver.findElement(By.id(locatorvalue)).getAttribute("value");
	}else 
		if (locatortype.equalsIgnoreCase("name"))
		{
		data=driver.findElement(By.name(locatorvalue)).getAttribute("value");
	}else 
		if (locatortype.equalsIgnoreCase("xpath"))
		{
			data=driver.findElement(By.xpath(locatorvalue)).getAttribute("value");
		
	}
	FileWriter fw=new FileWriter("D:\\Harika_Stock\\MavenStock\\CaptureData\\Data.txt");
	BufferedWriter bw=new BufferedWriter(fw);
	bw.write(data);
	bw.flush();
	bw.close();
}



	//Table validation

	public static void tableValidation(WebDriver driver,String colNum) throws Throwable, Throwable

	{
		FileReader fr=new FileReader("D:\\Harika_Stock\\MavenStock\\CaptureData\\Data.txt");
		BufferedReader br=new BufferedReader(fr);
		String exp_data=br.readLine();
		int colNum1=Integer.parseInt(colNum);
		if (driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).isDisplayed())
		{
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).clear();
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).sendKeys(exp_data);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.button"))).click();
		}else
		{
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.panel"))).click();
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).clear();
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).sendKeys(exp_data);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.button"))).click();
		}
		
			WebElement webtable=driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("webtable.path")));
			List<WebElement>rows=webtable.findElements(By.tagName("tr"));
			for(int i=1; i<=rows.size(); i++)
			{
				String act_data=driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/form/div/div//table[@id='tbl_a_supplierslist']/tbody/tr["+i+"]/td["+colNum1+"]/div/span/span")).getText();
				System.out.println(act_data);
				Assert.assertEquals(exp_data, act_data);
				break;
			}
	
	}
	
	//PURCHASES DROPDOWN
	//public static void purchaseDropdown(WebDriver driver)
	//{
	//	Select dlist=new 		
	//}
	
	
	//STOCK VALIDATION 
	public static void stockValidation(WebDriver driver,String exp_data) throws Throwable, Throwable
	{
		if(driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).isDisplayed())
				{
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).clear();
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).sendKeys(exp_data);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.button"))).click();
		}else
		{
			Thread.sleep(3000);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.panel"))).click();
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).clear();
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).sendKeys(exp_data);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.button"))).click();
		}
		WebElement webtable=driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("webtable.path1")));
		List<WebElement>rows=webtable.findElements(By.tagName("tr"));
		for(int i=1; i<=rows.size(); i++)
		{
			String act_data=driver.findElement(By.xpath("///*[@id='tbl_a_stock_categorieslist']/tbody/tr["+i+"]/td[4]/div/span/span")).getText();
			System.out.println(act_data);
			Assert.assertEquals(exp_data, act_data);
			break;
		}

}
	}





	



	
	

		
			
	
	

