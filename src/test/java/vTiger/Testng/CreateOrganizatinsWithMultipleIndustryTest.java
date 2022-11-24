package vTiger.Testng;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import vTiger.GenericLibrary.ExcelFileLibrary;
import vTiger.GenericLibrary.JavaLibrary;
import vTiger.GenericLibrary.PropertyFileLibrary;
import vTiger.GenericLibrary.WebDriverLibrary;
import vTiger.ObjectRepository.CreateNewOrganizationPage;
import vTiger.ObjectRepository.HomePage;
import vTiger.ObjectRepository.LoginPage;
import vTiger.ObjectRepository.OrganizationsInfoPage;
import vTiger.ObjectRepository.OrganizationsPage;


public class CreateOrganizatinsWithMultipleIndustryTest {
	

	// Step 1: Create Object of all the libraries
	JavaLibrary jLib = new JavaLibrary();
	PropertyFileLibrary pLib = new PropertyFileLibrary();
	ExcelFileLibrary eLib = new ExcelFileLibrary();
	WebDriverLibrary wLib = new WebDriverLibrary();

	@Test
	public void createOrgaWithMultipleIndustryTest(String orgName) throws IOException
	{
			String Org = orgName;

				// Step 2: read all the required data
				String BROWSER = pLib.readDataFromPropertyFile("browser");
				String URL = pLib.readDataFromPropertyFile("url");
				String USERNAME = pLib.readDataFromPropertyFile("username");
				String PASSWORD = pLib.readDataFromPropertyFile("password");
				
				WebDriver driver = null;

				// Step 3: launch the browser
				if (BROWSER.equalsIgnoreCase("Chrome")) {
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
				} else if (BROWSER.equalsIgnoreCase("firefox")) {
					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver();
				} else {
					System.out.println("invalid browser name");
				}

				wLib.maximiseWindow(driver);
				wLib.waitForPageLoad(driver);
				driver.get(URL);

				// Step 4: Login to Application
				LoginPage lp = new LoginPage(driver);
				lp.loginToApp(USERNAME, PASSWORD);
				
				//Step 5: Navigate to Organizations link
				HomePage hp = new HomePage(driver);
				hp.clickOrganizationLnk();
				
				//Step 6: Navigate to create Organizations look up image
				OrganizationsPage op = new OrganizationsPage(driver);
				op.clickOnCreateOrgImg();
				
				//Step 7: Create new organization and save
				CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
				cnop.createNewOrg(Org); //failed - exception
				
				//Step 8: Validate
				OrganizationsInfoPage oip = new OrganizationsInfoPage(driver);
				String orgHeader = oip.getOrgHeader();
				if(orgHeader.contains(Org))
				{
					System.out.println(orgHeader);
					System.out.println("pass");
				}
				else
				{
					System.out.println("Fail");
				}
				
				//Step 9: Logout
				hp.signOutOfApp(driver);
				
	}

		//@DataProvider(name = "MultipleOrganization")
		//public Object[][] getData() throws EncryptedDocumentException, IOException {
		
		//Object[][] data = eLib.readMultipleData("MultiplesOrganization");
		
		//return data;

	//}

}
