package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Base;

public class YoutubeHome extends Base {

	//NOTE: WebElements are not always guaranteed to represent an existing element
	//on a webpage
	
	//An alternative to using until() method: telling the main thread to sleep for enough time.
	
	public static WebElement lastElement;
	
	public YoutubeHome() {
		
		//PageFactory deals with the Web elements' instantiation
		PageFactory.initElements(Base.driver, this);
	}
	
	public WebElement refuseLoginBtn(WebDriver driver) {
		
		WebElement refuseLoginBtn = new WebDriverWait(Base.driver, 10)
				//We check whether the element exists or not and its visibility
				.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//body/ytd-app[1]/ytd-popup-container[1]/tp-yt-paper-dialog[1]/yt-upsell-dialog-renderer[1]/div[1]/div[3]/div[1]/yt-button-renderer[1]/a[1]/paper-button[1]")));

		lastElement = refuseLoginBtn;
		return lastElement;
	}
	
	
	public WebElement agreeCookiesBtn(WebDriver driver) {
		
		//OLD COOKIES POP UP: The cookies usage agreement pop up is an iframe. First we have to switch into it.
		
		//driver.switchTo().frame("iframe");
		
		WebElement agreeCookiesBtn = new WebDriverWait(Base.driver, 10)
				//We check whether the element exists or not and its visibility
				.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//body/div[@id='yDmH0d']/c-wiz[1]/div[1]/div[1]/div[2]/div[1]/div[4]/form[1]/div[1]/div[1]/button[1]")));
		
		lastElement = agreeCookiesBtn;
		
		//We switch back into the parent HTML document
		
		
		return lastElement;
	}
	
	
	public WebElement searchBox(WebDriver driver) {
		lastElement = driver.findElement(By.xpath("//input[@id='search']"));
		return lastElement;
	}
	
	public WebElement historyButton(WebDriver driver) {
		lastElement = driver.findElement(By.xpath("//body/ytd-app[1]/div[1]/tp-yt-app-drawer[1]/div[2]/div[1]/div[2]/div[2]/ytd-guide-renderer[1]/div[1]/ytd-guide-section-renderer[2]/div[1]/ytd-guide-entry-renderer[2]/a[1]"));
		return lastElement;
	}
	
	public WebElement searchButton(WebDriver driver) {
		lastElement = driver.findElement(By.xpath("//button[@id='search-icon-legacy']"));
		return lastElement;
	}
	
	public WebElement loginButtonTopNavBar(WebDriver driver) {
		lastElement = driver.findElement(By.xpath("//ytd-masthead/div[@id='container']/div[@id='end']/div[@id='buttons']/ytd-button-renderer[1]/a[1]/paper-button[1]"));
		return lastElement;
	}
	
	/*
	 * Returns the WebElement that represents the first video on the list
	 * Youtube's video list is loaded asynchronously. Timeout is set after 10 seconds.
	 */
	public WebElement firstVideo(WebDriver driver) {
		
		WebElement firstVideo = new WebDriverWait(Base.driver, 10)
				//We check whether the element exists or not and its visibility
				.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//body/ytd-app[1]/div[1]/ytd-page-manager[1]/ytd-browse[1]/ytd-two-column-browse-results-renderer[1]/div[1]/ytd-rich-grid-renderer[1]/div[6]/ytd-rich-item-renderer[1]/div[1]")));
		
		lastElement = firstVideo;
		return firstVideo;
	}
	
	//This button is only visible whenever the mouse hovers over the video in which
	//it's located
	
	public WebElement addToQueueHoverButton(WebDriver driver) {
		
		lastElement = driver.findElement(By.xpath("//body/ytd-app[1]/div[1]/ytd-page-manager[1]/ytd-browse[1]/ytd-two-column-browse-results-renderer[1]/div[1]/ytd-rich-grid-renderer[1]/div[6]/ytd-rich-item-renderer[1]/div[1]/ytd-rich-grid-media[1]/div[1]/ytd-thumbnail[1]/a[1]/div[3]/ytd-thumbnail-overlay-toggle-button-renderer[1]"));
		return lastElement;
		
	}
	
}
