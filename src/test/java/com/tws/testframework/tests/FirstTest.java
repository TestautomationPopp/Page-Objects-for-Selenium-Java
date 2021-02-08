/**
 * #################### Test With A Smile ####################
 *                  Written by Andreas Popp
 *  For more informations visit https://test-with-a-smile.de
 *        or mailto andreas.popp@testautomation-popp.de
 * ###########################################################
 */

package com.tws.twsframework.tests;

import com.tws.twsframework.framework.Browser;
import com.tws.twsframework.framework.BrowserProvider;

import com.tws.twsframework.pageobjects.*;

import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;

public class FirstTest{

    // Create a public variable for the browser class to call the driver
    public Browser browser;

    /**
     * The test case opens the website https://test-with-a-smile.de and verify, that the logo is displayed
     * 
     * @param browsername   The browser defined in the data provider given from the -Dbrowsers parameter of the maven call
     * @throws Exception
     */
    @Test(dataProvider = "browser-data-provider", dataProviderClass = BrowserProvider.class)
    private void testCase(String browsername) throws Exception{
        browser = new Browser(browsername, 10);
        browser.driver.get("https://test-with-a-smile.de");
        
        // Initialize the TWS Main Page Object
        TwsMain poTwsMain = PageFactory.initElements(browser.driver, TwsMain.class);

        Assert.assertEquals(true, poTwsMain.logo.isDisplayed());

        // Check the footer by calling the function in the page object
        poTwsMain.checkFooter();

        // Execute the search by calling the function in the page object
        poTwsMain.searchFor("Create a Cross-Browser-Test Selenium Java Project with automated WebDriver Download");

        // Initialize the Search Result Page Object
        SearchResult poSearchResult = PageFactory.initElements(browser.driver, SearchResult.class);
        
        Assert.assertEquals("Search Results for: Create a Cross-Browser-Test Selenium Java Project with automated WebDriver Download", poSearchResult.searchResultText.getText());
        Assert.assertEquals("Create a Cross-Browser-Test Selenium Java Project with automated WebDriver Download", poSearchResult.firstResultHeader.getText());
        
    }
    
    /**
     * The after method will close the browser when the execution of the test method is done.
     */
    @AfterMethod
    private void closeBrowsers(){
        browser.driver.quit();
    }
}