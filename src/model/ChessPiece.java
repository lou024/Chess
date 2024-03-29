package model;

import java.util.ArrayList;

abstract class ChessPiece {
    String type;
    PieceColor color;
    boolean firstMove;
    final int plus;

    protected ChessPiece(PieceColor color, boolean firstMove, int plus) {
        this.color = color;
        this.firstMove = firstMove;
        this.plus = plus;
    }

    /**
     *
     * @param row on the board where piece is
     * @param column on the board where piece is
     * @param plus is -1 if piece started from the bottom of the board
     * @param board 2d array representing chess board
     * @return available moves for given piece OR 1 if piece started from the top of the board
     */
    public abstract ArrayList<Pair<Integer, Integer>> availableMoves(int row, int column, int plus, ChessCell[][] board);

    public String toString() {
        if(type == null) return "";
        return "Type: " + type + " Color: " + color;
    }
}
