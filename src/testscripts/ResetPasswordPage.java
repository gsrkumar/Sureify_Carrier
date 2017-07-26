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

public class ResetPasswordPage{
	
	private WebDriver driver;
	private JavascriptExecutor executer;
	
	@FindBy(id = "reset_login")
	private WebElement resetlogin;
	
	@FindBy(id = "recent_cancel")
	private WebElement resetcancel;
	
	@FindBy(id = "forgot_email")
	private WebElement mail;
	
	@FindBy(id = "forgot_reset")
	private WebElement reset;
	
    // test
    public String checkPwdResetText(){
    	
    	String result = "fail";
    	WebElement text = driver.findElement(By.xpath("//div[@class = 'text-center m-b-md']"));
    	WebDriverWait wait = new WebDriverWait(driver, 60);
    	wait.until(ExpectedConditions.visibilityOf(text));
    	String expt = text.getText();
        if(expt.trim().equals("Password Reset")){
        	result = "pass";  	
        }else{
        	Reporter.log(expt,true);
        }
        
        return result; 
    }
    
    // test
    public String checkResetDis(){

    	String result = "fail";
    	WebElement text = driver.findElement(By.xpath("html/body/div[2]/div/div/div[2]/div/div/div/div/h4"));
    	WebDriverWait wait = new WebDriverWait(driver, 60);
    	wait.until(ExpectedConditions.visibilityOf(text));
    	String expt = text.getText();
    	if(expt.trim().equals("We've sent you a link to reset your password. Please check your email. If you do not see it, check spam or try again.")){
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
    public String checkResetLoginField(){
    	
    	String result = "fail";
    	String expt = elementIsPresent(resetlogin);
    	if(expt.equals("pass")){
    		result = "pass";
    	}
    	
    	return result;
    }
    
    // test
    public String checkResetCancelLink(){
    	String result = "fail";
    	String expt = elementIsPresent(resetcancel);
    	if(expt.equals("pass")){
    		result = "pass";
    	}
    	return result;
    }
    
    // Test
    public String clickResetCancelLink() throws RiseException, InterruptedException{

    	String result = "fail";
    	resetcancel.click();
    	driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);
    	String ext = driver.getCurrentUrl();
    	if(ext.contains("login")){
    		driver.findElement(By.id("forgot_pass")).click();
    		driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);	
    		result = "pass";
    	}
        validEmai();
    	
    	return result;
    }
    
    // Test
    public String clickResetLogin() throws RiseException, InterruptedException{

    	String result = "fail";
    	resetlogin.click();
    	driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);
    	String ext = driver.getCurrentUrl();
    	if(ext.contains("login")){	
    		result = "pass";
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
    
    
    void validEmai() throws RiseException, InterruptedException{
    	
    	clickReset("sureshtest@gmail.com");
		driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
		String expt = driver.getCurrentUrl();
		if(expt.contains("carrier/authorization/passwordReset")){}else{
			throw new RiseException("It not redirect to home page");
		}

    }
   
    public ResetPasswordPage(WebDriver driver){
    	
    	this.driver = driver;
    	PageFactory.initElements(driver, this);
    	executer = (JavascriptExecutor)driver;
    	
    }
}
