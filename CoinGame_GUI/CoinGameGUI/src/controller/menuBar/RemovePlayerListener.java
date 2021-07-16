package controller.menuBar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;

/*
 * Class:           RemovePlayerListener
 * Description:     this class holds the action of removing a player from the player list
 * Author:          Hamed Alkaff - s3708483
 */
public class RemovePlayerListener implements ActionListener {

	private MainFrame frame;
	private Player player;
	private GameEngine engine;

	public RemovePlayerListener(MainFrame frame, GameEngine engine) {
		this.frame = frame;
		this.engine = engine;
	}

	@Override
	public void actionPerformed(ActionEvent r) {
		removePlayer();
	}

    //removes the player

	private void removePlayer() {
 
		int returnedValue = JOptionPane.showConfirmDialog(frame,
				"Are you sure you want to remove player " + frame.getStatus().getPlayerName(),
				"Confirm Dialog", JOptionPane.YES_NO_OPTION);
		if (returnedValue == JOptionPane.YES_OPTION || frame.getStatus().getCurrentPlayer().getPoints() == 0)		
 		frame.getPlayerSummary().removePlayer(player);
		engine.removePlayer(frame.getStatus().getCurrentPlayer());
		frame.getStatus().playerUpdate(player);
		frame.revalidate();
 			
		
	}

}
