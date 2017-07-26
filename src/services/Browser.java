package services;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

public class Browser {
	
  private static WebDriver driver;
  
	public static WebDriver getBrowser(String browsername){
		switch (browsername.trim().toUpperCase()) {
		case "CHROME":
			System.setProperty("webdriver.chrome.driver", "/home/pradeep/AutoTestingSoftwares/browser drivers/chromedriver");
			driver = new ChromeDriver();
			Reporter.log("CHROME Browser Lanched",true);
			break;
		case "IE":	

			Reporter.log("IE Browser Lanched",true);
			break;
		case "OPERA":
			
			Reporter.log("OPERA Browser Lanched",true);
			break;
		case "SAFARI":
			
			Reporter.log("SAFARIE Browser Lanched",true);
			break;
		case "FIREFOX":
			//System.setProperty("", "");
			System.setProperty("webdriver.firefox.marionette","/home/pradeep/AutoTestingSoftwares/browser drivers/geckodriver");
		   // System.setProperty("webdriver.gecko.driver", "/home/pradeep/AutoTestingSoftwares/browser drivers/geckodriver");
	        driver = new FirefoxDriver();
			Reporter.log("FIREFOX Browser Lanched",true);
			break;
		default:
			Reporter.log(browsername+"Invalid Browser Name ",true);
			break;
		}
		
		driver.manage().window().maximize();
		
		return driver;
	}
	
	
}
