package DriverFactory;

import org.apache.poi.hpsf.examples.ReadCustomPropertySets;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.screenshots.Screenshots;
import com.stock_accounting_purchases.FunctionLibrary;

import Utilities.ExcelFileUtils;

public class DriverScript extends FunctionLibrary
{
	

protected  static WebDriver driver;	
 ExtentReports report;
 ExtentTest test;
	
	public void startTest() throws Throwable
	{
		report=new ExtentReports("./Reports/Extentreports.html");
		ExcelFileUtils excel=new ExcelFileUtils();
		for (int i = 1; i <=excel.rowCount("MasterTestCases"); i++)
		{
			String ModuleStatus="";
			if (excel.getData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
			{
				String TCModule=excel.getData("MasterTestCases", i, 1);
				int rowcount=excel.rowCount(TCModule);
				
				test=report.startTest(TCModule);
				
				for (int j = 1; j <=rowcount; j++) 
				{
					String Description=excel.getData(TCModule, j,0);
					String Object_Type=excel.getData(TCModule, j,1);
					String Locator_Type=excel.getData(TCModule, j,2);
					String Locator_Value=excel.getData(TCModule, j, 3);
					String Test_Data=excel.getData(TCModule, j, 4);
					try
					{
					System.out.println(Description);
					if (Object_Type.equalsIgnoreCase("startBrowser"))
					{
						driver=startBrowser();
						test.log(LogStatus.INFO, Description);
						}
					if (Object_Type.equalsIgnoreCase("openApp"))
					{
						openApp();
						test.log(LogStatus.INFO, Description);
					}
					if (Object_Type.equalsIgnoreCase("waitforElement"))
					{
						
						FunctionLibrary.waitforElement(driver, Locator_Type, Locator_Value, Test_Data);
						test.log(LogStatus.INFO, Description);
					}
					if (Object_Type.equalsIgnoreCase("mouseActions"))
					{
						
						FunctionLibrary.mouseActions(driver);
						test.log(LogStatus.INFO, Description);
					}
					
					if (Object_Type.equalsIgnoreCase("typeAction")) 
					{
						FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
						test.log(LogStatus.INFO, Description);
						
					}
					
					if (Object_Type.equalsIgnoreCase("tableValidation")) 
					{
						FunctionLibrary.tableValidation(driver, Test_Data);
						test.log(LogStatus.INFO, Description);
						
					}
					
					if (Object_Type.equalsIgnoreCase("stockValidation")) 
					{
						FunctionLibrary.stockValidation(driver, Test_Data);
						test.log(LogStatus.INFO, Description);
						
					}
						if (Object_Type.equalsIgnoreCase("clickAction")) 
					{
						FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value);
						test.log(LogStatus.INFO, Description);
					}
					if (Object_Type.equalsIgnoreCase("mouseClick")) 
						{
							FunctionLibrary.mouseClick(driver);
							test.log(LogStatus.INFO, Description);
						}
					
					if (Object_Type.equalsIgnoreCase("captureData"))
					{
						FunctionLibrary.captureData(driver, Locator_Type, Locator_Value);
						test.log(LogStatus.INFO, Description);
					}
					
					if (Object_Type.equalsIgnoreCase("closeBrowser"))
					{
					FunctionLibrary.closeBrowser(driver);
					test.log(LogStatus.INFO, Description);
					}
					excel.setData(TCModule, j, 5, "PASS");
					ModuleStatus="true";
					test.log(LogStatus.PASS, Description);
				}catch(Exception e)
					{
					
					Screenshots.takeScreenshot();
					excel.setData(TCModule, j, 5, "FAIL");
					ModuleStatus="false";
					test.log(LogStatus.FAIL, Description);
					break;
					}
					
				}
				if (ModuleStatus.equalsIgnoreCase("true"))
				{
					excel.setData("MasterTestCases", i, 3, "PASS");
				}else
					if (ModuleStatus.equalsIgnoreCase("false")) 
					{
						excel.setData("MasterTestCases", i, 3, "FAIL");
					}
				
				}
			else
			{
				excel.setData("MasterTestCases", i, 3, "Not Executed");
			}
			report.endTest(test);
			report.flush();
		}
	}

}
