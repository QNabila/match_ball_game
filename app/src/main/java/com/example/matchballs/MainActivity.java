package com.example.matchballs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0: yellow , 1: red , 2: Empty
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    //For winning
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8},{2, 4, 6}};
    int activePlayer = 0; //here i've used int instead of boolean so that i can increase player number
    boolean gameActive=true;

    public void dropIn(View view) //Base Class, used to create interactive UI
    {
        ImageView counter = (ImageView) view;


        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        //If  empty section is clicked ball will appear,if there is already ball and section is clicked nothing will happen
        //This is the logic for the following if ....==2

        if(gameState[tappedCounter]==2 && gameActive){
        gameState[tappedCounter] = activePlayer;  //array is tracking gameState
        counter.setTranslationY(-1500);

        if (activePlayer == 0) {
            counter.setImageResource(R.drawable.yellow);
            activePlayer = 1;
        } else {
            counter.setImageResource(R.drawable.red);
            activePlayer = 0;
        }

        counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

        for (int[] winningPosition : winningPositions) {

            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                // Somone has won!

                gameActive = false;

                String winner = "";

                if (activePlayer == 1) {

                    winner = "Yellow";

                } else {

                    winner = "Red";

                }

                //Calling them when winner is detected
                Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                 TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                 winnerTextView.setText(winner + " has won!");

                playAgainButton.setVisibility(View.VISIBLE);    //here views are makeing visibles as we made them invisible in xml previously

                winnerTextView.setVisibility(View.VISIBLE);


            }

        }
    }
}

    public void playAgain(View view) {

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        //now to playagain we need to clear the all balls from the grid layout
        //For that we used For loop and to recognize gridlayout we called it by gridLayout id
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);  //remove image view source

        }

        for (int i=0; i<gameState.length; i++) {

            gameState[i] = 2;

        }

        activePlayer = 0;

        gameActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

