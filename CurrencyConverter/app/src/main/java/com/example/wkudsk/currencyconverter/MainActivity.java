package com.example.wkudsk.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void convert(View view){
        EditText dollarInput = (EditText) findViewById(R.id.dollarInput);

        Log.i("amount", dollarInput.getText().toString());

        Double input = Double.parseDouble(dollarInput.getText().toString());
        Double output = input * 19.7248;
        Toast.makeText(MainActivity.this, "MXN" + String.format("%.2f", output), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
