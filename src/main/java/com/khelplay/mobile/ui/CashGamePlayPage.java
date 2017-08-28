package com.khelplay.mobile.ui;

import static com.jayway.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.restassured.response.Response;
import com.khelplay.common.BasePage;
import com.khelplay.objectrepository.mobile.GamePlayBean;
import com.khelplay.objectrepository.mobile.WeaverLocators;
import com.khelplay.utils.ConfigManager;

public class CashGamePlayPage extends BasePage {
	public static Object obj;
	private static Logger logger = LoggerFactory.getLogger(CashGamePlayPage.class);
	boolean flag = false;
	public Response response;

	public CashGamePlayPage(WebDriver driver) {
		super(driver);
		if (findElement(WeaverLocators.cashPopUpTitle, 5).getText().contains("Cash Games")) {
			logger.info("cash pop up is opened");
		} else {
			logger.warn("cash pop up is not opened");
			throw new ElementNotVisibleException("cash pop up is not opened");
		}
	}

	public void selectTableType(String tableType, String playerType) {
		buttonClick(WeaverLocators.cashGameType + '"' + tableType + '"' + "))");
		buttonClick(WeaverLocators.cashGamePlayer + '"' + playerType + '"' + "))");
		buttonClick(WeaverLocators.letsPlay, 5);

	}

	public void selectLeaveTable() throws InterruptedException {
		
		List<WebElement> l=new ArrayList<WebElement>();
		l=findAllWebElements(WeaverLocators.leaveTableTimer, 10);
		System.out.println(l);	
		if (l!=null) {
			logger.info(findElement(WeaverLocators.leaveTableTimer, 10).getText());
			//buttonClick(WeaverLocators.leaveTableTimer, 5);
		    l.get(1).click();
		    logger.info("Leave Table Clicked Sucessfully");
		/*if (isElementPresent(WeaverLocators.leaveTableTimer, 10)) {
			System.out.println(findElement(WeaverLocators.leaveTableTimer, 10).getText());
			buttonClick(WeaverLocators.leaveTableTimer, 5);*/
		} else {			
			logger.info("Leave Table not picked");
			buttonClick(WeaverLocators.leavetable, 5);
			buttonClick(WeaverLocators.leaveTableYes, 5);
		}
		if (isElementPresent(WeaverLocators.avatarIcon, 5)) {
			logger.info("User has left table");
		} else {
			logger.warn("User has not left table");
		}
	}

	public void selectTakeSeatandValidate() {
		GamePlayBean.setTakeSeatCount(0);
		if (findElement(WeaverLocators.vacantSeatPlayer1, 5).getText().contains("Take Seat")) {
			consecutiveTakeSeat(WeaverLocators.takeSeatPlayer1, WeaverLocators.vacantSeatPlayer1);
			if (flag == true) {
				System.out.println("table joined");
				GamePlayBean.setTakeSeatCount(0);

			} else if (flag == false) {
				consecutiveTakeSeat(WeaverLocators.takeSeatPlayer1, WeaverLocators.vacantSeatPlayer1);
				if (GamePlayBean.getTakeSeatCount() > 3 && GamePlayBean.getTakeSeatCount() < 6) {
					response = given().post(
							"http://uat-rummy.khelplayrummy.com/RummyGameEngine/generateCheckEmail.action?email=harish.indouria@skilrock.com");
					String body = response.getBody().asString();
					System.out.println(body);
				} else if (GamePlayBean.getTakeSeatCount() > 6 && GamePlayBean.getTakeSeatCount() < 9) {
					response = given().post(
							"http://uat-rummy.khelplayrummy.com/RummyGameEngine/generateCheckingMessage.action?number=7891666000");
					String body = response.getBody().asString();
					System.out.println(body);
				} else if (GamePlayBean.getTakeSeatCount() > 9) {
					response = given()
							.post("http://uat-rummy.khelplayrummy.com/RummyGameEngine/generateCheckCall.action");
					String body = response.getBody().asString();
					System.out.println(body);
				}
			}

		} else if (findElement(WeaverLocators.vacantSeatPlayer2, 5).getText().contains("Take Seat")) {
			consecutiveTakeSeat(WeaverLocators.takeSeatPlayer2, WeaverLocators.vacantSeatPlayer2);
			if (flag == true) {
				System.out.println("table joined");
				GamePlayBean.setTakeSeatCount(0);

			} else if (flag == false) {
				consecutiveTakeSeat(WeaverLocators.takeSeatPlayer1, WeaverLocators.vacantSeatPlayer1);
				if (GamePlayBean.getTakeSeatCount() > 3 && GamePlayBean.getTakeSeatCount() < 6) {
					response = given().post(
							"http://uat-rummy.khelplayrummy.com/RummyGameEngine/generateCheckEmail.action?email=harish.indouria@skilrock.com");
					String body = response.getBody().asString();
					System.out.println(body);
				} else if (GamePlayBean.getTakeSeatCount() > 6 && GamePlayBean.getTakeSeatCount() < 9) {
					response = given().post(
							"http://uat-rummy.khelplayrummy.com/RummyGameEngine/generateCheckingMessage.action?number=7891666000");
					String body = response.getBody().asString();
					System.out.println(body);

				} else if (GamePlayBean.getTakeSeatCount() > 9) {
					response = given()
							.post("http://uat-rummy.khelplayrummy.com/RummyGameEngine/generateCheckCall.action");
					String body = response.getBody().asString();
					System.out.println(body);
				}
			}
		} else {
			System.out.println("User in watch mode");
		}

	}

	public boolean consecutiveTakeSeat(By takeSeatPlayer, By vacantSeatPlayer) {
		buttonClick(takeSeatPlayer, 5);
		flag = findElement(vacantSeatPlayer, 5).getText().contains(ConfigManager.getProperty("NickName"));
		GamePlayBean.setTakeSeatCount(GamePlayBean.getTakeSeatCount() + 1);
		return flag;

	}

}
