package com.khelplay.mobile.ui;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khelplay.common.BasePage;
import com.khelplay.objectrepository.mobile.WeaverLocators;

public class ForgotPasswordPage extends BasePage {

	private static Logger logger = LoggerFactory.getLogger(ForgotPasswordPage.class);
	public static ForgotPasswordPage obj;

	public ForgotPasswordPage(WebDriver driver) throws InterruptedException {
		super(driver);
		logger.info("" + driver);
		Thread.sleep(10000);
		initiate();

		if (isElementPresent(WeaverLocators.resetPassword, 5)) {
			logger.info("Forgot Password page is present");
		} else {
			logger.warn("Forgot Password page is not present");
			throw new ElementNotVisibleException("Forgot Password page is not present");
		}

	}

	public void initiate() {
		if (isElementPresent(WeaverLocators.updatePopUp, 5)) {
			buttonClick(WeaverLocators.laterButton, 5);
		} else {
			logger.info("Update popup is not Present");
		}
		buttonClick(WeaverLocators.forgotPassword, 5);

	}

	public void email(String email) {
		sendKeys(WeaverLocators.emailForgotPassword, email, 10);
	}

	public boolean verifyInvalidForgotPassword(String errormsg) {

		boolean flag = false;
		buttonClick(WeaverLocators.submitForgotPassword, 10);
		if (isElementPresent(WeaverLocators.forgotPasswordError, 5)) {
			flag = errormsg.equalsIgnoreCase(findElement(WeaverLocators.forgotPasswordError, 5).getText());
		}
		return flag;
	}

	public boolean verifyValidForgotPassword() {
		boolean flag = false;
		buttonClick(WeaverLocators.submitForgotPassword, 5);
		if (isElementPresent(WeaverLocators.loginPage, 5)) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}
}
