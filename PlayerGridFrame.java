import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

public class PlayerGridFrame extends JFrame {
    private PlayerGrid gridPanel;
    private ButtonPanel buttonPanel;

    public PlayerGridFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Mode Joueur - Sudoku");
        setLayout(new BorderLayout());

        gridPanel = new PlayerGrid();
        buttonPanel = new ButtonPanel(gridPanel);

        // Ajoute des boutons spécifiques au mode joueur
        buttonPanel.addSolveButton(e -> solveGrid());
        buttonPanel.addVerifyButton(e -> verifyGrid());

        add(gridPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void solveGrid() {
        Solver solver = new Solver(gridPanel);
        boolean solved = solver.solve();
        if (!solved) {
            System.out.println("Aucune solution possible.");
        }
    }

    private void verifyGrid() {
        // Implémentez la logique pour vérifier si la grille est correctement résolue
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PlayerGridFrame());
    }
}
