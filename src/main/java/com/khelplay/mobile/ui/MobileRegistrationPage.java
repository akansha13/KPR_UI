package com.khelplay.mobile.ui;

import java.util.List;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khelplay.common.BasePage;
import com.khelplay.objectrepository.mobile.WeaverLocators;
import com.khelplay.utils.ReusableStaticMethods;

public class MobileRegistrationPage extends BasePage {

	private static Logger logger = LoggerFactory.getLogger(MobileRegistrationPage.class);
	public static MobileRegistrationPage obj;

	public MobileRegistrationPage(WebDriver driver) throws InterruptedException {
		super(driver);
		logger.info("" + driver);
		Thread.sleep(10000);
		initiate();
		if (isElementPresent(WeaverLocators.registrationPage, 5)) {
			logger.info("Registration Page is present");
		} else {
			logger.warn("Registration Page is not present");
			throw new ElementNotVisibleException("Registration Page is not present");
		}
	}

	public void initiate() {
		if (isElementPresent(WeaverLocators.updatePopUp, 5)) {
			buttonClick(WeaverLocators.laterButton, 5);
		} else {
			logger.info("Update popup is not present");
		}
		buttonClick(WeaverLocators.registerNowButton, 5);
	}

	public void generateUsername() {
		List<Integer> list = ReusableStaticMethods.randomNumber(1, 9, 5);
		String Username = ReusableStaticMethods.IntegerListToString(list, "test");
		sendKeys(WeaverLocators.usernameRegistration, Username, 5);
	}

	public void Password(String password) {
		sendKeys(WeaverLocators.passwordRegistration, password, 5);
	}

	public void generateEmailID() {
		List<Integer> list = ReusableStaticMethods.randomNumber(1, 9, 5);
		String Email = ReusableStaticMethods.IntegerListToString(list, "test");
		Email = Email + "@skilrock.com";
		sendKeys(WeaverLocators.emailRegistration, Email, 5);
	}

	public void generateMobileNo() {
		List<Integer> list = ReusableStaticMethods.randomNumber(1, 9, 5);
		String mobileNo = ReusableStaticMethods.IntegerListToString(list, "99999");
		sendKeys(WeaverLocators.mobileNoRegistration, mobileNo, 5);
	}

	public boolean verifyValidRegistration() {
		boolean flag = false;
		buttonClick(WeaverLocators.registerButton, 5);
		buttonClick(WeaverLocators.ok_RegisterButton, 10);
		generateNickname();
		buttonClick(WeaverLocators.submit_Nickname, 5);
		buttonClick(WeaverLocators.skipTour, 10);

		if (isElementPresent(WeaverLocators.avatarIcon, 5)) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	private void generateNickname() {
		List<Integer> list = ReusableStaticMethods.randomNumber(1, 9, 5);
		String nickname = ReusableStaticMethods.IntegerListToString(list, "nick");
		sendKeys(WeaverLocators.nickname, nickname, 20);
	}

	public void Username(String username) {
		sendKeys(WeaverLocators.usernameRegistration, username, 5);
	}

	public void emailAddress(String emailAddress) {
		sendKeys(WeaverLocators.emailRegistration, emailAddress, 5);
	}

	public void mobileNumber(String mobileNumber) {
		sendKeys(WeaverLocators.mobileNoRegistration, mobileNumber, 5);
	}

	public boolean verifyInvalidRegistration(String errormsg) {
		boolean flag = false;
		buttonClick(WeaverLocators.registerButton, 5);

		if (isElementPresent(WeaverLocators.usernameError, 5)) {
			flag = errormsg.equalsIgnoreCase(findElement(WeaverLocators.usernameError, 5).getText());
		} else if (isElementPresent(WeaverLocators.passwordError, 5)) {
			flag = errormsg.equalsIgnoreCase(findElement(WeaverLocators.passwordError, 5).getText());
		} else if (isElementPresent(WeaverLocators.emailError, 5)) {
			flag = errormsg.equalsIgnoreCase(findElement(WeaverLocators.emailError, 5).getText());
		} else if (isElementPresent(WeaverLocators.mobileNoError, 5)) {
			flag = errormsg.equalsIgnoreCase(findElement(WeaverLocators.mobileNoError, 5).getText());
		}
		return flag;
	}

}
