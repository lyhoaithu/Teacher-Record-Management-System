package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddTeacherPage extends Page{

	public AddTeacherPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
public By txtTeacherName= By.id("tname");
public By txtImage= By.id("propic");
public By txtTeacherEmailID= By.id("email");
public By txtTeacherMobileNumber= By.id("mobilenumber");
public By txtTeacherAddress= By.id("address");
public By txtTeacherQualifications= By.id("qualifications");
public By txtTeachingExperience= By.id("teachingexp");
public By ddbSubjects= By.id("tsubjects");
public By txtDescription= By.id("description");
public By txtJoiningDate= By.id("joiningdate");
public By btnAdd= By.id("submit");

}
