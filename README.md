# ATM-MyBank

## Overview

ATM-MyBank is a Java-based application simulating the functionalities of an Automated Teller Machine (ATM) for a banking system. This application allows users to perform basic banking operations such as checking account balances, depositing money, and withdrawing cash. Users can securely log in to their accounts and view their transaction history.

## Features

- User authentication (login)
- Check account balance
- Deposit funds
- Withdraw funds
- View transaction history
- Simple command-line interface

## Requirements

- Java Development Kit (JDK) 8 or higher

## Usage

1. Follow the on-screen prompts to log in to your account.

2. Once logged in, you can:
    - Check Account Balance
    - Deposit Funds
    - Withdraw Funds
    - View Transaction History
    - Exit

## File Structure

- `MultipleChoiceQuestion.java`: Handles multiple choice questions for user interaction.
- `MyBank.java`: Main class for the bank ATM application.
- `Question.java`: Abstract class for question types.
- `QuestionDialog.java`: Manages dialog interactions for questions.
- `YesNoQuestion.java`: Handles yes/no questions for user interaction.

## Example

Upon running the application, you will see the following options:

Welcome to MyBank ATM

Please log in to continue.

Enter User ID: [user inputs ID]
Enter PIN: [user inputs PIN]

--- Main Menu ---

Check Account Balance
Deposit Funds
Withdraw Funds
View Transaction History
Exit
Enter your choice:

Follow the prompts to perform the desired action.

## License

This project is licensed under the MIT License. See the [LICENSE](https://github.com/Mo-Zeini/ATM-MyBank/blob/main/LICENSE.txt) file for details.

## Author

- [Mohamed Elzeini](https://github.com/Mo-Zeini)

## Acknowledgements

- This application was developed as a learning project to simulate ATM functionalities in a banking system using Java.
