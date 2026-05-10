package com.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Utilities.Data;


public class LoginTest extends BaseTest{
	
  @Test(dataProvider="ValidExcelData",dataProviderClass=Data.class)
  public void ValidLogin(String userName,String password) {
	  
	  	login_Page.login(userName,password);
	  	
	  	Assert.assertEquals(dashBoard.getMessage(),"Log out");
	  	
	  	log.info("Login Successful!!");
  }
  
  
  @Test(dataProvider="InvalidExcelData",dataProviderClass=Data.class)
  public void InvalidLogin(String userName,String password) {
	  
	    login_Page.login(userName,password);
	  	
	  	Assert.assertTrue(login_Page.getAlertMessage().equals("Wrong password.") || login_Page.getAlertMessage().equals("User does not exist."));
	  	
	  	log.info("Login UnSuccessful!!");
  }
  
}
