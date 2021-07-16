package view;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.Coin;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

/*
 * Class:           GameEngineCallbakImpl
 * Description:     Skeleton implementation of GameEngineCallback showing Java logging behavior and output
 * Author:          Hamed Alkaff - s3708483
 */

public class GameEngineCallbackImpl implements GameEngineCallback
{
	private static final Logger logger = Logger.getLogger(GameEngineCallback.class.getName());


	public GameEngineCallbackImpl()
	{
		logger.setLevel(Level.FINE);
	}

	private String MixedCase(String s) { 
		return s.charAt(0) + s.substring(1, s.length() ).toLowerCase();
	}

	public void playerCoinUpdate(Player player, Coin coin, GameEngine engine)
	{
		// intermediate player results logged at Level.FINE
		logger.log(Level.FINE, player.getPlayerName() + " coin " + coin.getNumber()+ " flipped to " + MixedCase(coin.getFace().toString()) ); 
	}

	public void playerResult(Player player, CoinPair coinPair, GameEngine engine)
	{
		// final player results logged at Level.INFO
		logger.log(Level.INFO, player.getPlayerName() + " final result=" + coinPair.toString() );
	}

	// intermediate spinner results logged at Level.FINE
	@Override
	public void spinnerCoinUpdate(Coin coin, GameEngine engine) {
		logger.log(Level.FINE, "Spinner coin " + coin.getNumber()+ " flipped to " + MixedCase(coin.getFace().toString() ) );
	}

	// looping through players and call toString to log output 
	@Override
	public void spinnerResult(CoinPair coinPair, GameEngine engine) {


		logger.log(Level.INFO, "Spinner, final result=" + coinPair.toString() );

		String a = "";
		for (Player player : engine.getAllPlayers() ) 
			a = a + player.toString() + "\n";
		logger.log(Level.INFO, "Final player results\n" + a );

	}


}
