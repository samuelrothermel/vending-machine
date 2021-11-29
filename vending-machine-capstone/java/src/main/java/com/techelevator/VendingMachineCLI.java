package com.techelevator;

import com.techelevator.view.Menu;

import java.math.BigDecimal;

public class VendingMachineCLI {


    private final VendingMachine vendingMachine = new VendingMachine();

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};

    private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY,
            PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

    private final Menu menu;

    public VendingMachineCLI(Menu menu) {
        this.menu = menu;
    }

    public void run() {

        vendingMachine.loadInventory();

        while (true) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                vendingMachine.displayItems();
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                System.out.println("Current Balance: $" + vendingMachine.getBalance().toString());
                String purchaseMenuChoiceFromOption = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

                if (purchaseMenuChoiceFromOption.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {

                    menu.feedMoneyMenu(vendingMachine);
                } else if (purchaseMenuChoiceFromOption.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
                    vendingMachine.displayItems();
                    menu.purchaseMenu(vendingMachine);
                } else if (purchaseMenuChoiceFromOption.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
                    vendingMachine.returnChange();
                }
            } else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
                System.out.println("Goodbye!");
                vendingMachine.returnChange();
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }
}
