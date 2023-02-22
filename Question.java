package ATM;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public abstract class Question {

	QuestionDialog question;  // Declare QuestionDialog variable.
	double balance = 7000; // Declare and initialize the variable. 
	double deposit = 0; // Declare and initialize the variable.
	double withdrawal = 0; // Declare and initialize the variable.
	static String pin = "mido123"; // Declare and initialize the class variable.
	static String currentPin; // Declare the class variable.
	String pinChange; // Declare the variable.
	String newPin; // Declare the variable.
	String input; // Declare the variable. 
	
	
	// Question Constructor.
	Question(String question) {
		this.question = new QuestionDialog(); // The QuestionDialog object.
		this.question.setLayout(new GridLayout(0,1)); // Set one column.
		JLabel label = new JLabel(" "+question+" ",JLabel.CENTER); // JLabel object in the center.
		label.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // change the font and its size.
		this.question.add(label); // Add the question.

		// Add a title
		label.setBorder(BorderFactory.createTitledBorder(null, "MY BANK", TitledBorder.CENTER, TitledBorder.TOP, new Font("Lucida Calligraphy", Font.BOLD, 20), Color.blue));
	}


	// the instance method check.
	String check() {

		// Call the ask function and assign its return value to the local variable answer.
		String answer = ask();

		// If the user selects Balance Inquiry, do the following:
		if (answer.equals("Balance Inquiry")) { 
			JLabel label = new JLabel("Your account balance is: " + "$" + balance); // Create a JLabel object.
			label.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18));  // change the font and its size.
			JOptionPane.showMessageDialog(null,label); // Show a message and the account balance.
		} // end of if statement.

		// If the user selects Deposit, do the following:
		else if (answer.equals("Deposit")) {
			JLabel label = new JLabel("Please, enter the deposit amount! "); // Create a JLabel object.
			label.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // change the font and its size.
			input = JOptionPane.showInputDialog(null, label); // Ask for an input from the user.

			// Check whether the entered values is a real number.
			try {
				deposit = Double.parseDouble(input); // Convert the entered value of type string to double.
				balance += deposit; // Add the entered value to the account balance. 
				JLabel label2 = new JLabel("Your account balance is now: " + "$" + balance); // Create a JLabel object.
				label2.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // change the font and its size.
				JOptionPane.showMessageDialog(null,label2); // Show a message and the account balance.
			} // End of try statement.

			// If the enters value was not a real number, do the following:
			catch ( NumberFormatException e ) {
				JLabel label3 = new JLabel("Not a legal number."); // Create a JLabel object.
				label3.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // change the font and its size.
				JOptionPane.showMessageDialog(null,label3); // Show the warning message.

				check(); // Call the method itself to select an option.
			} // End of catch statement.
		} // end of else if statement.

		// If the user selects Withdrawal, do the following:
		else if (answer.equals("Withdrawal")) {
			JLabel label = new JLabel("Please, enter the withdrawal amount! "); // Create a JLabel object.
			label.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // change the font and its size.
			input = JOptionPane.showInputDialog(null, label); // Ask for an input from the user.

			// Check whether the entered values is a real number.
			try {
				withdrawal = Double.parseDouble(input); // Convert the entered value of type string to double.
				balance -= withdrawal; // Subtract the entered value from the account balance.
				JLabel label2 = new JLabel("Your account balance is now: " + "$" + balance); // Create a JLabel object.
				label2.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // change the font and its size.
				JOptionPane.showMessageDialog(null,label2); // Show a message and the account balance.
			}

			// If the enters value was not a real number, do the following:
			catch ( NumberFormatException e ) {
				JLabel label3 = new JLabel("Not a legal number."); // Create a JLabel object.
				label3.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // change the font and its size.
				JOptionPane.showMessageDialog(null,label3); // Show the warning message.

				check(); // Call the method itself to select an option.
			} // End of catch statement.
		} // end of else if statement.

		// If the user selects Pin Change, do the following:
		else if (answer.equals("Pin Change")) {
			pinChange(); // Call the pinChange method to change the pin.
		} // end of else if statement.

		// If the user selects YES, do the following:
		else if (answer.equals("YES")) {
			MyBank.dashboard(); // Call the dashboard method in the MyBank class to show the transactions again.
		} // end of else if statement.

		// If the user selects NO, do the following:
		else if (answer.equals("NO")) {
			JLabel label = new JLabel("Thank you for banking with us"); // Create a JLabel object.
			label.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // change the font and its size.
			JOptionPane.showMessageDialog(null,label); // Show a goodbye  message.
			System.exit(0); // End the application.
		} // end of else if statement.

		// If the user selects Quit, do the following:
		else if (answer.equals("Quit")) {
			JLabel label = new JLabel("Thank you for banking with us"); // Create a JLabel object.
			label.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // change the font and its size.
			JOptionPane.showMessageDialog(null,label); // Show a goodbye  message.
			System.exit(0); // End the application.
		} // end of else if statement.

		// If the user selects anything else, do the following:
		else {
			JLabel label = new JLabel("We did not get a valid answer. Please try again!"); // Create a JLabel object.
			label.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // change the font and its size.
			JOptionPane.showMessageDialog(null,label); // Show the warning message.
		} // end of else statement.

		return answer; // Return the answer to be used in another part of the program.

	} // end of check method.


	// the class method access.
	static void access() {

		int n = 0;
		while (true) { // Repeatedly ask the user for a correct pin.
			JLabel label = new JLabel("Please, enter your pin: "); // Create a JLabel object.

			// Add a title
			label.setBorder(BorderFactory.createTitledBorder(null, "MY BANK", TitledBorder.CENTER, TitledBorder.TOP, new Font("Lucida Calligraphy", Font.BOLD, 20), Color.blue));
			label.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // change the font and its size.
			currentPin = JOptionPane.showInputDialog(null, label); // Ask for an input from the user.

			// If the entered pin is correct, do the following:
			if (currentPin.equals(pin)) {
				break; // the input value is OK, so jump out of loop
			} // end of if statement.

			n++; // increment the variable n.

			// If the variable n become 3, do the following:
			if (n == 3) {
				JLabel label3 = new JLabel("Sorry, you entered invalid pin for 3 times." + "\n" + "Please, try again later!"); // Create a JLabel object.
				label3.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // change the font and its size.
				JOptionPane.showMessageDialog(null,label3); // Show the warning message.
				System.exit(0); // End the application.
			}

			// If the entered pin is incorrect, do the following:
			JLabel label2 = new JLabel("Incorrect pin. Please, try again "); // Create a JLabel object.
			label2.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // change the font and its size.
			JOptionPane.showMessageDialog(null,label2); // Show the warning message.
		} // End of while loop.
	} // End of method access.


	// the instance method pinChange.
	void pinChange() {

		access(); // Call the access method first to ask for the correct pin.

		// If the entered pin is correct, do the following:
		JLabel label = new JLabel("Please, enter your new pin: "); // Create a JLabel object.
		label.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // change the font and its size.
		newPin = JOptionPane.showInputDialog(null, label); // Ask for an input from the user.
		currentPin = newPin; // Assign the new pin to the current pin variable.
		JLabel label2 = new JLabel("Your pin was successfully set to: " + " \" " + currentPin + " \" "); // Create a JLabel object.
		label2.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // change the font and its size.
		JOptionPane.showMessageDialog(null,label2); // Show the message.
	} // End of method pinChange.


	// This instance method initQuestionDialog is to to finish the initialization of the "question" instance variable.
	void initQuestionDialog() {
		this.question.setModal(true); // Call the setModal method to indicate that the dialog is modal.
		this.question.pack(); // call the pack() method to size the frame.
		this.question.setLocationRelativeTo(null); // Call the setLocationRelativeTo method to set the location of the window relative to the specified component.
	}

	// the instance method ask.
	String ask() {
		question.setVisible(true); // Call the setVisible method to make the frame appear on the screen.
		return question.answer;	// return the command string stored in the variable string in the the actionPerformed method in the ActionListener interface.
	} // end of method ask.

} // end of class Question
