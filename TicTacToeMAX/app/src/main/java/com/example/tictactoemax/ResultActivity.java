package com.example.tictactoemax;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {


    private TextView resultMessage;
    private EditText playerName;
    private Button saveScoreButton;
    private Button playAgainButton;
    private Button exitButton;
    private int moves;          // Number of moves in the game
    private String message;     // game outcome

    private DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Initialize UI elements
        resultMessage = findViewById(R.id.result_message);
        playerName = findViewById(R.id.player_name);
        saveScoreButton = findViewById(R.id.save_score_button);
        playAgainButton = findViewById(R.id.play_again_button);
        exitButton = findViewById(R.id.exit_button);

        // Retrieve the result message and moves from the previous activity
        Intent intent = getIntent();
        if (intent != null) {
            Log.e("TicTacToe", "intent created");

            message = intent.getStringExtra("result_message");
            Log.e("TicTacToe", message);

            int moves = intent.getIntExtra("moves", 0);
            Log.e("TicTacToe", String.valueOf(moves));

            if (message != null) {
                resultMessage.setText(message + " after " + moves + " moves");
            }
        }

        // Initialize the database helper
        dbHelper = new DatabaseHelper(this);

        // Set click listeners for buttons

        // Define the SAVE SCORE button and set its click listener
        saveScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveScore();
            }
        });

        // Define the PLAY AGAIN button and set its click listener
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
            }
        });

        // Define the EXIT button and set its click listener
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitApp();
            }
        });
    }

    /**
     * SAVE SCORE BUTTON
     */
    private void saveScore() {
        // Get the player's name from the EditText
        String name = playerName.getText().toString().trim();

        // Ensure that a name is entered
        if (name.isEmpty()) {
            playerName.setError("Please enter your name.");
            return;
        }

        // Insert the score into the local database
        // create database
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create ContentValues to store the updated data
        ContentValues values = new ContentValues();

        // Put the new data value into ContentValues for the specified column

        // Player name
        values.put(DatabaseContract.ScoreEntry.COLUMN_NAME_PLAYER_NAME, name);

        // Number of moves
        values.put(DatabaseContract.ScoreEntry.COLUMN_NAME_MOVES, moves);

        long newRowId = db.insert(DatabaseContract.ScoreEntry.TABLE_NAME, null, values);

        // Close the database
        dbHelper.close();

        // Display a message indicating that the score is saved
        Toast.makeText(this, "Score saved!", Toast.LENGTH_SHORT).show();

    }

    /**
     * PLAY AGAIN BUTTON
     */
    private void playAgain() {
        // Start a new game (you may want to pass additional data if needed)
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish(); // Close the current result screen
    }

    /**
     * EXIT APP BUTTON
     */
    private void exitApp() {
        finish(); // Close the app
    }
}
