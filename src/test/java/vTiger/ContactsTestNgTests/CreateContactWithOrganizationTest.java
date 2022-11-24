package vTiger.ContactsTestNgTests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import vTiger.GenericLibrary.BaseClass;
import vTiger.ObjectRepository.ContactsInfoPage;
import vTiger.ObjectRepository.ContactsPage;
import vTiger.ObjectRepository.CreateNewContactPage;
import vTiger.ObjectRepository.CreateNewOrganizationPage;
import vTiger.ObjectRepository.HomePage;
import vTiger.ObjectRepository.OrganizationsInfoPage;
import vTiger.ObjectRepository.OrganizationsPage;

public class CreateContactWithOrganizationTest extends BaseClass {
	
	@Test(groups = { "smokeSuite" , "regressionSuite"})
	public void createContactWithOrgTest() throws IOException
	{

		// Step 2: read all the required data
		String LASTNAME = eLib.readDataFromExcel("Contacts", 4, 2);
		String ORGNAME = eLib.readDataFromExcel("Contacts", 4, 3)+jLib.getRandomNumber();
		
		//Step 5: Navigate to Organizations link
		HomePage hp = new HomePage(driver);
		hp.clickOrganizationLnk();
		
		//Step 6: Navigate to create Organizations look up image
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnCreateOrgImg();
		
		//Step 7: Create new organization and save
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createNewOrg(ORGNAME);
		
		//Step 8: Validate for Organization
		OrganizationsInfoPage oip = new OrganizationsInfoPage(driver);
		String orgHeader = oip.getOrgHeader();
		Assert.assertTrue(orgHeader.contains(ORGNAME), "Organization created");
		
		
		/*if(orgHeader.contains(ORGNAME))
		{
			System.out.println(orgHeader);
			System.out.println("Organization created successfully");
		}
		else
		{
			System.out.println("Organization not created");
		}**/
		
		//Step 9: Navigate to contacts link
		hp.clickContactsLnk();

		// Step 10: Navigate to create Contact Look up image
		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactImg();

		// Step 11: create contact with mandatory details
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.createNewContact(LASTNAME, ORGNAME, driver);
		
        //Step 12: Validate for Contact
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String contactHeader = cip.getContactHeader(); //bug
		Assert.assertTrue(contactHeader.contains(LASTNAME));
		/*if(contactHeader.contains(LASTNAME))
		{
			System.out.println(contactHeader);
			System.out.println("Pass");
		}
		else
		{
			System.out.println("FAIL");
		}	**/
		
	}
	
		@Test
		public void createContactDemoTest()
		{
		System.out.println("demo test");
		}
}
