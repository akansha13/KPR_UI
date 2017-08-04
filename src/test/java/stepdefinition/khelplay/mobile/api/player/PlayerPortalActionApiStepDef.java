package stepdefinition.khelplay.mobile.api.player;

import java.util.HashMap;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khelplay.mobile.api.player.PlayerPortalActionsAPI;
import com.khelplay.utils.ConfigManager;

import cucumber.api.java.en.Given;

public class PlayerPortalActionApiStepDef {
	
	PlayerPortalActionsAPI playerportalaction = new PlayerPortalActionsAPI();
	private static Logger LOGGER = LoggerFactory.getLogger(PlayerPortalActionApiStepDef.class);
	HashMap<String, String> preloginandRegapidata = new HashMap<String, String>();

	@Given("^player login successfully$")
	public void callPlayerLoginAPI() throws Throwable {
		String User = ConfigManager.getProperty("Username");
		String pass = ConfigManager.getProperty("PassWord");

		preloginandRegapidata = playerportalaction.callPlayerLogin(User, pass);
		if (!preloginandRegapidata.isEmpty()) {
			LOGGER.info("call Player login API Pass");
		} else {
			Assert.fail("call Player login API fail");
		}
	}

}
