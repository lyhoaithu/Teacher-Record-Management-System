package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.Excelutils;
import pages.TeacherForgotPasswordPage;

public class TeacherForgotPasswordPageTest extends TestCase {

	public String pagePath = "http://localhost:8081/TRMS/trms/teacher/forgot-password.php";
	public Excelutils excelUtils = new Excelutils();
	public TeacherForgotPasswordPage tfpp = new TeacherForgotPasswordPage(driver);

	@Test(description = "Validate Navigating To Log In Page By Clicking On 'Sign In'", priority = 1)
	public void navigatingToLogInPage() {
		driver.navigate().to(pagePath);
		clickOnElement(tfpp.linktextSignIn);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("/teacher/index"));
	}

	@DataProvider(name = "Successful Data")
	public String[][] successfulData() throws IOException {
		String[][] data = excelUtils.getDataFromExcel(dataFilePath, "TeacherResetPassSuccessfully");
		return data;

	}

	@Test(description = "Validate Reseting Password Successfully", dataProvider = "Successful Data", priority = 4)
	public void resetPassSuccessfully(String email, String mobileNo, String newPass, String confirmPass,
			String expectedMessage) {
		driver.navigate().to(pagePath);
		sendKeys(tfpp.txtEmail, email);
		sendKeys(tfpp.txtMobileNo, mobileNo);
		sendKeys(tfpp.txtPassword, newPass);
		sendKeys(tfpp.txtConfirmPassword, confirmPass);
		clickOnElement(tfpp.btnReset);
		String actualMessage = getLocalMessage();
		assertEquals(actualMessage, expectedMessage);
	}

	@DataProvider(name = "Blank Field Data", indices = { 0, 1, 2, 3 })
	public String[][] blankFieldData() throws IOException {
		String[][] data = excelUtils.getDataFromExcel(dataFilePath, "TeacherResetPasswordFail");
		return data;
	}

	@Test(description = "Validate Forget Password Fail When Leaving Compulsory Field Blank", dataProvider = "Blank Field Data", priority = 2)
	public void resetPasswordFailWhenLeavingFieldBlank(String email, String mobile, String newPass, String confirmPass,
			String message) {
		driver.navigate().to(pagePath);
		By[] elements = { tfpp.txtEmail, tfpp.txtMobileNo, tfpp.txtPassword, tfpp.txtConfirmPassword };
		String[] data = { email, mobile, newPass, confirmPass };
		getHtml5ValidationFromASeriesOfFields(elements, data, tfpp.btnReset, message);
	}

	@DataProvider(name = "Incorrect/Invalid Data", indices = { 4, 5, 6, 7 })
	public String[][] incorrectAndInvalidData() throws IOException {
		String[][] data = excelUtils.getDataFromExcel(dataFilePath, "TeacherResetPasswordFail");
		return data;
	}

	@Test(description = "Validate Forget Pasword Fail When Provide Incorrect/Invalid Data", priority = 3, dataProvider ="Incorrect/Invalid Data" )
	public void validateResetingPasswordFailWhenProvidedIncorrectOrInvalidData(String email, String mobile,
			String newPass, String confirmPass, String expectedMessage) {
		driver.navigate().to(pagePath);
		sendKeys(tfpp.txtEmail, email);
		sendKeys(tfpp.txtMobileNo, mobile);
		sendKeys(tfpp.txtPassword, newPass);
		sendKeys(tfpp.txtConfirmPassword, confirmPass);
		clickOnElement(tfpp.btnReset);
		String actualMessage = getLocalMessage();
		assertEquals(actualMessage, expectedMessage);
	}
}
