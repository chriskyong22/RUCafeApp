package com.example.rucafe.Model;

/**
 * The MenuItem class is used for the different menu items, such as
 * coffee and donuts, and contains the price of the menu item.
 * @author Christopher Yong, Maya Ravichandran
 */
public class MenuItem {
    protected double itemPrice;

    /**
     * Constructor for the MenuItem class that initializes the menu item's
     * price to 0.
     */
    public MenuItem() {
        this.itemPrice = 0;
    }

    /**
     * The itemPrice() function is a method for setting the itemPrice field's
     * value that is implemented in the MenuItem's specific subclasses.
     */
    public void itemPrice() { }

    /**
     * Gets the menu item's price.
     * @return the menu item's price
     */
    public double getItemPrice() {
        return this.itemPrice;
    }

}
