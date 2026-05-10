package com.Pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Test.BaseTest;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy (id="login2")
	public WebElement login;
	
	@FindBy (id="loginusername")
	public WebElement userName;
	
	@FindBy (id="loginpassword")
	public WebElement password;
	
	@FindBy (xpath="//button[text()='Log in']")
	public WebElement loginButton;
	
	
	public void login(String name,String pass) {
		login.click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.visibilityOf(userName));
		
		BaseTest.element.set(userName);
		
		userName.sendKeys(name);
		
		BaseTest.element.set(password);
		
		password.sendKeys(pass);
		
		BaseTest.element.set(loginButton);
		
		loginButton.click();
		
		
		
	}
	
	public String getAlertMessage() {
		
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		
		 wait.until(ExpectedConditions.alertIsPresent());
		
		Alert alert = driver.switchTo().alert();
		
		String alertMessage=alert.getText();
		
		alert.accept();
		
		return alertMessage;
	}
}
