package com.example.tictactoecse2max;

import android.provider.BaseColumns;

public final class DatabaseContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DatabaseContract() {}

    // Inner class that defines the table contents
    public static class ScoreEntry implements BaseColumns {
        public static final String TABLE_NAME = "scores";
        public static final String COLUMN_NAME_PLAYER_NAME = "player_name";
        public static final String COLUMN_NAME_MOVES = "moves";
    }
}
