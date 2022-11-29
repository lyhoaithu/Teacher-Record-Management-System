package test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.Excelutils;
import pages.TeacherChangePasswordPage;

public class TeacherChangePasswordPageTest extends TestCase{

	public TeacherChangePasswordPage tcpp= new TeacherChangePasswordPage(driver);
	public Excelutils excelUtils = new Excelutils();
	public String pagePath= "http://localhost:8081/TRMS/trms/teacher/change-password.php";
	
	@DataProvider(name = "Validate Change Password Successfully")
	public String[][] successfulData() throws IOException {
		String [][] data= excelUtils.getDataFromExcel(dataFilePath, "TeacherChangePassSuccessfully");
		return data;
	}
	
	@Test(description="Validate Changing Password Successfully", groups = {"logIn"}, priority = 3, dataProvider ="Validate Change Password Successfully" )
	public void changePassSuccessfully(String currentPass, String newPass, String confirmPass, String expectedMessage) {
		driver.navigate().to(pagePath);
		sendKeys(tcpp.txtCurrentPassword, currentPass);
		sendKeys(tcpp.txtNewPass, newPass);
		sendKeys(tcpp.txtConfirmPass, confirmPass);
		clickOnElement(tcpp.btnChange);
String actualMessage= getLocalMessage();
assertEquals(actualMessage, expectedMessage);
	}
	
	@DataProvider(name = "Change Password Fail When Leaving Compulsory Field Blank Data", indices = {0,1,2})
	public String[][] fieldBlankData() throws IOException {
		String [][] data= excelUtils.getDataFromExcel(dataFilePath, "TeacherChangePassFail");
		return data;
	}
	
	@Test(description="Validate Changing Password Fail When Leaving Field Blank", groups = {"logIn"}, priority = 1, dataProvider = "Change Password Fail When Leaving Compulsory Field Blank Data")
	public void changePassFailWhenLeavingFieldBlank(String currentPass, String newPass, String confirmPass, String expectedMessage) {
		driver.navigate().to(pagePath);
	By[] elements= {tcpp.txtCurrentPassword, tcpp.txtNewPass, tcpp.txtConfirmPass};
	String [] data= {currentPass, newPass, confirmPass};
	getHtml5ValidationFromASeriesOfFields(elements, data, tcpp.btnChange, expectedMessage);
	}
	

	@DataProvider(name = "Change Password Fail When Provide Invalid Data", indices = {3,4,5})
	public String[][] InvalidData() throws IOException {
		String [][] data= excelUtils.getDataFromExcel(dataFilePath, "TeacherChangePassFail");
		return data;
	}
	
	@Test(description="Validate Changing Password Fail Using Invalid Data", groups = {"logIn"}, priority = 2, dataProvider = "Change Password Fail When Provide Invalid Data")
	public void changePassFailUsingInvalidData(String currentPass, String newPass, String confirmPass, String expectedMessage) {
		driver.navigate().to(pagePath);
	sendKeys(tcpp.txtCurrentPassword, currentPass);
	sendKeys(tcpp.txtNewPass, newPass);
	sendKeys(tcpp.txtConfirmPass, confirmPass);
	clickOnElement(tcpp.btnChange);
	String actualMessage= getLocalMessage();
	assertEquals(actualMessage, expectedMessage);
	}
}
