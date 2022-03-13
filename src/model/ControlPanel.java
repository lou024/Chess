package model;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    public ControlPanel(ChessPanel chessPanel) {
        super(new GridBagLayout());
        final JTextField inProgressText, turn, player, selectedPieceText, selectedPiece;
        // set main frame
        final JButton newGame = new JButton("New Game");
        // shows whose turn it is
        turn = new JTextField("Turn: ");
        turn.setEditable(false);
        player = chessPanel.playerTurn;
        // displays game status
        inProgressText = new JTextField("Game Status: ");
        inProgressText.setEditable(false);
        // displays selected chess piece
        selectedPieceText = new JTextField("Selected Chess Piece: ");
        selectedPieceText.setEditable(false);
        selectedPiece = chessPanel.selectedPiece;

        // adding action listeners to buttons
        newGame.addActionListener(actionEvent -> chessPanel.newGame());

        // placing buttons and text fields
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        int t = gbc.fill;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(newGame,gbc);

        gbc.fill = t;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets.left = 10;
        gbc.gridwidth = 1;
        add(inProgressText,gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets.left = 1;
        gbc.insets.right = 10;
        add(chessPanel.inProgress,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets.left = 10;
        gbc.insets.right = 1;
        gbc.gridwidth = 1;
        add(turn,gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets.left = 1;
        gbc.insets.right = 10;
        add(player,gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets.left = 10;
        gbc.insets.right = 1;
        add(selectedPieceText,gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets.left = 1;
        gbc.insets.right = 10;
        add(selectedPiece,gbc);
    }
}
