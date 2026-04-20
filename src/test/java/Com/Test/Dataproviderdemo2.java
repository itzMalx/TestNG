package Com.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Dataproviderdemo2 {
	private static final ThreadLocal <WebDriver> driver=new ThreadLocal<webDriver>();
	@BeforeMethod
	public void setUp() {
		System.out.println("Start the test");
		driver.set(new ChromeDriver());
		@Test(dataProvider="testData",dataProviderClass=DpClass.class)
		public void search(String keyWord1,String keyWord2)throws InterruptedException{
			WebDriver driver1=driver.get();
			driver1.get("https://www.bing.com/");
			WebElemnt txtbox=driver1.indElement(By.id("sb_form_q"));
			txtBox.sendkeys(keyWord entered is:"+keyWord1+""+keyWord2)");
					txtBox.sendkeys(Keys_ENTER);
					System.outptintln("Search result is displayed:");
					
		}
		@AfterMethod
		public void burndown() {
			webdriver
		}
	}
  @Test
  public void f() {
  }
}
