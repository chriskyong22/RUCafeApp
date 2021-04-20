package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rucafe.Model.MenuItem;
import com.example.rucafe.Model.Order;
import com.example.rucafe.Model.StoreOrders;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * StoreOrders controller to link the StoreOrders View to the StoreOrders
 * Model.
 * It updates the list view and total price upon selection and removal
 * of an order. In addition, you can export ALL stored orders to a text file.
 * @author Christopher Yong, Maya Ravichandran
 */

public class StoredOrdersActivity extends AppCompatActivity {

    private static StoreOrders orders = new StoreOrders();
    private Button cancelOrder;
    private RecyclerView storedOrderListView;
    private TextView totalPrice, subTotal,salesTax;
    private Spinner orderComboBox;
    private OrderAdapter orderAdapter;
    private RecyclerView.LayoutManager currentOrderLayoutManager;

    /**
     * Initializes the views in the layout.
     * Sets the order numbers and the recycler view and retrieves all the view references
     * so it can be used.
     * @param savedInstanceState savedInstanceState if provided
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stored_orders);
        cancelOrder = findViewById(R.id.cancelOrder);
        totalPrice = findViewById(R.id.totalPriceStoredOrders);
        totalPrice.setText(getString(R.string.default_price));
        subTotal = findViewById(R.id.subTotalStoredOrders);
        subTotal.setText(getString(R.string.default_price));
        salesTax = findViewById(R.id.salesTaxStoredOrders);
        salesTax.setText(getString(R.string.default_price));
        storedOrderListView = findViewById(R.id.storedOrderListView);
        if (checkEmptyStoredOrders()) {
            orderAdapter = new OrderAdapter(new ArrayList<MenuItem>());
            storedOrderListView.setAdapter(orderAdapter);
            currentOrderLayoutManager = new LinearLayoutManager(this);
            storedOrderListView.setLayoutManager(currentOrderLayoutManager);
            return;
        }
        orderComboBox = findViewById(R.id.storedOrderSpinner);
        updateSpinner();
        handleSelectedOrder();
        orderComboBox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * EventListener for item selected in the order selection spinner.
             * @param parent parent adapterview
             * @param view view associated with the listener
             * @param position position selected
             * @param id id
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                handleSelectedOrder();
            }

            /**
             * EventListener for no item selected in the order selection spinner.
             * @param parent parent adapterview
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    /**
     * Updates the spinner that holds all the order numbers that were not deleted.
     */
    private void updateSpinner() {
        if(orders.getOrderNumbers() == null) {
            orderComboBox.setAdapter(null);
            return;
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(StoredOrdersActivity.this,
                R.layout.support_simple_spinner_dropdown_item, orders.getOrderNumbers());
        orderComboBox.setAdapter(spinnerAdapter);
    }

    /**
     * Handles the selection of an order in the order spinner.
     * Upon selection, it will update the order displayed in the recycler view
     * and associated total price.
     * If there are no orders, it will generate an Toast warning the user that
     * there are no orders stored and disable all buttons.
     */
    public void handleSelectedOrder() {
        if (checkEmptyStoredOrders()) {
            return;
        }
        updateOrderDetails(orders.findOrder(Integer.parseInt(orderComboBox.
                getSelectedItem().toString())));
    }

    /**
     * Updates the recycler view with the menu items stored in the order, the
     * total price, sales tax, and subtotal displayed.
     * @param order the order used to display
     */
    private void updateOrderDetails(Order order) {
        updateList(order);
        DecimalFormat decimalFormat = new DecimalFormat("'$'#,##0.00");
        order.calculateSubTotalCost();
        subTotal.setText(decimalFormat.format(order.getSubTotalCost()));
        salesTax.setText(decimalFormat.format(order.getSalesTax()));
        order.calculateTotalCost();
        totalPrice.setText(decimalFormat.format(order.getTotalCost()));
    }

    /**
     * Used to create the adapter for the recyclerlist and update the list to display the
     * Menuitems in the order object passed in
     * @param order the order to create an adapter from and display in the list
     */
    public void updateList(Order order) {
        orderAdapter = new OrderAdapter(order);
        storedOrderListView.setAdapter(orderAdapter);
        currentOrderLayoutManager = new LinearLayoutManager(this);
        storedOrderListView.setLayoutManager(currentOrderLayoutManager);
    }

    /**
     * Handles the checking if the stored orders has any orders.
     * If no orders, generate a warning and disable the buttons.
     * @return true if empty, otherwise false.
     */
    public boolean checkEmptyStoredOrders() {
        if (orders.getOrderNumbers() == null) {
            totalPrice.setText(getString(R.string.default_price));
            subTotal.setText(getString(R.string.default_price));
            salesTax.setText(getString(R.string.default_price));
            disableButtons();
            generateEmptyWarning();
        }
        return orders.getOrderNumbers() == null;
    }

    /**
     * Getter for the store orders object to add new orders.
     * @return StoreOrders object that represents all the orders that are
     *         currently stored that have not been removed.
     */
    public static StoreOrders getOrders() {
        return orders;
    }

    /**
     * Generates an Toast that indicates the user should place some orders
     * because there are no more stored orders.
     */
    private void generateEmptyWarning() {
        Toast.makeText(StoredOrdersActivity.this,
                getString(R.string.empty_stored_order_message), Toast.LENGTH_SHORT).show();
    }

    /**
     * Disables the place order
     */
    private void disableButtons() {
        cancelOrder.setEnabled(false);
    }

    /**
     * Handles the deletion of the current selected order in the spinner.
     * Upon deletion, it will display the order before it, and if there are no
     * orders before it, it will display the next order. In addition, it will
     * update the associated total price and the menu items of the
     * order in the recycler view.
     * If there are no orders after removing the current selected order, it
     * will disable all buttons and display an alert to the user informing
     * there are no more orders to display.
     * @param v the view being used
     */
    public void handleDeleteOrder(View v) {
        if (checkEmptyStoredOrders()) {
            return;
        }
        int selectedIndex = orderComboBox.getSelectedItemPosition();
        Order order = orders.findOrder(Integer.parseInt((String) orderComboBox.getSelectedItem()));
        int size = order.getNumberOfMenuItems();
        order.clear();
        orderAdapter.notifyItemRangeRemoved(0, size);
        orders.remove(order);
        updateSpinner();
        if (selectedIndex > 0) {
           orderComboBox.setSelection(selectedIndex - 1);
        }
        if (checkEmptyStoredOrders()) {
            return;
        }
        handleSelectedOrder();
    }

}