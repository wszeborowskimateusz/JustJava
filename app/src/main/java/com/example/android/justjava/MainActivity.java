package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    public static final int MAXIMAL_NUMBER_OF_COFFEES = 100;
    public static final int MINIMAL_NUMBER_OF_COFFEES = 1;
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

        //Using Intent to mail the order
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava ordered for "+name);
        intent.putExtra(Intent.EXTRA_TEXT, msg);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

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
        if (this.quantity == MAXIMAL_NUMBER_OF_COFFEES ){
            Toast.makeText(this,"You cannot order more than 100 cups of coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        this.quantity++;
        displayQuantity(this.quantity);
    }

    /**
     * This function decrements thw value of coffees
     */
    public void decrement(View view) {
        if (this.quantity == MINIMAL_NUMBER_OF_COFFEES){
            Toast.makeText(this,"You cannot order less than 1 cup of coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        this.quantity--;
        displayQuantity(this.quantity);

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
        int price = pricePerCoffee;
        if(hasWhippedCream)price++;
        if(hasChocolate)price+=2;
        return price * quantity;
    }


}