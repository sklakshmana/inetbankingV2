package com.inetbanking.utilites;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ReadConfig {
	
	Properties pro;
	
	public ReadConfig()
	{
		File src = new File("./Configuration/config.properties");
		
		
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception is "+e.getMessage());
		}
	}
	
	public String getApplicationURL()
	{
		return pro.getProperty("baseURL");
	}
	
	public String getUsername()
	{
		return pro.getProperty("username");
	}
	
	public String getPassword()
	{
		return pro.getProperty("password");
	}
	
	public String getChromepath()
	{
		return pro.getProperty("chromepath");
	}
	
	public String getIepath()
	{
		return pro.getProperty("iepath");
	}
	
	public String getFirefoxpath()
	{
		return pro.getProperty("firefoxpath");
	}
	public String getEdgepath()
	{
		return pro.getProperty("edgepath");
	}
}
