package test;

import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import pages.YoutubeHome;
import utils.Base;
import utils.ExtentManager;

public class SearchTest {
  
	//Step 1. Open the browser and navigate to youtube home page
	//Step 2. Deny the login request pop up and accept cookie usage
	//Step 3. Write something on the search bar and search it.

	@Test
	@Parameters({"searchText", "browserUsed"})
	public void search(String searchText, String browserUsed) throws Exception {
		
		
		//Instantiation of necessary objects
		
		ExtentReports extent = ExtentManager.getReporter();
		ExtentManager.attachSparkReporter("./src/test/resources/"); 
		ExtentTest test = extent.createTest("Youtube search test");
		
		YoutubeHome HomePage = new YoutubeHome();
		SearchTest helper = new SearchTest();
		
		//Step 1. Open the browser and navigate to youtube home page
				
		if(browserUsed.equalsIgnoreCase("chrome")) {
			Base.driver = Base.startUpChromeBrowser();
		} else if(browserUsed.equalsIgnoreCase("firefox")) {
			Base.driver = Base.startUpFirefoxBrowser();
		}
		
		
		try {
			Base.navigateURL("http://www.youtube.com/");
			test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath("./src/test/resources/Step1_success.png", "Step 1 completed").build());
			
		} catch (Exception e) {
			test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath("./src/test/resources/Step1_fail.png", "Step 1 failed").build());
			test.fail(e);
		}
		
		
		
		
		
		//End of step 1.
		
		
		//Step 2. Deny the login request pop up and accept cookie usage
		
		try {
			helper.skipLoginAndAcceptCookies(HomePage);
			
			//If an exception was not thrown, and the test case was designed well, the only possibility
			//left is that it has completed this step successfully
			test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath("./src/test/resources/Step2_success.png", "Step 2 completed").build());
			
		} catch (Exception e) {
			//Blocking error, thus, not only this step fails, but the whole test
			test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath("./src/test/resources/Step2_fail.png", "Step 2 failed").build());
			test.fail(e);
			
		}
		
		
		
		
		
		//End of Step 2.
		
		
		//Step 3. Write inside the search box and search.
		
		
		try {
			
			WebElement searchBox = HomePage.searchBox(Base.driver);
			
			//This could also be achieved by using the search button WebElement,
			//instead of the enter key
				
			searchBox.click();
			searchBox.sendKeys(searchText + Keys.ENTER);
			
			//Checking current URL to see if it worked
			
			String urlQuery = "https://www.youtube.com/results?search_query=" + searchText.replace(" ", "+");
			
			if(urlQuery.equals(Base.driver.getCurrentUrl())) {
				test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath("./src/test/resources/Step3_success.png", "Step 3 completed").build());
			} else {
				test.log(Status.FAIL,  MediaEntityBuilder.createScreenCaptureFromPath("./src/test/resources/Step3_fail.png", "Step 3 failed").build());
			}
		
		} catch(Exception e) {
			
			//Blocking error, thus, not only this step fails, but the whole test
			test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath("./src/test/resources/Step3_fail.png", "Step 3 failed").build());
			test.fail(e);
			
		}
		
		
		//End of Step 3.
		
		extent.flush();
	}
	
	
	private void skipLoginAndAcceptCookies(YoutubeHome HomePage) throws Exception {
		
		//OLD COOKIES POP UP The button is inside an iframe tag. In the method call, the driver
		//switches into it. After it's done, it has to switch back into the parent document
				
				try {
					WebElement agreeCookiesBtn = HomePage.agreeCookiesBtn(Base.driver);
					agreeCookiesBtn.click();
				} catch (Exception e) {
					throw e;
				}
				
				
				//Base.driver.switchTo().parentFrame();
		
		//Both WebElements are in different try/catch blocks because the cookie menu
		//only exists after clicking on the login pop up
		
		try {
			
			WebElement refuseLoginBtn = HomePage.refuseLoginBtn(Base.driver);
			refuseLoginBtn.click();
			
		} catch (Exception e) {
			throw e;
		}
		
		
		
	}
	
}
