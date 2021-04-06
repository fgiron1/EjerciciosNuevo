package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Base {

	public static WebDriver driver;
	
	
	
	/**
	 * Startup - Instantiate webdriver and open Chrome browser
	 */
	
	@BeforeTest
	public static WebDriver startUpChromeBrowser() {
		try {

			driver = startUpChrome();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return driver;
	}
	
	/**
	 * Startup webdriver with chrome
	 */
	private static WebDriver startUpChrome() {
		ChromeOptions options = new ChromeOptions();
		
		try {
			
			System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
			
			options.addArguments("start-maximized"); // open Browser in maximized mode
			options.addArguments("--disable-infobars"); // disabling infobars
			options.addArguments("--disable-extensions"); // disabling extensions

			driver = new ChromeDriver(options);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return driver;
	}	
	
	
	/**
	 * Startup webdriver with Firefox
	 */
	private static WebDriver startUpFirefox() {
		
		FirefoxOptions options = new FirefoxOptions();
		
		try {
			
			System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
			
			options.addArguments("start-maximized"); // open Browser in maximized mode
			options.addArguments("--disable-infobars"); // disabling infobars
			options.addArguments("--disable-extensions"); // disabling extensions
			driver = new FirefoxDriver(options);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return driver;
	}
	
	/**
	 * Startup - Create webdriver and open Firefox browser
	 */
	public static WebDriver startUpFirefoxBrowser() {
		try {

			driver = startUpFirefox();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return driver;
	}
	
	/**
	 * Navigate to url
	 */
	public static void navigateURL(String url) throws Exception {
		try {
			driver.get(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Close browser and kill process
	 */
	
	@AfterTest
	public void tearDown() {
		
		
		try {
			driver.close();
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	
	
	
	
}
