package controller.menuBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/*
 * Class:           ExitListener
 * Description:     this class holds the action of exiting the system
 * Author:          Hamed Alkaff - s3708483
 */

public class ExitListener implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent r) {

		exit();
	}

	private void exit() {
		int returnedValue = JOptionPane.showConfirmDialog(null, "Are You Sure You Want to Exit Application?", 
				"Confirm" , JOptionPane.YES_NO_OPTION);
		
		if (returnedValue == JOptionPane.YES_OPTION)
			System.exit(0);
	}

}
