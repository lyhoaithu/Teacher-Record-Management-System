package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TeacherDashboardPage extends Page{

	public TeacherDashboardPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
public By logo= By.xpath("//a[@class='navbar-brand']");
public By iconDashboard=By.xpath("//li//a[contains(@href,'dashboard')]");
public By iconQueries=By.xpath("//a[contains(@href,'queries')]"); 
public By iconProfile= By.xpath("//a[contains(@href,'profile')]");
public By iconChangePass= By.xpath("//a[contains(@href,'change-pass')]");
public By iconLogOut= By.xpath("//a[contains(@href,'logout')]");
public By iconUser= By.xpath("//div[@class='user-area dropdown float-right']");
public By sddMyProfile= By.xpath("//div[@class='user-menu dropdown-menu show']//a[1]");
public By sddChangePassword= By.xpath("//div[@class='user-menu dropdown-menu show']//a[2]");
public By sddLogOut= By.xpath("//div[@class='user-menu dropdown-menu show']//a[3]");
}
