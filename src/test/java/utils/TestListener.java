package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class TestListener extends Base implements ITestListener {

	private static ExtentManager manager;
	private static ExtentTest test;

		public void onTestStart(ITestResult result) {
			
			//PROBLEM: Can't reference the test object instantiated here in test class, so I can't log into this test
			//CAN'T USE LISTENERS
			
			ExtentReports extent = manager.getReporter();
			test = extent.createTest(result.getName() + "test");
			
		}

		public void onTestSuccess(ITestResult result) {
			
			ExtentReports extent = manager.getReporter();
			
			test.addScreenCaptureFromPath("extentSuccess.png");
			test.log(Status.PASS, result.getTestName());
			test.pass(MediaEntityBuilder.createScreenCaptureFromPath("extent.png", "Test passed").build());
			
			
		}

		public void onTestFailure(ITestResult result) {
			ExtentReports extent = manager.getReporter();
			test.addScreenCaptureFromPath("extentFailure.png");
			test.fail(MediaEntityBuilder.createScreenCaptureFromPath("extent.png", "Test failed").build());
			
		}

		public void onTestSkipped(ITestResult result) {
			ExtentReports extent = manager.getReporter();
			test.addScreenCaptureFromPath("extentSkip.png");
			test.skip(MediaEntityBuilder.createScreenCaptureFromPath("extent.png", "Test skipped").build());
			
		}

		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			
			
		}

		public void onStart(ITestContext context) {
			context.setAttribute("WebDriver", this.driver);
			
		}

		public void onFinish(ITestContext context) {
			ExtentReports extent = ExtentManager.getReporter();
			extent.flush();
			
		}
	
}
