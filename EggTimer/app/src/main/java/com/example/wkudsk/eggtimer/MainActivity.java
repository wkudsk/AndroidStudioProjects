package com.example.wkudsk.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerScrubber;
    int countDown = 1000;
    int timerLength;
    TextView timerText;
    Button  controllerButton;
    boolean counterIsActive;
    CountDownTimer timer;
    MediaPlayer alarm;

    public void eggTimer(View view)
    {


        if(!counterIsActive) {
            counterIsActive = true;
            controllerButton.setText("STOP");
            timerScrubber.setEnabled(false);


            timer = new CountDownTimer(timerScrubber.getProgress() * 1000 + 100, countDown) {
                @Override
                public void onTick(long millisUntilFinished) {
                    //Every Tick (1 second)
                    setTimerText((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {
                    //On Finish
                    timerText.setText("0:00");
                    alarm = MediaPlayer.create(getApplicationContext(), R.raw.churchbell);
                    alarm.start();

                }
            }.start();
        }
        else {
            timerText.setText("0:30");
            timerScrubber.setProgress(30);
            counterIsActive = false;
            controllerButton.setText("GO!");
            timerScrubber.setEnabled(true);
            timer.cancel();
            if(alarm != null) alarm.stop();
        }

    }

    public void setTimerText(int timerDuration)
    {
        int minutes = (int) timerDuration/60;
        int seconds = timerDuration - minutes * 60;
        String secondString = Integer.toString(seconds);
        if(secondString == "0") secondString = "00";
        else if(seconds < 10) secondString = "0" + secondString;
        timerText.setText(Integer.toString(minutes) + ":" + secondString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerText = (TextView) findViewById(R.id.timerText);
        timerScrubber = (SeekBar) findViewById(R.id.timerScrubber);
        controllerButton = (Button) findViewById(R.id.controllerButton);

        timerScrubber.setMax(600);
        timerScrubber.setProgress(30);

        timerScrubber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                setTimerText(progress);
                timerLength = progress;
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
