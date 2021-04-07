package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class   MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //activePlayer 0= yellow,1= red
    //2 means unplayed
    int activePlayer=0;
    int [] gameState={2,2,2,2,2,2,2,2,2};
    int [][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    boolean gameIsActive =true;

    public void dropIn(View view) {
        ImageView counter = (ImageView)view;

        int tappedCounter=Integer.parseInt(counter.getTag().toString());


        if(gameState[tappedCounter]==2 && gameIsActive) {
            gameState[tappedCounter]=activePlayer;
            counter.setTranslationY(-1000f);
            TextView turn = findViewById(R.id.turn);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
                turn.animate().setDuration(300);
            turn.setText("Red's Turn");

            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
                turn.animate().setDuration(300);
                turn.setText("Yellow's turn");
            }
            counter.animate().translationYBy(1000f).setDuration(300);

            for(int [] position :winningPositions){
                if (gameState[position[0]] == gameState[position[1]] &&
                        gameState[position[1]] == gameState[position[2]] &&
                        gameState[position[0]] != 2) {

                    gameIsActive=false;

                    String winner = "Red Won";

                     if (gameState[position[0]]==0){
                        winner ="Yellow Won";
                    }

//                    else
//                        winner="Match Drawn";

                   TextView winnerMessage = findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner);


                    LinearLayout layout =(LinearLayout) findViewById(R.id.layout);

                    layout.setVisibility(View.VISIBLE);
                }

                else{
                    boolean gameOver = true;
                    for(int countState : gameState){
                        if(countState==2)
                            gameOver =false;
                    }
                    if(gameOver){
                        TextView winnerMessage = findViewById(R.id.winnerMessage);
                        winnerMessage.setText("Game Draw");


                        LinearLayout layout =(LinearLayout) findViewById(R.id.layout);

                        layout.setVisibility(View.VISIBLE);

                    }
                }
                
            }
        }
    }

    public void playAgain(View view) {
        gameIsActive=true;
         LinearLayout layout =(LinearLayout) findViewById(R.id.layout);
         activePlayer=0;


        for (int i = 0; i < gameState.length; i++) {

            gameState[i] = 2;

        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for (int i = 0; i< gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }
        layout.setVisibility(View.INVISIBLE);
    }

}