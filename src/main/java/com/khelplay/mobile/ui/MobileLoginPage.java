package com.khelplay.mobile.ui;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khelplay.common.BasePage;
import com.khelplay.objectrepository.mobile.MobileLocators;

public class MobileLoginPage extends BasePage {
	public static Object obj;
	public static Logger logger = LoggerFactory.getLogger(MobileLoginPage.class);
			

	public MobileLoginPage(WebDriver driver) throws InterruptedException {
		super(driver);
		
		Thread.sleep(10000);
		initiate();
		if (isElementPresent(MobileLocators.loginPage,5))
		{
			logger.info("Login Page is Present");
		}
		else
		{
			logger.info("Login Page is not Present");
		}		
	}
	
	public void initiate(){
	buttonClick(MobileLocators.nmUpdateLater, 10);
	}
	
	public void username(String username)
	{
		
		sendKeys(MobileLocators.usernameLogin, username, 10);
	}
	public void password(String password)
	{
		sendKeys(MobileLocators.passwordLogin, password, 10);
	}
	
	public MobileHomePage clickLogin()
	{
		try
		{
			if (isElementPresent(MobileLocators.loginButton,5))
			{
				buttonClick(MobileLocators.loginButton,10);
				if (isElementPresent(MobileLocators.slowNetworkPopup,5))
				{
				buttonClick(MobileLocators.slowNetworkPopup,10);
				logger.info("Slow Network PopUp Dismissed");
					if (isElementPresent(MobileLocators.skipTour,5))
					{
					buttonClick(MobileLocators.skipTour,10);
					logger.info("App Tour Skipped");
					}
				
				}
			return new MobileHomePage(driver);	
			}
		}	
		catch (Exception e)
		{
		
			return null;
		}
		return null;
	}
	
}



