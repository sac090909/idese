package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserFactory {

	public static WebDriver driver;

	public static WebDriver getBrowswer() {

		//driver = null;
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		return driver;

	}
/*
	public static void waitForElememt(WebDriver driver, int timeInSeconds, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		BrowserFactory.driver = driver;
		wait.until(ExpectedConditions.visibilityOf(element));

	}
*/
}
