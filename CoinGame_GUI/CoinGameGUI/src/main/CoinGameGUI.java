package main;


import javax.swing.SwingUtilities;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.MainFrame;

/*
 * Class:           CoinGameGUI
 * Description:     this class runs the main frame which has the main method
 * Author:          Hamed Alkaff - s3708483
 */
public class CoinGameGUI {

	public static void main(String[] args) {
		final GameEngine gameEngine = new GameEngineImpl();
		 SwingUtilities.invokeLater(new Runnable()
		 {
		 @Override
		 public void run()
		 {
			 new MainFrame(gameEngine).setVisible(true);
    		 }
		 });
	}

}
