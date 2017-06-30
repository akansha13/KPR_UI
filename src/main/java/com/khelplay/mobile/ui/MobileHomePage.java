package com.khelplay.mobile.ui;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khelplay.common.BasePage;
import com.khelplay.objectrepository.mobile.MobileLocators;

public class MobileHomePage extends BasePage {
	public static Logger logger = LoggerFactory.getLogger(MobileHomePage.class);
	
	
	public MobileHomePage(WebDriver driver)
		{
		super(driver);
		if (isElementPresent(MobileLocators.avatarIcon,5))
			{
			logger.info("User is successfully Logged In");
			}
		else
			{
			logger.info("User is not Logged In");
			}
		}

}
