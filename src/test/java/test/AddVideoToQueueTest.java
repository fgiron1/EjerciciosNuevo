package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.YoutubeHome;
import utils.Base;

public class AddVideoToQueueTest extends Base {
  
	//Step 1. Open the browser and navigate to youtube's home page
	//Step 2. Deny the login request pop up and accept cookie usage
	//Step 3. Hover over the first video on the list
	//Step 4. Click the "add to queue" button
		
		
		@Test
		@Parameters("browserUsed")
		public void testHistory(String browserUsed) throws Exception {
			
			//Instantiation of necessary objects
			YoutubeHome HomePage = new YoutubeHome();
			AddVideoToQueueTest helper = new AddVideoToQueueTest();
			
			//Step 1. Open the browser
					
			if(browserUsed.equalsIgnoreCase("chrome")) {
				Base.driver = Base.startUpChromeBrowser();
			} else if(browserUsed.equalsIgnoreCase("firefox")) {
				Base.driver = Base.startUpFirefoxBrowser();
			}
			
			try {
				Base.navigateURL("https://www.youtube.com/");
			} catch (Exception e) {
				throw new Exception("Unable to access the website.");
			}
			
			
			
			//End of step 1.
			
			
			//Step 2. Deny the login request pop up and accept cookie usage
			
			helper.skipLoginAndAcceptCookies(HomePage);
			
			//End of step 2.
			
			
			//Step 3. Hover over the first video on the list
			
			WebElement firstVideo = HomePage.firstVideo(driver);
			
			//We perform the hover gesture through an action
			Actions action = new Actions(driver);
			action.moveToElement(firstVideo);
			
			//Now that the add to queue button appears on the webpage, the respective
			//WebElement is instantiated (The xpath route makes sense in this state)
			
			//FIXME: Can't find element through xPath
			WebElement addToQueueButton =  HomePage.addToQueueHoverButton(driver);
			
			
			//End of Step 3.
			
			//Step 4. Click the "add to queue" button
			
			addToQueueButton.click();
			
			//End of Step 4.
			
			System.out.println("Test finished");
			
		}
		
		private void skipLoginAndAcceptCookies(YoutubeHome HomePage) {
			
			WebElement refuseLoginBtn = HomePage.refuseLoginBtn(driver);
			refuseLoginBtn.click();
			
			//The button is inside an iframe tag. In the method call, the driver
			//switches into it. After it's done, it has to switch back into the parent document
			
			WebElement agreeCookiesBtn = HomePage.agreeCookiesBtn(driver);
			agreeCookiesBtn.click();
			
			driver.switchTo().parentFrame();
		}
	
}
