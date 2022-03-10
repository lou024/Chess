package model;

import java.util.ArrayList;

public class King extends ChessPiece {

    public King(PieceColor color, int plus) {
        super(color, plus);
        this.type = "King";
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> availableMoves(int row, int column, int plus, ChessCell[][] board) {
        ArrayList<Pair<Integer, Integer>> out = new ArrayList<>();
        if(row+1 <= 7 && (board[row+1][column].pieceInCell == null
                || color != board[row+1][column].pieceInCell.color)) out.add(new Pair<>(row+1, column));
        if(row-1 >= 0 && (board[row-1][column].pieceInCell == null
                || color != board[row-1][column].pieceInCell.color)) out.add(new Pair<>(row-1, column));
        if(column+1 <= 7 && (board[row][column+1].pieceInCell == null
                || color != board[row][column+1].pieceInCell.color)) out.add(new Pair<>(row, column+1));
        if(column-1 >= 0 && (board[row][column-1].pieceInCell == null
                || color != board[row][column-1].pieceInCell.color)) out.add(new Pair<>(row, column-1));


        if(column+1 <= 7 && row-1 >= 0 && (board[row-1][column+1].pieceInCell == null
                || color != board[row-1][column+1].pieceInCell.color)) out.add(new Pair<>(row-1, column+1));
        if(column-1 >= 0 && row-1 >= 0 && (board[row-1][column-1].pieceInCell == null
                || color != board[row-1][column-1].pieceInCell.color)) out.add(new Pair<>(row-1, column-1));
        if(column+1 <= 7 && row+1 <= 7 && (board[row+1][column+1].pieceInCell == null
                || color != board[row+1][column+1].pieceInCell.color)) out.add(new Pair<>(row+1, column+1));
        if(column-1 >= 0 && row+1 <= 7 && (board[row+1][column-1].pieceInCell == null
                || color != board[row+1][column-1].pieceInCell.color)) out.add(new Pair<>(row+1, column-1));

        return out;
    }

}
