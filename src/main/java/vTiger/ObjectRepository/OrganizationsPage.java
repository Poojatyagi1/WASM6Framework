package vTiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	 
	//Declaration
	
	@FindBy(xpath="//img[@alt='Create Organization...']")
	private WebElement createOrgImg;
	
	//Initialization
	public OrganizationsPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	//Utilization
	
	public WebElement getCreateOrgImg() {
		return createOrgImg;
	}
	
	//Business library
	/**
	 * This method will click on create organizations look up image
	 */
	
	public void clickOnCreateOrgImg()
	{
		createOrgImg.click();
	}
	
	

}
