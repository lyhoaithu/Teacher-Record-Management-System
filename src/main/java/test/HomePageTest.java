package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import pages.HomePage;

public class HomePageTest extends TestCase{

	public HomePage hp= new HomePage(driver);
	
	@Test(description = "Validate Navigating To Home By Clicking On The Web Logo")
	public void clickOnWebLogo() {
		driver.navigate().to(homePageLink);
		clickOnElement(hp.linktextLogo);
		String currentURL= driver.getCurrentUrl();
		assertEquals(currentURL, homePageLink);
	}
	
	@Test(description = "Validate Navigating To Home By Clicking On Home At Nav Bar")
	public void clickOnHomeAtNavBar() {
		driver.navigate().to(homePageLink);
		clickOnElement(hp.navLinHome);
		String currentURL= driver.getCurrentUrl();
		assertEquals(currentURL, homePageLink);
	}
	
	@Test (description = "Validate Navigating To Home By Clicking On Home At Footer")
	public void clickOnHomeAtFooter() {
		driver.navigate().to(homePageLink);
		scrollDownAtTheEndOfPage();
		JavascriptExecutor jse= (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", driver.findElement(hp.footerlinkHome));
		String currentURL= driver.getCurrentUrl();
		assertEquals(currentURL, homePageLink);
	}
	
	@Test(description = "Validate Navigating To About Us Page By Clicking On About Us At Nav Bar")
	public void clickOnAboutUsAtNavBar() {
		driver.navigate().to(homePageLink);
		clickOnElement(hp.navLinkAbout);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("about"));
	}
	
	@Test(description = "Validate Navigating To About Us Page By Clicking On About Us At Footer")
	public void clickOnAboutUsAtFooter() {
		driver.navigate().to(homePageLink);
		scrollDownAtTheEndOfPage();
		JavascriptExecutor jse= (JavascriptExecutor) driver;
	    jse.executeScript("arguments[0].click();", driver.findElement(hp.footerlinkAboutUs));
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("about"));
	}
	
	@Test(description = "Validate Navigating To About Us Page By Clicking On Contact At Nav Bar")
	public void clickOnContactAtNavBar() {
		driver.navigate().to(homePageLink);
		clickOnElement(hp.navLinkContact);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("contact"));
	}
	
	@Test(description = "Validate Navigating To Contact Page By Clicking On Contact At Footer")
	public void clickOnContactAtFooter() {
		driver.navigate().to(homePageLink);
		scrollDownAtTheEndOfPage();
		JavascriptExecutor jse= (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", driver.findElement(hp.footerlinkContact));
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("contact"));
	}
	
	@Test(description = "Validate Navigating To Listed Teachers On Nav Bar")
	public void clickOnListedTeachersAtNavBar() {
		driver.navigate().to(homePageLink);
		clickOnElement(hp.navLinkListedTeacher);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("listed-teachers"));
	}
	
	@Test(description = "Validate Navigating To Admin Login Page By Clicking On Admin At Navbar")
	public void clickOnAdminAtNavbar() {
		driver.navigate().to(homePageLink);
		clickOnElement(hp.navLinkAdmin);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("admin"));
	}
	
	@Test(description = "Validate Navigating To Teacher SignUp Page By Clicking On The Teacher Signup Button At Home")
public void clickOnTeacherSignUpButton() {
	driver.navigate().to(homePageLink);
	clickOnElement(hp.btnTeacherSignUp);
	String currentURL= driver.getCurrentUrl();
	assertTrue(currentURL.contains("teacher/signup"));
}
	
	@Test(description = "Validate Navigating To Teacher Login Page By Clicking On The Teacher Login Button At Home")
public void clickOnTeacherLoginButton() {
	driver.navigate().to(homePageLink);
	clickOnElement(hp.btnTeacherLogIn);
	String currentURL= driver.getCurrentUrl();
	assertTrue(currentURL.contains("teacher/index"));
}
	
	@Test(description = "Validate Navigating To Teacher Details Page By Clicking On The Teacher Pictures")
	public void clickOnTeacherPicture() {
		driver.navigate().to(homePageLink);
List<WebElement> teacherList= driver.findElements(hp.linktextTeacher);
JavascriptExecutor jse= (JavascriptExecutor)driver;
scrollDownAtTheEndOfPage();
for (int i=0; i<teacherList.size();i++) {
	jse.executeScript("arguments[0].click();", teacherList.get(i));
	ArrayList<String> tabs= new ArrayList<String>(driver.getWindowHandles());
	driver.switchTo().window(tabs.get(i+1));
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("teacher-details.php?tid="+(i+1)));
		driver.switchTo().window(tabs.get(0));
}

}
	}

