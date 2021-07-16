package view.toolBar;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JToolBar;

import controller.cancelBetListener.CancelBetListener;
import controller.placeBetListener.PlaceBetListener;
import controller.spinCoinListener.SpinCoinListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;



/*
 * Class:           ToolBar
 * Description:     this class takes care of the toolBar or in other words, the buttons.
 * Author:          Hamed Alkaff - s3708483
 */

@SuppressWarnings("serial")
public class ToolBar extends JToolBar {
	protected DefaultListCellRenderer defaultRenderer  = new DefaultListCellRenderer();
	
	private JButton betButton = new JButton();
	private JButton spinButton = new JButton();
	private JButton cancelButton = new JButton();

	private PlaceBetListener placeBetListener;
	JList<? extends Player> listModel = new JList<>();
	DefaultListModel<Player> listModell = new DefaultListModel<>();

	public ToolBar(MainFrame frame, GameEngine engine) {

		JToolBar container = new JToolBar();
		betButton = new JButton("PLACE BET");
		betButton.setFont(new Font("Arial", Font.BOLD, 15));
		betButton.setActionCommand("PLACE BET");
		placeBetListener = new PlaceBetListener(frame, engine);
		betButton.addActionListener(placeBetListener);


		spinButton = new JButton("SPIN COIN");
		spinButton.setFont(new Font("Arial", Font.BOLD, 15));
		spinButton.setForeground(Color.BLACK);
		spinButton.setActionCommand("SPIN COIN");
		spinButton.addActionListener(new SpinCoinListener(frame, engine));
		
		cancelButton = new JButton("CANCEL BET");
		cancelButton.setFont(new Font("Arial", Font.BOLD, 15));
		cancelButton.setForeground(Color.RED);
		cancelButton.setActionCommand("CANCEL BET");
		cancelButton.addActionListener(new CancelBetListener(frame));

		
		container.add(betButton);
		container.add(spinButton);
		container.add(cancelButton);
		container.setLayout(new FlowLayout(FlowLayout.CENTER));


		add(container);
		betButton.setEnabled(true);
		spinButton.setEnabled(true);
		cancelButton.setEnabled(false);
		container.setFloatable(false);

        
	}
	public void updateBox(Player player) {
		listModell.addElement(player);

	}
	
	public PlaceBetListener getPlaceBetListener() {
		return placeBetListener;
	}
		
	public JButton getBetButton() {
		return betButton;
	}

	public JButton getSpinButton() {
		return spinButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

}
