package mybank;
import java.awt.*;
import javax.swing.*;

public class MultipleChoiceQuestion extends Question {

	// MultipleChoiceQuestion Constructor to create answer choices.
	public MultipleChoiceQuestion(String query) {
		super(query); // Call the Question Constructor in the Question class.

		// Call the addChoice method to add buttons and their names.
		addChoice("Balance Inquiry");
		addChoice("Deposit");
		addChoice("Withdrawal");
		addChoice("Pin Change");
		addChoice("Quit");

		initQuestionDialog(); // Call the initQuestionDialog method to finish the initialization of the "question" instance variable.
	} // End of the MultipleChoiceQuestion Constructor.

	// The addChoice method creates a button and defines its layout.
	void addChoice(String name) {
		JPanel choice = new JPanel(new BorderLayout()); // Create a panel to arrange the button.
		JButton button = new JButton(name); // Create a button with the given name.
		button.setPreferredSize(new Dimension(200, 60)); // Set the size of the button with a width of 200 and a height of 60.
		choice.setBackground(Color.LIGHT_GRAY); // Set the background color to light gray.
		button.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // change the font and its size.
		button.addActionListener(question); // Wire the button into the "actionPerformed" method of the "question" object, so it will record the answer if the button is clicked.
		choice.add(button,BorderLayout.CENTER); // Add the button to the "choice" panel, aligned to the center of the panel.
		question.add(choice); // Add the panel holding the button to the dialog box.
	} // End of the addChoice method.
} // End of class MultipleChoiceQuestion.
