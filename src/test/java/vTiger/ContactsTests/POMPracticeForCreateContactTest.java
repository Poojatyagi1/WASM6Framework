package vTiger.ContactsTests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import vTiger.GenericLibrary.ExcelFileLibrary;
import vTiger.GenericLibrary.JavaLibrary;
import vTiger.GenericLibrary.PropertyFileLibrary;
import vTiger.GenericLibrary.WebDriverLibrary;
import vTiger.ObjectRepository.ContactsInfoPage;
import vTiger.ObjectRepository.ContactsPage;
import vTiger.ObjectRepository.CreateNewContactPage;
import vTiger.ObjectRepository.HomePage;
import vTiger.ObjectRepository.LoginPage;

public class POMPracticeForCreateContactTest {

	public static void main(String[] args) throws IOException {
	
		// step 1:create object for all the libraries
		JavaLibrary jlib= new JavaLibrary();
		PropertyFileLibrary pLib = new PropertyFileLibrary();
		ExcelFileLibrary eLib = new ExcelFileLibrary();
		WebDriverLibrary wLib = new WebDriverLibrary();
		
		//Step 2: Read all the required data 
		String BROWSER = pLib.readDataFromPropertyFile("browser");
		String URL = pLib.readDataFromPropertyFile("url");
		String USERNAME = pLib.readDataFromPropertyFile("username");
		String PASSWORD = pLib.readDataFromPropertyFile("password");
		String LASTNAME = eLib.readDataFromExcel("Contacts", 1, 2);
		
		WebDriver driver = null;
		
		// Step 3: Launch the browser
		
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();  
		}
		else if(BROWSER .equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else
		{
			System.out.println("Invalid Browser Name");
		}
		
		wLib.maximiseWindow(driver);
		wLib.waitForPageLoad(driver);
		driver.get(URL);
		
		//step 4 login to the application
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		
		//step 5:navigate to contacts link
		HomePage hp =new HomePage(driver);
		hp.clickContactsLnk();
				
		//step 6 Navigate to create Contact lookup image
		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactImg();
				
		// step 7 create contact with mandatory fields and save
		CreateNewContactPage cnp = new CreateNewContactPage(driver);
		cnp.createNewContact(LASTNAME);
				
		// step 8 Navigate to Contacts info page
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String contactHeader = cip.getContactHeader();
		
		if (contactHeader.contains(LASTNAME))
			{
				System.out.println("PASS");
			}
				
		else
			{
				System.out.println("Fail");
			}
				
		// step 9: logout of the application
		hp.signOutOfApp(driver);
	}

}
