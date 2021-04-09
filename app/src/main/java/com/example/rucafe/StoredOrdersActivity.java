package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rucafe.Model.StoreOrders;

public class StoredOrdersActivity extends AppCompatActivity {

    private static StoreOrders orders = new StoreOrders();
    private Button placeOrder;
    private RecyclerView storedOrderListView;
    private TextView totalPrice;
    private Spinner orderComboBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stored_orders);
        placeOrder = findViewById(R.id.cancelOrder);
        storedOrderListView = findViewById(R.id.storedOrderListView);
        totalPrice = findViewById(R.id.totalPriceStoredOrders);
        totalPrice.setText(R.string.default_price);
        orderComboBox = findViewById(R.id.storedOrderSpinner);
        checkEmptyStoredOrders();
    }

    /**
     * Handles the checking if the stored orders has any orders.
     * If no orders, generate a warning and disable the buttons.
     * @return true if empty, otherwise false.
     */
    public boolean checkEmptyStoredOrders() {
        if (orders.getOrderNumbers() == null) {
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
     * Generates an Alert that indicates the user should place some orders
     * because there are no more stored orders.
     */
    private void generateEmptyWarning() {
        Toast.makeText(StoredOrdersActivity.this,  "There are no orders placed! Please" +
                " navigate back to the menu and place some orders!", Toast.LENGTH_LONG).show();
    }
    /**
     * Disables the place order
     */
    private void disableButtons() {
        placeOrder.setEnabled(false);
    }

}