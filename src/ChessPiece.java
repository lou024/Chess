import java.util.ArrayList;

/*
 * @author Luis Estevez
 * Nov 2020 - Jan 2021
 */

public class ChessPiece {
    PieceType type;
    PieceColor color;
    boolean firstMove;
    final int plus;

    public ChessPiece(PieceType type, PieceColor color, int plus) {
        this.type = type;
        this.color = color;
        this.firstMove = false;
        this.plus = plus;
    }

    // returns available moves for given piece
    // plus is -1 if piece started from the bottom of the board
    // OR 1 if piece started from the top of the board
    public ArrayList<Pair<Integer, Integer>> availableMoves(int row, int column, int plus, ChessCell[][] board) {
        ArrayList<Pair<Integer, Integer>> out = new ArrayList<>();
        // Pawn - moves one cell forward (can move 2 if first move)
        // attacks only one cell diagonal
        if(type == PieceType.P) {
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

            // Bishop - moves and attacks only in diagonal moves
        } else if(type == PieceType.B) {
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
            // Rook - moves and attacks horizontal or vertical
        } else if(type == PieceType.R) {
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
            // Knight - moves and attacks in 'L' shape for example: 2 cells forward and one cell left
        } else if(type == PieceType.Kn) {
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

        } else if(type == PieceType.K) {
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

            // if piece is not one from above then must be a Queen
            // Queen - moves horizontal, vertical and diagonal
        } else {
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
        }
        return out;
    }

    public String toString() {
        if(type == null) return "";
        return "Type: " + type.toString() + " Color: " + color;
    }

}
