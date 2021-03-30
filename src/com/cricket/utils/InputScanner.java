package com.cricket.utils;

import java.util.Scanner;

public class InputScanner {
	private static Scanner scanner = null;
	private InputScanner() {
		scanner = new Scanner(System.in);
	}
	
	public static Scanner getScanner() {
		if (scanner == null) {
			new InputScanner();
		}
		return scanner;
		
	}
	
	public static void stop() {
		if (scanner != null) {
			scanner.close();
		}
	}
	
}
