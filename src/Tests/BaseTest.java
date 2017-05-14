 package Tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import Utilities.DriverRoutine;
import Utilities.DriverRoutine.DriverToUSe;
import TestData.TestData;

public class BaseTest {
	
	public static WebDriver driver;
	
	@BeforeTest 
	public WebDriver beforeTest(){
		 driver = new DriverRoutine().Create(TestData.CH_DRIVER_TO_U_SE);
		 return driver;
	}
	
	@AfterTest
	public void afterTest(){
		driver.close();
        if (driver != null)
        {
            driver.quit();                          
            //Nothing could be done if browser did not closed             
        }
	}
}
