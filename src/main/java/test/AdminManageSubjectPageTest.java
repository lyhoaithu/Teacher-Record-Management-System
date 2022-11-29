package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.Excelutils;
import pages.AdminManageSubjectPage;

public class AdminManageSubjectPageTest extends TestCase {

	public AdminManageSubjectPage amsp = new AdminManageSubjectPage(driver);
	public Excelutils excelUtils = new Excelutils();
	public String pagePath = "http://localhost:8081/TRMS/trms/admin/manage-subjects.php";

	@Test(description = "Validate Displayed Information", groups = "logInAdmin")
	public void validateDisplayedInfor() throws ClassNotFoundException, SQLException {
		driver.navigate().to(pagePath);

		By[] elements = { amsp.lblSubjectNameList, amsp.lblCreationDate };
		String[] expectedResult = new String[elements.length];
		String[][] actualResult = new String[elements.length][driver.findElements(amsp.lblCreationDate).size()];
		for (int i = 0; i < elements.length; i++) {
			List<WebElement> elementsList = driver.findElements(elements[i]);
			for (int j = 0; j < elementsList.size(); j++) {
				actualResult[i][j] = elementsList.get(j).getText();
			}
			expectedResult[i] = getValueFromDatabase("SELECT Subject, CreationDate FROM `tblsubjects`;", i + 1)
					.toString();
		}

		String expectedResultFinal = Arrays.deepToString(expectedResult).replace("[", "").replace("]", "");
		String actualResultFinal = Arrays.deepToString(actualResult).replace("[", "").replace("]", "");
		assertEquals(actualResultFinal, expectedResultFinal);
	}

	@Test(description = "Validate Navigating To Edit Subject Page", groups = "logInAdmin")
	public void navigatingToEditSubjectPage() {
		driver.navigate().to(pagePath);
		clickOnElement(amsp.btnEdit);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("edit-subjects-detail"));
	}
	
	@DataProvider(name = "Edit Subject Successfully Data")
	public String [][]successfulData() throws IOException {
	String [][] data= excelUtils.getDataFromExcel(dataFilePath, "EditSubjectSuccessfully");
	return data;
	}
	
	@Test(description = "Validate Edit Subject Successfully", groups="logInAdmin", dataProvider = "Edit Subject Successfully Data", priority = 3 )
	public void editSubjectSuccessfully(String subjectName, String expectedMessage) {
		driver.navigate().to(pagePath);
		clickOnElement(amsp.btnEdit);
		sendKeysWithAttributeAvailable(amsp.txtSubjectName, subjectName);
		clickOnElement(amsp.btnUpdate);
		String actualMessage= getLocalMessage();
		acceptAlertMessage();
		String updatedSubjectName= driver.findElement(amsp.lblSubjectNameList).getText();
		assertEquals(actualMessage, expectedMessage);
		assertEquals(updatedSubjectName, subjectName);
	}
	
	@DataProvider(name = "Edit Subject Fail When Leaving Field Blank Data", indices = {0})
	public String [][]leavingFieldBlankData() throws IOException {
	String [][] data= excelUtils.getDataFromExcel(dataFilePath, "EditSubjectFail");
	return data;
	}
	
	@Test(description = "Validate Edit Subject Fail When Leaving Field Blank", groups="logInAdmin", dataProvider = "Edit Subject Fail When Leaving Field Blank Data", priority = 1 )
	public void editSubjectFailWhenLeavingFieldBlank(String subjectName, String expectedMessage) {
		driver.navigate().to(pagePath);
		clickOnElement(amsp.btnEdit);
		sendKeysWithAttributeAvailable(amsp.txtSubjectName, subjectName);
		clickOnElement(amsp.btnUpdate);
		String actualMessage= getHtml5ValidationMessage(amsp.txtSubjectName);
		assertEquals(actualMessage, expectedMessage);
	}
	
	@DataProvider(name = "Edit Subject Fail When Provide Invalid Data", indices = {1, 2})
	public String [][]invalidData() throws IOException {
	String [][] data= excelUtils.getDataFromExcel(dataFilePath, "EditSubjectFail");
	return data;
	}
	
	@Test(description = "Validate Edit Subject Fail When Provide Invalid Data", groups="logInAdmin", dataProvider = "Edit Subject Fail When Provide Invalid Data", priority = 2 )
	public void editSubjectFailUsingInvalid(String subjectName, String expectedMessage) {
		driver.navigate().to(pagePath);
		clickOnElement(amsp.btnEdit);
		sendKeysWithAttributeAvailable(amsp.txtSubjectName, subjectName);
		clickOnElement(amsp.btnUpdate);
		String actualMessage= getLocalMessage();
		assertEquals(actualMessage, expectedMessage);
	}
	
	@Test(description = "Validate Delete Subject Fail When Clicking On 'Cancel' At Alert Message", groups="logInAdmin", priority = 4)
	public void deleteSubjectFail() {
		driver.navigate().to(pagePath);
		String subjectNameBefore= driver.findElement(amsp.lblSubjectNameList).getText();
		clickOnElement(amsp.btnDelete);
		rejectAlertMessage();
		String subjectNameAfter= driver.findElement(amsp.lblSubjectNameList).getText();
		assertEquals(subjectNameAfter, subjectNameBefore);
	}
	
	@Test(description = "Validate Delete Subject Successfully When Clicking On 'OK' At Alert Message", groups="logInAdmin", priority = 5)
	public void deleteSubjectSuccessfully() {
		driver.navigate().to(pagePath);
		String subjectNameBefore= driver.findElement(amsp.lblSubjectNameList).getText();
		clickOnElement(amsp.btnDelete);
		acceptAlertMessage();
		String actualMessage=getLocalMessage();
assertEquals(actualMessage, "Subject deleted");
		acceptAlertMessage();
		String subjectNameAfter= driver.findElement(amsp.lblSubjectNameList).getText();
		assertNotEquals(subjectNameAfter, subjectNameBefore);
	}
}
