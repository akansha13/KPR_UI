package com.khelplay.objectrepository.mobile;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;

public class WeaverLocators {

	public static By updatePopUp = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/txt_title\")");
	public static By laterButton = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/btn_no\")");
	public static By usernameAndroid = MobileBy
			.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/username\")");
	public static By passwordAndroid = MobileBy
			.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/password\")");
	public static By loginAndroid = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/btn_login\")");
	public static By okButton = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/btn_ok\")");
	public static By skipTour = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/skip_Tour\")");
	public static By avatarIcon = MobileBy
			.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/img_user_icon\")");
	public static By instantPlay = MobileBy
			.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/lobbyPlayNow\")");
	public static By clickDeals = MobileBy
			.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/btn_playnow_deals\")");
	public static By gameTableTitle = MobileBy
			.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/layout_game_type_value\")");
	public static By takeSeat = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/img_5\")");

	public static String cardlist = "new UiSelector().className(android.widget.RelativeLayout).resourceIdMatches(\".*/layout_card_distribution\").childSelector(new UiSelector().className(android.widget.RelativeLayout).index(";

	public static String cardlist1 = ")).childSelector(new UiSelector().className(android.widget.TextView).resourceIdMatches(\".*/card_text\"))";
	public static By sortCard = MobileBy
			.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/btn_sort_cards\")");
	public static By leavetable = MobileBy
			.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/btn_leave_table\")");
	public static By leaveTableYes = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/btn_yes\")");
	public static By invalidLoginMsg = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/txt_msg\")");
	public static By enterPassword = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/password_error\")");
	public static By enterUsername = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/username_error\")");
	public static By forgotPassword = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/forgot_pswd\")");
	public static By resetPassword = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/title\")");
	public static By emailForgotPassword = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/email\")");
	public static By loginPage = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/login_title\")");
	public static By forgotPasswordError = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/email_error\")");
	public static By submitForgotPassword = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/btn_submit\")");
	public static By registrationPage = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/signup_title\")");
	public static By registerNowButton = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/btn_sign_up\")");
	public static By registerButton = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/btn_register\")");
	public static By usernameRegistration = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/username\")");
	public static By mobileNoRegistration = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/mobileno\")");
	public static By emailRegistration = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/email\")");
	public static By passwordRegistration = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/password\")");
	public static By ok_RegisterButton = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/btn_ok\")");
	public static By nickname = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/nickname\")");
	public static By submit_Nickname = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/btn_submit\")");
	public static By usernameError = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/username_error\")");
	public static By passwordError = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/password_error\")");
	public static By emailError = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/email_error\")");
	public static By mobileNoError = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/mobileno_error\")");
	public static By lobbyMenuButton = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/btn_lobby_menu_icon\")");
	public static By changePasswordPage = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/title\")");
	//public static By myAccountMenuButton = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/drawer_listview\").childSelector(new UiSelector().className(android.widget.RelativeLayout).index(0)");
	public static By myAccountMenuButton = MobileBy.xpath("//android.widget.TextView[@text='My Account']");
	//public static By changePasswordButton = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/tv_child_text\")");
	public static By changePasswordButton = MobileBy.xpath("//android.widget.TextView[@text='Change Password']");
	public static By oldPassword = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/edt_cur_pswd\")");
	public static By newPassword = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/edt_new_pswd\")");
	public static By retypePassword = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/edt_retype_pswd\")");
	public static By ok_ChangePasswordButton = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/btn_change\")");
	public static By oldPasswordError = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/cur_pswd_error\")");
	public static By newPasswordError = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/new_pswd_error\")");
	public static By retypePasswordError = MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/retype_pswd_error\")");
}
