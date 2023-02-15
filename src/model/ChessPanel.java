package model;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class ChessPanel extends JPanel {
    ChessCell[][] board;
    ChessCell chosenCell;
    boolean blackTurn;
    JTextField playerTurn, selectedPiece, inProgress;
    King WHITE_KING, BLACK_KING;
    Pair<Integer, Integer> WHITE_KING_POSITION, BLACK_KING_POSITION;
    public ChessPanel() {
        super(new GridLayout(8, 8));
        board = new ChessCell[8][8];
        this.chosenCell = null;
        this.blackTurn = false;
        boolean black = false;
        for(int row = 0; row < board.length; row++) {

            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = new ChessCell(row, col, this, black);
                board[row][col].setBorder(new LineBorder(Color.BLACK));

                add(board[row][col]);
                board[row][col].setOpaque(true);
                if(!black){
                    board[row][col].setBackground(Color.WHITE);
                    black = true;
                } else {
                    board[row][col].setBackground(Color.BLACK);
                    board[row][col].setForeground(Color.WHITE);
                    black = false;
                }
            }
            black = !black;

        }
        playerTurn = new JTextField("White");
        playerTurn.setEditable(false);
        selectedPiece = new JTextField("none");
        selectedPiece.setEditable(false);
        inProgress = new JTextField("In Progress");
        inProgress.setEditable(false);
        testInit();
//        init();
    }

    public void testInit() {
        board[1][6].setPiece(new Pawn(PieceColor.BLACK, 1));
        board[3][7].setPiece(new Pawn(PieceColor.WHITE, -1));
        board[3][5].setPiece(new Pawn(PieceColor.WHITE, -1));

        changeTurns();changeTurns();
    }

    /**
     * sets up board for game
     */
    public void init() {
        // set up kings
        BLACK_KING = new King(PieceColor.BLACK, 1);
        BLACK_KING_POSITION = new Pair<>(0, 0);
        board[0][3].setPiece(new King(PieceColor.BLACK, 1));
        WHITE_KING = new King(PieceColor.WHITE, -1);
        WHITE_KING_POSITION = new Pair<>(7, 3);
        board[7][3].setPiece(new King(PieceColor.WHITE, -1));

        // set up pawns
        for(ChessCell cell : board[1]) cell.setPiece(new Pawn(PieceColor.BLACK, 1));
        for(ChessCell cell : board[6]) cell.setPiece(new Pawn(PieceColor.WHITE, -1));

        // set up black pieces
        board[0][0].setPiece(new Rook(PieceColor.BLACK, 1));
        board[0][7].setPiece(new Rook(PieceColor.BLACK, 1));
        board[0][1].setPiece(new Knight(PieceColor.BLACK, 1));
        board[0][6].setPiece(new Knight(PieceColor.BLACK, 1));
        board[0][2].setPiece(new Bishop(PieceColor.BLACK, 1));
        board[0][5].setPiece(new Bishop(PieceColor.BLACK, 1));
        board[0][4].setPiece(new Queen(PieceColor.BLACK, 1));

        // set up white pieces
        board[7][0].setPiece(new Rook(PieceColor.WHITE, -1));
        board[7][7].setPiece(new Rook(PieceColor.WHITE, -1));
        board[7][1].setPiece(new Knight(PieceColor.WHITE, -1));
        board[7][6].setPiece(new Knight(PieceColor.WHITE, -1));
        board[7][2].setPiece(new Bishop(PieceColor.WHITE, -1));
        board[7][5].setPiece(new Bishop(PieceColor.WHITE, -1));
        board[7][4].setPiece(new Queen(PieceColor.WHITE, -1));

        changeTurns();
    }

    private void changeTurns() {
        for (ChessCell[] chessCells : board) {
            for (int c = 0; c < board[0].length; c++) {
                if (blackTurn && chessCells[c].pieceInCell != null
                        && chessCells[c].pieceInCell.color == PieceColor.BLACK) {
                    chessCells[c].setPiece = true;
                } else chessCells[c].setPiece = !blackTurn && chessCells[c].pieceInCell != null
                        && chessCells[c].pieceInCell.color == PieceColor.WHITE;
            }
        }
        blackTurn = !blackTurn;
        if(!blackTurn) playerTurn.setText("Black");
        else playerTurn.setText("White");
//        if(!King.validKingLocation(PieceColor.WHITE, WHITE_KING_POSITION.left, WHITE_KING_POSITION.right, board)) {
//          inProgress.setText("CHECK");
//        }

    }

    public void setChosenCell(int rowPosition, int colPosition) {
        if(chosenCell != null && chosenCell.pieceInCell != null
                && pairExistsInList(chosenCell.pieceInCell
                        .availableMoves(chosenCell.rowPosition, chosenCell.colPosition,chosenCell.pieceInCell.plus, board)
                , new Pair<>(rowPosition, colPosition))) {
            // update king positions
            if(chosenCell.pieceInCell instanceof King) {
                if(chosenCell.pieceInCell.color == PieceColor.WHITE) WHITE_KING_POSITION = new Pair<>(rowPosition, colPosition);
                else BLACK_KING_POSITION = new Pair<>(rowPosition, colPosition);
            }
            // update enPassant rule variable then move piece
            if(chosenCell.pieceInCell instanceof Pawn
                    && (chosenCell.rowPosition == rowPosition+2 || chosenCell.rowPosition == rowPosition-2)) {
                if(colPosition + 1 <= 7 && board[rowPosition][colPosition+1].pieceInCell != null
                        && board[rowPosition][colPosition+1].pieceInCell instanceof Pawn
                        && board[rowPosition][colPosition+1].pieceInCell.color != chosenCell.pieceInCell.color) {
//                    System.out.println("EN PASSANT HERE: " + rowPosition + ", " + (colPosition+1));
                    board[rowPosition][colPosition+1].pieceInCell.updateEnPassant_RIGHT();
                }
                if(colPosition - 1 >= 0 && board[rowPosition][colPosition-1].pieceInCell != null
                        && board[rowPosition][colPosition-1].pieceInCell instanceof Pawn
                        && board[rowPosition][colPosition-1].pieceInCell.color != chosenCell.pieceInCell.color) {
//                    System.out.println("EN PASSANT HERE: " + rowPosition + ", " + (colPosition-1));
                    board[rowPosition][colPosition-1].pieceInCell.updateEnPassant_LEFT();
                }
            }
            // capture pawn and set to NULL that triggered EnPassant
            int plusValue = chosenCell.pieceInCell.plus;
            if(chosenCell.pieceInCell.enPassant_LEFT && rowPosition == chosenCell.rowPosition+plusValue
                    && colPosition == chosenCell.colPosition+1) {
                clearChessCell(board[chosenCell.rowPosition][chosenCell.colPosition+1]);
            }
            if(chosenCell.pieceInCell.enPassant_RIGHT && rowPosition == chosenCell.rowPosition+plusValue
                    && colPosition == chosenCell.colPosition-1) {
                clearChessCell(board[chosenCell.rowPosition][chosenCell.colPosition-1]);
            }
            chosenCell.movePiece(board[rowPosition][colPosition]);
            clearEnPassantRule(board[rowPosition][colPosition].pieceInCell.color);
            chosenCell = null;
            selectedPiece.setText("none");
            changeTurns();
            unhighlight();
        }else {
            ChessPiece p = board[rowPosition][colPosition].pieceInCell;
            if (p != null && board[rowPosition][colPosition].setPiece) {
                System.out.println("current row: " + rowPosition + ", col: " + colPosition);
                highlight(p.availableMoves(rowPosition, colPosition, p.plus, board));
                System.out.println(p.availableMoves(rowPosition, colPosition, p.plus, board).toString());
                selectedPiece.setText(p.type);
                this.chosenCell = board[rowPosition][colPosition];
            }

        }
    }

    /**
     * set EnPassant boolean to false for all Pawns of given color
     * @param color of chess pieces to clear
     */
    private void clearEnPassantRule(PieceColor color) {
        for(ChessCell[] cells : board) {
            for(ChessCell c : cells) {
                if (c.pieceInCell != null && c.pieceInCell.color == color && c.pieceInCell instanceof Pawn) {
                    if(c.pieceInCell.enPassant_LEFT) c.pieceInCell.updateEnPassant_LEFT();
                    if(c.pieceInCell.enPassant_RIGHT) c.pieceInCell.updateEnPassant_RIGHT();
                }
            }
        }
    }

    private void clearChessCell(ChessCell toClear) {
        toClear.pieceInCell = null;
        toClear.setIcon(null);
    }

    public boolean pairExistsInList(ArrayList<Pair<Integer, Integer>> list, Pair<Integer, Integer> pair) {
        for(Pair<Integer, Integer> p : list) {
            if(p.equals(pair))return true;
        }
        return false;
    }

    public void highlight(ArrayList<Pair<Integer, Integer>> list) {
        unhighlight();
        for(Pair<Integer, Integer> pair : list) {
            board[pair.left][pair.right].setBackground(Color.getHSBColor(0.33116f, 0.4112f, 0.690123f));
        }
    }

    public void unhighlight() {
        for(ChessCell[] cells : board) {
            for(ChessCell c : cells) {
                if(c.cellColor){
                    c.setBackground(Color.BLACK);
                }else c.setBackground(Color.WHITE);
            }
        }
    }

    public void newGame() {
        for(ChessCell[] cells : board) {
            for(ChessCell c : cells) {
                if(c.pieceInCell != null) {
                    c.pieceInCell = null;
                    c.setIcon(null);
                }
            }
        }

        playerTurn.setText("White");
        selectedPiece.setText("none");
        blackTurn = false;
        chosenCell = null;
//        init();
        testInit();
        unhighlight();
        System.out.println("\n\nNew Game Started\n");

    }
}
