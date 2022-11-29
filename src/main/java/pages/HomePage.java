package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Page{

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
public By linktextLogo=By.xpath("//a[@class='navbar-brand']");
public By navLinHome= By.xpath("//a[@class='nav-link'][1]");
public By navLinkAbout= By.xpath("//li[@class='nav-item'][2]//a[@class='nav-link']");
public By navLinkListedTeacher= By.xpath("//li[@class='nav-item'][3]//a[@class='nav-link']");
public By navLinkContact= By.xpath("//li[@class='nav-item'][4]//a[@class='nav-link']");
public By navLinkAdmin= By.xpath("//li[@class='nav-item'][5]//a[@class='nav-link']");
public By btnTeacherSignUp= By.xpath("//a[contains(@href,'teacher/signup')]");
public By btnTeacherLogIn= By.xpath("//a[contains(@href,'teacher/index')]");
public By linktextTeacher=By.xpath("//div[@class='col-lg-4 mb-5']//a");
//public By linktextTeacher02=By.xpath("//div[@class='col-lg-4 mb-5']");
//public By linktextTeacher03=By.xpath("//div[@class='col-lg-4 mb-5'][3]");
public By footerlinkHome=By.xpath("//a[@class='link-light small'][1]");
public By footerlinkAboutUs=By.xpath("//a[@class='link-light small'][2]");
public By footerlinkContact=By.xpath("//a[@class='link-light small'][3]");
}
