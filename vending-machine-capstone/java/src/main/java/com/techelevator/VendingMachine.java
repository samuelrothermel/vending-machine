package com.techelevator;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {

    private LinkedHashMap<String, List<Item>> itemsInTheMachine = new LinkedHashMap<>();
    private LogWriter logWriter = new LogWriter();
    private BigDecimal balance = new BigDecimal(0);

    public BigDecimal getBalance() {
        return balance;
    }

    public void buyItem(String slot) {
        List<Item> itemListStockCount = itemsInTheMachine.get(slot);
        if (outOfStock(itemListStockCount)) {
            System.out.println("SOLD OUT");
        } else {
            Item item = itemListStockCount.get(0);
            BigDecimal itemPrice = item.getPrice();
            if (balance.doubleValue() < itemPrice.doubleValue()) {
                System.out.println("Please Insert More Money.");
            } else {
                System.out.println("Consuming " + item.getName() + "..." + item.getItemSound());
                BigDecimal startingBalance = balance;
                balance = balance.subtract(itemPrice);
                logWriter.logPurchase(slot, item, startingBalance, balance);
                itemListStockCount.remove(0);
            }

        }
    }

    public void returnChange() {
        if (balance.doubleValue() > 0) {
            ChangeRegister changeRegister = new ChangeRegister();
            BigDecimal changeGiven = getBalance();

            changeRegister.returnChange(balance);

            resetBalance();

            logWriter.logChange(changeGiven, getBalance());
        }
    }

    private boolean outOfStock(List<Item> items) {
        return items.isEmpty();
    }

    public void feedMoney(BigDecimal money) {
        balance = balance.add(money);
        System.out.println("Inserted $" + money.toString() + " dollars.");
        logWriter.logFeed(money, balance);
    }

    public void displayItems() {
        System.out.println(String.format("%s, %s, %s, %s", "Slot", "Name", "Price", "Count"));
        for (Map.Entry<String, List<Item>> entry : itemsInTheMachine.entrySet()) {
            if (!outOfStock(entry.getValue())) {
                String itemSlot = entry.getKey();
                String itemName = entry.getValue().get(0).getName();
                String itemPrice = "$" + entry.getValue().get(0).getPrice().toString();
                String itemCount = String.valueOf(entry.getValue().size());

                System.out.println(String.format("%s, %s, %s, %s",
                        itemSlot, itemName, itemPrice, itemCount));
            } else {
                String itemSlot = entry.getKey();
                System.out.println(String.format("%s", itemSlot, "SOLD OUT"));
            }
        }
    }

    public Map<String, List<Item>> getItemsInTheMachine() {
        return itemsInTheMachine;
    }

    public void resetBalance() {
        balance = new BigDecimal(0);
    }

    public void loadInventory() {
        Loader loader = new Loader();
        itemsInTheMachine = loader.passImportMapToVendingMachine();
    }

}

