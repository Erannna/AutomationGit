package com.selenium.hybrid.suitea;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentAventReporter;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.selenium.hybrid.base.BaseTest;
import com.selenium.hybrid.reports.ExtentManager;
import com.selenium.hybrid.util.Constants;
import com.selenium.hybrid.util.DataUtil;


public class TestB extends BaseTest{
	
	@Test(dataProvider="getData")
	public void TestB(Hashtable<String, String> data) {
		
		//ExtentReporter rep = ExtentManager.getInstance(prop.getProperty("reportPath"));
		//ExtentTest test = rep.createTest();
		test.log(Status.INFO, "Starting Test B");
		test.log(Status.INFO, data.toString());
		
		
		
		if(DataUtil.isSkip(testName, xls) || data.get(Constants.RUNMODE_COL).equals(Constants.RUNMODE_NO)) {
			test.log(Status.SKIP, "Run Mode set to NO");
			throw new SkipException("Run Mode set to NO");
		}

	//	System.out.println("Running Test B Test");
		ds.executeKeywords(testName, xls, data);
		
	
		
		test.log(Status.INFO, "executing keywords");
		test.log(Status.PASS, "Test Passed");
		//rep.flush();
	}

}
