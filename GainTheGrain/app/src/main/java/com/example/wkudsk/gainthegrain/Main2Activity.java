package com.example.wkudsk.gainthegrain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    double flourAmount;
    double hydroAmount;
    double saltAmount;
    double starterAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        flourAmount = intent.getIntExtra("Flour Amount", 0);
        hydroAmount = intent.getIntExtra("Hydration Amount", 0);
        saltAmount = intent.getIntExtra("Salt Amount", 0);
        starterAmount = intent.getIntExtra("Starter Amount", 0);
        TextView flourText = (TextView) findViewById(R.id.flour);
        TextView hydroText = (TextView) findViewById(R.id.hydration);
        TextView saltText = (TextView) findViewById(R.id.salt);
        TextView starterText = (TextView) findViewById(R.id.starter);
        TextView loafText = (TextView) findViewById(R.id.loaves);

        flourText.setText("For " + flourAmount + "g of flour...");
        hydroText.setText("You will want to use " + ((hydroAmount/100)* flourAmount) + "ml of water");
        saltText.setText("You will want to use " + ((saltAmount/100)* flourAmount) + "g of salt");
        starterText.setText("You will want to use " + ((starterAmount/100)* flourAmount) + "g of starter");

        Toast.makeText(this, "Your hydration is: " + intent.getIntExtra("Hydration Amount", 0), Toast.LENGTH_SHORT).show();

        double totalDoughWeight = flourAmount + ((hydroAmount/100)* flourAmount) + ((saltAmount/100)* flourAmount) + ((starterAmount/100)* flourAmount);

        double loafSize = 0;
        double tempDoughWeight = totalDoughWeight;
        int numOfLoaves = 1;
        while(tempDoughWeight > 1000){
            tempDoughWeight = tempDoughWeight/numOfLoaves;
            numOfLoaves += 1;
        }


        loafSize = totalDoughWeight/numOfLoaves;

        if(numOfLoaves == 1)
        {
            loafText.setText("You will want to use "+ numOfLoaves + " loaf each " + loafSize
                    + "g for " + totalDoughWeight + "g of dough");
        }
        else
        {
            loafText.setText("You will want to use "+ numOfLoaves + " loaves each " + loafSize
                    + "g for " + totalDoughWeight + "g of dough");
        }

    }
}
