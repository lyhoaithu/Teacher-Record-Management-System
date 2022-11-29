package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import pages.QueryDetailsPage;

public class QueryDetailsPageTest extends TestCase {
	public QueryDetailsPage qdp= new QueryDetailsPage(driver);
	public String pagePath="http://localhost:8081/TRMS/trms/teacher/query-details.php?qid=5";
	
	@Test(description = "Validate Navigating To Dashboard By Clicking On 'Dashboard' At Breadcrum", groups="logIn")
	public void navigatingToDashboard() {
		driver.navigate().to(pagePath);
		clickOnElement(qdp.linktextDashboard);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("teacher/dashboard"));
	}
	
	@Test(description = "Validate Navigating To Query Details Page By Clicking On 'Query Details' At Breadcrum", groups="logIn")
	public void navigatingToQueryDetailspage() {
		driver.navigate().to(pagePath);
		clickOnElement(qdp.linktextQueryDetails);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("query-details"));
	}
	
@Test(description = "Validate Update Note Successfully", groups="logIn", priority = 1)
public void updateNoteSuccessfully() {
	driver.navigate().to(pagePath);
	sendKeys(qdp.txtNote, "Note Solved123!");
	clickOnElement(qdp.btnUpdate);
	String actualMessage= getLocalMessage();
	assertEquals(actualMessage, "Notes updated succesfull.");
}

@Test(description = "Validate Update Not Fail", groups = "logIn", priority = 2)
public void updateNoteFail() {
	driver.navigate().to(pagePath);
	driver.findElement(qdp.txtNote).clear();
	clickOnElement(qdp.btnUpdate);
	String actualMessage= getHtml5ValidationMessage(qdp.txtNote);
	assertEquals(actualMessage, "Please fill out this field.");
}
}
