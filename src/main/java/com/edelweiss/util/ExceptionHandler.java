package com.edelweiss.util;

import java.io.IOException;

import com.edelweiss.FunctionKeyword;

public class ExceptionHandler extends Exception{
	
	ExtendReporter reporter;
	
	public ExceptionHandler() {
		reporter=new ExtendReporter();
	}
	
	public void timeOutExceptionHandler(String elementName) {
		/*
		 * try { reporter.abnormalErrorHandling(FunctionKeyword.driver,elementName); }
		 * catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		
	}

}