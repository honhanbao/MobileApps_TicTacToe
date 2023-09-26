package com.example.tictactoecse2max;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
    private int moves; // Number of moves in the game

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
            String message = intent.getStringExtra("result_message");
            moves = intent.getIntExtra("moves", 0);

            if (message != null) {
                resultMessage.setText(message);
            }
        }

        // Initialize the database helper
        dbHelper = new DatabaseHelper(this);

        // Set click listeners for buttons
        saveScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveScore();
            }
        });

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitApp();
            }
        });
    }

    private void saveScore() {
        // Get the player's name from the EditText
        String name = playerName.getText().toString().trim();

        // Ensure that a name is entered
        if (name.isEmpty()) {
            playerName.setError("Please enter your name.");
            return;
        }

        // Insert the score into the local database
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.ScoreEntry.COLUMN_NAME_PLAYER_NAME, name);
        values.put(DatabaseContract.ScoreEntry.COLUMN_NAME_MOVES, moves);

        long newRowId = db.insert(DatabaseContract.ScoreEntry.TABLE_NAME, null, values);

        // Close the database
        dbHelper.close();

        // Optionally, you can display a message indicating that the score is saved
        // For example: Toast.makeText(this, "Score saved!", Toast.LENGTH_SHORT).show();
        // Display a message indicating that the score is saved
        Toast.makeText(this, "Score saved!", Toast.LENGTH_SHORT).show();

        // After saving the score, you can perform additional actions as needed
    }

    private void playAgain() {
        // Start a new game (you may want to pass additional data if needed)
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish(); // Close the current result screen
    }

    private void exitApp() {
        finish(); // Close the app
    }
}
