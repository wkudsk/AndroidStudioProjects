package com.example.wkudsk.gainthegrain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SeekBar hydroScrubber;
    EditText flourInput;

    public void jumpToResult(View view)
    {
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hydroScrubber =(SeekBar)findViewById(R.id.HydrationBar);
        flourInput = (EditText) findViewById(R.id.flourInput);
        hydroScrubber.setMax(100);
        hydroScrubber.setProgress(0);

    }
}
