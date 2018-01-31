package com.example.wkudsk.commonphrases;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    public void playSound(View view)
    {
        int numId = view.getId();
        String id = "";
        id = view.getResources().getResourceEntryName(numId);
        MediaPlayer phrase;



        if(id.equals("speakEnglish"))
        {
            phrase = MediaPlayer.create(this, R.raw.doyouspeakenglish);
        }
        else if(id.equals("goodEvening"))
        {
            phrase = MediaPlayer.create(this, R.raw.goodevening);
        }
        else if(id.equals("hello"))
        {
            phrase = MediaPlayer.create(this, R.raw.hello);
        }
        else if(id.equals("howAreYou"))
        {
            phrase = MediaPlayer.create(this, R.raw.howareyou);
        }
        else if(id.equals("iLiveIn"))
        {
            phrase = MediaPlayer.create(this, R.raw.ilivein);
        }
        else if(id.equals("myNameIs"))
        {
            phrase = MediaPlayer.create(this, R.raw.mynameis);
        }
        else if(id.equals("please"))
        {
            phrase = MediaPlayer.create(this, R.raw.please);
        }
        else
        {
            phrase = MediaPlayer.create(this, R.raw.welcome);
        }

        phrase.start();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
