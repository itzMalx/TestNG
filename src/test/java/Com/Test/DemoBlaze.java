package Com.Test;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class DemoBlaze {

    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Start the test");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // correct

        driver = new ChromeDriver(options);

        driver.get("https://demoblaze.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    
    @Test(priority = 1)
    public void validation() {
        driver.findElement(By.id("login2")).click();
        driver.findElement(By.id("loginusername")).sendKeys("validUser");
        driver.findElement(By.id("loginpassword")).sendKeys("validPass");
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
           
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            Assert.fail("Unexpected alert: " + alert.getText());
        } catch (TimeoutException e) {
          
            String actualUser = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser"))
            ).getText();

            Assert.assertTrue(actualUser.contains("Welcome"));
            System.out.println("Login successful: " + actualUser);
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

        System.out.println("Alert: " + msg);
        Assert.assertTrue(msg.contains("User does not exist"));

        alert.accept();
    }

    @Test(priority = 3)
    public void invalidpassword() {
        driver.findElement(By.id("login2")).click();
        driver.findElement(By.id("loginusername")).sendKeys("validUser");
        driver.findElement(By.id("loginpassword")).sendKeys("wrongPass");
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String msg = alert.getText();

        System.out.println("Alert: " + msg);
        Assert.assertTrue(msg.contains("Wrong password"));

        alert.accept();
    }

    @AfterMethod
    public void after() {
        driver.quit();
    }
}