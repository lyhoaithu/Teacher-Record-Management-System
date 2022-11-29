package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminManageTeacherPage extends Page{

	public AdminManageTeacherPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
public By txtSearch= By.xpath("//input[@type='search']");
public By btnEdit= By.xpath("//a[contains(@href,'edit-teacher-detail')]");
public By btnDelete= By.xpath("//a[contains(@href,'delid')]");
public By btnQueries=By.xpath("//a[contains(@href,'queries')]");
public By lblTeacherNameList=By.xpath("//tbody//tr//td[2]");
public By lblSubjectList= By.xpath("//tbody//tr//td[3]");
public By lblRegistrationDateList= By.xpath("//tbody//tr//td[4]");
public By lblSNO= By.xpath("//th[@class='sorting sorting_asc']");
public By lblTeacherName= By.xpath("//th[@aria-controls='dtBasicExample'][2]");
public By lblSubject= By.xpath("//th[@aria-controls='dtBasicExample'][3]");
public By lblRegistrationDate= By.xpath("//th[@aria-controls='dtBasicExample'][4]");
public By lblNoResultFound= By.xpath("//tbody//tr//td");
}
