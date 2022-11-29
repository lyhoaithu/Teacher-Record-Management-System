package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.Excelutils;
import pages.TeacherLogInPage;

public class TeacherLogInPageTest extends TestCase {

	public TeacherLogInPage tlp = new TeacherLogInPage(driver);
	public Excelutils excelUtils = new Excelutils();
	public String loginPage = "http://localhost:8081/TRMS/trms/teacher/index.php";

	@Test(description = "Validate Navigating Home When Clicking On 'Back Home'")
	public void navigatingHome() {
		driver.navigate().to(loginPage);
		clickOnElement(tlp.linktextBackHome);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("index"));
	}

	@Test(description = "Validate Navigating To Forgot Password Page When Clicking On 'Forgot Password'")
	public void navigatingForgetPasswordPage() {
		driver.navigate().to(loginPage);
		clickOnElement(tlp.linktextForgetPassword);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("forgot-password"));
	}

	@Test(description = "Validate Navigating To Register Page When Clicking On 'Sign Up Here'")
	public void navigatingRegisterPage() {
		driver.navigate().to(loginPage);
		clickOnElement(tlp.linktextRegister);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("signup"));
	}

	@DataProvider(name = "Teacher Log In Successfully Data")
	public String[][] successfulData() throws IOException {
		String[][] data = excelUtils.getDataFromExcel(dataFilePath, "TeacherLogInSuccessfully");
		return data;

	}

	@Test(description = "Validate Log In Successfully", dataProvider = "Teacher Log In Successfully Data")
	public void logInSuccessfully(String emailOrPhoneNo, String password) {
		driver.navigate().to(loginPage);
		sendKeys(tlp.txtEmail, emailOrPhoneNo);
		sendKeys(tlp.txtPassword, password);
		clickOnElement(tlp.btnLogin);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("teacher/dashboard"));
		String teacherName = driver.findElement(tlp.lblTeacherName).getText();
		assertEquals(teacherName, "Welcome back : John Doe");
	}
	
	@DataProvider(name = "Incorrect Data", indices = {0,1,2,3})
	public String[][] incorrectData() throws IOException {
		String[][] data = excelUtils.getDataFromExcel(dataFilePath, "TeacherLogInFail");
		return data;

	}
	@Test(description = "Validate Teacher Log In Fail When Provide Incorrect Data", dataProvider = "Incorrect Data")
	public void logInFailUsingIncorrectData(String emailOrPhoneNo, String password, String expectedMessage) {
		driver.navigate().to(loginPage);
		sendKeys(tlp.txtEmail, emailOrPhoneNo);
		sendKeys(tlp.txtPassword, password);
		clickOnElement(tlp.btnLogin);
		String actualMessage= getLocalMessage();
		acceptAlertMessage();
		boolean formPresence=checkElementPresence(tlp.frmLogIn);
		assertEquals(actualMessage, expectedMessage);
		assertEquals(formPresence, true);
	}
	
	@DataProvider(name = "Blank Data", indices = {4,5,6,7})
	public String[][] blanktData() throws IOException {
		String[][] data = excelUtils.getDataFromExcel(dataFilePath, "TeacherLogInFail");
		return data;

	}
	@Test(description = "Validate Teacher Log In Fail When Leave Field Blank", dataProvider = "Blank Data")
	public void logInFailLeavingFieldBlank(String emailOrPhoneNo, String password, String expectedMessage) {
		driver.navigate().to(loginPage);
		 By [] elements= {tlp.txtEmail, tlp.txtPassword};
		 String [] data= {emailOrPhoneNo, password};
		 getHtml5ValidationFromASeriesOfFields(elements, data, tlp.btnLogin, expectedMessage);
	}
}
