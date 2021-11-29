package com.techelevator;

import java.math.BigDecimal;

public class Candy extends Item {

    public Candy(String name, BigDecimal price) {
        super(name, price);
    }

    public String toString() {
        return name + " " + price;
    }

    @Override
    public String getItemSound() {
        return "Munch Munch, Yum!";
    }

}

