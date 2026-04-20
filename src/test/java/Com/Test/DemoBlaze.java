package Com.Test;
import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

public class DemoBlaze {
	    WebDriver driver;
	    @BeforeMethod
	    public void beforeMethod() {
	  	  System.out.println("Start the test");
	  	  ChromeOptions options=new ChromeOptions();
	  	  driver=new ChromeDriver(options);
	  	  options.addArguments("--start-maximize");
	  	  options.addArguments("--headless");
	  	  driver.get("https://demoblaze.com/");
	  	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    }
	    
	    @Test(priority = 2)
	    public void validation() {
	  	  driver.findElement(By.id("login2")).click();
	  	  driver.findElement(By.id("loginusername")).sendKeys("Malavicka");
	  	  driver.findElement(By.id("loginpassword")).sendKeys("12345678");
	      driver.findElement(By.xpath("//button[text()='Log in']")).click();	      
	      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	      String actualUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser"))).getText();
	      Assert.assertEquals(actualUser, "Welcome Malavicka", "Login failed");
	      System.out.println("Login successful:"+actualUser);
	      String title = driver.getTitle();
	      Assert.assertEquals(title, "STORE", "Page title mismatch");
	      System.out.println("Page Title: "+title);
	    }
	   	    
	    @Test(dependsOnMethods = "validation", priority = 1)
	    public void invaliduser() {
	    	driver.findElement(By.id("login2")).click();
		  	driver.findElement(By.id("loginusername")).sendKeys("Malavicka");
		  	driver.findElement(By.id("loginpassword")).sendKeys("12345678");
		    driver.findElement(By.xpath("//button[text()='Log in']")).click();
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    try {
		        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		        String msg = alert.getText();
		        System.out.println(msg);
		        System.out.println("invalid name");

		        Assert.assertTrue(msg.contains("Wrong"));
		        alert.accept();

		    } catch (Exception e) {
		        String user = driver.findElement(By.id("nameofuser")).getText();
		        System.out.println("Logged in: " + user);
		        Assert.fail("Expected alert, but login succeeded");
		    }
	    }
	    @Test(priority = 3)
	    public void invalidpassword() {
	    	driver.findElement(By.id("login2")).click();
		  	driver.findElement(By.id("loginusername")).sendKeys("Malavicka");
		  	driver.findElement(By.id("loginpassword")).sendKeys("12345678");	  
		    driver.findElement(By.xpath("//button[text()='Log in']")).click();		    
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		    String alertMessage = alert.getText();
		    System.out.println(alertMessage);
		    Assert.assertEquals(alertMessage, "Wrong password.");
		    alert.accept();		    		    
	    }	   
	    @AfterMethod
	    public void after() {
	        driver.quit();
	    }

}