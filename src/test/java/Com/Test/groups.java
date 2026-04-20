package Com.Test;

import org.testng.annotations.Test;

public class groups {
  @Test(groups="groupA")
  public void testMethod1ForGroupA() {
	  System.out.println("Running test method1 of groupA");
  }
  @Test(groups="groupA")
  public void testMethod21ForGroupA() {
	  System.out.println("Running tetst method2 of groupA");
  }
  @Test(groups="groupsB")
  public void tetsMethod1ForGroupB() {
	  System.out.println("Running test method1 of groupB");
  }
  @Test(groups="groupsB")
  public void testMethod2ForGroupB() {
	  System.out.println("Running test method2 of groupbB");
  }
  @Test(dependsOnGroups="groupA")
  public void dependentTestOnGroupA() {
	  System.out.println("Running the dependent test");
  }
  }
