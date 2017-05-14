package Cucumber;

import cucumber.api.java.en.*;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import PageObjects.GooglePage;
import TestData.TestData;
import Tests.BaseTest;
import Tests.Tests;
import Utilities.DriverRoutine;
import Utilities.DriverRoutine.DriverToUSe;

import org.testng.Assert;
import cucumber.api.PendingException;

public class GoogleTestsStepsDefinition {
	//private Tests Test;
	private WebDriver _driver;
	private GooglePage TestPage;
	private String sFoundResult;
	private boolean bPassed=true;
	
	@Given("^I have chosen \"([^\"]*)\" browser for testing$")
	public void openBrowser(DriverToUSe eDriver) {	
		_driver = new DriverRoutine().Create(eDriver);
	}
	@When("^I open \"([^\"]*)\"\\.\\.\\.$")
	public void i_open(String Url) throws Throwable {	   
		TestPage = new GooglePage(_driver,Url);
	}	
	
	@And("^I enter \"([^\"]*)\" into search field and click search button$")
	public void searchForText(String toSearch) {
		TestPage.EnterSearchPattern(toSearch);
	}
	@Then("^I should receive list of search results, click onto first one and get the title of first result$")
	public void getResults(){
		sFoundResult = TestPage.GetTitleOfFirstLink();
	}
	@And("^First result should contain \"([^\"]*)\" pattern in title$")
	public void validateResultswith (String arg1){
		bPassed = sFoundResult.contains(TestPage._toSearch);
		Assert.assertTrue(bPassed);
	}
	
	@Then("^I should find \"([^\"]*)\" at page 1-(\\d+)$")
	public void i_should_find_at_page(String sDomainTosaerch, byte iPages) throws Throwable {
		sFoundResult = TestPage.SearchForDomain(sDomainTosaerch, iPages);
		bPassed = sFoundResult != "";
		Assert.assertTrue(bPassed);
	}
	
	@Then("^I close browser$")
	public void i_close_browser() throws Throwable {
		_driver.quit();
	}
}
