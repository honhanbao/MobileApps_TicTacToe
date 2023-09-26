package com.example.tictactoemax;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class HighScoresActivity extends AppCompatActivity {

    private ListView highScoresListView;
    private Button playAgainButton;
    private Button exitButton;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        // Initialize UI elements
        highScoresListView = findViewById(R.id.high_scores_list);
        playAgainButton = findViewById(R.id.play_again_button);
        exitButton = findViewById(R.id.exit_button);

        // Initialize the database helper
        dbHelper = new DatabaseHelper(this);

        // Retrieve and display high scores
        List<Score> highScores = dbHelper.getAllScores();
        displayHighScores(highScores);

        // Set click listeners for buttons
        playAgainButton.setOnClickListener(view -> playAgain());
        exitButton.setOnClickListener(view -> exitApp());
    }

    private void displayHighScores(List<Score> highScores) {
        List<String> scoreStrings = new ArrayList<>();
        for (Score score : highScores) {
            String scoreString = "Player: " + score.getPlayerName() + " | Moves: " + score.getMoves();
            scoreStrings.add(scoreString);
        }

        // Create an ArrayAdapter to display scores in the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scoreStrings);
        highScoresListView.setAdapter(adapter);
    }

    private void playAgain() {
        Intent intent = new Intent(this, GameActivityTest.class);
        startActivity(intent);
        finish(); // Close the current result screen
    }

    private void exitApp() {
        finish(); // Close the app
    }
}
