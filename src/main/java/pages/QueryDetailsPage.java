package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class QueryDetailsPage extends Page{

	public QueryDetailsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
public By linktextDashboard= By.xpath("//ol//li//a[contains(@href,'dashboard')]");
public By linktextQueryDetails= By.xpath("//ol//li//a[contains(@href,'manage-teacher')]");
public By txtNote= By.name("notes");
public By btnUpdate=By.name("submit");
}
