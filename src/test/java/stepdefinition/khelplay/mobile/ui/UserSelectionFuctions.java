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
		InstantPlayPage.obj=instantPlayPage;
	}
	@When("^User selects take seat on table$")
	public void select_takeseat_module() throws Throwable {
		instantPlayPage.selectTakeSeat();
	}
	@When("^User selects cards data from table$")
	public void select_carddata_module() throws Throwable {
		instantPlayPage.selectCardData();
	}
	
	@When("^User selects sort cards on table$")
	public void select_card_sort_module() throws Throwable {
		instantPlayPage.selectSortCard();
	}
	@Then("^User selects leave table on table$")
	public void select_drop_module() throws Throwable {
		instantPlayPage.selectLeaveTable();
	}
	
	@When("^User select card from closed deck$")
	public void select_sort_module() throws Throwable {
		instantPlayPage.selectClosedCard();
	}

}
