package vTiger.GenericLibrary;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import vTiger.ObjectRepository.HomePage;
import vTiger.ObjectRepository.LoginPage;

/**
 * This is a generic class which consist of all the basic configuration annotations
 * @author shail
 *
 */
public class BaseClass {
	
	public PropertyFileLibrary pLib = new PropertyFileLibrary();
	public ExcelFileLibrary eLib = new ExcelFileLibrary();
	public JavaLibrary jLib = new JavaLibrary();
	public WebDriverLibrary wLib = new WebDriverLibrary();
	public WebDriver driver = null;
	public static WebDriver sdriver = null; //it is used for implementing Takescreenshot in ListenerImplementationLibrary when test is failed
	@BeforeSuite(groups= {"smokeSuite" , "regressionSuite"})
	public void bsConfig()
	{
		System.out.println("database connected successfully");
	}
	
	//@Parameters("browser")
	//@BeforeTest
	@BeforeClass(groups ={"smokeSuite" , "regressionSuite"})
	public void bcConfig(/*String BROWSER**/) throws IOException
	{
		String BROWSER = pLib.readDataFromPropertyFile("browser");
		String URL = pLib.readDataFromPropertyFile("url");
		
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println("--Browser launched successfully -> "+BROWSER+" --");
		}
		
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println("--Browser launched successfully -> "+BROWSER+" --");
		}
		
		else
		{
			System.out.println("Invalid browser name");
		}
		
		sdriver = driver; //we have to initialize  sdriver 
		wLib.maximiseWindow(driver);
		wLib.waitForPageLoad(driver);
		driver.get(URL);
	 }
	
	 @BeforeMethod(groups =  {"smokeSuite" , "regressionSuite"})
	 public void bmConfig() throws IOException
	 {
		String USERNAME = pLib.readDataFromPropertyFile("username");
		String PASSWORD = pLib.readDataFromPropertyFile("password");
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		System.out.println(" <-- login is successfully --> ");
	 }
	 
	 @AfterMethod(groups =  {"smokeSuite" , "regressionSuite"})
	 public void amConfig()
	 {
		 HomePage hp = new HomePage(driver);
		 hp.signOutOfApp(driver);
		 System.out.println(" <-- Logout Successfully --> ");
	 }
	 
	 @AfterClass(groups =  {"smokeSuite" , "regressionSuite"})
	 public void acConfig()
	 {
		 driver.quit();
		 System.out.println(" <-- Browser closed successfully --> ");
	 }
	 
	 @AfterSuite(groups = {"smokeSuite" , "regressionSuite"})
	 public void asConfig()
	 {
		 System.out.println(" <-- database closed successfully --> ");
	 }
}	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
		
		 
	 

	
	
	
	
	
	
	








