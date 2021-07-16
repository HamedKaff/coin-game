package controller.menuBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.UUID;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.playersDialog.PlayersDialog;
import view.dialogErrors.DialogError;
import view.MainFrame;

/*
 * Class:           AddPlayerListener
 * Description:     this class holds the action of adding a player including the validation 
 * Author:          Hamed Alkaff - s3708483
 */
public class AddPlayerListener implements ActionListener {
	
	private MainFrame frame;
	private Player player;
	private GameEngine engine;
	private PlayersDialog addplayer;

	public AddPlayerListener(MainFrame frame, GameEngine engine) {
		this.frame = frame;
		this.engine = engine;
	}

	@Override
	public void actionPerformed(ActionEvent r) {

		addPlayers();
	}

	public boolean createPlayer() {
		int points;
		DialogError errorCaller = new DialogError();
		
		int startingIndex = 0;
		String playerID = UUID.randomUUID().toString().substring(startingIndex);
		addplayer = new PlayersDialog(frame).addingPlayerDialog();
		
		if (addplayer.getName().equals("") || addplayer.getPoints().equals("")) {
			errorCaller.errorMessage(frame, " A text area was left blank! Please add in text area");
			return false;
			
		} else if (!addplayer.getPoints().matches(".*\\d+.*" )) {
			errorCaller.errorMessage(frame, "Points should only contain integers!");
			return false;
		}

		for (Player player : engine.getAllPlayers()) {
			if (addplayer.getName().equals(player.getPlayerName())) {
			errorCaller.errorMessage(frame, "Player name already exists, please try again!");
			return false;
			}
		}
		if (addplayer.getName().length() > 10) {
			errorCaller.errorMessage(frame, "Player name is too long!\n it must be 10 or less characters");
			return false;
		}
		if (addplayer.getPoints().length() > 6) {
			errorCaller.errorMessage(frame, "bet is too large, please bet below 1,000,000");
			return false;
		}
		points = Integer.valueOf(addplayer.getPoints());
		if (points < 0) {
			errorCaller.errorMessage(frame, "Initial Points cannot be 0 or negative!");
			return false;
		}
		player = new SimplePlayer(playerID, addplayer.getName(), points);
		frame.getStatus().statResults(null);
		return true;  
	}

	public void addPlayers() {
		if (!createPlayer()) {
			return;
		} else if (engine.getAllPlayers().size() == 0) {
			engine.addPlayer(player);
			frame.getPlayerSummary().updateList(player);
			frame.getToolbar().updateBox(player);
			frame.getStatus().playerUpdate(player);
		} else {
			engine.addPlayer(player);
			frame.getPlayerSummary().updateList(player);
		}
		frame.revalidate();

	}
	public void removePlayerUpdateList(GameEngine engine, Player player){
		Collection<Player> c = engine.getAllPlayers();
		
		for (Player p : c) {
			
			if(p.getPoints() == 0) { 
				removePlayerUpdateList(engine, p);
				
			}
			
		}
		
	}

}
