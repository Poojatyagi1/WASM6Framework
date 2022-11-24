package vTiger.OrganizationsTestNGTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import vTiger.GenericLibrary.BaseClass;
import vTiger.ObjectRepository.CreateNewOrganizationPage;
import vTiger.ObjectRepository.HomePage;
import vTiger.ObjectRepository.OrganizationsInfoPage;
import vTiger.ObjectRepository.OrganizationsPage;

public class CreateOrganiztionsTest extends BaseClass{

	@Test
	public void createOrg() throws EncryptedDocumentException, IOException 
	{
		//read data from excel
		
		String ORGNAME = 	eLib.readDataFromExcel("Organization", 1, 2) + jLib.getRandomNumber();
		
		//step 4:navigate organization link
		HomePage hp =new HomePage(driver);
		hp.clickOrganizationLnk();
		
		//enter mandatory fields and save
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnCreateOrgImg();
		
		CreateNewOrganizationPage cnp = new CreateNewOrganizationPage(driver);
		cnp.createNewOrg(ORGNAME);
		
		//Step 8: Validate for Organization
		OrganizationsInfoPage oip = new OrganizationsInfoPage(driver);
		String orgHeader = oip.getOrgHeader();
		
		if(orgHeader.contains(ORGNAME))
		{
			System.out.println(orgHeader);
			System.out.println("Organization created successfully");
		}
		else
		{
			System.out.println("Organization not created");
		}
		
	}
	
}