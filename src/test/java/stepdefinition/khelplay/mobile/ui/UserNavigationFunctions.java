package stepdefinition.khelplay.mobile.ui;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khelplay.mobile.ui.MobileLoginPage;
import com.khelplay.utils.CommonFunctionLibrary;

import cucumber.api.java.en.Given;
import stepdefinition.AttachHooks;

public class UserNavigationFunctions {
	MobileLoginPage mobileLoginPage;
	CommonFunctionLibrary functionLibrary;
	
	private static Logger logger = LoggerFactory.getLogger(UserNavigationFunctions.class);
	
	
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
	
	
}
