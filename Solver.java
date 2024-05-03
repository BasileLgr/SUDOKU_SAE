import javax.swing.JOptionPane; // Import nécessaire pour JOptionPane

public class Solver {
    private GridPanel gridPanel;

    public Solver(GridPanel gridPanel) {
        this.gridPanel = gridPanel;
    }

    public boolean solve() {
        long startTime = System.nanoTime(); // Démarre le chronomètre
        boolean result = solveInternal();  // Appelle la méthode récursive privée pour résoudre
        long endTime = System.nanoTime();   // Arrête le chronomètre
        long duration = (endTime - startTime) / 1_000_000;  // Convertit en millisecondes
        JOptionPane.showMessageDialog(null, "Résolution complétée en " + duration + " ms", "Temps de résolution", JOptionPane.INFORMATION_MESSAGE);
        return result;
    }

    private boolean solveInternal() {
        for (int row = 0; row < GridPanel.TAILLE_GRILLE; row++) {
            for (int col = 0; col < GridPanel.TAILLE_GRILLE; col++) {
                Cell cell = gridPanel.getCellules()[row][col];
                if (cell.getText().isEmpty()) {
                    for (char num = '1'; num <= '9'; num++) {
                        if (gridPanel.isValidInput(row, col, num)) {
                            cell.setText(String.valueOf(num));
                            if (solveInternal()) {
                                return true;
                            } else {
                                cell.setText(""); // backtrack
                            }
                        }
                    }
                    return false; // trigger backtrack
                }
            }
        }
        return true; // Solution trouvée
    }
}
