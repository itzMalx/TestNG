package Com.Test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class mals {

  @Test
  @Parameters({"val1","val2"})
  public void sum(int v1, int v2) {
      int finalSum = v1 + v2;
      System.out.println("The final sum is: " + finalSum);
  }

  @Test
  @Parameters({"val1","val2"})
  public void difference(int v1, int v2) {
      int finalDiff = v1 - v2;
      System.out.println("The difference is: " + finalDiff);
  }
}