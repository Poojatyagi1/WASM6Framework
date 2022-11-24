package vTiger.Practice;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import vTiger.ObjectRepository.LoginPage;

public class POMPracticeForLogin {

	public static void main(String[] args) {
	WebDriverManager.firefoxdriver().setup();
	WebDriver driver=new FirefoxDriver();
	
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.get("http://localhost:8888/");
	
	LoginPage lp = new LoginPage(driver);
	// if we are using business library in pom class we don't need to write here   
	//lp.getUserNameEdt().sendKeys("admin");
	//lp.getPasswordEdt().sendKeys("admin");
	//lp.getLoginBtn().click();
	lp.loginToApp("admin" , "admin");

	}

}
