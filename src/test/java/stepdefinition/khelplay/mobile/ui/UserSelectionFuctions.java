package stepdefinition.khelplay.mobile.ui;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khelplay.mobile.ui.InstantPlayPage;
import com.khelplay.mobile.ui.MobileHomePage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UserSelectionFuctions {
	MobileHomePage homePage = (MobileHomePage) MobileHomePage.obj;
	InstantPlayPage instantPlayPage = (InstantPlayPage) InstantPlayPage.obj;
	private static Logger logger = LoggerFactory.getLogger(UserSelectionFuctions.class);

	
	@Given("^User selects (.*) game module app icon$")
	public void select_game_module(String gameModule) throws Throwable {
		instantPlayPage = homePage.selectInstantPlay();
		if (instantPlayPage == null) {
			logger.error("User is not navigated to instant Play Page");
			Assert.fail();
		}
	}
	@When("^User selects take seat on table$")
	public void select_takeseat_module() throws Throwable {
		instantPlayPage.selectTakeSeat();
		if (instantPlayPage == null) {
			logger.error("seat taken");
			Assert.fail();
		}
	}
	@When("^User selects cards data from table$")
	public void select_carddata_module() throws Throwable {
		instantPlayPage.selectCardData();
		if (instantPlayPage == null) {
			logger.error("seat taken");
			Assert.fail();
		}
	}
	/*@When("^User selects cards image data from table$")
	public void select_card_imagedata_module() throws Throwable {
		instantPlayPage.selectCardImageData();
		if (instantPlayPage == null) {
			logger.error("seat taken");
			Assert.fail();
		}
	}*/
	@When("^User selects sort cards on table$")
	public void select_card_sort_module() throws Throwable {
		instantPlayPage.selectSortCard();
		if (instantPlayPage == null) {
			logger.error("seat taken");
			Assert.fail();
		}
	}
	@Then("^User selects leave table on table$")
	public void select_drop_module() throws Throwable {
		instantPlayPage.selectLeaveTable();
		if (instantPlayPage == null) {
			logger.error("seat taken");
			Assert.fail();
		}
	}
	
	@When("^User select card from closed deck$")
	public void select_sort_module() throws Throwable {
		instantPlayPage.selectClosedCard();
		if (instantPlayPage == null) {
			logger.error("seat taken");
			Assert.fail();
		}
	}

}
