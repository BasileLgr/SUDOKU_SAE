import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

public class GrilleFrame extends JFrame {
    private GridPanel gridPanel;
    private ButtonPanel buttonPanel;

    public GrilleFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Éditeur de Sudoku");
        setLayout(new BorderLayout());

        gridPanel = new GridPanel();
        buttonPanel = new ButtonPanel(gridPanel);

        // Use the new method to add listener
        buttonPanel.addOpenButtonListener(e -> FileHandler.loadGridFromFile(gridPanel, false));

        add(gridPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GrilleFrame());
    }
}
