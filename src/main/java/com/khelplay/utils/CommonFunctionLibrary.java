package com.khelplay.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.Scenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class CommonFunctionLibrary {

	WebDriver driver;
	WebDriverWait wait;
	public Dimension size;
	public static String month;
	public static String date;

	public CommonFunctionLibrary(WebDriver driver) {this.driver = driver;
	}

	public boolean switchFrame(String frameId) {
		try {
			driver.switchTo().defaultContent();
			wait = new WebDriverWait(driver, 5);
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));
			// driver.switchTo().frame(frameId);
			System.out.println(driver.getWindowHandle());
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public void embedScreenshot(Scenario scenario) {
		if (scenario.isFailed()) {
			try {
				final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This function fires events on device keys
	 * 
	 * @param key
	 * @param noOfClk
	 */
	public void deviceKeyAndroid(int key) {
		((AndroidDriver) driver).pressKeyCode(key);
	}

	public void swipeHorizontal(int endNumber, double x1, double x2, int duration, int sleep) {
		try {
			for (int iCount = 1; iCount <= endNumber; iCount++) {
				size = driver.manage().window().getSize();
				int startx = (int) (size.width * x1);
				int endx = (int) (size.width * x2);
				int starty = size.height / 2;
				((AppiumDriver) driver).swipe(startx, starty, endx, starty, duration);
				Thread.sleep(sleep);
				System.out.println("Count : " + iCount);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void swipeVertical(int endNumber, double x1, double x2, int duration, int sleep)
			throws InterruptedException {
		Thread.sleep(200);
		for (int iCount = 1; iCount <= endNumber; iCount++) {
			// Get the size of screen.
			size = driver.manage().window().getSize();
			System.out.println(size);
			int starty = (int) (size.height * x1);
			int endy = (int) (size.height * x2);
			int startx = size.width / 2;
			System.out.println("starty = " + starty + " ,endy = " + endy + " , startx = " + startx);

			// Swipe from Bottom to Top.
			((AppiumDriver) driver).swipe(startx, starty, startx, endy, duration);
			Thread.sleep(sleep);

			System.out.println("Count : " + iCount);
			// Swipe from Top to Bottom.
			// ((AppiumDriver) driver).swipe(startx, endy, startx, starty,
			// 3000);
		}
	}

	public void switchToAlertOk() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public WebElement findElement(By locator, int timeoutSeconds) {
		wait = new WebDriverWait(driver, timeoutSeconds);
		WebElement elem = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		if (elem != null) {
			return elem;
		} else {
			return null;
		}
	}

	public void switchToAlertCancel() {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public void mouseHover(By elem) {
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(elem);
		action.moveToElement(element).build().perform();

	}

	public List<String> dropdownList(By listid, String tagelem) {
		List<String> optionlist = new ArrayList<String>();
		WebElement items = driver.findElement(listid);
		List<WebElement> options = items.findElements(By.tagName(tagelem));
		System.out.println(options.size());

		for (WebElement data : options) {
			if (!data.getText().contains("Please Select")) {
				optionlist.add(data.getText());
			}

		}

		return optionlist;

	}

	public void selectdropdownByIndex(By listitem, int Index) {
		Select dropdownitem = new Select(findElement(listitem, 5));
		dropdownitem.selectByIndex(Index);

	}

	public void selectdropdownByValue(By listvalue, String Value) {
		Select dropdownitem = new Select(findElement(listvalue, 5));
		dropdownitem.selectByValue(Value);
	}

	public void selectdropdownByvisibleTxt(By listvalue, String Value) {
		Select dropdownitem = new Select(findElement(listvalue, 5));
		dropdownitem.selectByVisibleText(Value);
	}

	public String integerToMonthConverter(String num) {
		if (num.equals("01")) {
			month = "January";
		} else if (num.equals("02")) {
			month = "February";
		} else if (num.equals("03")) {
			month = "March";
		} else if (num.equals("04")) {
			month = "April";
		} else if (num.equals("05")) {
			month = "May";
		} else if (num.equals("06")) {
			month = "June";
		} else if (num.equals("07")) {
			month = "July";
		} else if (num.equals("08")) {
			month = "August";
		} else if (num.equals("09")) {
			month = "September";
		} else if (num.equals("10")) {
			month = "October";
		} else if (num.equals("11")) {
			month = "November";
		} else if (num.equals("12")) {
			month = "December";
		}

		return month;
	}

	public boolean isAlertPresent() {

		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException ex) {
			return false;
		}

	}
}
