package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rucafe.Model.Donut;
import com.example.rucafe.Model.Order;

import java.text.DecimalFormat;

public class CurrentOrderActivity extends AppCompatActivity {

    private static Order currentOrder = new Order();

    private TextView subTotal, salesTax, totalPrice;
    private Button removeItem, placeOrder;
    private RecyclerView currentOrderListView;
    private OrderAdapter orderAdapter;
    private RecyclerView.LayoutManager currentOrderLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        subTotal = findViewById(R.id.subTotalCurrentOrder);
        salesTax = findViewById(R.id.salesTaxCurrentOrder);
        totalPrice = findViewById(R.id.totalPriceCurrentOrder);
        removeItem = findViewById(R.id.removeItemOrder);
        placeOrder = findViewById(R.id.placeOrder);
        currentOrderListView = findViewById(R.id.currentOrderListView);
        updateList();
        updateCosts();
        checkEmptyOrder();
    }

    public void updateList() {
        orderAdapter = new OrderAdapter(currentOrder);
        currentOrderListView.setAdapter(orderAdapter);
        currentOrderLayoutManager = new LinearLayoutManager(this);
        currentOrderListView.setLayoutManager(currentOrderLayoutManager);
    }

    /**
     * Getter for the Current Order object to add the menu items.
     * @return Order object which represents the current order
     */
    public static Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Performs the necessary menu item calculations for each menu item and
     * updates the sub total, sales tax, and total price of all menu items
     * rounded to the nearest hundredths place.
     */
    public void updateCosts() {
        currentOrder.calculateSubTotalCost();
        DecimalFormat decimalFormat = new DecimalFormat("'$'#,##0.00");
        subTotal.setText(decimalFormat.format(currentOrder
                .getSubTotalCost()));
        salesTax.setText(decimalFormat.format(currentOrder.getSalesTax()));
        currentOrder.calculateTotalCost();
        totalPrice.setText(decimalFormat.format(currentOrder.getTotalCost()));
    }

    /**
     * Disables the place order and remove item buttons.
     */
    private void disableButtons() {
        placeOrder.setEnabled(false);
        removeItem.setEnabled(false);
    }

    /**
     * Handles the removal of a selected item in the menu item listview.
     * Updates the current order object and the sub total, sales tax, and
     * total price and the menu item listview.
     * If no item was selected, it will display a warning alert that tells
     * the user to select an item from the menu item list view.
     * If the user removes the last item in the list view, it will display
     * a warning and disable the buttons.
     */
    public void handleRemoveItem(View v) {
        int selectedIndex = orderAdapter.getSelected();
        if (selectedIndex < 0) {
            Toast.makeText(this, "Please select a valid item from the list!",
                    Toast.LENGTH_LONG).show();
        } else {
            currentOrder.remove(currentOrder.getItem(selectedIndex));
            orderAdapter.notifyItemRemoved(selectedIndex);
            orderAdapter.resetSelection();
            updateCosts();
            checkEmptyOrder();
        }
    }

    /**
     * Checks if the current order has no menu items in it.
     * If so, it will generate a popup warning to notify the user that they
     * need to add some menu items.
     * It will also disable the Place Order and Remove Item buttons.
     * @return true if empty, otherwise false
     */
    public boolean checkEmptyOrder() {
        if (currentOrder.getNumberOfMenuItems() == 0) {
            generateEmptyWarning();
            disableButtons();
            return true;
        }
        return false;
    }

    /**
     * Generates the alert message for an empty cart (current order has no
     * menu items).
     */
    private void generateEmptyWarning() {
        Toast.makeText(this, "There are no current items in the cart!" +
                " Please navigate back to the menu and select some items" +
                " to checkout!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Handles the adding to the stored orders.
     * If there are no items currently in the current order, it will not
     * add it to the stored orders and just display a warning instead and
     * disables the buttons.
     * Upon addition of the current order to the stored orders, it will
     * generate a new order object and update the sub total, sales, and total
     * price and the list view and disable all the buttons and display a
     * alert indicating it has successfully added the current order to the
     * stored orders.
     */
    public void addToStoredOrders(View v) {
        disableButtons();
        if (checkEmptyOrder()) {
            return;
        }
        StoredOrdersActivity.getOrders().add(currentOrder);
        currentOrder = new Order();
        updateList();
        updateCosts();
        Toast.makeText(this, "Successfully added your current order! " +
                "To view your previous orders, please click on the" +
                " clipboard icon in the main menu", Toast.LENGTH_SHORT).show();
    }

}