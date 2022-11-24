package vTiger.Practice;

import org.testng.annotations.Test;

public class TestNG_Practice {
	
	@Test(invocationCount = 3, priority = -3)
	
	public void createCustomer()
	{
		// Asser.fail();
		System.out.println("customer created");  // 1-->1
	}
	
	@Test(dependsOnMethods = "createCustomer")
	public void modifyCustomer()
	{
		System.out.println("customer modified"); // 3 -->2
	}

	
	@Test(priority = 2)
	public void deleteCustomer()
	{
		System.out.println("Customer deleted"); // 2 -->3
	}
}
