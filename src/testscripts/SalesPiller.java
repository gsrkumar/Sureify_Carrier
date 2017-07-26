package testscripts;


import java.awt.AWTException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Reporter;

import services.RiseException;
import services.*;

public class SalesPiller{
	
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor executer;
	private Actions action;
	
	
	@FindBy(id = "carrier_logo")
	private WebElement sureifylogo;
	
	@FindBy(xpath = ".//a[@id='sales']")
	private WebElement  saleslayout;
	
	@FindBy(xpath = "//a[@id='carrier_quotes']/span")
	private WebElement quotes;
	
	@FindBy(xpath = "//a[@id='carrier_applications']/span")
	private WebElement application;
	
	@FindBy(xpath = "//a[@id='carrier_payments']/span")
	private WebElement payments;
	
	@FindBy(xpath = "//a[@id='carrier_policy_status']/span")
	private WebElement policystatus;
	
	@FindBy(xpath = "//a[@id='device_logistics']/span")
	private WebElement devicelogis;
	
	@FindBy(linkText = "Weekly")
	private WebElement weekly;
    
	@FindBy(linkText = "Monthly")
	private WebElement Monthly;
    
	@FindBy(linkText = "Yearly")
	private WebElement Yearly;
    
	@FindBy(name = "sales-quote_length")
	private WebElement recordselect;
	
	@FindBy(xpath = "//input[@type = 'search']")
	private WebElement searchbox;
	
	@FindBy(id = "delete_btn")
	private WebElement delete;
	
	@FindBy(xpath = (".//*[@id='wrapper']/div[2]/div[1]/div[1]/div/div/h1"))
	private WebElement userscount;
	
	@FindBy(id = "ZeroClipboard_TableToolsMovie_1")
	private WebElement copy;
	
	@FindBy(id = "ZeroClipboard_TableToolsMovie_2")
	private WebElement csv;
	
	@FindBy(id = "ZeroClipboard_TableToolsMovie_3")
	private WebElement pdf;
	
	@FindBy(id = "ToolTables_sales-quote_3")
	private WebElement print;
	
	@FindBy(name = "sales-application_length")
	private WebElement sales_application_length;
	
	String errmsgfname1 = "Please enter a valid input for first name.";
	String errmsgfname2 = "First name is required.";
	
	String errmsglname1 = "Please enter a valid input for last name.";
	String errmsglname2 = "Last name is required.";
	
	String errmsgemail1 = "Please enter a valid email.";
	String errmsgemail2 = "Email is required.";
	
	String errmsgphno1 = "Please enter a valid phone number.";
	String errmsgphno2 = "Phone number is required.";
	
	String errmsgdob = "This field is required.";
	
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
		
		Thread.sleep(5000);

		return result;

	}

	// Test
	public String clickSalesLayout() throws InterruptedException{

		return clickLayout(saleslayout,"carrier/dashboard/quotes");

	}
   
	// test
	public String checkHeading(){
       
		WebElement ele = driver.findElement(By.xpath(".//*[@id='wrapper']/div[1]/div/div/h2"));
		return HelperClass.checkText(driver, ele, "QUOTES");
		
		/*String result = "fail";
		WebElement text = driver.findElement(By.xpath(".//*[@id='wrapper']/div[1]/div/div/h2"));
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(text));
		String expt = text.getText();
		if(expt.trim().equals("QUOTES")){
			result = "pass";  
		}else{
			Reporter.log(expt,true);
		}

		return result; */
	}


	// test
	public String checkHeadingDis(){
		
		WebElement ele = driver.findElement(By.xpath(".//*[@id='wrapper']/div[1]/div/div/small"));
		String text = "Basic information about leads requesting a quote.";
        return HelperClass.checkText(driver, ele, text);
		
		/*String result = "fail";
		WebElement text = driver.findElement(By.xpath(".//*[@id='wrapper']/div[1]/div/div/small"));
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(text));
		String expt = text.getText();
		if(expt.trim().equals("Basic information about leads requesting a quote.")){
			result = "pass";  
		}else{
			Reporter.log(expt,true);
		}

		return result; */
	}
	
	String elementIsPresent(WebElement ele,String act){
        
		String result = "fail";
		WebDriverWait wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOf(ele));
		String expt = ele.getText();
		if(expt.trim().equals(act)){
			result = "pass";
		}else{
			Reporter.log("Actual :"+act+" , Expected :"+expt,true);
		}
		
		return result;

	}
    
	//test
    public String checkQuoteOption(){
    	
       return elementIsPresent(quotes, "QUOTES");
    }
    
    //test
    public String checkApplicationOption(){

    	return elementIsPresent(application, "APPLICATIONS");
    }
    
    //test
    public String checkPaymentsOption(){

    	return elementIsPresent(payments, "PAYMENTS");
    }

    //test
    public String checkPolicyStatusOption(){

    	return elementIsPresent(policystatus, "POLICY STATUS");
    }
    
    //test
    public String checkDeviceLogisticsOption(){

    	return elementIsPresent(devicelogis, "DEVICE LOGISTICS");
    }

    //test
    public String noOfPolicyHolders(){
    	
    	String result = "fail";
    	WebElement users = driver.findElement(By.xpath(".//*[@id='wrapper']/div[2]/div[1]/div[1]/div/div/h1"));
    	String first = users.getText().trim();
    	String last = ((String)executer.executeScript("return $('#sales-quote_info').text();")).trim();
    	if(last.contains(first)){
    		result = "pass";
    	}else{
    		Reporter.log("First :"+first+" ,Last :"+last.length(),true);
    	}
    	
    	return result;
    }
    
    //test
    public String clickWeekly() throws InterruptedException{
    	
    	String result = "fail";
    	weekly.click();
    	Thread.sleep(5000);
    	if(weekly.getAttribute("class").equals("redem-class redem-class-active")){
    		
    		result = "pass";
    	}
    	
    	return result;
    }
    
    //test
    public String clickMonthly() throws InterruptedException{

    	String result = "fail";
    	Monthly.click();
    	Thread.sleep(5000);
    	if(Monthly.getAttribute("class").equals("redem-class redem-class-active")){

    		result = "pass";
    	}

    	return result;
    }
    
    //test
    public String clickYearly() throws InterruptedException{

    	String result = "fail";
    	Yearly.click();
    	Thread.sleep(5000);
    	if(Yearly.getAttribute("class").equals("redem-class redem-class-active")){

    		result = "pass";
    	}

    	return result;
    }
    
    
    public String selectRecordRange(String len) throws InterruptedException{
    	
    	String result = "fail";
    	action.moveToElement(recordselect).perform();;
    	Select s = new Select(recordselect);
    	s.selectByValue(len);
    	wait.until(ExpectedConditions.attributeToBe(By.id("sales-quote_processing"), "style" ,"display: none;"));
    	Thread.sleep(4000);
    	WebElement table = driver.findElement(By.xpath("//table[@id = 'sales-quote']/tbody"));
    	int noforecords = table.findElements(By.tagName("tr")).size();
    	// getting records
    	 //System.out.println("No Of records :"+noforecords);
    	
    	WebElement users = driver.findElement(By.xpath(".//*[@id='wrapper']/div[2]/div[1]/div[1]/div/div/h1"));
    	long totalrecords = Long.parseLong(users.getText().trim().replaceAll("[^0-9]", ""));
    	//long totalrecords = 7;
    	if(!len.equals("-1")){
    	   if(Integer.parseInt(len) <= totalrecords){
    		   
    		   if(Integer.parseInt(len) == noforecords){
    			   result = "pass";
    		   }else{
    			   Reporter.log("Actual size :"+len+" , Expected size :"+noforecords,true);
    		   }
    	   }else{
    		   
    		   if(noforecords == totalrecords){
    			   result = "pass";
    		   }else{
    			   Reporter.log("Actual size :"+totalrecords+" , Expected size :"+noforecords,true);
    		   }
    	   }
    	}else{
    		
    		if(noforecords == totalrecords){
    			result = "pass";
    		}else{
    			Reporter.log("Actual size :"+totalrecords+" , Expected size :"+noforecords,true);
    		}
    		
    	}
    	
    	
		return result;
    	
    }
    
    // test
    public String select10Rows() throws InterruptedException{
    	return selectRecordRange("10");
    }
    
    // test
    public String select25Rows() throws InterruptedException{
    	return selectRecordRange("25");
    }
   
    // test
    public String select50Rows() throws InterruptedException{
    	return selectRecordRange("50");
    }
    
    // test
    public String select100Rows() throws InterruptedException{
    	return selectRecordRange("100");
    }
    
    // test
    public String selectAllRows() throws InterruptedException{
    	return selectRecordRange("-1");
    }
    
    String search(String name) throws InterruptedException{

    	String result = "pass";
    	searchbox.sendKeys(name);
    	wait.until(ExpectedConditions.attributeToBe(By.id("sales-quote_processing"), "style" ,"display: none;"));
    	Select s = new Select(recordselect);
    	s.selectByValue("-1");
    	wait.until(ExpectedConditions.attributeToBe(By.id("sales-quote_processing"), "style" ,"display: none;"));
    	Thread.sleep(3000);
    	WebElement table = driver.findElement(By.xpath("//table[@id = 'sales-quote']/tbody"));
    	int noforecords = table.findElements(By.tagName("tr")).size();
    	  //System.out.println("No of records found :"+noforecords);
        for(int row = 1;row<=noforecords;row++){
        	WebElement rowele = table.findElement(By.xpath("tr["+row+"]"));
        	String _name = rowele.findElement(By.xpath("td["+3+"]")).getText().toLowerCase();
        	//String _name = "ram";
        	String email = rowele.findElement(By.xpath("td["+4+"]")).getText().toLowerCase();
        	//String email = "sam";
        	if(!_name.contains(name.toLowerCase())){
        		if(!email.contains(name.toLowerCase())){
        			result = "fail";
        			Reporter.log("Keyword :"+name+" ,Name :"+_name+" ,Email :"+email,true);
        		}
        	}
        }
        
    	return result;
    }
    
    // test
    public String serachRecord() throws InterruptedException{
    	
    	return search("suresh");
    }
    
    String delete(String keyword,String operation) throws InterruptedException{
    	
    	String result = "fail";
    	String popup1 = "fail";
    	String popup2 = "fail";
    	long totalrecords = Long.parseLong(userscount.getText().trim().replaceAll("[^0-9]", ""));
    	 System.out.println("Before "+totalrecords);
    	searchbox.clear();
    	searchbox.sendKeys(keyword);
    	wait.until(ExpectedConditions.attributeToBe(By.id("sales-quote_processing"), "style" ,"display: none;"));
    	WebElement table = driver.findElement(By.xpath("//table[@id = 'sales-quote']/tbody"));
    	int noforecords = table.findElements(By.tagName("tr")).size();
    	if(noforecords != 0){
    		WebElement rowele = table.findElement(By.xpath("tr["+1+"]"));
        	rowele.findElement(By.xpath("td["+3+"]")).click();
        	delete.click();
        	Thread.sleep(4000);
        	if(operation.equalsIgnoreCase("delete")){
        		
        		// operation pop up display
        		WebElement yes = driver.findElement(By.id("ok_button"));
        		wait.until(ExpectedConditions.visibilityOf(yes));
        		executer.executeScript("$('#ok_button').click();");
        		Thread.sleep(4000);
        		
        		// OK poup display
        		WebElement ok = driver.findElement(By.id("ok_button"));
        		wait.until(ExpectedConditions.visibilityOf(ok));
        		String h1 = driver.findElement(By.xpath("//div[@id = 'Users_deleted.']/h2")).getText().trim();
        		String h2 = driver.findElement(By.xpath("//*[@id='Users_deleted.']/div")).getText().trim();
        		  //System.out.println(h1);
        		  //System.out.println(h2);
        		if(h1.equals("Success!")&&h2.equals("The Users has been deleted.")){
        			popup2 = "pass";
        		}
        		executer.executeScript("$('#ok_button').click();");
        		driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
        		long ofterdelete = Long.parseLong(userscount.getText().trim().replaceAll("[^0-9]", ""));
        		  System.out.println("After :"+ofterdelete);
        		if(totalrecords - ofterdelete == 1){
        			result = "pass";
        		}else{
        			Reporter.log("Failed delete operation"+(totalrecords - ofterdelete),true);
        		}
        		
        		result = popup2+","+result;
        	}else if(operation.equalsIgnoreCase("cancel")){
        		WebElement cancel = driver.findElement(By.id("cancel_button"));
        		wait.until(ExpectedConditions.visibilityOf(cancel));
        		
        		// check popup 1 text
        		String h1 = driver.findElement(By.xpath("//h2[@class = 'delete_text']")).getText().trim();
        		String h2 = driver.findElement(By.xpath("//div[@class = 'delete_content']")).getText().trim();
        		  //System.out.println(h1);
        		  //System.out.println(h2);
        		if(h1.equals("Are you sure?")&&h2.equals("You are permenantly deleting this users!")){
        			popup1 = "pass";
        		}
        		
        		cancel.click();
        		Thread.sleep(4000);
        		long ofterdelete = Long.parseLong(userscount.getText().trim().replaceAll("[^0-9]", ""));
        		if(totalrecords == ofterdelete){
        		    result = "pass";	
        		}else{
        			Reporter.log("Failed cancel operation",true);
        		}
        		
        		result = popup1+","+result;
        	}else{
        		Reporter.log("Invalid opration :"+operation,true);
        	}
        	
    	}else{
    		Reporter.log("No results found",true);
    		result = "No results found";
    	}
    	
    	return result;
    }
    
    // test
    public String  deleteRecord() throws InterruptedException{
    	System.out.println("Delete");
    	return delete("suresh@sureify.com", "delete");
    }
    
    //test
    public String cancelDelete() throws InterruptedException{
    	System.out.println("Cancel");
    	return delete("suresh@sureify.com", "cancel");
    }
    
    //test
    public String deleteWithoutSel() throws InterruptedException{
        
    	String result = "fail";
    	Thread.sleep(4000);
    	delete.click();
    	WebElement ok = driver.findElement(By.id("ok_button"));
		wait.until(ExpectedConditions.visibilityOf(ok));
		Thread.sleep(4000);
		// check popup 
		String h1 = driver.findElement(By.xpath("//h2[@class = 'warning_text']")).getText().trim();
		String h2 = driver.findElement(By.xpath("//div[@class = 'delete_content']")).getText().trim();
		  //System.out.println(h1);
		  //System.out.println(h2);
		if(h1.equals("Failed!")&&h2.equals("Select atleast one record for delete.")){
			result = "pass";
		}
		executer.executeScript("$('#ok_button').click();");
    	return result;
    }
    
    //test
    public String sortLastVisitedDatecolumn_Asc() throws InterruptedException{
    	
    	Thread.sleep(3000);
    	WebElement lvd = driver.findElement(By.xpath("//th[text() = 'Last Visited Date']"));
    	return HelperClass.changeOrder(driver,lvd);
    }
    
    //test
    public String sortLastVisitedDatecolumn_Desc() throws InterruptedException{
    	
    	Thread.sleep(3000);
    	WebElement lvd = driver.findElement(By.xpath("//th[text() = 'Last Visited Date']"));
    	return HelperClass.changeOrder(driver,lvd);
    }
    
    //test
    public String sortIDColumn_Asc() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement lvd = driver.findElement(By.id("quotes_id"));
    	return HelperClass.changeOrder(driver,lvd);
    }

    //test
    public String sortIDColumn_Desc() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement lvd = driver.findElement(By.id("quotes_id"));
    	return HelperClass.changeOrder(driver,lvd);
    }
    
    //test
    public String sortNameColumn_Asc() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement lvd = driver.findElement(By.id("quotes_name"));
    	return HelperClass.changeOrder(driver,lvd);
    }

    //test
    public String sortNameColumn_Desc() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement lvd = driver.findElement(By.id("quotes_name"));
    	return HelperClass.changeOrder(driver,lvd);
    }
    
    //test
    public String sortEmail_Asc() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement lvd = driver.findElement(By.id("quotes_email"));
    	return HelperClass.changeOrder(driver,lvd);
    }

    //test
    public String sortEmail_Desc() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement lvd = driver.findElement(By.id("quotes_email"));
    	return HelperClass.changeOrder(driver,lvd);
    }

    //test
    public String sortFaceAmount_Asc() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement lvd = driver.findElement(By.id("quotes_amount"));
    	return HelperClass.changeOrder(driver,lvd);
    }

    //test
    public String sortFaceAmount_Desc() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement lvd = driver.findElement(By.id("quotes_amount"));
    	return HelperClass.changeOrder(driver,lvd);
    }
    
    //test
    public String sortTermLen_Asc() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement lvd = driver.findElement(By.id("quotes_termlength"));
    	return HelperClass.changeOrder(driver,lvd);
    }

    //test
    public String sortTermLen_Desc() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement lvd = driver.findElement(By.id("quotes_termlength"));
    	return HelperClass.changeOrder(driver,lvd);
    }
    
    //test
    public String sortQuoteYearly_Asc() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement lvd = driver.findElement(By.id("quotes_yearlyquote"));
    	return HelperClass.changeOrder(driver,lvd);
    }

    //test
    public String sortQuoteYearly_Desc() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement lvd = driver.findElement(By.id("quotes_yearlyquote"));
    	return HelperClass.changeOrder(driver,lvd);
    }
    
    //test
    public String sortAge_Asc() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement lvd = driver.findElement(By.id("quotes_age"));
    	return HelperClass.changeOrder(driver,lvd);
    }

    //test
    public String sortAge_Desc() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement lvd = driver.findElement(By.id("quotes_age"));
    	return HelperClass.changeOrder(driver,lvd);
    }
    
    //test
    public String sortSex_Asc() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement lvd = driver.findElement(By.id("quotes_sex"));
    	return HelperClass.changeOrder(driver,lvd);
    }

    //test
    public String sortSex_Desc() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement lvd = driver.findElement(By.id("quotes_sex"));
    	return HelperClass.changeOrder(driver,lvd);
    }
    
    //test
    public String sortHealth_Asc() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement lvd = driver.findElement(By.id("quotes_healthstatus"));
    	return HelperClass.changeOrder(driver,lvd);
    }

    //test
    public String sortHealth_Desc() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement lvd = driver.findElement(By.id("quotes_healthstatus"));
    	return HelperClass.changeOrder(driver,lvd);
    }
    
    //test
    public String sortSmoke_Asc() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement lvd = driver.findElement(By.id("quotes_smoker"));
    	return HelperClass.changeOrder(driver,lvd);
    }

    //test
    public String sortSmoke_Desc() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement lvd = driver.findElement(By.id("quotes_smoker"));
    	return HelperClass.changeOrder(driver,lvd);
    }
    
    public String copyTable() throws InterruptedException, AWTException, IOException{
    	
    	String filepth = "/home/pradeep/suresh_workspace/CarrierDashboardAutomation/sales_quots"; 
    	copy.click();
    	Thread.sleep(2000);
    	return HelperClass.copyRecords(filepth);
    }
    
    public String downloadCSV() throws AWTException, InterruptedException{
    	
    	csv.click();
    	Thread.sleep(3000);
    	return HelperClass.downloadFile("sales_quotes");
     
    }
    
    public String downloadPDF() throws AWTException, InterruptedException{
    	
    	pdf.click();
    	Thread.sleep(3000);
    	return HelperClass.downloadFile("sales_quotes");

    }

    /*
     * 
     *  Applications in sales 
     */
    
    public String clickAppliactions() throws InterruptedException{
    	
    	String result = "fail";
    	application.click();
    	wait.until(ExpectedConditions.urlContains("applications"));
    	Thread.sleep(5000);
    	result = "pass";
    	
    	return result;
    }
    
    public String appHeading(){
    	
    	String result = "fail";
    	WebElement h = driver.findElement(By.xpath(".//*[@id='wrapper']/div[1]/div/div/h2"));
    	return HelperClass.checkText(driver, h, "Applications");
    
    }
    
    public String appHeadingDis(){

    	String result = "fail";
    	WebElement d = driver.findElement(By.xpath(".//*[@id='wrapper']/div[1]/div/div/small"));
    	return HelperClass.checkText(driver, d, "Aggregate view of all customers who are in the application process.");

    }
    
    // test
    public String appFirstModule(){
    	
    	String result = "fail";
    	String  progress = driver.findElement(By.xpath(".//*[@id='wrapper']/div[2]/div[1]/div[1]/div/div/h1")).getText().trim();
    	String p = "pass";
    	try { 
    		Integer.parseInt(progress);
    	} catch(NumberFormatException e) { 
    		
    		p = "fail";
    	} catch(NullPointerException e) {
    		//Reporter.log("Fail to display progress ",true);
    		p = "fail";
    	}
    	
    	WebElement d = driver.findElement(By.xpath(".//*[@id='wrapper']/div[2]/div[1]/div[1]/div/div/h3"));
    	String text =  HelperClass.checkText(driver, d, "Total Applications In Progress");
    	
    	if(p.equals("fail")){
    	  Reporter.log("Fail to display progress ",true);
    		if(text.equals("fail")){
    			Reporter.log("Fail to display progress text ",true);
    		}
    	}else{
    		result = "pass";
    		if(text.equals("fail")){
    			result = "fail";
    			Reporter.log("Fail to display progress text ",true);
    		}
    	}
    	
    	return result;
    }
    
    // test
    public String appSecondModule(){

    	String result = "fail";
    	String  progress = driver.findElement(By.xpath(".//*[@id='wrapper']/div[2]/div[1]/div[2]/div/div/h1")).getText().trim();
    	String p = "pass";
    	try { 
    		Integer.parseInt(progress);
    	} catch(NumberFormatException e) { 

    		p = "fail";
    	} catch(NullPointerException e) {
    		//Reporter.log("Fail to display progress ",true);
    		p = "fail";
    	}

    	WebElement d = driver.findElement(By.xpath(".//*[@id='wrapper']/div[2]/div[1]/div[2]/div/div/h3"));
    	String text =  HelperClass.checkText(driver, d, "Abandoned Applications");

    	if(p.equals("fail")){
    		Reporter.log("Fail to display  abandoned applications ",true);
    		if(text.equals("fail")){
    			Reporter.log("Fail to display  abandoned applications text ",true);
    		}
    	}else{
    		result = "pass";
    		if(text.equals("fail")){
    			result = "fail";
    			Reporter.log("Fail to display abandoned applications text ",true);
    		}
    	}

    	return result;
    }
    
    // test
    public String appThirdModulePaiChart(){
    	
    	String result = "fail";
    	WebElement pia =driver.findElement(By.id("applications_chart"));
    	if(pia.isDisplayed()){
    		result = "pass";
    	}else{
    		Reporter.log("Fail to diaplay pia chart",true);
    	}
    	
    	return result;	
    	
    }
    
    // test
    public String appCompletedStatus(){
    	
    	String result = "fail";
    	WebElement completed = driver.findElement(By.xpath(".//*[@id='js-legend']/ul/li[1]"));
    	if(HelperClass.checkText(driver, completed, "Completed").equals("pass")){

    		String color = completed.findElement(By.tagName("span")).getAttribute("style");
    		if(color.contains("#62CB31")){
                result = "pass";
    		}else{
                Reporter.log("Inavlid Completed color :"+color,true);
    		}
    		
    	}else{
    		Reporter.log("Fail to display Completed text",true);
    	}
    	
    	return result;
    }
    
    // test
    public String appInProgressStatus(){
        
    	String result = "fail";
    	WebElement progress = driver.findElement(By.xpath(".//*[@id='js-legend']/ul/li[2]"));
    	if(HelperClass.checkText(driver, progress, "In Progress").equals("pass")){

    		String color = progress.findElement(By.tagName("span")).getAttribute("style");
    		if(color.contains("#A3E186")){
                result = "pass";
    		}else{
                 Reporter.log("Invalid InProgress color :"+color,true);
    		} 
    	}else{
    		Reporter.log("Failed to display InProgress text",true);
    	}
    	
    	return result;

    }
    
    // test
    public String appAbandonedStatus(){
        
    	String result = "fail";
    	WebElement abandoned = driver.findElement(By.xpath(".//*[@id='js-legend']/ul/li[3]"));
    	if(HelperClass.checkText(driver, abandoned, "Abandoned").equals("pass")){

    		String color = abandoned.findElement(By.tagName("span")).getAttribute("style");
    		if(color.contains("#E0E0E0")){
                result = "pass";
    		}else{
                 Reporter.log("Invalid Abandoned color :"+color,true);
    		}
    	}else{
    		Reporter.log("Failed to display Abandoned text",true);
    	}
    	
    	return result;

    }
    
    public String selectApplicationRecordRange(String len) throws InterruptedException{

    	String result = "fail";
    	action.moveToElement(sales_application_length).perform();;
    	Select s = new Select(sales_application_length);
    	s.selectByValue(len);
    	wait.until(ExpectedConditions.attributeToBe(By.id("sales-application_processing"), "style" ,"display: none;"));
    	Thread.sleep(4000);
    	WebElement table = driver.findElement(By.xpath("//table[@id = 'sales-application']/tbody"));
    	int noforecords = table.findElements(By.tagName("tr")).size();
    	// getting records
    	//System.out.println("No Of records :"+noforecords);

    	WebElement progress = driver.findElement(By.xpath(".//*[@id='wrapper']/div[2]/div[1]/div[1]/div/div/h1"));
    	WebElement abandoned = driver.findElement(By.xpath(".//*[@id='wrapper']/div[2]/div[1]/div[2]/div/div/h1"));
    	long totalrecords = Long.parseLong(progress.getText().trim().replaceAll("[^0-9]", "")) +  Long.parseLong(abandoned.getText().trim().replaceAll("[^0-9]", ""));
    	//long totalrecords = 7;
    	if(!len.equals("-1")){
    		if(Integer.parseInt(len) <= totalrecords){

    			if(Integer.parseInt(len) == noforecords){
    				result = "pass";
    			}else{
    				Reporter.log("Actual size :"+len+" , Expected size :"+noforecords,true);
    			}
    		}else{

    			if(noforecords == totalrecords){
    				result = "pass";
    			}else{
    				Reporter.log("Actual size :"+totalrecords+" , Expected size :"+noforecords,true);
    			}
    		}
    	}else{

    		if(noforecords == totalrecords){
    			result = "pass";
    		}else{
    			Reporter.log("Actual size :"+totalrecords+" , Expected size :"+noforecords,true);
    		}

    	}

    	return result;

    }

    //test
    public String selectSalesapplication10Rows() throws InterruptedException{
    	return selectApplicationRecordRange("10");
    }

    // test
    public String selectSalesApplication25Rows() throws InterruptedException{
    	return selectApplicationRecordRange("25");
    }

    // test
    public String selectSalesApplication50Rows() throws InterruptedException{
    	return selectApplicationRecordRange("50");
    }

    // test
    public String selectSalesApplication100Rows() throws InterruptedException{
    	return selectApplicationRecordRange("100");
    }

    // test
    public String selectSalesApplicationAllRows() throws InterruptedException{
    	return selectApplicationRecordRange("-1");
    }
    
    String searchApp(String name) throws InterruptedException{

    	String result = "pass";
    	searchbox.sendKeys(name);
    	wait.until(ExpectedConditions.attributeToBe(By.id("sales-application_processing"), "style" ,"display: none;"));
    	Select s = new Select(sales_application_length);
    	s.selectByValue("-1");
    	wait.until(ExpectedConditions.attributeToBe(By.id("sales-application_processing"), "style" ,"display: none;"));
    	Thread.sleep(3000);
    	WebElement table = driver.findElement(By.xpath("//table[@id = 'sales-application']/tbody"));
    	int noforecords = table.findElements(By.tagName("tr")).size();
    	System.out.println("No of records found :"+noforecords);

    	for(int row = 1;row<=noforecords;row++){
    		WebElement rowele = table.findElement(By.xpath("tr["+row+"]"));
    		try{
    			String _name = rowele.findElement(By.xpath("td["+2+"]")).getText().toLowerCase();
    			if(!_name.contains(name.toLowerCase())){
    				result = "fail";
    				Reporter.log("Keyword :"+name+" ,Name :"+_name,true);
    			}
    		}catch(Exception e){
    			String _name = rowele.findElement(By.xpath("td["+1+"]")).getText().trim();
    			if(_name.equals("No data available")){
    				result = "No records found";
    				Reporter.log("No records found",true);
    			}
    			e.printStackTrace();
    		}
    	}
    	searchbox.clear();;  
    	return result;
    }
    
    // test
    public String searchApplication() throws InterruptedException{
   
    	return searchApp("suresh");
    }
    
  
    String deleteApplication(String keyword,String operation) throws InterruptedException{

    	String result = "fail";
    	String popup1 = "fail";
    	String popup2 = "fail";
    	WebElement progress = driver.findElement(By.xpath(".//*[@id='wrapper']/div[2]/div[1]/div[1]/div/div/h1"));
    	WebElement abandoned = driver.findElement(By.xpath(".//*[@id='wrapper']/div[2]/div[1]/div[2]/div/div/h1"));
    	long totalrecords = Long.parseLong(progress.getText().trim().replaceAll("[^0-9]", "")) +  Long.parseLong(abandoned.getText().trim().replaceAll("[^0-9]", ""));

    	System.out.println("Before "+totalrecords);
    	searchbox.clear();
    	searchbox.sendKeys(keyword);
    	wait.until(ExpectedConditions.attributeToBe(By.id("sales-application_processing"), "style" ,"display: none;"));
    	WebElement table = driver.findElement(By.xpath("//table[@id = 'sales-application']/tbody"));
    	int noforecords = table.findElements(By.tagName("tr")).size();
    	System.out.println("No of rows :"+noforecords);
    	try{
    		Thread.sleep(5000);
    		WebElement rowele = table.findElement(By.xpath("tr[1]"));
    		rowele.findElement(By.xpath("td[3]")).click();
    		delete.click();
    		Thread.sleep(4000);
    		if(operation.equalsIgnoreCase("delete")){

    			// operation pop up display
    			WebElement yes = driver.findElement(By.id("ok_button"));
    			wait.until(ExpectedConditions.visibilityOf(yes));
    			executer.executeScript("$('#ok_button').click();");
    			Thread.sleep(4000);

    			// OK poup display
    			WebElement ok = driver.findElement(By.id("ok_button"));
    			wait.until(ExpectedConditions.visibilityOf(ok));
    			String h1 = driver.findElement(By.xpath("//div[@id = 'Users_deleted.']/h2")).getText().trim();
    			String h2 = driver.findElement(By.xpath("//*[@id='Users_deleted.']/div")).getText().trim();
    			//System.out.println(h1);
    			//System.out.println(h2);
    			if(h1.equals("Success!")&&h2.equals("The Users has been deleted.")){
    				popup2 = "pass";
    			}
    			executer.executeScript("$('#ok_button').click();");
    			driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
    			progress = driver.findElement(By.xpath(".//*[@id='wrapper']/div[2]/div[1]/div[1]/div/div/h1"));
    			abandoned = driver.findElement(By.xpath(".//*[@id='wrapper']/div[2]/div[1]/div[2]/div/div/h1"));
    			long ofterdelete = Long.parseLong(progress.getText().trim().replaceAll("[^0-9]", "")) +  Long.parseLong(abandoned.getText().trim().replaceAll("[^0-9]", ""));
    			//long ofterdelete = Long.parseLong(userscount.getText().trim().replaceAll("[^0-9]", ""));
    			System.out.println("After :"+ofterdelete);
    			if(totalrecords - ofterdelete == 1){
    				result = "pass";
    			}else{
    				Reporter.log("Failed delete operation"+(totalrecords - ofterdelete),true);
    			}

    			result = popup2+","+result;
    		}else if(operation.equalsIgnoreCase("cancel")){
    			WebElement cancel = driver.findElement(By.id("cancel_button"));
    			wait.until(ExpectedConditions.visibilityOf(cancel));

    			// check popup 1 text
    			String h1 = driver.findElement(By.xpath("//h2[@class = 'delete_text']")).getText().trim();
    			String h2 = driver.findElement(By.xpath("//div[@class = 'delete_content']")).getText().trim();
    			//System.out.println(h1);
    			//System.out.println(h2);
    			if(h1.equals("Are you sure?")&&h2.equals("You are permenantly deleting this users!")){
    				popup1 = "pass";
    			}

    			cancel.click();
    			Thread.sleep(4000);
    			progress = driver.findElement(By.xpath(".//*[@id='wrapper']/div[2]/div[1]/div[1]/div/div/h1"));
    			abandoned = driver.findElement(By.xpath(".//*[@id='wrapper']/div[2]/div[1]/div[2]/div/div/h1"));
    			long ofterdelete = Long.parseLong(progress.getText().trim().replaceAll("[^0-9]", "")) +  Long.parseLong(abandoned.getText().trim().replaceAll("[^0-9]", ""));
    			if(totalrecords == ofterdelete){
    				result = "pass";	
    			}else{
    				Reporter.log("Failed cancel operation",true);
    			}

    			result = popup1+","+result;
    			rowele.findElement(By.xpath("td[3]")).click();
    		}else{
    			Reporter.log("Invalid opration :"+operation,true);
    		}
    	}catch(Exception e){
    		String _name = driver.findElement(By.xpath(".//*[@id='sales-application']/tbody/tr[1]/td[1]")).getText().trim();
    		if(_name.equals("No data available")){
    			result = "No records found";
    			Reporter.log("No records found",true);
    		}
            e.printStackTrace();
    	}

    	return result;
    }
    
    // test
    public String  deleteSalesApplication() throws InterruptedException{
    	System.out.println("Delete");
    	return deleteApplication("ssdsd", "delete");
    }
    
    //test
    public String cancelDeleteSalesApplication() throws InterruptedException{
    	System.out.println("Cancel");
    	return deleteApplication("ssdsd", "cancel");
    }
    
    public String changeOrder(WebElement ele){
    	String result = "fail";
		WebDriverWait wait = new WebDriverWait(driver, 180);
		try{
			 //System.out.println(ele.getAttribute("aria-label"));
			if(ele.getAttribute("aria-label").contains("ascending")){

				ele.click();
				wait.until(ExpectedConditions.attributeToBe(By.id("sales-application_processing"), "style" ,"display: none;"));
				  //System.out.println(ele.getAttribute("aria-sort"));
				if(ele.getAttribute("aria-label").contains("descending")){
					result = "pass";
				}else{
					Reporter.log("Fail change to Descending order",true);
				}

			}else if(ele.getAttribute("aria-label").contains("descending")){

				ele.click();
				wait.until(ExpectedConditions.attributeToBe(By.id("sales-application_processing"), "style" ,"display: none;"));
				   //System.out.println(ele.getAttribute("aria-sort"));
				if(ele.getAttribute("aria-label").contains("ascending")){
					result = "pass";
				}else{
					Reporter.log("Fail change to Ascending order",true);
				}

			}else{}
		}catch(Exception e){
             
		     Reporter.log("Exception rised in ordering the records",true);
		}
		
		return result;

	}
	
    //test
    public String sortSalesAppID_Asc() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement id = driver.findElement(By.id("app_id"));
    	return changeOrder(id);
    }
    
    //test
    public String sortSalesAppID_Dec() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement id = driver.findElement(By.id("app_id"));
    	return changeOrder(id);
    }
    
    //test
    public String sortSalesAppName_Asc() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement id = driver.findElement(By.id("app_name"));
    	return changeOrder(id);
    }
    
    //test
    public String sortSalesAppName_Dec() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement id = driver.findElement(By.id("app_name"));
    	return changeOrder(id);
    }

    //test
    public String sortSalesAppFaceAmt_Asc() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement id = driver.findElement(By.id("app_faceamount"));
    	return changeOrder(id);
    }
    
    //test
    public String sortSalesAppFaceAmt_Dec() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement id = driver.findElement(By.id("app_faceamount"));
    	return changeOrder(id);
    }
    
    //test
    public String sortSalesAppTermLength_Asc() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement id = driver.findElement(By.id("app_termlength"));
    	return changeOrder(id);
    }
    
    //test
    public String sortSalesAppTermLength_Dec() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement id = driver.findElement(By.id("app_termlength"));
    	return changeOrder(id);
    }
    
    //test
    public String sortSalesAppProgress_Asc() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement id = driver.findElement(By.id("app_progress"));
    	return changeOrder(id);
    }
    
    //test
    public String sortSalesAppProgress_Dec() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement id = driver.findElement(By.id("app_progress"));
    	return changeOrder(id);
    }
    
    //test
    public String sortSalesAppStage_Asc() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement id = driver.findElement(By.id("app_stage"));
    	return changeOrder(id);
    }
    
    //test
    public String sortSalesAppStage_Dec() throws InterruptedException{

    	Thread.sleep(3000);
    	WebElement id = driver.findElement(By.id("app_stage"));
    	return changeOrder(id);
    }
    
    public String downloadSalesAppCSV() throws AWTException, InterruptedException{

    	csv.click();
    	Thread.sleep(3000);
    	return HelperClass.downloadFile("sales_application");

    }

    public String downloadSalesAppPDF() throws AWTException, InterruptedException{

    	pdf.click();
    	Thread.sleep(3000);
    	return HelperClass.downloadFile("sales_application");

    }
    
   /*
    *  Sales ApplicationDetails
    */
    public String click_Id_Sales_AppDetails() throws InterruptedException{
    	
    	String result = "fail";
        if(searchApp("suresh").equalsIgnoreCase("pass")){
           Thread.sleep(4000);
           driver.findElement(By.xpath("//table[@id = 'sales-application']/tbody/tr[1]/td[1]/a")).click();
           try{
              wait.until(ExpectedConditions.urlContains("applicationDetails"));
              Thread.sleep(3000);
              result = "pass";
           }catch(Exception e){
        	   
        	   String url = driver.getCurrentUrl();
        	   Reporter.log("Fail:Url :"+url,true);
        	   
           }
          
        }
        
    	return result;
    }
     
    public String click_Name_Sales_AppDetails() throws InterruptedException{

    	String result = "fail";
    	if(searchApp("suresh").equalsIgnoreCase("pass")){
    		Thread.sleep(4000);
    		driver.findElement(By.xpath("//table[@id = 'sales-application']/tbody/tr[1]/td[2]/a")).click();
    		try{
    			wait.until(ExpectedConditions.urlContains("applicationDetails"));
    			Thread.sleep(3000);
    			result = "pass";
    		}catch(Exception e){

    			String url = driver.getCurrentUrl();
    			Reporter.log("Fail:Url :"+url,true);

    		}

    	}

    	return result;
    }
     
    public String appDetailsHeading(){
    	
    	WebElement h = driver.findElement(By.xpath(".//*[@id='wrapper']/div[1]/div/div/h2"));
    	return HelperClass.checkText(driver, h, "APPLICATION DETAILS");

    }

    public String appDetailsHeadingDis(){
    	
    	WebElement d = driver.findElement(By.xpath(".//*[@id='wrapper']/div[1]/div/div/small"));
    	return HelperClass.checkText(driver, d, "Detailed view of customer.");

    }
    
    public String clickSalesAppDetailsAppLink() throws InterruptedException{
    	
    	String result = "fail";
    	WebElement d = driver.findElement(By.id("app_details_app_link"));
    	d.click();
    	Thread.sleep(4000);
    	if(d.getAttribute("aria-expanded").equals("true")){
    		result = "pass";
    	}
    	
    	return result;

    }
    
    public String checkSalesAppDetailsDownload(){

    	String result = "fail";
    	WebElement d = driver.findElement(By.id("download"));
    	if(d.isDisplayed()){
    		result = "pass";
    	}

    	return result;

    }
    
    public String checkSalesAppDetailsPrint(){

    	String result = "fail";
    	WebElement d = driver.findElement(By.id("print"));
    	if(d.isDisplayed()){
    		result = "pass";
    	}

    	return result;

    }
    
    public String clickSalesAppDetailsQuoteLink() throws InterruptedException{

    	String result = "fail";
    	WebElement d = driver.findElement(By.id("app_quotes_link"));
    	d.click();
    	Thread.sleep(4000);
    	if(d.getAttribute("aria-expanded").equals("true")){
    		result = "pass";
    	}

    	return result;

    }
    
    public String clickSalesEditApp(){
    	
    	String result = "fail";
        WebElement edit = driver.findElement(By.id("app_details_id"));
    	if(edit.isDisplayed()){
    		edit.click();
    		try{
    			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("editCarrierProfile")));
    			result = "pass";
    		}catch(Exception e){
    			result = "fail";
    			e.printStackTrace();
    		}
    		
    	}else{
    		Reporter.log("Edit option was not displaying",true);
    		result = "Edit not displyed";
    	}
    	
    	return result;
    }
    
    /*
     *  First Last
     * 
     */
    public String invalidFirstName1() throws InterruptedException{
    
    	 return HelperClass.firstName(driver, "1234567", errmsgfname1,"invalid");
    }
    
    
    public String invalidFirstName2() throws InterruptedException{

    	return HelperClass.firstName(driver, "!@#$%^&*()-.'+", errmsgfname1,"invalid");
    }
    
    public String invalidFirstName3() throws InterruptedException{

    	return HelperClass.firstName(driver, "ABC123", errmsgfname1,"invalid");
    }
    
    public String invalidFirstName4() throws InterruptedException{

    	return HelperClass.firstName(driver, "ABC!@#$%^", errmsgfname1,"invalid");
    }
    
    public String invalidFirstName5() throws InterruptedException{

    	return HelperClass.firstName(driver, "      ", errmsgfname1,"invalid");
    }
    
    public String invalidFirstName6() throws InterruptedException{

    	return HelperClass.firstName(driver, "", errmsgfname2,"invalid");
    }

    public String validFirstName1() throws InterruptedException{
        
   	 return HelperClass.firstName(driver, "Suresh Kumar", errmsgfname1,"valid");
   	 
    }
    
    public String validFirstName2() throws InterruptedException{

    	return HelperClass.firstName(driver, "G-Suresh'Kumar.", errmsgfname1,"valid");

    }
    
    public String validFirstName3() throws InterruptedException{

    	return HelperClass.firstName(driver, "sureshkumar", errmsgfname1,"valid");

    }
    
    public String validFirstName4() throws InterruptedException{

    	return HelperClass.firstName(driver, "SURESH", errmsgfname1,"valid");

    }

    /*
     * 
     * Last Name
     * 
     */
    
    public String invalidLastName1() throws InterruptedException{

    	return HelperClass.lastName(driver, "1234567", errmsglname1,"invalid");
    }

   public String invalidLastName2() throws InterruptedException{

   	return HelperClass.lastName(driver, "!@#$%^&*()-.'+", errmsglname1,"invalid");
   }
   
   public String invalidLastName3() throws InterruptedException{

   	return HelperClass.lastName(driver, "ABC123", errmsglname1,"invalid");
   }
   
   public String invalidLastName4() throws InterruptedException{

   	return HelperClass.lastName(driver, "ABC!@#$%^", errmsglname1,"invalid");
   }
   
   public String invalidLastName5() throws InterruptedException{

   	return HelperClass.lastName(driver, "      ", errmsglname1,"invalid");
   }

   public String invalidLastName6() throws InterruptedException{

	   return HelperClass.lastName(driver, "", errmsglname2,"invalid");
   }

   
   
   public String validLastName1() throws InterruptedException{
       
  	 return HelperClass.lastName(driver, "Ratna Kumar", errmsglname1,"valid");
  	 
   }
   
   public String validLastName2() throws InterruptedException{

   	return HelperClass.lastName(driver, "S-Ratna'Kumar.", errmsglname1,"valid");

   }
   
   public String validLastName3() throws InterruptedException{

   	return HelperClass.lastName(driver, "ratnakkumar", errmsglname1,"valid");

   }
   
   public String validLastName4() throws InterruptedException{

   	return HelperClass.lastName(driver, "KUMAR", errmsglname1,"valid");

   }
   
   /*
    *  Email
    *     
    */
   
   public String invalidEmail1() throws InterruptedException{

	   return HelperClass.email(driver, "suresh@sureify", errmsgemail1,"invalid");
   }
   
   public String invalidEmail2() throws InterruptedException{

	   return HelperClass.email(driver, "suresh", errmsgemail1,"invalid");
   }
   
   public String invalidEmail3() throws InterruptedException{

	   return HelperClass.email(driver, "sureshsureify.com", errmsgemail1,"invalid");
   }
   
   public String invalidEmail4() throws InterruptedException{

	   return HelperClass.email(driver, "suresh@sureifycom", errmsgemail1,"invalid");
   }
   
   public String invalidEmail5() throws InterruptedException{

	   return HelperClass.email(driver, "@suresh.com", errmsgemail1,"invalid");
   }
   
   public String invalidEmail6() throws InterruptedException{

	   return HelperClass.email(driver, "suresh@sureify.co.", errmsgemail1,"invalid");
   }
   
   public String invalidEmail7() throws InterruptedException{

	   return HelperClass.email(driver, "suresh kumar@sureify.com", errmsgemail1,"invalid");
   }
   
   public String invalidEmail8() throws InterruptedException{

	   return HelperClass.email(driver, "!@#@%#^$^%@@sureify.com", errmsgemail1,"invalid");
   }
   
   public String invalidEmail9() throws InterruptedException{

	   return HelperClass.email(driver, "          ", errmsgemail1,"invalid");
   }
   
   public String invalidEmail10() throws InterruptedException{

	   return HelperClass.email(driver, "", errmsgemail2,"invalid");
   }
   
   public String validEmail1() throws InterruptedException{

	   return HelperClass.email(driver, "s@x.c", errmsgemail1,"valid");
   }
   
   public String validEmail2() throws InterruptedException{
	   return HelperClass.email(driver, "suresh@sureify.co.in", errmsgemail1,"valid");
   }
   
   public String validEmail3() throws InterruptedException{
	   return HelperClass.email(driver, "suresh@sureify.com", errmsgemail1,"valid");
   }
  
    
   /*
    * Phone number
    * 
    */
   
   public String invalidPhoneNo1() throws InterruptedException{

	   return HelperClass.phoneNumber(driver, "9", errmsgphno1,"invalid");
   }

   public String invalidPhoneNo2() throws InterruptedException{

	   return HelperClass.phoneNumber(driver, "964", errmsgphno1,"invalid");
   }

   public String invalidPhoneNo3() throws InterruptedException{

	   return HelperClass.phoneNumber(driver, "996655889", errmsgphno1,"invalid");
   }
   
   public String invalidPhoneNo4() throws InterruptedException{

	   return HelperClass.phoneNumber(driver, "", errmsgphno2,"invalid");
   }
   
   public String validPhoneNo1() throws InterruptedException{

	   return HelperClass.phoneNumber(driver, "9944556677", errmsgphno1,"valid");
   }
   
   /*
    *  DOB
    * 
    */
    
   public String invalidDOB() throws InterruptedException{

	   return HelperClass.DOB(driver, "", errmsgdob,"invalid");
   }
   
   public String validDoB() throws InterruptedException{

	   return HelperClass.DOB(driver, "19900705", errmsgdob,"valid");
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
	public SalesPiller(WebDriver driver){

		this.driver = driver;
		PageFactory.initElements(driver, this);
		executer = (JavascriptExecutor)driver;
		wait  = new WebDriverWait(driver,60);
     	action = new Actions(driver);

	}
}
