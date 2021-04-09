package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rucafe.Model.Donut;
import com.example.rucafe.Model.MenuItem;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DonutOrderActivity extends AppCompatActivity {

    private ArrayList<MenuItem> storedDonuts = new ArrayList<>();
    private Spinner selection;
    private RecyclerView donutRecycleView;
    private EditText quantity;
    private TextView subTotal;
    private Button addItem, removeItem, addToCart;
    private OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_order);
        selection = findViewById(R.id.donutSelection);
        donutRecycleView = findViewById(R.id.donutListView);
        quantity = findViewById(R.id.quantityDonuts);
        subTotal = findViewById(R.id.donutSubTotal);
        addItem = findViewById(R.id.addItem);
        removeItem = findViewById(R.id.removeItem);
        addToCart = findViewById(R.id.addtoCart);
        updateList();
    }

    /**
     * Retrieves the selected donut type, flavor, and quantity from the view,
     * creates the donut object to be stored, and updates the donut list
     * view and sub-total price.
     * Also performs the necessary validation for the quantity and will
     * display an alert message if the quantity is invalid.
     */
    public void handleAdd(View v) {
        String donutTypeAndFlavor = selection.getSelectedItem().toString();
        try {
            String donutQuantity = quantity.getText().toString();
            int quantity = Integer.parseInt(donutQuantity);
            if (quantity > 0 && donutTypeAndFlavor != null) {
                Donut donut = new Donut(donutTypeAndFlavor, quantity);
                storedDonuts.add(donut);
                orderAdapter.notifyItemInserted(storedDonuts.size() - 1);
                updateSubTotal();
                return;
            }
        } catch (NumberFormatException | NullPointerException e) {
            // Catch the exception when it occurs and go straight to the
            // Alert message afterwards below
        }
        Toast.makeText(this, "Please enter a non-negative and" +
                " non-empty/zero quantity that is below or " +
                "equal to " + Integer.MAX_VALUE + ".", Toast.LENGTH_LONG).show();
    }

    /**
     * Retrieves the selected donut from the donut list view to remove, and
     * updates the list view and sub-total upon deletion of the donut.
     * If no donut was selected, it will display an alert telling the user to
     * select an item from the list view.
     */
    public void handleRemove(View v) {
        int selectedIndex = orderAdapter.getSelected();
        if (selectedIndex < 0) {
            Toast.makeText(this, "Please select a valid item from the list!",
                    Toast.LENGTH_SHORT).show();
        } else {
            storedDonuts.remove(selectedIndex);
            orderAdapter.notifyItemRemoved(selectedIndex);
            orderAdapter.resetSelection();
            updateSubTotal();
        }
    }

    private void updateList() {
        orderAdapter = new OrderAdapter(storedDonuts);
        donutRecycleView.setAdapter(orderAdapter);
        LinearLayoutManager currentOrderLayoutManager = new LinearLayoutManager(this);
        donutRecycleView.setLayoutManager(currentOrderLayoutManager);
    }

    /**
     * Performs the necessary donut(s) price calculations for the sub-total
     * and displays the new sub-total rounded to the nearest hundredths
     * place.
     */
    public void updateSubTotal() {
        double price = 0;
        for (MenuItem donut : storedDonuts) {
            donut.itemPrice();
            price += donut.getItemPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("'$'#,##0.00");
        subTotal.setText(decimalFormat.format(price));
    }

    /**
     * Adds list of donuts that are displayed in the list view into the
     * current order object.
     * Upon success, it will generate an alert telling the user all the
     * donuts were successfully added to the current order or the cart.
     * If no donuts were added (meaning the donut list view is empty), it
     * will generate a warning telling the user to add donuts before adding
     * to the cart.
     */
    public void addToShoppingCart(View v) {
        if (storedDonuts.size() == 0) {
            Toast.makeText(this,"Please add some items before adding to " +
                    "the shopping cart", Toast.LENGTH_SHORT).show();
            return;
        }

        storedDonuts.forEach((item) -> CurrentOrderActivity.
                getCurrentOrder().add(item));
        int size = storedDonuts.size();
        storedDonuts.clear();
        orderAdapter.notifyItemRangeRemoved(0, size);
        updateSubTotal();
        Toast.makeText(this,"Successfully added to your shopping cart! " +
                "Please check your current orders or the shopping cart " +
                "icon to checkout your items!", Toast.LENGTH_LONG).show();
    }
}