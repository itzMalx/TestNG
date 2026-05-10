package com.Test;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class listeners implements ITestListener{
	
	public void onTestStart(ITestResult result) {
		System.out.println(result.getName()+": Started");
	}
	
	public void onTestSkip(ITestResult result) {
		System.out.println(result.getName()+": Skipped");
	}
	
	public void onTestSuccess(ITestResult result) {
		System.out.println(result.getName()+": Success");
	}
	
	public void onTestFailure(ITestResult result) {
		
	    WebDriver driver = BaseTest.get_Driver();
		
		WebElement element = BaseTest.get_Element();
		
//		if (driver == null) {
//		    System.out.println("Driver is NULL → skipping screenshot");
//		    return;
//		}
		
		try {
			
			TakesScreenshot screen = (TakesScreenshot) element;
			
			File src = screen.getScreenshotAs(OutputType.FILE);
			
			 String fileName = result.getName() + "_" + System.currentTimeMillis() + ".png";
			
			FileUtils.copyFile(src,new File("./Screenshots/"+fileName));
			
			System.out.println(fileName);
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		System.out.println(result.getName()+": Failed");
	}

}
