package Selenium;
import static org.junit.Assert.*;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class basicTitleTest {
	
	//URL to visit
	public String QWE="http://www.google.com";
	
	//Title to check
	public String QWE1="Google";
	public String nodeURL = "http://selenium-hub:4444/wd/hub";

	public static void main(String[] args){
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		testng.setTestClasses(new Class[] { basicTitleTest.class });
		testng.addListener(tla);
		testng.run();
	}
	
	@Test
	public void testSelenium() throws IOException
	{
		//FirefoxProfile firefoxProfile = new FirefoxProfile();
		//firefoxProfile.setPreference("xpinstall.signatures.required",false);				
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		//capability.setCapability(FirefoxDriver.PROFILE,firefoxProfile);
		capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
		capability.setBrowserName("firefox");
        WebDriver driver = new RemoteWebDriver(new URL(nodeURL), capability);
        System.out.println("navigating to: " + QWE);
        driver.get(QWE);
        System.out.println("succesfully navigated to: " + QWE);
        
        String actualTitle = driver.getTitle();
        String expectedTitle = QWE1;
	    assertTrue(actualTitle.contains(expectedTitle));
    
	
//    	driver = new Augmenter().augment( driver );
	

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        System.out.println("Screenshot Successful");
        FileUtils.copyFile(screenshot, new File("Screenshot.png"));
        System.out.println("Screenshot Saved");
        
        driver.quit();

	}
	
}