package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminViewReportPage extends Page{

	public AdminViewReportPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
public By txtFDate= By.name("fromdate");
public By txtToDate = By.name("todate");
public By btnSubmit = By.name("submit");
public By lblTeacherNameList=By.xpath("//tbody//tr//td[2]");
public By lblSubjectList= By.xpath("//tbody//tr//td[3]");
public By lblRegistrationDateList= By.xpath("//tbody//tr//td[4]");
}
