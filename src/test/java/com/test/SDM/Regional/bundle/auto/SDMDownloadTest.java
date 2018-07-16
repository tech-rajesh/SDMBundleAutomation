package com.test.SDM.Regional.bundle.auto;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import junit.framework.Assert;

public class SDMDownloadTest {

	public static void main(String[] args) throws Exception {
		
		
		
/*		System.setProperty("webdriver.driver.chrome", "\\browserDrivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();*/
		//System.setProperty("webdriver.driver.chrome", "\\browserDrivers\\chromedriver.exe");
		//phantomjs		---- working onto windows
		//File file = new File("D:\\Workspace\\Selenium\\support\\drivers\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");				
        
		//phantomjs		---- validating onto linux box
		File file = new File("src\\main\\resources\\browserDrivers\\phantomjs");
		
		System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
		
		WebDriver driver = new PhantomJSDriver();
		
		
		driver.get("https://google.co.in");
		Assert.assertEquals("results", "true", "true");
		System.out.println("Page title is: " + driver.getTitle());
		/*
		//working code - using chrome browser
		driver.get("https://product-definition.pitneybowes.com");
		Thread.sleep(30000);
		
		//Entering login credentials  
				
		driver.findElement(By.xpath("//li[5]/a/span")).click();
		Thread.sleep(7000);
	    driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Geocoding");
	    Thread.sleep(5000);
	    //clicking onto next page - page rendering
	    
	    //for(int p=1;p<36;p++)
	    for(int p=1;p<3;p++)		//demo purpose 2 pages
	    {
	    	//driver.findElement(By.xpath("//button[@type='button'])[4]")).click();
	    	Thread.sleep(5000);
	    	driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
	    	Thread.sleep(8000);
	    
	    //for(int i=2;i<12;i++)
	    for(int i=5;i<8;i++)			//demo purpose
	    {
		    Thread.sleep(7000);
		    //select the dataset
		    driver.findElement(By.xpath("//div["+i+"]/div/div/div")).click();
		    Thread.sleep(7000);
		    //download started 
		    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
		    Thread.sleep(7000);
		    //back button
		    driver.findElement(By.xpath("//button")).click();
		    Thread.sleep(7000);
	    
	    driver.findElement(By.xpath("//div["+i+"]/div/div/div")).click();
	    Thread.sleep(7000);
	    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
	    
	    }
	    
	    	    
	    //page - for loop close
	    
	    }*/
		

	}

}
