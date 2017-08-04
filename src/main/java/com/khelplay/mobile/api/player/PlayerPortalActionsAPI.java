package com.khelplay.mobile.api.player;

import static com.jayway.restassured.RestAssured.given;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.restassured.response.Response;
import com.khelplay.objectrepository.mobile.AllApiURL;
import com.khelplay.utils.ConfigManager;
import com.khelplay.utils.GeneralMethods;
import com.khelplay.utils.MD5;


public class PlayerPortalActionsAPI {
	
	public Response response;
	public String resbody;
	public boolean flag;
	public boolean flag1;
	private static Logger LOGGER = LoggerFactory.getLogger(PlayerPortalActionsAPI.class);
	PlayerPortalActionsApiCommonValidationPage portalcommomvalidation = new PlayerPortalActionsApiCommonValidationPage();

	/*
	 * partialRegistration playerRegistration playerLogin playerLogout
	 * checkLogin forgotPassword changePassword resetPassword getPostLoginData
	 * resetPasswordLink validatePlrSession accountActivationLink
	 * fetchHeaderInfo
	 */
	public HashMap<String, String> callPlayerLogin(String User, String pass) {
		HashMap<String, String> temp = new HashMap<String, String>();
		try {
			flag = false;
			String loginToken = "khelplay";
			String passMd5 = MD5.md5(pass);
			String Password = MD5.md5(passMd5.concat(loginToken));
			String url = ConfigManager.getProperty("ApiURL") + AllApiURL.loginApiURL;

			String loginJsonData = GeneralMethods.concateString("{\"userName\":\"", User, "\",", "\"password\":\"",
					Password, "\",", "\"userAgent\":\"", ConfigManager.getProperty("login.userAgent"), "\",",
					"\"requestIp\":\"", ConfigManager.getProperty("login.requestIp"), "\",", "\"loginToken\":\"",
					loginToken, "\",", "\"imeiNo\":\"", ConfigManager.getProperty("login.imeiNo"), "\",",
					"\"domainName\":\"", ConfigManager.getProperty("login.aliasName"), "\",", "\"deviceType\":\"",
					ConfigManager.getProperty("login.deviceType"), "\",", "\"deviceId\":\"",
					ConfigManager.getProperty("login.deviceId"), "\",", "\"currAppVer\":\"",
					ConfigManager.getProperty("login.currAppVer"), "\",", "\"appType\":\"",
					ConfigManager.getProperty("login.appType"), "\"}\n");
			response = given().contentType("application/json").body(loginJsonData).when().post(url);
			resbody = response.getBody().asString();
			int statuscode = response.getStatusCode();
			if (statuscode == 200) {
				int errorcode = response.jsonPath().getInt("errorCode");
				if (errorcode == 0) {
					String playerToken = response.jsonPath().get("playerToken").toString();
					if (!playerToken.isEmpty()) {
						temp = GeneralMethods.jsonToMap(resbody);
						PlayerPortalActionsAPIBean.setPlayerId(temp.get("playerId"));
						PlayerPortalActionsAPIBean.setUserName(temp.get("userName"));
						PlayerPortalActionsAPIBean.setDomainName(temp.get("domainName"));
						PlayerPortalActionsAPIBean.setPlayerToken(temp.get("playerToken"));
						PlayerPortalActionsAPIBean.setMobileNo(temp.get("mobileNo"));
						PlayerPortalActionsAPIBean.setEmailId(temp.get("emailId"));
						PlayerPortalActionsAPIBean.setCashBalance(temp.get("cashBalance"));
						PlayerPortalActionsAPIBean.setPracticeBalance(temp.get("practiceBalance"));
						PlayerPortalActionsAPIBean.setPassword(pass);
						flag = true;
					} else {
						LOGGER.error("login response successfully but playerToken is not found");
						flag = false;
					}
				} else {
					if (errorcode == 406 || errorcode == 412 || errorcode == 405) {
						if (User.isEmpty() || pass.isEmpty()) {
							LOGGER.info("login response successfully but username or password fields are blank");
							flag = true;
						} else {
							HashMap<String, String> DbRequiredValue = new HashMap<String, String>();
							DbRequiredValue = portalcommomvalidation.requiredValueForLogin(User,
									ConfigManager.getProperty("login.aliasName"));
							if (!DbRequiredValue.isEmpty()) {
								if (passMd5.equalsIgnoreCase(DbRequiredValue.get("password")) && errorcode == 406) {
									LOGGER.error("user and password are valid but error in response");
									flag = false;
								} else if ((errorcode == 412
										&& DbRequiredValue.get("plr_status").equalsIgnoreCase("Active"))
										|| (errorcode == 405
												&& DbRequiredValue.get("plr_status").equalsIgnoreCase("Active"))) {
									LOGGER.error(response.jsonPath().get("respMsg").toString());
									flag = false;
								} else {
									LOGGER.info(response.jsonPath().get("respMsg").toString());
									flag = true;
								}
							} else {
								LOGGER.info(response.jsonPath().get("respMsg").toString());
								flag = true;
							}
						}
					} else {
						LOGGER.error(response.jsonPath().get("respMsg").toString());
						flag = false;
					}
				}
			} else {
				LOGGER.error("Login API is not working");
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error: ", e);
		}

		return temp;

	}

}
