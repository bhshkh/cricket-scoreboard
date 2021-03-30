package com.cricket.game;

import java.util.Scanner;

import com.cricket.utils.InputScanner;
import com.cricket.utils.Validations;

public class Game {
	private int noOfPlayersPerTeam;
	private int noOfOvers;
	private Team team1;
	private Team team2;

	public Game() {
		super();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
		game.calculateResults();
		game.stop();

	}

	private void start() {
		getPlayersAndOvers();
		team1 = new Team(1, noOfPlayersPerTeam, noOfOvers);
		team1.play();

		team2 = new Team(2, noOfPlayersPerTeam, noOfOvers);
		team2.play();
	}

	private void stop() {
		InputScanner.stop();
	}

	private void calculateResults() {
		Team winningTeam = null;
		int diff = Math.abs(team1.getTotalRuns() - team2.getTotalRuns());
		if (team1.getTotalRuns() > team2.getTotalRuns()) {
			winningTeam = team1;
		} else if (team1.getTotalRuns() < team2.getTotalRuns()) {
			winningTeam = team2;
		}
		if (winningTeam == null) {
			System.out.print("\n\nResult: Match is a tie");
		} else {
			System.out.print("\n\nResult: Team " + winningTeam.getTeamNumber() + " won the match by " + diff + " runs");
		}
	}

	private void getPlayersAndOvers() {
		Scanner sc = InputScanner.getScanner();

//		Get number of players input from user
		System.out.println("No. of players for each team: ");
		String line = sc.nextLine();
		while (!Validations.isInt(line) || (Validations.isInt(line) && Integer.parseInt(line) < 2)) {
			System.out.println("Please enter an integer >= 2");
			line = sc.nextLine();
		}
		noOfPlayersPerTeam = Integer.parseInt(line);

//		Get number of overs input from user
		System.out.println("No. of overs: ");
		line = sc.nextLine();
		while (!Validations.isInt(line)) {
			System.out.println("Please enter an integer >= 2");
			line = sc.nextLine();
		}
		noOfOvers = Integer.parseInt(line);

	}

}
