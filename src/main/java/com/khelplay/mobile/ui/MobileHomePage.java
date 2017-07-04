package com.khelplay.mobile.ui;


import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khelplay.common.BasePage;
import com.khelplay.objectrepository.mobile.WeaverLocators;
import com.khelplay.utils.CommonFunctionLibrary;


public class MobileHomePage extends BasePage {
	public static Object obj;
	private static Logger logger = LoggerFactory.getLogger(MobileHomePage.class);

	public MobileHomePage(WebDriver driver) {
		super(driver);
		functionLibrary = new CommonFunctionLibrary(driver);
		if (isElementPresent(WeaverLocators.avatarIcon, 5)) {
			logger.info("User has logged in");
		} else {
			logger.warn("User is not logged in");
			throw new ElementNotVisibleException("User is not logged in");
		}

	}

	public InstantPlayPage selectInstantPlay() {
		if (buttonClick(WeaverLocators.instantPlay, 10)) {
			buttonClick(WeaverLocators.clickDeals, 10);
			return new InstantPlayPage(driver);
		} else {
			return null;
		}

	}


}
