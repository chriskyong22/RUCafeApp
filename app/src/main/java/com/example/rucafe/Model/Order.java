package com.example.rucafe.Model;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The Order class holds the list of all the items in the order and
 * calculates the order's total cost.
 * @author Christopher Yong, Maya Ravichandran
 */
public class Order implements Customizable {
    private static int generatingOrderNumbers = 1;
    private int orderNumber;
    private double subTotalCost;
    private double totalCost;
    private ArrayList<MenuItem> items;
    public static final double SALES_TAX_RATE = 0.06625;

    /**
     * Constructor for the Order class that initializes an empty list of
     * items, sets the total cost to zero, and sets a new order number.
     */
    public Order() {
        this.items = new ArrayList<>();
        this.orderNumber = generatingOrderNumbers++;
        this.subTotalCost = 0;
        this.totalCost = 0;
    }

    /**
     * The add method adds an item to the order.
     * @param obj the item to be added to the order
     * @return true if the item was added successfully, false if the item was
     *          not an instance of MenuItem
     */
    @Override
    public boolean add(Object obj) {
        if (!(obj instanceof MenuItem)) {
            return false;
        }
        items.add((MenuItem) obj);
        return true;
    }

    /**
     * The remove method removes an item from the order.
     * @param obj the item to be removed from the order
     * @return true if the item was removed successfully, false if the item
     *          was not an instance of MenuItem
     */
    @Override
    public boolean remove(Object obj) {
        if (!(obj instanceof MenuItem)) {
            return false;
        }
        return items.remove(obj);
    }

    /**
     * Gets the number of menu items in the order.
     * @return the number menu items in the order
     */
    public int getNumberOfMenuItems() {
        return items.size();
    }

    /**
     * Gets the menu item at the specified position in the list of items.
     * @param index the index of the item being fetched
     * @return the MenuItem being fetched
     */
    public MenuItem getItem(int index) {
        return items.get(index);
    }

    /**
     * Gets the order number of this order.
     * @return the order number
     */
    public int getOrderNumber() {
        return this.orderNumber;
    }

    /**
     * Calculates the subtotal cost by summing up the cost of each item in the
     * list and sets the corresponding field to this value.
     */
    public void calculateSubTotalCost() {
        this.subTotalCost = 0;
        for(MenuItem item : items) {
            item.itemPrice();
            subTotalCost += item.getItemPrice();
        }
    }

    /**
     * Gets the subtotal cost of this order.
     * Assumes calculateSubTotalCost() was called beforehand.
     * @return the subtotal cost
     */
    public double getSubTotalCost() {
        return this.subTotalCost;
    }

    /**
     * Calculates the total cost of this order by adding the sales tax to the
     * subtotal cost and sets the corresponding field to this value.
     * Assumes calculateSubTotalCost() was called beforehand.
     */
    public void calculateTotalCost() {
        this.totalCost = this.subTotalCost + getSalesTax();
    }

    /**
     * Calculates the sales tax charge for this order by multiplying the
     * subtotal cost by the tax rate and returns this value.
     * Assumes calculateSubTotalCost() was called beforehand.
     * @return the sales tax charge of this order
     */
    public double getSalesTax() {
        return this.subTotalCost * SALES_TAX_RATE;
    }

    /**
     * Gets the total cost of this order.
     * Assumes calculateTotalCost() was called beforehand.
     * @return the total cost of this order
     */
    public double getTotalCost() {
        return this.totalCost;
    }

    /**
     * Creates an ArrayList of all the menu items in this order converted to
     * String form and returns this ArrayList.
     * @return an ArrayList of all the menu items in String form.
     */
    public ArrayList<String> stringifiedMenuItems() {
        ArrayList<String> toReturn = new ArrayList<>();
        for (MenuItem item : items) {
            toReturn.add(item.toString());
        }
        return toReturn;
    }

    /**
     * Creates a String representation of the order with the order number,
     * list of menu items, and the total price.
     * @return a String representation of the order
     */
    @Override
    public String toString() {
        String orderString = "[ORDER NUMBER " + orderNumber + "]\n";
        for (MenuItem item : items) {
            orderString += (item.toString()) + "\n";
        }
        this.calculateSubTotalCost();
        this.calculateTotalCost();
        DecimalFormat decimalFormat = new DecimalFormat("'$'#,##0.00");
        orderString += "[TOTAL PRICE: " + decimalFormat.format(
                this.getTotalCost()) + "]\n";
        return orderString;
    }

    /**
     * Checks if two orders are equal via their order numbers.
     * @param obj the object to compare to
     * @return true if the orders match, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if (!(obj instanceof Order)) {
            return false;
        }
        Order temp = (Order) obj;
        return this.orderNumber == temp.orderNumber;
    }


}
