package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.Excelutils;
import pages.AdminManageTeacherPage;

public class AdminManageTeacherPageTest extends TestCase{
	public AdminManageTeacherPage amtp= new AdminManageTeacherPage(driver);
public String pagePath= "http://localhost:8081/TRMS/trms/admin/manage-teacher.php";
public Excelutils excelUtils= new Excelutils();

public void verifyDisplayedInfor(String query) throws ClassNotFoundException, SQLException {
	By[] elements = { amtp.lblTeacherNameList, amtp.lblSubjectList, amtp.lblRegistrationDateList};

	// Create a 2 ways Array to save the actual displayed information. Each big
	// array is a column in the table
	String[][] actualDisplayedInfor = new String[elements.length][driver.findElements(amtp.lblTeacherNameList).size()];
	String[] expectedDisplayedInfor = new String[elements.length];

	// Run the for loop to find list of each element in the array.
	for (int i = 0; i < elements.length; i++) {
		List<WebElement> elementsList = driver.findElements(elements[i]);

		// Run the for loop again to get the text from each element in the elements list
		for (int j = 0; j < elementsList.size(); j++) {
			actualDisplayedInfor[i][j] = elementsList.get(j).getText();
		}
		expectedDisplayedInfor[i] = getValueFromDatabase(query, i + 1);
	}
	String actualResult = Arrays.deepToString(actualDisplayedInfor).replace("[", "").replace("]", "");
	String expectedResult = Arrays.deepToString(expectedDisplayedInfor).replace("[", "").replace("]", "").replace("null", "");
	assertEquals(actualResult, expectedResult);

}
@Test(description = "Validate Sorting By S.No", groups="logInAdmin")
public void sortingBySNo() throws ClassNotFoundException, SQLException {
	driver.navigate().to(pagePath);
	clickOnElement(amtp.lblSNO);
	verifyDisplayedInfor("SELECT Name, TeacherSub, RegDate FROM `tblteacher` ORDER by ID DESC;");
}

@Test(description = "Validate Sorting By Teacher Name", groups="logInAdmin")
public void sortingByTeacherName() throws ClassNotFoundException, SQLException {
	driver.navigate().to(pagePath);
	clickOnElement(amtp.lblTeacherName);
	verifyDisplayedInfor("SELECT Name, TeacherSub, RegDate FROM `tblteacher` ORDER by Name ASC;");
//	webWait();
	clickOnElement(amtp.lblTeacherName);
	verifyDisplayedInfor("SELECT Name, TeacherSub, RegDate FROM `tblteacher` ORDER by Name DESC;");
}

@Test(description = "Validate Sorting By Subject", groups="logInAdmin")
public void sortingBySubject() throws ClassNotFoundException, SQLException {
	driver.navigate().to(pagePath);
	clickOnElement(amtp.lblSubject);
	verifyDisplayedInfor("SELECT Name, TeacherSub, RegDate FROM `tblteacher` ORDER by TeacherSub ASC;");
//	webWait();
	clickOnElement(amtp.lblSubject);
	verifyDisplayedInfor("SELECT Name, TeacherSub, RegDate FROM `tblteacher` ORDER by TeacherSub DESC;");
}

@Test(description = "Validate Sorting By Creation Date", groups="logInAdmin")	
public void sortingByCreationDate() throws ClassNotFoundException, SQLException {
	driver.navigate().to(pagePath);
	clickOnElement(amtp.lblRegistrationDate);
	clickOnElement(amtp.lblRegistrationDate);
	verifyDisplayedInfor("SELECT Name, TeacherSub, RegDate FROM `tblteacher` ORDER by RegDate DESC;");
}

@Test(description = "Validate Navigate To Edit Teacher Page By Clicking On 'Delete'", groups="logInAdmin")
public void navigatingToEditPage() {
	driver.navigate().to(pagePath);
	clickOnElement(amtp.btnEdit);
	String currentURL= driver.getCurrentUrl();
	assertTrue(currentURL.contains("edit-teacher-detail"));
}

@Test(description = "Validate Navigate To Queries Page By Clicking On 'Queries' Button", groups="logInAdmin")
private void navigatingToQueriesPage() {
	driver.navigate().to(pagePath);
	clickOnElement(amtp.btnQueries);
	String currentURL= driver.getCurrentUrl();
	assertTrue(currentURL.contains("admin/queries.php?tid=1&&tname=Anuj%20kumar%20(Chemistry)"));
}

@DataProvider(name = "Successful Data")
public String[][] successfulData() throws IOException {
	String[][] data= excelUtils.getDataFromExcel(dataFilePath, "SearchTeacherSuccessfully");
	return data;
}
@Test(description = "Search Teacher Successfully", groups = "logInAdmin", dataProvider = "Successful Data")
public void searchTeacherSuccessfully(String data, String query) throws ClassNotFoundException, SQLException {
	driver.navigate().to(pagePath);
	sendKeys(amtp.txtSearch, data);
	driver.findElement(amtp.txtSearch).sendKeys(Keys.ENTER);
	verifyDisplayedInfor(query);
}

@DataProvider(name = "Field Blank Data", indices = {0})
public String[][]fieldBlankData() throws IOException {
	String[][] data= excelUtils.getDataFromExcel(dataFilePath, "SearchTeacherFail");
	return data;
}
@Test(description = "Search Teacher Fail When Leaving Field Blank", groups = "logInAdmin", dataProvider = "Field Blank Data")
public void searchTeacherFailWhenLeavingFieldBlank(String data, String expectedMessage) throws ClassNotFoundException, SQLException {
	driver.navigate().to(pagePath);
	sendKeys(amtp.txtSearch, data);
	driver.findElement(amtp.txtSearch).sendKeys(Keys.ENTER);
	String actualMessage= getHtml5ValidationMessage(amtp.txtSearch);
	assertEquals(actualMessage, expectedMessage);
}

@DataProvider(name = "No Result Found Data", indices = {1})
public String[][]invalidData() throws IOException {
	String[][] data= excelUtils.getDataFromExcel(dataFilePath, "SearchTeacherFail");
	return data;
}
@Test(description = "Search Teacher When No Result Is Found", groups = "logInAdmin", dataProvider = "No Result Found Data")
public void searchTeacherWhenNoResultIsFound(String data, String expectedMessage) throws ClassNotFoundException, SQLException {
	driver.navigate().to(pagePath);
	sendKeys(amtp.txtSearch, data);
	driver.findElement(amtp.txtSearch).sendKeys(Keys.ENTER);
	String actualMessage= driver.findElement(amtp.lblNoResultFound).getText();
	assertEquals(actualMessage, expectedMessage);
}
}
