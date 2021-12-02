package com.example.tic_tac_toe;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int[] state = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningIndex = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view) {


        ImageView counter = (ImageView) view;

        Log.i("Values", counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

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

                if (count == 1)
                    Toast.makeText(this, "The winner is yellow", Toast.LENGTH_SHORT).show();


                else if (count == 0)
                    Toast.makeText(this, "The winner is red", Toast.LENGTH_SHORT).show();

            }
        }

    }

}