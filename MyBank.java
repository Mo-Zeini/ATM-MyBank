package mybank;

/*
 * This application is an ATM for a bank that allows users to deposit and Withdrawal money
 * using Swing GUI Java.
 * It also lets users inquire about the account balance and change the pin if needed.
 */

public class MyBank {

	public static void main(String[] args) {

		// Call the access method in order to check whether the inserted pin is correct.
		Question.access();

		dashboard(); // Call the dashboard() method to call the constructors.
	} // end of main()

	static void dashboard() {

		// First, the multiple-choice question object.
		Question question1 = new MultipleChoiceQuestion("Please, select a transaction from the menu below!");
		question1.check(); // Call the check method to check the selected answer.

		// Second, the true or false question object.
		Question question2 = new YesNoQuestion("Would you like to make another transaction?");
		question2.check(); // Call the check method to check the selected answer.
	} // end of method dashboard

} // end of class Quiz
