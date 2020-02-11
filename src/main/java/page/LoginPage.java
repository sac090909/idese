package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.ID, using = "username")
	WebElement UserName;
	@FindBy(how = How.ID, using = "password")
	WebElement Password;
	@FindBy(how = How.NAME, using = "login")
	WebElement SingInButton;
	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Dashboard')]")
	WebElement DashboardPageHeaderTitle;

	public String getLoginPageTitle() {
		return driver.getTitle();

	}

	public void setUserName(String uid) {
		UserName.sendKeys(uid);
	}

	public void setPassword(String pwd) {
		Password.sendKeys(pwd);
	}

	public void clickOnLoginButton() {
		SingInButton.click();

	}
	
	public WebElement getDashboardPageHeaderTitleElement() {
		return DashboardPageHeaderTitle;
	}


}
