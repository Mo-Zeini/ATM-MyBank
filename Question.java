package mybank;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public abstract class Question {

	QuestionDialog question;  // Declare QuestionDialog variable.
	double balance = 0; // Declare and initialize the variable. 
	static String currentUsername;
	static String currentPin; // Declare the class variable.
	String input; // Declare the variable. 

	// Database connection details
	static String url = "jdbc:mysql://localhost:3306/bankaccounts"; // Database URL
	static String username = "root"; // MySQL username
	static String password = "password"; // MySQL password
	
	// Question Constructor.
	Question(String question) {
		this.question = new QuestionDialog(); // The QuestionDialog object.
		this.question.setLayout(new GridLayout(0,1)); // Set one column.
		JLabel label = new JLabel(" "+question+" ",JLabel.CENTER); // JLabel object in the center.
		label.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // change the font and its size.
		this.question.add(label); // Add the question.

		// Add title MY BANK
		label.setBorder(BorderFactory.createTitledBorder(null, "MY BANK", TitledBorder.CENTER, TitledBorder.TOP, new Font("Lucida Calligraphy", Font.BOLD, 20), Color.blue));
	}

	// Method to fetch the balance from the database for the current user
	private double fetchBalanceFromDatabase() {
		try (Connection connection = DriverManager.getConnection(url, username, password)) { // Establish a connection to the database
			// Prepare the SQL statement
			String sql = "SELECT balance FROM accounts WHERE username = ?";
			PreparedStatement statement = connection.prepareStatement(sql);

			// Replace "current_user" with the actual username of the current user
			statement.setString(1, currentUsername);

			// Execute the query
			ResultSet resultSet = statement.executeQuery();

			// Retrieve the balance from the result set
			if (resultSet.next()) {
				balance = resultSet.getDouble("balance");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle any exceptions that occur during database operations
		}

		return balance;
	}

	// Method to update the balance in the database for the current user
	private void updateBalanceInDatabase(double newBalance) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) { // Establish a connection to the database
			// Prepare the SQL statement
			String sql = "UPDATE accounts SET balance = ? WHERE username = ?";
			PreparedStatement statement = connection.prepareStatement(sql);

			// Replace "current_user" with the actual username of the current user
			statement.setDouble(1, newBalance);
			statement.setString(2, currentUsername);

			// Execute the update
			int rowsAffected = statement.executeUpdate();

			// Check if the update was successful
			if (rowsAffected > 0) {
				System.out.println("Balance updated successfully.");
			} else {
				System.out.println("Failed to update balance.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle any exceptions that occur during database operations
		}
	}


	String check() {

		// Call the ask function and assign its return value to the local variable answer.
		String answer = ask();

		// If the user selects Balance Inquiry, fetch and display the balance from the database:
		if (answer.equals("Balance Inquiry")) { 
			// Access the balance column in the database for the current user and fetch the value
			// Assign the fetched value to the variable balanceFromDB
			double balanceFromDB = fetchBalanceFromDatabase();

			JLabel label = new JLabel("Your account balance is: $" + balanceFromDB); // Create a JLabel object.
			label.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18));  // change the font and its size.
			JOptionPane.showMessageDialog(null, label); // Show a message with the account balance.
		} // end of if statement.

		// If the user selects Deposit, perform the deposit operation:
		else if (answer.equals("Deposit")) {
			JLabel label = new JLabel("Please enter the deposit amount: "); // Create a JLabel object.
			label.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // change the font and its size.
			input = JOptionPane.showInputDialog(null, label); // Ask for an input from the user.

			try {
				// Parse the entered value and ensure it is a positive number
				double deposit = Double.parseDouble(input);
				if (deposit < 0) {
					throw new IllegalArgumentException("Negative deposit amount not allowed.");
				}

				// Access the balance column in the database for the current user and fetch the current balance
				double currentBalance = fetchBalanceFromDatabase();

				// Calculate the new balance after the deposit
				double newBalance = currentBalance + deposit;

				// Update the balance column in the database with the new balance
				updateBalanceInDatabase(newBalance);

				JLabel label2 = new JLabel("Your account balance is now: $" + newBalance); // Create a JLabel object.
				label2.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // change the font and its size.
				JOptionPane.showMessageDialog(null, label2); // Show a message with the updated account balance.
			}
			catch (NumberFormatException e) {
				JLabel label3 = new JLabel("Not a legal number."); // Create a JLabel object.
				label3.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // change the font and its size.
				JOptionPane.showMessageDialog(null, label3); // Show the warning message.
				check(); // Call the method itself to select an option.
			}
			catch (IllegalArgumentException e) {
				JLabel label3 = new JLabel(e.getMessage()); // Create a JLabel object.
				label3.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // change the font and its size.
				JOptionPane.showMessageDialog(null, label3); // Show the warning message.
				check(); // Call the method itself to select an option.
			}
		} // end of else if statement.

		// If the user selects Withdrawal, perform the withdrawal operation:
		else if (answer.equals("Withdrawal")) {
			JLabel label = new JLabel("Please enter the withdrawal amount: "); // Create a JLabel object.
			label.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // change the font and its size.
			input = JOptionPane.showInputDialog(null, label); // Ask for an input from the user.

			try {
				// Parse the entered value and ensure it is a positive number
				double withdrawal = Double.parseDouble(input);
				if (withdrawal < 0) {
					throw new IllegalArgumentException("Negative withdrawal amount not allowed.");
				}

				// Access the balance column in the database for the current user and fetch the current balance
				double currentBalance = fetchBalanceFromDatabase();

				// Check if the withdrawal amount exceeds the current balance
				if (withdrawal > currentBalance) {
					throw new IllegalArgumentException("Insufficient funds.");
				}

				// Calculate the new balance after the withdrawal
				double newBalance = currentBalance - withdrawal;

				// Update the balance column in the database with the new balance
				updateBalanceInDatabase(newBalance);

				JLabel label2 = new JLabel("Your account balance is now: $" + newBalance); // Create a JLabel object.
				label2.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // change the font and its size.
				JOptionPane.showMessageDialog(null, label2); // Show a message with the updated account balance.
			}
			catch (NumberFormatException e) {
				JLabel label3 = new JLabel("Not a legal number."); // Create a JLabel object.
				label3.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // change the font and its size.
				JOptionPane.showMessageDialog(null, label3); // Show the warning message.
				check(); // Call the method itself to select an option.
			}
			catch (IllegalArgumentException e) {
				JLabel label3 = new JLabel(e.getMessage()); // Create a JLabel object.
				label3.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // change the font and its size.
				JOptionPane.showMessageDialog(null, label3); // Show the warning message.
				check(); // Call the method itself to select an option.
			}
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
		int maxAttempts = 3; // Maximum number of attempts for entering the correct current pin
		int attemptCount = 0;
		// Ask the user to input a correct pin for three times only.
		while (attemptCount < maxAttempts) {
			JLabel label = new JLabel("Please enter your username: "); // Create a JLabel object.
			label.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // Change the font and its size.
			currentUsername = JOptionPane.showInputDialog(null, label); // Ask for an input from the user.

			JLabel pinLabel = new JLabel("Please enter your 4-digit PIN: "); // Create a JLabel object.
			pinLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // Change the font and its size.
			currentPin = JOptionPane.showInputDialog(null, pinLabel); // Ask for an input from the user.

			try {
				Connection connection = DriverManager.getConnection(url, username, password); // Establish a connection to the database
				String query = "SELECT username, pin FROM accounts WHERE username = ? AND pin = ?"; // Prepare the SQL query
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, currentUsername); // Set the username parameter
				preparedStatement.setString(2, currentPin); // Set the current pin parameter
				ResultSet resultSet = preparedStatement.executeQuery(); // Execute the query and retrieve the result

				if (resultSet.next()) {
					break; // Matching record found, exit the loop
				} else {
					attemptCount++;
					JLabel incorrectLabel = new JLabel("Incorrect username or PIN. Please try again."); // Create a JLabel object.
					incorrectLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // Change the font and its size.
					JOptionPane.showMessageDialog(null, incorrectLabel); // Show the message.
				}

				resultSet.close(); // Close the result set
				preparedStatement.close(); // Close the prepared statement
				connection.close(); // Close the database connection
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// Do the following when the user exceeds their login attempts.
		if (attemptCount == maxAttempts) {
			JLabel maxAttemptsLabel = new JLabel("Maximum login attempts exceeded. Exiting application."); // Create a JLabel object.
			maxAttemptsLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // Change the font and its size.
			JOptionPane.showMessageDialog(null, maxAttemptsLabel); // Show the message.
			System.exit(0); // End the application.
		} else {
			JLabel successLabel = new JLabel("Login successful! Welcome, " + currentUsername + "!"); // Create a JLabel object.
			successLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // Change the font and its size.
			JOptionPane.showMessageDialog(null, successLabel); // Show the message.
		}
	} // End of method access

	// the instance method pinChange.
	void pinChange() {
		int maxAttempts = 3; // Maximum number of attempts for entering the correct current pin
		int attemptCount = 0;

		while (attemptCount < maxAttempts) {
			JLabel pinLabel = new JLabel("Please enter your current 4-digit PIN: "); // Create a JLabel object.
			pinLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // Change the font and its size.
			currentPin = JOptionPane.showInputDialog(null, pinLabel); // Ask for an input from the user.

			try {
				Connection connection = DriverManager.getConnection(url, username, password); // Establish a connection to the database

				String query = "SELECT pin FROM accounts WHERE username = ? AND pin = ?"; // Prepare the SQL query
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, currentUsername); // Set the username parameter
				preparedStatement.setString(2, currentPin); // Set the current pin parameter

				ResultSet resultSet = preparedStatement.executeQuery(); // Execute the query and retrieve the result

				if (resultSet.next()) {
					break; // Correct PIN entered, exit the loop
				} else {
					attemptCount++;
					if (attemptCount == maxAttempts) {
						JLabel maxAttemptsLabel = new JLabel("Maximum login attempts exceeded. Exiting application."); // Create a JLabel object.
						maxAttemptsLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // Change the font and its size.
						JOptionPane.showMessageDialog(null, maxAttemptsLabel); // Show the message.
						System.exit(0); // End the application.
					} else {
						JLabel incorrectPinLabel = new JLabel("Incorrect PIN. Please try again."); // Create a JLabel object.
						incorrectPinLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // Change the font and its size.
						JOptionPane.showMessageDialog(null, incorrectPinLabel); // Show the message.
					}
				}

				resultSet.close(); // Close the result set
				preparedStatement.close(); // Close the prepared statement
				connection.close(); // Close the database connection
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		String newPin = "";
		String confirmPin = "";

		do {
			JLabel newPinLabel = new JLabel("Please enter your new 4-digit PIN: "); // Create a JLabel object.
			newPinLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // Change the font and its size.
			newPin = JOptionPane.showInputDialog(null, newPinLabel); // Ask for an input from the user.

			JLabel confirmPinLabel = new JLabel("Please confirm your new 4-digit PIN: "); // Create a JLabel object.
			confirmPinLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // Change the font and its size.
			confirmPin = JOptionPane.showInputDialog(null, confirmPinLabel); // Ask for an input from the user.

			if (!newPin.equals(confirmPin)) {
				JLabel mismatchLabel = new JLabel("The entered PINs do not match. Please try again."); // Create a JLabel object.
				mismatchLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // Change the font and its size.
				JOptionPane.showMessageDialog(null, mismatchLabel); // Show the message.
			}
		} while (!newPin.equals(confirmPin));

		// Update the PIN in the database
		String updateQuery = "UPDATE accounts SET pin = ? WHERE username = ?";
		try {
			Connection connection = DriverManager.getConnection(url, username, password); // Establish a connection to the database

			PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, newPin); // Set the new pin parameter
			updateStatement.setString(2, currentUsername); // Set the username parameter

			int rowsAffected = updateStatement.executeUpdate(); // Execute the update statement

			if (rowsAffected > 0) {
				JLabel successLabel = new JLabel("Your PIN was successfully changed to: " + newPin); // Create a JLabel object.
				successLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // Change the font and its size.
				JOptionPane.showMessageDialog(null, successLabel); // Show the message.
			} else {
				JLabel errorLabel = new JLabel("Failed to update PIN. Please try again."); // Create a JLabel object.
				errorLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18)); // Change the font and its size.
				JOptionPane.showMessageDialog(null, errorLabel); // Show the message.
			}

			updateStatement.close(); // Close the update statement
			connection.close(); // Close the database connection
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
