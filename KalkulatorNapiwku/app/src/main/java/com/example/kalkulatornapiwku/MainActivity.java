package com.example.kalkulatornapiwku;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.Math;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnCompute;
    Button btnClear;
    EditText txtOrder;
    EditText txtTip;
    RatingBar ServiceRatingBar;
    RatingBar FoodRatingBar;
    TextView finalFullCost;     // final cost ( includes order cost and tips)

    double ComputeCost(double order, double tipPercent, double serviceRating, double foodRating)
    {
        // kazda gwiazdka to 2 procent od sumy zamówienia
        return order + order * (tipPercent/100)  + (serviceRating * 0.02)  * order + (foodRating * 0.02) * order;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtOrder = (EditText)findViewById(R.id.txtOrder);
        txtTip = (EditText)findViewById(R.id.txtTip);
        ServiceRatingBar = (RatingBar)findViewById(R.id.ServiceRatingBar);
        FoodRatingBar = (RatingBar)findViewById(R.id.FoodRatingBar);
        finalFullCost = findViewById(R.id.finfullcost);

        txtOrder.setText("0.00");
        txtTip.setText("0.00");

        btnClear = (Button)findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new OnClickListener() {
            // clear the text boxes
            @Override
            public void onClick(View v) {
                txtOrder.setText("0.00");
                txtTip.setText("0.00");
                finalFullCost.setText("0.00");
                ServiceRatingBar.setRating(0);
                FoodRatingBar.setRating(0);
            }
        });



        btnCompute = (Button) findViewById(R.id.btnCompute);
        btnCompute.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                // pobranie wartosci zamowienia
                String ordercostStr = txtOrder.getText().toString();
                double ordercost = Double.parseDouble(ordercostStr);
                // pobranie procentu napiwka
                String tipvalueStr = txtTip.getText().toString();
                double tipvalue = Double.parseDouble(tipvalueStr);
                // Rating
                double serviceRating = ServiceRatingBar.getRating();
                double foodRating = FoodRatingBar.getRating();

                // obliczenie calego kosztu
                double cost = ComputeCost(ordercost, tipvalue, serviceRating, foodRating );
                double fullcost = (double) Math.round(cost * 100) / 100;    // zaokraglenie do dwóch miejsc po przecinku
                String fullcostStr = Double.toString(fullcost);

                finalFullCost.setText(fullcostStr);
            }
        });


    }
}
