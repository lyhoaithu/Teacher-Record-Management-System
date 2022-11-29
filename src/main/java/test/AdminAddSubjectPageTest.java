package test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.Excelutils;
import pages.AdminAddSubjectPage;

public class AdminAddSubjectPageTest extends TestCase {

	public AdminAddSubjectPage aasp = new AdminAddSubjectPage(driver);
	public Excelutils excelUtils = new Excelutils();
	public String pagePath = "http://localhost:8081/TRMS/trms/admin/add-subjects.php";

	@DataProvider(name = "Successful Data")
	public String[][] successfulData() throws IOException {
		String[][] data = excelUtils.getDataFromExcel(dataFilePath, "AddSubjectSuccessfully");
		return data;

	}

	@Test(description = "Validate Add Subject Successfully", groups = "logInAdmin", priority = 3, dataProvider = "Successful Data")
	public void validateAddSubjectSuccessfully(String subjectName, String expectedMessage) {
		driver.navigate().to(pagePath);
		sendKeys(aasp.txtSubjectName, subjectName);
		clickOnElement(aasp.btnAdd);
		String actualMessage = getLocalMessage();
		assertEquals(actualMessage, expectedMessage);
		acceptAlertMessage();
		List<WebElement> elements = driver.findElements(aasp.lblSubjectName);
		String actualSubjectName = elements.get(elements.size() - 1).getText();
		assertEquals(actualSubjectName, subjectName);
	}
	
	@DataProvider(name = "Field Blank Data", indices = {0})
	public String[][] leavingFieldBlank() throws IOException {
		String[][] data= excelUtils.getDataFromExcel(dataFilePath, "AddSubjectFail");
		return data;
	}
	
	@Test(description = "Validate Add Subject Fail When Leaving Field Blank", dataProvider = "Field Blank Data", groups = "logInAdmin", priority = 1)
	public void leavingFieldBlank(String subjectName, String expectedMessage) {
		driver.navigate().to(pagePath);
		sendKeys(aasp.txtSubjectName, subjectName);
		clickOnElement(aasp.btnAdd);
		String actualMessage = getHtml5ValidationMessage(aasp.txtSubjectName);
		assertEquals(actualMessage, expectedMessage);
	}
	
	@DataProvider(name = "Invalid Data", indices = {1,2})
	public String[][] invalidData() throws IOException {
		String[][] data= excelUtils.getDataFromExcel(dataFilePath, "AddSubjectFail");
		return data;
	}
	
	@Test(description = "Validate Add Subject Fail When Leaving Field Blank", dataProvider = "Invalid Data", groups = "logInAdmin", priority = 2)
	public void invalidData(String subjectName, String expectedMessage) {
		driver.navigate().to(pagePath);
		sendKeys(aasp.txtSubjectName, subjectName);
		clickOnElement(aasp.btnAdd);
		String actualMessage = getLocalMessage();
		assertEquals(actualMessage, expectedMessage);
	}
}
