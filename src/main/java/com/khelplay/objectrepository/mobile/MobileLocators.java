package com.khelplay.objectrepository.mobile;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;

public class MobileLocators {
	
public static By nmUpdateLater = By.xpath("//android.widget.Button[@text='Later']");
public static By loginPage = By.xpath("//android.widget.TextView[@text='Login to your Account']");
public static By usernameLogin = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/username\")");
public static By passwordLogin = By.xpath("//android.widget.EditText[@index='2']");
public static By loginButton = By.xpath("//android.widget.Button[@text='Login']");
public static By slowNetworkPopup = By.xpath("//android.widget.Button[@text='OK']");
public static By avatarIcon = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/img_user_icon\")");
public static By skipTour = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/skip_Tour\")");


}
