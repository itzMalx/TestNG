package Com.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DPdemo {

    @DataProvider(name = "testData", parallel = true)
    public Object[][] dataprovFun() {
        return new Object[][] {
            {"Selenium"},
            {"TestNG"},
            {"Automation"}
        };
    }
    @Test(dataProvider = "testData")
    public void demoTest(String data) {
        System.out.println(data);
    }
}