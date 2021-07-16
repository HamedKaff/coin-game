package view.menuBar;

import java.awt.Font;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.menuBar.AddPlayerListener;
import controller.menuBar.ExitListener;
// import controller.menuBar.ExitListener;
import controller.menuBar.RemovePlayerListener;
import model.interfaces.GameEngine;
import view.MainFrame;

/*
 * Class:           MenuBar
 * Description:     this class creates the pull down menus above the ToolBar
 * Author:          Hamed Alkaff - s3708483
 */
 
@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	
	public MenuBar(MainFrame frame, GameEngine engine) {
		
	JMenu Menu = new JMenu("Players");
	this.add(Menu);
	
	Menu.setFont(new Font("Arial", Font.BOLD, 14));
	JMenu Menu2 = new JMenu("Options");
	this.add(Menu2);
	
	Menu2.setFont(new Font("Arial", Font.BOLD, 14));

	JMenuItem addplayer = new JMenuItem("Add players");
	addplayer.setActionCommand("add players");
	addplayer.addActionListener(new AddPlayerListener(frame, engine));
	addplayer.setFont(new Font("Arial", Font.BOLD, 12));
	
	JMenuItem removeplayer = new JMenuItem("Remove player");
	removeplayer.setActionCommand("remove player");
	removeplayer.addActionListener(new RemovePlayerListener(frame, engine));
	removeplayer.setFont(new Font("Arial", Font.BOLD, 12));
	
	JMenuItem exitgame = new JMenuItem("Exit");
	exitgame.setActionCommand("exit");
    exitgame.addActionListener(new ExitListener());
	exitgame.setFont(new Font("Arial", Font.BOLD, 12));
	
	
	Menu.add(addplayer);
	Menu.add(removeplayer);
	Menu2.add(exitgame);

	}
}
