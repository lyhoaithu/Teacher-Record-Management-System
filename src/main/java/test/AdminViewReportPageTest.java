package test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.Excelutils;
import pages.AdminViewReportPage;

public class AdminViewReportPageTest extends TestCase{

	public AdminViewReportPage avrp= new AdminViewReportPage(driver);
	public Excelutils excelUtils= new Excelutils();
	public String pagePath= "http://localhost:8081/TRMS/trms/admin/bwdates-report-ds.php";
	
	public void verifyDisplayedInfor(String query) throws ClassNotFoundException, SQLException {
		By[] elements = { avrp.lblTeacherNameList, avrp.lblSubjectList, avrp.lblRegistrationDateList};

		// Create a 2 ways Array to save the actual displayed information. Each big
		// array is a column in the table
		String[][] actualDisplayedInfor = new String[elements.length][driver.findElements(avrp.lblTeacherNameList).size()];
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
	
	@DataProvider(name = "Successfull Data")
	public String[][] successfullData() throws IOException {
	String[][] data= excelUtils.getDataFromExcel(dataFilePath, "ViewReportSuccessfully");
	return data;

	}
	@Test(description = "Validate View Report Successfully", groups = "logInAdmin", dataProvider = "Successfull Data")
    public void viewReportSuccessfsully(String fDate, String toDate, String query) throws ClassNotFoundException, SQLException {
		driver.navigate().to(pagePath);
		sendKeys(avrp.txtFDate, fDate);
		sendKeys(avrp.txtToDate, toDate);
		clickOnElement(avrp.btnSubmit);
		verifyDisplayedInfor(query);
	}
	
	@DataProvider(name = "Field Blank Data", indices = {0,1})
	public String[][] fieldBlankData() throws IOException {
	String[][] data= excelUtils.getDataFromExcel(dataFilePath, "ViewReportFail");
	return data;

	}
	@Test(description = "Validate View Report When Leaving Field Blank", groups = "logInAdmin", dataProvider = "Field Blank Data")
    public void viewReportWhenLeavingFieldBlank(String fDate, String toDate, String expectedMessage) throws ClassNotFoundException, SQLException {
		driver.navigate().to(pagePath);
		sendKeys(avrp.txtFDate, fDate);
		sendKeys(avrp.txtToDate, toDate);
		clickOnElement(avrp.btnSubmit);
		String actualMessage=null;
		if(fDate.equals("")) {
			actualMessage= getHtml5ValidationMessage(avrp.txtFDate);
		}
		else if (toDate.equals("")) {
			actualMessage=getHtml5ValidationMessage(avrp.txtToDate);
		}
		assertEquals(actualMessage, expectedMessage);
	}
	
	@DataProvider(name = "Invalid Data", indices = {2,3})
	public String[][] invalidData() throws IOException {
	String[][] data= excelUtils.getDataFromExcel(dataFilePath, "ViewReportFail");
	return data;

	}
	@Test(description = "Validate View Report When Leaving Field Blank", groups = "logInAdmin", dataProvider = "Invalid Data")
    public void viewReportWithInvalidData(String fDate, String toDate, String expectedMessage) throws ClassNotFoundException, SQLException {
		driver.navigate().to(pagePath);
		sendKeys(avrp.txtFDate, fDate);
		sendKeys(avrp.txtToDate, toDate);
		clickOnElement(avrp.btnSubmit);
		String actualMessage=null;
		if(fDate.length()==4) {
			actualMessage= getHtml5ValidationMessage(avrp.txtFDate);
		}
		else if (toDate.length()==4) {
			actualMessage=getHtml5ValidationMessage(avrp.txtToDate);
		}
		assertEquals(actualMessage, expectedMessage);
	}
}
