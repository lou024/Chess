import javax.swing.*;
import java.awt.*;

/*
 * @author Luis Estevez
 * Nov 2020 - Jan 2021
 */

public class MainFrame extends JFrame {

    private JPanel mainPanel;
    private ChessPanel chessPanel;
    private ControlPanel controlPanel;
    public MainFrame() {
        super();
        mainPanel = new JPanel(new BorderLayout());
        chessPanel = new ChessPanel();
        controlPanel = new ControlPanel(chessPanel);

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
