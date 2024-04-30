import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.GridLayout;

public class ButtonPanel extends JPanel {
    private JButton openButton = new JButton("Ouvrir une nouvelle grille");
    private JButton exportButton = new JButton("Exporter la grille");
    private JButton quitButton = new JButton("Quitter");

    public ButtonPanel(GridPanel gridPanel) {
        setLayout(new GridLayout(3, 1, 5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        openButton.addActionListener(e -> FileHandler.loadGridFromFile(gridPanel));
        exportButton.addActionListener(e -> FileHandler.exportGridToFile(gridPanel));
        quitButton.addActionListener(e -> System.exit(0));

        add(openButton);
        add(exportButton);
        add(quitButton);
    }
}
