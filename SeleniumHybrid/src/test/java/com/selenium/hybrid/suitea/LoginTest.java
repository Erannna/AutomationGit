package com.selenium.hybrid.suitea;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.selenium.hybrid.base.BaseTest;
import com.selenium.hybrid.driver.DriverScript;
import com.selenium.hybrid.util.Constants;
import com.selenium.hybrid.util.DataUtil;
import com.selenium.hybrid.util.Xls_Reader;



public class LoginTest extends BaseTest {

	//Make sure the prop file is loaded and initialization happens
	

	@Test(dataProvider="getData")
	public void loginTest(Hashtable<String, String> data){

	test.log(Status.INFO, "Starting"+testName);
		//System.out.println("Running Login Test");
		
		//Set Runmode based on setting
		if(DataUtil.isSkip(testName,xls) || data.get(Constants.RUNMODE_COL).equals(Constants.RUNMODE_NO)) {
			test.log(Status.SKIP, "Runmode is set to NO");
			throw new SkipException("RunModeis set to No");
		}
		
		ds = new DriverScript();
		ds.executeKeywords(testName, xls, data);
		
	}
	
	
	
}
