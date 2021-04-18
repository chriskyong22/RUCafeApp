package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rucafe.Model.Coffee;

import java.text.DecimalFormat;

/**
 * TODO
 * @author Christopher Yong, Maya Ravichandran
 */
public class CoffeeOrderActivity extends AppCompatActivity {
    private Spinner coffeeSize, coffeeQuantity;
    private CheckBox cream, syrup, milk, caramel, whippedCream;
    private TextView subTotal;
    private Coffee coffee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_order);
        coffeeSize = findViewById(R.id.coffeeSize);
        coffeeQuantity = findViewById(R.id.coffeeQuantity);
        cream = findViewById(R.id.creamCheck);
        syrup = findViewById(R.id.syrupCheck);
        milk = findViewById(R.id.milkCheck);
        caramel = findViewById(R.id.caramelCheck);
        whippedCream = findViewById(R.id.whippedCreamCheck);
        subTotal = findViewById(R.id.coffeeSubTotal);
        createNewCoffee();
        coffeeSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSize();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        coffeeQuantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateQuantity();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * Retrieves and updates the size of the coffee wanted and updates
     * the sub-total.
     */
    public void updateSize() {
        coffee.setSize(coffeeSize.getSelectedItem().toString());
        updateSubTotal();
    }

    /**
     * Retrieves and updates the quantity of coffee desired and updates
     * the sub-total.
     */
    public void updateQuantity() {
        coffee.setQuantity(Integer.parseInt(coffeeQuantity.getSelectedItem().toString()));
        updateSubTotal();
    }

    /**
     * Checks if the Cream add-in should be added to the current
     * coffee object, does the corresponding remove/add operation,
     * and updates the sub-total.
     * @param v View being used
     */
    public void checkCream(View v) {
        if (cream.isChecked()) {
            coffee.add(getString(R.string.cream));
        } else {
            coffee.remove(getString(R.string.cream));
        }
        updateSubTotal();
    }

    /**
     * Checks if the syrup add-in should be added to the current
     * coffee object, does the corresponding remove/add operation,
     * and updates the sub-total.
     * @param v View being used
     */
    public void checkSyrup(View v) {
        if (syrup.isChecked()) {
            coffee.add(getString(R.string.syrup));
        } else {
            coffee.remove(getString(R.string.syrup));
        }
        updateSubTotal();
    }

    /**
     * Checks if the milk add-in should be added to the current
     * coffee object, does the corresponding remove/add operation,
     * and updates the sub-total.
     * @param v View being used
     */
    public void checkMilk(View v) {
        if (milk.isChecked()) {
            coffee.add(getString(R.string.milk));
        } else {
            coffee.remove(getString(R.string.milk));
        }
        updateSubTotal();
    }

    /**
     * Checks if the caramel add-in should be added to the current
     * coffee object, does the corresponding remove/add operation,
     * and updates the sub-total.
     * @param v View being used
     */
    public void checkCaramel(View v) {
        if (caramel.isChecked()) {
            coffee.add(getString(R.string.caramel));
        } else {
            coffee.remove(getString(R.string.caramel));
        }
        updateSubTotal();
    }

    /**
     * Checks if the whipped cream add-in should be added to the current
     * coffee object, does the corresponding remove/add operation,
     * and updates the sub-total.
     * @param v View being used
     */
    public void checkWhippedCream(View v) {
        if (whippedCream.isChecked()) {
            coffee.add(getString(R.string.whippedcream));
        } else {
            coffee.remove(getString(R.string.whippedcream));
        }
        updateSubTotal();
    }

    /**
     * Creates a new coffee object, sets the correct add-ins according to
     * what is checked in the view, and updates the sub total.
     */
    private void createNewCoffee() {
        coffee = new Coffee(coffeeSize.getSelectedItem().toString(),
                Integer.parseInt(coffeeQuantity.getSelectedItem().toString()));
        checkCream(cream);
        checkCaramel(caramel);
        checkMilk(milk);
        checkSyrup(syrup);
        checkWhippedCream(whippedCream);
    }

    /**
     * Performs the necessary coffee price calculations for the sub total and
     * displays the new sub total rounded to the nearest hundredths place.
     */
    public void updateSubTotal() {
        coffee.itemPrice();
        double price = coffee.getItemPrice();
        DecimalFormat decimalFormat = new DecimalFormat("'$'#,##0.00");
        subTotal.setText(decimalFormat.format(price));
    }

    /**
     * Adds the current coffee to the current order and generates
     * a new coffee if the user wishes to add another coffee.
     * Generates Alert confirming the coffee was added to the cart.
     * @param v View being used
     */
    public void addToShoppingCart(View v) {
        CurrentOrderActivity.getCurrentOrder().add(coffee);
        createNewCoffee();
        Toast.makeText(this, getString(R.string.success_add_to_shopping_cart),
                Toast.LENGTH_SHORT).show();
    }
}