package com.miguels.chrome;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class searchTest {
	
	private WebDriver driver;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","/usr/bin/chromedriver");
		ChromeOptions options = new ChromeOptions().setHeadless(true);
//		ChromeOptions options = new ChromeOptions().addArguments("--allowed-ips");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
		
	}
	@After
	public void tearDown()
	{
		driver.close();
	}
	
	@Test
	public void GoogleSearchTest() {
		WebElement searchbox = driver.findElement(By.name("q"));
		searchbox.clear();
		searchbox.sendKeys("wikipedia");
		searchbox.submit();
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("rc"),1));
		List<WebElement> results = driver.findElements(By.className("rc"));
		WebElement webpage = results.get(0).findElement(By.xpath("./div/a"));
		webpage.click();
		wait.until(ExpectedConditions.urlToBe("https://es.wikipedia.org/wiki/Wikipedia:Portada"));
		System.out.println(driver.getTitle());
	}

}
