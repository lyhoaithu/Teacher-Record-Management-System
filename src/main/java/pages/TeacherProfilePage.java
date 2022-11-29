package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TeacherProfilePage extends Page{

	public TeacherProfilePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
public By txtTeacherName= By.name("tname");
public By imgEditImage= By.xpath("//a[contains(@href,'changeimage')]");
public By txtTeacherEmailID=By.name("email");
public By txtTeacherMobileNumber= By.id("mobilenumber");
public By txtTeacherAddress= By.id("address");
public By dpJoiningDate= By.id("joiningdate");
public By txtTeacherQualification= By.id("qualifications");
public By txtTeachingExperience= By.id("teachingexp");
public By ddTeacherSubjects= By.id("tsubjects");
public By txtDescription= By.id("description");
public By ddProfileStatus= By.id("status");
public By btnUpdate= By.id("submit");
public By btnChooseFile= By.id("newpic");
public By btnUpdateImage=By.id("submit");
public By lblTeacherList= By.xpath("//h5");
}
