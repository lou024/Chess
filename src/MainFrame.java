import model.ChessPanel;
import model.ControlPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        super();
        final JPanel mainPanel = new JPanel(new BorderLayout());
        final ChessPanel chessPanel = new ChessPanel();
        final ControlPanel controlPanel = new ControlPanel(chessPanel);

        mainPanel.add(chessPanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.LINE_END);
        setContentPane(mainPanel);
        setTitle("Simple Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension pref = new Dimension(screenSize.width * 2/3, screenSize.height * 2/3);
        setPreferredSize(pref);
        setMinimumSize(pref);
    }
}
