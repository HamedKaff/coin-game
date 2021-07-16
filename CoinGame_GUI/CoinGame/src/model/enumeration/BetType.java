package model.enumeration;

import model.interfaces.CoinPair;
import model.interfaces.Player;


/* 
 * Enum:            BetType
 * Description:     The enum represents types of bets 
 * Author:          Hamed Alkaff - s3708483
 */

 
public enum BetType  
{
	COIN1 
	{
		@Override
		public void applyWinLoss(Player player, CoinPair spinnerResult) 
		{
			if (spinnerResult.getCoin1().equals(player.getResult().getCoin1() ) )   
				player.setPoints(player.getPoints() + player.getBet() );
			else
				player.setPoints(player.getPoints() - player.getBet() );
		}
	},
	COIN2
	{
		@Override
		public void applyWinLoss(Player player, CoinPair spinnerResult)
		{
			if (spinnerResult.getCoin2().equals(player.getResult().getCoin2() ) )   
				player.setPoints(player.getPoints() + player.getBet() );
			else
				player.setPoints(player.getPoints() - player.getBet() );

		}
	},
	BOTH
	{
		@Override
		public void applyWinLoss(Player player, CoinPair spinnerResult)
		{
			if (spinnerResult.getCoin1().equals(player.getResult().getCoin1() ) 
			 && spinnerResult.getCoin2().equals(player.getResult().getCoin2() ) )   
				player.setPoints(player.getPoints() + 2 * player.getBet() );
			else
				player.setPoints(player.getPoints() - player.getBet() );

		}
	},
	NO_BET
	{
		@Override
		public void applyWinLoss(Player player, CoinPair spinnerResult)
		{
			//no action
 		}
	};

	// applying the win and loss on players in each bet type above.
	public abstract void applyWinLoss(Player player, CoinPair spinnerResult);



}