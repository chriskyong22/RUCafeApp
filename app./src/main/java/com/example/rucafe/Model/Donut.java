package com.example.rucafe.Model;

/**
 * The Donut class handles all operations relating to manipulating donuts,
 * including setting the donut's flavor, changing its quantity, and
 * calculating its price.
 * @author Christopher Yong, Maya Ravichandran
 */
public class Donut extends MenuItem {
    private String donut;
    private int quantity;

    public static final double BASE_DONUT_PRICE = 1.39;

    /**
     * The constructor for the Donut class.
     * Sets the donut's type, flavor, and quantity to the values specified
     * in the method parameters.
     * @param donut the type of the donut
     * @param quantity the number of this type of donut being ordered
     */
    public Donut(String donut, int quantity) {
        this.donut = donut;
        this.quantity = quantity;
    }

    /**
     * Calculates the donut's price based on its type and quantity,
     * and updates the corresponding itemPrice field in the MenuItem
     * superclass with this value.
     */
    @Override
    public void itemPrice() {
        super.itemPrice = quantity * BASE_DONUT_PRICE;
    }

    /**
     * Creates the String representation of the donut in the format
     * "Type Flavor(Quantity)".
     * @return the String representation of the donut
     */
    @Override
    public String toString() {
        return donut + " (" + quantity + ")";
    }

    /**
     * Checks if two coffees are equal via their quantities, types,
     * and flavors.
     * @param o the object to compare to
     * @return true if the donuts match, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Donut)) return false;
        Donut donut = (Donut) o;
        return this.quantity == donut.quantity &&
                this.donut.equals(donut.donut);
    }

}
