package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.Excelutils;
import pages.AdminLogInPage;

public class AdminLogInPageTest extends TestCase{
	
public String pagePath="http://localhost:8081/TRMS/trms/admin/index.php";
public AdminLogInPage alp= new AdminLogInPage(driver);
public Excelutils excelUtils= new Excelutils();

@Test(description = "Validate Navigating Home By Clicking On 'Back Home!!'", priority = 1)
public void navigatingHome() {
	driver.navigate().to(pagePath);
	clickOnElement(alp.linktextBackHome);
	String currentURL= driver.getCurrentUrl();
	assertTrue(currentURL.contains("/trms/index"));
}

@Test(description = "Validate Navigating Forgot Password Page By Clicking On 'Forgot Password'", priority = 2)
public void navigatingForgotPassword() {
	driver.navigate().to(pagePath);
	clickOnElement(alp.linktextForgotPassword);
	String currentURL= driver.getCurrentUrl();
	assertTrue(currentURL.contains("admin/forgot-password"));
}

@DataProvider(name = "Successful Data")
public String[][] successfulData() throws IOException {
	String[][]data= excelUtils.getDataFromExcel(dataFilePath, "AdminLogInSuccessfully");
	return data;
}

@Test(description = "Validate Log In Successfully", dataProvider = "Successful Data", priority = 3)
public void loginSuccessfully(String username, String password) {
	driver.navigate().to(pagePath);
sendKeys(alp.txtUserName, username);
sendKeys(alp.txtPassword, password);
clickOnElement(alp.btnSignIn);
String currentURL= driver.getCurrentUrl();
System.out.println(currentURL);
//assertTrue(currentURL.contains("admin/dashboard"));
}

@DataProvider(name = "Invalid Data", indices = {0})
public String[][] invalidData() throws IOException {
	String[][]data= excelUtils.getDataFromExcel(dataFilePath, "AdminLogInFail");
	return data;
}

@Test(description = "Validate Log In Fail Using Invalid Data", dataProvider = "Invalid Data", priority = 4)
public void loginFailUsingInvalidData(String username, String password, String expectedMessage) {
	driver.navigate().to(pagePath);
sendKeys(alp.txtUserName, username);
sendKeys(alp.txtPassword, password);
JavascriptExecutor jse= (JavascriptExecutor) driver;
jse.executeScript("arguments[0].click();", driver.findElement(alp.btnSignIn));
String actualMessage= getLocalMessage();
acceptAlertMessage();
assertEquals(actualMessage, expectedMessage);
}

@DataProvider(name = "Field Blank Data", indices = {8,9})
public String[][] fieldBlankData() throws IOException {
	String[][]data= excelUtils.getDataFromExcel(dataFilePath, "AdminLogInFail");
	return data;
}

@Test(description = "Validate Log In Fail Leaving Field Blank", dataProvider = "Field Blank Data", priority = 5)
public void loginFailLeavingFieldBlank(String username, String password, String expectedMessage) {
	driver.navigate().to(pagePath);
sendKeys(alp.txtUserName, username);
sendKeys(alp.txtPassword, password);
clickOnElement(alp.btnSignIn);
String actualMessage= null;
if(username.equals("")) {
	actualMessage=getHtml5ValidationMessage(alp.txtUserName);
}
else if(password.equals("")) {
	actualMessage=getHtml5ValidationMessage(alp.txtPassword);
}
assertEquals(actualMessage, expectedMessage);
}
}
