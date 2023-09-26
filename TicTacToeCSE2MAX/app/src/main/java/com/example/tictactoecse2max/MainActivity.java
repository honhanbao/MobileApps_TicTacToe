package com.example.tictactoecse2max;

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
                // Handle the "Start" button click
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

    private void startGame() {
        // Start the game activity
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    private void viewHighScores() {
        // Start the high scores activity
        Intent intent = new Intent(this, HighScoresActivity.class);
        startActivity(intent);
    }

    private void exitApp() {
        // Exit the app
        finish();
    }
}
