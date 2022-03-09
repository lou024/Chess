package model;

import javax.swing.*;
import java.awt.*;

public class ChessCell extends JButton {
    ChessPiece pieceInCell;
    int rowPosition, colPosition;
    boolean cellColor, setPiece;
    static String CURRENT_DIRECTORY = System.getProperty("user.dir");

    public ChessCell(int rowPosition, int colPosition, ChessPanel panel, boolean cellColor) {
        super();
        this.pieceInCell = null;
        this.rowPosition = rowPosition;
        this.colPosition = colPosition;
        this.cellColor = cellColor;
        this.setPiece = false;
        addActionListener(actionEvent -> panel.setChosenCell(rowPosition, colPosition));
    }

    public void setPiece(ChessPiece piece) {
        this.pieceInCell = piece;
        if(piece != null){
            // set pawn icons
            if(piece instanceof Pawn) {
                Icon ic;
                Image imageIcon;
                if(piece.color == PieceColor.WHITE){
                    imageIcon = new ImageIcon(CURRENT_DIRECTORY + "./src/icons/white_pawn.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                }else {
                    imageIcon = new ImageIcon(CURRENT_DIRECTORY + "./src/icons/black_pawn.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                }
                ic = new ImageIcon(imageIcon);
                super.setIcon(ic);

                // set bishop icons
            }else if(piece instanceof Bishop) {
                Icon ic;
                Image imageIcon;
                if(piece.color == PieceColor.WHITE){
                    imageIcon = new ImageIcon(CURRENT_DIRECTORY + "./src/icons/white_bishop.png").getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
                }else {
                    imageIcon = new ImageIcon(CURRENT_DIRECTORY + "./src/icons/black_bishop.png").getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
                }
                ic = new ImageIcon(imageIcon);
                super.setIcon(ic);

                // set knight icons
            } else if(piece instanceof Knight) {
                Icon ic;
                Image imageIcon;
                if(piece.color == PieceColor.WHITE) {
                    imageIcon = new ImageIcon(CURRENT_DIRECTORY + "./src/icons/white_knight.png").getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
                }else {
                    imageIcon = new ImageIcon(CURRENT_DIRECTORY + "./src/icons/black_knight.png").getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
                }
                ic = new ImageIcon(imageIcon);
                super.setIcon(ic);

                // set rook icons
            } else if(piece instanceof Rook) {
                Icon ic;
                Image imageIcon;
                if(piece.color == PieceColor.WHITE){
                    imageIcon = new ImageIcon(CURRENT_DIRECTORY + "./src/icons/white_rook.png").getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
                }else {
                    imageIcon = new ImageIcon(CURRENT_DIRECTORY + "./src/icons/black_rook.png").getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
                }
                ic = new ImageIcon(imageIcon);
                super.setIcon(ic);

                // set king icons
            } else if(piece instanceof King) {
                Icon ic;
                Image imageIcon;
                if(piece.color == PieceColor.WHITE) {
                    imageIcon = new ImageIcon(CURRENT_DIRECTORY + "./src/icons/white_king.png").getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
                }else {
                    imageIcon = new ImageIcon(CURRENT_DIRECTORY + "./src/icons/black_king.png").getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
                }
                ic = new ImageIcon(imageIcon);
                super.setIcon(ic);

                // set queen icons
            } else {
                Icon ic;
                Image imageIcon;
                if(piece.color == PieceColor.WHITE){
                    imageIcon = new ImageIcon(CURRENT_DIRECTORY + "./src/icons/white_queen.png").getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
                }else {
                    imageIcon = new ImageIcon(CURRENT_DIRECTORY + "./src/icons/black_queen.png").getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
                }
                ic = new ImageIcon(imageIcon);
                super.setIcon(ic);
            }
        }

    }

    public void movePiece(ChessCell moveTo) {
        moveTo.setPiece(pieceInCell);
        this.pieceInCell.firstMove = true;
        this.setIcon(null);
        this.pieceInCell = null;
        setText("");
    }
}
