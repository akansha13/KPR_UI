package stepdefinition.khelplay.mobile.ui;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khelplay.mobile.ui.CashGamePlayPage;
import com.khelplay.mobile.ui.InstantPlayPage;
import com.khelplay.mobile.ui.MobileHomePage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UserSelectionFuctions {
	MobileHomePage homePage = (MobileHomePage) MobileHomePage.obj;
	InstantPlayPage instantPlayPage = (InstantPlayPage) InstantPlayPage.obj;
	CashGamePlayPage cashGamePlayPage = (CashGamePlayPage) CashGamePlayPage.obj;
	private static Logger logger = LoggerFactory.getLogger(UserSelectionFuctions.class);

	@Given("^User selects (.*) game module app icon$")
	public void select_game_module(String gameModule) throws Throwable {
		if (gameModule.contains("InstantPlay")) {
			instantPlayPage = homePage.selectInstantPlay();
			if (instantPlayPage == null) {
				logger.error("User is not navigated to instant Play Page");
				Assert.fail();
			}
			InstantPlayPage.obj = instantPlayPage;
		}
	}

	@Given("^User selects (.*) game module for (.*) game type app icon$")

	public void select_game_module_cash(String gameModule, String gameType) throws Throwable {
		if (gameModule.contains("CashGames")) {
			cashGamePlayPage = homePage.selectCashGames(gameType);
			if (cashGamePlayPage == null) {
				logger.error("User is not navigated to cash Game Play Page");
				Assert.fail();
			}
			CashGamePlayPage.obj = cashGamePlayPage;
		}
	}
	
	@When("^User selects take seat on table and validates seat is taken by player$")
	public void select_take_seat_module_validate() throws Throwable {
		cashGamePlayPage.selectTakeSeatandValidate();
	}

	@Given("^User selects (.*) and (.*) table$")
	public void select_table_type(String TableType, String PlayerType) throws Throwable {
		cashGamePlayPage.selectTableType( TableType,  PlayerType);
	}
	@Then("^User selects leave table on table for (.*)$")
	public void select_drop_module(String gameType) throws Throwable {
		cashGamePlayPage.selectLeaveTable();
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

	@When("^(.*) groups his cards as (.*)$")
	public void select_group_card(String Player, String group) throws Throwable {
		instantPlayPage.groupCard(Player, group);
	}

	@When("^(.*) picks card from closed deck and discard (.*)$")
	public void select_closed_deck_card_discard(String Player, String discardCard) throws Throwable {
		instantPlayPage.discardCard(Player, discardCard);
	}

	@When("^(.*) picks card from closed deck$")
	public void select_closed_deck_card(String Player) throws Throwable {
		instantPlayPage.closedDeck(Player);
	}

	@When("^(.*) places show (.*)$")
	public void places_show(String Player, String showCard) throws Throwable {
		instantPlayPage.placeShow(Player, showCard);
	}

}
