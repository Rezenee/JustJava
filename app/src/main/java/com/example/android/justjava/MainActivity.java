package com.example.android.justjava;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the order button is clicked.
     */
    int quantity = 0;
    public void submitOrder(View view) {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_check_box);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);
        displayMessage(priceMessage);
    }

    private String createOrderSummary(String name, int price, boolean  whippedCream, boolean hasChocolate){
        String priceMessage = "Name: " + name;
        priceMessage += "\nAdd whipped cream? " + whippedCream;
        priceMessage += "\nAdd chocolate? " + hasChocolate;
        priceMessage += "\nQuantity " + quantity;
        priceMessage += "\nTotal: $ " + price;
        priceMessage += "\nThank you!";
        return priceMessage;
    }
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate){
        int basePrice = 5;
        if (addWhippedCream) {
            basePrice += 1;
        }
        if (addChocolate){
            basePrice += 2;
        }
        return quantity * basePrice;
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }
    /**
     * This method displays the given price on the screen.
     */

    public void increment(View view){
        quantity++;
        displayQuantity(quantity);
    }

    public void decrement(View view){
        if(quantity > 0){
            quantity--;
        }
        displayQuantity(quantity);
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}