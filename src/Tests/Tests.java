package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import PageObjects.GooglePage;
import TestData.TestData;

public class Tests extends BaseTest {	
	
	@Test(enabled=true)
	public static void test1() throws Exception{       
	   GooglePage searchPage = new GooglePage(driver, TestData.URL);
       String sActualTitleOfFirstLink = searchPage.EnterSearchPattern(TestData.TO_SEARCH).GetTitleOfFirstLink();       
       Assert.assertTrue(sActualTitleOfFirstLink.contains(TestData.TO_SEARCH));       
	}	
	@Test(enabled = true)
	public static void test2() throws Exception{
	    GooglePage searchPage = new GooglePage(driver,TestData.URL);
	    searchPage.EnterSearchPattern(TestData.TO_SEARCH);
	    Assert.assertTrue(!searchPage.SearchForDomain(TestData.DOMAIN_TO_SEARCH, TestData.MAX_PAGES).isEmpty());	    
	}
}
