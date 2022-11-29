package test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.Excelutils;
import pages.TeacherProfilePage;

public class TeacherProfilePageTest extends TestCase {
	public TeacherProfilePage tpp = new TeacherProfilePage(driver);
	public Excelutils excelUtils = new Excelutils();
	public String pagePath = "http://localhost:8081/TRMS/trms/teacher/profile.php";

	@DataProvider(name = "Changing Profile Successfully Data")
	public String[][] successfulData() throws IOException {
		String[][] data = excelUtils.getDataFromExcel(dataFilePath, "TeacherProfileSuccessfully");
		return data;
	}

	@Test(description = "Validate Edit Profile Successfully", groups = "logIn", dataProvider = "Changing Profile Successfully Data")
	public void editProfileSuccessfully(String name, String img, String email, String mobile, String address,
			String joiningDate, String qualifications, String experience, String subject, String description,
			String status, String expectedMessage) {
		driver.navigate().to(pagePath);
	clickOnElement(tpp.imgEditImage);
	sendKeys(tpp.btnChooseFile, img);
	clickOnElement(tpp.btnUpdateImage);
	acceptAlertMessage();
	webWait();
		By[] elements = { tpp.txtTeacherName, tpp.txtTeacherEmailID, tpp.txtTeacherMobileNumber, tpp.txtTeacherAddress,
				tpp.dpJoiningDate, tpp.txtTeacherQualification, tpp.txtTeachingExperience,
				tpp.txtDescription};
		String [] value= {name, email, mobile, address, joiningDate, qualifications, experience, description};
		for (int i=0; i<value.length; i++) {
			sendKeysWithAttributeAvailable(elements[i], value[i]);
		}
		selectDropdownByValue(tpp.ddTeacherSubjects, subject);
		selectDropdownByValue(tpp.ddProfileStatus, status);
		
		clickOnElement(tpp.btnUpdate);
		String actualMessage= getLocalMessage();
		assertEquals(actualMessage, expectedMessage);
		acceptAlertMessage();
		if(status.equals("0")) {
			driver.navigate().to("http://localhost:8081/TRMS/trms/index.php");
			List<WebElement> teacherListed= driver.findElements(tpp.lblTeacherList);
			String[] nameStr= new String[teacherListed.size()];
			for (int i=0; i<teacherListed.size();i++) {
				nameStr[i]= teacherListed.get(i).getText();
			}
			boolean checkVisibility= Arrays.deepToString(nameStr).contains(name);
			assertEquals(checkVisibility, false);
		}
	}
	
	@DataProvider(name = "Changing Profile Fail When Leaving Compulsory Field", indices = {0,1,2,3,4,5,6,7})
	public String[][] leavingFieldBlankData() throws IOException {
		String[][] data= excelUtils.getDataFromExcel(dataFilePath, "TeacherProfileFail");
		return data;
	}
	
	@Test(description = "Validate Changing Profile Fail When Leaving Compulsory Field Blank", dataProvider = "Changing Profile Fail When Leaving Compulsory Field", groups = "logIn", priority=1)
	public void validateChangingProfileFailWhenLeavingBlankField(String name, String img, String email, String mobile, String address,
			String joiningDate, String qualifications, String experience, String subject, String description,
			String status, String expectedMessage1, String expectedMessage2) {
		driver.navigate().to(pagePath);
		By[] elements = { tpp.txtTeacherName, tpp.txtTeacherEmailID, tpp.txtTeacherMobileNumber, tpp.txtTeacherAddress,
				tpp.dpJoiningDate, tpp.txtTeacherQualification, tpp.txtTeachingExperience,
				tpp.txtDescription};
		String [] value= {name, email, mobile, address, joiningDate, qualifications, experience, description};
		selectDropdownByValue(tpp.ddTeacherSubjects, subject);
		selectDropdownByValue(tpp.ddProfileStatus, status);
		clickOnElement(tpp.imgEditImage);
		sendKeys(tpp.btnChooseFile, img);
		clickOnElement(tpp.btnUpdateImage);
		acceptAlertMessage();
		webWait();
		getHtml5ValidationFromASeriesOfFieldsWithAttributes(elements, value, tpp.btnUpdate, expectedMessage2);
	}
	
	@DataProvider(name = "Change Profile Fail When Use Invalid Picture Format", indices = {9})
	public String[][] invalidPictureData() throws IOException {
		String [][] data= excelUtils.getDataFromExcel(dataFilePath, "TeacherProfileFail");
		return data;
	}

	@Test(description = "Validate Change Profile Information Fail When Using Invalid Picture Format", dataProvider = "Change Profile Fail When Use Invalid Picture Format", groups = {"logIn"}, priority = 2)
	public void invalidPicture(String name, String img, String email, String mobile, String address,
			String joiningDate, String qualifications, String experience, String subject, String description,
			String status, String expectedMessage1, String expectedMessage2) {
		driver.navigate().to(pagePath);
		clickOnElement(tpp.imgEditImage);
		sendKeys(tpp.btnChooseFile, img);
		clickOnElement(tpp.btnUpdateImage);
		String actualMessage= getLocalMessage();
		assertEquals(actualMessage, expectedMessage1);
	}

	@DataProvider(name = "Invalid Mobile Phone", indices = { 14, 15 })
	public String[][] invalidNumberData() throws IOException {
		String[][] data = excelUtils.getDataFromExcel(dataFilePath, "TeacherProfileFail");
		return data;
	}

	@Test(description = "Validate Changing Profile Fail When Provide Invalid Mobile Phone Or Year Of Experience", dataProvider = "Invalid Mobile Phone", groups = {
			"logIn" }, priority = 3)
	public void changeProfileFailUsingInvalidMobilePhoneOrExYear(String name, String img, String email, String mobile,
			String address, String joiningDate, String qualifications, String experience, String subject,
			String description, String status, String expectedMessage1, String expectedMessage2) {
		driver.navigate().to(pagePath);
		clickOnElement(tpp.imgEditImage);
		sendKeys(tpp.btnChooseFile, img);
		clickOnElement(tpp.btnUpdateImage);
		acceptAlertMessage();
		By[] elements = { tpp.txtTeacherName, tpp.txtTeacherEmailID, tpp.txtTeacherMobileNumber, tpp.txtTeacherAddress,
				tpp.dpJoiningDate, tpp.txtTeacherQualification, tpp.txtTeachingExperience, tpp.txtDescription };
		String[] value = { name, email, mobile, address, joiningDate, qualifications, experience, description };
		for (int i = 0; i < value.length; i++) {
			sendKeysWithAttributeAvailable(elements[i], value[i]);
		}
		selectDropdownByValue(tpp.ddTeacherSubjects, subject);
		selectDropdownByValue(tpp.ddProfileStatus, status);
		clickOnElement(tpp.btnUpdate);
		String actualMessage = getHtml5ValidationMessage(tpp.txtTeacherMobileNumber);
		assertEquals(actualMessage, expectedMessage2);
	}

	@DataProvider(name = "Invalid Year Of Experience", indices = { 21, 22 })
	public String[][] invalidYOEData() throws IOException {
		String[][] data = excelUtils.getDataFromExcel(dataFilePath, "TeacherProfileFail");
		return data;
	}

	@Test(description = "Validate Changing Profile Fail When Provide Invalid Year Of Experience", dataProvider = "Invalid Year Of Experience", groups = {
			"logIn" }, priority = 4)
	public void changeProfileFailUsingInvalidYOE(String name, String img, String email, String mobile, String address,
			String joiningDate, String qualifications, String experience, String subject, String description,
			String status, String expectedMessage1, String expectedMessage2) {
		driver.navigate().to(pagePath);
		clickOnElement(tpp.imgEditImage);
		sendKeys(tpp.btnChooseFile, img);
		clickOnElement(tpp.btnUpdateImage);
		acceptAlertMessage();
		webWait();
		By[] elements = { tpp.txtTeacherName, tpp.txtTeacherEmailID, tpp.txtTeacherMobileNumber, tpp.txtTeacherAddress,
				tpp.dpJoiningDate, tpp.txtTeacherQualification, tpp.txtTeachingExperience, tpp.txtDescription };
		String[] value = { name, email, mobile, address, joiningDate, qualifications, experience, description };
		for (int i = 0; i < value.length; i++) {
			sendKeysWithAttributeAvailable(elements[i], value[i]);
		}
		selectDropdownByValue(tpp.ddTeacherSubjects, subject);
		selectDropdownByValue(tpp.ddProfileStatus, status);
		clickOnElement(tpp.btnUpdate);
		String actualMessage = getHtml5ValidationMessage(tpp.txtTeachingExperience);
		assertEquals(actualMessage, expectedMessage2);
	}
	
	@DataProvider(name = "Invalid Email Data", indices = { 10, 11, 12, 13 })
	public String[][] invalidEmailData() throws IOException {
		String[][] data = excelUtils.getDataFromExcel(dataFilePath, "TeacherProfileFail");
		return data;
	}

	@Test(description = "Validate Changing Profile Fail When Provide Invalid Email", dataProvider = "Invalid Email Data", groups = {
			"logIn" }, priority = 8)
	public void changeProfileFailUsingInvalidEmail(String name, String img, String email, String mobile, String address,
			String joiningDate, String qualifications, String experience, String subject, String description,
			String status, String expectedMessage1, String expectedMessage2) {
		driver.navigate().to(pagePath);
		clickOnElement(tpp.imgEditImage);
		sendKeys(tpp.btnChooseFile, img);
		clickOnElement(tpp.btnUpdateImage);
		acceptAlertMessage();
		webWait();
		By[] elements = { tpp.txtTeacherName, tpp.txtTeacherEmailID, tpp.txtTeacherMobileNumber, tpp.txtTeacherAddress,
				tpp.dpJoiningDate, tpp.txtTeacherQualification, tpp.txtTeachingExperience, tpp.txtDescription };
		String[] value = { name, email, mobile, address, joiningDate, qualifications, experience, description };
		for (int i = 0; i < value.length; i++) {
			sendKeysWithAttributeAvailable(elements[i], value[i]);
		}
		selectDropdownByValue(tpp.ddTeacherSubjects, subject);
		selectDropdownByValue(tpp.ddProfileStatus, status);
		clickOnElement(tpp.btnUpdate);
		String actualMessage = getHtml5ValidationMessage(tpp.txtTeachingExperience);
		assertEquals(actualMessage, expectedMessage2);
	}	

	@DataProvider(name = "Invalid Name Data", indices = { 8})
	public String[][] invalidNameData() throws IOException {
		String[][] data = excelUtils.getDataFromExcel(dataFilePath, "TeacherProfileFail");
		return data;
	}

	@Test(description = "Validate Changing Profile Fail When Provide Invalid Mobile Phone Or Year Of Experience", dataProvider = "Invalid Name Data", groups = {
			"logIn" }, priority = 5)
	public void changeProfileFailUsingInvalidName(String name, String img, String email, String mobile, String address,
			String joiningDate, String qualifications, String experience, String subject, String description,
			String status, String expectedMessage1, String expectedMessage2) {
		driver.navigate().to(pagePath);
		clickOnElement(tpp.imgEditImage);
		sendKeys(tpp.btnChooseFile, img);
		clickOnElement(tpp.btnUpdateImage);
		acceptAlertMessage();
		webWait();
		By[] elements = { tpp.txtTeacherName, tpp.txtTeacherEmailID, tpp.txtTeacherMobileNumber, tpp.txtTeacherAddress,
				tpp.dpJoiningDate, tpp.txtTeacherQualification, tpp.txtTeachingExperience, tpp.txtDescription };
		String[] value = { name, email, mobile, address, joiningDate, qualifications, experience, description };
		for (int i = 0; i < value.length; i++) {
			sendKeysWithAttributeAvailable(elements[i], value[i]);
		}
		selectDropdownByValue(tpp.ddTeacherSubjects, subject);
		selectDropdownByValue(tpp.ddProfileStatus, status);
		clickOnElement(tpp.btnUpdate);
		String actualMessage = getLocalMessage();
		assertEquals(actualMessage, expectedMessage2);
	}	
	
	@DataProvider(name = "Registered Phone Number", indices = {16})
	public String[][] registeredPhoneNoData() throws IOException {
		String[][] data = excelUtils.getDataFromExcel(dataFilePath, "TeacherProfileFail");
		return data;
	}

	@Test(description = "Validate Changing Profile Fail When Provide Registered Phone Number", dataProvider = "Registered Phone Number Data", groups = {
			"logIn" }, priority = 6)
	public void changeProfileFailUsingRegisteredPhoneNo(String name, String img, String email, String mobile, String address,
			String joiningDate, String qualifications, String experience, String subject, String description,
			String status, String expectedMessage1, String expectedMessage2) {
		driver.navigate().to(pagePath);
		clickOnElement(tpp.imgEditImage);
		sendKeys(tpp.btnChooseFile, img);
		clickOnElement(tpp.btnUpdateImage);
		acceptAlertMessage();
		webWait();
		By[] elements = { tpp.txtTeacherName, tpp.txtTeacherEmailID, tpp.txtTeacherMobileNumber, tpp.txtTeacherAddress,
				tpp.dpJoiningDate, tpp.txtTeacherQualification, tpp.txtTeachingExperience, tpp.txtDescription };
		String[] value = { name, email, mobile, address, joiningDate, qualifications, experience, description };
		for (int i = 0; i < value.length; i++) {
			sendKeysWithAttributeAvailable(elements[i], value[i]);
		}
		selectDropdownByValue(tpp.ddTeacherSubjects, subject);
		selectDropdownByValue(tpp.ddProfileStatus, status);
		clickOnElement(tpp.btnUpdate);
		String actualMessage = getLocalMessage();
		assertEquals(actualMessage, expectedMessage2);
	}

	
	@DataProvider(name = "Invalid Address/Qualification/Joining Date", indices = {17,18,19,20})
	public String[][] invalidAddressQualificationDateData() throws IOException {
		String[][] data = excelUtils.getDataFromExcel(dataFilePath, "TeacherProfileFail");
		return data;
	}

	@Test(description = "Validate Changing Profile Fail When Provide Invalid Address/Qualification/Joining Date", dataProvider = "Invalid Address/Qualification/Joining Date", groups = {
			"logIn" }, priority = 7)
	public void changeProfileFailUsingInvalidAddressQualificationDate(String name, String img, String email, String mobile, String address,
			String joiningDate, String qualifications, String experience, String subject, String description,
			String status, String expectedMessage1, String expectedMessage2) {
		driver.navigate().to(pagePath);
		clickOnElement(tpp.imgEditImage);
		sendKeys(tpp.btnChooseFile, img);
		clickOnElement(tpp.btnUpdateImage);
		acceptAlertMessage();
		webWait();
		By[] elements = { tpp.txtTeacherName, tpp.txtTeacherEmailID, tpp.txtTeacherMobileNumber, tpp.txtTeacherAddress,
				tpp.dpJoiningDate, tpp.txtTeacherQualification, tpp.txtTeachingExperience, tpp.txtDescription };
		String[] value = { name, email, mobile, address, joiningDate, qualifications, experience, description };
		for (int i = 0; i < value.length; i++) {
			sendKeysWithAttributeAvailable(elements[i], value[i]);
		}
		selectDropdownByValue(tpp.ddTeacherSubjects, subject);
		selectDropdownByValue(tpp.ddProfileStatus, status);
		clickOnElement(tpp.btnUpdate);
		String actualMessage = getLocalMessage();
		assertEquals(actualMessage, expectedMessage2);
	}
}
