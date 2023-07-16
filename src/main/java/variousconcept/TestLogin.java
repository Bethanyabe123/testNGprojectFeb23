package variousconcept;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestLogin {
	String browser ="chrome";
	String url;
	WebDriver driver;
	@BeforeSuite 
	public void reading() {
		//filereader,bufferereader,inputstream,scanner
		 try {
			 
		InputStream input = new FileInputStream("src\\main\\java\\confg\\config.properties"); 
		
			 Properties prop = new Properties();
			 prop.load(input);
			 browser = prop.getProperty("browser");
					 
			 System.out.println("browser used"+ browser);
			  url = prop.getProperty("url");
			 }catch(IOException e){
		}	
	}
	
	//element list
	By userNameField = By.xpath("//input[@id=\"username\"]");
	By passwordField = By.xpath("//input[@id=\"password\"]");
	By signInButtonElement = By.xpath("/html/body/div/div/div/form/div[3]/button");
	By DashboardHeaderField = By.xpath(("//*[@id=\"page-wrapper\"]/div[2]/div/h2"));
	By customerMenuField = By.xpath(("//*[@id=\"side-menu\"]/li[3]/a/span[1]"));
	By addcustomerMenuField = By.xpath(("//*[@id=\"side-menu\"]/li[3]/ul/li[1]/a"));
	By addcustomerHeaderField = By.xpath(("//*[@id=\"page-wrapper\"]/div[3]/div[1]/div/div/div/div[1]/h5"));
	By fullnameField = By.xpath(("//*[@id=\"account\"]"));
	By companyDropdownField = By.xpath(("//select[@id='cid']"));
	By emailField = By.xpath(("//*[@id=\"email\"]"));
	By phoneField = By.xpath(("//*[@id=\"phone\"]"));
	By countryField = By.xpath("//*[@id=\"select2-country-container\"]");
	//testdata or mockdata
	String User_Name = "demo@techfios.com";
	String Password = "abc123";
	String Dashboard_Header_Text = "Dashboard";
	String AddCustomer_Header_Text = "Add Contact";
	String Full_Name = "Selenium Feb2023";
	String company = "Techfios";
	String Email = "demoFeb23@techfios.com";
	String Phone = "23445689";
    String country = "Aland Islands";
	
			
	
	 @BeforeMethod
	
	 public void init() {
		 if(browser == "chrome") {
			 System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
			driver = new ChromeDriver();
			 
		 }else {
			 System.setProperty("webdriver.edge.driver", "driver\\msedgedriver2.exe");
			 driver = new EdgeDriver(); 
		 }
		//System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		//driver = new ChromeDriver();
		//System.setProperty("webdriver.edge.driver", "driver\\msedgedriver2.exe");
		// driver = new EdgeDriver();
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	//@Test
	public void Login() {
	driver.findElement(userNameField).sendKeys(User_Name);	
	driver.findElement(passwordField).sendKeys(Password);
	driver.findElement(signInButtonElement).click();
	Assert.assertEquals(driver.findElement(DashboardHeaderField).getText(), Dashboard_Header_Text, "Dashboard page not found");
	
	}
	@Test
	public void addCustomer() throws InterruptedException {
		Login();
		Thread.sleep(2000);
		driver.findElement(customerMenuField).click();
		driver.findElement(addcustomerMenuField).click();
		//Assert.assertEquals(driver.findElement(addcustomerHeaderField).getText(),"AddCustomer_Header_Text","Add customer page is not available");
		//Random rnd = new Random();
		//int generatedNum = rnd.nextInt(999);
		
		randomNumGenerator(999);
		
		driver.findElement(fullnameField).sendKeys(Full_Name + randomNumGenerator(999));
		
		Select sel =new Select(driver.findElement(companyDropdownField));
		sel.selectByVisibleText(company);
		driver.findElement(emailField).sendKeys(randomNumGenerator(9999) + Email);
		driver.findElement(phoneField).sendKeys(Phone + randomNumGenerator(99));
		
	    Select Sel = new Select(driver.findElement(countryField));
		Sel.selectByVisibleText(country);
		 }
	
	private int randomNumGenerator(int bound) {
		Random rnd = new Random();
		int generatedNum = rnd.nextInt(bound);
		return generatedNum;
		
	}

	

	//@AfterMethod
	public void  tearDown() {
		driver.close();
		driver.quit();
		
	}
	

}
