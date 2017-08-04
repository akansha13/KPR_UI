package com.khelplay.mobile.api.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khelplay.DataBaseQueries.DBConnection;
import com.khelplay.DataBaseQueries.PlayerPortalActionDB;
import com.khelplay.utils.GeneralMethods;




public class PlayerPortalActionsApiCommonValidationPage {
	
	private static Logger LOGGER = LoggerFactory.getLogger(PlayerPortalActionsApiCommonValidationPage.class);

	public boolean isVerifyLoginResponseWithDB(HashMap<String, String> apidata) throws Exception {
		HashMap<String, String> dbdata = new HashMap<String, String>();
		PlayerPortalActionDB playerDbquery = new PlayerPortalActionDB();
		List<HashMap<String, String>> dataList = DBConnection.getInstance()
				.ExecuteQuery("rummy",playerDbquery.loginActionQuery);
		dbdata = dataList.size() == 0 ? new HashMap<>() : dataList.get(0);
		if (!dbdata.isEmpty())
			return GeneralMethods.validationWithDb(apidata, dbdata);
		else
			return false;
	}

	public HashMap<String, String> requiredValueForLogin(String user, String alias_name) throws Exception {
		HashMap<String, String> dbdata = new HashMap<String, String>();
		PlayerPortalActionDB playerDbquery = new PlayerPortalActionDB();
		List<HashMap<String, String>> dataList = DBConnection.getInstance()
				.ExecuteQuery("rummy",playerDbquery.loginActionRequiredData, user, alias_name);
		dbdata = dataList.size() == 0 ? new HashMap<>() : dataList.get(0);
		return dbdata;
	}

	public boolean isVerifyRegistrationResponseWithDB(HashMap<String, String> apidata) throws Exception {
		HashMap<String, String> dbdata = new HashMap<String, String>();
		PlayerPortalActionDB playerDbquery = new PlayerPortalActionDB();
		List<HashMap<String, String>> dataList = DBConnection.getInstance().ExecuteQuery("rummy",playerDbquery.regActionQuery);
		dbdata = dataList.size() == 0 ? new HashMap<>() : dataList.get(0);
		if (!dbdata.isEmpty())
			return GeneralMethods.validationWithDb(apidata, dbdata);
		else
			return false;
	}

	public HashMap<String, String> requiredValueForRegistration(String user, String mobile_no, String email_id,String Alias)
			throws Exception {
		HashMap<String, String> dbdata = new HashMap<String, String>();
		PlayerPortalActionDB playerDbquery = new PlayerPortalActionDB();
		List<HashMap<String, String>> dataList = DBConnection.getInstance()
				.ExecuteQuery("rummy",playerDbquery.registrationActionRequiredData, user, mobile_no, email_id,Alias);
		dbdata = dataList.size() == 0 ? new HashMap<>() : dataList.get(0);
		return dbdata;
	}

	public boolean isVerifyCheckLoginResponseWithDB(HashMap<String, String> apidata) throws Exception {
		HashMap<String, String> dbdata = new HashMap<String, String>();
		PlayerPortalActionDB playerDbquery = new PlayerPortalActionDB();
		List<HashMap<String, String>> dataList = DBConnection.getInstance()
				.ExecuteQuery("rummy",playerDbquery.checkLoginActionQuery);
		dbdata = dataList.size() == 0 ? new HashMap<>() : dataList.get(0);
		if (!dbdata.isEmpty())
			return GeneralMethods.validationWithDb(apidata, dbdata);
		else
			return false;
	}

	public boolean isVerifyFetchHeaderInfoResponseWithDB(HashMap<String, String> apiResponseData) throws Exception {
		HashMap<String, String> dbDataMap_wallet = new HashMap<String, String>();
		HashMap<String, String> dbDataMap_bonus = new HashMap<String, String>();
		HashMap<String, String> dbDataMap_loyalty = new HashMap<String, String>();
		PlayerPortalActionDB playerDbquery = new PlayerPortalActionDB();
		List<HashMap<String, String>> dataList1 = DBConnection.getInstance()
				.ExecuteQuery("rummy",playerDbquery.fetchHeaderInfo_walletQuery);
		dbDataMap_wallet = dataList1.size() == 0 ? new HashMap<>() : dataList1.get(0);
		List<HashMap<String, String>> dataList2 = DBConnection.getInstance()
				.ExecuteQuery("rummy",playerDbquery.fetchHeaderInfo_bonusQuery);
		dbDataMap_bonus = dataList2.size() == 0 ? new HashMap<>() : dataList2.get(0);
		List<HashMap<String, String>> dataList3 = DBConnection.getInstance()
				.ExecuteQuery("rummy",playerDbquery.fetchHeaderInfo_loyaltyQuery);
		dbDataMap_loyalty = dataList3.size() == 0 ? new HashMap<>() : dataList3.get(0);
		if (!apiResponseData.get("bonusBarInfo").equalsIgnoreCase("0")) {
			String[] value = apiResponseData.get("bonusBarInfo").split("\\|");
			apiResponseData.put("Tbonus", value[1]);
			apiResponseData.put("Rbonus", value[2]);
		} else {
			apiResponseData.put("Tbonus", "0");
			apiResponseData.put("Rbonus", "0");
		}
		if (!GeneralMethods.validationWithDb(apiResponseData, dbDataMap_wallet))
			return false;
		else if (!GeneralMethods.validationWithDb(apiResponseData, dbDataMap_loyalty))
			return false;
		else if (!GeneralMethods.validationWithDb(apiResponseData, dbDataMap_bonus))
			return false;
		else
			return true;
	}

	public String getVerificationCodeandPlayerID() throws Exception {

		HashMap<String, String> dbdata = new HashMap<String, String>();
		PlayerPortalActionDB playerDbquery = new PlayerPortalActionDB();
		List<HashMap<String, String>> dataList = DBConnection.getInstance().ExecuteQuery("rummy",playerDbquery.IdAndCodeQuery);
		dbdata = dataList.size() == 0 ? new HashMap<>() : dataList.get(0);
		if (!dbdata.isEmpty())
			return dbdata.get("player_id") + "_" + dbdata.get("email_verification_code");
		else
			return null;
	}

	public boolean validateAccountActivationLink(HashMap<String, String> apidata) throws Exception {
		HashMap<String, String> dbdata = new HashMap<String, String>();
		PlayerPortalActionDB playerDbquery = new PlayerPortalActionDB();
		List<HashMap<String, String>> dataList = DBConnection.getInstance()
				.ExecuteQuery("rummy",playerDbquery.accountActivationQuery);
		dbdata = dataList.size() == 0 ? new HashMap<>() : dataList.get(0);
		if (!dbdata.isEmpty())
			return GeneralMethods.validationWithDb(apidata, dbdata);
		else
			return false;
	}

	public boolean validateGetPostLoginData(ArrayList<Object> apiDataList) throws Exception {
		boolean flag = false;
		if (!apiDataList.isEmpty()) {
			HashMap<String, String> dbMap_balTier = new HashMap<String, String>();
			PlayerPortalActionDB playerDbquery = new PlayerPortalActionDB();
			List<HashMap<String, String>> dataList = DBConnection.getInstance()
					.ExecuteQuery("rummy",playerDbquery.getPostLoginData_tournamentQuery);
			List<HashMap<String, String>> dataList1 = DBConnection.getInstance()
					.ExecuteQuery("rummy",playerDbquery.getPostLoginData_balanceTier);
			dbMap_balTier = dataList1.size() == 0 ? new HashMap<>() : dataList1.get(0);
			if (dbMap_balTier.isEmpty()) {
				dbMap_balTier.put("tierName", "BRONZE");
				dbMap_balTier.put("loyaltyBalance", "0");
			}
			dataList.add(dbMap_balTier);

			if (dataList.size() == apiDataList.size()) {
				if (!dataList.isEmpty()) {
					for (int i = 0; i < dataList.size(); i++) {
						HashMap<String, String> dbData = new HashMap<String, String>(dataList.get(i));
						HashMap<String, String> apiData = new HashMap<String, String>(
								(HashMap<String, String>) apiDataList.get(i));
						flag = GeneralMethods.validationWithDb(apiData, dbData);
						System.out.println("--------------------------");
						if (flag) {
							continue;
						} else {
							break;
						}
					}

				} else {
					flag = true;
					LOGGER.info("API data and DB data size equal but both are empty");
				}
			} else {
				flag = false;
				LOGGER.info("API data and DB data size not equal");
			}

		} else {
			flag = true;
		}

		return flag;

	}

	public HashMap<String, String> getPasswordVerificationCodeandPlayerID(String emailId,String alias) throws Exception {
		HashMap<String, String> dbdata = new HashMap<String, String>();
		PlayerPortalActionDB playerDbquery = new PlayerPortalActionDB();
		List<HashMap<String, String>> dataList = DBConnection.getInstance()
				.ExecuteQuery("rummy",playerDbquery.PasswordIdAndCodeAndDate, emailId,alias);
		dbdata = dataList.size() == 0 ? new HashMap<>() : dataList.get(0);
		return dbdata;

	}

	public HashMap<String, String> checkEmailExistence(String emailId,String alias) throws Exception {
		HashMap<String, String> dbdata = new HashMap<String, String>();
		PlayerPortalActionDB playerDbquery = new PlayerPortalActionDB();
		List<HashMap<String, String>> dataList = DBConnection.getInstance().ExecuteQuery("rummy",playerDbquery.emailExist,
				emailId,alias);
		dbdata = dataList.size() == 0 ? new HashMap<>() : dataList.get(0);
		return dbdata;

	}

	public HashMap<String, String> validatePlrSession() throws Exception {
		HashMap<String, String> dbdata = new HashMap<String, String>();
		PlayerPortalActionDB playerDbquery = new PlayerPortalActionDB();
		List<HashMap<String, String>> dataList = DBConnection.getInstance()
				.ExecuteQuery("rummy",playerDbquery.ValidatePlrSessionQuery);
		dbdata = dataList.size() == 0 ? new HashMap<>() : dataList.get(0);
		return dbdata;

	}


}
