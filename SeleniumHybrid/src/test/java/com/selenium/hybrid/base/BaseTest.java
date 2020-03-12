package com.selenium.hybrid.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Driver;
import java.util.Properties;

import org.junit.rules.TestName;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.selenium.hybrid.driver.DriverScript;
import com.selenium.hybrid.reports.ExtentManager;
import com.selenium.hybrid.util.DataUtil;
import com.selenium.hybrid.util.Xls_Reader;

public class BaseTest {
	
	public Properties envProp;
	public Properties prop;
	public Xls_Reader xls;
	public String testName;
	public DriverScript ds;
	public ExtentReports rep;
	public ExtentTest test;
	
	
	@BeforeTest
	public void init() {
		
		System.out.println("Initializing.......");
		//init TestName
		System.out.println("***"+this.getClass().getSimpleName());
		testName = this.getClass().getSimpleName();
		System.out.println("***"+this.getClass().getPackage().getName());
		String arr[] = this.getClass().getPackage().getName().split("\\.");
		System.out.println(arr[arr.length-1]);
		String suiteName = arr[arr.length-1];
		
		prop= new Properties();
		envProp= new Properties();
		
		//init Property file
		try {
			FileInputStream fs =  new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\env.properties");
			prop.load(fs); // intiialize Env Prop
			System.out.println(prop.getProperty("env"));
			String env = prop.getProperty("env");
			fs=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\"+env+".properties");
			envProp.load(fs); // Initializing prop file
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Intialiize xls file
		//how do i come to know the suite name
		
		xls =  new Xls_Reader(envProp.getProperty(suiteName+"_xls"));
		
		
		//Initialize Driver script
		ds= new DriverScript();
		ds.setEnvProp(envProp);
		ds.setProp(envProp);
		
	}
	
	@BeforeMethod
	public void initTest() {
		rep= ExtentManager.getInstance(prop.getProperty("reportPath"));
		test=rep.createTest(testName);
		ds.setExtentTest(test);
	}
	
	@AfterMethod
	public void quit() {
	
		if(ds!=null) {
			ds.quit();
		}
		
		if(rep!=null) {
			rep.flush();
		}
	}
	@DataProvider
	public Object[][] getData(){
		
		//i can use xl object to read data
		System.out.println("Inside data provider");
		Object[][] data = new Object[1][1];
		//data[0][0]="a";
		//String testName = "LoginTest";
		//Xls_Reader xls =  new Xls_Reader("E:\\Eranna-Automation\\DataFiles\\SuiteA.xlsx");
		return DataUtil.getTestData(testName, xls);
	}

}
