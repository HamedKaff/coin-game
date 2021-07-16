package controller.placeBetListener;

import model.enumeration.BetType; 
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;
import view.playersDialog.PlayersDialog;
import view.dialogErrors.DialogError;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/*
 * Class:           PlaceBetListener
 * Description:     this class holds the action of the place bet button, including the validation and initializes a thread. 
 * Author:          Hamed Alkaff - s3708483
 */

public class PlaceBetListener implements ActionListener {

	private MainFrame frame;
	private GameEngine engine;
	private PlayersDialog playerbet;

	private int initialDelay2 = 50;
	private int finalDelay2 = 500;
	private int delayIncrement2 = 50;
	private int initialDelay1 = 100;
	private int finalDelay1 = 1000;
	private int delayIncrement1 = 100;

	public PlaceBetListener(MainFrame frame, GameEngine engine) {
		this.frame = frame;
		this.engine = engine;
	}

	@Override

	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();

		if (frame.getStatus().getCurrentPlayer() != null) {
			if (s.equals("PLACE BET")) {
				placeBet(engine);
			}
		} else
			new DialogError().errorMessage(frame, "No current players!");

	}

	private void placeBet(GameEngine engine) {

		DialogError errorCall = new DialogError();
		playerbet = new PlayersDialog(frame).getPlayerBet();
		
		if (playerbet == null) {
			errorCall.errorMessage(frame, "bet cannot be empty");
			return;
		} else if (!playerbet.getBet().matches(".*\\d+.*")) {
			errorCall.errorMessage(frame, "bet cannot have Strings");
			return;
		} else if (Integer.valueOf(playerbet.getBet()) <= 0) {
			errorCall.errorMessage(frame, "bet can't be 0 or negative");
			return;

		} else if (Integer.valueOf(playerbet.getBet()) > frame.getStatus().getCurrentPlayer().getPoints()) {
			errorCall.errorMessage(frame, "You Do not have enough points to place bet");
			return;
		}
		
		
		 else {
			
			if (!engine.placeBet(frame.getStatus().getCurrentPlayer(), Integer.valueOf(playerbet.getBet()), null)) {
				errorCall.errorMessage(frame, "Cannot Place bet!");
			} else {
		
				Player player = frame.getStatus().getCurrentPlayer();
				if (player.getBet() != 0) {
					frame.getToolbar().getBetButton().setEnabled(false);
					frame.getToolbar().getSpinButton().setEnabled(false);
					frame.getToolbar().getCancelButton().setEnabled(false);

					new Thread() {

						@Override
						public void run() {
							engine.spinPlayer(player, initialDelay1, finalDelay1, delayIncrement1, initialDelay2,
									finalDelay2, delayIncrement2);
							
					        frame.repaint();
							frame.revalidate();
						}
					}.start();

				}

				frame.getStatus().statResults(null);
				frame.getToolbar().getBetButton().setEnabled(false);
				frame.getToolbar().getSpinButton().setEnabled(true);
				frame.getToolbar().getCancelButton().setEnabled(true);
				frame.revalidate();

			}
			if (!engine.placeBet(frame.getStatus().getCurrentPlayer(), Integer.valueOf(playerbet.getBet()),
					BetType.valueOf(playerbet.getbetType()))) {
				errorCall.errorMessage(frame, "Select a Bet Type!");

			} 

		}

        frame.repaint();
		frame.revalidate();
	}

	public void betSetSpin(GameEngine engine) {
		
		new Thread() {

			@Override
			public void run() {

				engine.spinSpinner(initialDelay1, finalDelay1, delayIncrement1, initialDelay2, finalDelay2,
						delayIncrement2);
			
			}
		}.start();
	}

}
