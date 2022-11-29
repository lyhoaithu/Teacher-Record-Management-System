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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.Excelutils;
import pages.TeacherQueriesPage;

public class TeacherQueriesPageTest extends TestCase {

	public TeacherQueriesPage tqp = new TeacherQueriesPage(driver);
	public Excelutils excelUtils = new Excelutils();
	public String pagepath = "http://localhost:8081/TRMS/trms/teacher/queries.php";

	public void verifyDisplayedInfor(String query) throws ClassNotFoundException, SQLException {
		By[] elements = { tqp.lblNameList, tqp.lblEmailList, tqp.lblMobileList, tqp.lblDateList };

		// Create a 2 ways Array to save the actual displayed information. Each big
		// array is a column in the table
		String[][] actualDisplayedInfor = new String[elements.length][driver.findElements(tqp.lblMobileList).size()];
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
		String expectedResult = Arrays.deepToString(expectedDisplayedInfor).replace("[", "").replace("]", "");
		assertEquals(actualResult, expectedResult);

	}

	@Test(description = "Validate Naviagting To Dashboard By Clicking On 'Dashboard' at breadcrum", groups = "logIn")
	public void clickOnDashboardAtBreadCrum() {
		driver.navigate().to(pagepath);
		clickOnElement(tqp.linktextDashboard);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("teacher/dashboard"));
	}

	@Test(description = "Validate Navigating To Queries Page By Clicking On 'Manage Queries' At Breadcrum", groups = "logIn")
	public void clickOnManageQueriesAtBreadcrum() {
		driver.navigate().to(pagepath);
		clickOnElement(tqp.linktextDashboard);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("teacher/queries"));
	}

	@Test(description = "Validate Displayed Information", groups = "logIn")
	public void validateDisplayedInformation() throws ClassNotFoundException, SQLException {
		// Create an Array to contain all the locators that need to findElementLists
		driver.navigate().to(pagepath);
		By[] elements = { tqp.lblNameList, tqp.lblEmailList, tqp.lblMobileList, tqp.lblDateList };

		// Create a 2 ways Array to save the actual displayed information. Each big
		// array is a column in the table
		String[][] actualDisplayedInfor = new String[elements.length][10];
		String[] expectedDisplayedInfor = new String[elements.length];

		// Run the for loop to find list of each element in the array.
		for (int i = 0; i < elements.length; i++) {
			List<WebElement> elementsList = driver.findElements(elements[i]);

			// Run the for loop again to get the text from each element in the elements list
			for (int j = 0; j < elementsList.size(); j++) {
				actualDisplayedInfor[i][j] = elementsList.get(j).getText();
			}
			expectedDisplayedInfor[i] = getValueFromDatabase(
					"SELECT fName, emailId, mobileNumber, queryDate FROM `tblquery` WHERE teacherId='2' LIMIT 10;",
					i + 1);
		}

		String actualResult = Arrays.deepToString(actualDisplayedInfor).replace("[", "").replace("]", "");
		String expectedResult = Arrays.deepToString(expectedDisplayedInfor).replace("[", "").replace("]", "");
		assertEquals(actualResult, expectedResult);
	}

	@Test(description = "Validate Page Navigation By Clicking On Page Number", groups = "logIn")
	public void validatePageNav() {
		driver.navigate().to(pagepath);
		clickOnElement(tqp.linktextCurrentPage);
		List<WebElement> elementsCurrentPage = driver.findElements(tqp.lblNameList);
		assertEquals(elementsCurrentPage.size(), 10);
		clickOnElement(tqp.linktextNextPage);
		List<WebElement> elementsNextPage = driver.findElements(tqp.lblNameList);
		assertEquals(elementsNextPage.size(), 1);
	}

	@Test(description = "Validate Page Navigation By Clicking On Previous And Next", groups = "logIn")
	public void validatePageNavByClickingOnPreviousAndNext() {
		driver.navigate().to(pagepath);
		clickOnElement(tqp.linktextNext);
		List<WebElement> elementsNextPage = driver.findElements(tqp.lblNameList);
		assertEquals(elementsNextPage.size(), 1);
		clickOnElement(tqp.linktextPrevious);
		List<WebElement> elementsPreviousPage = driver.findElements(tqp.lblNameList);
		assertEquals(elementsPreviousPage.size(), 10);
	}

	@Test(description = "Validate Sorting By S.NO", groups = "logIn")
	public void validateSortingBySNO() throws ClassNotFoundException, SQLException {
		driver.navigate().to(pagepath);
		clickOnElement(tqp.lblSNO);
		verifyDisplayedInfor(
				"SELECT fName, emailId, mobileNumber, queryDate FROM `tblquery` WHERE teacherId='2' ORDER BY id DESC LIMIT 10;");
	}

	@Test(description = "Validate Sorting By Name", groups = "logIn")
	public void validateSortingByName() throws ClassNotFoundException, SQLException {
		driver.navigate().to(pagepath);
		clickOnElement(tqp.lblName);
		verifyDisplayedInfor(
				"SELECT fName, emailId, mobileNumber, queryDate FROM `tblquery` WHERE teacherId='2' ORDER BY fName ASC LIMIT 10;");
		clickOnElement(tqp.lblName);
		verifyDisplayedInfor(
				"SELECT fName, emailId, mobileNumber, queryDate FROM `tblquery` WHERE teacherId='2' ORDER BY fName DESC LIMIT 10;");
	}

	@Test(description = "Validate Sorting By Email", groups = "logIn")
	public void validateSortingByEmail() throws ClassNotFoundException, SQLException {
		driver.navigate().to(pagepath);
		clickOnElement(tqp.lblName);
		verifyDisplayedInfor(
				"SELECT fName, emailId, mobileNumber, queryDate FROM `tblquery` WHERE teacherId='2' ORDER BY fName ASC LIMIT 10;");
		clickOnElement(tqp.lblName);
		verifyDisplayedInfor(
				"SELECT fName, emailId, mobileNumber, queryDate FROM `tblquery` WHERE teacherId='2' ORDER BY fName DESC LIMIT 10;");
	}

	@Test(description = "Validate Sorting By Mobile Phone", groups = "logIn")
	public void validateSortingByMobilePhone() throws ClassNotFoundException, SQLException {
		driver.navigate().to(pagepath);
		clickOnElement(tqp.lblMobile);
		verifyDisplayedInfor(
				"SELECT fName, emailId, mobileNumber, queryDate FROM `tblquery` WHERE teacherId='2' ORDER BY mobileNumber ASC LIMIT 10;");
		clickOnElement(tqp.lblMobile);
		verifyDisplayedInfor(
				"SELECT fName, emailId, mobileNumber, queryDate FROM `tblquery` WHERE teacherId='2' ORDER BY mobileNumber DESC LIMIT 10;");
	}

	@Test(description = "Validate Sorting By Date", groups = "logIn")
	public void validateSortingByDate() throws ClassNotFoundException, SQLException {
		driver.navigate().to(pagepath);
		clickOnElement(tqp.lblDate);
		verifyDisplayedInfor(
				"SELECT fName, emailId, mobileNumber, queryDate FROM `tblquery` WHERE teacherId='2' ORDER BY queryDate ASC LIMIT 10;");
		clickOnElement(tqp.lblDate);
		verifyDisplayedInfor(
				"SELECT fName, emailId, mobileNumber, queryDate FROM `tblquery` WHERE teacherId='2' ORDER BY queryDate DESC LIMIT 10;");
	}

	@DataProvider(name = "Search Query Successful Data")
	public String[][] successfulData() throws IOException {
		String[][] data = excelUtils.getDataFromExcel(dataFilePath, "SearchQuerySuccessfully");
		return data;

	}

	@Test(description = "Validate Search Query Successfully", groups = "logIn", dataProvider = "Search Query Successful Data")
	public void searchQuerySuccessfully(String data, String query) throws ClassNotFoundException, SQLException {
		driver.navigate().to(pagepath);
		sendKeys(tqp.txtSearch, data);
		driver.findElement(tqp.txtSearch).sendKeys(Keys.ENTER);
		verifyDisplayedInfor(query);
	}

	@Test(description = "Validate Navigating To Query Details Page By Clicking On 'View' Button")
	public void validateNavigatingToQueryDetailsPage() {
		driver.navigate().to(pagepath);
		clickOnElement(tqp.btnView);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("teacher/query-details"));
	}

}
