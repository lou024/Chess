package model;

import java.util.ArrayList;

public class Rook extends ChessPiece {
    public Rook(PieceColor color, int plus) {
        super(color, false, plus);
        this.type = "Rook";
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> availableMoves(int row, int column, int plus, ChessCell[][] board) {
        ArrayList<Pair<Integer, Integer>> out = new ArrayList<>();
        // straight down
        for(int i = 1; i < 8 && row+i <= 7; i++){
            if(board[row+i][column].pieceInCell == null || color != board[row+i][column].pieceInCell.color) {
                out.add(new Pair<>(row+i, column));
                if(board[row+i][column].pieceInCell != null)break;
            }else break;
        }
        // straight up
        for(int i = 1; i < 8 && row-i >= 0; i++){
            if(board[row-i][column].pieceInCell == null || color != board[row-i][column].pieceInCell.color) {
                out.add(new Pair<>(row-i, column));
                if(board[row-i][column].pieceInCell != null)break;
            }else break;
        }
        // straight left
        for(int i = 1; i < 8 && column-i >= 0; i++){
            if(board[row][column-i].pieceInCell == null || color != board[row][column-i].pieceInCell.color) {
                out.add(new Pair<>(row, column-i));
                if(board[row][column-i].pieceInCell != null)break;
            }else break;
        }
        // straight right
        for(int i = 1; i < 8 && column+i <= 7; i++){
            if(board[row][column+i].pieceInCell == null || color != board[row][column+i].pieceInCell.color) {
                out.add(new Pair<>(row, column+i));
                if(board[row][column+i].pieceInCell != null)break;
            }else break;
        }
        return out;
    }
}
