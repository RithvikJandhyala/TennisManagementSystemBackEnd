package com.tennis.springboots.model;

import java.util.HashMap;

public class PlayersList {
	public static HashMap<Integer, Object> playersList = new HashMap<Integer, Object>();

	public static Object get(int player1) {
		Object player = playersList.get(player1);
		if(player instanceof Player)
		{
			return player;
		}
		return null;

	}

	public static void assignMatch(int player1id, Match match) {
		Object player = playersList.get(player1id);
		if(player instanceof Player)
		{
			((Player)player).assignMatch(match);
		}
		if(player instanceof DoublesPair)
		{
			((DoublesPair)player).assignMatch(match);
		}
	}

	public static void setStatus(int player1id, String status) {
		Object player = playersList.get(player1id);
		if(player instanceof Player)
		{
			((Player)player).setStatus(status);
		}
		if(player instanceof DoublesPair)
		{
			((DoublesPair)player).setStatus(status);
		}
		
	}

	public static void setCurrentMatch(int player1id, Match match) {
		Object player = playersList.get(player1id);
		if(player instanceof Player)
		{
			((Player)player).setCurrentMatch(match);
		}
		if(player instanceof DoublesPair)
		{
			((DoublesPair)player).setCurrentMatch(match);
		}
	}

	public static String getStatus(int playerid) {
		Object player = playersList.get(playerid);
		if(player instanceof Player)
		{
			return ((Player)player).getStatus();
		}
		if(player instanceof DoublesPair)
		{
			return ((DoublesPair)player).getStatus();
		}
		return "No Status";
	}

	public static String getName(int playerid) {
		Object player = playersList.get(playerid);
		if(player instanceof Player)
		{
			return ((Player)player).getName();
		}
		if(player instanceof DoublesPair)
		{
			return ((DoublesPair)player).getName();
		}
		return "No Player";
	}
	
	

}
