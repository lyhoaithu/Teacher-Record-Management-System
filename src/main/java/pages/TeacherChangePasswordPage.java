package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TeacherChangePasswordPage extends Page{

	public TeacherChangePasswordPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
public By txtCurrentPassword=By.id("currentpassword");
public By txtNewPass= By.name("newpassword");
public By txtConfirmPass= By.id("confirmpassword");
public By btnChange=By.id("submit");
}
