package model;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class ChessPanel extends JPanel {
    ChessCell[][] board;
    ChessCell chosenCell;
    boolean blackTurn;
    JTextField playerTurn, selectedPiece;
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

        testInit();
//        init();
    }

    public void testInit() {
        board[0][0].setPiece(new Queen(PieceColor.BLACK, 1));
        board[4][4].setPiece(new King(PieceColor.WHITE, -1));

        changeTurns();
    }

    public void init() {
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
        board[0][3].setPiece(new King(PieceColor.BLACK, 1));
        board[0][4].setPiece(new Queen(PieceColor.BLACK, 1));

        // set up white pieces
        board[7][0].setPiece(new Rook(PieceColor.WHITE, -1));
        board[7][7].setPiece(new Rook(PieceColor.WHITE, -1));
        board[7][1].setPiece(new Knight(PieceColor.WHITE, -1));
        board[7][6].setPiece(new Knight(PieceColor.WHITE, -1));
        board[7][2].setPiece(new Bishop(PieceColor.WHITE, -1));
        board[7][5].setPiece(new Bishop(PieceColor.WHITE, -1));
        board[7][3].setPiece(new King(PieceColor.WHITE, -1));
        board[7][4].setPiece(new Queen(PieceColor.WHITE, -1));

        changeTurns();
    }

    private void changeTurns() {
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[0].length; c++) {
                if(blackTurn && board[r][c].pieceInCell != null
                        && board[r][c].pieceInCell.color == PieceColor.BLACK) {
                    board[r][c].setPiece = true;
                } else board[r][c].setPiece = !blackTurn && board[r][c].pieceInCell != null
                        && board[r][c].pieceInCell.color == PieceColor.WHITE;
            }
        }
        blackTurn = !blackTurn;
        if(!blackTurn) playerTurn.setText("Black");
        else playerTurn.setText("White");

    }

    public void setChosenCell(int rowPosition, int colPosition) {
        if(chosenCell != null && chosenCell.pieceInCell != null
                && pairExistsInList(chosenCell.pieceInCell
                        .availableMoves(chosenCell.rowPosition, chosenCell.colPosition,chosenCell.pieceInCell.plus, board)
                , new Pair<>(rowPosition, colPosition))) {
            System.out.println(chosenCell.pieceInCell.toString());
            chosenCell.movePiece(board[rowPosition][colPosition]);
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
        init();
        unhighlight();
    }
}
