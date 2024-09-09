package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
static ExtentReports extent;
	
	public static ExtentReports getReportObject()
	{
		
		String path =System.getProperty("user.dir")+"\\ExtentReports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		 extent =new ExtentReports();
		 
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Growitskill Tester");
		extent.setSystemInfo("Tools", "Selenium 4");
		extent.setSystemInfo("JDK", "15");
		return extent;
		
	}

}
