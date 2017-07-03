package stepdefinition.khelplay.mobile.ui;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khelplay.mobile.ui.MobileHomePage;
import com.khelplay.mobile.ui.MobileLoginPage;

import cucumber.api.java.en.Then;

public class UserValidation {

	MobileLoginPage mobileLoginPage = (MobileLoginPage) MobileLoginPage.obj;
	MobileHomePage mobileHomePage;
	Logger logger = LoggerFactory.getLogger(UserValidation.class);

	@Then("^User validates Lobby is visible in app$")
	public void user_validates_Lobby_is_visible_in_app() throws Throwable {
		mobileHomePage = mobileLoginPage.clickLogin();
		if (mobileHomePage == null) {
			logger.error("User is not navigated to Lobby");
			Assert.fail();
		}
	}

	@Then("^User validates Lobby is not visible in app$")
	public void user_validates_Lobby_is_not_visible_in_app() throws Throwable {
		mobileHomePage = mobileLoginPage.clickLogin();
		if (mobileLoginPage != null) {
			logger.error("User is not navigated to Lobby");
			Assert.fail();
		}
	}

}
