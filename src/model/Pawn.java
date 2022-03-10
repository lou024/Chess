package model;

import java.util.ArrayList;

public class Pawn extends ChessPiece {

    public Pawn(PieceColor color, int plus) {
        super(color, plus);
        this.type = "Pawn";
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> availableMoves(int row, int column, int plus, ChessCell[][] board) {
        ArrayList<Pair<Integer, Integer>> out = new ArrayList<>();
        if(row+plus >=0 && row+plus <= 7 && board[row+plus][column].pieceInCell == null) {
            out.add(new Pair<>(row + plus, column));
            if(!firstMove && row+(2*plus) <= 7)out.add(new Pair<>(row+(2*plus), column));
        }
        if(row+plus >= 0 && row+plus <= 7 && column-1 >= 0
                && board[row+plus][column-1].pieceInCell != null
                && color != board[row+plus][column-1].pieceInCell.color)out.add(new Pair<>(row+plus, column-1));
        if(row+plus >= 0 && row+plus <= 7 && column+1 <= 7
                && board[row+plus][column+1].pieceInCell != null
                && color != board[row+plus][column+1].pieceInCell.color)out.add(new Pair<>(row+plus, column+1));
        return out;
    }

}
