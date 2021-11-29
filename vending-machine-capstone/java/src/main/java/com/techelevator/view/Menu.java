package com.techelevator.view;

import com.techelevator.ChangeRegister;
import com.techelevator.Item;
import com.techelevator.VendingMachine;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    private PrintWriter out;
    private Scanner in;
    private VendingMachine vendingMachine = new VendingMachine();

    public Menu(InputStream input, OutputStream output) {
        this.out = new PrintWriter(output);
        this.in = new Scanner(input);
    }

    public Object getChoiceFromOptions(Object[] options) {
        Object choice = null;
        while (choice == null) {
            displayMenuOptions(options);
            choice = getChoiceFromUserInput(options);
        }
        return choice;
    }

    private Object getChoiceFromUserInput(Object[] options) {
        Object choice = null;
        String userInput = in.nextLine();
        try {
            int selectedOption = Integer.valueOf(userInput);
            if (selectedOption > 0 && selectedOption <= options.length) {
                choice = options[selectedOption - 1];
            }
        } catch (NumberFormatException e) {
            // eat the exception, an error message will be displayed below since choice will be null
        }
        if (choice == null) {
            out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
        }
        return choice;
    }

    private void displayMenuOptions(Object[] options) {
        out.println();
        for (int i = 0; i < options.length; i++) {
            int optionNum = i + 1;
            out.println(optionNum + ") " + options[i]);
        }
        out.print(System.lineSeparator() + "Please choose an option >>> ");
        out.flush();
    }

    public void feedMoneyMenu(VendingMachine vendingMachine) {
        System.out.println("Current Money Provided: $" + vendingMachine.getBalance().toString());
        System.out.println("Please insert $1, $2, $5, or $10 dollars (enter dollar amount)");
        String userInput = in.nextLine();
        Integer currency = Integer.valueOf(userInput);
        vendingMachine.feedMoney(new BigDecimal(currency));
    }

    public void purchaseMenu(VendingMachine vendingMachine) {
        String input = in.nextLine().toUpperCase();
        vendingMachine.buyItem(input);
    }
}
