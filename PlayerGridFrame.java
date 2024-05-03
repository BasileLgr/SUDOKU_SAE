import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
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
        if (isGridFull() && areAllSumsValid()) {
            JOptionPane.showMessageDialog(this, "La grille est correctement résolue!", "Vérification Réussie", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "La grille n'est pas correctement résolue!", "Erreur de Vérification", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isGridFull() {
        for (int i = 0; i < GridPanel.TAILLE_GRILLE; i++) {
            for (int j = 0; j < GridPanel.TAILLE_GRILLE; j++) {
                if (gridPanel.getCellules()[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean areAllSumsValid() {
        for (int i = 0; i < GridPanel.TAILLE_GRILLE; i++) {
            if (!checkRow(i) || !checkColumn(i) || !checkBox(i)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkRow(int row) {
        boolean[] seen = new boolean[9];
        for (int i = 0; i < GridPanel.TAILLE_GRILLE; i++) {
            int num = Character.getNumericValue(gridPanel.getCellules()[row][i].getText().charAt(0)) - 1;
            if (seen[num]) return false;
            seen[num] = true;
        }
        return true;
    }

    private boolean checkColumn(int col) {
        boolean[] seen = new boolean[9];
        for (int i = 0; i < GridPanel.TAILLE_GRILLE; i++) {
            int num = Character.getNumericValue(gridPanel.getCellules()[i][col].getText().charAt(0)) - 1;
            if (seen[num]) return false;
            seen[num] = true;
        }
        return true;
    }

    private boolean checkBox(int index) {
        boolean[] seen = new boolean[9];
        int rowStart = (index / 3) * 3;
        int colStart = (index % 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int num = Character.getNumericValue(gridPanel.getCellules()[rowStart + i][colStart + j].getText().charAt(0)) - 1;
                if (seen[num]) return false;
                seen[num] = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PlayerGridFrame());
    }
}
