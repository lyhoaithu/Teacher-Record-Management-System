package test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.Excelutils;
import pages.AddTeacherPage;

public class AdminAddTeacherPageTest extends TestCase {

	public AddTeacherPage atp = new AddTeacherPage(driver);
	public Excelutils excelUtils = new Excelutils();
	public String pagePath = "http://localhost:8081/TRMS/trms/admin/add-teacher.php";

	@DataProvider(name = "Leave Field Blank Data", indices = { 0, 1, 2, 3, 4, 5, 6, 7, 8 })
	public String[][] leaveFieldBlankData() throws IOException {
		String[][] data = excelUtils.getDataFromExcel(dataFilePath, "AdminAddTeacherFail");
		return data;
	}

	@Test(description = "Validate Add Teacher Fail When Leaving Field Blank", groups = "logInAdmin", priority = 1, dataProvider = "Leave Field Blank Data")
	public void leavingFieldBlank(String name, String image, String email, String mobile, String address,
			String qualifications, String experience, String subjects, String description, String joiningDate,
			String expectedMessage) {
		driver.navigate().to(pagePath);
		By[] elements = { atp.txtTeacherName, atp.txtImage, atp.txtTeacherEmailID, atp.txtTeacherMobileNumber,
				atp.txtTeacherAddress, atp.txtTeacherQualifications, atp.txtTeachingExperience, atp.txtDescription,
				atp.txtJoiningDate };
		String[] data = { name, image, email, mobile, address, qualifications, experience, description, joiningDate };
		selectDropdownByValue(atp.ddbSubjects, subjects);
		getHtml5ValidationFromASeriesOfFields(elements, data, atp.btnAdd, expectedMessage);
	}

	@DataProvider(name = "Invalid Data", indices = { 9, 10, 11, 12, 19, 20, 21, 22})
	public String[][] invalidData() throws IOException {
		String[][] data = excelUtils.getDataFromExcel(dataFilePath, "AdminAddTeacherFail");
		return data;
	}

	@Test(description = "Validate Add Teacher Fail When Provide Invalid Data", groups = "logInAdmin", priority = 5, dataProvider = "Invalid Data")
	public void addFailUsingInvalidData(String name, String image, String email, String mobile, String address,
			String qualifications, String experience, String subjects, String description, String joiningDate,
			String expectedMessage) {
		driver.navigate().to(pagePath);
		By[] elements = { atp.txtTeacherName, atp.txtImage, atp.txtTeacherEmailID, atp.txtTeacherMobileNumber,
				atp.txtTeacherAddress, atp.txtTeacherQualifications, atp.txtTeachingExperience, atp.txtDescription,
				atp.txtJoiningDate };
		String[] data = { name, image, email, mobile, address, qualifications, experience, description, joiningDate };
		for(int i=0; i<elements.length;i++) {
			sendKeys(elements[i], data[i]);
		}
		selectDropdownByValue(atp.ddbSubjects, subjects);
clickOnElement(atp.btnAdd);
String actualMessage= getLocalMessage();
assertEquals(actualMessage, expectedMessage);
	}
	
	@DataProvider(name = "Invalid Email", indices = { 13,14,15,16})
	public String[][] invalidEmail() throws IOException {
		String[][] data = excelUtils.getDataFromExcel(dataFilePath, "AdminAddTeacherFail");
		return data;
	}

	@Test(description = "Validate Add Teacher Fail When Provide Invalid Email", groups = "logInAdmin", priority = 2, dataProvider = "Invalid Email")
	public void addFailUsingInvalidEmail(String name, String image, String email, String mobile, String address,
			String qualifications, String experience, String subjects, String description, String joiningDate,
			String expectedMessage) {
		driver.navigate().to(pagePath);
		By[] elements = { atp.txtTeacherName, atp.txtImage, atp.txtTeacherEmailID, atp.txtTeacherMobileNumber,
				atp.txtTeacherAddress, atp.txtTeacherQualifications, atp.txtTeachingExperience, atp.txtDescription,
				atp.txtJoiningDate };
		String[] data = { name, image, email, mobile, address, qualifications, experience, description, joiningDate };
		for(int i=0; i<elements.length;i++) {
			sendKeys(elements[i], data[i]);
		}
		selectDropdownByValue(atp.ddbSubjects, subjects);
clickOnElement(atp.btnAdd);
String actualMessage= getHtml5ValidationMessage(atp.txtTeacherEmailID);
assertEquals(actualMessage, expectedMessage);
	}
	
	@DataProvider(name = "Invalid Phone Number", indices = {17,18})
	public String[][] invalidPhoneNumber() throws IOException {
		String[][] data = excelUtils.getDataFromExcel(dataFilePath, "AdminAddTeacherFail");
		return data;
	}

	@Test(description = "Validate Add Teacher Fail When Provide Invalid Email", groups = "logInAdmin", priority = 3, dataProvider = "Invalid Phone Number")
	public void addFailUsingInvalidPhoneNumber(String name, String image, String email, String mobile, String address,
			String qualifications, String experience, String subjects, String description, String joiningDate,
			String expectedMessage) {
		driver.navigate().to(pagePath);
		By[] elements = { atp.txtTeacherName, atp.txtImage, atp.txtTeacherEmailID, atp.txtTeacherMobileNumber,
				atp.txtTeacherAddress, atp.txtTeacherQualifications, atp.txtTeachingExperience, atp.txtDescription,
				atp.txtJoiningDate };
		String[] data = {name, image, email, mobile, address, qualifications, experience, description, joiningDate };
		for(int i=0; i<elements.length;i++) {
			sendKeys(elements[i], data[i]);
		}
		selectDropdownByValue(atp.ddbSubjects, subjects);
clickOnElement(atp.btnAdd);
String actualMessage= getHtml5ValidationMessage(atp.txtTeacherMobileNumber);
assertEquals(actualMessage, expectedMessage);
	}
	
	@DataProvider(name = "Invalid Teaching Experience", indices = {24})
	public String[][] invalidTeachingEx() throws IOException {
		String[][] data = excelUtils.getDataFromExcel(dataFilePath, "AdminAddTeacherFail");
		return data;
	}

	@Test(description = "Validate Add Teacher Fail When Provide Invalid Email", groups = "logInAdmin", priority = 4, dataProvider = "Invalid Teaching Experience")
	public void addFailUsingInvalidTeachingEx(String name, String image, String email, String mobile, String address,
			String qualifications, String experience, String subjects, String description, String joiningDate,
			String expectedMessage) {
		driver.navigate().to(pagePath);
		By[] elements = { atp.txtTeacherName, atp.txtImage, atp.txtTeacherEmailID, atp.txtTeacherMobileNumber,
				atp.txtTeacherAddress, atp.txtTeacherQualifications, atp.txtTeachingExperience, atp.txtDescription,
				atp.txtJoiningDate };
		String[] data = {name, image, email, mobile, address, qualifications, experience, description, joiningDate };
		for(int i=0; i<elements.length;i++) {
			sendKeys(elements[i], data[i]);
		}
		selectDropdownByValue(atp.ddbSubjects, subjects);
clickOnElement(atp.btnAdd);
String actualMessage= getHtml5ValidationMessage(atp.txtTeachingExperience);
assertEquals(actualMessage, expectedMessage);
	}
	
}
