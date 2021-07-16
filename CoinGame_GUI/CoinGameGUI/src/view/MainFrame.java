package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import model.interfaces.GameEngine;
import view.coinPanel.CoinPanel;
import view.gameEngineCallbackGUI.GameEngineCallbackGUI;
import view.interfaces.GameEngineCallback;
import view.statusBar.StatusBar;
import view.summaryPanel.SummaryPanel;
import view.toolBar.ToolBar;
import view.menuBar.MenuBar;
import view.model.ViewModel;

/*
 * Class:           MainFrame
 * Description:     this class represents the main frame that includes everything
 * Author:          Hamed Alkaff - s3708483
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	
	private SummaryPanel playerSum;
	private ToolBar toolbar;
	private StatusBar status;
	private CoinPanel coinsFrame;
	private CoinPanel coinPanel;
	private ViewModel viewmodel = null;
	
	
	public MainFrame (GameEngine engine) { 


		setIconImage(Toolkit.getDefaultToolkit().getImage("img/tails.png"));
		setBounds(0, 0, 600, 700);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Two-Up Australian Coin Game");
		setMinimumSize(new Dimension(getWidth(), getHeight()));

		setJMenuBar(new MenuBar(this, engine));
		toolbar = new ToolBar(this, engine);
		playerSum = new SummaryPanel(this, engine);
		coinsFrame = new CoinPanel(this, engine);
		coinPanel = coinsFrame;
  
		status = new StatusBar();
		add(status,BorderLayout.SOUTH);
		add(toolbar, BorderLayout.NORTH);
		add(playerSum, BorderLayout.WEST);
		add(coinsFrame, BorderLayout.CENTER);
		
		GameEngineCallback gameEngineCB1 = new GameEngineCallbackGUI(this);
		engine.addGameEngineCallback(gameEngineCB1);

		gameEngineCB1 = new GameEngineCallbackImpl();
        engine.addGameEngineCallback(gameEngineCB1);		
		setVisible(true);
		viewmodel = new ViewModel();

	}

	public StatusBar getStatus() {
		return status;
	}

	public SummaryPanel getPlayerSummary() {
		return playerSum;
	}

	public ToolBar getToolbar() {
		return toolbar;
	}

	public CoinPanel coinPanel() {
		return coinPanel;
	}
	
	public ViewModel getviewmodel() {
		return viewmodel;
	}

}
