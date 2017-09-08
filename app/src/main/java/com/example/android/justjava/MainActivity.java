package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    //variable to store ammount of coffees
    private int quantity = 2;
    private int pricePerCoffee = 5;
    private boolean hasWhippedCream = false;
    private boolean hasChocolate = false;
    private String name = "";

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
        hasWhippedCream = checkWhippedCreamCheckBox();
        hasChocolate = checkChocolateCheckBox();
        name = readName();
        int price = calculatePrice();
        String msg = createOrderSummary(price);
        displayMassage(msg);

    }

    /**
     * @return the state of the whipped cream checkobox (checked or unchecked)
     */
    public boolean checkWhippedCreamCheckBox(){
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream);
        return whippedCream.isChecked();
    }

    /**
     * @return the state of the chocolate checkobox (checked or unchecked)
     */
    public boolean checkChocolateCheckBox(){
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        return chocolate.isChecked();
    }
    /**
     * @return the state of the chocolate checkobox (checked or unchecked)
     */
    public String readName(){
        EditText name = (EditText) findViewById(R.id.name_edit_text);
        return name.getText().toString();
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
     * @param price the price for all ordered coffes
     * @return summary text massage for an order
     */
    public String createOrderSummary(int price){
        return "Name: "+ name +
                "\nAdd whipped cream: "+hasWhippedCream+" " +
                "\nAdd chocolate: "+hasChocolate+" " +
                "\nQuantity: "+quantity+
                "\nTotal: $"+price+" "
                + "\nThank you!";
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayMassage(String price) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(price);
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
        int price = quantity * pricePerCoffee;
        if(hasWhippedCream)price++;
        if(hasChocolate)price+=2;
        return price;
    }

}