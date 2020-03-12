package com.selenium.hybrid.keyword;

import java.sql.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.utils.FileUtil;

import junit.framework.Assert;

public class GenericKeyword {
	
	public Properties envProp;
	public Properties prop;
	public String objectkey;
	public String Datakey;
	public Hashtable<String, String> data;
	public WebDriver driver;
	public String ProceedOnFail;
	public SoftAssert softassert;
	
	public void setProceedOnFail(String proceedOnFail) {
		this.ProceedOnFail = proceedOnFail;
	}

	public String getProceedOnFail() {
		return ProceedOnFail;
	}

	public ExtentTest test;
	
	/*++++++++++++Setter functions ==========*/

	
	public void setExtentTest(ExtentTest test) {
		this.test = test;
	}
	
	public void setEnvProp(Properties envProp) {
		this.envProp = envProp;
	}
	
	public void setProp(Properties prop) {
		this.prop = prop;
	}
	
	public void setObjectkey(String objectkey) {
		this.objectkey = objectkey;
	}
	
	public void setDatakey(String datakey) {
		Datakey = datakey;
	}
	
	public void setData(Hashtable<String, String> data) {
		this.data = data;
	}
	public void openBrowser() {
		
	}
		public void click() {
			test.log(Status.INFO, "CLick on object "+envProp.getProperty(objectkey));
			getObject(objectkey).click();
		}
	
	public void type() {
		test.log(Status.INFO, "Enter the text into " +getObject(objectkey) +"=== with value : "+data.get(Datakey) );
		getObject(objectkey).sendKeys(data.get(Datakey));
	}

	public void navigate() {
		test.log(Status.INFO, "Navigate to Website " +envProp.getProperty(objectkey));
	
	}
	
	public void validateTitle() {
		System.out.println("Validating title :"+prop.getProperty(objectkey));
		String expectedTitle = prop.getProperty(objectkey);
		String actualTitle = driver.getTitle();
		if(!expectedTitle.equals(actualTitle)) {
			//Report the failure
			reportFailure("Not matching the title");
		}
	}
	
	//This function will return true or false based on the presence
	// it just tell the result
	public boolean isElemntPresent(String objectKey) {
		
		WebElement list = null;
		String identifier[] = prop.getProperty(objectkey).split(",");
		String idetf = identifier[0];
		
		if(idetf.equals("xpath")){
			//visibilty
			list= driver.findElement(By.xpath(prop.getProperty(objectKey)));
		}else if(idetf.equals("css")){
			list= driver.findElement(By.cssSelector(prop.getProperty(objectKey)));
		}else if (idetf.equals("id")){
			list= driver.findElement(By.id(prop.getProperty(objectKey)));
		}else {
			list= driver.findElement(By.name(prop.getProperty(objectKey)));
		}
		
		if(list.getSize() != null)
		return false;
		else
			return true;
		
	}
	
	//Failure reporting
	/*********Reporting function********/
	public void reportFailure(String FailureMessage) {
		test.log(Status.FAIL, FailureMessage);
		//Assert.fail(FailureMessage);
		
		if(ProceedOnFail.equals("Y")) {
			softassert.fail(FailureMessage);
		}
		else {
			softassert.fail(FailureMessage);
			softassert.assertAll();
		}
	}
	
	public void assertAll() {
		softassert.assertAll();
	}
	
	public void validateElemntPresent() {
		if(!isElemntPresent(objectkey)) {
			//Report failure
		}
	}
	
	//Central function to extract Objects
	public WebElement getObject(String objectKey) {
		
		WebElement e=null;
		try {
			
			String identifier[] = prop.getProperty(objectkey).split(",");
			String idetf = identifier[0];
			
			if(idetf.equals("xpath")){
				//visibilty
				e= driver.findElement(By.xpath(prop.getProperty(objectKey)));
			}else if(idetf.equals("css")){
				e= driver.findElement(By.cssSelector(prop.getProperty(objectKey)));
			}else if (idetf.equals("id")){
				e= driver.findElement(By.id(prop.getProperty(objectKey)));
			}else {
				e= driver.findElement(By.name(prop.getProperty(objectKey)));
			}
			
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(e));
			//state of the object
			wait.until(ExpectedConditions.elementToBeClickable(e));
			
		} catch(Exception ex)
		{
			// failure report
		}
		
		return e;
		
	}

	public void quit() {
		if(driver!=null) {
			driver.quit();
		}
	}

	public void takeScreenShot() {
		//file name has to be decided
		//Path of the screenshot to be stored
		
		Date d = new Date();
		String screenshotFile= d.toString().replace(":", "_").replace(":", "_")+".png";
		//take screenshot
		FileUti
		
		
	}
	
}
