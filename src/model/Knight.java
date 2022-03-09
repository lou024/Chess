package model;

import java.util.ArrayList;

public class Knight extends ChessPiece {
    public Knight(PieceColor color, int plus) {
        super(color, false, plus);
        this.type = "Knight";
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> availableMoves(int row, int column, int plus, ChessCell[][] board) {
        ArrayList<Pair<Integer, Integer>> out = new ArrayList<>();
        if(row-2 >= 0 && column-1 >= 0
                && (board[row-2][column-1].pieceInCell == null
                || color != board[row-2][column-1].pieceInCell.color))
            out.add(new Pair<>(row-2, column-1));
        if(row-2 >= 0 && column+1 <= 7
                && (board[row-2][column+1].pieceInCell == null
                || color != board[row-2][column+1].pieceInCell.color))
            out.add(new Pair<>(row-2, column+1));
        if(row-1 >= 0 && column-2 >= 0
                && (board[row-1][column-2].pieceInCell == null
                || color != board[row-1][column-2].pieceInCell.color))
            out.add(new Pair<>(row-1, column-2));
        if(row+1 <= 7 && column-2 >= 0
                && (board[row+1][column-2].pieceInCell == null
                || color != board[row+1][column-2].pieceInCell.color))
            out.add(new Pair<>(row+1, column-2));

        if(row-1 >= 0 && column+2 <= 7
                && (board[row-1][column+2].pieceInCell == null
                || color != board[row-1][column+2].pieceInCell.color))
            out.add(new Pair<>(row-1, column+2));
        if(row+1 <= 7 && column+2 <= 7
                && (board[row+1][column+2].pieceInCell == null
                || color != board[row+1][column+2].pieceInCell.color))
            out.add(new Pair<>(row+1, column+2));
        if(row+2 <= 7 && column-1 >= 0
                && (board[row+2][column-1].pieceInCell == null
                || color != board[row+2][column-1].pieceInCell.color))
            out.add(new Pair<>(row+2, column-1));
        if(row+2 <= 7 && column+1 <= 7
                && (board[row+2][column+1].pieceInCell == null
                || color != board[row+2][column+1].pieceInCell.color))
            out.add(new Pair<>(row+2, column+1));
        return out;
    }
}
