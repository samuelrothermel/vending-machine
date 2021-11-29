package com.techelevator;

import com.sun.source.tree.Tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class Loader {

    private static final int PRODUCT_SLOT = 0;
    private static final int PRODUCT_NAME = 1;
    private static final int PRODUCT_PRICE = 2;
    private static final int PRODUCT_TYPE = 3;
    private static final int PRODUCT_INITIAL_QUANTITY = 5;

    private String fileName = "vendingmachine.csv";
    private File inventoryFile = new File(fileName);

    public LinkedHashMap<String, List<Item>> itemsInVendingMachine = new LinkedHashMap<>();

    private List<String> getItemFromLine() {
        List<String> listOfItems = new ArrayList<String>();
        try (Scanner fileScanner = new Scanner(inventoryFile)) {
            while (fileScanner.hasNextLine()) {
                listOfItems.add(fileScanner.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Could not load file: " + ex.getMessage());
        }
        return listOfItems;
    }

    private void createInventoryMap (List<String> listOfItems) {
        for (String line : listOfItems) {
            String[] inputFileItems = line.split("\\|");
            List<Item> itemList = new ArrayList<>();
            String slot = inputFileItems[PRODUCT_SLOT];
            String name = inputFileItems[PRODUCT_NAME];
            String type = inputFileItems[PRODUCT_TYPE];
            BigDecimal price = new BigDecimal(inputFileItems[PRODUCT_PRICE]);
            if (type.equals("Chip")) {
                Item item = new Chip(name, price);
                itemLoader(itemList, slot, item);
            } else if (type.equals("Candy")) {
                Item item = new Candy(name, price);
                itemLoader(itemList, slot, item);
            } else if (type.equals("Drink")) {
                Item item = new Drink(name, price);
                itemLoader(itemList, slot, item);
            } else if (type.equals("Gum")) {
                Item item = new Gum(name, price);
                itemLoader(itemList, slot, item);
            }
        }
    }

    private void itemLoader (List<Item> itemList, String slot, Item item) {
        for (int i = 0; i < PRODUCT_INITIAL_QUANTITY; i++) {
            itemList.add(item);
            itemsInVendingMachine.put(slot, itemList);
        }
    }

    public LinkedHashMap<String, List<Item>> passImportMapToVendingMachine() {
        List<String> listOfItems = getItemFromLine();
        createInventoryMap(listOfItems);
        return itemsInVendingMachine;
    }

    //    public List<String[]> inventoryArrayList() {
//        List<String[]> inventoryArray = new ArrayList<String[]>();
//
//        for (String currentString : getItemFromLine()) {
//            inventoryArray.add(currentString.split("\\|"));
//        }
//        return inventoryArray;
//    }

}



