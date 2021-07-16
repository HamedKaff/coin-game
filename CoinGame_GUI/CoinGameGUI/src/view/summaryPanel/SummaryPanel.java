package view.summaryPanel;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;
  
import controller.listlistener.ListListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;



/*
 * Class:           SummaryPanel
 * Description:     this class takes care of the Summary panel and the player list
 * Author:          Hamed Alkaff - s3708483
 */
@SuppressWarnings("serial")
public class SummaryPanel extends JPanel {
	
	MainFrame frame;
	DefaultListModel<Player> listModel = new DefaultListModel<>();

	public SummaryPanel(MainFrame frame, GameEngine engine) {

        this.frame = frame;

		JPanel container = new JPanel();
		
		container.setBorder(new TitledBorder("Player List"));
		container.setLayout(new BorderLayout());

 
		JList<Player> playerList = new JList<>(listModel);
		ListCellRenderer<Player> renderer = new PlayerListCellRenderer(frame);
		playerList.setCellRenderer(renderer);
		playerList.setFixedCellWidth(125);
		playerList.addListSelectionListener(new ListListener(frame, playerList, engine));
		
		
		container.add(new JScrollPane(playerList));
		JScrollPane scrollPane2 = new JScrollPane(playerList);
		scrollPane2.setBorder(new TitledBorder("Player List"));
		add(scrollPane2);
         
	}

	public void updateList(Player player) {
		listModel.addElement(player);
	}

	public void removePlayer(Player player) {
		listModel.removeElementAt(WHEN_FOCUSED);

	}
	public void removePlayerFromList(Player player) {
		listModel.removeElement(player);
		frame.repaint();
		frame.revalidate();

	}

}