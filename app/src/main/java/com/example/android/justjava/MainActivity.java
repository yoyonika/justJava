package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

// This app displays an order form to order coffee

public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    String priceOfOrder = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // This method is called when the order button is clicked

    public void submitOrder(View view) {
        EditText enterName = findViewById(R.id.enter_name);
        String enteredName = enterName.getText().toString();

        CheckBox whippedCream = findViewById(R.id.whipped_cream);
        boolean withWhippedCream = whippedCream.isChecked();

        CheckBox chocolate = findViewById(R.id.chocolate);
        boolean hasChocolate = chocolate.isChecked();

        int price = calculatePrice(withWhippedCream, hasChocolate);
        sendOrderEmail(price, withWhippedCream, hasChocolate, enteredName);
    }

    // This method displays the given quantity value on the screen

    public void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private int calculatePrice(boolean withWhippedCream, boolean hasChocolate) {
        int basePrice = 5;
        if (withWhippedCream) {
            basePrice = basePrice + 1;
        }

        if (hasChocolate) {
            basePrice = (basePrice + 2);
        }

        return basePrice * quantity;
    }

    private String createOrderSummary(int price, boolean withWhippedCream, boolean hasChocolate, String enteredName){
        priceOfOrder = "\n" + getString(R.string.customer_name,enteredName);
        priceOfOrder += "\n" + getString(R.string.with_whippded_cream,withWhippedCream);
        priceOfOrder += "\n" + getString(R.string.with_chocolate,hasChocolate);
        priceOfOrder += "\n" + getString(R.string.quantity_ordered) + quantity;
        priceOfOrder += "\n" + getString(R.string.total_price) + NumberFormat.getCurrencyInstance().format(price);
        priceOfOrder += "\n" + getString(R.string.thank_you);

        return priceOfOrder;
    }

    private void sendOrderEmail(int price, boolean withWhippedCream, boolean hasChocolate, String enteredName) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.customer_name_email_subject,enteredName));
            intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(price, withWhippedCream, hasChocolate, enteredName));
        if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }

    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }

    // Increments the price

    public void increment(View view){
        if(quantity < 100) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Toast.makeText(this, "Please enter less than 100 cups of coffee", Toast.LENGTH_SHORT).show();
        }
    }

    //Decrements the price

    public void decrement(View view){
        if(quantity > 1) {
       quantity = quantity - 1;
            display(quantity);
        } else {
            Toast.makeText(this, "Please enter 1 or more cups of coffee", Toast.LENGTH_SHORT).show();
        }
    }
}
