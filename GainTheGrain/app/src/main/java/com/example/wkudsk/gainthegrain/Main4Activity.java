package com.example.wkudsk.gainthegrain;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity {

    SeekBar hydroScrubber;
    SeekBar saltScrubber;
    SeekBar yeastScrubber;
    EditText flourInput;
    TextView hydroPercent;
    TextView saltPercent;
    TextView yeastPercent;
    int flourAmount = 0;
    int hydroAmount = 0;
    int yeastAmount = 0;
    int saltAmount = 0;
    SharedPreferences sharedPreferences;

    public void jumpToResult(View view)
    {
        sharedPreferences.edit().putInt("Flour Amount", flourAmount);
        sharedPreferences.edit().putInt("Hydration Amount", hydroAmount);
        sharedPreferences.edit().putInt("Salt Amount", saltAmount);
        sharedPreferences.edit().putInt("Starter Amount", yeastAmount);
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        intent.putExtra("Flour Amount", flourAmount);
        intent.putExtra("Hydration Amount", hydroAmount);
        intent.putExtra("Salt Amount", saltAmount);
        intent.putExtra("Starter Amount", yeastAmount);
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
        setContentView(R.layout.activity_main4);
    }
}
