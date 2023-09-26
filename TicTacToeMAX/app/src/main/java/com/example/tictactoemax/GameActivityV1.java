package com.example.tictactoemax;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class GameActivityV1 extends AppCompatActivity {
    private Button[][] buttons = new Button[3][3];
    private boolean player1Turn = true;
    private int roundCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        // Reset game
        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        // Loop through rows (i) and columns (j) to initialize buttons in the 3x3 grid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Create a unique identifier for each button based on its row and column
                String buttonID = "button_" + i + j;

                // Get the resource ID of the button using its identifier
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());

                // Find the button view by its resource ID and assign it to the buttons array
                buttons[i][j] = findViewById(resID);

                // Add a Toast message to indicate that the button has been found and assigned
                Toast.makeText(this, "Button found: " + buttonID, Toast.LENGTH_SHORT).show();

                // Set an onClickListener for the button to display a Toast message
                if (buttons[i][j] == null) {
                    // Log an error message if the button is not found
                    Log.e("TicTacToe", "Button not found for ID: " + buttonID);
                } else {
                    // Set an onClickListener for the button to display a Toast message
                    buttons[i][j].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.e("TicTacToe", "Button ID: " + buttonID + "is clicked");

                            // Check if the clicked button already has text (i.e., is not empty)
                            if (!((Button) v).getText().toString().equals("")) {
                                return; // Return early if the button is not empty (no action needed)
                            }

                            // Set "X" or "O" on the clicked button based on the current player's turn
                            if (player1Turn) {
                                ((Button) v).setText("X"); // Set "X" if it's Player 1's turn
                                // player1Turn = !player1Turn;
                            } else {
                                ((Button) v).setText("O"); // Set "O" if it's Player 2's turn
                                // player1Turn = !player1Turn;
                            }

                            // Increment the round count to keep track of moves
                            roundCount++;

                            // Check if there is a win (either player wins the game)
                            if (checkForWin()) {
                                // Determine which player won and call the corresponding method
                                if (player1Turn) {
                                    player1Wins(); // Player 1 wins
                                } else {
                                    player2Wins(); // Player 2 wins
                                }
                            } else if (roundCount == 9) {
                                // If there's no win and the round count reaches 9, it's a draw
                                draw();
                            } else {
                                // Switch the turn to the other player for the next move
                                player1Turn = !player1Turn;
                            }
                        }
                    });
                }
            }
        }
    }


    /**
     * Checks if there is a win condition in the Tic-Tac-Toe game.
     *
     * @return True if there is a win, false otherwise.
     */
    private boolean checkForWin() {
        // Create a 3x3 grid to store the current state of the buttons
        String[][] field = new String[3][3];

        // Fill the grid with the text (either "X" or "O") of the buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        // Check rows for a win
        for (int i = 0; i < 3; i++) {
            // If all buttons in a row have the same non-empty text, it's a win
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")) {
                return true;
            }
        }

        // Check columns for a win
        for (int i = 0; i < 3; i++) {
            // If all buttons in a column have the same non-empty text, it's a win
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")) {
                return true;
            }
        }

        // Check diagonals for a win
        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) {
            // Diagonal from top-left to bottom-right
            return true;
        }

        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")) {
            // Diagonal from top-right to bottom-left
            return true;
        }

        // If none of the win conditions are met, return false
        return false;
    }

    /**
     * Handles the case when Player 1 wins the game.
     * Displays a toast message and resets the game.
     */
    private void player1Wins() {
        // Display a toast message indicating that Player 1 wins
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();

        // Reset the game to its initial state
        //`resetGame();
    }

    /**
     * Handles the case when Player 2 wins the game.
     * Displays a toast message and resets the game.
     */
    private void player2Wins() {
        // Display a toast message indicating that Player 2 wins
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();

        // Reset the game to its initial state
        // resetGame();
    }

    /**
     * Handles the case when the game ends in a draw.
     * Displays a toast message and resets the game.
     */
    private void draw() {
        // Display a toast message indicating that the game is a draw
        Toast.makeText(this, "It's a draw!", Toast.LENGTH_SHORT).show();

        // Reset the game to its initial state
//        resetGame();
    }

    /**
     * Resets the game by clearing the button text, resetting the player's turn,
     * and resetting the round count.
     */
    private void resetGame() {
        // Clear the text on all buttons in the grid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        // Reset the player's turn to Player 1
        player1Turn = true;

        // Reset the round count to 0
        roundCount = 0;
    }
}
