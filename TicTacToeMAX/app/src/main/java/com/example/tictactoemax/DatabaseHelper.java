package com.example.tictactoemax;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TicTacToeScores.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "scores";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PLAYER_NAME = "player_name";
    private static final String COLUMN_MOVES = "moves";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PLAYER_NAME + " TEXT, " +
                COLUMN_MOVES + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addScore(String playerName, int moves) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PLAYER_NAME, playerName);
        values.put(COLUMN_MOVES, moves);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    @SuppressLint("Range")
    public List<Score> getAllScores() {
        List<Score> scoreList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_MOVES + " ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Score score = new Score();
                score.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                score.setPlayerName(cursor.getString(cursor.getColumnIndex(COLUMN_PLAYER_NAME)));
                score.setMoves(cursor.getInt(cursor.getColumnIndex(COLUMN_MOVES)));
                scoreList.add(score);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return scoreList;
    }

    @SuppressLint("Range")
    public String getPlayerName() {
        SQLiteDatabase db = this.getReadableDatabase();
        String playerName = null;

        Cursor cursor = db.query(
                DatabaseContract.ScoreEntry.TABLE_NAME,
                new String[]{DatabaseContract.ScoreEntry.COLUMN_NAME_PLAYER_NAME},
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            playerName = cursor.getString(cursor.getColumnIndex(DatabaseContract.ScoreEntry.COLUMN_NAME_PLAYER_NAME));
        }

        cursor.close();
        db.close();

        return playerName;
    }
}


