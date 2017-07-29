package executeTests;

import org.testng.annotations.Test;

import com.org.home.methods.HomePage;
import com.org.util.framework.Driver;

import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;


public class AliexpressTest {
	
	public WebDriver driver;
	Driver driverFact = new Driver();
	String sheetName = "TestCases";

  @BeforeTest
  public void beforeTest() {
	  driverFact.initializeDriver();
	  driver = driverFact.getDriver();
  }
  
  @Test(groups="Smoke Test")
  public void searchProduct(final ITestContext testContext) {
	  HomePage mp = new HomePage(driver);
	  mp.launchapplication();
	  mp.searchByProductName("TC_001", "Product");
	  mp.getProductResults();
  }
  
  @AfterTest
  public void afterTest() {
	  driverFact.quitDriver();
  }

}
