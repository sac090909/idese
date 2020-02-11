package test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import page.BasePage;
import page.DashboardPage;
import page.LoginPage;
import util.BrowserFactory;

public class DashboardPageTest extends BasePage {

	WebDriver driver;

	@BeforeTest
	public void initialization() {
		driver = BrowserFactory.getBrowswer();
		driver.get("http://techfios.com/test/billing/?ng=admin");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test(priority = 0)

	public void validUserShouldAbleToLogin() throws InterruptedException {
		
//	    Here LoginPageTest TestCase runs as user login is required for DashboardPage.
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

		String expectedTitleLoginPage = "Login - TechFios Test Application - Billing";
		String actualTitleLoginPage = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitleLoginPage, expectedTitleLoginPage, "Login Page M!Smatched ..");

		loginPage.setUserName("techfiosdemo@gmail.com");
		loginPage.setPassword("abc123");
		loginPage.clickOnLoginButton();

//      Login validation by Dashboard Page element by Explicit Wait . Need to know where actually util type methods can be used and how!
		//BrowserFactory.waitForElememt(driver, 10, loginPage.getDashboardPageHeaderTitleElement());
		waitForElememt(driver, 10, loginPage.getDashboardPageHeaderTitleElement());

	}

	@Test(priority = 1)
	public void addNewAccount() throws InterruptedException {

//      How to use LoginPageTest info DashboardPageTest without complete reusing of LoginPageTest code when test are seperated 
//		as per Page Object Model design pattern
		DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);

		// Dashboard page validation by Assert
		String expectedTitleDashboardPage = "Dashboard- TechFios Test Application - Billing";
		String actualTitleDashboardPage = dashboardPage.getDashboardPageTitle();
		Assert.assertEquals(actualTitleDashboardPage, expectedTitleDashboardPage, "Dashboard Page M!Smatched ..");

//		Where to use this type of util things 
		Random rnd = new Random();
		int rndAmount = rnd.nextInt(99);
		String rndAccountNameExpected = "Test Account " + rndAmount;
		String rndDescriptionExpected = "Test Account By Fall19 Std " + rndAmount*2;
		String rndInitialBalance = String.valueOf(rndAmount);

		// DashboardPageTest start here

		dashboardPage.clickOnBankCashMenu();
		dashboardPage.clickOnNewAccountMenu();
		Thread.sleep(1000);
		dashboardPage.setAccountTitle(rndAccountNameExpected);
		dashboardPage.setDescription(rndDescriptionExpected);
		dashboardPage.setInitialBalance(rndInitialBalance);
		dashboardPage.clickSubmitButton();
		Thread.sleep(2000);

		// Account create validation
		Assert.assertTrue(dashboardPage.displayAcccountCreateSuccessMessageActual().isDisplayed(),
				"Account Created Successfully Not Displayed !!");

		// Scroll to bottom need to delete account
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)"); // js.executeScript("window.scrollTo(0,
																			// 0)"); // Scroll top
		Thread.sleep(2000);

//		Page Object Page Factory design pattern - not the same way. Issue to define WebElement with Variable @FindBy. What to do ?
		dashboardPage.getDynamicValueForAccountDeleteButton(rndAccountNameExpected).click();

		// Without delay ElementNotInteractableException comes. Disable Implicit wait
		// and Explicit wait to see this.
		//Thread.sleep(2000);
        //BrowserFactory.waitForElememt(driver, 10, dashboardPage.getAccountDeleteConfirmationOkButtonElement());
        waitForElememt(driver, 10, dashboardPage.getAccountDeleteConfirmationOkButtonElement());
		dashboardPage.clickOnAccountDeleteConfirmationOkButton();

		// Validation of Account Delete Successfull by Assert and visually
		Assert.assertTrue(dashboardPage.displayAcccountDeleteSuccessMessageActual().isDisplayed(),
				"Account Delete Successfully Not Displayed !!");
		Thread.sleep(5000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);

//      Account delete validation try by Assert. Element not present. How to do
		// Assert.assertFalse((driver.findElement(By.xpath("//td[contains(text(),'"+rndAccountNameExpected+"')]//following-sibling::*[2]//child::a[2]"))).isDisplayed()
		// ,"Deleted Account Displayed");

	}

	@AfterTest
	public void exitBrowser() {

		driver.close();
		driver.quit();

	}

}
