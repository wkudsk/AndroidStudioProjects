package com.example.wkudsk.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public int randomInt;
    public Random r = new Random();

    public void higherOrLower(View view){
        EditText userGuess = (EditText) findViewById(R.id.Guess);

        Log.i("number guessed: ", userGuess.getText().toString());

        int input = Integer.parseInt(userGuess.getText().toString());
        if(input > randomInt)
        {
            Toast.makeText(MainActivity.this, "Lower!", Toast.LENGTH_SHORT).show();
        }
        else if(input < randomInt)
        {
            Toast.makeText(MainActivity.this, "Higher!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(MainActivity.this, "Correct! Now Try Again!", Toast.LENGTH_SHORT).show();
            randomInt = r.nextInt(20) + 1;
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        randomInt = r.nextInt(20) + 1;
    }
}
