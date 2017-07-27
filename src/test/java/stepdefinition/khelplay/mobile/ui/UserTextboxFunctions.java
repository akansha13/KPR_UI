package stepdefinition.khelplay.mobile.ui;

import org.apache.log4j.Logger;

import com.khelplay.mobile.ui.ForgotPasswordPage;
import com.khelplay.mobile.ui.MobileDrawerPage;
import com.khelplay.mobile.ui.MobileLoginPage;
import com.khelplay.mobile.ui.MobileRegistrationPage;
import com.khelplay.utils.ConfigManager;

import cucumber.api.java.en.When;

public class UserTextboxFunctions {

	MobileLoginPage mobileLoginPage = (MobileLoginPage) MobileLoginPage.obj;
	ForgotPasswordPage forgotPasswordPage = (ForgotPasswordPage) ForgotPasswordPage.obj;
	MobileRegistrationPage mobileRegistrationPage = (MobileRegistrationPage) MobileRegistrationPage.obj;
	MobileDrawerPage mobileDrawerPage = (MobileDrawerPage) MobileDrawerPage.obj;
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

	@When("^User enters valid Registration details and sets Nickname$")
	public void user_enters_valid_Registration_details_and_sets_Nickname() throws Throwable {
		mobileRegistrationPage.generateUsername();
		mobileRegistrationPage.Password(ConfigManager.getProperty("Password"));
		mobileRegistrationPage.generateEmailID();
		mobileRegistrationPage.generateMobileNo();
	}

	@When("^User enters invalid/blank Username (.*)$")
	public void User_enters_invalid_blank_Username(String Username) throws Throwable {
		mobileRegistrationPage.Username(Username);
	}

	@When("^User enters invalid/blank Password (.*)$")
	public void User_enters_invalid_blank_Password(String password) throws Throwable {
		mobileRegistrationPage.Password(password);
	}

	@When("^User enters invalid/blank Email Address (.*)$")
	public void User_enters_invalid_blank_EmailAddress(String emailAddress) throws Throwable {
		mobileRegistrationPage.emailAddress(emailAddress);
	}

	@When("^User enters invalid/blank Mobile Number (.*)$")
	public void User_enters_invalid_blank_MobileNumber(String mobileNumber) throws Throwable {
		mobileRegistrationPage.mobileNumber(mobileNumber);
	}
	
	@When("^User enters Old Password, New Password and Retype Password$")
	public void User_changes_Password() throws Throwable {
		mobileDrawerPage.OldPassword(ConfigManager.getProperty("Password"));
		mobileDrawerPage.NewPassword(ConfigManager.getProperty("NewPassword"));
		mobileDrawerPage.RetypePassword(ConfigManager.getProperty("NewPassword"));
		mobileDrawerPage.changePasswordOK();
	}
	
	@When("^User enters invalid/blank Old Password (.*)$")
	public void User_enters_invalid_blank_OldPassword(String oldpassword) throws Throwable {
		mobileDrawerPage.OldPassword(oldpassword);
	} 

	@When("^User enters invalid/blank New Password (.*)$")
	public void User_enters_invalid_blank_NewPassword(String newpassword) throws Throwable {
		mobileDrawerPage.NewPassword(newpassword);
	} 
	
	@When("^User enters invalid/blank Retype Password (.*)$")
	public void User_enters_invalid_blank_RetypePassword(String retypepassword) throws Throwable {
		mobileDrawerPage.RetypePassword(retypepassword);
	} 
}