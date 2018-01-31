package com.example.wkudsk.timestable;

import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;
import android.media.MediaPlayer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView myListView;

    public void generateTimesTable(int tableValue)
    {

        final ArrayList<String> timesTable = new ArrayList<String>();

        for(int i = 1; i <= 12; i++)
        {
            timesTable.add(Integer.toString(i*tableValue));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, timesTable);
        myListView.setAdapter(arrayAdapter);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myListView = (ListView) findViewById(R.id.list);

        final SeekBar number = (SeekBar) findViewById(R.id.seekBar);
        number.setMax(20);
        number.setProgress(10);
        generateTimesTable(10);

        number.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int min = 1;
                int timesTable;
                if(progress < min)
                {
                    timesTable = min;
                    number.setProgress(1);
                }
                else
                {
                    timesTable = progress;
                }

                generateTimesTable(timesTable);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




    }
}