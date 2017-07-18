package stepdefinition.khelplay.mobile.ui;

import org.apache.log4j.Logger;

import com.khelplay.mobile.ui.ForgotPasswordPage;
import com.khelplay.mobile.ui.MobileLoginPage;
import com.khelplay.utils.ConfigManager;

import cucumber.api.java.en.When;

public class UserTextboxFunctions {

	MobileLoginPage mobileLoginPage = (MobileLoginPage) MobileLoginPage.obj;
	ForgotPasswordPage forgotPasswordPage = (ForgotPasswordPage) ForgotPasswordPage.obj;
	public static Logger logger = Logger.getLogger(UserTextboxFunctions.class);

	@When("^User enters valid app credentials in app$")
	public void user_enters_valid_app_credentials_are_entered_in_app() throws Throwable {
		try {
			mobileLoginPage.username(ConfigManager.getProperty("Username"));
			mobileLoginPage.password(ConfigManager.getProperty("Password"));
		} catch (Exception e) {
			logger.warn("Excpetion in Valid App Credentials" + e);
		}
	}

	@When("^User enters invalid Username (.*)$")
	public void User_enters_invalid_Username(String username) throws Throwable {
		mobileLoginPage.username(username);
	}

	@When("^User enters invalid Password (.*)$")
	public void User_enters_invalid_Password(String password) throws Throwable {
		mobileLoginPage.password(password);
	}
	
	@When("^User enters valid email ID in App$")
	public void user_enters_valid_email_id_in_app() throws Throwable {
		try {
			forgotPasswordPage.email(ConfigManager.getProperty("Email"));
		} catch (Exception e) {
			logger.warn("Exception in Email ID" + e);
		}
	}

	@When("^User enters blank/invalid email ID in App (.*)$")
	public void User_enters_invalid_email(String email) throws Throwable {
		forgotPasswordPage.email(email);
	}
	
	
	
	
	
}