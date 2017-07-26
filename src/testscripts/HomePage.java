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

import org.testng.Reporter;

import services.RiseException;

public class HomePage{
	
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor executer;
	
	
	@FindBy(id = "carrier_logo")
	private WebElement sureifylogo;
	
	@FindBy (xpath = "//div[@class = 'di pr pr10 username_carrier']")
	private WebElement username;
	
	@FindBy(id = "carrier_edit_profile")
	private WebElement editprofile;
	
	@FindBy(id = "carrier_change_pass")
	private WebElement pwdchange;
	
	@FindBy(xpath = "//*[@id = 'logoout_carrier']")
	private WebElement logout;
	
	@FindBy(xpath = ".//a[@id='devices']")
	private WebElement  iotlayout;
	
	@FindBy(xpath = ".//a[@id='wellness']//div[@class='wellness_image mt10']")
	private WebElement  wellnesslayout;
	
	@FindBy(xpath = ".//a[@id='products']//div[@class='products_image mt10']")
	private WebElement  productlayout;
	
	@FindBy(xpath = ".//a[@id='underwriting']")
	private WebElement  healthlayout;
	
	@FindBy(xpath = ".//a[@id='premiums']//div[@class='premiums_image mt10']")
	private WebElement  premiumlayout;
	
	@FindBy(xpath = ".//a[@id='engagements']//div[@class='engagement_image mt10']")
	private WebElement  engagementlayout;
	
	@FindBy(xpath = ".//a[@id='social_integration']")
	private WebElement  sociallayout;
	
	@FindBy(xpath = ".//a[@id='point_system']//div[@class='pointsystem_image mt10']")
	private WebElement  pointsyslayout;
	
	@FindBy(xpath = ".//a[@id='thanks']//div[@class='rewards_image mt10']")
	private WebElement  rewardlayout;
	
	@FindBy(xpath = ".//a[@id='app_analytics']//div[@class='appanalytics_image mt10']")
	private WebElement  appanlylayout;
	
	@FindBy(xpath = ".//a[@id='app_analytics']//div[@class='logistics_image mt10']")
	private WebElement  logisticslayout;
	
	@FindBy(xpath = ".//a[@id='sales']")
	private WebElement  saleslayout;
	
	@FindBy(xpath = ".//a[@id='policyholders']//div[@class='user_policy_image mt10']")
	private WebElement  policylayout;
	
	
	
	
	
	
	
	
	
    // test
    public String checkHomeSureifyLogo(){
    	
    	String result = "fail";
    	if(sureifylogo.isDisplayed()){
    		result = "pass";  	
    	}
    	return result; 
    }
    
    // test
    public String checkHeading(){

    	String result = "fail";
    	WebElement text = driver.findElement(By.xpath("//div[@class = 'hpanel']/div/h2"));
    	WebDriverWait wait = new WebDriverWait(driver, 60);
    	wait.until(ExpectedConditions.visibilityOf(text));
    	String expt = text.getText();
    	if(expt.trim().equals("HOME")){
    		result = "pass";  
    	}else{
    		Reporter.log(expt,true);
    	}

    	return result; 
    }
    
    
    // test
    public String checkHeadingDis(){

    	String result = "fail";
    	WebElement text = driver.findElement(By.xpath("//div[@class = 'hpanel']/div/small"));
    	WebDriverWait wait = new WebDriverWait(driver, 60);
    	wait.until(ExpectedConditions.visibilityOf(text));
    	String expt = text.getText();
    	if(expt.trim().equals("A quick way to access of the main pillars of ths platform.")){
    		result = "pass";  
    	}else{
    		Reporter.log(expt,true);
    	}

    	return result; 
    }
	
    // test
    public String checkUserName(){

    	String result = "fail";
    	
    	WebDriverWait wait = new WebDriverWait(driver, 60);
    	wait.until(ExpectedConditions.visibilityOf(username));
    	String expt = username.getText();
    	if(expt.trim().equals("Suresh")){
    		result = "pass";  
    	}else{
    		Reporter.log(expt,true);
    	}

    	return result; 
    }
    
    void clickProfile(){
    	
    	username.click();
    }
    // test
    public String checkProfileDropdown(){

    	String result = "fail";

    	WebDriverWait wait = new WebDriverWait(driver, 60);
    	clickProfile();
    	WebElement dropdown = driver.findElement(By.id("login_user_name"));
    	wait.until(ExpectedConditions.visibilityOf(dropdown));
    	result = "pass";
    	
    	return result; 
    }
    
    String elementIsPresent(WebElement ele){
    	
        WebDriverWait wait = new WebDriverWait(driver,60);
        wait.until(ExpectedConditions.visibilityOf(ele));
        return "pass";

    }
    
    // test
    public String checkEditProfile(){
    	
    	String result = "fail";
    	boolean v = (Boolean)executer.executeScript("return  $('#login_user_name').is(':visible')");
    	if(v == false){
    		clickProfile();
    	}
    	String expt = elementIsPresent(editprofile);
    	if(expt.equals("pass")){
    		result = "pass";
    	}
    	
    	return result;
    }
    
    
    // test
    public String checkChangePWD(){
    	
    	String result = "fail";
    	boolean v = (Boolean)executer.executeScript("return $('#login_user_name').is(':visible')");
    	if(v == false){
    		clickProfile();
    	}
    	String expt = elementIsPresent(pwdchange);
    	if(expt.equals("pass")){
    		result = "pass";
    	}

    	return result;
    }
    
    // test
    public String checkLogout(){
    	
    	String result = "fail";
    	boolean v = (Boolean)executer.executeScript("return $('#login_user_name').is(':visible')");
    	if(v == false){
    		clickProfile();
    	}
    	String expt = elementIsPresent(logout);
    	if(expt.equals("pass")){
    		result = "pass";
    	}

    	return result;
    }
    
    //test
    public String checkOverviewIsSelectedOrNot(){
    	
    	String result = "fail";
    	WebElement over = driver.findElement(By.xpath("//button[@data-id='menu-down']"));
    	String text = over.getAttribute("title");
    	if(text.trim().equals("Overview")){
    		result = "pass";
    	}
    	
    	return result;
    	
    }
    
    void clickDropDown(WebElement ele){

    	WebElement over = driver.findElement(By.xpath("//button[@data-id='menu-down']"));
    	//String v = over.getAttribute("aria-expanded");
    	if(!ele.isDisplayed()){
    		over.click();
    	}
    	
    }
    
    //test
    public String selectSales() throws InterruptedException{
    	
    	String result = "fail";
    	
    	WebElement sales = driver.findElement(By.xpath("//div[@class = 'dropdown-menu open']/ul/li[2]/a/span"));
    	clickDropDown(sales);
    	sales.click();
    	Thread.sleep(3000);
    	WebDriverWait wait = new WebDriverWait(driver, 60);
    	wait.until(ExpectedConditions.urlContains("quotes"));
        result = "pass";
        sureifylogo.click();
        Thread.sleep(5000);
        return result;
        
    }
    
    //test
    public String selectBehaviorData() throws InterruptedException{

    	String result = "fail";
    	
    	WebElement bhd = driver.findElement(By.xpath("//div[@class = 'dropdown-menu open']/ul/li[3]/a/span"));
    	clickDropDown(bhd);
    	bhd.click();
    	Thread.sleep(3000);
    	WebDriverWait wait = new WebDriverWait(driver, 60);
    	wait.until(ExpectedConditions.urlContains("deviceUsage"));
    	result = "pass";
    	sureifylogo.click();
    	Thread.sleep(5000);
    	//wait.until(ExpectedConditions.urlContains("carrier/dashboard/home"));
    	return result;

    }
    
    //test
    public String selectPHManagement() throws InterruptedException{

    	String result = "fail";
    	
    	WebElement phm = driver.findElement(By.xpath("//div[@class = 'dropdown-menu open']/ul/li[4]/a/span"));
    	clickDropDown(phm);
    	phm.click();
    	Thread.sleep(3000);
    	WebDriverWait wait = new WebDriverWait(driver, 60);
    	wait.until(ExpectedConditions.urlContains("activePolicyholders"));
    	result = "pass";
    	sureifylogo.click();
    	Thread.sleep(5000);
    	//wait.until(ExpectedConditions.urlContains("carrier/dashboard/home"));
    	return result;

    }
    
    //test
    public String selectEngagement() throws InterruptedException{

    	String result = "fail";
    	WebElement eng = driver.findElement(By.xpath("//div[@class = 'dropdown-menu open']/ul/li[5]/a/span"));
    	clickDropDown(eng);
    	eng.click();
    	Thread.sleep(3000);
    	WebDriverWait wait = new WebDriverWait(driver, 60);
    	wait.until(ExpectedConditions.urlContains("pointsForActivity"));
    	result = "pass";
    	sureifylogo.click();
    	Thread.sleep(5000);
    	//wait.until(ExpectedConditions.urlContains("carrier/dashboard/home"));
    	return result;

    }
    
    
    String clickLayout(WebElement ele,String acturl) throws InterruptedException{
    	
    	String result = "fail";
    	executer.executeScript("arguments[0].click()", ele);
    	driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
    	String expturl = driver.getCurrentUrl();
    	if(expturl.contains(acturl)){
    		result = "pass";
    	}else{
    		Reporter.log("Actual url :"+acturl+" , Expexted url :"+expturl,true);
    	} 
        Thread.sleep(4000);
        driver.navigate().back();
        Thread.sleep(5000);
        
       return result;
    	
    }
    
    // Test
    public String clickSalesLayout() throws InterruptedException{
    	
    	return clickLayout(saleslayout,"carrier/dashboard/quotes");
    	
    }
    
    // Test
    public String clickPolicyHolderLayout() throws InterruptedException{

    	return clickLayout(policylayout,"carrier/dashboard/activePolicyholders");

    }
    
    // Test
    public String clickIotDeviceLayout() throws InterruptedException{

    	return clickLayout(iotlayout,"carrier/dashboard/deviceUsage");

    }
    
    // Test
    public String clickWellnessLayout() throws InterruptedException{

    	return clickLayout(wellnesslayout,"carrier/challenges");

    }
    
    // Test
    public String clickProductsLayout() throws InterruptedException{

    	return clickLayout(productlayout,"carrier/dashboard/existingProducts");

    }
   
    // Test
    public String clickHealthLayout() throws InterruptedException{

    	return clickLayout(healthlayout,"carrier/dashboard/bloodAnalysis");

    }
    
    // Test
    public String clickPremiumLayout() throws InterruptedException{

    	return clickLayout(premiumlayout,"carrier/dashboard/aggregatePremiums");

    }
    
    // Test
    public String clickEngagementLayout() throws InterruptedException{

    	return clickLayout(engagementlayout,"carrier/engagement");

    }
    
    // Test
    public String clickSocialIntLayout() throws InterruptedException{

    	return clickLayout(sociallayout,"carrier/dashboard/socialIntegration");

    }
    
    // Test
    public String clickPointsLayout() throws InterruptedException{

    	return clickLayout(pointsyslayout,"carrier/dashboard/pointsForActivity");

    }
    
    // Test
    public String clickRewardsLayout() throws InterruptedException{

    	return clickLayout(rewardlayout,"carrier/dashboard/rewardsManager");

    }
    
    // Test
    public String clickAppAnlyLayout() throws InterruptedException{

    	return clickLayout(appanlylayout,"carrier/dashboard/appAnalytics");

    }
    
    // Test
    public String clickDeviceLogisLayout() throws InterruptedException{

    	return clickLayout(logisticslayout,"carrier/dashboard/deviceLogistics");

    }
    
    
    
    
    
     
    
    
    
    public HomePage(WebDriver driver){
    	
    	this.driver = driver;
    	PageFactory.initElements(driver, this);
    	executer = (JavascriptExecutor)driver;
    	wait  = new WebDriverWait(driver, 10000);
    	
    }
}
