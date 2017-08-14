package com.khelplay.mobile.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khelplay.common.BasePage;
import com.khelplay.objectrepository.mobile.GamePlayBean;
import com.khelplay.objectrepository.mobile.WeaverLocators;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;

public class InstantPlayPage extends BasePage {
	public static Object obj;
	private static Logger logger = LoggerFactory.getLogger(InstantPlayPage.class);

	public InstantPlayPage(WebDriver driver) {
		super(driver);
		if (findElement(WeaverLocators.gameTableTitle, 5).getText().contains("Deals-Best of 3 (N)")) {
			logger.info("deals table is opened");
		} else {
			logger.warn("deals table is not opened");
			throw new ElementNotVisibleException("deals table is not opened");
		}
	}

	public void selectTakeSeat() {
		if (findElement(WeaverLocators.vacantSeatPlayer1, 5).getText().contains("Take Seat")) {
			buttonClick(WeaverLocators.takeSeatPlayer1, 5);
		} else {
			buttonClick(WeaverLocators.takeSeatPlayer2, 5);
		}
	}

	public void selectCardData() {
		if (isElementPresent(WeaverLocators.sortCard, 30)) {
			ArrayList<String> cardDataList = new ArrayList<String>();
			for (int i = 0; i < 13; i++) {
				String[] cardInfo = findElement(WeaverLocators.cardlist + i + WeaverLocators.cardImageData)
						.getAttribute("name").split("\\s+");
				String cardData = "";
				String symbol = cardInfo[0];

				switch (symbol) {
				case "0":
					cardData = cardInfo[1] + "-DIAMOND";
					break;
				case "1":
					cardData = cardInfo[1] + "-SPADE";
					break;
				case "2":
					cardData = cardInfo[1] + "-HEART";
					break;
				case "3":
					cardData = cardInfo[1] + "-CLUB";
					break;
				case "4":
					cardData = "JOKER";
					break;
				default:
					Assert.fail();
				}
				cardDataList.add(cardData);
			}

			logger.info(cardDataList.toString());
			GamePlayBean.setGamePlayCardList(cardDataList);
		} else {
			logger.error("sort button not displayed");
		}

	}

	public void selectSortCard() {
		if (isElementPresent(WeaverLocators.sortCard, 30))
			buttonClick(WeaverLocators.sortCard, 5);
		logger.info("cards sorted");
	}

	public void selectLeaveTable() {
		buttonClick(WeaverLocators.leavetable, 5);
		buttonClick(WeaverLocators.leaveTableYes, 5);
		if (isElementPresent(WeaverLocators.avatarIcon, 5)) {
			logger.info("User has left table");
		} else {
			logger.warn("User has not left table");
		}
	}

	public void selectClosedCard() {

		if (isElementPresent(WeaverLocators.timer_1G, 45)) {
			buttonClick(WeaverLocators.closedDeck, 5);
			findElement(WeaverLocators.cardlist + 13 + WeaverLocators.cardlist1).click();
			findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/btn_drop\")"), 5).click();
			buttonClick(WeaverLocators.leaveTableYes, 5);
			buttonClick(WeaverLocators.meld, 5);
			buttonClick(WeaverLocators.leaveTableYes, 5);
		}

	}

	public void getFistHandHistory() {
		ArrayList<String> cardDataList = new ArrayList<String>();
		buttonClick(WeaverLocators.drawerIcon, 5);
		buttonClick(WeaverLocators.drawerList, 5);
		for (int i = 0; i < 13; i++) {
			String[] cardInfo = findElement(WeaverLocators.fisrtHandHistory + i + WeaverLocators.cardImageData)
					.getAttribute("name").split("\\s+");
			String cardData = "";
			String symbol = cardInfo[0];
			switch (symbol) {
			case "0":
				cardData = cardInfo[1] + "-DIAMOND";
				break;
			case "1":
				cardData = cardInfo[1] + "-SPADE";
				break;
			case "2":
				cardData = cardInfo[1] + "-HEART";
				break;
			case "3":
				cardData = cardInfo[1] + "-CLUB";
				break;
			case "4":
				cardData = "JOKER";
				break;
			default:
				Assert.fail();
			}
			cardDataList.add(cardData);

			if (i == 4 || i == 9) {
				((AppiumDriver) driver).swipe(1973, 403, 1000, 403, 1200);
			}
		}
		GamePlayBean.setFistHandCardList(cardDataList);
	}

	public boolean verifyCardData() {
		boolean flag;
		if (GamePlayBean.getGamePlayCardList().toString().equals(GamePlayBean.getFistHandCardList().toString())) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public void groupCard(String player, String group) {
		buttonClick(WeaverLocators.sortCard, 5);
		if (isElementPresent(WeaverLocators.timer_1G, 10) || isElementPresent(WeaverLocators.extra_timer, 10)) {

			String[] a = group.split(",");

			List<WebElement> parent = driver
					.findElements(By.className("android.widget.RelativeLayout").id("layout_card_distribution"));
			List<WebElement> child = parent.get(0).findElements(By.className("android.widget.TextView"));
			System.out.println(child.size());

			for (int i = child.size() - 1; i >= 0; i--) {
				boolean flag1 = isElementPresent(WeaverLocators.cardlist + i + WeaverLocators.cardImageData, 1);
				if (flag1 == true) {
					String cardData = findElement(WeaverLocators.cardlist + i + WeaverLocators.cardImageData)
							.getAttribute("name");
					if (Arrays.asList(a).contains(cardData)) {
						buttonClick(WeaverLocators.cardlist + i + WeaverLocators.cardlist1);
					}
				} else if (flag1 == false)
					break;
			}
			buttonClick(WeaverLocators.groupBtn, 5);
		} else {
			findElement(WeaverLocators.timer_1G, 30);
			System.out.println("Chance switched");
		}
	}

	public void discardCard(String player, String discardCard) {
		if (isElementPresent(WeaverLocators.timer_1G, 10) || isElementPresent(WeaverLocators.extra_timer, 10)) {
			buttonClick(WeaverLocators.closedDeck, 5);

			List<WebElement> parent = driver
					.findElements(By.className("android.widget.RelativeLayout").id("layout_card_distribution"));
			List<WebElement> child = parent.get(0).findElements(By.className("android.widget.TextView"));
			System.out.println(child.size());

			for (int i = child.size() - 1; i >= 0; i--) {
				boolean flag1 = isElementPresent(WeaverLocators.cardlist + i + WeaverLocators.cardImageData, 5);
				if (flag1 == true) {
					String cardData = findElement(WeaverLocators.cardlist + i + WeaverLocators.cardImageData)
							.getAttribute("name");
					if (discardCard.contains(cardData)) {
						buttonClick(WeaverLocators.cardlist + i + WeaverLocators.cardlist1);
						buttonClick(WeaverLocators.discard, 5);
					}
				} else if (flag1 == false)
					break;
			}
			System.out.println("hello");
		} else {
			findElement(WeaverLocators.timer_1G, 30);
			System.out.println("Chance switched");
		}
	}

	public void closedDeck(String player) {
		if (isElementPresent(WeaverLocators.timer_1G, 10) || isElementPresent(WeaverLocators.extra_timer, 10)) {
			buttonClick(WeaverLocators.closedDeck, 5);
		} else {
			System.out.println("chance switched");
		}

	}

	public void placeShow(String player, String showCard) {
		if (isElementPresent(WeaverLocators.timer_1G, 10) || isElementPresent(WeaverLocators.extra_timer, 10)) {

			List<WebElement> parent = driver
					.findElements(By.className("android.widget.RelativeLayout").id("layout_card_distribution"));
			List<WebElement> child = parent.get(0).findElements(By.className("android.widget.TextView"));
			System.out.println(child.size());

			for (int i = child.size() - 1; i >= 0; i--) {
				boolean flag1 = isElementPresent(WeaverLocators.cardlist + i + WeaverLocators.cardImageData, 5);
				if (flag1 == true) {
					String cardData = findElement(WeaverLocators.cardlist + i + WeaverLocators.cardImageData)
							.getAttribute("name");
					if (showCard.contains(cardData)) {
						buttonClick(WeaverLocators.cardlist + i + WeaverLocators.cardlist1);
						buttonClick(WeaverLocators.show, 5);
						buttonClick(WeaverLocators.leaveTableYes, 5);
						buttonClick(WeaverLocators.meld, 5);
						buttonClick(WeaverLocators.leaveTableYes, 5);
					}
				} else if (flag1 == false)
					break;
			}

		} else {
			System.out.println("Player 2 chance");
		}

	}

}
