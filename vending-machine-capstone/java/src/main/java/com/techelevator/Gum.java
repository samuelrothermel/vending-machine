package com.techelevator;

import java.math.BigDecimal;

public class Gum extends Item {

    public Gum(String name, BigDecimal price) {
        super(name, price);
    }

    public String toString() {
        return name + " " + price;
    }

    @Override
    public String getItemSound() {
        return "Chew Chew, Yum!";
    }

}

