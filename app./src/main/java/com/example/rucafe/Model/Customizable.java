package com.example.rucafe.Model;

/**
 * The Customizable interface ensures that those classes that implement it
 * can add and remove items from a list that the class contains.
 * @author Christopher Yong, Maya Ravichandran
 */
public interface Customizable {
    /**
     * Method to add an object to associated object/class.
     * (addins for coffee, menuitems for orders, and order for storedOrders)
     * @param obj the object to add
     * @return true if add succeeded, otherwise false
     */
    boolean add(Object obj);

    /**
     * Method to remove an object from associated object/class.
     * (addins for coffee, menuitems for orders, and order for storedOrders)
     * @param obj the object to remove
     * @return true if remove succeeded, otherwise false
     */
    boolean remove(Object obj);
}
