package com.techelevator;

import com.techelevator.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.LinkedHashMap;

public class ChangeRegister {

    private int quarterAmt = 0;
    private int dimeAmt = 0;
    private int nickelAmt = 0;

    private LinkedHashMap<String, List<Item>> itemsInTheMachine = new LinkedHashMap<>();
    private LogWriter logWriter = new LogWriter();


    public void returnChange(BigDecimal balance) {
        if (balance.doubleValue() > 0) {
            String startingBalance = balance.toPlainString();
            double currentBalance = balance.doubleValue() * 100;
            quarterAmt = (int) (currentBalance / 25);
            currentBalance -= quarterAmt * 25;
            dimeAmt = (int) (currentBalance / 10);
            currentBalance -= dimeAmt * 10;
            nickelAmt = (int) (currentBalance / 5);
            currentBalance -= nickelAmt * 5;

            System.out.println("Your change is " + quarterAmt + " quarters " + dimeAmt + " dimes " + nickelAmt + " nickels.");
        }
    }
}
