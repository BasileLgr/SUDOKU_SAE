import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel {
    private JButton openButton = new JButton("Ouvrir une nouvelle grille");
    private JButton exportButton = new JButton("Exporter la grille");
    private JButton quitButton = new JButton("Quitter");

    public ButtonPanel(GridPanel gridPanel) {
        setLayout(new GridLayout(3, 1, 5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Listener added in the constructor
        openButton.addActionListener(e -> FileHandler.loadGridFromFile(gridPanel, false));
        exportButton.addActionListener(e -> FileHandler.exportGridToFile(gridPanel));
        quitButton.addActionListener(e -> System.exit(0));

        add(openButton);
        add(exportButton);
        add(quitButton);
    }

    public void addOpenButtonListener(ActionListener listener) {
        openButton.addActionListener(listener);
    }

    public void addSolveButton(ActionListener listener) {
        JButton solveButton = new JButton("Résoudre");
        solveButton.addActionListener(listener);
        add(solveButton);
    }

    public void addVerifyButton(ActionListener listener) {
        JButton verifyButton = new JButton("Vérifier");
        verifyButton.addActionListener(listener);
        add(verifyButton);
    }
}
