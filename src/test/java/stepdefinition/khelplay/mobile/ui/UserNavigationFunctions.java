package stepdefinition.khelplay.mobile.ui;

import org.apache.log4j.Logger;
import org.junit.Assert;

import com.khelplay.mobile.ui.ForgotPasswordPage;
import com.khelplay.mobile.ui.InstantPlayPage;
import com.khelplay.mobile.ui.MobileDrawerPage;
import com.khelplay.mobile.ui.MobileHomePage;
import com.khelplay.mobile.ui.MobileLoginPage;
import com.khelplay.mobile.ui.MobileRegistrationPage;
import com.khelplay.utils.ConfigManager;

import cucumber.api.java.en.Given;
import stepdefinition.AttachHooks;

public class UserNavigationFunctions {
	MobileLoginPage mobileLoginPage;
	public MobileHomePage mobileHomePage;
	ForgotPasswordPage forgotPasswordPage;
	MobileRegistrationPage mobileRegistrationPage;
	MobileDrawerPage mobileDrawerPage;
	InstantPlayPage instantPlayPage;
	private static Logger logger = Logger.getLogger(UserNavigationFunctions.class);

	@Given("^User navigates to login popup$")
	public void user_navigates_to_login_popup() throws Throwable {
		try {
			mobileLoginPage = new MobileLoginPage(AttachHooks.driver);
		} catch (Exception e) {
			logger.warn("Exception Occured:" + e);
			logger.error("User is not navigated to login pop up");
			Assert.fail();
		}
		MobileLoginPage.obj = mobileLoginPage;

	}

	@Given("^User is logged in app$")
	public void user_is_logged_in() throws Throwable {
		try {
			mobileLoginPage = new MobileLoginPage(AttachHooks.driver);
		} catch (Exception e) {
			logger.warn("Exception Occured:" + e);
			logger.error("User is not navigated to Login");
			Assert.fail();
		}
		mobileLoginPage.username(ConfigManager.getProperty("Username"));
		mobileLoginPage.password(ConfigManager.getProperty("Password"));
		mobileHomePage = mobileLoginPage.clickLogin();
		if (mobileHomePage == null) {
			logger.error("User is not navigated to Home Page");
			Assert.fail();
		}
		MobileHomePage.obj = mobileHomePage;
	}

	@Given("^User navigates to Forgot Password$")
	public void user_navigates_to_forgot_password() throws Throwable {
		try {
			forgotPasswordPage = new ForgotPasswordPage(AttachHooks.driver);
		} catch (Exception e) {
			logger.warn("Exception Occured:" + e);
			logger.error("User is not navigated to Forgot Password");
			Assert.fail();
		}
		ForgotPasswordPage.obj = forgotPasswordPage;
	}

	@Given("^User navigates to Registration page$")

	public void user_navigates_to_registration_page() throws Throwable {
		try {
			mobileRegistrationPage = new MobileRegistrationPage(AttachHooks.driver);
		} catch (Exception e) {
			logger.warn("Exception Occured:" + e);
			logger.error("User is not navigated to Registration Page");
			Assert.fail();
		}
		MobileRegistrationPage.obj = mobileRegistrationPage;
	}

	@Given("^User navigates to Change Password page$")
	public void User_navigates_to_ChangePassword_page() throws Throwable {
		try {
			mobileDrawerPage = mobileHomePage.changePassword();
		} catch (Exception e) {
			logger.warn("Exception Occured:" + e);
			logger.error("User is not navigated to ChangePassword Page");
			Assert.fail();
		}
		MobileDrawerPage.obj = mobileDrawerPage;
	}
}
