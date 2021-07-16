
package view.playersDialog;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import model.enumeration.BetType;
import view.MainFrame;

/*
 * Class:           PlayersDialog
 * Description:     this class holds the windows or dialogs for the betting and adding players
 * Author:          Hamed Alkaff - s3708483
 */
public class PlayersDialog {
	
 
 	JComboBox<BetType> combo = new JComboBox<BetType>(BetType.values());
 	private int index = 0;
	private JTextField playerName;
	private JTextField initialPoints;
	private JTextField bet;
	private MainFrame frame;

	public PlayersDialog(MainFrame frame) {
		this.frame = frame;
	}

	public PlayersDialog addingPlayerDialog() {
		
		
		JPanel container = new JPanel(new BorderLayout(7, 6));
		JPanel section = new JPanel(new GridLayout(0, 1, 2, 2));
		JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));

		
		section.add(new JLabel("Player Name", SwingConstants.RIGHT));
		section.add(new JLabel("Points", SwingConstants.RIGHT));
		container.add(section, BorderLayout.WEST);
		playerName = new JTextField();
		controls.add(playerName);
		initialPoints = new JTextField();
		initialPoints.getText();
		
		
		controls.add(initialPoints);
		container.add(controls, BorderLayout.CENTER);
		JOptionPane.showMessageDialog(frame, container, "Add a Player", JOptionPane.QUESTION_MESSAGE);
		return this;
	}

	public PlayersDialog getPlayerBet() {
		
		JPanel container = new JPanel(new BorderLayout(7, 6));
		JPanel section = new JPanel(new GridLayout(0, 1, 2, 2));
		JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		
		
		section.add(new JLabel("Bet", SwingConstants.RIGHT));
		section.add(new JLabel("Bet Type", SwingConstants.RIGHT));
		container.add(section, BorderLayout.WEST);
		bet = new JTextField();
		
		
		controls.add(bet);
		controls.add(combo);
		container.add(controls, BorderLayout.CENTER);
		JOptionPane.showMessageDialog(frame, container, "Enter Betting Details", JOptionPane.QUESTION_MESSAGE);
		return this;

	}
	public String getName() {
		return playerName.getText();
	}
	
	public String getBet() {
		return bet.getText();
	}
	public String getPoints() {
		return initialPoints.getText();
	}
	public int getSelectedIndex() {
		return index;
	}

	public String getbetType() {
		return combo.getSelectedItem().toString();
	}

	 
}