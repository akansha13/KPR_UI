package com.khelplay.mobile.ui;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khelplay.common.BasePage;
import com.khelplay.objectrepository.mobile.WeaverLocators;
import com.khelplay.utils.ConfigManager;

public class MobileChangePassword extends BasePage {

	private static Logger logger = LoggerFactory.getLogger(MobileChangePassword.class);
	public static MobileChangePassword obj;

	public MobileChangePassword(WebDriver driver) {
		super(driver);
		if (isElementPresent(WeaverLocators.changePasswordPage, 5)) {
			logger.info("Change Password page is Present");
		} else {
			logger.warn("Change Password page is Present");
			throw new ElementNotVisibleException("Change Password page is Present");
		}
	}

	public void OldPassword(String oldpassword) {
		sendKeys(WeaverLocators.oldPassword, oldpassword, 10);
	}

	public void NewPassword(String newpassword) {
		sendKeys(WeaverLocators.newPassword, newpassword, 10);
	}

	public void RetypePassword(String retypepassword) {
		sendKeys(WeaverLocators.retypePassword, retypepassword, 10);
	}

	public void changePasswordOK() {
		buttonClick(WeaverLocators.ok_ChangePasswordButton, 5);
	}

	public void username(String username) {
		sendKeys(WeaverLocators.usernameAndroid, username, 10);
	}

	public void password(String password) {
		sendKeys(WeaverLocators.passwordAndroid, password, 10);
	}

	public boolean verifyValidChangePassword() {
		boolean flag = false;
		if (isElementPresent(WeaverLocators.usernameAndroid, 5)) {
			username(ConfigManager.getProperty("Username"));
			password(ConfigManager.getProperty("NewPassword"));
			buttonClick(WeaverLocators.loginAndroid, 10);
			if (isElementPresent(WeaverLocators.updatePopUp, 5)) {
				buttonClick(WeaverLocators.okButton, 10);
				logger.info("Notification dismissed");
			} else {
				logger.info("Notification pop up not present");
			}
			buttonClick(WeaverLocators.lobbyMenuButton, 5);
			buttonClick(WeaverLocators.myAccountMenuButton, 5);
			buttonClick(WeaverLocators.changePasswordButton, 5);
			OldPassword(ConfigManager.getProperty("NewPassword"));
			NewPassword(ConfigManager.getProperty("Password"));
			RetypePassword(ConfigManager.getProperty("Password"));
			changePasswordOK();
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public boolean verifyInvalidChangePassword(String errormsg) {
		boolean flag = false;
		buttonClick(WeaverLocators.ok_ChangePasswordButton, 5);

		if (isElementPresent(WeaverLocators.oldPasswordError, 5)) {
			flag = errormsg.equalsIgnoreCase(findElement(WeaverLocators.oldPasswordError, 5).getText());
		} else if (isElementPresent(WeaverLocators.newPasswordError, 5)) {
			flag = errormsg.equalsIgnoreCase(findElement(WeaverLocators.newPasswordError, 5).getText());
		} else if (isElementPresent(WeaverLocators.retypePasswordError, 5)) {
			flag = errormsg.equalsIgnoreCase(findElement(WeaverLocators.retypePasswordError, 5).getText());
		} 
		return flag;
	}
	

}
