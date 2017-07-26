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

public class LoginPage{
	
	private WebDriver driver;
	private JavascriptExecutor executer;
	
	@FindBy(id = "email")
	private WebElement uid;
	
	@FindBy(id = "password")
	private WebElement pwd;
	
	@FindBy(id = "btn_login")
	private WebElement login;
	
	@FindBy(id = "forgot_pass")
	private WebElement forgot;
	
	// test
	public String checkUrl(String url){
		String result = "fail";
		String expturl = driver.getCurrentUrl();
		if(url.equalsIgnoreCase(expturl)){
			result = "pass";
		}else{
			Reporter.log(expturl,true);
		}
		
		return result;
	}
	
	// test
    public String checkSureifyLogo(){
    	
        String result = "fail";
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("html/body/div[2]/div/div/div[1]/h1/img")))); 
        result = "pass";
    	
        return result;
    }
    
    // test
    public String checkSureifyText(){
    	
    	String result = "fail";
    	WebElement text = driver.findElement(By.xpath("html/body/div[2]/div/div/div[1]/h1/span"));
    	WebDriverWait wait = new WebDriverWait(driver, 60);
    	wait.until(ExpectedConditions.visibilityOf(text));
    	String expt = text.getText();
        if(expt.equalsIgnoreCase("SureifyLabs")){
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
    	String expt = elementIsPresent(uid);
    	if(expt.equals("pass")){
    		result = "pass";
    	}
    	
    	return result;
    }
    
    // test
    public String checkPasswordField(){

    	String result = "fail";
    	String expt = elementIsPresent(pwd);
    	if(expt.equals("pass")){
    		result = "pass";
    	}

    	return result;
    }
    
    // test
    public String checkLoginButton(){
    	
    	String result = "fail";
    	String expt = elementIsPresent(login);
    	if(expt.equals("pass")){
    		result = "pass";
    	}

    	return result;
    }
    
    // test
    public String checkForgotLink(){

    	String result = "fail";
    	String expt = elementIsPresent(forgot);
    	if(expt.equals("pass")){
    		result = "pass";
    	}

    	return result;
    }
    
    String checkErrMsg(WebElement ele , String msg){
    	
    	String result = "fail";
    	WebDriverWait wait = new WebDriverWait(driver, 60);
    	wait.until(ExpectedConditions.visibilityOf(ele));
    	if(ele.getText().contains(msg)){
    		result = "pass";
    	}
    	
    	return result;
    }
    
    public void clickLogin(String id,String pw) throws InterruptedException{
    	
    	if((Boolean)executer.executeScript("return $('#email').is(\":blank\");") == false){
    		uid.clear();	
    	}
    	uid.sendKeys(id);
    	Thread.sleep(3000);

    	if((Boolean)executer.executeScript("return $('#password').is(\":blank\");") == false){
    		pwd.clear();
    	}
    	pwd.sendKeys(pw);
    	Thread.sleep(3000);
    	login.click();
    }
    
    // Click on "Login" with no email ID and no password
    public String validateLogin1() throws InterruptedException{
    	
    	String result1 = null;
    	String result2 = null;
    	clickLogin("","");
    	String mail = checkErrMsg(driver.findElement(By.id("email-error")), "This field is required.");
    	String pwd  = checkErrMsg(driver.findElement(By.id("password-error")), "This field is required.");
    	
    	result1 = mail.equals("pass") ? "pass" : "fail";
    	result2 = pwd.equals("pass") ? "pass" : "fail";
    	
    	return result1.equals(result2) ? "pass" : "fail";
    	
    }
    
    // Click on "Login" with no email ID and password
    public String validateLogin2() throws InterruptedException{
    
    	clickLogin("", "Vendus@123");
    	return checkErrMsg(driver.findElement(By.id("email-error")),"This field is required.");
    	
    }
    
    // Click on "Login" with email ID and no password
    public String validateLogin3() throws InterruptedException{

    	clickLogin("sureshtest@gmail.com", "");
    	return checkErrMsg(driver.findElement(By.id("password-error")), "This field is required.");

    }
    
    // Enter invalid input - Unregistered email ID and valid password
    public String validateEmailField() throws InterruptedException{

    	clickLogin("sureshtest@gmai.com", "Vendus@123");
    	String res =  checkErrMsg(driver.findElement(By.xpath("//div[@class = 'alert alert-warning']")), "Invalid login details.");
        executer.executeScript("$('.close').click();");
        
        return res;
    }
    
    // Enter invalid input - registered email ID and in invalid password
    public String validatePwdField() throws InterruptedException{

    	clickLogin("sureshtest@gmail.com", "Vendus@1");
    	String res =  checkErrMsg(driver.findElement(By.xpath("//div[@class = 'alert alert-warning']")), "Invalid login details.");
        executer.executeScript("$('.close').click();");
        
        return res;
    }
    
    // Enter invalid email and invalid password
    public String InValidEmailAndPwd() throws InterruptedException{

    	clickLogin("sureshtest@gmai.com", "Vendus@1");
    	String res =  checkErrMsg(driver.findElement(By.xpath("//div[@class = 'alert alert-warning']")), "Invalid login details.");
        executer.executeScript("$('.close').click();");
        
        return res;
    }
    
    // Test
    public String clickForgotPassword(){
    	
    	String result = "fail";
    	forgot.click();
    	driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);
    	String ext = driver.getCurrentUrl();
    	if(ext.contains("forgotPassword")){
    		//driver.navigate().back();
    		//driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);	
    	    result = "pass";
    	}
    	
    	return result;
    }
    
    // TestinValidMailAndPwd
    public String validEmaiAndPwd() throws RiseException, InterruptedException{
    	
    	String result = "fail";
    	clickLogin("sureshtest05@gmail.com", "Vendus@123");
		driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
		String expt = driver.getCurrentUrl();
		if(expt.contains("carrier/dashboard/home")){
			result = "pass";
		}else{
			throw new RiseException("Exception occured It not redirect to home page");
		}
		Thread.sleep(5000);
		return result;

    }
   
    public LoginPage(WebDriver driver){
    	
    	this.driver = driver;
    	PageFactory.initElements(driver, this);
    	executer = (JavascriptExecutor)driver;
    	
    }
}
