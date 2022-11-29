package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminAddSubjectPage extends Page {

	public AdminAddSubjectPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public By txtSubjectName = By.name("subjects");
	public By btnAdd = By.name("submit");
	public By lblSubjectName= By.xpath("//tbody//tr//td[2]");
}
