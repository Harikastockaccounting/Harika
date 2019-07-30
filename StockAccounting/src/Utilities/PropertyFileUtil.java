package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtil
{

	public static String getValueForKey(String key) throws Throwable, Exception
	{
		Properties configProperties=new Properties();
		FileInputStream fis=new FileInputStream("D:\\Harika_Stock\\StockAccounting\\PropertiesFile\\Environment.properties");
		configProperties.load(fis);
	
		return configProperties.getProperty(key);
	}
	
	}
	

