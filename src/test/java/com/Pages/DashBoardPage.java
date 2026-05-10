package com.Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashBoardPage extends BasePage {
	
	public DashBoardPage(WebDriver driver) {
		super(driver);
	}
	
	

	@FindBy(id="logout2")
	public WebElement logout;
	
	
	
	public String getMessage() {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.visibilityOf(logout));
		
		return logout.getText();
	}
	
}
