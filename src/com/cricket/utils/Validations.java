package com.cricket.utils;

public class Validations {

	public static boolean isValidBall(String ball) {
		return ball.equals("Wd") || ball.equals("N") || ball.equals("W") || isInt(ball);
	}
	public static boolean isLegalDelivery(String ball) {
//		Assumption 1: No ball will be entered as N
		return !ball.equals("Wd") && !ball.equals("N");
	}

	public static boolean isInt(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			Integer.parseInt(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
