package com.selenium.hybrid.driver;

import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.selenium.hybrid.keyword.AppKeyword;
import com.selenium.hybrid.util.Xls_Reader;
import com.selenium.hybrid.util.Constants;


public class DriverScript {
	
	public Properties envProp;
	public Properties prop;
	public AppKeyword app;
	public ExtentTest test;
	public Properties getEnvProp() {
		return envProp;
	}

	public void setEnvProp(Properties envProp) {
		this.envProp = envProp;
	}

	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}

	public void setExtentTest(ExtentTest test) {
		this.test = test;
	}


//E:\code\Module 21\July 2016 - New framework\Hybrid_Framework\src\main\java\com
	public void executeKeywords(String testName,Xls_Reader xls, Hashtable<String, String> data) {
		
		int rows = xls.getRowCount(Constants.KEYWORDS_SHEET);
		System.out.println("Rows are :" +rows);
		app= new AppKeyword();
		
		//send keyword to prop file
		app.setEnvProp(envProp);
		app.setProp(prop);
		app.setData(testData);
		app.setExtentTest(test);
		
		for(int rNum=2;rNum<=rows;rNum++) {
		String tcid = xls.getCellData(Constants.KEYWORDS_SHEET, Constants.TCID_COL, rNum);
		if(tcid.equals(testName)) {
			String keyword = xls.getCellData(Constants.KEYWORDS_SHEET, "Keyword", rNum);
			String objectkey = xls.getCellData(Constants.KEYWORDS_SHEET, "Object", rNum);
			String datakey = xls.getCellData(Constants.KEYWORDS_SHEET, "Data", rNum);
			String proceedOnFail= xls.getCellData(Constants.KEYWORDS_SHEET, Constants.PROCEED_COL, rNum);
			String data =  testData.get(dataKey);
			
			//System.out.println(tcid+"---"+keyword+"---"+prop.getProperty(objectkey)+"----"+Datakey);
			test.log(Status.INFO, tcid+"---"+keyword+"---"+prop.getProperty(objectkey)+"----"+datakey);
			app.setDatakey(datakey);
			app.setObjectkey(objectkey);
			app.setProceedOnFail(proceedOnFail);
			
			Method method;			
				method = app.getClass().getMethod(keyword);
				method.invoke(app);
			 
			
		}
		app.assertAll();
		
		}
	}
	public void quit() {
		if(app!=null) {
			app.quit();
		}
		
	}
	
}
