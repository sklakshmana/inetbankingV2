package com.inetbanking.testCases;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass {
	
	@Test
	public void addNewCustomer() throws InterruptedException, IOException
	{
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("User name is provided");
		lp.setPassword(password);
		logger.info("Password is provided");
		lp.clickSubmit();
		
		Thread.sleep(3000);    	// 3 seconds
		
		AddCustomerPage addcust=new AddCustomerPage(driver);
		
		addcust.clickAddNewCustomer();
		
		logger.info("Providing customer details.....");
		
		addcust.custName("TestUser");
		addcust.custgender("male");
		addcust.custdob("10", "15", "1995");
		Thread.sleep(5000);
		addcust.custaddress("India");
		addcust.custcity("Bangalore");
		addcust.custstate("KA");
		addcust.custpinno("560097");
		addcust.custtelephoneno("987890091");
		
		String email=randomestring()+"@gmail.com";
		addcust.custemailid(email);
		addcust.custpassword("Password1@");
		addcust.custsubmit();
		
		Thread.sleep(3000);
		
		logger.info("validation started");
		
		boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");
		
		System.out.println("Response "+res);
		
		if(res==true)
		{
			
			Assert.assertTrue(true);
			logger.info("test case passed....");
		}
		else
		{
			logger.info("test case failded....");
			captureScreen(driver, "addNewCustomer");
			Assert.assertTrue(false);
		}
	}
	
	

}
