package vTiger.OrganizationsTestNGTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import vTiger.GenericLibrary.BaseClass;
import vTiger.ObjectRepository.CreateNewOrganizationPage;
import vTiger.ObjectRepository.HomePage;
import vTiger.ObjectRepository.OrganizationsInfoPage;
import vTiger.ObjectRepository.OrganizationsPage;

public class CreateMultipleOrganizationsWithIndustryTest extends BaseClass {

	@Test(dataProvider = "MultipleOrganizations")
	public void createMultipleOrgTest(String orgName, String indType) throws IOException {
		String Org = orgName + jLib.getRandomNumber();

		// Step 5: Navigate to Organizations link
		HomePage hp = new HomePage(driver);
		hp.clickOrganizationLnk();

		// Step 6: Navigate to create Organizations look up image
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnCreateOrgImg();

		// Step 7: Create new organization and save
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createNewOrg(Org, indType); // failed - exception

		// Step 8: Validate
		OrganizationsInfoPage oip = new OrganizationsInfoPage(driver);
		String orgHeader = oip.getOrgHeader();
		
		if (orgHeader.contains(Org)) 
		{
			System.out.println(orgHeader);
			System.out.println("pass");
		} 
		else 
		{
			System.out.println("Fail");
		}

	}

	@DataProvider(name = "MultipleOrganizations")
	public Object[][] getData() throws EncryptedDocumentException, IOException {

		Object[][] data = eLib.readMultipleData("MultipleOrganization");

		return data;

	}

}
