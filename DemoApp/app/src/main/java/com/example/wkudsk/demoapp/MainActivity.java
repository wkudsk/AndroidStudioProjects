package com.example.wkudsk.demoapp;

import android.content.Context;
import android.content.res.Resources;
import android.media.AudioManager;
import android.os.CountDownTimer;
import android.os.Handler;
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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.media.MediaPlayer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView text;

    public void show(View view)
    {
        text.setVisibility(View.VISIBLE);
    }
    public void hide(View view)
    {

        text.setVisibility(View.INVISIBLE);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.hello);
    }
}