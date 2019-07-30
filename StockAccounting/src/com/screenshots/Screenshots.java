package com.screenshots;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import DriverFactory.DriverScript;


public class Screenshots extends DriverScript {


	public static void takeScreenshot() throws Throwable
	
	{
		
		DateFormat dft=new SimpleDateFormat("yyyy_mm_dd_ss_hh");
		Date d1=new Date();
		String date=dft.format(d1);
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("./Screenshots/shots"+date+".png"));

	}

}
