package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TeacherForgotPasswordPage extends Page {

	public TeacherForgotPasswordPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public By txtEmail = By.name("email");
	public By txtMobileNo = By.name("mobile");
	public By txtPassword = By.name("newpassword");
	public By txtConfirmPassword = By.name("confirmpassword");
	public By linktextSignIn = By.xpath("//a[contains(@href,'index')]");
	public By btnReset = By.name("submit");

}
