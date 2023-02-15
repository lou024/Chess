package model;

import java.util.ArrayList;

abstract class ChessPiece {

    String type;
    PieceColor color;
    boolean firstMove;
    boolean enPassant_LEFT;
    boolean enPassant_RIGHT;
    final int plus;

    public ChessPiece(PieceColor color, int plus) {
        this.color = color;
        this.firstMove = false;
        this.plus = plus;
        this.enPassant_LEFT = false;
        this.enPassant_RIGHT = false;
    }

    public void updateEnPassant_LEFT() {

    }

    public void updateEnPassant_RIGHT() {

    }

    /**
     *
     * @param row on the board where piece is
     * @param column on the board where piece is
     * @param plus is -1 if piece started from the bottom of the board
     * @param board 2d array representing chess board
     * @return ArrayList of available moves for given piece OR 1 if piece started from the top of the board
     */
    public abstract ArrayList<Pair<Integer, Integer>> availableMoves(int row, int column, int plus, ChessCell[][] board);

    public String toString() {
        if(type == null) return "";
        return "Type: " + type + " Color: " + color;
    }

}
