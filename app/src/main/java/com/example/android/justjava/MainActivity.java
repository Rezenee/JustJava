package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    int quantity = 1;
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
        String priceMessage = getString(R.string.order_summary_name, name);
        priceMessage += "\n" + getString(R.string.addWhippedCream) + whippedCream;
        priceMessage += "\n" + getString(R.string.addChocolate) + hasChocolate;
        priceMessage += "\n" + getString(R.string.quantity) + quantity;
        priceMessage += "\n" + getString(R.string.total) + price;
        priceMessage += "\n" + getString(R.string.thank_you);
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
        if (quantity == 100){
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        displayQuantity(quantity);
    }

    public void decrement(View view){
        if(quantity == 1){
            Toast.makeText(this, "You cannot less than 1 coffee", Toast.LENGTH_SHORT).show();

            return;
        }
        quantity --;
        displayQuantity(quantity);
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}