package Com.Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class Test {

    public WebDriver driver;
    public WebDriverWait wait;

    By loginLink = By.id("login2");
    By usernameField = By.id("loginusername");
    By passwordField = By.id("loginpassword");
    By loginBtn = By.xpath("//button[text()='Log in']");
    By loginTitle = By.id("nameofuser");

    @BeforeMethod
    @Parameters("browser")
    public void parameterisedTest(String browser) {

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
            System.out.println("Chrome launched");
        } 
        else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            driver = new EdgeDriver(options);
            driver.manage().window().maximize();
            System.out.println("Edge launched");
        } 
        else {
            throw new RuntimeException("Invalid browser name");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://demoblaze.com/");
    }

    @Test
    @Parameters({"username","password"})
    public void validCredentials(String username, String password) {

        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);

        driver.findElement(loginBtn).click();

        String newPageText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(loginTitle)).getText();

        System.out.println("Login Text: " + newPageText);

        Assert.assertTrue(newPageText.contains("Welcome"), "Login Failed");
    }
}