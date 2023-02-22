package ATM;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

public class YesNoQuestion extends Question {

	// YesNoQuestion constructor to create yes or no question.
	public YesNoQuestion(String question) {
		super(question); // Call the Question Constructor in the Question class.

		JPanel buttons = new JPanel(); // Create a panel to arrange the button.
		buttons.setBackground(Color.LIGHT_GRAY); // Set the background color to light gray.
		addButton(buttons,"YES"); // Add a button labeled YES.
		addButton(buttons,"NO"); // Add a button labeled NO.
		this.question.add(buttons); // Add the "buttons" panel to the dialog box itself.

		initQuestionDialog(); // Call the initQuestionDialog method to finish the initialization of the "question" instance variable.
	} // End of the YesNoQuestion constructor.

	// The "addButton" method to create buttons.
	void addButton(JPanel buttons, String label) {
		JButton button = new JButton(label); // Create a new button with the provided label.
		button.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // change the font and its size.
		button.addActionListener(question); // Wire the button into the "actionPerformed" method of the "question" object, so it will record the answer if the button is clicked.
		button.setPreferredSize(new Dimension(100, 60)); // Set the button size with a width of 100 and a height of 60.
		buttons.add(button); // Add the button to the "buttons" panel.
	} // End of the "addButton" method.
} // end of inherited class TrueFalseQuestion
