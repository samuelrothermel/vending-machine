package com.techelevator;

import java.math.BigDecimal;

public class Item {

    protected String name;
    protected BigDecimal price;
    protected String itemSound;

    public Item(String name, BigDecimal price) {

        this.name = name;
        this.price = price;

    }

    public String getItemSound() {
        return itemSound;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
