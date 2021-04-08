package com.example.rucafe.Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * The StoreOrders class stores a list of all orders placed and allows for
 * adding, removing, searching for, and exporting orders from this list.
 * @author Christopher Yong, Maya Ravichandran
 */
public class StoreOrders implements Customizable {
    private ArrayList<Order> orders;

    /**
     * Constructor for the StoreOrders class.
     * Creates an empty ArrayList to hold the orders.
     */
    public StoreOrders() {
        this.orders = new ArrayList<>();
    }

    /**
     * Gets a list of all order numbers of the orders that the user has
     * placed.
     * @return a list of all the order numbers that the user has placed
     */
    public ArrayList<String> getOrderNumbers() {
        if (orders.size() == 0) {
            return null;
        }
        ArrayList<String> orderNumbers = new ArrayList<>();
        for(Order order : orders) {
            orderNumbers.add(order.getOrderNumber() + "");
        }
        return orderNumbers;
    }

    /**
     * Searches for an order based on the given order number and returns the
     * order if found.
     * @param orderNumber the order number of the order being searched for
     * @return the order with the corresponding order number if found, null
     *          if not found
     */
    public Order findOrder(int orderNumber) {
        for (Order order : orders) {
            if (order.getOrderNumber() == orderNumber) {
                return order;
            }
        }
        return null;
    }

    /**
     * Adds an order to the list of orders placed by the user.
     * @param obj the order to be added to the list
     * @return true if the order was added successfully, false if the order
     *          was not an instance of Order
     */
    @Override
    public boolean add(Object obj) {
        if (!(obj instanceof Order)) {
            return false;
        }
        return orders.add((Order) obj);
    }

    /**
     * Removes an order from the list of orders placed by the user.
     * @param obj the order to be removed from the list
     * @return true if the order was removed successfully, false if the order
     *          was not an instance of Order
     */
    @Override
    public boolean remove(Object obj) {
        if (!(obj instanceof Order)) {
            return false;
        }
        return orders.remove(obj);
    }
}
