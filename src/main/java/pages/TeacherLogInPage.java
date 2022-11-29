package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TeacherLogInPage extends Page{

	public TeacherLogInPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
public By txtEmail= By.xpath("//input[@name='username']");
public By txtPassword= By.xpath("//input[@name='password']");
public By linktextBackHome= By.xpath("//a[contains(@href,'index')]");
public By linktextForgetPassword= By.xpath("//a[contains(@href,'forgot-password')]");
public By btnLogin= By.xpath("//button[@name='login']");
public By linktextRegister=By.xpath("//a[contains(@href,'signup')]");
public By lblTeacherName= By.xpath("//span");
public By frmLogIn= By.xpath("//div[@class='login-form']");
}
