package com.selenium.hybrid.keyword;

public class AppKeyword extends GenericKeyword{

	public void ValidateLogin() {
		
		
	}
	
	public void defaultLogin() {
		String username = envProp.getProperty("adminusername");
		String password = envProp.getProperty("adminpassword");
		
	}
	
}
