package stepdefinition.khelplay.mobile.ui;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khelplay.mobile.ui.ForgotPasswordPage;
import com.khelplay.mobile.ui.MobileHomePage;
import com.khelplay.mobile.ui.MobileLoginPage;

import cucumber.api.java.en.Then;

public class UserValidation {

	MobileLoginPage mobileLoginPage = (MobileLoginPage) MobileLoginPage.obj;
	ForgotPasswordPage forgotPasswordPage = (ForgotPasswordPage) ForgotPasswordPage.obj;
	MobileHomePage mobileHomePage;
	private static Logger logger = LoggerFactory.getLogger(UserValidation.class);
	private boolean flag;

	@Then("^User validates avatar icon is visible in app$")
	public void user_validates_Lobby_is_visible_in_app() throws Throwable {
		mobileHomePage = mobileLoginPage.clickLogin();
		if (mobileHomePage == null) {
			logger.error("User is not navigated to Lobby");
			Assert.fail();
		}
	}

	@Then("^User validates error message (.*) visible in app$")
	public void user_validates_Lobby_is_not_visible_in_app(String errormsg) throws Throwable {
		flag = mobileLoginPage.verifyInvalidLogin(errormsg);
		if (flag) {
			logger.info("User has not logged in with invalid credentials");
		} else {
			logger.warn("User has logged in with invalid credentials");
			Assert.fail();
		}
	}

	@Then("^User validates Login Screen is visible in App$")
	public void user_validates_Login_Screen_is_visible_in_app() throws Throwable {
		flag= forgotPasswordPage.verifyValidLogin();
		if (flag) {
			logger.info("Reset Password Link sent successfully with valid credentials");
		} else {
			logger.warn("Reset Password Link not sent successfully with valid credentials");
			Assert.fail();
		}
		
	}
	
	@Then("^User validates error messages (.*) visible in App$")
	public void user_validates_error_message_on_forgotPassword(String errormsg) throws Throwable{
		flag = forgotPasswordPage.verifyInvalidForgotPassword(errormsg);
		if (flag){
			logger.info("User not able to make forgot password request with invalid/blank email ID");
		} else {
			logger.warn("User is able to make forgot password request with invalid/blank email ID");
			Assert.fail();
		}
	}

}
