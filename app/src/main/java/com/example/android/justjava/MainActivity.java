package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    //variable to store ammount of coffees
    private int quantity = 2;
    private int pricePerCoffee = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        displayQuantity(this.quantity);
        int price = calculatePrice();
        displayPrice(Integer.toString(price));

    }

    /**
     * This function increments thw value of coffees
     */

    public void increment(View view) {
        if (this.quantity == Integer.MAX_VALUE) this.quantity = Integer.MAX_VALUE - 1;
        displayQuantity(this.quantity + 1);
        this.quantity++;
    }


    /**
     * This function decrements thw value of coffees
     */

    public void decrement(View view) {
        if (this.quantity == 0) this.quantity = 1;
        displayQuantity(this.quantity - 1);
        this.quantity--;
    }


    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(String number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        String displayText = "Total: "+ NumberFormat.getCurrencyInstance().format(Integer.parseInt(number)).toString()+"\nThank you!";
        priceTextView.setText(displayText);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    /**
     * Calculates the price of the order.
     * @return total price of the order
    */
    private int calculatePrice() {
        return quantity * pricePerCoffee;
    }

}