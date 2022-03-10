package model;

import java.util.ArrayList;

public class Bishop extends ChessPiece {

    public Bishop(PieceColor color, int plus) {
        super(color, plus);
        this.type = "Bishop";
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> availableMoves(int row, int column, int plus, ChessCell[][] board) {
        ArrayList<Pair<Integer, Integer>> out = new ArrayList<>();
        // diagonal left-up
        for(int i = 1; i < 8 && row-i >= 0 && column-i >= 0; i++) {
            if(board[row-i][column-i].pieceInCell == null || color != board[row-i][column-i].pieceInCell.color) {
                out.add(new Pair<>(row - i, column - i));
                if(board[row-i][column-i].pieceInCell != null) break;
            } else break;
        }
        // diagonal right-up
        for(int i = 1; i < 8 && row-i >= 0 && column+i <= 7; i++) {
            if(board[row-i][column+i].pieceInCell == null || color != board[row-i][column+i].pieceInCell.color) {
                out.add(new Pair<>(row - i, column + i));
                if(board[row-i][column+i].pieceInCell != null)break;
            } else break;
        }
        // diagonal left-down
        for(int i = 1; i < 8 && row+i <= 7 && column-i >= 0; i++) {
            if(board[row+i][column-i].pieceInCell == null || color != board[row+i][column-i].pieceInCell.color) {
                out.add(new Pair<>(row + i, column - i));
                if(board[row+i][column-i].pieceInCell != null)break;
            } else break;
        }
        // diagonal right-down
        for(int i = 1; i < 8 && row+i <= 7 && column+i <= 7; i++) {
            if(board[row+i][column+i].pieceInCell == null || color != board[row+i][column+i].pieceInCell.color) {
                out.add(new Pair<>(row + i, column + i));
                if(board[row+i][column+i].pieceInCell != null)break;
            }else break;
        }
        return out;
    }

}
