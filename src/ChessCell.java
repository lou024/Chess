import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * @author Luis Estevez
 * Nov 2020 - Jan 2021
 */

public class ChessCell extends JButton {

    ChessPiece pieceInCell;
    int rowPosition, colPosition;
    boolean cellColor, setPiece;

    public ChessCell(int rowPosition, int colPosition, ChessPanel panel, boolean cellColor) {
        super();
        this.pieceInCell = null;
        this.rowPosition = rowPosition;
        this.colPosition = colPosition;
        this.cellColor = cellColor;
        this.setPiece = false;
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setChosenCell(rowPosition, colPosition);
            }
        });
    }

    public void setPiece(ChessPiece piece) {
        this.pieceInCell = piece;
        if(piece != null){
            // set pawn icons
            if(piece.type == PieceType.P) {
                Icon ic;
                Image imageIcon;
                if(piece.color == PieceColor.WHITE){
                    imageIcon = new ImageIcon("./icons/white_pawn.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                }else {
                     imageIcon = new ImageIcon("./icons/black_pawn.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                }
                ic = new ImageIcon(imageIcon);
                super.setIcon(ic);

                // set bishop icons
            }else if(piece.type == PieceType.B) {
                Icon ic;
                Image imageIcon;
                if(piece.color == PieceColor.WHITE){
                    imageIcon = new ImageIcon("./icons/white_bishop.png").getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
                }else {
                    imageIcon = new ImageIcon("./icons/black_bishop.png").getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
                }
                ic = new ImageIcon(imageIcon);
                super.setIcon(ic);

                // set knight icons
            } else if(piece.type == PieceType.Kn) {
                Icon ic;
                Image imageIcon;
                if(piece.color == PieceColor.WHITE) {
                    imageIcon = new ImageIcon("./icons/white_knight.png").getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
                }else {
                    imageIcon = new ImageIcon("./icons/black_knight.png").getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
                }
                ic = new ImageIcon(imageIcon);
                super.setIcon(ic);

                // set rook icons
            } else if(piece.type == PieceType.R) {
                Icon ic;
                Image imageIcon;
                if(piece.color == PieceColor.WHITE){
                    imageIcon = new ImageIcon("./icons/white_rook.png").getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
                }else {
                    imageIcon = new ImageIcon("./icons/black_rook.png").getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
                }
                ic = new ImageIcon(imageIcon);
                super.setIcon(ic);

                // set king icons
            } else if(piece.type == PieceType.K) {
                Icon ic;
                Image imageIcon;
                if(piece.color == PieceColor.WHITE) {
                    imageIcon = new ImageIcon("./icons/white_king.png").getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
                }else {
                    imageIcon = new ImageIcon("./icons/black_king.png").getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
                }
                ic = new ImageIcon(imageIcon);
                super.setIcon(ic);

                // set queen icons
            } else {
                Icon ic;
                Image imageIcon;
                if(piece.color == PieceColor.WHITE){
                    imageIcon = new ImageIcon("./icons/white_queen.png").getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
                }else {
                    imageIcon = new ImageIcon("./icons/black_queen.png").getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
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
