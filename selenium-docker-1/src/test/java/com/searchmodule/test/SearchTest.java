package com.searchmodule.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.searchmodule.pages.SearchPage;

public class SearchTest {
	private WebDriver driver;
	
	@BeforeTest
	public void setupDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Jebaraj Isaac J\\git\\SeleniumDocker\\selenium-docker-1\\src\\test\\resources\\chromedriver.exe");
		this.driver = new ChromeDriver();
	} 
	
	@Test
	@Parameters({"keyword"})
	public void search(String keyword) {
		SearchPage searchPage = new SearchPage(driver);
		searchPage.goTo();
		searchPage.doSearch(keyword);
		searchPage.goToVideos();
		int size = searchPage.getResult();
		
		Assert.assertTrue(size>0);
	}
	
	@AfterTest
	public void quitDriver() {
		this.driver.quit();
	}
}
