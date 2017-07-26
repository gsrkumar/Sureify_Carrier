package controller;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.swing.tree.ExpandVetoException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import services.Browser;
import services.RiseException;
import services.URLClass;
import services.WriteResults;
import testscripts.ForgotPasswordPage;
import testscripts.HomePage;
import testscripts.LoginPage;
import testscripts.ResetPasswordPage;
import testscripts.SalesPiller;

public class TestNgClass {
	
	private WebDriver driver = null;
	private Map<String , String> resultmap = null;
	private String filename = null;
	
	private LoginPage login = null;
	private ForgotPasswordPage forgotpwd = null;
	private ResetPasswordPage resetpwd = null;
	private HomePage homepage = null;
	private SalesPiller salespiller = null;
	
	@Parameters({"browsername","filename"})
	@BeforeTest
	public void setUp(@Optional ("firefox") String browsername,String filename){
		
		resultmap = new HashMap<String,String>();
		this.filename = filename;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String datestring = dateFormat.format(new Date());
		// Launch browser
		driver = Browser.getBrowser(browsername);
		resultmap.put("H3",browsername.toUpperCase());
		resultmap.put("H2", datestring);
		
	}
	
	
	/*@Parameters({"browsername","filename"})
	@Test(groups = {"regressiontest","smoketest"},priority = 0)
	public void setUp(@Optional ("firefox") String browsername,String filename) {
		
		resultmap = new HashMap<String,String>();
		this.filename = filename;
		driver = Browser.getBrowser(browsername);
		resultmap.put("E3",browsername);
		
	}*/
	
	/**
	 * Launch site and creating instance for login page  
	 * @param url
	 */
	
	@Parameters("url")
	@Test(groups = {"regressiontest","smoketest"},priority = 1)
	public void launchSite(String url){
		
		driver.get(URLClass.getUrl(url));
		driver.manage().timeouts().pageLoadTimeout(2,TimeUnit.MINUTES);
		login = new LoginPage(driver);
		
		String result = login.checkUrl(URLClass.getUrl(url));
		resultmap.put("J10", result);
	       
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "launchSite",priority = 2)
	public void checkSureifyLogo(){
		
		String result = login.checkSureifyLogo();
		resultmap.put("J11", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "launchSite",priority = 3)
	public void checkSureifytext(){
		
		String result = login.checkSureifyText();
		resultmap.put("J12", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "launchSite",priority = 4)
	public void checkEmailField(){
		
		String result = login.checkEmailField();
		resultmap.put("J13", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "launchSite",priority = 5)
	public void checkPasswordField(){
		
		String result = login.checkPasswordField();
		resultmap.put("J14", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "launchSite",priority = 6)
	public void checkFogotLink(){
		
		String result = login.checkForgotLink();
		resultmap.put("J15", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "launchSite",priority = 7)
	public void checkLoginBtn(){
		
		String result = login.checkLoginButton();
		resultmap.put("J16", result);
		
	}
	
	
	// Click on "Login" with no email ID and no password
	@Test(groups = {"regressiontest"},dependsOnMethods = "launchSite",priority = 8)
	public void validateLogin1() throws InterruptedException{
		
		String result = login.validateLogin1();
		resultmap.put("J17", result);
		
	}
	
	// Click on "Login" with no email ID and password
	@Test(groups = {"regressiontest"},dependsOnMethods = "launchSite",priority = 9)
	public void validateLogin2() throws InterruptedException{

		String result = login.validateLogin2();
		resultmap.put("J18", result);

	}
	
	// Click on "Login" with email ID and no password
	@Test(groups = {"regressiontest"},dependsOnMethods = "launchSite",priority = 10)
	public void validateLogin3() throws InterruptedException{

		String result = login.validateLogin3();
		resultmap.put("J19", result);

	}
    
	// Enter invalid input - Unregistered email ID and valid password
	@Test(groups = {"regressiontest"},dependsOnMethods = "launchSite",priority = 11)
	public void validateEmailField() throws InterruptedException{

		String result = login.validateEmailField();
		resultmap.put("J20", result);

	}
	
	// Enter invalid input - registered email ID and in invalid password
	@Test(groups = {"regressiontest"},dependsOnMethods = "launchSite",priority = 12)
	public void validatePwdField() throws InterruptedException{

		String result = login.validatePwdField();
		resultmap.put("J21", result);

	}
	
	// Enter invalid email and invalid password
	@Test(groups = {"regressiontest"},dependsOnMethods = "launchSite",priority = 13)
	public void inValidMailAndPwd() throws InterruptedException{

		String result = login.InValidEmailAndPwd();
		resultmap.put("J22", result);

	}
	
	// Click Forgot password link
	@Test(groups = {"regressiontest"},dependsOnMethods = "launchSite",priority = 14)
	public void clickkForgotPwd() throws InterruptedException{

		String result = login.clickForgotPassword();
		resultmap.put("J23", result);

	}
	
	// Enter vailid email and password
	@Test(groups = {"regressiontest","smoketest"},dependsOnMethods = "launchSite",priority = 15)
	public void validEmailAndPwd() throws InterruptedException, RiseException{

		String result = login.validEmaiAndPwd();
		resultmap.put("J24", result);

	}
	
	/**
	 *  Creating instance for forgot password page 
	 */
	
	@Test(groups = {"regressiontest","smoketest"},priority = 16)
	public void forgotPasswordPage(){
		
		forgotpwd = new ForgotPasswordPage(driver);
	}
	
	// Check Forgot password text
	@Test(groups = {"regressiontest","smoketest"},dependsOnMethods = "forgotPasswordPage",priority = 17)
	public void checkForgotText() throws InterruptedException, RiseException{

		String result = forgotpwd.checkForgotText();
		resultmap.put("J25", result);

	}
	
	// Check Description
	@Test(groups = {"regressiontest"},dependsOnMethods = "forgotPasswordPage",priority = 18)
	public void checkForgotDis() throws InterruptedException, RiseException{

		String result = forgotpwd.checkForgotDis();
		resultmap.put("J26", result);

	}
	
	// Check Description
	@Test(groups = {"regressiontest"},dependsOnMethods = "forgotPasswordPage",priority = 19)
	public void checkMailField() throws InterruptedException, RiseException{

		String result = forgotpwd.checkEmailField();
		resultmap.put("J27", result);

	}
	
	// Check Reset button field
	@Test(groups = {"regressiontest"},dependsOnMethods = "forgotPasswordPage",priority = 20)
	public void checkResetButton() throws InterruptedException, RiseException{

		String result = forgotpwd.checkResetButton();
		resultmap.put("J28", result);

	}
	
	// Check Cancel link field
	@Test(groups = {"regressiontest"},dependsOnMethods = "forgotPasswordPage",priority = 21)
	public void checkCancelLink() throws InterruptedException, RiseException{

		String result = forgotpwd.checkCancelLink();
		resultmap.put("J29", result);

	}
	
	// Click Cancel link field
	@Test(groups = {"regressiontest"},dependsOnMethods = "forgotPasswordPage",priority = 22)
	public void clickCancelLink() throws InterruptedException, RiseException{

		String result = forgotpwd.clickCancelLink();
		resultmap.put("J30", result);

	}
	
	// Click on Reset by not entering email id
	@Test(groups = {"regressiontest"},dependsOnMethods = "forgotPasswordPage",priority = 23)
	public void validateReset1() throws InterruptedException, RiseException{

		String result = forgotpwd.validateReset1();
		resultmap.put("J31", result);

	}
	
	// Entering invalid email id
	@Test(groups = {"regressiontest"},dependsOnMethods = "forgotPasswordPage",priority = 24)
	public void validateReset2() throws InterruptedException, RiseException{

		String result = forgotpwd.validateReset2();
		resultmap.put("J33", result);

	}

	// Enter unregistered  email
	@Test(groups = {"regressiontest"},dependsOnMethods = "forgotPasswordPage",priority = 25)
	public void inValidEmail() throws InterruptedException, RiseException{

		String result = forgotpwd.inValidEmail();
		resultmap.put("J32", result);

	}
   
	// Test ValidMailAndPwd
	@Test(groups = {"regressiontest"},dependsOnMethods = "forgotPasswordPage",priority = 26)
	public void validEmail() throws InterruptedException, RiseException{

		String result = forgotpwd.validEmai();
		resultmap.put("J34", result);

	}
	
	/**
	 *  Create instance for ResetPassword page.
	 */
	
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "validEmail",priority = 27)
	public void ResetPasswordPage(){
		
		resetpwd = new ResetPasswordPage(driver);
		
	}
	
	// Check "Password Reset" text visibility
	@Test(groups = {"regressiontest"},dependsOnMethods = "ResetPasswordPage",priority = 28)
	public void checkPwdResetText() throws InterruptedException, RiseException{

		String result = resetpwd.checkPwdResetText();
		resultmap.put("J35", result);

	}
	
	// Check Password Reset description
	@Test(groups = {"regressiontest"},dependsOnMethods = "ResetPasswordPage",priority = 29)
	public void checkResetDis() throws InterruptedException, RiseException{

		String result = resetpwd.checkResetDis();
		resultmap.put("J36", result);

	}
	
	// Check login button displayed or not
	@Test(groups = {"regressiontest"},dependsOnMethods = "ResetPasswordPage",priority = 30)
	public void checkResetLoginField() throws InterruptedException, RiseException{

		String result = resetpwd.checkResetLoginField();
		resultmap.put("J37", result);

	}
	
	// Check cancel link displayed or not
	@Test(groups = {"regressiontest"},dependsOnMethods = "ResetPasswordPage",priority = 31)
	public void checkResetCancelLink() throws InterruptedException, RiseException{

		String result = resetpwd.checkResetCancelLink();
		resultmap.put("J38", result);

	}
	
	// Click cancel link
	@Test(groups = {"regressiontest"},dependsOnMethods = "ResetPasswordPage",priority = 32)
	public void clickResetCancelLink() throws InterruptedException, RiseException{

		String result = resetpwd.clickResetCancelLink();
		resultmap.put("J40", result);

	}
	
	// Click reset login  link
	@Test(groups = {"regressiontest"},dependsOnMethods = "ResetPasswordPage",priority = 33)
	public void clickResetLogin() throws InterruptedException, RiseException{

		String result = resetpwd.clickResetLogin();
		resultmap.put("J39", result);

	}
	
	/**
	 *  create instance for Home page
	 */
	
	@Test(groups = {"regressiontest","smoketest"},dependsOnMethods = "validEmailAndPwd",priority = 34)
	public void HomePage(){
		
		homepage = new HomePage(driver);
				
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "HomePage",priority = 35)
	public void checkHomeSureifyLogo(){
		
		String result = homepage.checkHomeSureifyLogo();
		resultmap.put("J41", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "HomePage",priority = 36)
	public void checkHeading(){
		
		String result = homepage.checkHeading();
		resultmap.put("J42", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "HomePage",priority = 37)
	public void checkHeadingDis(){
		
		String result = homepage.checkHeading();
		resultmap.put("J43", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "HomePage",priority = 38)
	public void checkUserName(){
		
		String result = homepage.checkUserName();
		resultmap.put("J44", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "HomePage",priority = 39)
	public void checkProfileDropdown(){
		
		String result = homepage.checkProfileDropdown();
		resultmap.put("J45", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "HomePage",priority = 40)
	public void checkEditProfile(){
		
		String result = homepage.checkEditProfile();
		resultmap.put("J46", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "HomePage",priority = 41)
	public void checkChangePWD(){
		
		String result = homepage.checkChangePWD();
		resultmap.put("J47", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "HomePage",priority = 42)
	public void checkLogout(){
		
		String result = homepage.checkLogout();
		resultmap.put("J48", result);
		
	}
     
	@Test(groups = {"regressiontest"},dependsOnMethods = "HomePage",priority = 43)
	public void checkOverviewIsSelectedOrNot(){

		String result = homepage.checkOverviewIsSelectedOrNot();
		resultmap.put("J49", result);

	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "HomePage",priority = 44)
	public void selectSales() throws InterruptedException{

		String result = homepage.selectSales();
		resultmap.put("J50", result);

	}
    
	@Test(groups = {"regressiontest"},dependsOnMethods = "HomePage",priority = 45)
	public void selectPHManagement() throws InterruptedException{

		String result = homepage.selectPHManagement();
		resultmap.put("J51", result);

	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "HomePage",priority = 46)
	public void selectBehaviorData() throws InterruptedException{

		String result = homepage.selectBehaviorData();
		resultmap.put("J52", result);

	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "HomePage",priority = 47)
	public void selectEngagement() throws InterruptedException{

		String result = homepage.selectBehaviorData();
		resultmap.put("J53", result);

	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "HomePage",priority = 48)
	public void clickSalesLayout() throws InterruptedException{

		String result = homepage.clickSalesLayout();
		resultmap.put("J54", result);

	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "HomePage",priority = 49)
	public void clickPolicyHolderLayout() throws InterruptedException{

		String result = homepage.clickPolicyHolderLayout();
		resultmap.put("J55", result);

	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "HomePage",priority = 50)
	public void clickIotDeviceLayout() throws InterruptedException{

		String result = homepage.clickIotDeviceLayout();
		resultmap.put("J56", result);

	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "HomePage",priority = 51)
	public void clickWellnessLayout() throws InterruptedException{

		String result = homepage.clickWellnessLayout();
		resultmap.put("J57", result);

	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "HomePage",priority = 52)
	public void clickProductsLayout() throws InterruptedException{

		String result = homepage.clickProductsLayout();
		resultmap.put("J58", result);

	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "HomePage",priority = 53)
	public void clickHealthLayout() throws InterruptedException{

		String result = homepage.clickHealthLayout();
		resultmap.put("J59", result);

	}
    
	@Test(groups = {"regressiontest"},dependsOnMethods = "HomePage",priority = 54)
	public void clickPremiumLayout() throws InterruptedException{

		String result = homepage.clickPremiumLayout();
		resultmap.put("J60", result);

	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "HomePage",priority = 55)
	public void clickEngagementLayout() throws InterruptedException{

		String result = homepage.clickEngagementLayout();
		resultmap.put("J61", result);

	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "HomePage",priority = 56)
	public void clickSocialIntLayout() throws InterruptedException{

		String result = homepage.clickSocialIntLayout();
		resultmap.put("J62", result);

	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "HomePage",priority = 57)
	public void clickPointsLayout() throws InterruptedException{

		String result = homepage.clickPointsLayout();
		resultmap.put("J63", result);

	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "HomePage",priority = 58)
	public void clickRewardsLayout() throws InterruptedException{

		String result = homepage.clickRewardsLayout();
		resultmap.put("J64", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "HomePage",priority = 59)
	public void clickAppAnlyLayout() throws InterruptedException{

		String result = homepage.clickAppAnlyLayout();
		resultmap.put("J65", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "HomePage",priority = 60)
	public void clickDeviceLogisLayout() throws InterruptedException{

		String result = homepage.clickDeviceLogisLayout();
		resultmap.put("J66", result);
		
	}
	
	/**
	 * Create instance for Sales piller
	 */
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "HomePage",priority = 61)
	public void salesPiller(){
		
		salespiller = new SalesPiller(driver);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 61)
	public void checkSalesPillerLink() throws InterruptedException{
		
		String result = salespiller.clickSalesLayout();
		resultmap.put("J67", result);
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 62)
	public void checkSalesPillerHeading() throws InterruptedException{
		
		String result = salespiller.checkHeading();
		resultmap.put("J68", result);
	}
     
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 63)
	public void checkSalesPillerHeadingDiscription() throws InterruptedException{
		
		String result = salespiller.checkHeadingDis();
		resultmap.put("J69", result);
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 64)
	public void checkQuoteOption() throws InterruptedException{
		
		String result = salespiller.checkQuoteOption();
		resultmap.put("J70", result);
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 65)
	public void checkApplicationOption() throws InterruptedException{
		
		String result = salespiller.checkApplicationOption();
		resultmap.put("J71", result);
	}
    
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 66)
	public void checkPaymentOption() throws InterruptedException{
		
		String result = salespiller.checkPaymentsOption();
		resultmap.put("J72", result);
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 67)
	public void checkPolicyStatusOption() throws InterruptedException{
		
		String result = salespiller.checkPolicyStatusOption();
		resultmap.put("J73", result);
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 68)
	public void checkDeviceLogisticsOption() throws InterruptedException{
		
		String result = salespiller.checkDeviceLogisticsOption();
		resultmap.put("J74", result);
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 69)
	public void noOfPolicyHolders() throws InterruptedException{
		
		String result = salespiller.noOfPolicyHolders();
		resultmap.put("J75", result);
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 70)
	public void clickYearly() throws InterruptedException{
		
		String result = salespiller.clickYearly();
		resultmap.put("J78", result);
	}

	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 71)
	public void clickMonthly() throws InterruptedException{
		
		String result = salespiller.clickMonthly();
		resultmap.put("J77", result);
	}

	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 72)
	public void clickWeekly() throws InterruptedException{
		
		String result = salespiller.clickWeekly();
		resultmap.put("J76", result);
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 73)
	public void select_SalesQuots_25Rows() throws InterruptedException{
		
		String result = salespiller.select25Rows();
		resultmap.put("J80", result);
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 74)
	public void select_SalesQuots_10Rows() throws InterruptedException{
		
		String result = salespiller.select10Rows();
		resultmap.put("J79", result);
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 75)
	public void select_SalesQuots_50Rows() throws InterruptedException{

		String result = salespiller.select50Rows();
		resultmap.put("J81", result);
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 76)
	public void select_SalesQuots_100Rows() throws InterruptedException{
		
		String result = salespiller.select100Rows();
		resultmap.put("J82", result);
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 77)
	public void select_SalesQuots_AllRows() throws InterruptedException{
		
		String result = salespiller.selectAllRows();
		resultmap.put("J83", result);
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 78)
	public void serachRecord() throws InterruptedException{
		
		String result = salespiller.serachRecord();
		resultmap.put("J84", result);
	}
    
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 79)
	public void deleteWithoutSel() throws InterruptedException{
		
		String result = salespiller.deleteWithoutSel();
		resultmap.put("J85", result);
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 80)
	public void deleteRecord() throws InterruptedException{
		
		String result = salespiller.deleteRecord();
		String[] split = result.split(",");
		resultmap.put("J87", split[0]);
		resultmap.put("J88", split[1]);
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 81)
	public void cancelDelete() throws InterruptedException{
		
		String result = salespiller.cancelDelete();
		String[] split = result.split(",");
		resultmap.put("J86", split[0]);
		resultmap.put("J89", split[1]);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 82)
	public void sortLastVisitedDatecolumn() throws InterruptedException{
		
		String result = salespiller.sortLastVisitedDatecolumn_Asc();
		resultmap.put("J90", result);
		result = salespiller.sortLastVisitedDatecolumn_Desc();
		resultmap.put("J91", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 83)
	public void sortIDcolumn() throws InterruptedException{
		
		String result = salespiller.sortIDColumn_Asc();
		resultmap.put("J92", result);
		result = salespiller.sortIDColumn_Desc();
		resultmap.put("J93", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 84)
	public void sortNamecolumn() throws InterruptedException{
		
		String result = salespiller.sortNameColumn_Asc();
		resultmap.put("J94", result);
		result = salespiller.sortNameColumn_Desc();
		resultmap.put("J95", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 85)
	public void sortEmailcolumn() throws InterruptedException{
		
		String result = salespiller.sortEmail_Asc();
		resultmap.put("J96", result);
		result = salespiller.sortEmail_Desc();
		resultmap.put("J97", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 86)
	public void sortFaceAmountcolumn() throws InterruptedException{
		
		String result = salespiller.sortFaceAmount_Asc();
		resultmap.put("J98", result);
		result = salespiller.sortFaceAmount_Desc();
		resultmap.put("J99", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 87)
	public void sortTermLencolumn() throws InterruptedException{
		
		String result = salespiller.sortTermLen_Asc();
		resultmap.put("J100", result);
		result = salespiller.sortTermLen_Desc();
		resultmap.put("J101", result);
		
	}
    
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 88)
	public void sortQuoteYearly_column() throws InterruptedException{
		
		String result = salespiller.sortQuoteYearly_Asc();
		resultmap.put("J102", result);
		result = salespiller.sortQuoteYearly_Desc();
		resultmap.put("J103", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 89)
	public void sortAge_column() throws InterruptedException{
		
		String result = salespiller.sortAge_Asc();
		resultmap.put("J104", result);
		result = salespiller.sortAge_Desc();
		resultmap.put("J105", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 90)
	public void sortSex_column() throws InterruptedException{
		
		String result = salespiller.sortSex_Asc();
		resultmap.put("J106", result);
		result = salespiller.sortSex_Desc();
		resultmap.put("J107", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 91)
	public void sortHealth_column() throws InterruptedException{
		
		String result = salespiller.sortHealth_Asc();
		resultmap.put("J108", result);
		result = salespiller.sortHealth_Desc();
		resultmap.put("J109", result);
		
	}
    
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 92)
	public void sortSmoke_column() throws InterruptedException{
		
		String result = salespiller.sortSmoke_Asc();
		resultmap.put("J110", result);
		result = salespiller.sortSmoke_Desc();
		resultmap.put("J111", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 93)
	public void downloadCSV_Sales_Quote() throws InterruptedException, AWTException{
		
		String result = salespiller.downloadCSV();
		resultmap.put("J112", result);
		
	}
    
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 94)
	public void downloadPDF_Sales_Quote() throws InterruptedException, AWTException{
		
		String result = salespiller.downloadPDF();
		resultmap.put("J113", result);
		
	}
   
	/*
	 *  Applications under sales.
	 */
	@Test(groups = {"regressiontest"},dependsOnMethods = "checkSalesPillerLink",priority = 95)
	public void clickSalesApplication() throws InterruptedException{
		
		String result = salespiller.clickAppliactions();
		resultmap.put("J114", result);
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "clickSalesApplication",priority = 96)
	public void checkSalesApplicationHeading() throws InterruptedException{
		
		String result = salespiller.appHeading();
		resultmap.put("J115", result);
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "clickSalesApplication",priority = 97)
	public void checkSalesApplicationHeadingDis() throws InterruptedException{
		
		String result = salespiller.appHeadingDis();
		resultmap.put("J116", result);
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "clickSalesApplication",priority = 98)
	public void checkSalesAppFirstModule() throws InterruptedException{
		
		String result = salespiller.appFirstModule();
		resultmap.put("J117", result);
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "clickSalesApplication",priority = 99)
	public void checkSalesAppSecondModule() throws InterruptedException{
		
		String result = salespiller.appSecondModule();
		resultmap.put("J118", result);
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "clickSalesApplication",priority = 100)
	public void checkSalesAppThirdModule() throws InterruptedException{
		String result = null;
		try{
			result = salespiller.appThirdModulePaiChart();
			resultmap.put("J119", result);
		}catch(Exception e){}

		try{
			result = salespiller.appCompletedStatus();
			resultmap.put("J120", result);
		}catch(Exception e){}

		try{
			result = salespiller.appInProgressStatus();
			resultmap.put("J121", result);
		}catch(Exception e){}

		try{
			result = salespiller.appAbandonedStatus();
			resultmap.put("J122", result);
		}catch(Exception e){}
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "clickSalesApplication",priority = 101)
	public void selectSalesApplication25Rows() throws InterruptedException{
		
		String result = salespiller.selectSalesApplication25Rows();
		resultmap.put("J124", result);
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "clickSalesApplication",priority = 102)
	public void selectSalesapplication10Rows() throws InterruptedException{
		
		String result = salespiller.selectSalesapplication10Rows();
		resultmap.put("J123", result);
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "clickSalesApplication",priority = 103)
	public void selectSalesApplication50Rows() throws InterruptedException{
		
		String result = salespiller.selectSalesApplication50Rows();
		resultmap.put("J125", result);
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "clickSalesApplication",priority = 104)
	public void selectSalesApplication100Rows() throws InterruptedException{
		
		String result = salespiller.selectSalesApplication100Rows();
		resultmap.put("J126", result);
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "clickSalesApplication",priority = 105)
	public void selectSalesApplicationAllRows() throws InterruptedException{
		
		String result = salespiller.selectSalesApplicationAllRows();
		resultmap.put("J127", result);
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "clickSalesApplication",priority = 106)
	public void searchSalesApplication() throws InterruptedException{
		
		String result = salespiller.searchApplication();
		resultmap.put("J128", result);
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 107)
	public void deleteSalesAppWithoutSel() throws InterruptedException{
		
		String result = salespiller.deleteWithoutSel();
		resultmap.put("J129", result);
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 108)
	public void deleteSalesApplication() throws InterruptedException{
		
		String result = salespiller.deleteSalesApplication();
		String[] split = result.split(",");
		try{
			resultmap.put("J131", split[0]);
			resultmap.put("J132", split[1]);
		}catch(Exception e){
			resultmap.put("J132", "No records found");
		}
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 109)
	public void cancelDeleteSalesApplication() throws InterruptedException{
		
		String result = salespiller.cancelDeleteSalesApplication();
		String[] split = result.split(",");
		try{
		resultmap.put("J130", split[0]);
		resultmap.put("J133", split[1]);
		}catch(Exception e){
			resultmap.put("J133", "No records found");
		}
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 110)
	public void sortSalesAppID() throws InterruptedException{
		
		String result = salespiller.sortSalesAppID_Asc();
		resultmap.put("J136", result);
		result = salespiller.sortSalesAppID_Dec();
		resultmap.put("J137", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 111)
	public void sortSalesAppName() throws InterruptedException{
		
		String result = salespiller.sortSalesAppName_Asc();
		resultmap.put("J138", result);
		result = salespiller.sortSalesAppName_Dec();
		resultmap.put("J139", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 112)
	public void sortSalesAppFaceAmt() throws InterruptedException{
		
		String result = salespiller.sortSalesAppFaceAmt_Asc();
		resultmap.put("J142", result);
		result = salespiller.sortSalesAppFaceAmt_Dec();
		resultmap.put("J143", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 113)
	public void sortSalesAppTermLength() throws InterruptedException{
		
		String result = salespiller.sortSalesAppTermLength_Asc();
		resultmap.put("J144", result);
		result = salespiller.sortSalesAppTermLength_Dec();
		resultmap.put("J145", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 114)
	public void sortSalesAppProgress() throws InterruptedException{
		
		String result = salespiller.sortSalesAppProgress_Asc();
		resultmap.put("J140", result);
		result = salespiller.sortSalesAppProgress_Dec();
		resultmap.put("J141", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 115)
	public void sortSalesAppStage() throws InterruptedException{
		
		String result = salespiller.sortSalesAppStage_Asc();
		resultmap.put("J134", result);
		result = salespiller.sortSalesAppStage_Dec();
		resultmap.put("J135", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 116)
	public void downloadSalesAppCSV() throws InterruptedException, AWTException{
		
		String result = salespiller.downloadSalesAppCSV();
		resultmap.put("J146", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 117)
	public void downloadSalesAppPDF() throws InterruptedException, AWTException{
		
		String result = salespiller.downloadSalesAppPDF();
		resultmap.put("J147", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 118)
	public void click_Id_Sales_AppDetails() throws InterruptedException{
		
		String result = salespiller.click_Id_Sales_AppDetails();	
		resultmap.put("J148", result);
		driver.navigate().back();;
		Thread.sleep(3000);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 119)
	public void click_Name_Sales_AppDetails() throws InterruptedException{
		
		String result = salespiller.click_Name_Sales_AppDetails();
		resultmap.put("J149", result);
		//driver.navigate().back();;
		//Thread.sleep(3000);
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 120)
	public void appDetailsHeading() throws InterruptedException{
		
		String result = salespiller.appDetailsHeading();
		resultmap.put("J150", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 121)
	public void appDetailsHeadingDis() throws InterruptedException{
		
		String result = salespiller.appDetailsHeadingDis();
		resultmap.put("J151", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 122)
	public void clickSalesAppDetailsAppLink() throws InterruptedException{
		
		String result = salespiller.clickSalesAppDetailsAppLink();
		resultmap.put("J153", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 123)
	public void checkSalesAppDetailsDownload() throws InterruptedException{
		
		String result = salespiller.checkSalesAppDetailsDownload();
		resultmap.put("J154", result);
	
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 124)
	public void checkSalesAppDetailsPrint() throws InterruptedException{
		
		String result = salespiller.checkSalesAppDetailsPrint();
		resultmap.put("J155", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 125)
	public void clickSalesAppDetailsQuoteLink() throws InterruptedException{
		
		String result = salespiller.clickSalesAppDetailsQuoteLink();
		resultmap.put("J152", result);
		
	}
	
	@Test(groups = {"regressiontest"},dependsOnMethods = "salesPiller",priority = 126)
	public void clickSalesEditApp() throws InterruptedException{
		
		String result = salespiller.clickSalesEditApp();
		resultmap.put("J156", result);
	
	}
	
	// Validate Sales/ApplicatinDetails/Edit from/First name with invalid data
	@Test(groups = {"regressiontest"},dependsOnMethods = {"salesPiller","clickSalesEditApp"},priority = 127)
	public void invalidFName_Sales_AppDetails() throws InterruptedException{
		
		String result = null;
		result = salespiller.invalidFirstName1();
		resultmap.put("J157", result);
		
		result = salespiller.invalidFirstName2();
		resultmap.put("J158", result);
		
		result = salespiller.invalidFirstName3();
		resultmap.put("J159", result);
		
		result = salespiller.invalidFirstName4();
		resultmap.put("J160", result);
		
		result = salespiller.invalidFirstName5();
		resultmap.put("J161", result);
		
		result = salespiller.invalidFirstName6();
		resultmap.put("J188", result);
		
	}
	
	// Validate Sales/ApplicatinDetails/Edit from/First name with valid data
	@Test(groups = {"regressiontest"},dependsOnMethods = {"salesPiller","clickSalesEditApp"},priority = 128)
	public void validFName_Sales_AppDetails() throws InterruptedException{

		String result = null;
		result = salespiller.validFirstName1();
		resultmap.put("J162", result);

		result = salespiller.validFirstName2();
		resultmap.put("J163", result);

		result = salespiller.validFirstName3();
		resultmap.put("J164", result);

		result = salespiller.validFirstName4();
		resultmap.put("J165", result);

	}
	
	// Validate Sales/ApplicatinDetails/Edit from/Last name with invalid data
	@Test(groups = {"regressiontest"},dependsOnMethods = {"salesPiller","clickSalesEditApp"},priority = 129)
	public void invalidLastName_Sales_AppDetails() throws InterruptedException{

		String result = null;
		result = salespiller.invalidLastName1();
		resultmap.put("J166", result);

		result = salespiller.invalidLastName2();
		resultmap.put("J167", result);

		result = salespiller.invalidLastName3();
		resultmap.put("J168", result);

		result = salespiller.invalidLastName4();
		resultmap.put("J169", result);

		result = salespiller.invalidLastName5();
		resultmap.put("J170", result);
		
		result = salespiller.invalidLastName6();
		resultmap.put("J189", result);


	}

	// Validate Sales/ApplicatinDetails/Edit from/Last name with valid data
	@Test(groups = {"regressiontest"},dependsOnMethods = {"salesPiller","clickSalesEditApp"},priority = 130)
	public void validLastName_Sales_AppDetails() throws InterruptedException{

		String result = null;
		result = salespiller.validLastName1();
		resultmap.put("J171", result);

		result = salespiller.validLastName2();
		resultmap.put("J172", result);

		result = salespiller.validLastName3();
		resultmap.put("J173", result);

		result = salespiller.validLastName4();
		resultmap.put("J174", result);

	}
		
	// Validate Sales/ApplicatinDetails/Edit form/Email with invalid data
	@Test(groups = {"regressiontest"},dependsOnMethods = {"salesPiller","clickSalesEditApp"},priority = 131)
	public void invalidEmail_Sales_AppDetails() throws InterruptedException{

		String result = null;
		result = salespiller.invalidEmail1();
		resultmap.put("J175", result);

		result = salespiller.invalidEmail2();
		resultmap.put("J176", result);

		result = salespiller.invalidEmail3();
		resultmap.put("J177", result);

		result = salespiller.invalidEmail4();
		resultmap.put("J178", result);

		result = salespiller.invalidEmail5();
		resultmap.put("J179", result);

		result = salespiller.invalidEmail6();
		resultmap.put("J180", result);

		result = salespiller.invalidEmail7();
		resultmap.put("J181", result);
        
		result = salespiller.invalidEmail8();
		resultmap.put("J182", result);

		result = salespiller.invalidEmail9();
		resultmap.put("J183", result);
		
		result = salespiller.invalidEmail10();
		resultmap.put("J187", result);
		
	}
    
	// Validate Sales/ApplicatinDetails/Edit from/Email with valid data
	@Test(groups = {"regressiontest"},dependsOnMethods = {"salesPiller","clickSalesEditApp"},priority = 132)
	public void validEmail_Sales_AppDetails() throws InterruptedException{

		String result = null;
		result = salespiller.validEmail1();
		resultmap.put("J184", result);

		result = salespiller.validEmail2();
		resultmap.put("J185", result);

		result = salespiller.validEmail3();
		resultmap.put("J186", result);
	}
	
	// Validate Sales/ApplicatinDetails/Edit from/Phone number with invalid data
	@Test(groups = {"regressiontest"},dependsOnMethods = {"salesPiller","clickSalesEditApp"},priority = 133)
	public void invalidPhoneNumber_Sales_AppDetails() throws InterruptedException{

		String result = null;
		result = salespiller.invalidPhoneNo1();
		resultmap.put("J190", result);

		result = salespiller.invalidPhoneNo2();
		resultmap.put("J191", result);

		result = salespiller.invalidPhoneNo3();
		resultmap.put("J192", result);
		
		result = salespiller.invalidPhoneNo4();
		resultmap.put("J193", result);

	}
	
	// Validate Sales/ApplicatinDetails/Edit from/Phone number with valid data
	@Test(groups = {"regressiontest"},dependsOnMethods = {"salesPiller","clickSalesEditApp"},priority = 134)
	public void validPhoneNumber_Sales_AppDetails() throws InterruptedException{

		String result = null;
		result = salespiller.validPhoneNo1();
		resultmap.put("J194", result);

	}
	
	// Validate Sales/ApplicatinDetails/Edit from/DOB with invalid data
	@Test(groups = {"regressiontest"},dependsOnMethods = {"salesPiller","clickSalesEditApp"},priority = 135)
	public void invalidDOB_Sales_AppDetails() throws InterruptedException{

		String result = null;
		result = salespiller.invalidDOB();
		resultmap.put("J195", result);

	}
	
	// Validate Sales/ApplicatinDetails/Edit from/DOB with valid data
	@Test(groups = {"regressiontest"},dependsOnMethods = {"salesPiller","clickSalesEditApp"},priority = 136)
	public void validDOB_Sales_AppDetails() throws InterruptedException{

		String result = null;
		result = salespiller.validDoB();
		resultmap.put("J196", result);

	}

	
	@AfterClass
	public void tearDown(){

		WriteResults.write(filename, resultmap);

	}

		
	

}
