package com.example.justjava;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.util.*;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
/* at first this is a class named mainactivity which public in nature and this class is extending another class named appcompatactivity and
* inside this class there is an instance variable of data type integer and variable name quantity then there is a method which is overridder that it is taken from
* the appcompatactivity class and now it is redefined within this class this method is having a protected value of accesss variable and the return type is void that is
* it and the name of the method is oncreate and the parameters that it is taking is an object of bundle and it has a name */
public class MainActivity extends AppCompatActivity {
boolean re;
boolean re1;
    int quantity =0;

    @Override/* this method has taken from appcompactactivity and redefined in its own way*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);/* the super word is used to call the methods of the super class*/
        setContentView(R.layout.activity_main);


    }
    private String displayName(){
        EditText editText=(EditText)findViewById(R.id.name_view);
        String string=editText.getText().toString();
        return string;
    }


    /**
     * This method is called when the order button is clicked.
     */
    /*this the method has been made to create an intent so that we can send the order to another person using any mailing app*/
    public void submitOrder(View view) {
        String result=createOrderSummary(quantity,re,re1);
        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
       intent.putExtra(Intent.EXTRA_SUBJECT,"Coffee order for: " + displayName());
       intent.putExtra(Intent.EXTRA_TEXT,result);
//       this method makes sure that the app does not crashes by checking for the other apps that can accept the sent intents.
         if(intent.resolveActivity(getPackageManager())!=null && quantity>0 && !displayName().isEmpty()){
             startActivity(intent);
         }
    }
    /*this is a new method made by us on our own to understand the main logic behind the creating and calling of methods*/
    /* Name=Kaptain Kunal
      Quantity=3;
      Total:$15;
      Thankyou!
     */
     public String createOrderSummary(int quantity , boolean hasWhippedCream, boolean hasChocolate){

            int price=quantity*10;
            int whippedCreamPrice=6;
            int chocolatePrice=5;
            if(hasWhippedCream && quantity>0){
                price=price+whippedCreamPrice*quantity;
            }
            if(hasChocolate && quantity>0){
                price=price+chocolatePrice*quantity;
            }
          String s="Name:" +displayName() +"\nadd whipped cream? "+ hasWhippedCream+ "\nadd chocolate? "  + hasChocolate +"\nQuantity:"+ quantity +"\nTotal:"+ price+"\nThankyou!!";


         return s;
     }

    public void increment(View view){
      quantity=quantity+1;

       display(quantity);


    }



//    this is the method to check if the whipped cream has been added in the coffee or not it will update the remaining code accordingly
    public void toCheckIfChecked(View view){
   boolean check=((CheckBox)view).isChecked();
   re=check;

    }

  public void choco(View view){
        boolean chocoCheck=((CheckBox)view).isChecked();
        re1=chocoCheck;
  }






    public void decrement(View view) {
            if(quantity>0) {
                quantity = quantity - 1;
                display(quantity);
            }
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
}