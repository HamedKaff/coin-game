package model;

import model.enumeration.BetType;
import model.interfaces.CoinPair;
import model.interfaces.Player;

/*
 * Class:           SimplePlayer
 * Description:     implementation of the Player interface which represents players of the game 
 * Author:          Hamed Alkaff - s3708483
 */

public class SimplePlayer implements Player {

	private String playerName;
	private String playerId;
	private int points;
	private CoinPair coinPair; 
	private BetType betType;
	private int bet;

	public SimplePlayer(String playerId, String playerName, int initialPoints) {
		this.playerName = playerName;
		this.playerId = playerId;
		this.points = initialPoints;
	} 



	@Override
	public String getPlayerName() {  

		return playerName;
	}

	@Override
	public void setPlayerName(String playerName) {

		this.playerName = playerName;
	}

	@Override
	public int getPoints() {
		return points;	
	}

	@Override
	public void setPoints(int points) {
		this.points = points;

	}

	@Override
	public String getPlayerId() {
		return playerId;
	}

	@Override
	public boolean setBet(int bet) {
		if (bet > 0 && bet <= points) {
			this.bet = bet;
			return true;
		}
		return false;
	}

	@Override
	public int getBet() {
		return bet; 
	}

	//returns a formatted string representing the players' final results.
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%s","Player: id= "+ getPlayerId()));
		sb.append(String.format(", %s","Name= " + getPlayerName()));
		sb.append(String.format(", %s","Bet= " + bet));
		sb.append(String.format(", %s","BetType= " + getBetType()));
		sb.append(String.format(", %s","Points= " + getPoints()));
		sb.append(String.format(", %s","RESULT.. " + getResult()));
		return sb.toString(); 
	}



	@Override
	public void setBetType(BetType betType) {
		this.betType = betType;		
	}

	@Override
	public BetType getBetType() {
		return betType;
	}

	@Override
	public void resetBet() {
		this.bet = 0;		
	}

	@Override
	public CoinPair getResult() {
		return coinPair; 	
	}

	@Override
	public void setResult(CoinPair coinPair) {
		this.coinPair = coinPair;
	}


}
