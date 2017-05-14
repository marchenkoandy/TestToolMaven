Feature: Google search must be executed properly for two tests.
Scenario Outline: Test #1.
			Open Google.
			Search for “automation”.
			Open the first link on search results page.
			Verify that title contains searched word
				
				
Given I have chosen "<Browser>" browser for testing 
When I open <URL>...
* I enter <Pattern> into search field and click search button
Then I should receive list of search results, click onto first one and get the title of first result
* First result should contain <Pattern> pattern in title
* I close browser
 
Examples: 
	|Browser	|URL														|	Pattern				|
	|Firefox	|"https://www.google.com.ua/"		|	"Automation"	|
	|Chrome		|"https://www.google.com.ua/"		|	"Automation"	|
	
Scenario Outline: Test #2.
			Open Google.
			Search for “automation”.
			Verify that there is expected domain (“testautomationday.com”) on search results pages (page: 1-5).
				
				
Given I have chosen "<Browser>" browser for testing 
When I open <URL>...
* I enter <Pattern> into search field and click search button
Then I should find <Expected domain> at page 1-<Max pages for search>
* I close browser

Examples: 
	|Browser	|URL														|	Pattern					|	Max pages for search	|	Expected domain										|
	|Firefox	|"https://www.google.com.ua/"		|	"Automation"		|5											|"en.wikipedia.org/wiki/Automation"	|
	|Chrome		|"https://www.google.com.ua/"		|	"Automation"		|5											|"en.wikipedia.org/wiki/Automation"	|
	