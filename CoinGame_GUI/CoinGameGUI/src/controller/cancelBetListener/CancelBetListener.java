package controller.cancelBetListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.enumeration.BetType;
import model.interfaces.Player;
import view.MainFrame;

/*
 * Class:           CancelBetListener
 * Description:     this class holds the action of canceling the bet using the button
 * Author:          Hamed Alkaff - s3708483
 */

public class CancelBetListener implements ActionListener {

	private MainFrame frame;


	public CancelBetListener(MainFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Player p = frame.getStatus().getCurrentPlayer();
		
		p.resetBet(); 
		p.setBetType(BetType.NO_BET); 
		frame.getToolbar().getCancelButton().setEnabled(false);
		frame.repaint();
		frame.revalidate();
	}
}
