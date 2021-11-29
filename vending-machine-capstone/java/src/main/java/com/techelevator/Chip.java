package com.techelevator;

import java.math.BigDecimal;

public class Chip extends Item{

    public Chip(String name, BigDecimal price) {
        super(name, price);
    }

    public String toString() {
        return name + " " + price;
    }

    @Override
    public String getItemSound() {
        return "Crunch Crunch, Yum!";
    }

}

