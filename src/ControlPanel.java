import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * @author Luis Estevez
 * Nov 2020 - Jan 2021
 */
public class ControlPanel extends JPanel {

    private JButton newGame;
    private JTextField inProgressText, inProgress, turn, player, selectedPieceText, selectedPiece;
    private final ChessPanel chessPanel;
    public ControlPanel(ChessPanel chessPanel) {
        super(new GridBagLayout());
        // set main frame
        this.chessPanel = chessPanel;
        newGame = new JButton("New Game");
        // shows whose turn it is
        turn = new JTextField("Turn: ");
        turn.setEditable(false);
        player = chessPanel.playerTurn;
        // displays game status
        inProgressText = new JTextField("Game Status: ");
        inProgressText.setEditable(false);
        inProgress = new JTextField("In Progress");
        inProgress.setEditable(false);
        // displays selected chess piece
        selectedPieceText = new JTextField("Selected Chess Piece: ");
        selectedPieceText.setEditable(false);
        selectedPiece = chessPanel.selectedPiece;

        // adding actionlisteners to buttons
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chessPanel.newGame();
            }
        });

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
        add(inProgress,gbc);

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
