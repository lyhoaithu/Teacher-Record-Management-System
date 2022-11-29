package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminLogInPage extends Page{

	public AdminLogInPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
public By txtUserName= By.name("username");
public By txtPassword= By.name("password");
public By linktextBackHome= By.xpath("//a[contains(@href,'index')]");
public By linktextForgotPassword= By.xpath("//a[contains(@href,'forgot-password')]");
public By btnSignIn=By.xpath("//button[@type='submit']");
}
