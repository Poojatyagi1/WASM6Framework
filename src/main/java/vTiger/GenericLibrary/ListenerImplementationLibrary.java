package vTiger.GenericLibrary;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * This class will provide Implementation to all the abstract methods in ITestlistener
 * @author shail
 *
 */
public class ListenerImplementationLibrary implements ITestListener 
{

	ExtentReports report;
	ExtentTest test;
	
	public void onTestStart(ITestResult result) // run for every @Test start
	{
		String MethodName = result.getMethod().getMethodName();
		//Reporter.log("test execution started-> " +MethodName,true);
		
		test=report.createTest(MethodName);  // test script execution is started in report
		test.log(Status.INFO, "test execution started");
	}

	public void onTestSuccess(ITestResult result) //pass
	{
		String MethodName = result.getMethod().getMethodName();
		//System.out.println("test is successful-> " +MethodName);
		test.log(Status.PASS, MethodName+" -> pass" );
	}

	public void onTestFailure(ITestResult result) //fail
	{
		WebDriverLibrary wLib = new WebDriverLibrary();
		JavaLibrary jLib = new JavaLibrary();
		
		String MethodName = result.getMethod().getMethodName()+jLib.getSystemDateInFormat();
		//System.out.println("test is failed-> " +MethodName);	
		test.log(Status.FAIL , MethodName+" -> failed" );
		
		try 
		{
			wLib.takeScreenShot(BaseClass.sdriver, MethodName);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	 }

	public void onTestSkipped(ITestResult result) //skipped
	{
		String MethodName = result.getMethod().getMethodName();
		//System.out.println("test is skipped-> " +MethodName);
		test.log(Status.SKIP, MethodName+" -> Skipped");
		test.log(Status.SKIP, result.getThrowable());
	}
	

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}
		
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	public void onStart(ITestContext context) 
	{
		Reporter.log("Execution of suite started",true);
		
		//Execution of suite starts here, hence configure the extent reports here. Report- 11-11-2022-10-48.html
		
		ExtentSparkReporter htmlReport = new ExtentSparkReporter(".\\ExtentReports\\Report-"+new JavaLibrary().getSystemDateInFormat()+".html");
		htmlReport.config().setDocumentTitle("Execution Reports for vTiger");
		htmlReport.config().setTheme(Theme.DARK);
		htmlReport.config().setReportName("vTiger Execution Reports");
		
		// attach the report to extent reports
		ExtentReports report = new ExtentReports();
		report.attachReporter(htmlReport);
		report.setSystemInfo("Base Browser","Chrome");
		report.setSystemInfo("Base Enviornment", "Testing");
		report.setSystemInfo("Base Url", "http://localhost:8888");
		report.setSystemInfo("Reporter Name", "Pooja");
	}

	public void onFinish(ITestContext context) 
	{
		Reporter.log("Execution of suite finished",true);
		
		//Flush the report. - Here the execution is finished
		report.flush();
	}
	
	
	

}
