package mybank;
import java.awt.event.*;
import javax.swing.*;

/*
 * This dialog box will store the user's answer based on a button click.
 * When a button is clicked in a "QuestionDialog", "answer" will get set to its label.
 */

public class QuestionDialog extends JDialog implements ActionListener {

	String answer; // Declare the String instance variable answer.

	// The actionPerformed method supports the "ActionListener" interface.
	public void actionPerformed(ActionEvent e) {
		answer = e.getActionCommand(); // Return the command string associated with this action and store it in the variable string.
		dispose(); // Close the dialog.
	} // End of the actionPerformed method.
} // End of the ActionListener interface.
