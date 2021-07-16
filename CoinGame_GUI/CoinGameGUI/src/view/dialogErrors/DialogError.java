package view.dialogErrors;

import javax.swing.*;

import view.MainFrame;

/*
 * Class:           DialogError
 * Description:     this class returns an error message that then can be used when validating inputs
 * Author:          Hamed Alkaff - s3708483
 */
@SuppressWarnings("serial")
public class DialogError extends JOptionPane {

	public void errorMessage(MainFrame frame, String Message) {
		showConfirmDialog(frame, Message, "Error Message", CLOSED_OPTION);
	}

}
