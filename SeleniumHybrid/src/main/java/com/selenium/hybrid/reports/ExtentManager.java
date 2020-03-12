package com.selenium.hybrid.reports;


//http://relevantcodes.com/Tools/ExtentReports2/javadoc/index.html?com/relevantcodes/extentreports/ExtentReports.html


import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.selenium.hybrid.util.Constants;

public class ExtentManager {
	private static ExtentReports extent;

	public static ExtentReports getInstance() {
		if (extent == null) {
			Date d=new Date();
			String fileName=d.toString().replace(":", "_").replace(" ", "_")+".html";
			extent = new ExtentReports(Constants.REPORT_PATH+fileName, true, DisplayOrder.NEWEST_FIRST);

			
			extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
			// optional
			extent.addSystemInfo("Selenium Version", "2.53.0").addSystemInfo(
					"Environment", "QA");
			
		}
		return extent;
	}
	
	public static ExtentReports createInstance (String filename) {
		ExtentHtmlReporter htmlreport = new ExtentHtmlReporter(filename);
		htmlreport.config().setTheme(Theme.DARK);
		htmlreport.config().setDocumentTitle("Reports");
		return extent;
		
		
	}
}
