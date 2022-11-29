package pages;

import static org.testng.Assert.assertEquals;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TeacherQueriesPage extends Page{

	public TeacherQueriesPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
public By txtSearch= By.xpath("//input[@type='search']");
public By lblSNO= By.xpath("//table[@id='dtBasicExample']//th[1]");
public By lblName= By.xpath("//table[@id='dtBasicExample']//th[2]");
public By lblEmail= By.xpath("//table[@id='dtBasicExample']//th[3]");
public By lblMobile= By.xpath("//table[@id='dtBasicExample']//th[4]");
public By lblDate= By.xpath("//table[@id='dtBasicExample']//th[5]");
public By linktextDashboard= By.xpath("//ol//li//a[contains(@href,'dashboard')]");
public By linktextManageQueries= By.xpath("//ol//li//a[contains(@href,'manage-teacher')]");
public By btnView= By.xpath("//td//a[contains(@href,'query-details')][1]");
public By lblNameList= By.xpath("//tbody//tr//td[2]");
public By lblEmailList= By.xpath("//tbody//tr//td[3]");
public By lblMobileList= By.xpath("//tbody//tr//td[4]");
public By lblDateList= By.xpath("//tbody//tr//td[5]");
public By linktextPrevious= By.id("dtBasicExample_previous");
public By linktextNext= By.id("dtBasicExample_next");
public By linktextNextPage= By.xpath("//a[@class='paginate_button ']");
public By linktextCurrentPage= By.xpath("//a[@class='paginate_button current']");


}
