package com.selenium.hybrid.util;

import java.util.Hashtable;

public class DataUtil {

	
	//function to read Run mode
			public static boolean isSkip(String testName, Xls_Reader xls) {
				
				int rows =xls.getRowCount(Constants.TESTCASES_SHEET);
				
				for(int rNum=2;rNum<=rows;rNum++) {
					String tcid =  xls.getCellData(Constants.TESTCASES_SHEET, Constants.TCID_COL, rNum);
					if(tcid.equals(testName)) {
						String runmode = xls.getCellData(Constants.TESTCASES_SHEET, Constants.RUNMODE_COL, rNum);
						if(runmode.equals(Constants.RUNMODE_NO)) {
							return true;
						}
							else {
								return false;
							}
						
						}
					}
				
				return true;
			}
			
			
			
	public static Object[][] getTestData(String testName, Xls_Reader xls){
		
		int testStartRowNum = 1;
		while(!xls.getCellData("Data", 0, testStartRowNum).equals(testName)) {
			testStartRowNum++;
		}
		
		System.out.println("Row Number of test is :"+testStartRowNum);
		//find total cols in testcase
		int colStartRowNum= testStartRowNum+1;
		int totalCols=0;
		while(!xls.getCellData("Data", totalCols, colStartRowNum).equals("")) {
			totalCols++;
		}
		
		System.out.println("Total Columns are :"+totalCols);
		
		int dataStartRowNumber = testStartRowNum+2;
		
		int totalRows=0;
		while(!xls.getCellData("Data", 0, dataStartRowNumber).equals("")) {
			totalRows++;
			dataStartRowNumber++;
		}
		
		System.out.println("Total Rows :"+totalRows);
		
		
		//read data
		dataStartRowNumber =  testStartRowNum+2;
		
		Hashtable<String,String> table = null;
		
		int finalRows = dataStartRowNumber+totalRows;
		
		Object[] [] myData = new Object[totalRows][1];
		int i=0;
		for(int rNum=dataStartRowNumber;rNum<finalRows;rNum++) {
			table = new Hashtable<String, String>();
			for(int cNum=0;cNum<totalCols;cNum++) {
				String data = xls.getCellData("Data", cNum, rNum);
				String key = xls.getCellData("Data", cNum, colStartRowNum);
				System.out.println(key +"===="+ data);
			}
			System.out.println(table);
			myData[i][0]=table;
			i++;
			System.out.println("=====================");
		}
		
		return myData;
		
	}
	
	
	
}

