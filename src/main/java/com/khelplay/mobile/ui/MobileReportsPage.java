package com.khelplay.mobile.ui;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khelplay.common.BasePage;
import com.khelplay.objectrepository.mobile.WeaverLocators;



public class MobileReportsPage extends BasePage {
	private static Logger logger = LoggerFactory.getLogger(MobileReportsPage.class);
	public static MobileReportsPage obj;

	public MobileReportsPage(WebDriver driver) {
		super(driver);
		if (isElementPresent(WeaverLocators.mobileReportsPage, 5)) {
			logger.info("Mobile Reports Page is Present");
		} else {
			logger.warn("Mobile Reports Page is not Present");
			throw new ElementNotVisibleException("Mobile Reports Page is not Present");
		}
	}

	public void FromDate(String fromDate) {
		buttonClick(WeaverLocators.fromDate, 5);
	}

	public void ToDate(String toDate) {
		sendKeys(WeaverLocators.toDate, toDate, 10);
	}

	boolean flag = false;

	public void selectCalendarDate() {

		String year = "new UiSelector().className(android.widget.DatePicker).resourceIdMatches(\".*/datePicker\").childSelector(new UiSelector().className(android.widget.LinearLayout).resourceIdMatches(\".*/date_picker_header\")).childSelector(new UiSelector().className(android.widget.TextView).resourceIdMatches(\".*/date_picker_header_year\"))";
		buttonClick(year);

		String yearselect = "new UiSelector().className(android.widget.ListView).resourceIdMatches(\".*/date_picker_year_picker\").index(";

		flag = consecutiveSwipe(yearselect);
		if (flag = false) {

			consecutiveSwipe(yearselect);
		} else {
			System.out.println("done");
		}

	}

	public boolean consecutiveSwipe(String yearselect) {

		for (int i = 0; i < 5; i++) {
			if (findElement(yearselect + i + ")").getText().equalsIgnoreCase("2017")) {
				findElement(yearselect + i + "))").click();
				flag = true;
			}
		}
		if (flag = true) {
			System.out.println("clicked");
		} else {
			try {
				functionLibrary.swipeVertical(1, 0.75, 0.03, 1000, 1500);
			} catch (InterruptedException e) {
				logger.warn("Exception Occurred:" + e);
			}
		}
		return flag;

	}

}
