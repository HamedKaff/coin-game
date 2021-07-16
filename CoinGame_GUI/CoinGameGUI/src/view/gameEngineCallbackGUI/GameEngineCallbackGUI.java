package view.gameEngineCallbackGUI;

import java.util.ArrayList;
import java.util.Collection;
import javax.swing.SwingUtilities;
import model.SimplePlayer;
import model.interfaces.Coin;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import view.MainFrame;
import view.interfaces.GameEngineCallback;
import model.interfaces.Player;

/*
 * Class:           GameEngineCallbackGUI
 * Description:     this class updates the GUI 
 * Author:          Hamed Alkaff - s3708483
 */

public class GameEngineCallbackGUI implements GameEngineCallback {

	MainFrame frame;
	Collection<Player> players = new ArrayList<Player>();

	public GameEngineCallbackGUI(MainFrame frame) {
		this.frame = frame;
	
	}

	@Override
	public void playerCoinUpdate(Player player, Coin coin, GameEngine engine) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				/* the code below should cause the coin panel update/display to occur if the player
				   spinning is the one that is selected.
			     */ 
				if(player == frame.getStatus().getCurrentPlayer())
				  frame.coinPanel().showCoinPanel(coin);
				frame.getToolbar().getSpinButton().setEnabled(false);
				frame.getToolbar().getBetButton().setEnabled(false);
				frame.getStatus().nextPlay(coin, true);
				frame.getStatus().updateCoin();
			}
		});

	}

	@Override
	public void spinnerCoinUpdate(Coin coin, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.coinPanel().showCoinPanel(coin);
				frame.getToolbar().getSpinButton().setEnabled(false);
				frame.getToolbar().getBetButton().setEnabled(false);
				frame.getStatus().nextPlay(coin, false);
				frame.getStatus().updateCoin();

			}
		});

	}

	@Override
	public void playerResult(Player player, CoinPair coinPair, GameEngine engine) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				frame.getToolbar().getBetButton().setEnabled(true);
				frame.getStatus().statResults(null);


				/*the player result could potentially be used to trigger a
			   	check to see if all players with active bets have spun,
				then if this check returns true, auto trigger the spinners
			    spin. 
			    */

				Collection<Player> c = engine.getAllPlayers();
				int players_count = 0, setBets = 0;
				for (Player p : c) {
					if (p != null) {
						players_count++;
						if (p.getBet() > 0 && p.getBetType() != null) {
							setBets++;
						}
					}
				}
				
				boolean found = false;
				for (Player p : players) {
					if (player.getPlayerId() == p.getPlayerId()) {
						found = true;
						p.setPoints(player.getPoints());
						break;
					}
				}
				if (!found) {
					players.add(new SimplePlayer(player.getPlayerId(), player.getPlayerName(), player.getPoints()));
				}
				
				if (setBets == players_count) {
					frame.getToolbar().getPlaceBetListener().betSetSpin(engine);
				} else
					frame.getToolbar().getSpinButton().setEnabled(true);
			}

		});

	}

	@Override
	public void spinnerResult(CoinPair coinPair, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				frame.getToolbar().getBetButton().setEnabled(true);
				frame.getStatus().statResults(coinPair);

				String winloss = "";
				boolean found = false;

				//comparing points to decide win and loss
				for (Player p2 : engine.getAllPlayers()) {
					found = false;
					for (Player p : players) {
						if (p2.getPlayerId() == p.getPlayerId()) {
							found = true;

							if (p2.getResult() == null)
								winloss = "";
							else if (p.getPoints() < p2.getPoints())
								winloss = "Won " + (p2.getPoints() - p.getPoints());
							else if (p.getPoints() == p2.getPoints())
								winloss = "";
							else
								winloss = "Lost " + (p.getPoints() - p2.getPoints());
							;

							p.setPoints(p2.getPoints());
							break;
						}
					}

					if (!found) {
						winloss = "";
						players.add(new SimplePlayer(p2.getPlayerId(), p2.getPlayerName(), p2.getPoints()));
					}
					frame.getviewmodel().addWinloss(p2.getPlayerId() + ":" + winloss);

				}

				resetBet(engine);

				// removes a player when the points of the player is 0
				Collection<Player> c = engine.getAllPlayers();
				Player p2 = null;
				do {
					p2 = null;
					for (Player p : c) {
						if (p.getPoints() == 0) {
							frame.getPlayerSummary().removePlayerFromList(p);
							p2 = p;
							break;
						}

					}
					if (p2 != null)
						engine.removePlayer(p2);
				} while (p2 != null);

				frame.repaint();
				frame.revalidate();
			}

		});

	}

	//this will loop through all the players to apply rules and update the UI
	private void resetBet(GameEngine engine) {
		for (Player plyer : engine.getAllPlayers()) {
			plyer.resetBet();
		}
	}

}
