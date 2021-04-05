package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentKlovReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

//We apply the singleton pattern

//In Extentreports 5, you can attach multiple reporters of different types to the same ExtentReports object

public class ExtentManager {
	
	private static ExtentReports extent;
	
	
	public synchronized static ExtentReports getReporter() {

		if(extent == null) {
			extent = new ExtentReports();
		}
		
		return extent;
		
	}
	
	//Maybe unnecessary, only saves a single line of code
	public synchronized static void attachSparkReporter(String outputDir) {
		
		ExtentSparkReporter spark = new ExtentSparkReporter(outputDir);
		
		if(extent != null) {
			extent.attachReporter(spark);
		}
		
	}
	
	public synchronized static void attachKlovReporter(String outputDir) {
		
		ExtentKlovReporter klov = new ExtentKlovReporter(outputDir);
		
		if(extent != null) {
			extent.attachReporter(klov);
		}
		
	}

}
