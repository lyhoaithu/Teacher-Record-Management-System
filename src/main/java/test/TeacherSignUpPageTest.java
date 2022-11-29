package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.Excelutils;
import pages.TeacherSignUpPage;

public class TeacherSignUpPageTest extends TestCase {
public TeacherSignUpPage tsup= new TeacherSignUpPage(driver);
public String signUpPage="http://localhost:8081/TRMS/trms/teacher/signup.php";
public Excelutils excelUtils= new Excelutils();

@Test(description = "Validate Navigating To Home When Clicking On 'Back Home!!'")
public void navigatingHome() {
	driver.navigate().to(signUpPage);
	clickOnElement(tsup.linktextHome);
	String currentURL= driver.getCurrentUrl();
	assertTrue(currentURL.contains("trms/index"));
}

@Test(description = "Validate Navigating To Log In Page When Clicking On 'Log In'")
public void navigatingLogInPage() {
	driver.navigate().to(signUpPage);
	clickOnElement(tsup.linktextLogIn);
	String currentURL= driver.getCurrentUrl();
	assertTrue(currentURL.contains("teacher/index"));
}

@DataProvider(name = "Teacher Sign Up Successfully")
public String[][] successfulData() throws IOException {
	String[][] data= excelUtils.getDataFromExcel(dataFilePath, "TeacherSignUpSuccessfully");
	return data;
}

@Test(description = "Validate Teacher Sign Up Successfully When Enter Correct Data", dataProvider = "Teacher Sign Up Successfully", priority = 1)
public void logInSuccessfully(String name, String email, String phoneNo, String password, String expectedMessage) {
	driver.navigate().to(signUpPage);
sendKeys(tsup.txtEmail, email);
sendKeys(tsup.txtMobileNo, phoneNo);
sendKeys(tsup.txtName, name);
sendKeys(tsup.txtPassword, password);
clickOnElement(tsup.btnSignUp);
String actualMessage= getLocalMessage();
assertEquals(actualMessage, expectedMessage);
}

@DataProvider(name = "Registered Phone Or Email", indices = {0,1})
public String[][] invalidOrIncorrectData() throws IOException {
	String [][] data= excelUtils.getDataFromExcel(dataFilePath, "TeacherSignUpFail");
	return data;
}

@Test(description = "Validate Teacher Sign Up Fail When Using Registered Phone Number Or Email", dataProvider = "Registered Phone Or Email", priority = 2)
public void signUpFailUsingInvaidOrIncorrectData(String name, String email, String phoneNo, String password, String expectedMessage) {
	driver.navigate().to(signUpPage);
	sendKeys(tsup.txtEmail, email);
	sendKeys(tsup.txtMobileNo, phoneNo);
	sendKeys(tsup.txtName, name);
	sendKeys(tsup.txtPassword, password);
	clickOnElement(tsup.btnSignUp);
	String actualMessage=getLocalMessage();
	assertEquals(actualMessage, expectedMessage);
}

@DataProvider(name = "Invalid Email", indices = {3,4,5,6})
public String[][] invalidEmailData() throws IOException {
	String [][] data= excelUtils.getDataFromExcel(dataFilePath, "TeacherSignUpFail");
	return data;
}

@Test(description = "Validate Teacher Sign Up Fail When Using Invalid Email", dataProvider = "Invalid Email", priority = 3)
public void signUpFailUsingInvaidEmail(String name, String email, String phoneNo, String password, String expectedMessage) {
	driver.navigate().to(signUpPage);
	sendKeys(tsup.txtEmail, email);
	sendKeys(tsup.txtMobileNo, phoneNo);
	sendKeys(tsup.txtName, name);
	sendKeys(tsup.txtPassword, password);
	clickOnElement(tsup.btnSignUp);
	String actualMessage=getHtml5ValidationMessage(tsup.txtEmail);
	assertEquals(actualMessage, expectedMessage);
}

@DataProvider(name = "Invalid Phone Number", indices = {7,8})
public String[][] invalidPhoneNumberData() throws IOException {
	String [][] data= excelUtils.getDataFromExcel(dataFilePath, "TeacherSignUpFail");
	return data;
}

@Test(description = "Validate Teacher Sign Up Fail When Using Invalid Phone Number", dataProvider = "Invalid Phone Number", priority = 4)
public void signUpFailUsingInvaidPhoneNumber(String name, String email, String phoneNo, String password, String expectedMessage) {
	driver.navigate().to(signUpPage);
	sendKeys(tsup.txtEmail, email);
	sendKeys(tsup.txtMobileNo, phoneNo);
	sendKeys(tsup.txtName, name);
	sendKeys(tsup.txtPassword, password);
	clickOnElement(tsup.btnSignUp);
	String actualMessage=getHtml5ValidationMessage(tsup.txtMobileNo);
	assertEquals(actualMessage, expectedMessage);
}

@DataProvider(name = "Invalid Name Or Password", indices = {2,9})
public String[][] invalidNameOrPass() throws IOException {
	String [][] data= excelUtils.getDataFromExcel(dataFilePath, "TeacherSignUpFail");
	return data;
}

@Test(description = "Validate Teacher Sign Up Fail When Using Invalid Phone Number", dataProvider = "Invalid Name Or Password", priority = 5)
public void signUpFailUsingInvaidNameOrPass(String name, String email, String phoneNo, String password, String expectedMessage) {
	driver.navigate().to(signUpPage);
	sendKeys(tsup.txtEmail, email);
	sendKeys(tsup.txtMobileNo, phoneNo);
	sendKeys(tsup.txtName, name);
	sendKeys(tsup.txtPassword, password);
	clickOnElement(tsup.btnSignUp);
	String actualMessage=getLocalMessage();
	assertEquals(actualMessage, expectedMessage);
}


@DataProvider(name = "Field Blank", indices = {10,11,12,13})
public String[][] FieldBlank() throws IOException {
	String [][] data= excelUtils.getDataFromExcel(dataFilePath, "TeacherSignUpFail");
	return data;
}

@Test(description = "Validate Teacher Sign Up Fail When Leaving Field Blank", dataProvider = "FieldBlank", priority = 6)
public void signUpFailLeavingFieldBlank(String name, String email, String phoneNo, String password, String expectedMessage) {
	driver.navigate().to(signUpPage);
	By[] elements= {tsup.txtName, tsup.txtEmail, tsup.txtMobileNo, tsup.txtPassword};
	String [] data= {name, email, phoneNo, password};
	getHtml5ValidationFromASeriesOfFields(elements, data, tsup.btnSignUp, expectedMessage);
}
}
