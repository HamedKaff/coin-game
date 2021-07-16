package view.coinPanel;

import java.awt.Graphics;
import javax.swing.JPanel;
import model.enumeration.CoinFace;
import model.interfaces.Coin;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import view.MainFrame;
import view.enumfaces.Faces;
import view.statusBar.StatusBar;
import view.summaryPanel.SummaryPanel;
import view.toolBar.ToolBar;

/*
 * Class:           CoinPanel
 * Description:     this class represents the CoinPanel, including the resizing and methods that are used in GECGUI
 * Author:          Hamed Alkaff - s3708483
 */
@SuppressWarnings("serial")
public class CoinPanel extends JPanel {

	
	private StatusBar statusBar;
	private ToolBar toolBar;
	private SummaryPanel playerSum;
	private Faces face1 = null, face2 = null;

	public CoinPanel(MainFrame mainFrame, GameEngine engine) {

	

	}

	public void paintComponent(Graphics g) {
		super.paintComponents(g);

		int w = getWidth();
		int h = getHeight();

		// to set a good horizontal fit:
		if (h * 2 <= w) {

			if (face1 != null)
				g.drawImage(face1.getImage(), w / 4 - h / 2, 0, h, h, this);
			if (face2 != null)
				g.drawImage(face2.getImage(), 3 * w / 4 - h / 2, 0, h, h, this);

		} else {
			if (face1 != null)
				g.drawImage(face1.getImage(), w / 4 - w / 4, h / 2 - w / 4, w / 2, w / 2, this);
			if (face2 != null)
				g.drawImage(face2.getImage(), 3 * w / 4 - w / 4, h / 2 - w / 4, w / 2, w / 2, this);
		}

	}

	//method to be used in the GECGUI class to update the faces when flipping
	public void showCoinPanel(Coin coin) {
		
		if (coin.getFace() == CoinFace.HEADS) {
			if (coin.getNumber() == 1)
				face1 = Faces.HEADS;
			else
				face2 = Faces.HEADS;

		} else {
			if (coin.getNumber() == 1)
				face1 = Faces.TAILS;
			else
				face2 = Faces.TAILS;
		}

		this.updateUI();
		this.repaint();
	}


	public CoinPair CoinPairFinal(CoinPair coinPair) {
		return coinPair;
	}

	public StatusBar getStatus() {
		return statusBar;
	}

	public ToolBar getToolBar() {
		return toolBar;
	}

	public SummaryPanel playerSummary() {
		return playerSum;
	}
	// these methods to be used later when setting the final faces for the players to enable switching
	public void setFace1(Faces f1) {
		face1 = f1;
		this.updateUI();
		this.repaint();
	}
	public void setFace2(Faces f2) {
		face2 = f2;
		this.updateUI();
		this.repaint();
	}
	
}
