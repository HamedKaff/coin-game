package view.statusBar;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import model.interfaces.Coin;
import model.interfaces.CoinPair;
import model.interfaces.Player;
import java.awt.Color;
import java.awt.Font;


/*
 * Class:           StatusBar
 * Description:     this class forms the status bar and updates it with the spinnings
 * Author:          Hamed Alkaff - s3708483
 */

@SuppressWarnings("serial")
public class StatusBar extends JPanel {
	private JLabel[] tableStatus = new JLabel[2];

	private Player player;
	private Player playerRound;
	private Coin coin1 = null;
	private Coin coin2 = null;

	public StatusBar() {

		setBorder(new BevelBorder(BevelBorder.LOWERED));
		setLayout(new GridLayout(2, 1));
		getTableState()[0] = new JLabel("Welcome! add Player to Get Started");
		getTableState()[0].setBorder(new LineBorder(Color.RED));
		getTableState()[0].setFont(new Font("Arial", Font.BOLD, 13));
		getTableState()[1] = new JLabel("Winning  player : ");
		getTableState()[1].setBorder(new LineBorder(Color.RED));
		getTableState()[1].setFont(new Font("", Font.BOLD, 13));

		
		
		for (int i = 0; i < getTableState().length; i++) {
			add(getTableState()[i]);

		}
	}

	public JLabel[] getTableState() {
		return tableStatus;
	}

	public void setTableStat(JLabel[] tableStat) {
		this.tableStatus = tableStat;
	}

	public void playerUpdate(Player player) {
		if (player != null) {
			this.player = player;

			getTableState()[1].setText("Spinning  coins :");

		} else {
			getTableState()[1].setText("Spinning  coins :");

		}
	}

	//method to update the players and spinners coins 
	public void nextPlay(Coin coin, boolean forPlayer) {
		String coin1txt, coin2txt;
		if (coin.getNumber() == 1) {
			coin1txt = coin.toString();
			coin1 = coin;
			if (coin2 == null)
				coin2txt = "";
			else
				coin2txt = coin2.toString();
		} else {
			coin2txt = coin.toString();
			coin2 = coin;
			if (coin1 == null)
				coin1txt = "";
			else
				coin1txt = coin1.toString();
		}
		if (forPlayer) {
			getTableState()[1].setText("Good Luck ! : " + coin1txt + " " + coin2txt );
			getTableState()[0].setText("" );
		}
		else 
			getTableState()[0].setText("Spinner spin : " + coin1txt + " " + coin2txt );
	}



	public void updateCoin() {
		this.playerRound = player;
		getTableState()[0].setText("spinning.." + getTableState()[0].getText());

	}


	//reports the results 
	public void statResults(CoinPair spinnerCoinPair) {
		if (spinnerCoinPair == null)
			getTableState()[0].setText("Ready to place bet");
		else 
			getTableState()[0].setText("Last Spinner Result= " +spinnerCoinPair.toString() );
	}

	public Player getPlayer() {
		return playerRound;
	}

	public String getPlayerName() {
		return player.getPlayerName();
	}

	public Player getCurrentPlayer() {
		return player;
	}

	 


}
