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
                || color != board[row+1][column].pieceInCell.color) && validKingLocation(color, row+1, column, board))
            out.add(new Pair<>(row+1, column));
        if(row-1 >= 0 && (board[row-1][column].pieceInCell == null
                || color != board[row-1][column].pieceInCell.color) && validKingLocation(color, row-1, column, board))
            out.add(new Pair<>(row-1, column));
        if(column+1 <= 7 && (board[row][column+1].pieceInCell == null
                || color != board[row][column+1].pieceInCell.color) && validKingLocation(color, row, column+1, board))
            out.add(new Pair<>(row, column+1));
        if(column-1 >= 0 && (board[row][column-1].pieceInCell == null
                || color != board[row][column-1].pieceInCell.color) && validKingLocation(color, row, column-1, board))
            out.add(new Pair<>(row, column-1));


        if(column+1 <= 7 && row-1 >= 0 && (board[row-1][column+1].pieceInCell == null
                || color != board[row-1][column+1].pieceInCell.color) && validKingLocation(color, row-1, column+1, board))
            out.add(new Pair<>(row-1, column+1));
        if(column-1 >= 0 && row-1 >= 0 && (board[row-1][column-1].pieceInCell == null
                || color != board[row-1][column-1].pieceInCell.color) && validKingLocation(color, row-1, column-1, board))
            out.add(new Pair<>(row-1, column-1));
        if(column+1 <= 7 && row+1 <= 7 && (board[row+1][column+1].pieceInCell == null
                || color != board[row+1][column+1].pieceInCell.color) && validKingLocation(color, row+1, column+1, board))
            out.add(new Pair<>(row+1, column+1));

        if(column-1 >= 0 && row+1 <= 7 && (board[row+1][column-1].pieceInCell == null
                || color != board[row+1][column-1].pieceInCell.color) && validKingLocation(color, row+1, column-1, board))
            out.add(new Pair<>(row+1, column-1));

        return out;
    }

    /**
     * checks if king is in a valid position
     * @param row on the board where king is/can be
     * @param column on the board where king is/can be
     * @return boolean which is true if and only if the king
     */
    public static boolean validKingLocation(PieceColor color, int row, int column, ChessCell[][] board) {
        // QUEEN/BISHOP/ROOK check
        // straight down
        for(int i = 1; i < 8 && row+i <= 7; i++){
            if(board[row+i][column].pieceInCell == null || color != board[row+i][column].pieceInCell.color) {
                if(board[row+i][column].pieceInCell != null) {
                    if (board[row + i][column].pieceInCell instanceof Queen || board[row + i][column].pieceInCell instanceof Rook)
                        return false;
                    else break;
                }
            } else if(board[row + i][column].pieceInCell != null && !(board[row + i][column].pieceInCell instanceof King)) break;
        }
        // straight up
        for(int i = 1; i < 8 && row-i >= 0; i++){
            if(board[row-i][column].pieceInCell == null || color != board[row-i][column].pieceInCell.color) {
                if(board[row-i][column].pieceInCell != null) {
                    if (board[row - i][column].pieceInCell instanceof Queen || board[row - i][column].pieceInCell instanceof Rook)
                        return false;
                    else break;
                }
            } else if (board[row-i][column].pieceInCell != null && !(board[row-i][column].pieceInCell instanceof King)) break;
        }
        // straight left
        for(int i = 1; i < 8 && column-i >= 0; i++){
            if(board[row][column-i].pieceInCell == null || color != board[row][column-i].pieceInCell.color) {
                if(board[row][column-i].pieceInCell != null) {
                    if (board[row][column - i].pieceInCell instanceof Queen || board[row][column - i].pieceInCell instanceof Rook)
                        return false;
                    else break;
                }
            } else if(board[row][column-i].pieceInCell != null && !(board[row][column-i].pieceInCell instanceof King)) break;
        }
        // straight right
        for(int i = 1; i < 8 && column+i <= 7; i++){
            if(board[row][column+i].pieceInCell == null || color != board[row][column+i].pieceInCell.color) {
                if(board[row][column+i].pieceInCell != null) {
                    if (board[row][column + i].pieceInCell instanceof Queen || board[row][column + i].pieceInCell instanceof Rook)
                        return false;
                    else break;
                }
            } else if(board[row][column+i].pieceInCell != null && !(board[row][column+i].pieceInCell instanceof King)) break;
        }
        // diagonal left-up
        for(int i = 1; i < 8 && row-i >= 0 && column-i >= 0; i++) {
            if(board[row-i][column-i].pieceInCell == null || color != board[row-i][column-i].pieceInCell.color) {
                if (board[row - i][column - i].pieceInCell != null) {
                    if (board[row - i][column - i].pieceInCell instanceof Queen || board[row - i][column - i].pieceInCell instanceof Bishop)
                        return false;
                    else break;
                }
            }
        }
        // diagonal right-up
        for(int i = 1; i < 8 && row-i >= 0 && column+i <= 7; i++) {
            if(board[row-i][column+i].pieceInCell == null || color != board[row-i][column+i].pieceInCell.color) {
                if(board[row-i][column+i].pieceInCell != null) {
                    if (board[row - i][column + i].pieceInCell instanceof Queen || board[row - i][column + i].pieceInCell instanceof Bishop)
                        return false;
                    else break;
                }
            }
        }
        // diagonal left-down
        for(int i = 1; i < 8 && row+i <= 7 && column-i >= 0; i++) {
            if(board[row+i][column-i].pieceInCell == null || color != board[row+i][column-i].pieceInCell.color) {
                if(board[row+i][column-i].pieceInCell != null) {
                    if (board[row + i][column - i].pieceInCell instanceof Queen || board[row + i][column - i].pieceInCell instanceof Bishop)
                        return false;
                    else break;
                }
            }
        }
        // diagonal right-down
        for(int i = 1; i < 8 && row+i <= 7 && column+i <= 7; i++) {
            if(board[row+i][column+i].pieceInCell == null || color != board[row+i][column+i].pieceInCell.color) {
                if(board[row+i][column+i].pieceInCell != null) {
                    if (board[row + i][column + i].pieceInCell instanceof Queen || board[row + i][column + i].pieceInCell instanceof Bishop)
                        return false;
                    else break;
                }
            }
        }

        // KNIGHT check
        if(row-2 >= 0 && column-1 >= 0
                && (board[row-2][column-1].pieceInCell != null
                && color != board[row-2][column-1].pieceInCell.color
                && board[row-2][column-1].pieceInCell instanceof Knight))
            return false;
        if(row-2 >= 0 && column+1 <= 7
                && (board[row-2][column+1].pieceInCell != null
                && color != board[row-2][column+1].pieceInCell.color
                && board[row-2][column+1].pieceInCell instanceof Knight))
            return false;
        if(row-1 >= 0 && column-2 >= 0
                && (board[row-1][column-2].pieceInCell != null
                && color != board[row-1][column-2].pieceInCell.color
                && board[row-1][column-2].pieceInCell instanceof Knight))
            return false;
        if(row+1 <= 7 && column-2 >= 0
                && (board[row+1][column-2].pieceInCell != null
                && color != board[row+1][column-2].pieceInCell.color
                && board[row+1][column-2].pieceInCell instanceof Knight))
            return false;

        if(row-1 >= 0 && column+2 <= 7
                && (board[row-1][column+2].pieceInCell != null
                && color != board[row-1][column+2].pieceInCell.color
                && board[row-1][column+2].pieceInCell instanceof Knight))
            return false;
        if(row+1 <= 7 && column+2 <= 7
                && (board[row+1][column+2].pieceInCell != null
                && color != board[row+1][column+2].pieceInCell.color
                && board[row+1][column+2].pieceInCell instanceof Knight))
            return false;
        if(row+2 <= 7 && column-1 >= 0
                && (board[row+2][column-1].pieceInCell != null
                && color != board[row+2][column-1].pieceInCell.color
                && board[row+2][column-1].pieceInCell instanceof Knight))
            return false;
        if(row+2 <= 7 && column+1 <= 7
                && (board[row+2][column+1].pieceInCell != null
                && color != board[row+2][column+1].pieceInCell.color
                && board[row+2][column+1].pieceInCell instanceof Knight))
            return false;
        // PAWN check
        int plus = color == PieceColor.BLACK ? 1:-1;
        if((row+plus >= 0 && row+plus <= 7 && column-1 >= 0
                && board[row+plus][column-1].pieceInCell != null
                && color != board[row+plus][column-1].pieceInCell.color
                && (board[row+plus][column-1].pieceInCell instanceof Pawn || board[row+plus][column-1].pieceInCell instanceof King))
            || (row+plus >= 0 && row+plus <= 7 && column+1 <= 7
                && board[row+plus][column+1].pieceInCell != null
                && color != board[row+plus][column+1].pieceInCell.color
                && (board[row+plus][column+1].pieceInCell instanceof Pawn || board[row+plus][column+1].pieceInCell instanceof King)))return false;
        return true;
    }

}
