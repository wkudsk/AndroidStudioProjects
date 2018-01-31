package com.example.wkudsk.connect3;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    boolean redTurn;
    boolean gameNotWon;
    int[] GameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view) {
        if (gameNotWon) {
            ImageView counter = (ImageView) view;
            int tappedCounter = Integer.parseInt(counter.getTag().toString());

            if (GameState[tappedCounter] == 2) {

                counter.setTranslationY(-1000f);
                if (redTurn) {
                    counter.setImageResource(R.drawable.red);
                    GameState[tappedCounter] = 1;
                    redTurn = !redTurn;
                } else {
                    counter.setImageResource(R.drawable.yellow);
                    GameState[tappedCounter] = 0;
                    redTurn = !redTurn;
                }

                counter.animate().translationYBy(1000f).rotation(360f).setDuration(500);
            }

            boolean tieGame = true;
            for(int i = 0 ; i < GameState.length; i++)
            {
                if(GameState[i] == 2) {
                    tieGame = false;
                }
            }
            if(tieGame)
            {
                TextView winningMessage = (TextView) findViewById(R.id.winning);
                winningMessage.setText("It's a Draw!");
                LinearLayout youWin = (LinearLayout) findViewById(R.id.winLayout);
                youWin.setBackgroundColor(Color.MAGENTA);
                youWin.setVisibility(View.VISIBLE);
            }
            for (int[] winningPosition : winningPositions) {
                if (GameState[winningPosition[0]] == GameState[winningPosition[1]] &&
                        GameState[winningPosition[1]] == GameState[winningPosition[2]] &&
                        GameState[winningPosition[0]] != 2) {
                    String winner;
                    gameNotWon = false;
                    if (GameState[winningPosition[0]] == 0) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    TextView winningMessage = (TextView) findViewById(R.id.winning);
                    winningMessage.setText(winner + " has won!");
                    LinearLayout youWin = (LinearLayout) findViewById(R.id.winLayout);
                    if (winner == "Yellow") {
                        youWin.setBackgroundColor(Color.YELLOW);
                    } else {
                        youWin.setBackgroundColor(Color.RED);
                    }
                    youWin.setVisibility(View.VISIBLE);

                }
            }


        }
    }

    public void Reset(View view)
    {
        LinearLayout youWin = (LinearLayout) findViewById(R.id.winLayout);
        youWin.setVisibility(View.INVISIBLE);
        redTurn =  true;
        gameNotWon = true;
        for(int i = 0; i < GameState.length; i++)
        {
            GameState[i] = 2;
        }

        GridLayout board = (GridLayout) findViewById(R.id.layoutBoard);
        for(int i = 0; i <board.getChildCount(); i++)
        {
            ((ImageView) board.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        redTurn = true;
        gameNotWon = true;
    }
}
