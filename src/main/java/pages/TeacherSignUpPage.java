package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TeacherSignUpPage extends Page {

	public TeacherSignUpPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
public By txtName=By.name("fname");
public By txtEmail= By.name("emailid");
public By txtMobileNo= By.name("mobileno");
public By txtPassword=By.name("password");
public By btnSignUp=By.name("submit");
public By linktextHome=By.xpath("//a[contains(@href,'index')]");
public By linktextLogIn=By.xpath("//label[@class='pull-right']//a");
public By frmLogIn= By.xpath("//div[@class='login-form']");
}
