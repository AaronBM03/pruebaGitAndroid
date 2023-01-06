package com.example.pruebagit.model;

import java.util.ArrayList;
import java.util.List;

public class Match {
	static private final int N_TURNS = 5;
	private Card player;
	private Card opponent;
	private int turn;
	private int scorePlayer;
	private int scoreOpponent;
	private List<Stats> usedStats;
	private MatchStatus status;

	/**
	 * Constructor for Match class
	 *
	 * @param player        The player's card
	 * @param opponent      The selected opponent's card
	 * @param turn          The current turn
	 * @param scorePlayer   The player's score
	 * @param scoreOpponent Their opponent's score
	 * @param usedStats     List of already used stats. Can be passed as null if new
	 *                      list is desired.
	 */
	public Match(Card player, Card opponent, int turn, int scorePlayer, int scoreOpponent, List<Stats> usedStats,
			MatchStatus status) {
		this.player = player;
		this.opponent = opponent;
		this.turn = turn;
		this.scorePlayer = scorePlayer;
		this.scoreOpponent = scoreOpponent;
		this.usedStats = (usedStats != null) ? usedStats : new ArrayList<>();
		this.status = status;
	}

	public Match() {
	}

	public Card getPlayer() {
		return player;
	}

	public void setPlayer(Card player) {
		this.player = player;
	}

	public Card getOpponent() {
		return opponent;
	}

	public void setOpponent(Card opponent) {
		this.opponent = opponent;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public int getScorePlayer() {
		return scorePlayer;
	}

	public void setScorePlayer(int scorePlayer) {
		this.scorePlayer = scorePlayer;
	}

	public int getScoreOpponent() {
		return scoreOpponent;
	}

	public void setScoreOpponent(int scoreOpponent) {
		this.scoreOpponent = scoreOpponent;
	}

	public List<Stats> getUsedStats() {
		return usedStats;
	}

	public void setUsedStats(List<Stats> usedStats) {
		this.usedStats = usedStats;
	}

	public MatchStatus getStatus() {
		return status;
	}

	public void setStatus(MatchStatus status) {
		this.status = status;
	}

	public void resolveRound(Stats stat) {
		if (player.getStat(stat) > opponent.getStat(stat)) {
			scorePlayer++;
		} else if (player.getStat(stat) < opponent.getStat(stat)) {
			scoreOpponent++;
		} else if(player.getCardAvg() >= opponent.getCardAvg()) {
				scorePlayer++;
		} else {
			scoreOpponent++;
		}

		if(scorePlayer > N_TURNS/2 || scoreOpponent > N_TURNS/2) {
			resolveMatch();
		}
	}

	private void resolveMatch() {
		if(scorePlayer > scoreOpponent) {
			status = MatchStatus.WON;
		} else {
			status = MatchStatus.LOST;
		}
	}
}
