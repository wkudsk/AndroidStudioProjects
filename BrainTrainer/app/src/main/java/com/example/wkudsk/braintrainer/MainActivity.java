package com.example.wkudsk.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button start;
    ArrayList<Integer> answers;
    GridLayout buttonAnswers;
    TextView question;
    int correctAnswerLocation;
    int score;
    int numberofQuestions;
    TextView scoreView;
    TextView result;
    TextView time;
    Button topRight;
    Button topLeft;
    Button botRight;
    Button botLeft;
    TextView finalScore;


    public void start(View view)
    {
        finalScore.setVisibility(View.INVISIBLE);
        start.setVisibility(View.INVISIBLE);
        buttonAnswers.setVisibility(GridLayout.VISIBLE);
        result.setVisibility(View.VISIBLE);
        time.setVisibility(View.VISIBLE);
        scoreView.setVisibility(View.VISIBLE);
        question.setVisibility(View.VISIBLE);
        score = 0;
        numberofQuestions = 0;
        scoreView.setText("0/0");
        time.setText("30s");


        CountDownTimer timer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time.setText(Integer.toString((int) millisUntilFinished/1000) +"s");
            }

            @Override
            public void onFinish() {

                buttonAnswers.setVisibility(GridLayout.INVISIBLE);
                question.setVisibility(View.INVISIBLE);
                result.setVisibility(View.INVISIBLE);
                time.setVisibility(View.INVISIBLE);
                scoreView.setVisibility(View.INVISIBLE);
                finalScore.setText("Your score is: " + Integer.toString(score) + "/" + Integer.toString(numberofQuestions));
                finalScore.setVisibility(View.VISIBLE);
                start.setVisibility(View.VISIBLE);
            }
        }.start();
        newQuestion();
    }

    public void chooseAnswer(View view)
    {
        if(view.getTag().toString().equals(Integer.toString(correctAnswerLocation)))
        {
            score++;
            result.setText("Correct!");
        }
        else
        {
            result.setText("Wrong!");
        }
        numberofQuestions++;
        scoreView.setText(Integer.toString(score) + "/" + Integer.toString(numberofQuestions));
        newQuestion();
    }

    public void newQuestion()
    {
        answers = new ArrayList<Integer>();
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        question.setText(Integer.toString(a) + " + " + Integer.toString(b) + " = ");
        correctAnswerLocation = rand.nextInt(3);

        int incorrectAnswer;
        for(int i = 0; i < 4; i++)
        {
            if(i == correctAnswerLocation)
            {
                answers.add(a + b);
            }
            else
            {
                incorrectAnswer = rand.nextInt(41);
                while(incorrectAnswer == a + b)
                {
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        topLeft.setText(Integer.toString(answers.get(0)));
        topRight.setText(Integer.toString(answers.get(1)));
        botLeft.setText(Integer.toString(answers.get(2)));
        botRight.setText(Integer.toString(answers.get(3)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        finalScore = (TextView) findViewById(R.id.finalScore);
        start = (Button) findViewById(R.id.startButton);
        buttonAnswers = (GridLayout) findViewById(R.id.answers);
        question = (TextView) findViewById(R.id.question);
        time = (TextView) findViewById(R.id.time);
        score = 0;
        numberofQuestions = 0;
        scoreView = (TextView) findViewById(R.id.score);
        result = (TextView) findViewById(R.id.resultView);
        topRight = (Button) findViewById(R.id.topRight);
        topLeft = (Button) findViewById(R.id.topLeft);
        botRight = (Button) findViewById(R.id.botRight);
        botLeft = (Button) findViewById(R.id.botLeft);
        newQuestion();
    }
}
