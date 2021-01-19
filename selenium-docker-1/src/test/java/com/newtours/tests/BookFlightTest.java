package com.newtours.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.newtours.pages.FindFlightPage;
import com.newtours.pages.FlightConfirmationPage;
import com.newtours.pages.FlightDetailsPage;
import com.newtours.pages.RegistrationConfirmationPage;
import com.newtours.pages.RegistrationPage;

public class BookFlightTest {
	private WebDriver driver;
	
	@BeforeTest
	public void setupDriver() {
		//set path
		this.driver = new ChromeDriver();
	}
	
	@Test
	public void registrationPageTest() {
		RegistrationPage registrationPage = new RegistrationPage(driver);
		registrationPage.goTo();
		registrationPage.enterUserDetails("selenium", "docker");
		registrationPage.enterUserCredentials("selenium", "docker");
	}
	
	@Test(dependsOnMethods = "registrationPageTest")
	public void registrationConfirmationPage() {
		RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
		registrationConfirmationPage.goToFlightDetailsPage();
	}
	
	@Test(dependsOnMethods = "registrationConfirmationPage")
	public void flightDetailsPage() {
		FlightDetailsPage flightDetailsPage = new FlightDetailsPage(driver);
		flightDetailsPage.selectPassengers("2");
		flightDetailsPage.goToFindFlightsPage();
		
	}
	
	@Test(dependsOnMethods = "flightDetailsPage")
	public void findFlightPage() {
		FindFlightPage findFlightPage = new FindFlightPage(driver);
		findFlightPage.submitFindFlightPage();
		findFlightPage.goToFlightConfirmationPage();
		
	}
	
	@Test(dependsOnMethods = "findFlightPage")
	public void flightConfirmationPage() {
		FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
		flightConfirmationPage.printConfirmation();
	}
}
