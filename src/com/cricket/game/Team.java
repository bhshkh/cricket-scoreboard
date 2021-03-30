package com.cricket.game;

import java.util.Scanner;

import com.cricket.utils.InputScanner;
import com.cricket.utils.Validations;

public class Team {
	private Player[] players;
	private int teamNumber, totalOvers, totalPlayers, totalRuns, outCount;
	private double completedOvers;
	private Player strikerBatsman, nonstrikerBatsman;

	Team(int teamNumber, int playerCount, int overCount) {
		this.teamNumber = teamNumber;
		totalOvers = overCount;
		totalPlayers = playerCount;

		totalRuns = 0;
		completedOvers = 0;
		outCount = 0;

		getPlayersInput();
		strikerBatsman = players[0];
		nonstrikerBatsman = players[1];

	}

	void getPlayersInput() {
		Scanner sc = InputScanner.getScanner();
		players = new Player[totalPlayers];
		System.out.println("\n\nBatting Order for team " + teamNumber + ":");
		for (int i = 0; i < totalPlayers; i++) {
			players[i] = new Player(sc.nextLine());
		}

	}

	void play() {
		Scanner sc = InputScanner.getScanner();
		int nextIndex = 2;

		// Calculate number of out players for team to be out
		int teamOut = totalPlayers;
		if (totalPlayers % 2 == 1) {
			teamOut--;
		}

		for (int currentOver = 1; currentOver <= totalOvers && outCount != teamOut; currentOver++) {
			System.out.println("\n\nOver " + currentOver + ":");
			int wickets = 0, ballCount;
			for (ballCount = 1; ballCount <= 6 && outCount != teamOut; ballCount++) {

				// Get input for ball
				String overIn = sc.nextLine();
				while (!Validations.isValidBall(overIn)) {
					System.out.println("Please enter an integer or W or Wd or N");
					overIn = sc.nextLine();
				}

				while (!Validations.isLegalDelivery(overIn)) {
					totalRuns++;
					overIn = sc.nextLine();
				}

				// Increment balls for striker
				strikerBatsman.setBalls(strikerBatsman.getBalls() + 1);

				if (overIn.equals("W")) {
					strikerBatsman.setOut(true);
					outCount++;
					wickets++;
					if (nextIndex < totalPlayers) {
						strikerBatsman = players[nextIndex];
						nextIndex++;
					} else {
						strikerBatsman = null;
					}
				} else {
					int runCount = Integer.parseInt(overIn);
					strikerBatsman.setCurrentScore(strikerBatsman.getCurrentScore() + runCount);
					totalRuns += runCount;

					if (runCount == 4) {
						strikerBatsman.setFours(strikerBatsman.getFours() + 1);
					} else if (runCount == 6) {
						strikerBatsman.setSixes(strikerBatsman.getSixes() + 1);
					} else if (runCount % 2 == 1) {
						changeStrike();
					}
				}
			}
			ballCount--;
			if (ballCount == 6) {
				completedOvers++;
			} else {
				completedOvers += ballCount * 0.1;
			}
			changeStrike();
			printScoreForOver(wickets);
		}
	}

	private void changeStrike() {
		Player temp = nonstrikerBatsman;
		nonstrikerBatsman = strikerBatsman;
		strikerBatsman = temp;

	}

	private void printScoreForOver(int wickets) {
		System.out.println("Scorecard for Team " + teamNumber + ":");
		System.out.printf("%-14s %-7s %-5s %-5s %-7s\n", "Player Name", "Score", "4s", "6s", "Balls");

		for (int i = 0; i < totalPlayers; i++) {
			String playerName = players[i].getName();
			if ((strikerBatsman == players[i] || nonstrikerBatsman == players[i]) && !players[i].isOut()) {
				playerName += "*";
			}
			System.out.printf("%-14s %-7d %-5d %-5d %-7d\n", playerName, players[i].getCurrentScore(),
					players[i].getFours(), players[i].getSixes(), players[i].getBalls());

		}
		System.out.println("Total: " + totalRuns + "/" + wickets);
		System.out.printf("Overs: ");
		if (Math.floor(completedOvers) == completedOvers) {
			System.out.printf("%d\n", (int)completedOvers);
		} else {
			System.out.printf("%.1f\n", completedOvers);

		}

	}

	public int getTotalRuns() {
		return totalRuns;
	}

	public int getTeamNumber() {
		return teamNumber;
	}
}
