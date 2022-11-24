package vTiger.Practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithDDT {

	public static void main(String[] args) throws IOException {
	FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
		
		//create object of properties
		Properties pobj = new Properties();
		pobj.load(fis);
		String BROWSER = pobj.getProperty("browser");
		String URL = pobj.getProperty("url");
		String USERNAME = pobj.getProperty("username");
		String PASSWORD = pobj.getProperty("password");
		
		WebDriver driver = null;
		
		//step 2: Launch the browser - RUN TIME POLYMORPHISM CONCEPT USED HERE
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			 driver = new ChromeDriver();
			System.out.println("==="+BROWSER+" is launched =====");
		}
		
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup(); // when we don't want to download executables in project we used this setup by adding dependency in Pom,xml file it will execute.
			driver = new FirefoxDriver();
			System.out.println("==="+BROWSER+" is launched =====");	
		}
		
		else 
		{
			System.out.println("Invalid browse namer");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get(URL);
		
		//Step 3: Login to the application
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		//step 4: navigate to contact
		driver.findElement(By.linkText("Contacts")).click();
		
		//step 5: click on create organization
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		//step 6: Create contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys("Tyagi");
		
		//step 7 : Save
		driver.findElement(By.name("button")).click();
		
		//step 8: logout
		WebElement logout = driver.findElement(By.xpath("(//td[@class='small'])[2]")); //hover for logout
		Actions a=new Actions(driver);
		a.moveToElement(logout).perform();
				
		driver.findElement(By.linkText("Sign Out")).click();
	}

}
