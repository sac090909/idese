package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import page.BasePage;
import page.LoginPage;
import util.BrowserFactory;

public class LoginPageTest extends BasePage{

	WebDriver driver;

	@Test
	public void validUserShouldBeAbleToLogin() throws InterruptedException {

		driver = BrowserFactory.getBrowswer();
		driver.get("http://techfios.com/test/billing/?ng=admin");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

		String expectedTitle = "Login - TechFios Test Application - Billing";
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "Login Page M!Smatched ..");

		loginPage.setUserName("techfiosdemo@gmail.com");
		loginPage.setPassword("abc123");
		loginPage.clickOnLoginButton();

		waitForElememt(driver, 10, loginPage.getDashboardPageHeaderTitleElement());
		//BrowserFactory.waitForElememt(driver, 10, loginPage.getDashboardPageHeaderTitleElement());
		
		Thread.sleep(3000);
		driver.close();
		driver.quit();

	}

}
