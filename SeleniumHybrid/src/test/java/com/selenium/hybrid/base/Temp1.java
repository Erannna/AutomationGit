package com.selenium.hybrid.base;

import java.sql.Date;

public class Temp1 {

	public static void main(String[] args) {
		
		Date d =  new Date();
		System.out.println(d.toString().replace(":", "_").replace(" ", "_")+".html");
	}

}
