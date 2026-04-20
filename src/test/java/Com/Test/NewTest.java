package Com.Test;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
public class NewTest {
	public WebDriver driver;
  
  @Test(priority = 1)
  public void validation() {
	  driver.findElement(By.xpath("//a[@id='login2']")).click();
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='loginusername']"))).sendKeys("Malavicka");
	  driver.findElement(By.xpath("//input[@id='loginpassword']")).sendKeys("12345678");
	  driver.findElement(By.xpath("//button[text()='Log in']")).click();
	  try {
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			if (alert != null) {
				alert.accept();
				System.out.println("Login Failed");
			}
		}  
		catch (Exception e) {
			WebElement home = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Welcome Malavicka']")));

			if (home.getText().equals("Welcome Malavicka")) {
				System.out.println("Login Successful");
			}
		}
	  
  }
  
  @Test(priority = 2)
  public void InvalidUserName() {
	  driver.findElement(By.xpath("//a[@id='login2']")).click();
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='loginusername']"))).sendKeys("Mala");
	  driver.findElement(By.xpath("//input[@id='loginpassword']")).sendKeys("12345678");
	  driver.findElement(By.xpath("//button[text()='Log in']")).click();
	  try {
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			if (alert != null) {
				alert.accept();
				System.out.println("Login Failed(Invalid UserName)");
			}
		} 
		catch (Exception e) {
			WebElement home = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Welcome Malavicka']")));

			if (home.getText().equals("Welcome Malavicka")) {
				System.out.println("Login Successful");
			}
		}
	  
  }
  
  @Test(priority = 3,dependsOnMethods="Invalid Username")
  public void InvalidPassword() {
	  driver.findElement(By.xpath("//a[@id='login2']")).click();
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='loginusername']"))).sendKeys("Malavicka");
	  driver.findElement(By.xpath("//input[@id='loginpassword']")).sendKeys("malu");
	  driver.findElement(By.xpath("//button[text()='Log in']")).click();
	  try {
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			if (alert != null) {
				alert.accept();
				System.out.println("Login Failed(Invalid Password)");
			}
		} 
		catch (Exception e) {
			WebElement home = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Welcome Malavicka']")));

			if (home.getText().equals("Welcome Malavicka")) {
				System.out.println("Login Successful");
			}
		}
	  
  }
  
  @BeforeTest
  public void beforeTest() {
      ChromeOptions options = new ChromeOptions();
      options.addArguments("--start-maximized");
      //options.addArguments("--headless");
      driver = new ChromeDriver(options);
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
      
  }
  @BeforeMethod
 public void beforeMethod() {
	 driver.get("https://www.demoblaze.com/");
 }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}