package test;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import pages.TeacherDashboardPage;

public class TeacherDashboardPageTest extends TestCase {
	public TeacherDashboardPage tdbp = new TeacherDashboardPage(driver);

	@Test(description = "Validate Navigating To Dashboard By Clicking On Logo", groups = "logIn")
	public void clickOnLogo() {
		clickOnElement(tdbp.logo);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("teacher/dashboard"));
	}

	@Test(description = "Validate Navigating To Dashboard By Clicking On Dashboard Icon", groups = "logIn")
	public void clickOnDashboardIcon() {
		clickOnElement(tdbp.iconDashboard);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("teacher/dashboard"));
	}

	@Test(description = "Validate Navigating To Queries Page By Clicking On Queries Icon", groups = "logIn")
	public void clickOnQueriesIcon() {
		clickOnElement(tdbp.iconQueries);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("teacher/queries"));
	}

	@Test(description = "Validate Navigating To Profile Page By Clicking On Profile Icon", groups = "logIn")
	public void clickOnProfileIcon() {
		clickOnElement(tdbp.iconProfile);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("teacher/profile"));
	}

//	
	@Test(description = "Validate Navigating To Profile Page By Clicking On Profile At Dropdown", groups = "logIn")
	public void clickOnProfileAtDropdown() {
		clickOnElement(tdbp.iconUser);
		clickOnElement(tdbp.sddMyProfile);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("teacher/profile"));
	}

	@Test(description = "Validate Navigating To Change Password Page By Clicking On Change Password Icon", groups = "logIn")
	public void clickOnChangePassIcon() {
		clickOnElement(tdbp.iconChangePass);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("teacher/change-password"));
	}

	@Test(description = "Validate Navigating To Change Password Page By Clicking On Change Password At Dropdown", groups = "logIn")
	public void clickOnChangePassAtDropdown() {
		clickOnElement(tdbp.iconUser);
		clickOnElement(tdbp.sddChangePassword);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("teacher/change-password"));
	}

	@Test(description = "Validate Log Out By Clicking On Log Out Icon", groups = "logIn")
	public void clickOnLogOutIcon() {
		clickOnElement(tdbp.iconLogOut);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("teacher/index"));
	}

	@Test(description = "Validate Log Out By Clicking On Log Out At Dropdown", groups = "logIn")
	public void clickOnLogOutAtDropdown() {
		clickOnElement(tdbp.iconUser);
		clickOnElement(tdbp.sddLogOut);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("teacher/index"));
	}
}
