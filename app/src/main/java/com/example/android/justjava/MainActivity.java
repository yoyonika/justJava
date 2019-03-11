package com.example.android.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

// This app displays an order form to order coffee

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // This method is called when the order button is clicked

    public void submitOrder (View view) {
        int quantity = 2;
        display(quantity);
        displayPrice(quantity*5);
    }

    // This method displays the given quantity value on the screen

    public void display (int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    // This method displays the price on the screen

    public void displayPrice (int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    // Increments the price

    public void increment(View view){
        int quantity = 3;
        display(quantity);
    }

    //Decrements the price

    public void decrement(View view){
        int quantity = 1;
        display(quantity);
    }
}
