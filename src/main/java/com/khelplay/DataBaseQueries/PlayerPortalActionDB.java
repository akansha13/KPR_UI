package com.khelplay.DataBaseQueries;

import com.khelplay.mobile.api.player.PlayerPortalActionsAPIBean;
import com.khelplay.utils.ConfigManager;

public class PlayerPortalActionDB {
	String avatar = "/playerImages/";

	public String regActionQuery = "select e.alias_name as domainName, b.registration_date as registrationDate,g.name as country ,f.name as state ,"
			+ "d.session_id as playerToken, a.first_deposit_refer_source as firstDepositReferSource, a.first_deposit_refer_source_id as firstDepositReferSourceId, "
			+ "a.first_deposit_sub_source_id as firstDepositSubSourceId, a.first_deposit_camp_track_id as firstDepositCampTrackId, a.mobile_no as mobileNo, "
			+ "a.player_id as playerId, a.email_verified as emailVerified, a.age_verified as ageVerified, a.address_verified as addressVerified, a.status as "
			+ "playerStatus, a.email_id as emailId, c.withdrawable_bal as withdrawableBal, c.bonus_bal as bonusBalance, c.total_bal as totalBalance, c.cash_bal"
			+ " as cashBalance, a.practice_bal as practiceBalance, c.deposit_bal depositBalance, c.winning_bal as winningBalance, a.phone_verified as phoneVerified,"
			+ " b.registration_ip as registrationIp, a.reg_device as regDevice, a.referFriendCode as referFriendCode, a.is_play2X as isPlay2x,"
			+ " concat('" + avatar
			+ "',a.player_image_path) as avatarPath, a.affiliate_bind as olaPlayer, a.user_name as userName from st_pm_player_master as "
			+ "a  join st_pm_player_info as b using(player_id) join st_txn_plr_wallet_master as c using(player_id) join st_pm_plr_login_status as d"
			+ " using(player_id) join st_dm_domain_alias_name_master as e on a.domain_id=e.domain_id  left join st_gen_state_master as f on b.state_code=f.state_code join "
			+ "st_gen_country_master as g on b.country_code=g.country_code where a.user_name='"
			+ PlayerPortalActionsAPIBean.getUserName() + "' " + "and e.alias_name='"
			+ ConfigManager.getProperty("reg.aliasName") + "'";

	public String registrationActionRequiredData = "select * from st_pm_player_master as a join st_dm_domain_alias_name_master as e on a.domain_id=e.domain_id where a.user_name=? or a.mobile_no=? or a.email_id=? and e.alias_name=?";

	public String loginActionQuery = "select e.alias_name as domainName,d.session_id AS playerToken,a.first_deposit_refer_source as firstDepositReferSource,"
			+ " a.first_deposit_refer_source_id as firstDepositReferSourceId, a.first_deposit_sub_source_id as firstDepositSubSourceId, a.first_deposit_camp_track_id"
			+ " as firstDepositCampTrackId, a.mobile_no as mobileNo, a.player_id as playerId, a.email_verified as emailVerified, a.age_verified as ageVerified,"
			+ " a.address_verified as addressVerified, a.status as playerStatus, b.state_code as stateCode, a.email_id as emailId, b.country_code as countryCode,"
			+ " c.withdrawable_bal as withdrawableBal, c.bonus_bal as bonusBalance, c.total_bal as totalBalance, c.cash_bal as cashBalance, "
			+ "a.practice_bal as practiceBalance, c.deposit_bal depositBalance, c.winning_bal as winningBalance, a.phone_verified as phoneVerified, b.city as "
			+ "city, b.address_line1 as addressLine1, a.gender as gender, a.refer_source as referSource, b.registration_ip as registrationIp, a.reg_device as regDevice,"
			+ " a.referFriendCode as referFriendCode, a.is_play2X as isPlay2x, f.poker_nick_name as pokerNickName, f.rummy_nick_name as rummyNickName,"
			+ " b.postal_code as pinCode, concat('" + avatar
			+ "',a.player_image_path) as avatarPath,a.affiliate_id as affilateId, a.affiliate_bind as olaPlayer,"
			+ "d.ip_address as lastLoginIP ,a.user_name as userName , a.last_name as lastName,a.first_name as firstName,"
			+ " a.first_deposit_date AS firstDepositDate, b.registration_date AS registrationDate from st_pm_player_master as a"
			+ "  left join st_pm_player_nick_name_mapping as f using(player_id) join st_pm_player_info as b using(player_id)"
			+ " join st_txn_plr_wallet_master as c using(player_id) join st_pm_plr_login_status as d using(player_id) join "
			+ "st_dm_domain_alias_name_master as e on a.domain_id=e.domain_id where a.user_name='"
			+ PlayerPortalActionsAPIBean.getUserName() + "'" + " and e.alias_name='"
			+ ConfigManager.getProperty("login.aliasName") + "'";

	public String loginActionRequiredData = "Select a.user_name,a.password,a.plr_status from st_pm_player_master as a join st_dm_domain_alias_name_master as b on a.domain_id=b.domain_id where a.user_name=? and b.alias_name=?";

	public String checkLoginActionQuery = "SELECT b.session_id AS playerToken, a.`address_verified` AS addressVerified, a.`phone_verified` AS phoneVerified,"
			+ " a.`email_verified` AS emailVerified, a.`age_verified` AS ageVerified, a.player_id AS playerId, a.`status` AS playerStatus, a.user_name AS userName,"
			+ " c.`total_bal` AS totalBalance, c.`cash_bal` AS cashBalance, a.`practice_bal` AS practiceBalance, c.`deposit_bal` AS depositBalance, c.`winning_bal`"
			+ " AS winningBalance, c.`withdrawable_bal` AS withdrawableBal FROM st_pm_player_master AS a JOIN `st_pm_plr_login_status` AS b USING(player_id) "
			+ "JOIN st_txn_plr_wallet_master AS c using(player_id) join st_dm_domain_alias_name_master AS d on a.domain_id=d.domain_id where "
			+ "a.user_name='" + PlayerPortalActionsAPIBean.getUserName() + "' and d.alias_name='"
			+ ConfigManager.getProperty("login.aliasName") + "'";

	public String fetchHeaderInfo_walletQuery = "SELECT SUM(amt.cash) AS cashbal,SUM(amt.pract) AS practiceBal,(SUM(amt.with_bal)-SUM(amt.subwallet_amount)) "
			+ "AS withdrawableBal FROM (SELECT pm.player_id AS plr,wm.cash_bal AS cash,pm.practice_bal AS pract,wm.withdrawable_bal AS with_bal,0 AS subwallet_amount "
			+ "FROM st_pm_player_master AS pm JOIN st_txn_plr_wallet_master AS wm USING(player_id) WHERE player_id='"
			+ PlayerPortalActionsAPIBean.getPlayerId() + "' "
			+ "UNION ALL SELECT SUM(a.aa),SUM(a.bb),SUM(a.cc),SUM(a.dd),SUM(a.subwallet_amount) FROM (SELECT 0 AS aa,0 AS bb,0 AS cc,0 AS dd,SUM(amount)"
			+ " AS subwallet_amount FROM st_txn_plr_subwallet_master WHERE player_id='"
			+ PlayerPortalActionsAPIBean.getPlayerId() + "' AND STATUS='ACTIVE'"
			+ " UNION ALL SELECT 0,0,0,0,SUM(amount) FROM st_txn_plr_subwallet_master_poker WHERE player_id='"
			+ PlayerPortalActionsAPIBean.getPlayerId() + "'" + "AND STATUS='ACTIVE') AS a) AS amt";

	public String fetchHeaderInfo_bonusQuery = "SELECT SUM(bonus_amt) AS Tbonus,SUM(redeemed_bonus_amt) AS Rbonus FROM `st_txn_plr_bonus_details`"
			+ "WHERE player_id='" + PlayerPortalActionsAPIBean.getPlayerId() + "' AND wr_status='pending'";

	public String IdAndCodeQuery = "SELECT player_id ,email_verification_code FROM st_pm_plr_verification_master WHERE player_id='"
			+ PlayerPortalActionsAPIBean.getPlayerId() + "'";

	public String DiffIdCodeQuery = "SELECT a.player_id, a.email_verification_code FROM `st_pm_plr_verification_master` AS a JOIN st_pm_plr_login_status AS b ON a.player_id=b.player_id WHERE b.status='LOGGED_IN' LIMIT 1";

	public String getLoginStatus = "SELECT STATUS FROM st_pm_plr_login_status WHERE player_id=?";

	public String fetchHeaderInfo_loyaltyQuery = "select aa.currentTier1 as currentTier,sum(aa.currentTierMaintanancePoints1) as currentTierMaintanancePoints,"
			+ "sum(aa.nextTierEntryPt1) as nextTierEntryPt,sum(aa.currentTierEarning1) as currentTierEarning from(SELECT lt.display_name as currentTier1,lt.`maintanance_points` "
			+ "as currentTierMaintanancePoints1,0 as nextTierEntryPt1, lpp.curr_point as currentTierEarning1 FROM `st_lp_loyalplayer_master` AS lm JOIN `st_lp_loyalty_tier`"
			+ " AS lt ON lm.tier=lt.id LEFT JOIN (SELECT a.wallet_id AS walletID,a.point_as_total AS curr_point FROM (SELECT * FROM `st_lp_loyalty_wallet_packet`"
			+ " WHERE state='current' ORDER BY id DESC) AS a GROUP BY a.wallet_id) AS lpp ON lm.`loyalty_wallet`=lpp.walletID WHERE"
			+ " lm.player_id='" + PlayerPortalActionsAPIBean.getPlayerId()
			+ "' union all select 0,0,entry_points,0 from `st_lp_loyalty_tier` where "
			+ "id=(select tier from `st_lp_loyalplayer_master` where player_id='"
			+ PlayerPortalActionsAPIBean.getPlayerId() + "')+1) as aa";

	public String emailExist = "SELECT a.player_id FROM st_pm_player_master AS a JOIN `st_dm_domain_alias_name_master` AS b ON b.domain_id=a.domain_id WHERE a.email_id=? and b.alias_name=?";

	public String accountActivationQuery = "SELECT a.player_id AS playerId , a.user_name AS userName, a.status AS playerStatus, a.address_verified AS addressVerified,"
			+ "a.email_verified AS emailVerified, a.phone_verified AS phoneVerified, a.age_verified AS ageVerified, a.practice_bal AS practiceBalance,b.total_bal AS totalBalance,"
			+ "b.cash_bal AS cashBalance,b.deposit_bal AS depositBalance,b.winning_bal AS winningBalance,b.withdrawable_bal AS withdrawableBal,b.bonus_bal AS bonusBalance FROM st_pm_player_master AS a JOIN st_txn_plr_wallet_master AS b ON a.player_id=b.player_id JOIN st_dm_domain_alias_name_master AS d ON a.domain_id=d.domain_id WHERE a.player_id='"
			+ PlayerPortalActionsAPIBean.getPlayerId() + "' AND d.alias_name='"
			+ ConfigManager.getProperty("login.aliasName") + "'";

	public String getPostLoginData_tournamentQuery = "SELECT tournament_id as tournamentId , fee_amount as feeAmount, buy_in_amount as buyInType, prize_pool as prizePool, tournament_name as tournamentName, CONVERT_TZ(start_date_time_ISO,'+00:00','+05:30') AS startTime FROM `st_txn_poker_tournament_feed_master` WHERE start_date_time_ISO>(SELECT NOW())";

	public String getPostLoginData_balanceTier = "SELECT tier_name as tierName,balance as loyaltyBalance FROM st_pm_plr_loyalty_master WHERE game_group='POKER' AND player_id='"
			+ PlayerPortalActionsAPIBean.getPlayerId() + "'";

	public String PasswordIdAndCodeAndDate = "SELECT a.player_id as playerId,a.password_verification_code as passwordVerificationCode,a.password_verification_expiry as passwordVerificationExpiry FROM st_pm_plr_verification_master AS a JOIN st_pm_player_master AS b ON a.player_id=b.player_id JOIN st_dm_domain_alias_name_master AS c ON c.domain_id=b.domain_id  WHERE b.email_id=? and c.alias_name=?";

	public String ValidatePlrSessionQuery = "SELECT password FROM st_pm_player_master WHERE player_id='"
			+ PlayerPortalActionsAPIBean.getPlayerId() + "'";


}
