package com.khelplay.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khelplay.utils.CommonFunctionLibrary;
import com.khelplay.utils.DateUtils;

import io.appium.java_client.android.AndroidDriver;

public class BasePage {

	protected static WebDriver driver;
	static WebDriverWait wait;
	DBConnection connnection;
	public static CommonFunctionLibrary functionLibrary;
	private static Logger LOGGER = LoggerFactory.getLogger(BasePage.class);

	public BasePage(WebDriver driver) {
		this.driver = driver;
		functionLibrary = new CommonFunctionLibrary(this.driver);
		connnection = DBConnection.getInstance();
	}

	public boolean isElementPresent(By locator, int timeoutInSeconds) {
		try {
			wait = new WebDriverWait(driver, timeoutInSeconds);
			WebElement elem = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			if (elem != null) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}

	}

	public boolean isElementPresent(String locator, int timeoutInSeconds) {
		try {
			WebElement elem = ((AndroidDriver) driver).findElementByAndroidUIAutomator(locator);
			if (elem != null) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}

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

	public WebElement findElement(String locator, int timeoutSeconds) {
		wait = new WebDriverWait(driver, timeoutSeconds);
		WebElement elem = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
		if (elem != null) {
			return elem;
		} else {
			return null;
		}
	}

	public WebElement findElement(String mobileBy) {
		WebElement elem = ((AndroidDriver) driver).findElementByAndroidUIAutomator(mobileBy);
		if (elem == null) {
			throw new ElementNotVisibleException(mobileBy + " :: element is not visible");
		}
		return elem;
	}

	public List<String> findElements(By locator, int timeoutSeconds) {
		wait = new WebDriverWait(driver, timeoutSeconds);
		List<WebElement> elem = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		List<String> temp = new ArrayList<String>();
		if (elem != null) {
			for (WebElement val : elem) {
				temp.add(val.getText());
			}
			return temp;
		} else {
			return null;
		}
	}

	public List<WebElement> findAllWebElements(By locator, int timeoutSeconds) {
		wait = new WebDriverWait(driver, timeoutSeconds);
		List<WebElement> elem = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		return elem;
	}

	/**
	 * To click an element
	 * 
	 * @param locator
	 * @throws InterruptedException
	 */
	public boolean buttonClick(By locator, int timeoutSeconds) {
		try {
			WebElement element = findElement(locator, timeoutSeconds);
			element.click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean buttonClick(String locator, int timeoutSeconds) {
		try {
			WebElement element = findElement(locator, timeoutSeconds);
			element.click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * To click an element using AndroidUIAutomator
	 * 
	 * @param mobileBy
	 * @return
	 */
	public boolean buttonClick(String mobileBy) {
		try {
			((AndroidDriver) driver).findElementByAndroidUIAutomator(mobileBy).click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean jsButtonClick(By locator) {
		try {
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = findElement(locator, 10);
			js.executeScript("arguments[0].click();", element);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean jsFunction(String function) {
		try {
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("" + function + "");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * This function enters values in text box
	 * 
	 * @param locator
	 * @param str
	 */
	public void sendKeys(By locator, String str, int timeoutInSeconds) {
		findElement(locator, timeoutInSeconds).clear();
		findElement(locator, timeoutInSeconds).sendKeys(str);
	}

	/**
	 * This function verifies element is present
	 * 
	 * @param locator
	 */
	public void verify(By locator, int timeoutInSeconds) {
		if (isElementPresent(locator, timeoutInSeconds)) {
			System.out.println(locator + " :: element is present");
		} else {
			System.out.println(locator + " :: element is not present");
		}
		WebElement elem = findElement(locator, timeoutInSeconds);

		if (elem == null) {
			throw new ElementNotVisibleException(locator + " :: element is not visible");
		}
	}

	public String verify(String mobileBy) {
		WebElement elem = ((AndroidDriver) driver).findElementByAndroidUIAutomator(mobileBy);
		if (elem == null) {
			throw new ElementNotVisibleException(mobileBy + " :: element is not visible");
		}
		return ((AndroidDriver) driver).findElementByAndroidUIAutomator(mobileBy).getText();
	}

	public boolean MouseHover(WebElement Mhover) {
		try {
			Actions Hover = new Actions(driver);
			Hover.moveToElement(Mhover).build().perform();
			Thread.sleep(2000);
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * @param parentLocator
	 * @param childPath
	 * @return
	 */
	public List<WebElement> getChildElements(By parentLocator, By childLocator) {
		try {
			WebElement listViewElement = findElement(parentLocator, 5);
			List<WebElement> childElementList = listViewElement.findElements(childLocator);
			// System.out.println("LIST ::" + childElementList);
			System.out.println(childElementList.size());
			for (WebElement elem : childElementList) {
				String text = elem.getText();
				System.out.println(text);
			}
			return childElementList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public void verifyActiveAdvanceDrawList(By locator, String query, String status, String returnFormat)
			throws SQLException {
		List<String> databaseValue = new ArrayList<String>();
		List<String> portalUiValue = new ArrayList<String>();
		ResultSet resultset = connnection.executeQuery(connnection.getDBConnectionDge(), query, status);
		while (resultset.next()) {
			for (int j = 1; j <= resultset.getMetaData().getColumnCount(); j++) {
				String temp = DateUtils.getDateInExpectedFormat("yyyy-MM-dd HH:mm:ss.S", resultset.getString(j),
						returnFormat);
				databaseValue.add(temp);
			}
		}
		System.out.println("DB LIST ::" + databaseValue);
		portalUiValue = findElements(locator, 5);
		Set<String> portalUiValueSet = new HashSet<String>();
		portalUiValueSet.addAll(portalUiValue);
		portalUiValue.clear();
		portalUiValue.addAll(portalUiValueSet);
		System.out.println("UI LIST ::" + portalUiValue);
		Collections.sort(databaseValue);
		Collections.sort(portalUiValue);
		if (databaseValue.equals(portalUiValue)) {
			System.out.println("Database value: " + databaseValue + " match with UI " + portalUiValue);
		} else {
			System.out.println("Value not matched");
		}
	}

	/**
	 * @author Shilpi Gupta
	 * @param locator
	 * @param query
	 * @param game
	 * @param status
	 * @return
	 * @throws SQLException
	 */
	public boolean verifyActiveBetType(By locator, String query, String game, String status) throws SQLException {
		DBConnection dbconnection = DBConnection.getInstance();
		Connection connection = dbconnection.getDBConnectionDge();
		ResultSet rs = dbconnection.executeQuery(connection, query, game, status);
		List<String> dbbettype = new ArrayList<String>();
		List<String> uibettype = new ArrayList<String>();
		boolean flag = false;
		while (rs.next()) {
			dbbettype.add(rs.getString(1));
		}
		System.out.println("DB LIST ::" + dbbettype);
		uibettype = findElements(locator, 5);
		System.out.println("UI LIST ::" + uibettype);
		Collections.sort(dbbettype);
		Collections.sort(uibettype);
		if (dbbettype.equals(uibettype)) {
			return true;
		}

		return flag;
	}

	/**
	 * @author Shreya Khosla
	 * @param parentLocator
	 * @param childLocator
	 * @param header
	 * @param query
	 * @param param1
	 * @param returnFormat
	 * @throws SQLException
	 */
	public void advanceDrawListVerify(By parentLocator, By childLocator, String header, String query, String param1,
			String returnFormat) throws SQLException {
		List<String> databaseValue = new ArrayList<String>();
		List<WebElement> drawList = getChildElements(parentLocator, childLocator);
		List<String> uiDrawInfo = new ArrayList<String>();
		if (drawList != null) {
			for (WebElement elem : drawList) {
				if (!elem.getText().equalsIgnoreCase(header) && !elem.getText().equalsIgnoreCase("")) {
					uiDrawInfo.add(elem.getText());
					System.out.println("UI List :: " + uiDrawInfo);
				}
			}
		} else {
			Assert.fail();
		}
		ResultSet resultset = connnection.executeQuery(connnection.getDBConnectionDge(), query, param1);
		while (resultset.next()) {
			for (int j = 1; j <= resultset.getMetaData().getColumnCount(); j++) {
				String temp = DateUtils.getDateInExpectedFormat("yyyy-MM-dd HH:mm:ss.S", resultset.getString(j),
						returnFormat);
				databaseValue.add(temp);
				System.out.println("DB List :: " + databaseValue);
			}
		}
		if (uiDrawInfo.equals(databaseValue)) {
			System.out.println("database value: " + databaseValue + " match with front end: " + uiDrawInfo);
		} else {
			System.out.println("value not matched");
		}
	}

	/**
	 * @author Shilpi Gupta
	 * @param parentLocator
	 * @param childLocator
	 * @param header
	 * @param query
	 * @param game_id_query
	 * @param param2
	 * @param param1
	 * @param returnFormat
	 * @throws SQLException
	 */
	public void advanceDrawListVerify(By parentLocator, By childLocator, String header, String query,
			String game_id_query, String param2, String param1, String returnFormat) throws SQLException {
		List<String> databaseValue = new ArrayList<String>();
		List<WebElement> drawList = getChildElements(parentLocator, childLocator);
		List<String> uiDrawInfo = new ArrayList<String>();
		if (drawList != null) {
			System.out.println("UI List :: ");
			for (WebElement elem : drawList) {
				if (!elem.getText().equalsIgnoreCase(header) && !elem.getText().equalsIgnoreCase("")) {
					uiDrawInfo.add(elem.getText());
				}
			}
			System.out.println(uiDrawInfo);
		} else {
			Assert.fail();
		}
		ResultSet game_id = connnection.executeQuery(connnection.getDBConnectionDge(), game_id_query, param2);
		while (game_id.next()) {
			query = query + game_id.getString(1) + " " + "where draw_status=" + "'" + param1 + "'"
					+ " and draw_datetime!='null'";
			ResultSet resultset = connnection.executeQuery(connnection.getDBConnectionDge(), query);
			System.out.println("DB List :: ");
			while (resultset.next()) {
				for (int j = 1; j <= resultset.getMetaData().getColumnCount(); j++) {
					String temp = DateUtils.getDateInExpectedFormat("yyyy-MM-dd HH:mm:ss.S", resultset.getString(j),
							returnFormat);
					databaseValue.add(temp);
				}
			}
			System.out.println(databaseValue);
			if (uiDrawInfo.equals(databaseValue)) {
				System.out.println("Database value: " + databaseValue + " matches with UI: " + uiDrawInfo);
			} else {
				System.out.println("Value not matched");
			}
		}
	}

	/**
	 * This function generates random Strings
	 * 
	 * @param str1
	 * @param str2
	 * @param str3
	 * @return
	 * @throws InterruptedException
	 */
	public String getRandStr(String str1, String str2, String str3) throws InterruptedException {

		String names[] = { str1, str2, str3 };
		String random = (names[new Random().nextInt(names.length)]);
		Thread.sleep(1000);
		try {
			System.out.println(names[new Random().nextInt(names.length)]);

		} catch (Exception e) {
			System.out.println(e);
		}
		return random;
	}

	public String getRandStr(String str1, String str2, String str3, String str4, String str5)
			throws InterruptedException {

		String names[] = { str1, str2, str3, str4, str5 };
		String random = (names[new Random().nextInt(names.length)]);
		Thread.sleep(1000);
		try {
			System.out.println(names[new Random().nextInt(names.length)]);

		} catch (Exception e) {
			System.out.println(e);
		}
		return random;
	}

	public static String getRandStr(String s1, String s2, String s3, String s4, String s5, String s6, String s7,
			String s8) throws InterruptedException {

		String names[] = { s1, s2, s3, s4, s5, s6, s7, s8 };
		String random = (names[new Random().nextInt(names.length)]);
		Thread.sleep(1000);
		try {
			System.out.println(names[new Random().nextInt(names.length)]);

		} catch (Exception e) {
			System.out.println(e);
		}
		return random;
	}

	/**
	 * This function clicks events randomly for Soccer
	 * 
	 * @param parentElem
	 * @param panelStartNum
	 * @param panelEndNumber
	 * @param str1
	 * @param str2
	 * @param str3
	 * @throws InterruptedException
	 */
	public void clickSLE(String parentElem, int panelStartNum, int panelEndNumber, String str1, String str2,
			String str3) throws InterruptedException {

		for (int iCount = panelStartNum; iCount <= panelEndNumber; iCount++) {
			WebElement relative = driver.findElement(By.xpath(parentElem + iCount + "']"));
			relative.findElement(By.id(getRandStr(str1, str2, str3))).click();
			System.out.println("Click successful:: " + relative);
		}
	}

	/**
	 * This function clicks events randomly for Soccer
	 * 
	 * @param parentElem
	 * @param panelStartNum
	 * @param panelEndNumber
	 * @param str1
	 * @param str2
	 * @param str3
	 * @param str4
	 * @param str5
	 * @throws InterruptedException
	 */
	public void clickSLE(String parentElem, int panelStartNum, int panelEndNumber, String str1, String str2,
			String str3, String str4, String str5) throws InterruptedException {

		for (int iCount = panelStartNum; iCount <= panelEndNumber; iCount++) {
			WebElement relative = driver.findElement(By.xpath(parentElem + iCount + "']"));
			relative.findElement(By.id(getRandStr(str1, str2, str3, str4, str5))).click();
			System.out.println("Click successful:: " + relative);
		}
	}

	public void switchToMBrowserWindow() {
		String parentWindowHandle = ((AndroidDriver) driver).getWindowHandle();
		System.out.println("Parent window's handle -> " + parentWindowHandle);
		for (String winHandle : driver.getWindowHandles()) {
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			driver.switchTo().window(winHandle);
			System.out.println(driver.getCurrentUrl());
		}
	}

	public static String generateNumber() throws InterruptedException {
		String number;
		String randmNumber = getRandStr(1 + "", 2 + "", 3 + "", 4 + "", 5 + "", 6 + "", 7 + "", 8 + "");
		number = randmNumber.concat(getRandStr(1 + "", 2 + "", 3 + "", 4 + "", 5 + "", 6 + "", 7 + "", 8 + ""))
				.concat(getRandStr(1 + "", 2 + "", 3 + "", 4 + "", 5 + "", 6 + "", 7 + "", 8 + ""))
				.concat(getRandStr(1 + "", 2 + "", 3 + "", 4 + "", 5 + "", 6 + "", 7 + "", 8 + ""));
		return number;
	}

	public boolean isSelectDateFromCalender(By locator, String incrementordecrementDay)
			throws InterruptedException, ParseException {
		String inputDate = incrementordecrementDay;
		boolean flag = false;
		List<WebElement> temp = new ArrayList<WebElement>();
		try {
			WebElement calenderbutton = findElement(locator, 5);
			WebElement parent = calenderbutton.findElement(By.xpath(".."));
			temp = parent.findElements(By.tagName("input"));
			String currentdate = temp.get(0).getAttribute("value");
			System.out.println("CURRENT DATE : " + currentdate);
			LOGGER.info("CURRENT DATE : " + currentdate);
			String mydate[] = currentdate.split("/");
			int d = Integer.parseInt(mydate[0]);
			int m = Integer.parseInt(mydate[1]);
			int y = Integer.parseInt(mydate[2]);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date dateString = new Date(inputDate);
				inputDate = dateFormat.format(dateString);
			} catch (Exception e) {
				inputDate = inputDate;
			}
			Date date = null;
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.DATE, Integer.parseInt(inputDate));
			// date = dateFormat.parse(dateFormat.format(c.getTime()));
			date = dateFormat.parse(dateFormat.format(c.getTime()));
			String strYear = (new SimpleDateFormat("yyyy")).format(date);
			String strMonth = (new SimpleDateFormat("MMMM")).format(date);
			String strDate = (new SimpleDateFormat("dd")).format(date);
			calenderbutton.click();
			WebElement dateWidget = driver.findElement(By.id("calendarDiv"));
			List<WebElement> dropDownMonth = driver.findElements(By.xpath("//span[text()='V']"));
			dropDownMonth.get(1).click();

			for (int j = 0; j < 5; j++) {
				try {
					if (j == 0) {
						if (driver.findElement(By.xpath("//div[text()='" + strYear + "']")).isDisplayed()) {
							driver.findElement(By.xpath("//div[text()='" + strYear + "']")).click();
							break;
						}
					} else {
						Actions builderYear = new Actions(driver);
						builderYear.moveToElement(driver.findElement(By.xpath(".//*[@id='yearDropDown']/div[1]")))
								.build().perform();
						Thread.sleep(1000);
						builderYear.moveByOffset(1320, 700).build().perform();

						if (driver.findElement(By.xpath("//div[text()='" + strYear + "']")).isDisplayed()) {
							driver.findElement(By.xpath("//div[text()='" + strYear + "']")).click();
							break;
						}
					}
				} catch (NoSuchElementException e) {
					continue;
				}
			}

			dropDownMonth.get(0).click();
			driver.findElement(By.xpath("//div[text()='" + strMonth + "']")).click();

			List<WebElement> columnPrev = dateWidget.findElements(By.tagName("td"));
			String simpledate = null;
			if ((Integer.parseInt(strDate) < 10) && (Integer.parseInt(strDate) > 0)) {
				simpledate = String.valueOf(Integer.parseInt(strDate));
			} else {
				simpledate = strDate;
			}

			for (WebElement cell : columnPrev) {
				if (cell.getText().equalsIgnoreCase(simpledate)) {
					cell.click();
					flag = true;
					break;
				} else {
					flag = false;
				}
			}
		} catch (NullPointerException e) {
			System.out.println("ELEMENT NOT FOUND : " + e);
			LOGGER.info("ELEMENT NOT FOUND : " + e);
		} catch (Exception e) {
			System.out.println("EXCEPTION : " + e);
			LOGGER.info("EXCEPTION : " + e);
		}
		String changedate = temp.get(0).getAttribute("value");
		System.out.println("SELECTED DATE : " + changedate);
		return flag;
	}

	public boolean selectOptionInDrowDown(By locator, String option) {
		boolean flag = false;
		try {
			Select dropdown = new Select(findElement(locator, 5));
			try {
				dropdown.selectByVisibleText(option.trim());
				flag = true;
			} catch (Exception e) {
				dropdown.selectByValue(option.trim());
				flag = true;
			}

		} catch (Exception e) {
			System.out.println("UNABLE TO SELECT OPTION :(" + e);
			flag = false;
		}
		return flag;
	}

}