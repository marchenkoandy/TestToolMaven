package Utilities;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import TestData.TestData;

public class DriverRoutine {
	 public static Logger logg = Logger.getLogger("");
	 public enum DriverToUSe { Firefox, Chrome }
	 public WebDriver Create(DriverToUSe eDriver)
     {
         WebDriver driver;
//         String sPath = "D:\\Install_MY\\SeleniumHQ\\drivers";
         switch (eDriver)
         {
             case Chrome:
//            	 sPath+= "\\chromedriver.exe";
//                 System.setProperty("webdriver.chrome.driver", sPath);
            	 ChromeDriverManager.getInstance().setup();
            	 driver = new ChromeDriver();
                 break;
             case Firefox:
//            	 sPath += "\\geckodriver.exe";
//            	 System.setProperty("webdriver.gecko.driver", sPath);
            	 FirefoxDriverManager.getInstance().setup();
                 driver = new FirefoxDriver();
                 break;            
             default:
            	 driver=null;
                 
         }       
         logg.info("Was loaded \"" + eDriver.toString() + "\"");
         driver.manage().timeouts().setScriptTimeout(TestData.WAIT_IMPLICIT,TimeUnit.SECONDS);
         return driver;                            
     }
	 public void Dismiss(WebDriver driver)
     {
         driver.close();
         if (driver != null)
         {
             driver.quit();                          
             //Nothing could be done if browser did not closed             
         }
     }
}
