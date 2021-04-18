package com.example.rucafe.Model;

import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * The Coffee class handles all operations relating to manipulating coffees,
 * including adding and removing add-ins to the coffee, changing its quantity,
 * and calculating its price.
 * @author Christopher Yong, Maya Ravichandran
 */
public class Coffee extends MenuItem implements Customizable {
    private ArrayList<String> addins;
    private String size;
    private int quantity;

    public static final double BASE_PRICE = 1.99;
    public static final double ADDIN_PRICE = 0.20;
    public static final double TALL_PRICE = 0.50;
    public static final double GRANDE_PRICE = 1.00;
    public static final double VENTI_PRICE = 1.50;

    /**
     * The constructor for the Coffee class.
     * Initializes the list of add-ins to an empty ArrayList and sets the
     * coffee's size and quantity to the values specified in the method
     * parameters.
     * @param size the size of the coffee (small, tall, grande, venti)
     * @param quantity the number of this type of coffee being ordered
     */
    public Coffee(String size, int quantity) {
        this.addins = new ArrayList<>();
        this.size = size;
        this.quantity = quantity;
    }

    /**
     * Adds the specified object to the list of the coffee's add-ins.
     * @param obj a string containing the name of the add-in to be added
     * @return true if removed, otherwise false
     */
    @Override
    public boolean add(Object obj) {
        if (!(obj instanceof String)) {
            return false;
        }
        return addins.add((String) obj);
    }

    /**
     * Removes the specified object from the list of the coffee's add-ins.
     * @param obj a string containing the name of the add-in to be removed
     * @return true if removed, otherwise false
     */
    @Override
    public boolean remove(Object obj) {
        if (!(obj instanceof String)) {
            return false;
        }
        return addins.remove(obj);
    }

    /**
     * Calculates the coffee's price based on its size, number of add-ins,
     * and quantity, and updates the corresponding itemPrice field in the
     * MenuItem superclass with this value.
     */
    @Override
    public void itemPrice() {
        super.itemPrice = BASE_PRICE;
        switch(size) {
            case "Tall":
                super.itemPrice += TALL_PRICE;
                break;
            case "Grande":
                super.itemPrice += GRANDE_PRICE;
                break;
            case "Venti":
                super.itemPrice += VENTI_PRICE;
                break;
        }
        super.itemPrice += (addins.size() * ADDIN_PRICE);
        super.itemPrice *= quantity;
    }

    /**
     * Sets the size of the coffee to the specified value.
     * @param size the size of the coffee (Small, Tall, Grande, Venti)
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Sets the size of the coffee to the specified value.
     * @param quantity the number of this type of coffee being ordered
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Creates the String representation of the coffee in the format
     * "Coffee Size(Quantity) [Add-In1,Add-In2,...]".
     * @return the String representation of the coffee
     */
    @Override
    public String toString() {
        String toReturn = "Coffee " + size + "(" + quantity + ") [";
        StringJoiner join = new StringJoiner(",");
        addins.forEach((item) -> join.add(item));
        return toReturn + join + "]";
    }

    /**
     * Checks if two coffees are equal via their quantities, add-in lists,
     * and sizes.
     * @param o the object to compare to
     * @return true if the coffees match, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coffee)) return false;
        Coffee coffee = (Coffee) o;
        return this.quantity == coffee.quantity &&
                this.addins.equals(coffee.addins) &&
                this.size.equals(coffee.size);
    }

}
