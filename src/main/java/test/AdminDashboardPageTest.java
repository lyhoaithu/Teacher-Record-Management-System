package test;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import pages.AdminDashboardPage;

public class AdminDashboardPageTest extends TestCase{

	public AdminDashboardPage adp= new AdminDashboardPage(driver);
	
	@Test(description = "Validate Navigating To Dashboard By Clicking On Logo", groups= {"logInAdmin"})
	public void navigatingToDashboardByClickingOnLogo() {
		clickOnElement(adp.logo);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("admin/dashboard"));
	}
	
	@Test(description = "Validate Navigating To Dashboard By Clicking Dashboard Icon", groups= {"logInAdmin"})
	public void navigatingToDashboardByClickingOnDashboardIcon() {
		clickOnElement(adp.iconDashboard);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("admin/dashboard"));
	}

	@Test(description = "Validate Navigating To Add Subject Page By Clicking On Add Subject At Dropdown", groups= {"logInAdmin"})
	public void navigatingToAddSubjectPageByClickingOnIcon() {
		clickOnElement(adp.iconSubjects);
		clickOnElement(adp.iconAddSubject);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("trms/admin/add-subjects"));
	}
	
	@Test(description = "Validate Navigating To Manage Subject Page By Clicking On Add Subject At Dropdown", groups= {"logInAdmin"})
	public void navigatingToManageSubjectPageByClickingOnIcon() {
		clickOnElement(adp.iconSubjects);
		clickOnElement(adp.iconManageSubject);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("trms/admin/manage-subjects"));
	}

	@Test(description = "Validate Navigating To Add Teacher Page By Clicking On Add Teacher At Dropdown", groups= {"logInAdmin"})
	public void navigatingToAddTeacherPageByClickingOnIcon() {
		clickOnElement(adp.iconTeacher);
		clickOnElement(adp.iconAddTeacher);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("trms/admin/add-teacher"));
	}
	
	@Test(description = "Validate Navigating To Manage Teacher Page By Clicking On Manage Teacher At Dropdown", groups= {"logInAdmin"})
	public void navigatingToManageTeacherPageByClickingOnIcon() {
		clickOnElement(adp.iconTeacher);
		clickOnElement(adp.iconManageTeacher);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("trms/admin/manage-teacher"));
	}
	
	@Test(description = "Validate Navigating To Search Page By Clicking On Manage Teacher At Dropdown", groups= {"logInAdmin"})
	public void navigatingToSearchPageByClickingOnIcon() {
		clickOnElement(adp.iconSearch);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("admin/search"));
	}
	
	@Test(description = "Validate Navigating To Reports Page By Clicking On Reports At Dropdown", groups= {"logInAdmin"})
	public void navigatingToReportsPageByClickingOnIcon() {
		clickOnElement(adp.iconReports);
		clickOnElement(adp.iconBetweenDatesReport);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("trms/admin/bwdates-report-ds"));
	}
	
	@Test(description = "Validate Navigating To Profile Page By Clicking On User Icon", groups= {"logInAdmin"})
	public void navigatingToProfilePageByClickingOnIcon() {
		clickOnElement(adp.iconUser);
		clickOnElement(adp.linktextMyProfile);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("admin/adminprofile"));
	}
	
	@Test(description = "Validate Navigating To Change Password Page By Clicking On User Icon", groups= {"logInAdmin"})
	public void navigatingToChangePasswordPageByClickingOnIcon() {
		clickOnElement(adp.iconUser);
		clickOnElement(adp.linktextChangePassword);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("trms/admin/change-password"));
	}
	
	@Test(description = "Validate Log Out By Clicking On User Icon", groups= {"logInAdmin"})
	public void validateLogOutByClickingOnIcon() {
		JavascriptExecutor jse= (JavascriptExecutor)driver;
		clickOnElement(adp.iconUser);
		clickOnElement(adp.linktextLogOut);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("trms/admin/index"));
	}
	
	@Test(description = "Validate Naviagting To Listed Subjects Page By Clicking On Card At Dashboard", groups = "logInAdmin")
	public void clickOnListedSubjectsCard() {
		clickOnElement(adp.cardListedSubjects);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("admin/manage-subject"));
	}
	
	@Test(description = "Validate Naviagting To Total Registered Teacher Page By Clicking On Card At Dashboard", groups = "logInAdmin")
	public void clickOnRegisteredTeacherCard() {
		clickOnElement(adp.cardTotalRegisteredTeachers);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("admin/manage-teacher"));
	}
	

	@Test(description = "Validate Naviagting To Total Registered Teacher Not Public Page By Clicking On Card At Dashboard", groups = "logInAdmin")
	public void clickOnRegisteredTeacherNotPublucCard() {
		clickOnElement(adp.cardRegisteredTeachersNotPublic);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("/admin/manage-notpublicprofileteacher"));
	}
	
	@Test(description = "Validate Naviagting To Total Registered Teacher Public Page By Clicking On Card At Dashboard", groups = "logInAdmin")
	public void clickOnRegisteredTeacherPublicCard() {
		clickOnElement(adp.cardRegisteredTeachersPublic);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("/admin/manage-publicprofileteacher"));
	}
}
