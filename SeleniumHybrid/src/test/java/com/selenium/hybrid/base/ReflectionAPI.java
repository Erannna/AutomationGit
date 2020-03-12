package com.selenium.hybrid.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionAPI {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		
		String x= "SampleTest";
		Method method = ReflectionAPI.class.getMethod(x, String.class) ;
		method.invoke(method, "Hello:");
		System.out.println("End");
	}
	
	public static void SampleTest(String x) {
		System.out.println("In sample test: "+x);
		
	}

}
