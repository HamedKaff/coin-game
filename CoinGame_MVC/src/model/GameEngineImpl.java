package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import model.enumeration.BetType;
import model.interfaces.CoinPair;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;
import model.interfaces.GameEngine;

/*
 * Class:           GameEngineImpl
 * Description:     implementation of the GameEngine interface which represents the main functionality of Two-Up Game 
 * Author:          Hamed Alkaff - s3708483
 */

public class GameEngineImpl implements GameEngine {
	
	private Collection <Player> players = new ArrayList<Player>();
	private Collection <GameEngineCallback> gameEngineCallBackImpls = new ArrayList<GameEngineCallback>(); 


	@Override
	public void spinPlayer(Player player, int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2,
			int finalDelay2, int delayIncrement2) throws IllegalArgumentException {



		CoinPair coinPair = new CoinPairImpl(); 
		int delay = initialDelay1;

		while ( delay < finalDelay1 ) {

			coinPair.getCoin1().flip();
			coinPair.getCoin2().flip();

			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
			}


			for (GameEngineCallback gameEngineCB : gameEngineCallBackImpls ) {
				gameEngineCB.playerCoinUpdate(player,coinPair.getCoin1(), this );
				gameEngineCB.playerCoinUpdate(player,coinPair.getCoin2(), this );
			}
			delay += delayIncrement1;
		}

		player.setResult(coinPair);
		for (GameEngineCallback gameEngineCB : gameEngineCallBackImpls ) 
			gameEngineCB.playerResult(player,coinPair, this );

	}

	/*
	 * BEGIN
	 *    INSTANTIATE spinnerCoin Object
	 *    initialize delay to InitialDelay1
	 *    WHILE delay is less than finalDelay1
	 *       CALL flip method from CoinImpl on both spinner's coins
	 *       Threa.sleep function on delay under TRY
	 *       AND CATCH exception
	 *       FOR-EACH GameEngineCallback in the collection
	 *       CALL spinnerCoinUpdate on declared variable (gameEngineCB)
	 *       Delay increments
	 *    ApplyBetResults on spinnerCoin
	 *    FOR-EACH GameEngineCallback in the collection
	 *    CALL spinnerResult on declared variable (gameEngineCB)   
	 * END
	 */

	@Override
	public void spinSpinner(int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2, int finalDelay2,
			int delayIncrement2) throws IllegalArgumentException {

		CoinPair spinnerCoin = new CoinPairImpl();
		int delay = initialDelay1;

		while ( delay < finalDelay1 ) {
			spinnerCoin.getCoin1().flip();
			spinnerCoin.getCoin2().flip();

			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
			}

			for (GameEngineCallback gameEngineCB : gameEngineCallBackImpls ) {
				gameEngineCB.spinnerCoinUpdate(spinnerCoin.getCoin1(), this );
				gameEngineCB.spinnerCoinUpdate(spinnerCoin.getCoin2(), this );
			}

			delay += delayIncrement1;

		}

		this.applyBetResults(spinnerCoin);
		for (GameEngineCallback gameEngineCB : gameEngineCallBackImpls ) {
			gameEngineCB.spinnerResult(spinnerCoin, this );

		}

	}

	//applies bet results and call win & loss on all players
	@Override
	public void applyBetResults(CoinPair spinnerResult) {
		for (Player player : players ) 
			player.getBetType().applyWinLoss(player, spinnerResult);
	}

	@Override
	public void addPlayer(Player player) {

		for ( Player thePlayer : players ) {
			if (player.getPlayerId().equals(thePlayer.getPlayerId()) ) {
				thePlayer = player;
				return;
			}
		}
		players.add(player);		
	}

	@Override
	public Player getPlayer(String id) {
		for (Player player: players) {
			if (player.getPlayerId().equals(id) ) 
				return player;
		}
		return null;
	}

	@Override
	public boolean removePlayer(Player player) {
		Iterator<Player> aPlayer = players.iterator();
		while (aPlayer.hasNext()) {
			Player a_player =  aPlayer.next();
			if (a_player.equals(player)) {
				aPlayer.remove();
				return true;  
			}
		}
		return false;	
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		gameEngineCallBackImpls.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		Iterator<GameEngineCallback> callback = gameEngineCallBackImpls.iterator();
		while (callback.hasNext()) {
			GameEngineCallback gameEngineCB =  callback.next();
			if (gameEngineCallback.equals(gameEngineCB)) {
				callback.remove();
				return true;
			}
		}
		return false;
	}

	@Override
	public Collection<Player> getAllPlayers() {

		return Collections.unmodifiableCollection(players);
	}

	@Override
	public boolean placeBet(Player player, int bet, BetType betType) {
		player.setBetType(betType);
		return player.setBet(bet);
	}


}
