package com.stock_accounting_purchases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class Library {
	WebDriver driver;
	String res;
	//appLaunch
	public String appLaunch(String url)
	{
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\harika.g\\Downloads\\geckodriver.exe");
		driver=new FirefoxDriver();
		driver.get(url);
		driver.manage().window().maximize();
		//validation
		if(driver.findElement(By.id("username")).isDisplayed())
		{
			res="pass";
		}
		else
		{
			res="fail";
		}
		return res;
		
	}
	
	

	public static void main(String[] args) throws Throwable {
		Library app=new Library();
		String results=app.appLaunch("http://webapp.qedge.com/login.php");
		System.out.println(results);
		app.appLogin("admin", "master");
		app.supplierCreation();
		app.appLogout();
		app.appClose();
		}


//appLogin
public String appLogin(String uid,String pwd) throws Throwable
{
	driver.findElement(By.id("username")).clear();
	driver.findElement(By.id("username")).sendKeys(uid);
	driver.findElement(By.id("password")).clear();
	driver.findElement(By.id("password")).sendKeys(pwd);
	Thread.sleep(4000);
	driver.findElement(By.id("btnsubmit")).click();
	//validation
	if(driver.findElement(By.xpath("//a[@id='logout']")).isDisplayed())
	{
		res="pass";
	}
	else
	{
		res="fail";
	}
	return res;
}

public String supplierCreation() throws Throwable
{
	Thread.sleep(4000);
	driver.findElement(By.xpath("//*[@id='mi_a_suppliers']/a")).click();
	driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]/div/a/span")).click();
	driver.findElement(By.id("x_Supplier_Name")).sendKeys("Harika");
	driver.findElement(By.id("x_Address")).sendKeys("Kukatpally");
	driver.findElement(By.id("x_City")).sendKeys("Hyderabad");
	driver.findElement(By.id("x_Country")).sendKeys("India");
	driver.findElement(By.id("x_Contact_Person")).sendKeys("Suresh");
	driver.findElement(By.id("x_Phone_Number")).sendKeys("040-45697896");
	driver.findElement(By.id("x__Email")).sendKeys("hema@gmail.com");
	driver.findElement(By.id("x_Mobile_Number")).sendKeys("9885325642");
	driver.findElement(By.id("x_Notes")).sendKeys("data enterd");
	Actions act=new Actions(driver);
	act.sendKeys(Keys.DOWN).perform();
	driver.findElement(By.xpath("//*[@id='btnAction']")).submit();
	driver.switchTo().alert().accept();
	Thread.sleep(4000);
	driver.findElement(By.xpath("//button[@class='ajs-close']")).click();
	
	//validation
	Thread.sleep(3000);
	if(driver.findElement(By.xpath("//*[@id='mi_a_suppliers']/a")).isDisplayed())
	{
		res="pass";
	}
	else
	{
		res="fail";
	}
	return res;
	}




//appLogout
public String appLogout() throws Throwable
{
	driver.findElement(By.id("logout")).click();
	Thread.sleep(4000);
	driver.findElement(By.xpath("/html/body/div[18]/div[2]/div/div[4]/div[2]/button[1]")).click();
	//validation
		if(driver.findElement(By.name("username")).isDisplayed())
		{
			res="pass";
		}
		else
		{
			res="fail";
		}
		return res;
	}
//appClose
public void appClose()
	{
		driver.close();
	}
}
	
