package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rucafe.Model.Donut;
import com.example.rucafe.Model.MenuItem;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Donut controller to link the Donut View to the Donut model.
 * It updates the sub-total and donut list view upon adding/removing donuts,
 * and it allows the user to add the list's donuts to the current order.
 * @author Christopher Yong, Maya Ravichandran
 */
public class DonutOrderActivity extends AppCompatActivity {

    private ArrayList<MenuItem> storedDonuts = new ArrayList<>();
    private Spinner selection;
    private RecyclerView donutRecycleView;
    private EditText quantity;
    private TextView subTotal;
    private OrderAdapter orderAdapter;

    /**
     * Initializes the views in the layout.
     * Retrieves all the references of the views to be used and initializes
     * the RecyclerView.
     * @param savedInstanceState savedInstanceState if provided
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_order);
        selection = findViewById(R.id.donutSelection);
        donutRecycleView = findViewById(R.id.donutListView);
        quantity = findViewById(R.id.quantityDonuts);
        subTotal = findViewById(R.id.donutSubTotal);
        updateList();
    }

    /**
     * Retrieves the selected donut type, flavor, and quantity from the view,
     * creates the donut object to be stored, and updates the donut list
     * view and sub-total price.
     * Also performs the necessary validation for the quantity and will
     * display an alert message if the quantity is invalid.
     * @param v View being used
     */
    public void handleAdd(View v) {
        String donutTypeAndFlavor = selection.getSelectedItem().toString();
        try {
            String donutQuantity = quantity.getText().toString();
            int quantity = Integer.parseInt(donutQuantity);
            if (quantity > 0) {
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
        Toast.makeText(this, getString(R.string.invalid_quantity),
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Retrieves the selected donut from the donut list view to remove, and
     * updates the list view and sub-total upon deletion of the donut.
     * If no donut was selected, it will display an alert telling the user to
     * select an item from the list view.
     * @param v View being used
     */
    public void handleRemove(View v) {
        int selectedIndex = orderAdapter.getSelected();
        if (selectedIndex < 0) {
            Toast.makeText(this, getString(R.string.invalid_selection),
                    Toast.LENGTH_SHORT).show();
        } else {
            storedDonuts.remove(selectedIndex);
            orderAdapter.notifyItemRemoved(selectedIndex);
            orderAdapter.resetSelection();
            updateSubTotal();
        }
    }

    /**
     * Updates the RecyclerView.
     */
    private void updateList() {
        orderAdapter = new OrderAdapter(storedDonuts);
        donutRecycleView.setAdapter(orderAdapter);
        LinearLayoutManager currentOrderLayoutManager =
                new LinearLayoutManager(this);
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
     * @param v View being used
     */
    public void addToShoppingCart(View v) {
        if (storedDonuts.size() == 0) {
            Toast.makeText(this,
                    getString(R.string.empty_shopping_cart_message),
                    Toast.LENGTH_SHORT).show();
            return;
        }

        storedDonuts.forEach((item) -> CurrentOrderActivity.
                getCurrentOrder().add(item));
        int size = storedDonuts.size();
        storedDonuts.clear();
        orderAdapter.notifyItemRangeRemoved(0, size);
        orderAdapter.resetSelection();
        updateSubTotal();
        Toast.makeText(this,
                getString(R.string.success_add_to_shopping_cart),
                Toast.LENGTH_SHORT).show();
    }
}