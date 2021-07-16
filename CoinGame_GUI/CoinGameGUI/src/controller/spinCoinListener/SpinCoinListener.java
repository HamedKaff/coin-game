package controller.spinCoinListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;

/*
 * Class:           SpinCoinListener
 * Description:     this class holds the action of the spin coin button and initializes a thread
 * Author:          Hamed Alkaff - s3708483
 */
public class SpinCoinListener implements ActionListener {

	private MainFrame frame;
	private GameEngine engine;

	private int initialDelay2 = 50;
	private int finalDelay2 = 500;
	private int delayIncrement2 = 50;
	private int initialDelay1 = 100;
	private int finalDelay1 = 1000;
	private int delayIncrement1 = 100;

	public SpinCoinListener(MainFrame frame, GameEngine engine) {

		this.frame = frame;
		this.engine = engine;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (frame.getStatus().getCurrentPlayer().getBet() != 0) {
			if (e.getActionCommand().equals("SPIN COIN")) {
				spinCoin();
			}

		}
	}

	public void spinCoin() {

		if (frame.getStatus().getCurrentPlayer() != null) {
			
			
			/* the code below is duplicated into PlaceBetListener
			  which implies it is fine to let a Player spin again if they want to
			  */
			Player player = frame.getStatus().getCurrentPlayer();
			if (player.getBet() != 0) {
				frame.getToolbar().getBetButton().setEnabled(false);
				frame.getToolbar().getSpinButton().setEnabled(false);

				new Thread() {

					@Override
					public void run() {
						engine.spinPlayer(player, initialDelay1, finalDelay1, delayIncrement1, initialDelay2,
								finalDelay2, delayIncrement2);
						
					}
				}.start();

			}
			frame.getToolbar().getBetButton().setEnabled(true);
			frame.getToolbar().getSpinButton().setEnabled(true);
		}
	
	}
}
