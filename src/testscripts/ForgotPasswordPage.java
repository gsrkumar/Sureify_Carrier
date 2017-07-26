package testscripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import services.RiseException;

public class ForgotPasswordPage{
	
	private WebDriver driver;
	private JavascriptExecutor executer;
	
	@FindBy(id = "forgot_email")
	private WebElement mail;
	
	@FindBy(id = "forgot_reset")
	private WebElement reset;
	
	@FindBy(id = "carrier_cancel")
	private WebElement cancel;
	
    // test
    public String checkForgotText(){
    	
    	String result = "fail";
    	WebElement text = driver.findElement(By.xpath("//span[@class = 'color-light-ash']"));
    	WebDriverWait wait = new WebDriverWait(driver, 60);
    	wait.until(ExpectedConditions.visibilityOf(text));
    	String expt = text.getText();
        if(expt.equals("Forgot Password")){
        	result = "pass";  	
        }else{
        	Reporter.log(expt,true);
        }
        
        return result; 
    }
    
    // test
    public String checkForgotDis(){

    	String result = "fail";
    	WebElement text = driver.findElement(By.xpath("html/body/div[2]/div/div/div[2]/div/center[1]/h5"));
    	WebDriverWait wait = new WebDriverWait(driver, 60);
    	wait.until(ExpectedConditions.visibilityOf(text));
    	String expt = text.getText();
    	if(expt.trim().equals("Please enter your information and we'll reset your password for you.")){
    		result = "pass";  
    	}else{
    		Reporter.log(expt,true);
    	}

    	return result; 
    }
	

    
    String elementIsPresent(WebElement ele){
    	
        WebDriverWait wait = new WebDriverWait(driver,60);
        wait.until(ExpectedConditions.visibilityOf(ele));
        return "pass";

    }
    
    // test
    public String checkEmailField(){
    	
    	String result = "fail";
    	String expt = elementIsPresent(mail);
    	if(expt.equals("pass")){
    		result = "pass";
    	}
    	
    	return result;
    }
    
    
    // test
    public String checkResetButton(){
    	
    	String result = "fail";
    	String expt = elementIsPresent(reset);
    	if(expt.equals("pass")){
    		result = "pass";
    	}

    	return result;
    }
    
    // test
    public String checkCancelLink(){
    	String result = "fail";
    	String expt = elementIsPresent(cancel);
    	if(expt.equals("pass")){
    		result = "pass";
    	}

    	return result;
    }
    
    // Test
    public String clickCancelLink(){

    	String result = "fail";
    	cancel.click();
    	driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);
    	String ext = driver.getCurrentUrl();
    	if(ext.contains("login")){
    		driver.findElement(By.id("forgot_pass")).click();
    		driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);	
    		result = "pass";
    	}

    	return result;
    }

    String checkErrMsg(WebElement ele , String msg){
    	
    	String result = "fail";
    	WebDriverWait wait = new WebDriverWait(driver, 60);
    	wait.until(ExpectedConditions.visibilityOf(ele));
    	String emsg = ele.getText().trim();
    	if(emsg.equals(msg)){
    		result = "pass";
    	}else{
    		Reporter.log(emsg,true);
    	}
    	
    	return result;
    }
    
    void clickReset(String id) throws InterruptedException{
    	
    	if((Boolean)executer.executeScript("return $('#email').is(\":blank\");") == false){
    		mail.clear();	
    	}
    	
    	if(id != null){
    		mail.sendKeys(id);
    		Thread.sleep(3000);
    	}
    	reset.click();
    	Thread.sleep(3000);
    	
    }
    
    // Click on "Reset" with no email ID 
    public String validateReset1() throws InterruptedException{
    
    	clickReset(null);
    	return checkErrMsg(driver.findElement(By.id("forgot_email-error")),"This field is required.");
    	
    }
    
    // Enter invalid email 
    public String validateReset2() throws InterruptedException{

    	clickReset("sureshtest");
    	return checkErrMsg(driver.findElement(By.id("forgot_email-error")),"Please enter a valid email address.");

    }
    
    // Enter unregistered  email
    public String inValidEmail() throws InterruptedException{

    	clickReset("sureshtest@gmai.com");
    	String res =  checkErrMsg(driver.findElement(By.xpath("//div[@class = 'login_err_msg']")), "Invalid Email");
        
        return res;
    }
    
    
    
    // Test ValidMail
    public String validEmai() throws RiseException, InterruptedException{
    	
    	String result = "fail";
    	clickReset("sureshtest05@gmail.com");
		driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
		String expt = driver.getCurrentUrl();
		if(expt.contains("carrier/authorization/passwordReset")){
			result = "pass";
		}else{
			throw new RiseException("It not redirect to home page");
		}
		
		return result;

    }
   
    public ForgotPasswordPage(WebDriver driver){
    	
    	this.driver = driver;
    	PageFactory.initElements(driver, this);
    	executer = (JavascriptExecutor)driver;
    	
    }
}
