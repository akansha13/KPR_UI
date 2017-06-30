package com.khelplay.mobile.ui;

import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khelplay.common.BasePage;
import com.skilrock.objectrepository.mobile.WeaverLocators;

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
		buttonClick(WeaverLocators.takeSeat, 5);

	}

	public void selectCardData() {
		if (isElementPresent(WeaverLocators.sortCard, 30)) {
			ArrayList<String> cardData = new ArrayList<String>();
			for (int i = 0; i < 13; i++) {
				cardData.add(findElement(WeaverLocators.cardlist + i + WeaverLocators.cardlist1).getText());
				
			}
			System.out.println(cardData.toString());
		} else {
			logger.info("sort button not displayed");
		}
	}

	public void selectSortCard() {
		if (isElementPresent(WeaverLocators.sortCard, 30))
			buttonClick(WeaverLocators.sortCard, 5);
		logger.info("cards sorted");
	}

}
