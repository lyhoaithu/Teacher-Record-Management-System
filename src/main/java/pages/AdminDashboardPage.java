package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminDashboardPage extends Page{

	public AdminDashboardPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
public By logo= By.xpath("//a[@class='navbar-brand']");
public By iconDashboard= By.xpath("//a[contains(@href,'dashboard')]");
public By iconSubjects=By.xpath("//li[@class='menu-item-has-children dropdown']//a");
public By iconTeacher= By.xpath("//li[@class='menu-item-has-children dropdown'][2]//a");
public By iconSearch= By.xpath("//a[contains(@href,'search')]");
public By iconReports= By.xpath("//li[@class='menu-item-has-children dropdown'][3]//a");
public By iconUser=By.xpath("//div[@class='user-area dropdown float-right']");
public By linktextMyProfile= By.xpath("//a[contains(@href,'adminprofile')]");
public By linktextChangePassword= By.xpath("//a[contains(@href,'change-password')])");
public By linktextLogOut= By.xpath("//a[contains(@href,'logout')]");
public By cardListedSubjects= By.xpath("//div[@class='card-body pb-0']");
public By cardTotalRegisteredTeachers=By.xpath("//div[@class='card text-white bg-flat-color-2']//div");
public By cardRegisteredTeachersNotPublic= By.xpath("//div[@class='card text-white bg-flat-color-3']//div");
public By cardRegisteredTeachersPublic= By.xpath("//div[@class='card text-white bg-flat-color-5']//div");
public By iconAddSubject= By.xpath("//a[contains(@href,'add-subject')]");
public By iconManageSubject= By.xpath("//a[contains(@href,'manage-subject')]");
public By iconAddTeacher= By.xpath("//a[contains(@href,'add-teacher')]");
public By iconManageTeacher= By.xpath("//a[contains(@href,'manage-teacher')]");
public By iconBetweenDatesReport= By.xpath("//a[contains(@href,'bwdates-report')]");
}
