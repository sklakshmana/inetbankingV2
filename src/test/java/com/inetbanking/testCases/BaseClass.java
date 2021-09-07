package com.inetbanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilites.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;




//import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	ReadConfig readconfig=new ReadConfig();
	public String baseURL=readconfig.getApplicationURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();
	
	/*	
	public String baseURL="http://demo.guru99.com/v4/";
	public String username="mngr337846";
	public String password="edUmEqU";
	public static WebDriver driver; 
	*/
	
	
	public WebDriver driver;
	
	//public String browser = "chrome";
	
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br) throws InterruptedException
	{		
		
		logger=Logger.getLogger("ebanking");
		PropertyConfigurator.configure("log4j.properties");
		
		if (br.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",readconfig.getChromepath());
			//WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if (br.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxpath());			
			driver = new FirefoxDriver();
		} 
		 
		else if (br.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver",readconfig.getIepath());
			driver = new InternetExplorerDriver();
		}
		else if (br.equalsIgnoreCase("edge"))
		{
			/*System.setProperty("webdriver.edge.driver",readconfig.getEdgepath());
			driver = new EdgeDriver();*/
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(baseURL);
		
		
	}
	@Parameters("browser")
	@AfterClass
	public void teardown(String br) throws IOException
	{
		driver.quit();
		
		if (br.equals("Firefox"))
		{
			Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
		} 
		else if (br.equals("chrome"))
		{
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
		}
		else if (br.equals("edge"))
		{
			Runtime.getRuntime().exec("taskkill /F /IM msedgedriver.exe");
		}
		else if (br.equals("ie"))
		{
			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
		}	
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	public String randomestring()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return generatedstring;
	}
	
	public String randomNum()
	{
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return generatedString2;
	}
	
	/*
	@BeforeClass
	public void setup()
	{		
	
	logger=Logger.getLogger("ebanking");
	PropertyConfigurator.configure("log4j.properties");
	
	if (browser.equals("Firefox"))
	{
		System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxpath());			
		driver = new FirefoxDriver();
	} 
	else if (browser.equals("chrome"))
	{
		System.setProperty("webdriver.chrome.driver",readconfig.getChromepath());
		driver = new ChromeDriver();
	}
	else if (browser.equals("ie"))
	{
		System.setProperty("webdriver.ie.driver",readconfig.getIepath());
		driver = new InternetExplorerDriver();
	}
	else if (browser.equals("edge"))
	{
		System.setProperty("webdriver.edge.driver",readconfig.getEdgepath());
		driver = new EdgeDriver();
	}
	 
	 
	 */
	
	/*
	if (browser.equals("Firefox"))
	{
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	} 
	else if (browser.equals("chrome"))
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		//F:\\Automation\\testworkspace\\inetbankingV2\\Drivers
	}
	else if (browser.equals("edge"))
	{
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
	}
	*/
	

}
