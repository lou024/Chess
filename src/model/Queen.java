package model;

import java.util.ArrayList;

public class Queen extends ChessPiece {

    public Queen(PieceColor color, int plus) {
        super(color, plus);
        this.type = "Queen";
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
