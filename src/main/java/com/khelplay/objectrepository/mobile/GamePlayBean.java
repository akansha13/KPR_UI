package com.khelplay.objectrepository.mobile;

import java.util.ArrayList;

public class GamePlayBean {
	private static ArrayList<String> gamePlayCardList;
	private static ArrayList<String> fistHandCardList;
	private static int takeSeatCount;

	public static ArrayList<String> getFistHandCardList() {
		return fistHandCardList;
	}

	public static void setFistHandCardList(ArrayList<String> fistHandCardList) {
		GamePlayBean.fistHandCardList = fistHandCardList;
	}

	public static ArrayList<String> getGamePlayCardList() {
		return gamePlayCardList;
	}

	public static void setGamePlayCardList(ArrayList<String> gamePlayCardList) {
		GamePlayBean.gamePlayCardList = gamePlayCardList;
	}

	public static int getTakeSeatCount() {
		return takeSeatCount;
	}

	public static void setTakeSeatCount(int setTakeSeatCount) {
		takeSeatCount=setTakeSeatCount;
		
	}

}
