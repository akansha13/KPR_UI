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

}
