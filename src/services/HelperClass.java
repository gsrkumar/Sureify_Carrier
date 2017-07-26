package services;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class HelperClass {
	
    private static String result = "fail";
    
   
	public static String changeOrder(WebDriver driver,WebElement ele){
		WebDriverWait wait = new WebDriverWait(driver, 180);
		try{
			if(ele.getAttribute("class").equals("sorting")){

				ele.click();
				wait.until(ExpectedConditions.attributeToBe(By.id("sales-quote_processing"), "style" ,"display: none;"));
				if(ele.getAttribute("class").equals("sorting_asc")){
					result = "pass";
				}else{
					Reporter.log("Fail change to Accending order",true);
				}

			}else if(ele.getAttribute("class").equals("sorting_asc")){

				ele.click();
				wait.until(ExpectedConditions.attributeToBe(By.id("sales-quote_processing"), "style" ,"display: none;"));
				if(ele.getAttribute("class").equals("sorting_desc")){
					result = "pass";
				}else{
					Reporter.log("Fail change to Deccending order",true);
				}

			}else if(ele.getAttribute("class").equals("sorting_desc")){

				ele.click();
				wait.until(ExpectedConditions.attributeToBe(By.id("sales-quote_processing"), "style" ,"display: none;"));
				if(ele.getAttribute("class").equals("sorting_asc")){
					result = "pass";
				}else{
					Reporter.log("Fail change to Accending order",true);
				}

			}else{}
		}catch(Exception e){
             
		     Reporter.log("Exception rised in ordering the records",true);
		}
		
		return result;

	}
	
	public static String downloadFile(String filename) throws AWTException{
        
		result = "fail";
		// create random object
		Random randomno = new Random();
		// get next next pseudorandom value 
		int value = randomno.nextInt();
		
		try{
			//Send file name to clip-board
			StringSelection x=new StringSelection(String.valueOf(value)+filename.toString());
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(x,null);
			//send clip-board text to note-pad window
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_CONTROL);

			r.delay(1500);

			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			
			Thread.sleep(5000);
			result = "pass";

		}catch(Exception e){
              Reporter.log("An exception occured while downloading file",true);
              e.printStackTrace();
		}
		
		return result;
	}
	
	public static String copyRecords(String filename) throws InterruptedException, AWTException, IOException{
		
		result = "fail";
		// create random object
		Random randomno = new Random();
		// get next next pseudorandom value 
		int value = randomno.nextInt();

		try{
			Robot r=new Robot();
			//Launch window based application(note-pad)
			r.keyPress(KeyEvent.VK_WINDOWS);
			r.keyRelease(KeyEvent.VK_WINDOWS);
			StringSelection y = new StringSelection("notepad");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(y,null);
			
			//send clip-board text to search window
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(5000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(5000);
			
			//send clip-board text to note-pad window
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(3000);
			
			//Close note-pad window
			r.keyPress(KeyEvent.VK_ALT);
			r.keyPress(KeyEvent.VK_F4);
			r.keyRelease(KeyEvent.VK_F4);
			r.keyRelease(KeyEvent.VK_ALT);
			Thread.sleep(5000);

			//click save
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(5000);

			//send file name to clip-board
			StringSelection y1 =new StringSelection((value+filename).toString());
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(y1,null);

			//send file name to save window
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(3000);
			result = "pass";
			
		}catch(Exception e){
			Reporter.log("Exception rised to save copyed records",true);
			e.printStackTrace();
		}
		
		return result;
	}
	
	/*
	 *  Checking Heading 
	 */

	public static String checkText(WebDriver driver,WebElement ele,String heading){

		String result = "fail";
		//WebElement text = driver.findElement(By.xpath(".//*[@id='wrapper']/div[1]/div/div/h2"));
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(ele));
		String expt = ele.getText();
		if(expt.trim().equals(heading)){
			result = "pass";  
		}else{
			Reporter.log(expt,true);
		}

		return result; 
	}
	
    /*
     *  Validate Last Name Field
     */
	
	public static String validateField(WebDriver driver,String data,String msg) throws InterruptedException{
		
		String result = "fail";
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement ele = driver.findElement(By.id("last_name"));
		js.executeScript("arguments[0].scrollIntoView(false);", ele);
		String check = (String)js.executeScript("return arguments[0].val()", ele);
		if(!check.equals("")){
			ele.clear();
		}
	
		ele.sendKeys(data);
		Actions a = new Actions(driver);
		a.sendKeys(Keys.TAB);
		Thread.sleep(4000);
	    WebElement err = driver.findElement(By.id("last_name-error"));
	    if(err.getText().trim().equals(msg)){
	    	result = "pass";
	    }
		
		return result;
		
	}
	
	/*
	 *  Validate First Name	
	 */
	public static String firstName(WebDriver driver,String data,String msg,String criteria) throws InterruptedException{
		
		String result = "fail";
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement ele = driver.findElement(By.id("first_name"));
		js.executeScript("arguments[0].scrollIntoView(false);", ele);
		String check = (String)js.executeScript("return $('#first_name').val();", ele);
		if(!check.equals("")){
			ele.clear();
		}
	
		ele.sendKeys(data);
		Thread.sleep(2000);
		driver.findElement(By.id("editCarrierProfile")).click();
		/*Actions a = new Actions(driver);
		a.sendKeys(Keys.TAB).perform();;*/
		Thread.sleep(2000);
		if(criteria.equals("invalid")){
			try{
				WebElement err = driver.findElement(By.id("first_name-error"));
				if(err.getText().trim().equals(msg)){
					result = "pass";
				}
			}catch(Exception e){
				e.getMessage();
                Reporter.log("Exception : Failed : Error message not displayed",true);
			}
		}else{
		    try{ 
		    	WebElement err = driver.findElement(By.id("first_name-error"));
		    	 //System.out.println("try block");
				if(!err.isDisplayed()){
					result = "pass";
				}
		    }catch(Exception e){
		    	e.getMessage();
		    	Reporter.log("Exception : Failed : Error message displayed",true);
		    }
		}
		
		return result;
		
	}
	
	/*
	 *  Validate Last Name	
	 */
	public static String lastName(WebDriver driver,String data,String msg,String criteria) throws InterruptedException{
		
		String result = "fail";
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement ele = driver.findElement(By.id("last_name"));
		js.executeScript("arguments[0].scrollIntoView(false);", ele);
		String check = (String)js.executeScript("return $('#last_name').val();", ele);
		if(!check.equals("")){
			ele.clear();
		}
	
		ele.sendKeys(data);
		Thread.sleep(2000);
		driver.findElement(By.id("editCarrierProfile")).click();
		/*Actions a = new Actions(driver);
		a.sendKeys(Keys.TAB).perform();;*/
		Thread.sleep(2000);
		if(criteria.equals("invalid")){
			try{
				WebElement err = driver.findElement(By.id("last_name-error"));
				if(err.getText().trim().equals(msg)){
					result = "pass";
				}
			}catch(Exception e){
				e.getMessage();
                Reporter.log("Exception : Failed : Error message not displayed",true);
			}
		}else{
		    try{ 
		    	WebElement err = driver.findElement(By.id("last_name-error"));
		    	 //System.out.println("try block");
				if(!err.isDisplayed()){
					result = "pass";
				}
		    }catch(Exception e){
		    	e.getMessage();
		    	Reporter.log("Exception : Failed : Error message displayed",true);
		    }
		}
		
		return result;
		
	}
	
	/*
	 *  Validate Email	
	 */
	public static String email(WebDriver driver,String data,String msg,String criteria) throws InterruptedException{

		String result = "fail";
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement ele = driver.findElement(By.id("email"));
		js.executeScript("arguments[0].scrollIntoView(false);", ele);
		String check = (String)js.executeScript("return $('#email').val();", ele);
		if(!check.equals("")){
			ele.clear();
		}

		ele.sendKeys(data);
		Thread.sleep(2000);
		driver.findElement(By.id("editCarrierProfile")).click();
		/*Actions a = new Actions(driver);
		a.sendKeys(Keys.TAB).perform();;*/
		Thread.sleep(2000);
		if(criteria.equals("invalid")){
			try{
				WebElement err = driver.findElement(By.id("email-error"));
				if(err.getText().trim().equals(msg)){
					result = "pass";
				}
			}catch(Exception e){
				e.printStackTrace();
				Reporter.log("Input :"+data,true);
				Reporter.log("Exception : Failed : Error message not displayed",true);
			}
		}else{
			try{ 
				WebElement err = driver.findElement(By.id("email-error"));
				//System.out.println("try block");
				if(!err.isDisplayed()){
					result = "pass";
				}
			}catch(Exception e){
				e.printStackTrace();
				Reporter.log("Exception : Failed : Error message displayed",true);
			}
		}

		return result;

	}
	
	/*
	 *  Validate Phonenumber	
	 */
	public static String phoneNumber(WebDriver driver,String data,String msg,String criteria) throws InterruptedException{

		String result = "fail";
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement ele = driver.findElement(By.id("phone_number"));
		js.executeScript("arguments[0].scrollIntoView(false);", ele);
		String check = (String)js.executeScript("return $('#phone_number').val();", ele);
		if(!check.equals("")){
			ele.clear();
		}

		ele.sendKeys(data);
		Thread.sleep(2000);
		driver.findElement(By.id("editCarrierProfile")).click();
		/*Actions a = new Actions(driver);
		a.sendKeys(Keys.TAB).perform();;*/
		Thread.sleep(2000);
		if(criteria.equals("invalid")){
			try{
				WebElement err = driver.findElement(By.id("phone_number-error"));
				if(err.getText().trim().equals(msg)){
					result = "pass";
				}
			}catch(Exception e){
				e.printStackTrace();
				Reporter.log("Input :"+data,true);
				Reporter.log("Exception : Failed : Error message not displayed",true);
			}
		}else{
			try{ 
				WebElement err = driver.findElement(By.id("phone_number-error"));
				//System.out.println("try block");
				if(!err.isDisplayed()){
					result = "pass";
				}
			}catch(Exception e){
				e.printStackTrace();
				Reporter.log("Exception : Failed : Error message displayed",true);
			}
		}

		return result;

	}
	
	/*
	 *  Validate DOB	
	 */
	public static String DOB(WebDriver driver,String data,String msg,String criteria) throws InterruptedException{

		String result = "fail";
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement ele = driver.findElement(By.id("date_of_birth"));
		js.executeScript("arguments[0].scrollIntoView(false);", ele);
		String check = (String)js.executeScript("return $('#date_of_birth').val();", ele);
		if(!check.equals("")){
			ele.clear();
		}

		ele.sendKeys(data);
		Thread.sleep(2000);
		driver.findElement(By.id("editCarrierProfile")).click();
		/*Actions a = new Actions(driver);
		a.sendKeys(Keys.TAB).perform();;*/
		Thread.sleep(2000);
		if(criteria.equals("invalid")){
			try{
				WebElement err = driver.findElement(By.id("date_of_birth-error"));
				if(err.getText().trim().equals(msg)){
					result = "pass";
				}
			}catch(Exception e){
				e.printStackTrace();
				Reporter.log("Input :"+data,true);
				Reporter.log("Exception : Failed : Error message not displayed",true);
			}
		}else{
			try{ 
				WebElement err = driver.findElement(By.id("date_of_birth-error"));
				//System.out.println("try block");
				if(!err.isDisplayed()){
					result = "pass";
				}
			}catch(Exception e){
				e.printStackTrace();
				Reporter.log("Exception : Failed : Error message displayed",true);
			}
		}

		return result;

	}
	
	
		
}
