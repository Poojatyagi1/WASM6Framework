package vTiger.Practice;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class BaseClassPractice {

	@BeforeClass
	public void bcConfig()
	{
		System.out.println("before class");
	}
	
	@BeforeMethod
	public void bmConfig()
	{
		System.out.println("Before Method");
	}
	
	@BeforeSuite
	public void bsConfig()
	{
		System.out.println("Before suite");
	}
	
	@AfterClass
	public void afConfig()
	{
		System.out.println("After class");
	}
	
	@AfterSuite
	public void asConfig()
	{
		System.out.println("After Suite");
	}
	
	@AfterMethod
	public void amConfig()
	{
		System.out.println("After Method");
	}
	
	@Test
	public void tConfig()
	{
		System.out.println(" Test");
	}
}
