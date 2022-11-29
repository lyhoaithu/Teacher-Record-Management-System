package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminManageSubjectPage extends Page{

	public AdminManageSubjectPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
public By btnEdit= By.xpath("//a[contains(@href,'edit-subjects-detail')]");
public By btnDelete= By.xpath("//a[contains(@href,'delid=1')]");
public By txtSubjectName= By.name("subjects");
public By btnUpdate=By.name("submit");
public By lblSubjectNameList= By.xpath("//tbody//tr//td[2]");
public By lblCreationDate= By.xpath("//tbody//tr//td[3]");
}
