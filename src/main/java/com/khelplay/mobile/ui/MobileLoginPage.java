package com.khelplay.mobile.ui;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khelplay.common.BasePage;
import com.khelplay.objectrepository.mobile.WeaverLocators;


public class MobileLoginPage extends BasePage {
	private static Logger logger = LoggerFactory.getLogger(MobileLoginPage.class);
	public static MobileLoginPage obj;

	public MobileLoginPage(WebDriver driver) throws InterruptedException {
		super(driver);
		System.out.println(driver);
		Thread.sleep(10000);
		initiate();
		if (isElementPresent(WeaverLocators.usernameAndroid, 5)) {
			logger.info("Login popup is present");
		} else {
			logger.warn("Login popup is not present");
			throw new ElementNotVisibleException("Login popup is not visible");
		}

	}

	public void initiate() {
		if (findElement(WeaverLocators.updatePopUp, 5).getText().contains("Update Available")) {
			buttonClick(WeaverLocators.laterButton, 5);
		} else {
			logger.info("update popup is not present");
		}
	}

	public void username(String username) {
		sendKeys(WeaverLocators.usernameAndroid, username, 10);
	}

	public void password(String password) {
		sendKeys(WeaverLocators.passwordAndroid, password, 10);

	}

	public MobileHomePage clickLogin() {
		try {
			if (isElementPresent(WeaverLocators.loginAndroid, 5)) {
				buttonClick(WeaverLocators.loginAndroid, 10);
				if (isElementPresent(WeaverLocators.updatePopUp, 5)) {
					buttonClick(WeaverLocators.okButton, 10);
					logger.info("Notification dismissed");
				} else {
					logger.info("Notification pop up not present");
				}
				buttonClick(WeaverLocators.skipTour, 10);
				return new MobileHomePage(driver);
			}

		} catch (Exception e) {
			
		}
		return null;
	}
}



