package PageObjects;


import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestData.TestData;
import Tests.Tests;

public class GooglePage {
	/*Defining elements*/
	By SearchTextField 			= By.id		("lst-ib");
	By SearchButton 			= By.xpath	("//button[@id='_fZl']/span");
	By SearchResultHeader 		= By.xpath	("//div/h3/a");
	String xPathCurrentPage 	= "//td[@class='cur']";
	By SearchResultUrl 			= By.xpath	(".//cite[@class='_Rm']");
	By CurrentPage				= By.xpath	(xPathCurrentPage);
	String xPAthToNextResulPage	= xPathCurrentPage + "/following-sibling::td[1]/a";
	By NextPage					= By.xpath(xPAthToNextResulPage);
	int ExplicitlyWait			= TestData.WAIT_EXPLICIT;
	 
	private static final Logger Logg = Logger.getLogger(Tests.class);
	private final WebDriver _driver;
	private String _Url;
	public String _toSearch;
	
	public GooglePage(WebDriver driver, String sUrl){	
		_driver=driver;
		_Url = sUrl;
		_driver.get(_Url);
		PageFactory.initElements(_driver, this);
	}	
	private WebElement weSearchTextField (){
		return _driver.findElement(SearchTextField);
	}
	private WebElement weSearchButton (){
		return _driver.findElement(SearchButton);
	}
	private WebElement weCurrentPage(){
		return _driver.findElement(CurrentPage);
	}
	private WebElement weNextPage(){
		return _driver.findElement(NextPage);
	}
	private List<WebElement> lweSearchResultHeader (){
		return _driver.findElements(SearchResultHeader);
	}
	
	public void waitElementToBeClickable(By by,int iWait){
		new WebDriverWait(_driver, iWait).until(ExpectedConditions.elementToBeClickable(by));
	}
	public void waitTextToBePresentInElement(By by, String sExpText,int iWait){
		WebDriverWait wait = new WebDriverWait(_driver, iWait);
        wait.until(ExpectedConditions.textToBePresentInElement(by, sExpText));
	}
	
	public GooglePage EnterSearchPattern(String toSearch){		
		_toSearch = toSearch;
		Logg.info("The \"" + _toSearch + "\" was set to search field.");
		weSearchTextField().sendKeys(_toSearch);
		weSearchButton().click();
		return this;
	}
	public String GetTitleOfFirstLink(){
		waitElementToBeClickable(SearchResultHeader,ExplicitlyWait);
		List <WebElement> firstResult = lweSearchResultHeader();
		firstResult.get(0).click();
		String sTitle = _driver.getTitle();
		Logg.info("The title of first page is: \"" + sTitle + "\"");
        return sTitle;
	}
	public String SearchForDomain(String sDomainTosaerch, byte iPages)
    {
        String sOut = null;
        {
        	waitElementToBeClickable(CurrentPage,ExplicitlyWait);
        	int iPage = Integer.parseInt(weCurrentPage().getText());
            do
            {
                List<WebElement> lFoundResults = _driver.findElements(SearchResultUrl);                    
                for (WebElement res : lFoundResults)
                {
                    String sCurrent = res.getText();
                    if (sCurrent.contains(sDomainTosaerch))
                    {
                        sOut = sCurrent;
                        Logg.info("Domain was found: (page " + iPage + " of " + iPages + "): \"" + sCurrent + "\"");
                        break;
                    }
                }
                if ((iPage < iPages) && sOut=="")
                {
                	weNextPage().click();                                      
                	waitTextToBePresentInElement(CurrentPage, String.valueOf(iPage + 1),ExplicitlyWait);
                }
            } while ((++iPage <= iPages) && sOut == "");
            
        }
        if (sOut.isEmpty()){
            Logg.error("Domain \""+sDomainTosaerch+"\" was not found.");
        }
        return sOut;
    }
}
