package com.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.Pages.DashBoardPage;
import com.Pages.LoginPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

@Listeners(listeners.class)
public class BaseTest {
	
	DashBoardPage dashBoard;
	LoginPage login_Page;	
	
	public static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	public static final ThreadLocal<WebElement> element = new ThreadLocal<WebElement>();
	
	public static final Logger log = LogManager.getLogger(BaseTest.class);
	
	public static WebDriver get_Driver() {
		return driver.get();
	}
	
	public static WebElement get_Element() {
		return element.get();
	}
	
  @BeforeMethod
  public void beforeMethod() {
	 
	  WebDriver driver1 = new ChromeDriver();
	  driver.set(driver1);
	  driver1.manage().window().maximize();
	  
	  log.info("Login Started!!");
	  driver1.get("https://www.demoblaze.com/");
	  
	  log.info("DemoBlaze Website launched...");
	  
	 
	  login_Page = new LoginPage(driver1);
	  dashBoard = new DashBoardPage(driver1);
	 
	  
  }

  @AfterMethod
  public void afterMethod() {
	  
	  WebDriver driver1 = driver.get();
	  
	  log.info("Closing....");
	  
	  if(driver1!=null) {
		  driver1.quit();
	  }
  }

}
