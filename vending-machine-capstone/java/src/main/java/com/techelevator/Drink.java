package com.techelevator;

import java.math.BigDecimal;

public class Drink extends Item {

    public Drink(String name, BigDecimal price) {
        super(name, price);
    }

    public String toString() {
        return name + " " + price;
    }

    @Override
    public String getItemSound() {
        return "Glug Glug, Yum!";
    }

}
