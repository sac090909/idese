package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DashboardPage {

	WebDriver driver;

	public DashboardPage(WebDriver driver) {

		this.driver = driver;

	}

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Bank & Cash')]")
	WebElement BankCashMenu;
	@FindBy(how = How.LINK_TEXT, using = "New Account")
	WebElement NewAccountMenu;
	@FindBy(how = How.ID, using = "account")
	WebElement AccountTitle;
	@FindBy(how = How.ID, using = "description")
	WebElement Description;
	@FindBy(how = How.ID, using = "balance")
	WebElement InitialBalance;
	@FindBy(how = How.XPATH, using = "//button[@type='submit' and @class='btn btn-primary']")
	WebElement SubmitButton;

	@FindBy(how = How.XPATH, using = "//div[@class='alert alert-success fade in']//child::i")
	WebElement AcccountCreateSuccessMessage;

//  FindBy does not take variable
//	@FindBy(how = How.XPATH, using = "//td[contains(text(),'"+rndAccountNameExpected+"')]//following-sibling::*[2]//child::a[2]")
//	WebElement AccountDeleteButton;

	@FindBy(how = How.XPATH, using = "//table[@class='table table-striped table-bordered']")
	WebElement AccountDeleteButton;

	public WebElement getDynamicValueForAccountDeleteButton(String dynamicAccount) {
		return AccountDeleteButton.findElement(
				By.xpath("//table[@class='table table-striped table-bordered']/descendant::td[contains(text(),'"
						+ dynamicAccount + "')]//following-sibling::*[2]//child::a[2]"));

	}

	@FindBy(how = How.XPATH, using = "//button[@data-bb-handler='confirm']")
	WebElement AccountDeleteConfirmationOkButton;

	@FindBy(how = How.XPATH, using = "//div[@class='alert alert-success fade in']//child::i")
	WebElement AccountDeleteSuccessMessage;

	public String getDashboardPageTitle() {
		return driver.getTitle();
	}

	public void clickOnBankCashMenu() {
		BankCashMenu.click();
	}

	public void clickOnNewAccountMenu() {
		NewAccountMenu.click();
	}

	public void setAccountTitle(String accountName) {
		AccountTitle.sendKeys(accountName);
	}

	public void setDescription(String description) {
		Description.sendKeys(description);
	}

	public void setInitialBalance(String initialBalance) {
		InitialBalance.sendKeys(initialBalance);
	}

	public void clickSubmitButton() {
		SubmitButton.click();
	}

	public WebElement displayAcccountCreateSuccessMessageActual() {
		return AcccountCreateSuccessMessage;
	}

	public void clickOnAccountDeleteButton() {
		AccountDeleteButton.click();
	}

	public WebElement getAccountDeleteConfirmationOkButtonElement() {
		return AccountDeleteConfirmationOkButton;
	}

	public void clickOnAccountDeleteConfirmationOkButton() {
		AccountDeleteConfirmationOkButton.click();
	}

	public WebElement displayAcccountDeleteSuccessMessageActual() {
		return AccountDeleteSuccessMessage;
	}

}
