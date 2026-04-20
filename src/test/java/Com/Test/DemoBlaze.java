package Com.Test;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class DemoBlaze {

    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Start the test");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);

        driver.get("https://demoblaze.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

  
    @Test(priority = 1)
    public void validation() {

        driver.findElement(By.id("login2")).click();
        driver.findElement(By.id("loginusername")).sendKeys("Malavicka");
        driver.findElement(By.id("loginpassword")).sendKeys("12345678");
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
          
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String msg = alert.getText();
            alert.accept();

            Assert.fail("Login failed. Unexpected alert: " + msg);

        } catch (TimeoutException e) {

           
            WebElement user = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser"))
            );

            String actualText = user.getText();
            System.out.println("Login successful: " + actualText);

            Assert.assertTrue(actualText.contains("Welcome"),
                    "Login not successful");
        }
    }

  
    @Test(priority = 2)
    public void invaliduser() {

        driver.findElement(By.id("login2")).click();
        driver.findElement(By.id("loginusername")).sendKeys("wrongUser");
        driver.findElement(By.id("loginpassword")).sendKeys("12345678");
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String msg = alert.getText();
        alert.accept();

        System.out.println("Alert: " + msg);

        Assert.assertTrue(
                msg.contains("User does not exist") || msg.contains("Wrong password"),
                "Unexpected alert message: " + msg
        );
    }

 
    @Test(priority = 3)
    public void invalidpassword() {

        driver.findElement(By.id("login2")).click();
        driver.findElement(By.id("loginusername")).sendKeys("Malavicka");
        driver.findElement(By.id("loginpassword")).sendKeys("wrongPass");
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String msg = alert.getText();
        alert.accept();

        System.out.println("Alert: " + msg);

        Assert.assertTrue(
                msg.contains("Wrong password") || msg.contains("User does not exist"),
                "Unexpected alert message: " + msg
        );
    }

    
    @AfterMethod
    public void after() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}