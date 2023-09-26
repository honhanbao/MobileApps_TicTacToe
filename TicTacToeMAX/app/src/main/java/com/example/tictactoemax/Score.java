package com.example.tictactoemax;

public class Score {
    private int id;
    private String playerName;
    private int moves;

    // Constructor with all fields
    public Score(int id, String playerName, int moves) {
        this.id = id;
        this.playerName = playerName;
        this.moves = moves;
    }

    // Constructor without id (useful for inserting into the database)
    public Score(String playerName, int moves) {
        this.playerName = playerName;
        this.moves = moves;
    }

    // Default constructor (no-argument constructor)
    public Score() {
    }

    // Getter and setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and setter for playerName
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    // Getter and setter for moves
    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }
}
