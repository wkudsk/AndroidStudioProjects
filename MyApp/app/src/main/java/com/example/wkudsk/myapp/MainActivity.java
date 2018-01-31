package com.example.wkudsk.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public void clickFunction(View view)
    {
        EditText myUserField = (EditText) findViewById(R.id.editUserText);
        EditText myPasswordField = (EditText) findViewById(R.id.editPasswordText);

        Log.i("Info", myUserField.getText().toString());
        Log.i("Info", myPasswordField.getText().toString());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
