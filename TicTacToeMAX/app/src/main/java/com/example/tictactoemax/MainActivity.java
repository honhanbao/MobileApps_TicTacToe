package com.example.tictactoemax;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find buttons by their IDs
        Button startButton = findViewById(R.id.start_button);
        Button highScoreButton = findViewById(R.id.high_score_button);
        Button exitButton = findViewById(R.id.exit_button);

        // Set click listeners for the buttons
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });

        highScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the "High Score" button click
                viewHighScores();
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the "Exit" button click
                exitApp();
            }
        });
    }

    /**
     *
     */
    private void startGame() {
        // Start the game activity
        //Intent intent = new Intent(MainActivity.this, GameActivity.class);
        Intent intent = new Intent(MainActivity.this, GameActivityTest.class);
        startActivity(intent);
    }

    /**
     *
     */
    private void viewHighScores() {
        // Start the high scores activity
        Intent intent = new Intent(MainActivity.this, HighScoresActivity.class);
        startActivity(intent);
    }

    /**
     * finish() is a built-in method in Android, and it's part of the Android Activity class.
     * finish() method is used to close the current activity and return to the previous activity or
     * to exit the app if the current activity is the main (root) activity.
     */
    private void exitApp() {
        // Exit the app
        finish();
    }
}