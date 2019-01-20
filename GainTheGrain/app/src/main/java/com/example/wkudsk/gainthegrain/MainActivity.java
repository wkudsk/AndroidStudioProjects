package com.example.wkudsk.gainthegrain;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar hydroScrubber;
    SeekBar saltScrubber;
    SeekBar starterScrubber;
    EditText flourInput;
    TextView hydroPercent;
    TextView saltPercent;
    TextView starterPercent;
    int flourAmount = 0;
    int hydroAmount = 0;
    int starterAmount = 0;
    int saltAmount = 0;
    SharedPreferences sharedPreferences;

    public void jumpToResult(View view)
    {
        sharedPreferences.edit().putInt("Flour Amount", flourAmount);
        sharedPreferences.edit().putInt("Hydration Amount", hydroAmount);
        sharedPreferences.edit().putInt("Salt Amount", saltAmount);
        sharedPreferences.edit().putInt("Starter Amount", starterAmount);
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        intent.putExtra("Flour Amount", flourAmount);
        intent.putExtra("Hydration Amount", hydroAmount);
        intent.putExtra("Salt Amount", saltAmount);
        intent.putExtra("Starter Amount", starterAmount);
        startActivity(intent);
    }

    public void jumpToPreset(View view)
    {
        Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        hydroScrubber =(SeekBar)findViewById(R.id.HydrationBar);
        flourInput = (EditText) findViewById(R.id.flourInput);
        hydroPercent = (TextView) findViewById(R.id.hydroPercent);
        saltPercent = (TextView) findViewById(R.id.SaltPercent);
        starterPercent = (TextView) findViewById(R.id.yeastPercent);
        saltScrubber = (SeekBar)findViewById(R.id.SaltBar);
        starterScrubber = (SeekBar)findViewById(R.id.yeastBar);
        sharedPreferences = this.getSharedPreferences("com.example.wkudsk.sharedpreferences", Context.MODE_PRIVATE);


        starterScrubber.setMax(100);
        starterScrubber.setProgress(0);
        saltScrubber.setMax(100);
        saltScrubber.setProgress(0);
        hydroScrubber.setMax(100);
        hydroScrubber.setProgress(0);

        flourInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(charSequence.toString().isEmpty())
                    flourAmount = 0;
                //flourAmount = Integer.parseInt(charSequence.toString());
                //Log.i("flourAmount", Integer.toString(flourAmount));
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!editable.toString().isEmpty()) flourAmount = Integer.parseInt(editable.toString());
                else flourAmount = 0;
                Log.i("FlourAmount", Integer.toString(flourAmount));
            }
        });

        hydroScrubber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                hydroPercent.setText("" + i + "%");
                hydroAmount = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                hydroAmount = seekBar.getProgress();
                Log.i("HydroAmount", Integer.toString(hydroAmount));
            }
        });
        starterScrubber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                starterPercent.setText("" + i + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                starterAmount = seekBar.getProgress();
                Log.i("StarterAmount", Integer.toString(starterAmount));
            }
        });
        saltScrubber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                saltPercent.setText("" + i + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                saltAmount = seekBar.getProgress();
                Log.i("SaltAmount", Integer.toString(saltAmount));
            }
        });
    }
}
