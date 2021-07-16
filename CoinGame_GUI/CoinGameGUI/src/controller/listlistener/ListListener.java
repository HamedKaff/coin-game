
package controller.listlistener;

import model.enumeration.CoinFace;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;
import view.enumfaces.Faces;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;

/*
 * Class:           ListListener
 * Description:     this class holds the action of selecting a player from the list or panel.
 * Author:          Hamed Alkaff - s3708483
 */
public class ListListener implements ListSelectionListener {
	private JList<Player> list;
	private MainFrame frame;

	public ListListener(MainFrame frame, JList<Player> list, GameEngine engine) {
		this.list = list;
		this.frame = frame;
	}

	@Override
	public void valueChanged(ListSelectionEvent listSelectionEvent) {
		if (!listSelectionEvent.getValueIsAdjusting()) {
			final List<Player> selectedValList = list.getSelectedValuesList();

			for (Player p : selectedValList) {
				if (p.getPoints() >= 0) {
					frame.getStatus().playerUpdate(p);
				} else {
					p.setPoints(0);
				}
				if (p.getBet() > 0) {
					frame.getStatus().playerUpdate(p);
					frame.getToolbar().getBetButton().setEnabled(false);
					frame.getToolbar().getCancelButton().setEnabled(true);
					frame.getToolbar().getSpinButton().setEnabled(true);

				} else {
					frame.getToolbar().getBetButton().setEnabled(true);
					frame.getToolbar().getCancelButton().setEnabled(false);
					frame.getToolbar().getSpinButton().setEnabled(false);

				}
				if (p.getResult() == null) {
					frame.coinPanel().setFace1(null);
					frame.coinPanel().setFace2(null);
					frame.repaint();
					frame.revalidate();
					return;
				}
				//setting final faces
				if (p.getResult().getCoin1() != null) {
					if (CoinFace.HEADS == p.getResult().getCoin1().getFace())
						frame.coinPanel().setFace1(Faces.HEADS);
					else
						frame.coinPanel().setFace1(Faces.TAILS);
				} else frame.coinPanel().setFace1(null);

				if (p.getResult().getCoin2() != null) {
					if (CoinFace.HEADS == p.getResult().getCoin2().getFace())
						frame.coinPanel().setFace2(Faces.HEADS);
					else
						frame.coinPanel().setFace2(Faces.TAILS);
				} else frame.coinPanel().setFace2(null);

				frame.revalidate();
				frame.repaint();
				break;
			}
		}
	}
}
