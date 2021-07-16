package view.summaryPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.StrokeBorder;
import javax.swing.border.TitledBorder;

import model.interfaces.Player;
import view.MainFrame;


/*
 * Class:           PlayerListCellRenderer
 * Description:     this class constructs the player summary block in the player list
 * Author:          Hamed Alkaff - s3708483
 */

//the following is the reference to what I've got in this class 
//https://stackoverflow.com/questions/49371809/jpanel-with-multiple-lables-as-a-listcellrenderer
//https://docs.oracle.com/javase/7/docs/api/javax/swing/ListCellRenderer.html

public class PlayerListCellRenderer implements ListCellRenderer<Player> {
	
	protected Border noFocusBorder = new EmptyBorder(14, 1, 1, 1);
	protected TitledBorder focusBorder = new TitledBorder(LineBorder.createGrayLineBorder(), "PLAYER");
	protected DefaultListCellRenderer defaultRenderer  = new DefaultListCellRenderer();
	protected DefaultListCellRenderer defaultRenderer1 = new DefaultListCellRenderer();
	protected DefaultListCellRenderer defaultRenderer2 = new DefaultListCellRenderer();
	protected DefaultListCellRenderer defaultRenderer3 = new DefaultListCellRenderer();
	protected DefaultListCellRenderer defaultRenderer4 = new DefaultListCellRenderer();

	private MainFrame frame;
	
	public PlayerListCellRenderer(MainFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Player> list, 
			Player player, int index,
			boolean isSelected, boolean cellHasFocus) 
	
	{
		String temp = (player.getBetType() != null) ? player.getBetType().toString() : "-";
         
		JLabel playerName = (JLabel) defaultRenderer.getListCellRendererComponent(list, 
				"Name: " + player.getPlayerName(),
				index, isSelected, cellHasFocus);
		playerName.setFont(new Font("Arial", Font.BOLD, 12));
		
		
		JLabel points = (JLabel) defaultRenderer1.getListCellRendererComponent(list, 
				"Points: " + player.getPoints(),
				index, isSelected, cellHasFocus);
		points.setFont(new Font("Arial", Font.BOLD, 12));
		
		
		JLabel bet = (JLabel) defaultRenderer2.getListCellRendererComponent(list,
				"Bet: " + player.getBet(), index,
				isSelected, cellHasFocus);
		bet.setFont(new Font("Arial", Font.BOLD, 12));
		
		
		JLabel betType = (JLabel) defaultRenderer3.getListCellRendererComponent(list, 
				"Bet Type: " + temp, index,
				isSelected, cellHasFocus);
		betType.setFont(new Font("Arial", Font.BOLD, 12));

	
		JLabel winloss = (JLabel) defaultRenderer4.getListCellRendererComponent(list, 
				"Win/loss: " + frame.getviewmodel().getWinloss(player.getPlayerId()), index,
				isSelected, cellHasFocus);
		winloss.setFont(new Font("Arial", Font.BOLD, 12));
		
		
		JPanel panel = new JPanel(new GridLayout(5, 5));
		Color borderColor = isSelected ? Color.GRAY : Color.PINK;

		panel.setBorder(new StrokeBorder(new BasicStroke(3.0f), borderColor));
		panel.add(playerName);
		panel.add(points);
		panel.add(bet);
		panel.add(betType);
		panel.add(winloss);

		return panel;
	}

}