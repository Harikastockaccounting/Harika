package com.stock_accounting_purchases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ReadingData {

	public static void main(String[] args) throws Throwable {
		Properties pr=new Properties();
		FileInputStream fis=new FileInputStream("D:\\Harika_Stock\\MavenStock\\PropertiesFile\\Environment.properties");
	pr.load(fis);
	System.out.println("Browser");
	System.out.println("username");
	System.out.println("password");
			
		

	}

}
