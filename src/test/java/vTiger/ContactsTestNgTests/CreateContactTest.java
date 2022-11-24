package vTiger.ContactsTestNgTests;

import java.io.IOException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import vTiger.GenericLibrary.BaseClass;
import vTiger.ObjectRepository.ContactsInfoPage;
import vTiger.ObjectRepository.ContactsPage;
import vTiger.ObjectRepository.CreateNewContactPage;
import vTiger.ObjectRepository.HomePage;
import vTiger.ObjectRepository.OrganizationsInfoPage;

//@Listeners(vTiger.GenericLibrary.ListenerImplementationLibrary.class)
public class CreateContactTest extends BaseClass{

	@Test//(groups = "smokeSuite")
	public void createContactTest() throws IOException 
	{
		//read data from excel
		String LASTNAME = eLib.readDataFromExcel("Contacts", 4, 2);
		
		//step 4:navigate to contacts link
		HomePage hp =new HomePage(driver);
		hp.clickContactsLnk();
		
		//Navigate to create Contact lookup image
		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactImg();
		
		// create contact with mandatory fields and save
		CreateNewContactPage cnp = new CreateNewContactPage(driver);
		cnp.createNewContact(LASTNAME);
		
		// Navigate to Contacts info page
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
		
		
	}
	
	@Test
	public void CreateContactDemotest()
	{
		System.out.println("demo test");
	}
	
	
	
}
	