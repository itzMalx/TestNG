package Com.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Dataproviderdemo1 {

    WebDriver driver;

    @DataProvider(name = "testdata")   
    public Object[][] dataprovFun() {
        return new Object[][] { {"Selenium"}, {"TestNG"} };
    }

    @BeforeMethod
    public void setUp() {
        System.out.println("Start the test");
        driver = new ChromeDriver();
        driver.get("https://www.bing.com/");
        driver.manage().window().maximize();
    }

    @Test(dataProvider = "testdata")   
    public void search(String keyWord) {
        WebElement txtBox = driver.findElement(By.id("sb_form_q"));
        txtBox.sendKeys(keyWord);
        System.out.println("Keyword entered is: " + keyWord);
        txtBox.sendKeys(Keys.ENTER);  
        System.out.println("Search result is displayed");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        System.out.println("End the test");
    }
}