package DriverFactory;

import com.stock_accounting_purchases.FunctionLibrary;

import Utilities.PropertyFileUtil;

public class Basic {

	public static void main(String[] args) throws Throwable
	{
	PropertyFileUtil fis=new PropertyFileUtil();
	fis.getValueForKey("url");
	System.out.println(fis.getValueForKey("url"));
	FunctionLibrary.startBrowser();
	FunctionLibrary.openApp();
	
	

	}

}
