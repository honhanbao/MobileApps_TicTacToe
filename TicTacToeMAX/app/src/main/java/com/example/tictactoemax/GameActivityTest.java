package com.example.tictactoemax;

        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;

public class GameActivityTest extends AppCompatActivity {
    private ImageView[][] imageViews = new ImageView[3][3];
    private int[][] field = new int[3][3];
    private boolean player1Turn = true;
    private int roundCount = 0;
    private TextView totalMoveCountTextView;

    private TextView playerSymbolTextView;

    private TextView gameOutcomeTextView;
    private String gameOutcomeMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_test);

        totalMoveCountTextView = findViewById(R.id.totalMoveCountTextView);
        playerSymbolTextView = findViewById(R.id.playerSymbolTextView);
        gameOutcomeTextView = findViewById(R.id.gameOutcomeTextView);


        // Define the Go to Home button and set its click listener
        Button goToHomeButton = findViewById(R.id.goToHomeButton);
        goToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHomePage();
            }
        });

        // Define the Send Result button and set its click listener
        Button sendResultButton = findViewById(R.id.sendResultButton);
        sendResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResult();
            }
        });


        // Define the End Game button and set its click listener
        Button endGameButton = findViewById(R.id.endGameButton);
        endGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call finishAffinity() to close this activity and all parent activities
                finishAffinity();
            }
        });

        // Define the Reset Game button and set its click listener
        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        // Initialize imageviews in the game board ImageView
        // initializeImageviews();
        initializeImageviewsTest();
    }

    /**
     * GO TO HOME BUTTON
     */
    private void goToHomePage(){
        Intent intent = new Intent(GameActivityTest.this, MainActivity.class);
        startActivity(intent);
    }

    private void reinitializeField() {
        // Loop through rows (i) and columns (j) to initialize imageview in the 3x3 grid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = 0;
            }
        }
    }

    private void initializeImageviewsTest() {
        // Loop through rows (i) and columns (j) to initialize imageview in the 3x3 grid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                initializeImageviewTest(i, j);
            }
        }
    }
    /**
     * Initialize imageview and set click listeners
     */
    private void initializeImageviews() {
        // Loop through rows (i) and columns (j) to initialize imageview in the 3x3 grid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                initializeImageview(i, j);
            }
        }
    }

    private void initializeImageviewTest(final int i, final int j) {

        // Initialize field to check winning
        field[i][j] = 0;

        String imageviewID = "imageview_" + i + j;

        // Get the resource ID of the imageview using its identifier
        int resID = getResources().getIdentifier(imageviewID, "id", getPackageName());
        // Toast.makeText(this, "ImageView found: " + resID, Toast.LENGTH_SHORT).show();
        Log.d("TicTacToe", "imageviewID: " + imageviewID + ", resID: " + resID);

        // Find the imageview view by its resource ID and assign it to the imageview array
        imageViews[i][j] = findViewById(resID);

        // Set an onClickListener for the imageview to display a Toast message
        if (imageViews[i][j] == null) {
            // Log an error message if the ImageView is not found
            Log.e("TicTacToe", "ImageView not found for ID: " + imageviewID);
        } else {
            // Add a Toast message to indicate that the ImageView has been found and assigned
            // Toast.makeText(this, "ImageView found: " + imageviewID, Toast.LENGTH_SHORT).show();

            // Log a message confirming ImageView is found
            Log.e("TicTacToe", "ImageView found for ID: " + imageviewID);

            imageViews[i][j].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // onImageViewClick(i, j);
                    onImageViewClickTest(i, j);
                }
            });
        }
    }

    /**
     * Initialize an individual imageview
     * @param i
     * @param j
     */
    private void initializeImageview(final int i, final int j) {

        // Create a unique identifier for each imageview based on its row and column
        String imageviewID = "imageview_" + i + j;

        // Get the resource ID of the imageview using its identifier
        int resID = getResources().getIdentifier(imageviewID, "id", getPackageName());
        // Toast.makeText(this, "ImageView found: " + resID, Toast.LENGTH_SHORT).show();
        Log.d("TicTacToe", "imageviewID: " + imageviewID + ", resID: " + resID);

        // Find the imageview view by its resource ID and assign it to the imageview array
         imageViews[i][j] = findViewById(resID);
         // Toast.makeText(this, "ImageView found: " + findViewById(resID), Toast.LENGTH_SHORT).show();
        Log.e("TicTacToe", "ImageView not found for ID: " + imageviewID + "resID: " + resID);

        // Set an onClickListener for the imageview to display a Toast message
        if (imageViews[i][j] == null) {
            // Log an error message if the ImageView is not found
            Log.e("TicTacToe", "ImageView not found for ID: " + imageviewID);
        } else {
            // Add a Toast message to indicate that the ImageView has been found and assigned
            Toast.makeText(this, "ImageView found: " + imageviewID, Toast.LENGTH_SHORT).show();

            // Set an onClickListener for the ImageView to display a Toast message
            imageViews[i][j].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onImageViewClick(i, j);
                }
            });
        }
    }

    private void onImageViewClickTest(int i, int j) {
        String imageviewID = "imageview_" + i + j;
        // Log.e("TicTacToe", "Imageview ID: " + imageviewID + " is clicked");
        // Toast.makeText(this, "Imageview ID: " + imageviewID+ " is clicked", Toast.LENGTH_SHORT).show();

        if (imageViews[i][j].getDrawable() != null) {
            Log.d("TicTacToe", "Imageview ID: " + imageviewID + " is not empty, no action needed");
            return; // Return early if the imageview is not empty (no action needed)
        }

        Log.e("TicTacToe","imageViews ");

        if(!checkForWinTest()) {    // lock game board if one player win.
            // Set "X" or "O" on the clicked imageview based on the current player's turn
            if (player1Turn) {
                //imageViews[i][j].setText("O"); // Set "X" if it's Player 1's turn
                imageViews[i][j].setImageResource(R.drawable.cross);

                // set array field
                field[i][j] = 1;
            } else {
                //imageViews[i][j].setText("O"); // Set "O" if it's Player 2's turn
                imageViews[i][j].setImageResource(R.drawable.naught);

                // set array field
                field[i][j] = 2;
            }

            // Increment the round count to keep track of moves
            roundCount++;
            Log.e("TicTacToe", "roundCount increased ");

            // Update total move on TextView
            roundCountUpdate(roundCount);
            Log.e("TicTacToe", "roundCount updated ");

            // Update player turn on TextView
            playerTurnUpdate();
            Log.e("TicTacToe", "playerTurnUpdated ");

            // Check if there is a win (either player wins the game)
            if (checkForWinTest()) {
                Log.e("TicTacToe", "checkForWinTest ");
                // Determine which player won and call the corresponding method
                if (player1Turn) {
                    player1WinsTest(); // Player 1 wins
                    Log.e("TicTacToe", "player1WinsTest ");
                } else {
                    player2WinsTest(); // Player 2 wins
                    Log.e("TicTacToe", "player2WinsTest ");
                }
            } else if (roundCount == 9) {
                // If there's no win and the round count reaches 9, it's a draw
                drawTest();
                Log.e("TicTacToe", "draw ");
            } else {
                // Switch the turn to the other player for the next move
                player1Turn = !player1Turn;
                Log.e("TicTacToe", "switch player ");
            }
        }
    }

    /**
     * Handle a imageview click
     * @param i
     * @param j
     */
    private void onImageViewClick(int i, int j) {
        String imageviewID = "imageview_" + i + j;
        // Log.e("TicTacToe", "Imageview ID: " + imageviewID + " is clicked");
        // Toast.makeText(this, "Imageview ID: " + imageviewID+ " is clicked", Toast.LENGTH_SHORT).show();

        if (imageViews[i][j].getDrawable() != null) {
            Log.d("TicTacToe", "Imageview ID: " + imageviewID + " is not empty, no action needed");
            return; // Return early if the imageview is not empty (no action needed)
        }

        // Set "X" or "O" on the clicked imageview based on the current player's turn
        if (player1Turn) {
            //imageViews[i][j].setText("O"); // Set "X" if it's Player 1's turn
            imageViews[i][j].setImageResource(R.drawable.cross);
        } else {
            //imageViews[i][j].setText("O"); // Set "O" if it's Player 2's turn
            imageViews[i][j].setImageResource(R.drawable.naught);
        }

        // Increment the round count to keep track of moves
        roundCount++;

        // Update total move on TextView
        roundCountUpdate(roundCount);

        // Update player turn on TextView
        playerTurnUpdate();

        // Check if there is a win (either player wins the game)
        if (checkForWin()) {
            // Determine which player won and call the corresponding method
            if (player1Turn) {
                player1Wins(); // Player 1 wins

                // sendResult();    // send to result activity
            } else {
                player2Wins(); // Player 2 wins
                // sendResult();    // send to result activity
            }

            // Make all imageViews not clickable
            disableAllImageViews();
        } else if (roundCount == 9) {
            // If there's no win and the round count reaches 9, it's a draw
            draw();
            //sendResult();         // send to result activity
        } else {
            // Switch the turn to the other player for the next move
            player1Turn = !player1Turn;
        }
    }

    // Method to disable all imageViews
    private void disableAllImageViews() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                imageViews[i][j].setClickable(false);
            }
        }
    }
    private boolean checkForWinTest() {

        // Check rows for a win
        for (int i = 0; i < 3; i++) {
            // If all imageview in a row have the same non-empty text, it's a win
            if (field[i][0] == field[i][1] && field[i][0]== field[i][2] && imageViews[i][0].getDrawable() != null) {
                return true;
            }
        }

        // Check columns for a win
        for (int i = 0; i < 3; i++) {
            // If all imageview in a column have the same non-empty text, it's a win
            if (field[0][i] == field[1][i] && field[0][i]== field[2][i] && imageViews[0][i].getDrawable() != null) {
                return true;
            }
        }

        // Check diagonals for a win
        if (field[0][0] == field[1][1] && field[0][0]== field[2][2] && imageViews[0][0].getDrawable() != null) {
            // Diagonal from top-left to bottom-right
            return true;
        }

        if (field[0][2] == field[1][1] && field[0][2]== field[2][0] && imageViews[0][2].getDrawable() != null) {
            // Diagonal from top-right to bottom-left
            return true;
        }

        // If none of the win conditions are met, return false
        return false;
    }

    /**
     * Checks if there is a win condition in the Tic-Tac-Toe game.
     *
     * @return True if there is a win, false otherwise.
     */
    private boolean checkForWin() {
        // Create a 3x3 grid to store the current state of the imageview
        int[][] field = new int[3][3];

        // Fill the grid with the text (either "X" or "O") of the imageview
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = (int) imageViews[i][j].getTag();
            }
        }

        // Check rows for a win
        for (int i = 0; i < 3; i++) {
            // If all imageview in a row have the same non-empty text, it's a win
            if (field[i][0] == field[i][1] && field[i][0]== field[i][2] && imageViews[i][0].getDrawable() != null) {
                return true;
            }
        }

        // Check columns for a win
        for (int i = 0; i < 3; i++) {
            // If all imageview in a column have the same non-empty text, it's a win
            if (field[0][i] == field[1][i] && field[0][i]== field[2][i] && imageViews[0][i].getDrawable() != null) {
                return true;
            }
        }

        // Check diagonals for a win
        if (field[0][0] == field[1][1] && field[0][0]== field[2][2] && imageViews[0][0].getDrawable() != null) {
            // Diagonal from top-left to bottom-right
            return true;
        }

        if (field[0][2] == field[1][1] && field[0][2]== field[2][0] && imageViews[0][2].getDrawable() != null) {
                // Diagonal from top-right to bottom-left
                return true;
        }

        // If none of the win conditions are met, return false
        return false;
    }

    private void player1WinsTest() {
        // Display a toast message indicating that Player 1 wins
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();

        // Display outcome on TextView
        gameOutcomeTextView.setText("Player 1 wins!");
        gameOutcomeMessage = "Player 1 wins";
    }

    /**
     * Handles the case when Player 1 wins the game.
     * Displays a toast message and resets the game.
     */
    private void player1Wins() {
        // Display a toast message indicating that Player 1 wins
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();

        // Display outcome on TextView
        gameOutcomeTextView.setText("Player 1 wins!");

        // Hide TextView
//        playerSymbolTextView.setVisibility(View.GONE);
//        playerTextView.setVisibility(View.GONE);

        // Reset the game to its initial state
        //`resetGame();
    }

    private void player2WinsTest() {
        // Display a toast message indicating that Player 2 wins
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();

        // Display outcome on TextView
        gameOutcomeTextView.setText("Player 2 wins!");
        gameOutcomeMessage = "Player 1 loses";

    }

    /**
     * Handles the case when Player 2 wins the game.
     * Displays a toast message and resets the game.
     */
    private void player2Wins() {
        // Display a toast message indicating that Player 2 wins
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();

        // Display outcome on TextView
        gameOutcomeTextView.setText("Player 2 wins!");

        // Hide TextView
//        playerSymbolTextView.setVisibility(View.GONE);
//        playerTextView.setVisibility(View.GONE);


        // Reset the game to its initial state
        // resetGame();
    }

    private void drawTest() {
        // Display a toast message indicating that the game is a draw
        Toast.makeText(this, "It's a draw!", Toast.LENGTH_SHORT).show();

        // Display outcome on TextView
        gameOutcomeTextView.setText("It's a draw!");
        gameOutcomeMessage = "It's a draw";
    }

    /**
     * Handles the case when the game ends in a draw.
     * Displays a toast message and resets the game.
     */
    private void draw() {
        // Display a toast message indicating that the game is a draw
        Toast.makeText(this, "It's a draw!", Toast.LENGTH_SHORT).show();

        // Display outcome on TextView
        gameOutcomeTextView.setText("It's a draw!");

        // Hide TextView
//        playerSymbolTextView.setVisibility(View.GONE);
//        playerTextView.setVisibility(View.GONE);

        // Reset the game to its initial state
//        resetGame();
    }

    /**
     * Resets the game by clearing the imageview , resetting the player's turn,
     * and resetting the round count.
     */
    private void resetGame() {
        // Clear the text on all imageview in the grid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                imageViews[i][j].setImageDrawable(null);
                field[i][j] = 0;
            }
        }

        // Reset the player's turn to Player 1
        player1Turn = true;

        // Reset the round count to 0
        roundCount = 0;

        // Update roundCount on TextView
        roundCountUpdate(roundCount);

        // Reset player turn on Textview
        playerSymbolTextView.setText("1");

        // hide result
        gameOutcomeTextView.setText("");

        // Unhide TextView
//        playerTextView.setVisibility(View.VISIBLE);
//        playerSymbolTextView.setVisibility(View.VISIBLE);
    }

    private void roundCountUpdate(int roundCount){
        // Convert the int roundCount to a String
        String roundCountString = String.valueOf(roundCount);

        // Set the text of the totalMoveCountTextView
        totalMoveCountTextView.setText(roundCountString);
    }

    private void playerTurnUpdate(){
        if(player1Turn){
            playerSymbolTextView.setText("2");
        }
        else{
            playerSymbolTextView.setText("1");
        }
    }

    /**
     * More to Result Screen/Activity if game over
     */
    private void sendResult() {
        Intent resultIntent = new Intent(GameActivityTest.this, ResultActivity.class);

        resultIntent.putExtra("result_message", gameOutcomeMessage);
        resultIntent.putExtra("moves", roundCount);

        startActivity(resultIntent);
    }







}

