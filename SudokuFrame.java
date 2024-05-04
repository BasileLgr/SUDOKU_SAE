import javax.swing.*;
import java.awt.BorderLayout;

public class SudokuFrame extends JFrame {
    private GridPanel gridPanel;
    private ButtonPanel buttonPanel;

    public SudokuFrame(boolean isPlayerMode) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(isPlayerMode ? "Mode Joueur - Sudoku" : "Éditeur de Sudoku");
        setLayout(new BorderLayout());

        gridPanel = isPlayerMode ? new PlayerGrid() : new GridPanel();
        buttonPanel = createButtonPanel(isPlayerMode);

        add(gridPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private ButtonPanel createButtonPanel(boolean isPlayerMode) {
        ButtonPanel panel = new ButtonPanel();
        panel.addButton("Ouvrir", e -> FileHandler.loadGridFromFile(gridPanel, isPlayerMode));
        panel.addButton("Exporter", e -> FileHandler.exportGridToFile(gridPanel));
        panel.addButton("Quitter", e -> System.exit(0));

        if (isPlayerMode) {
            panel.addButton("Résoudre", e -> solveGrid());
            panel.addButton("Vérifier", e -> verifyGrid());
        }

        return panel;
    }

    private boolean isGridFull() {
        for (int i = 0; i < gridPanel.TAILLE_GRILLE; i++) {
            for (int j = 0; j < gridPanel.TAILLE_GRILLE; j++) {
                if (gridPanel.getCellules()[i][j].getText().trim().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }


    private boolean areAllSumsValid() {
        return areRowsValid() && areColumnsValid() && areBoxesValid();
    }

    private boolean areRowsValid() {
        for (int row = 0; row < GridPanel.TAILLE_GRILLE; row++) {
            if (!isPartValid(row, 0, 0, 1)) return false;
        }
        return true;
    }

    private boolean areColumnsValid() {
        for (int col = 0; col < GridPanel.TAILLE_GRILLE; col++) {
            if (!isPartValid(0, col, 1, 0)) return false;
        }
        return true;
    }

    private boolean areBoxesValid() {
        for (int row = 0; row < GridPanel.TAILLE_GRILLE; row += 3) {
            for (int col = 0; col < GridPanel.TAILLE_GRILLE; col += 3) {
                if (!isPartValid(row, col, 1, 1)) return false;
            }
        }
        return true;
    }

    // Generic method to check a row, column, or box
    private boolean isPartValid(int startRow, int startCol, int rowIncrement, int colIncrement) {
        boolean[] seen = new boolean[GridPanel.TAILLE_GRILLE];
        for (int i = 0; i < GridPanel.TAILLE_GRILLE; i++) {
            int row = startRow + i * rowIncrement;
            int col = startCol + i * colIncrement;
            int value = Character.getNumericValue(gridPanel.getCellules()[row][col].getText().charAt(0));
            if (seen[value - 1]) return false;
            seen[value - 1] = true;
        }
        return true;
    }

    private void solveGrid() {
        long startTime = System.nanoTime();  // Début de la mesure du temps

        Solver solver = new Solver(gridPanel);
        boolean solved = solver.solve();

        long endTime = System.nanoTime();  // Fin de la mesure du temps
        long duration = (endTime - startTime) / 1_000_000;  // Convertir en millisecondes

        if (!solved) {
            JOptionPane.showMessageDialog(this, "Aucune solution possible.", "Résolution", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "La grille a été résolue en " + duration + " ms!", "Résolution Réussie", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void verifyGrid() {
        if (isGridFull() && areAllSumsValid()) {
            JOptionPane.showMessageDialog(this, "La grille est correctement résolue!", "Vérification Réussie", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "La grille n'est pas correctement résolue!", "Erreur de Vérification", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Vous pouvez modifier cette partie pour accepter un argument qui spécifie le mode
        boolean isPlayerMode = args.length > 0 && args[0].equals("player");
        SwingUtilities.invokeLater(() -> new SudokuFrame(isPlayerMode));
    }
}
