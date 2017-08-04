package com.khelplay.mobile.api.txns;

import static com.jayway.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.jayway.restassured.response.Response;
import com.khelplay.mobile.api.player.PlayerPortalActionsAPIBean;
import com.khelplay.objectrepository.mobile.AllApiURL;
import com.khelplay.utils.ConfigManager;
import com.khelplay.utils.GeneralMethods;

public class TransactionApiResponse {

	/*
	 * wager plrTxn txnStatus wagerAndWin fetchCapiInfo getBalance
	 */

	public Response response;
	public String resbody;
	public boolean flag;
	//TransactionApiResponseDBVerification txnDbValidation = new TransactionApiResponseDBVerification();
	private static Logger LOGGER = LoggerFactory.getLogger(TransactionApiResponse.class);

/*	public HashMap<String, String> callgetBalance() {
		HashMap<String, String> temp = new HashMap<String, String>();
		try {
			boolean refill = true;
			String url = ConfigManager.getProperty("ApiURL") + AllApiURL.getBalanceApiURL;
			String getbalanceJsonData = GeneralMethods.concateString("{\"domainName\":\"",
					ConfigManager.getProperty("login.aliasName"), "\",\"playerId\":",
					PlayerPortalActionsAPIBean.getPlayerId(), ",\"playerToken\":\"",
					PlayerPortalActionsAPIBean.getPlayerToken(), "\",\"walletType\":\"",
					ConfigManager.getProperty("getBalance.walletType"), "\",\"refill\":", refill, "}\n");
			response = given().contentType("application/json").body(getbalanceJsonData).when().post(url);
			resbody = response.getBody().asString();
			LOGGER.info(resbody);
			int statusCode = response.getStatusCode();
			if (statusCode == 200) {
				int errorCode = response.jsonPath().getInt("errorCode");
				if (errorCode == 0) {
					temp = GeneralMethods.jsonToMap(resbody);
				} else {
					LOGGER.error(response.jsonPath().get("respMsg").toString());
				}
			} else {
				LOGGER.error("GetBalance Api response failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error: ", e);

		}
		return temp;
	}

	public List<Object> callBounsDetaits(String fromDate, String toDate) {
		ArrayList<Object> apiResponseMap = new ArrayList<Object>();
		try {
			String url = ConfigManager.getProperty("ApiURL") + AllApiURL.bonusDetailsApiURL;
			String bonusDetailsJsonData = GeneralMethods.concateString("{\"domainName\":\"",
					ConfigManager.getProperty("login.aliasName"), "\",\"playerId\":",
					PlayerPortalActionsAPIBean.getPlayerId(), ",\"playerToken\":\"",
					PlayerPortalActionsAPIBean.getPlayerToken(), "\",\"limit\":\"",
					ConfigManager.getProperty("bonusDetails.limit"), "\",\"offset\":\"",
					ConfigManager.getProperty("bonusDetails.offset"), "\",\"fromDate\":\"", fromDate,
					"\",\"toDate\":\"", toDate, "\"}\n");
			response = given().contentType("application/json").body(bonusDetailsJsonData).when().post(url);
			resbody = response.getBody().asString();
			flag = false;
			LOGGER.info(resbody);
			int statusCode = response.getStatusCode();
			if (statusCode == 200) {
				int errorCode = response.jsonPath().getInt("errorCode");
				if (errorCode == 0) {
					flag = true;
					apiResponseMap = (ArrayList<Object>) response.jsonPath().getList("bonusList");
					if (apiResponseMap.isEmpty()) {
						apiResponseMap.add("No Bonus");
					}
				} else if (errorCode == 105) {
					flag = true;
					LOGGER.info(response.jsonPath().get("respMsg").toString());
				} else {
					flag = false;
					LOGGER.error(response.jsonPath().get("respMsg").toString());
				}
			} else {
				flag = false;
				LOGGER.error("fetchBonusDetails Api response failed");
			}
		} catch (

		Exception e) {
			e.printStackTrace();
			LOGGER.error("Error: ", e);

		}
		return apiResponseMap;
	}

	public List<Object> callWithdrawalDetails(String fromDate, String toDate) {
		ArrayList<Object> apiResponseMap = new ArrayList<Object>();
		try {
			String url = ConfigManager.getProperty("ApiURL") + AllApiURL.withdrawalDetailsApiURL;
			String withdrawalDetailsJsonData = GeneralMethods.concateString("{\"domainName\":\"",
					ConfigManager.getProperty("login.aliasName"), "\",\"playerId\":",
					PlayerPortalActionsAPIBean.getPlayerId(), ",\"playerToken\":\"",
					PlayerPortalActionsAPIBean.getPlayerToken(), "\",\"limit\":\"",
					ConfigManager.getProperty("withdrawalDetails.limit"), "\",\"offset\":\"",
					ConfigManager.getProperty("withdrawalDetails.offset"), "\",\"fromDate\":\"", fromDate,
					"\",\"toDate\":\"", toDate, "\"}\n");
			response = given().contentType("application/json").body(withdrawalDetailsJsonData).when().post(url);
			resbody = response.getBody().asString();
			flag = false;
			LOGGER.info(resbody);
			int statusCode = response.getStatusCode();
			if (statusCode == 200) {
				int errorCode = response.jsonPath().getInt("errorCode");
				if (errorCode == 0) {
					flag = true;
					apiResponseMap = (ArrayList<Object>) response.jsonPath().getList("withdrawalList");
					if (apiResponseMap.isEmpty()) {
						apiResponseMap.add("No Withdrawal");
					}
				} else if (errorCode == 105) {
					flag = true;
					LOGGER.info(response.jsonPath().get("respMsg").toString());
				} else {
					flag = false;
					LOGGER.error(response.jsonPath().get("respMsg").toString());
				}
			} else {
				flag = false;
				LOGGER.error("fetchWithdrawalDetails Api response failed");

			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error: ", e);

		}
		return apiResponseMap;
	}

	public List<Object> callTicketDetails(String fromDate, String toDate) {
		ArrayList<Object> apiResponseMap = new ArrayList<Object>();
		try {
			String txnType = "ticket";
			String url = ConfigManager.getProperty("ApiURL") + AllApiURL.ticketDetailsApiURL;
			String ticketDetailsJsonData = GeneralMethods.concateString("{\"domainName\":\"",
					ConfigManager.getProperty("login.aliasName"), "\",\"playerId\":",
					PlayerPortalActionsAPIBean.getPlayerId(), ",\"playerToken\":\"",
					PlayerPortalActionsAPIBean.getPlayerToken(), "\",\"limit\":\"",
					ConfigManager.getProperty("ticketDetails.limit"), "\",\"offset\":\"",
					ConfigManager.getProperty("ticketDetails.offset"), "\",\"txnType\":\"", txnType,
					"\",\"fromDate\":\"", fromDate, "\",\"toDate\":\"", toDate, "\"}\n");
			response = given().contentType("application/json").body(ticketDetailsJsonData).when().post(url);
			resbody = response.getBody().asString();
			flag = false;
			LOGGER.info(resbody);
			int statusCode = response.getStatusCode();
			if (statusCode == 200) {
				int errorCode = response.jsonPath().getInt("errorCode");
				if (errorCode == 0) {
					flag = true;
					apiResponseMap = (ArrayList<Object>) response.jsonPath().getList("ticketList");
					if (apiResponseMap.isEmpty()) {
						apiResponseMap.add("No Ticket");
					}
				} else if (errorCode == 105) {
					flag = true;
					LOGGER.info(response.jsonPath().get("respMsg").toString());
				} else {
					flag = false;
					LOGGER.error(response.jsonPath().get("respMsg").toString());
				}
			} else {
				flag = false;
				LOGGER.error("fetchWithdrawalDetails Api response failed");

			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error: ", e);

		}
		return apiResponseMap;
	}*/

	public ArrayList<Object> callTransactionDetails(String txnType, String fromDate, String toDate) {
		ArrayList<Object> apiResponseMap = new ArrayList<Object>();
		try {
			String url = ConfigManager.getProperty("ApiURL") + AllApiURL.transactionDetailsApiURL;
			String TransactionDetailsJsonData = GeneralMethods.concateString("{\"domainName\":\"",
					ConfigManager.getProperty("login.aliasName"), "\",\"playerId\":",
					PlayerPortalActionsAPIBean.getPlayerId(), ",\"playerToken\":\"",
					PlayerPortalActionsAPIBean.getPlayerToken(), "\",\"limit\":\"",
					ConfigManager.getProperty("transactionDetails.limit"), "\",\"offset\":\"",
					ConfigManager.getProperty("transactionDetails.offset"), "\",\"txnType\":\"", txnType,
					"\",\"fromDate\":\"", fromDate, "\",\"toDate\":\"", toDate, "\"}\n");
			response = given().contentType("application/json").body(TransactionDetailsJsonData).when().post(url);
			resbody = response.getBody().asString();
			flag = false;
			LOGGER.info(resbody);
			int statusCode = response.getStatusCode();
			if (statusCode == 200) {
				int errorCode = response.jsonPath().getInt("errorCode");
				if (errorCode == 0) {
					flag = true;
					Gson gson = new Gson();
					Map<String, Object> x = gson.fromJson(resbody, Map.class);
					if (txnType.equals("ticket")) {
						apiResponseMap = (ArrayList<Object>) x.get("ticketDetails");
						if (apiResponseMap.isEmpty()) {
							apiResponseMap.add("No data");
						}

					} else {
						apiResponseMap = (ArrayList<Object>) x.get("txnList");
						if (apiResponseMap.isEmpty()) {
							apiResponseMap.add("No data");
						}
					}
				} else if (errorCode == 105) {
					flag = true;
					LOGGER.info(response.jsonPath().get("respMsg").toString());
				} else {
					flag = false;
					LOGGER.info(response.jsonPath().get("respMsg").toString());
				}
			} else {
				flag = false;
				LOGGER.info("fetchTransactionDetails Api response failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error: ", e);

		}
		return apiResponseMap;
	}

	/*public HashMap<String, String> callWagerTxnAction(String gameName, String walletType, String balanceType,
			double amount, int rake_In_percentage) throws JSONException {
		HashMap<String, String> Apidata = new HashMap<String, String>();
		try {
			flag = false;
			String SubwagerRequest = GeneralMethods.concateString(
					"<playerTransactionRequest><vendorAuthenticationInfo><userName>",
					ConfigManager.getProperty("Txn_UserName"), "</userName><password>",
					ConfigManager.getProperty("Txn_Password"), "</password></vendorAuthenticationInfo><serviceCode>",
					ConfigManager.getProperty("Txn_serviceCode"), "</serviceCode><sessionId>",
					PlayerPortalActionsAPIBean.getPlayerToken(), "</sessionId><playerId>",
					PlayerPortalActionsAPIBean.getPlayerId(), "</playerId><aliasName>",
					ConfigManager.getProperty("login.aliasName"), "</aliasName><device>",
					ConfigManager.getProperty("login.deviceType"), "</device><platform>",
					ConfigManager.getProperty("Txn_platform"), "</platform><os>", ConfigManager.getProperty("Txn_os"),
					"</os><browser>", ConfigManager.getProperty("Txn_browser"), "</browser><model>",
					ConfigManager.getProperty("Txn_model"), "</model><transactionInfoList><walletType>", walletType,
					"</walletType><balanceType>", balanceType,
					"</balanceType><currencyCode>null</currencyCode><amount>", amount,
					"</amount></transactionInfoList><rake>", (amount / 100) * rake_In_percentage,
					"</rake><txnType>WAGER</txnType><wrContriAmount>", ConfigManager.getProperty("Txn_wrContriPer"),
					"</wrContriAmount><gameName>", gameName, "</gameName>");
			String wagerRequest = null;
			if (gameName.equalsIgnoreCase("POOL")) {
				wagerRequest = GeneralMethods.concateString(SubwagerRequest, "<gameId>",
						ConfigManager.getProperty("Txn_gameID"), "</gameId><gameType>",
						ConfigManager.getProperty("Txn_gameType"), "</gameType><subWalletRefNo>",
						ConfigManager.getProperty("Txn_gameID"), "</subWalletRefNo><particular>AutomationTesting for ",
						walletType, " ", gameName, " wager- ", ConfigManager.getProperty("Txn_gameID"),
						"</particular></playerTransactionRequest>");
			} else if (gameName.equalsIgnoreCase("POINT")) {
				wagerRequest = GeneralMethods.concateString(SubwagerRequest, "<gameId>",
						ConfigManager.getProperty("Txn_SubWalletgameID"), "</gameId><gameType>",
						ConfigManager.getProperty("Txn_SubWalletGameType"), "</gameType><subWalletRefNo>",
						ConfigManager.getProperty("Txn_SubWalletgameID"),
						"</subWalletRefNo><particular>AutomationTesting for ", walletType, " ", gameName,
						" SubWallet_Wager- ", ConfigManager.getProperty("Txn_SubWalletgameID"),
						"</particular><subWalletTime>", PlayerPortalActionsAPIBean.getSubWalletTime(),
						"</subWalletTime></playerTransactionRequest>");
			}

			String url = ConfigManager.getProperty("TxnActionURL") + "?reqData=" + wagerRequest;
			response = given().contentType("application/xml").get(url);
			resbody = response.getBody().asString();
			LOGGER.info(resbody);
			JSONObject jsonBody = GeneralMethods.xmlToJson(resbody);
			int statusCode = response.getStatusCode();
			LOGGER.info("Response Status Code is :- " + statusCode);
			if (statusCode == 200) {
				if (jsonBody.getJSONObject("playerTransactionResponse").optString("errorCode").isEmpty()) {
					flag = true;
					Apidata = GeneralMethods.jsonToMap(jsonBody);
				} else if (jsonBody.getJSONObject("playerTransactionResponse").optString("errorCode")
						.equalsIgnoreCase("209")) {
					if (walletType.equalsIgnoreCase("Cash")) {
						if (Double.parseDouble(PlayerPortalActionsAPIBean.getCashBalance()) <= amount) {
							flag = true;
							LOGGER.info(jsonBody.getJSONObject("playerTransactionResponse").optString("errorMsg"));
						} else {
							flag = false;
							LOGGER.info(jsonBody.getJSONObject("playerTransactionResponse").optString("errorMsg"));
						}
					} else if (walletType.equalsIgnoreCase("PRACTICE")) {
						if (Double.parseDouble(PlayerPortalActionsAPIBean.getPracticeBalance()) <= amount) {
							flag = true;
							LOGGER.info(jsonBody.getJSONObject("playerTransactionResponse").optString("errorMsg"));
						} else {
							flag = false;
							LOGGER.info(jsonBody.getJSONObject("playerTransactionResponse").optString("errorMsg"));
						}
					}
				} else {
					flag = false;
					LOGGER.info(jsonBody.getJSONObject("playerTransactionResponse").optString("errorMsg"));
				}
			} else {
				flag = false;
				LOGGER.info("Status of Wager Request is failed.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error: ", e);
		}
		return Apidata;
	}

	public HashMap<String, String> callWinningTxnAction(String gameName, String walletType, String balanceType,
			double amount) {
		HashMap<String, String> Apidata = new HashMap<String, String>();
		try {
			flag = false;
			String subWinningRequest = GeneralMethods.concateString(
					"<playerTransactionRequest><vendorAuthenticationInfo><userName>",
					ConfigManager.getProperty("Txn_UserName"), "</userName><password>",
					ConfigManager.getProperty("Txn_Password"), "</password></vendorAuthenticationInfo><serviceCode>",
					ConfigManager.getProperty("Txn_serviceCode"), "</serviceCode><sessionId>",
					PlayerPortalActionsAPIBean.getPlayerToken(), "</sessionId><playerId>",
					PlayerPortalActionsAPIBean.getPlayerId(), "</playerId><aliasName>",
					ConfigManager.getProperty("login.aliasName"), "</aliasName><device>",
					ConfigManager.getProperty("login.deviceType"), "</device><platform>",
					ConfigManager.getProperty("Txn_platform"), "</platform><os>", ConfigManager.getProperty("Txn_os"),
					"</os><browser>", ConfigManager.getProperty("Txn_browser"), "</browser><model>",
					ConfigManager.getProperty("Txn_model"), "</model><transactionInfoList><walletType>", walletType,
					"</walletType><balanceType>", balanceType,
					"</balanceType><currencyCode>null</currencyCode><amount>", amount,
					"</amount></transactionInfoList><txnType>WINNING</txnType><isWithdrawable>Y</isWithdrawable><gameName>",
					gameName, "</gameName>");
			String winningRequest = null;
			if (gameName.equalsIgnoreCase("POOL")) {
				winningRequest = GeneralMethods.concateString(subWinningRequest, "<gameId>",
						ConfigManager.getProperty("Txn_gameID"), "</gameId><gameType>",
						ConfigManager.getProperty("Txn_gameType"), "</gameType><subWalletRefNo>",
						ConfigManager.getProperty("Txn_gameID"), "</subWalletRefNo><particular>AutomationTesting for ",
						walletType, " ", gameName, " winning- ", ConfigManager.getProperty("Txn_gameID"),
						"</particular></playerTransactionRequest>");
			} else if (gameName.equalsIgnoreCase("POINT")) {
				winningRequest = GeneralMethods.concateString(subWinningRequest, "<gameId>",
						ConfigManager.getProperty("Txn_SubWalletgameID"), "</gameId><gameType>",
						ConfigManager.getProperty("Txn_SubWalletGameType"), "</gameType><subWalletRefNo>",
						ConfigManager.getProperty("Txn_SubWalletgameID"),
						"</subWalletRefNo><particular>AutomationTesting for ", walletType, " ", gameName,
						" SubWallet_Winning- ", ConfigManager.getProperty("Txn_SubWalletgameID"),
						"</particular><subWalletTime>", PlayerPortalActionsAPIBean.getSubWalletTime(),
						"</subWalletTime></playerTransactionRequest>");
			}

			String url = ConfigManager.getProperty("TxnActionURL") + "?reqData=" + winningRequest;
			response = given().contentType("application/xml").get(url);
			resbody = response.getBody().asString();
			LOGGER.info(resbody);
			JSONObject jsonBody = GeneralMethods.xmlToJson(resbody);
			int statusCode = response.getStatusCode();
			LOGGER.info("Response Status Code is :- " + statusCode);
			if (statusCode == 200) {
				if (jsonBody.getJSONObject("playerTransactionResponse").optString("errorCode").isEmpty()) {
					flag = true;
					Apidata = GeneralMethods.jsonToMap(jsonBody);
				} else {
					flag = false;
					LOGGER.info(jsonBody.getJSONObject("playerTransactionResponse").optString("errorMsg"));
				}

			} else {
				flag = false;
				LOGGER.info("Status of Winning Request is failed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error: ", e);
		}
		return Apidata;
	}

	public HashMap<String, String> callWagerRefundTxnAction(HashMap<String, String> ApiData) {
		HashMap<String, String> apiResponseData = new HashMap<String, String>();
		try {

			String Number = GeneralMethods.rendomNumberString(10);
			String refNumber = String.valueOf(Long.parseLong(Number));
			String wagerRefundRequest = GeneralMethods.concateString(
					"<playerTransactionRequest><vendorAuthenticationInfo><userName>",
					ConfigManager.getProperty("Txn_UserName"), "</userName><password>",
					ConfigManager.getProperty("Txn_Password"), "</password></vendorAuthenticationInfo><serviceCode>",
					ConfigManager.getProperty("Txn_serviceCode"), "</serviceCode><sessionId>",
					PlayerPortalActionsAPIBean.getPlayerToken(), "</sessionId><playerId>",
					PlayerPortalActionsAPIBean.getPlayerId(), "</playerId><aliasName>",
					ConfigManager.getProperty("login.aliasName"),
					"</aliasName><txnType>WAGER_REFUND</txnType><rake>0.0</rake><particular>AutomationTesting for ",
					ApiData.get("walletType"), " wager refund- ", ApiData.get("gameId"),
					"</particular><transactionInfoList><walletType>", ApiData.get("walletType"),
					"</walletType><balanceType>", ApiData.get("balanceType"),
					"</balanceType><currencyCode>null</currencyCode><amount>", ApiData.get("amount"),
					"</amount><refWagerTxnId>", ApiData.get("txnId"), "</refWagerTxnId><refTxnNo>", refNumber,
					"</refTxnNo></transactionInfoList></playerTransactionRequest>");
			String url = ConfigManager.getProperty("TxnActionURL") + "?reqData=" + wagerRefundRequest;
			response = given().contentType("application/xml").get(url);
			resbody = response.getBody().asString();
			LOGGER.info(resbody);
			JSONObject jsonBody = GeneralMethods.xmlToJson(resbody);
			String JsonBodyString = jsonBody.toString();
			int statusCode = response.getStatusCode();
			LOGGER.info("Response Status Code is :- " + statusCode);
			if (statusCode == 200) {
				int errorCode = jsonBody.optInt("errorCode");

				if (jsonBody.getJSONObject("playerTransactionResponse").optString("errorCode").isEmpty()) {
					flag = true;
					apiResponseData = GeneralMethods.jsonToMap(jsonBody);
				} else {
					flag = false;
					LOGGER.info(jsonBody.getJSONObject("playerTransactionResponse").optString("errorMsg"));
				}

			} else {
				flag = false;
				LOGGER.info("Status of Wager Refund Request is failed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error: ", e);
		}
		return apiResponseData;

	}

	public HashMap<String, String> callWagerConfirmTxnAction(HashMap<String, String> ApiData) {
		HashMap<String, String> apiResponseData = new HashMap<String, String>();
		try {
			String Baseurl = "https://dev.khelplayrummy.com/Weaver/portal/txn/plrConfirmWgrTxn?reqData=";
			String wagerConfirmRequest = GeneralMethods.concateString(
					"<playerTransactionRequest><vendorAuthenticationInfo><userName>",
					ConfigManager.getProperty("Txn_UserName"), "</userName><password>",
					ConfigManager.getProperty("Txn_Password"), "</password></vendorAuthenticationInfo><serviceCode>",
					ConfigManager.getProperty("Txn_serviceCode"),
					"</serviceCode><txnType>WAGER_CONFIRMATION</txnType><balanceType>", ApiData.get("balanceType"),
					"</balanceType><confirmWagerTxnBean><playerId>", PlayerPortalActionsAPIBean.getPlayerId(),
					"</playerId><aliasName>", ConfigManager.getProperty("login.aliasName"), "</aliasName><walletType>",
					ApiData.get("walletType"), "</walletType><refWagerTxnId>", ApiData.get("txnId"),
					"</refWagerTxnId><sessionId>", PlayerPortalActionsAPIBean.getPlayerToken(),
					"</sessionId><wrContriPer>", ConfigManager.getProperty("Txn_wrContriPer"),
					"</wrContriPer></confirmWagerTxnBean></playerTransactionRequest>");

			String url = Baseurl + wagerConfirmRequest;
			response = given().contentType("application/xml").get(url);
			resbody = response.getBody().asString();
			LOGGER.info(resbody);
			JSONObject jsonBody = GeneralMethods.xmlToJson(resbody);
			int statusCode = response.getStatusCode();
			LOGGER.info("Response Status Code is :- " + statusCode);
			if (statusCode == 200) {
				if (jsonBody.getJSONObject("playerTransactionResponse").optString("errorCode").isEmpty()) {
					flag = true;
					apiResponseData = GeneralMethods.jsonToMap(jsonBody);
				} else {
					flag = false;
					LOGGER.info(jsonBody.getJSONObject("playerTransactionResponse").optString("errorMsg"));
				}

			} else {
				flag = false;
				LOGGER.info("Status of Wager Confirm Request is failed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error: ", e);
		}
		return apiResponseData;

	}

	public HashMap<String, String> callTransferToSubWalletTxnAction(String gameName, String walletType,
			String balanceType, double amount, String isSubwallet) {
		HashMap<String, String> apidata = new HashMap<String, String>();
		try {
			String SubWalletTime_In_millisec = null;
			if ((isSubwallet.equalsIgnoreCase("YES") && walletType.equalsIgnoreCase("cash"))
					|| (isSubwallet.equalsIgnoreCase("YES") && walletType.equalsIgnoreCase("PRACTICE")))
				SubWalletTime_In_millisec = String.valueOf(System.currentTimeMillis());
			else if ((isSubwallet.equalsIgnoreCase("NO") && walletType.equalsIgnoreCase("cash"))
					|| (isSubwallet.equalsIgnoreCase("NO") && walletType.equalsIgnoreCase("PRACTICE")))
				SubWalletTime_In_millisec = PlayerPortalActionsAPIBean.getSubWalletTime();
			String TransferToSubWalletRequest = GeneralMethods.concateString(
					"<playerTransactionRequest><vendorAuthenticationInfo>", "<userName>",
					ConfigManager.getProperty("Txn_UserName"), "</userName><password>",
					ConfigManager.getProperty("Txn_Password"), "</password>",
					"</vendorAuthenticationInfo><serviceCode>", ConfigManager.getProperty("Txn_serviceCode"),
					"</serviceCode>", "<sessionId>", new PlayerPortalActionsAPIBean().getPlayerToken(), "</sessionId>",
					"<playerId>", PlayerPortalActionsAPIBean.getPlayerId(), "</playerId><aliasName>",
					ConfigManager.getProperty("login.aliasName"), "</aliasName>", "<gameId>",
					ConfigManager.getProperty("Txn_SubWalletgameID"), "</gameId>", "<gameType>",
					ConfigManager.getProperty("Txn_SubWalletGameType"), "</gameType><gameName>", gameName,
					"</gameName><subWalletRefNo>", ConfigManager.getProperty("Txn_SubWalletgameID"),
					"</subWalletRefNo><device>", ConfigManager.getProperty("login.deviceType"), "</device>",
					"<platform>", ConfigManager.getProperty("Txn_platform"), "</platform><os>",
					ConfigManager.getProperty("Txn_os"), "</os><browser>", ConfigManager.getProperty("Txn_browser"),
					"</browser>", "<model>", ConfigManager.getProperty("Txn_model"), "</model>", "<subWalletTime>",
					SubWalletTime_In_millisec, "</subWalletTime>", "<particular>AutomationTesting for ", walletType,
					" ", gameName, " Transfer_To_SubWallet- ", ConfigManager.getProperty("Txn_SubWalletgameID"),
					"</particular><txnType>TRANSFER_TO_SUBWALLET</txnType>", "<isNewSubWallet>", isSubwallet,
					"</isNewSubWallet><transactionInfoList><walletType>", walletType, "</walletType>", "<balanceType>",
					balanceType, "</balanceType><currencyCode>null</currencyCode><amount>", amount, "</amount>",
					"</transactionInfoList></playerTransactionRequest>");

			String url = ConfigManager.getProperty("TxnActionURL") + "?reqData=" + TransferToSubWalletRequest;
			response = given().contentType("application/xml").get(url);
			resbody = response.getBody().asString();
			LOGGER.info(resbody);
			JSONObject jsonBody = GeneralMethods.xmlToJson(resbody);
			int statusCode = response.getStatusCode();
			LOGGER.info("Response Status Code is :- " + statusCode);
			if (statusCode == 200) {
				if (jsonBody.getJSONObject("playerTransactionResponse").optString("errorCode").isEmpty()) {
					flag = true;
					apidata = GeneralMethods.jsonToMap(jsonBody);
					apidata.put("subwallet_creation_time",
							SubWalletTime_In_millisec + "-"
									+ jsonBody.getJSONObject("playerTransactionResponse").optString("playerId") + "-"
									+ jsonBody.getJSONObject("playerTransactionResponse").optString("gameId"));
					apidata.put("SubWalletTime_In_millisec", SubWalletTime_In_millisec);
					apidata.put("subwalletStatus", isSubwallet);
					apidata.put("gameName", gameName);
					PlayerPortalActionsAPIBean.setSubWalletTime(SubWalletTime_In_millisec);
				} else if (jsonBody.getJSONObject("playerTransactionResponse").optString("errorCode")
						.equalsIgnoreCase("209")) {
					if (walletType.equalsIgnoreCase("Cash")) {
						if (Double.parseDouble(PlayerPortalActionsAPIBean.getCashBalance()) <= amount) {
							flag = true;
							LOGGER.info(jsonBody.getJSONObject("playerTransactionResponse").optString("errorMsg"));
						} else {
							flag = false;
							LOGGER.info(jsonBody.getJSONObject("playerTransactionResponse").optString("errorMsg"));
						}
					} else if (walletType.equalsIgnoreCase("PRACTICE")) {
						if (Double.parseDouble(PlayerPortalActionsAPIBean.getPracticeBalance()) <= amount) {
							flag = true;
							LOGGER.info(jsonBody.getJSONObject("playerTransactionResponse").optString("errorMsg"));
						} else {
							flag = false;
							LOGGER.info(jsonBody.getJSONObject("playerTransactionResponse").optString("errorMsg"));
						}
					}
				} else {
					flag = false;
					LOGGER.info(jsonBody.getJSONObject("playerTransactionResponse").optString("errorMsg"));
				}
			} else {
				flag = false;
				LOGGER.info("Status of TransferTOSubwallet Request is failed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error: ", e);
		}
		return apidata;

	}

	public HashMap<String, String> callTransferFromSubWalletTxnAction(HashMap<String, String> apiData) {
		HashMap<String, String> Apidata = new HashMap<String, String>();
		try {
			String TransferFromSubWallet = GeneralMethods.concateString(
					"<playerTransactionRequest><vendorAuthenticationInfo>", "<userName>",
					ConfigManager.getProperty("Txn_UserName"), "</userName><password>",
					ConfigManager.getProperty("Txn_Password"), "</password>",
					"</vendorAuthenticationInfo><serviceCode>", ConfigManager.getProperty("Txn_serviceCode"),
					"</serviceCode>", "<sessionId>", PlayerPortalActionsAPIBean.getPlayerToken(), "</sessionId>",
					"<playerId>", PlayerPortalActionsAPIBean.getPlayerId(), "</playerId><aliasName>",
					ConfigManager.getProperty("login.aliasName"), "</aliasName>", "<gameId>",
					ConfigManager.getProperty("Txn_SubWalletgameID"), "</gameId><gameType>",
					ConfigManager.getProperty("Txn_SubWalletGameType"), "</gameType><gameName>",
					apiData.get("gameName"), "</gameName><subWalletRefNo>",
					ConfigManager.getProperty("Txn_SubWalletgameID"), "</subWalletRefNo><device>",
					ConfigManager.getProperty("login.deviceType"), "</device><platform>",
					ConfigManager.getProperty("Txn_platform"), "</platform><os>", ConfigManager.getProperty("Txn_os"),
					"</os><browser>", ConfigManager.getProperty("Txn_browser"), "</browser><model>",
					ConfigManager.getProperty("Txn_model"), "</model><subWalletTime>",
					PlayerPortalActionsAPIBean.getSubWalletTime(), "</subWalletTime><particular>AutomationTesting for ",
					apiData.get("walletType"), " ", apiData.get("gameName"), "Transfer_FROM_SubWallet- ",
					ConfigManager.getProperty("Txn_SubWalletgameID"),
					"</particular><txnType>TRANSFER_FROM_SUBWALLET</txnType><transactionInfoList><walletType>",
					apiData.get("walletType"), "</walletType><balanceType>", apiData.get("balanceType"),
					"</balanceType><currencyCode>null</currencyCode><amount>", apiData.get("amount"), "</amount>",
					"</transactionInfoList></playerTransactionRequest>");
			String url = ConfigManager.getProperty("TxnActionURL") + "?reqData=" + TransferFromSubWallet;
			response = given().contentType("application/xml").get(url);
			resbody = response.getBody().asString();
			LOGGER.info(resbody);
			JSONObject jsonBody = GeneralMethods.xmlToJson(resbody);
			int statusCode = response.getStatusCode();
			LOGGER.info("Response Status Code is :- " + statusCode);
			if (statusCode == 200) {
				if (jsonBody.getJSONObject("playerTransactionResponse").optString("errorCode").isEmpty()) {
					flag = true;
					Apidata = GeneralMethods.jsonToMap(jsonBody);
				} else {
					LOGGER.info(jsonBody.getJSONObject("playerTransactionResponse").optString("errorMsg"));
					flag = false;
				}

			} else {
				LOGGER.info("Status of TransferFromSubwallet Request is failed.");
				flag = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error: ", e);

		}

		return Apidata;
	}

	public HashMap<String, String> callPaymentCorrectionAction(String user, String correctionType, double amount,
			String withdrawalImpact, String reason) throws Exception {
		HashMap<String, String> apidata = new HashMap<String, String>();
		HashMap<String, String> dbreqdata = new HashMap<String, String>();
		try {
			dbreqdata = txnDbValidation.requiredDataForPaymentCorrection(user,
					ConfigManager.getProperty("login.aliasName"));
			if (!dbreqdata.isEmpty()) {
				flag = false;
				String paymentCorrection = GeneralMethods.concateString(
						"<playerTransactionRequest><vendorAuthenticationInfo>", "<userName>",
						ConfigManager.getProperty("Txn_UserName"), "</userName><password>",
						ConfigManager.getProperty("Txn_Password"), "</password>",
						"</vendorAuthenticationInfo><serviceCode>", ConfigManager.getProperty("Txn_serviceCode"),
						"</serviceCode>", "<playerId>", dbreqdata.get("player_id"), "</playerId><correctionType>",
						correctionType, "</correctionType><amount>", amount,
						"</amount><txnType>PAY_CORRECTION</txnType><withdrawableImpact>", withdrawalImpact,
						"</withdrawableImpact><particular>", reason, "</particular></playerTransactionRequest>");
				String url = ConfigManager.getProperty("PaymentCorrectionTxnURL") + "?reqData=" + paymentCorrection;
				response = given().body("application/xml").get(url);
				resbody = response.getBody().asString();
				LOGGER.info(resbody);
				JSONObject jsonBody = GeneralMethods.xmlToJson(resbody);
				int statusCode = response.getStatusCode();
				LOGGER.info("Response Status Code is :- " + statusCode);
				int StatusCode = response.getStatusCode();
				if (StatusCode == 200) {
					if (jsonBody.getJSONObject("playerTransactionResponse").optString("errorCode").isEmpty()) {
						flag = true;
						apidata = GeneralMethods.jsonToMap(jsonBody);
						apidata.put("correction_type", correctionType);
						apidata.put("withdrawableImpact", withdrawalImpact);
						apidata.put("reason", reason);
					} else if (jsonBody.getJSONObject("playerTransactionResponse").optString("errorCode")
							.equalsIgnoreCase("214")) {
						if (amount > Double.parseDouble(dbreqdata.get("cash_bal"))) {
							flag = true;
							LOGGER.info(jsonBody.getJSONObject("playerTransactionResponse").optString("errorMsg"));
						} else {
							flag = false;
							LOGGER.error(jsonBody.getJSONObject("playerTransactionResponse").optString("errorMsg"));
						}
					} else {
						flag = false;
						LOGGER.error(jsonBody.getJSONObject("playerTransactionResponse").optString("errorMsg"));
					}
				} else {
					LOGGER.info("Status of PaymentCorrection Request is failed.");
					flag = false;
				}

			} else {
				LOGGER.error("UserName Not In Our Database so please verify the UserName");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error: ", e);
		}

		return apidata;

	}

	public HashMap<String, String> callWagerApiForAllGameEngine(String gamename, String walletType, String balanceType,
		double amount) throws Exception {
		HashMap<String, String> Apidata = new HashMap<String, String>();
		try{
		HashMap<String, String> reqvandorInfo = txnDbValidation.requirdvandorInfo(gamename);
		
		if (reqvandorInfo.isEmpty())
			Assert.fail("Game Name Not avaiable for Weaver Txns");
		flag = false;
		
		String gameType = null;
		switch (gamename.toUpperCase()) {
		case "IGE":
			gameType = "INSTANT WIN";
		case "SGE":
			gameType = "SLOT";
		}
		String url = ConfigManager.getProperty("OtherGameApiURL") + AllApiURL.otherGamesWagerApiURL;
		String wagerApiForOther = GeneralMethods.concateString("{\"vendorAuthenticationInfo\":{\"password\":\"",
				reqvandorInfo.get("password"), "\",\"userName\":\"", reqvandorInfo.get("user_name"),
				"\"},\"serviceCode\":\"", reqvandorInfo.get("default_game_group"), "\",\"sessionId\":\"",
				PlayerPortalActionsAPIBean.getPlayerToken(), "\",\"playerId\":\"",
				PlayerPortalActionsAPIBean.getPlayerId(), "\",\"aliasName\":\"",
				ConfigManager.getProperty("login.aliasName"), "\",\"device\":\"",
				ConfigManager.getProperty("OtherTxnsDevice"), "\",\"deviceCheck\":\"true\",\"macAddress\":\"",
				ConfigManager.getProperty("OtherTxnsDaviceID"),
				"\",\"userAgent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML,like Gecko)Chrome/47.0.2526.106 Safari/537.36\",\"txnType\":\"WAGER\",\"transactionInfoList\":[{\"gameId\":",
				reqvandorInfo.get("group_id"), ",\"wrContriAmount\":\"100.00\",\"gameType\":\"", gameType,
				"\",\"amount\":", amount, ",\"balanceType\":\"", balanceType, "\",\"gameName\":\"", gamename,
				" games\",\"walletType\":\"", walletType,
				"\",\"refTxnNo\":\"5756149\",\"particular\":\"automation testing for ", gamename, " wager\"}]}");
		response = given().contentType("application/json").body(wagerApiForOther).when().post(url);
		resbody = response.getBody().asString();

		if (response.getStatusCode() == 200) {
			int errorcode=response.jsonPath().getInt("errorCode");
			if (errorcode == 0) {
				JSONObject jObject = new JSONObject(resbody.trim());
				String transactionInfoList = jObject.optJSONArray("transactionInfoList").get(0).toString();
				HashMap<String, String> txnsDetails = GeneralMethods.jsonToMap(transactionInfoList);
				flag = true;
				Apidata = GeneralMethods.jsonToMap(resbody);
				Apidata.putAll(txnsDetails);
				Apidata.remove("transactionInfoList");
				Apidata.put("vendorUserName", reqvandorInfo.get("user_name"));
				Apidata.put("vendorPassword", reqvandorInfo.get("password"));
				Apidata.put("vendorServiceCode", reqvandorInfo.get("default_game_group"));
				
				  Gson data = new GsonBuilder().create();
				   Type dataType = new TypeToken<OtherGamesWagerBean>(){}.getType();
				  OtherGamesWagerBean bean = data.fromJson(resbody, dataType);
				  System.out.println(bean);
				
			}else if(errorcode==290 || Double.parseDouble(PlayerPortalActionsAPIBean.getCashBalance()) <= amount)
			{		flag = true;
					LOGGER.info(response.jsonPath().getString("errorMsg"));
			}
			else {
				flag = false;
				LOGGER.info("errorCode: " + response.jsonPath().getInt("errorCode") + "\n errorMsg: "
						+ response.jsonPath().getString("errorMsg"));
			}
		} else {
			flag = false;
			LOGGER.error("API response Not Generated");
		}}catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error: ", e);
			
		}
		return Apidata;
	}

	public HashMap<String, String> callAfterWagerActionApiForOtherGames(HashMap<String, String> apiData,String afterWagerAction) 
	{	HashMap<String, String> Apidata=new HashMap<String,String>();
	try
	{	flag=false;
	String afterAction=null;
	if(afterWagerAction.contains("Confirm"))
		afterAction="WAGER_CONFIRMATION";
	else if(afterWagerAction.contains("Refund"))
		afterAction="WAGER_REFUND";
		String url = ConfigManager.getProperty("OtherGameApiURL") + AllApiURL.otherGamesAfterWagerEventApiURL;
		String AfterwagerActionForOtherGames=GeneralMethods.concateString("{\"vendorAuthenticationInfo\": {\"password\": \"",
				apiData.get("vendorPassword"),"\",\"userName\": \"",
				apiData.get("vendorUserName"),"\"},\"serviceCode\": \"",
				apiData.get("vendorServiceCode"),"\",\"txnType\": \"",afterAction,"\",\"playerTxnBean\": [{\"playerId\": \"",
				PlayerPortalActionsAPIBean.getPlayerId(),"\",\"aliasName\":\"",
				ConfigManager.getProperty("login.aliasName"),"\",\"walletType\": \"",
				apiData.get("walletType"),"\",\"refWagerTxnId\": \"",
				apiData.get("txnId"),"\",\"balanceType\": \"",
				apiData.get("balanceType"),"\",\"particular\":\"Automation Testing for After wager Actions \",\"gameId\": \"",
				apiData.get("gameId"),"\",\"gameName\": \"",apiData.get("gameName"),"\"}]}");
		
		response=given().contentType("application/json").body(AfterwagerActionForOtherGames).when().post(url);
		resbody=response.getBody().asString();
		LOGGER.info(resbody);
		int statusCode=response.getStatusCode();
		if(statusCode==200)
		{int errorCode=response.jsonPath().getInt("errorCode");
			if(errorCode==0)
			{JSONObject jsonObj=new JSONObject(resbody);
				int internalErrorCode=jsonObj.optJSONArray("plrWiseRespBean").optJSONObject(0).optInt("errorCode");
				if(internalErrorCode==0 || internalErrorCode==222)
				{
					Apidata=GeneralMethods.jsonToMap(jsonObj.optJSONArray("plrWiseRespBean").optJSONObject(0).toString());
					flag=true;
				}else if(internalErrorCode==201 && afterWagerAction.contains("Refund"))
				{
					Apidata=GeneralMethods.jsonToMap(jsonObj.optJSONArray("plrWiseRespBean").optJSONObject(0).toString());
					flag=true;
				}
				else
				{
					LOGGER.info("Player wise transaction "+afterWagerAction+" is failed");
					flag=true;
				}
			}
			else
			{
				LOGGER.info("Response of Wager "+afterWagerAction+" API is failed");
				flag=false;
			}
		}else
		{flag=false;
		LOGGER.error(" Wager "+afterWagerAction+" api not working");
		}
	}catch (Exception e) {
		e.printStackTrace();
		LOGGER.error("Error: ", e);
	}
		
		return Apidata;
	}

	public HashMap<String, String> callWinningApiActionForOtherGameEngine(HashMap<String, String> apiData,
			double winningAmount) {
	
		HashMap<String, String> Apidata=new HashMap<String,String>();
		try
		{	flag=false;
			String url = ConfigManager.getProperty("OtherGameApiURL") + AllApiURL.otherGamesAfterWagerEventApiURL;
			String WinningForOtherGames=GeneralMethods.concateString("{\"vendorAuthenticationInfo\": {\"password\": \"",
					apiData.get("vendorPassword"),"\",\"userName\": \"",
					apiData.get("vendorUserName"),"\"},\"serviceCode\": \"",
					apiData.get("vendorServiceCode"),"\",\"txnType\": \"WINNING\",\"playerTxnBean\": [{\"playerId\": \"",
					PlayerPortalActionsAPIBean.getPlayerId(),"\",\"aliasName\":\"",
					ConfigManager.getProperty("login.aliasName"),"\",\"walletType\": \"",
					apiData.get("walletType"),"\",\"refTxnNo\": \"",
					ConfigManager.getProperty("OtherGameWinningRef_txns_ID"),"\",\"balanceType\": \"",
					apiData.get("balanceType"),"\",\"particular\":\"Automation Testing for Winning\",\"gameId\": \"",
					apiData.get("gameId"),"\",\"gameName\": \"",apiData.get("gameName"),"\",\"amount\":\"",
					winningAmount,"\",\"isWithdrawable\":\"Y\",\"deviceCheck\":\"true\",\"device\":\"",
					ConfigManager.getProperty("OtherTxnsDevice"),"\",\"macAddress\":\"",
					ConfigManager.getProperty("OtherTxnsDaviceID"),"\"}]}");
			response=given().contentType("application/json").body(WinningForOtherGames).when().post(url);
			resbody=response.getBody().asString();
			LOGGER.info(resbody);
			int statusCode=response.getStatusCode();
			if(statusCode==200)
			{
				int errorCode=response.jsonPath().getInt("errorCode");
				if(errorCode==0)
				{
					JSONObject jsonObj=new JSONObject(resbody);
					int internalErrorCode=jsonObj.optJSONArray("plrWiseRespBean").optJSONObject(0).optInt("errorCode");
					if(internalErrorCode==0)
					{
						Apidata=GeneralMethods.jsonToMap(jsonObj.optJSONArray("plrWiseRespBean").optJSONObject(0).toString());
						flag=true;
					}else
					{LOGGER.info("Player wise transaction for winning is failed");
					flag=true;}
						
				}else
				{
					LOGGER.info("Response of Winning API is failed");
					flag=false;
				}
			}else
			{
				LOGGER.info("winning api for otherGame is not working");
				flag=false;
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error: ", e);
		}
		return Apidata;
	}

*/	
}
