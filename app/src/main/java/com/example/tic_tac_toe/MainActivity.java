package com.example.tic_tac_toe;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    int[] state = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningIndex = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    String winner = "";
    private int count = 0;
    private boolean gameActive = true;
    private int redScore = 0;
    private int yellowScore = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view) {


        ImageView counter = (ImageView) view;

        Log.i("Values", counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (state[tappedCounter] == 2 && gameActive) {
            state[tappedCounter] = count;


            counter.setTranslationY(-1500);

            if (count == 0) {
                counter.setImageResource(R.drawable.yellow);
                count = 1;
            } else {
                counter.setImageResource(R.drawable.red);

                count = 0;
            }


            counter.animate().translationYBy(1500).rotation(360).setDuration(400);


            for (int[] winningPosition : winningIndex) {
                if (state[winningPosition[0]] == state[winningPosition[1]] &&
                    state[winningPosition[1]] == state[winningPosition[2]] &&
                    state[winningPosition[0]] != 2) {

                    gameActive = false;


                    if (count == 1) {
                        winner = "Yellow";
                        yellowScore++;
                    } else if (count == 0) {
                        winner = "Red";
                        redScore++;
                    }

                    Button playAgain = findViewById(R.id.playAgain);
                    TextView upperTextView = findViewById(R.id.upperTextView);

                    upperTextView.setText(winner + " is the Winner");

                    setScore();


                    playAgain.setVisibility(View.VISIBLE);
                    upperTextView.setVisibility(View.VISIBLE);


                }


            }


        }
    }


//    Helper

    public void setScore() {

        TextView yellow = findViewById(R.id.yellowScore);
        TextView red = findViewById(R.id.redScore);

        yellow.setText(String.valueOf(yellowScore));
        red.setText(String.valueOf(redScore));


    }


    public void reset() {

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {

            ImageView child = (ImageView) gridLayout.getChildAt(i);

            child.setImageDrawable(null);

        }


        for (int i = 0; i < state.length; i++) {
            state[i] = 2;
        }

        count = 0;
        gameActive = true;


        Button playAgain = findViewById(R.id.playAgain);
        TextView upperTextView = findViewById(R.id.upperTextView);


        playAgain.setVisibility(View.INVISIBLE);
        upperTextView.setVisibility(View.INVISIBLE);


    }


    public void resetGame(View view) {

        yellowScore = 0;
        redScore = 0;
        setScore();
        reset();
    }

    public void playAgain(View view) {


        reset();
    }
}