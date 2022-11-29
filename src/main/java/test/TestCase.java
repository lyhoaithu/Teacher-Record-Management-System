package test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import common.TestBase;
import pages.TeacherLogInPage;


public class TestCase extends TestBase{
@BeforeMethod(alwaysRun = true)
public void openBrowser() {
	String browser="edge";
	openBroswer(browser);
}

@AfterMethod (alwaysRun = true)
public void closeBrowser() {
	driver.quit();
}

public String dataFilePath="D:\\AutomationTest\\02Projects\\TeacherRecordManagementSystem\\TestData\\TRMS_TestData.xlsx";
public String homePageLink= "http://localhost:8081/TRMS/trms/index.php";

@BeforeMethod(onlyForGroups = {"logIn"})
public void preConLogIn() {
	TeacherLogInPage tlp= new TeacherLogInPage(driver);
	driver.navigate().to("http://localhost:8081/TRMS/trms/teacher/index.php");
	sendKeys(tlp.txtEmail, "jogoe12@yourdomain.com");
	sendKeys(tlp.txtPassword, "Test@123");
	clickOnElement(tlp.btnLogin);
}

@BeforeMethod(onlyForGroups = {"logInAdmin"})
public void preConLogInAdmin() {
	TeacherLogInPage tlp= new TeacherLogInPage(driver);
	driver.navigate().to("http://localhost:8081/TRMS/trms/admin/index.php");
	sendKeys(tlp.txtEmail, "admin");
	sendKeys(tlp.txtPassword, "Test@123");
	clickOnElement(tlp.btnLogin);
}
}

