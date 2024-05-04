import javax.swing.JOptionPane; // Import nécessaire pour JOptionPane

/**
 * La classe <code>Solver</code> résout une grille de Sudoku représentée par un <code>GridPanel</code>.
 */
public class Solver {
    private GridPanel gridPanel;

    /**
     * Constructeur pour créer une nouvelle instance de <code>Solver</code>.
     * @param gridPanel le panneau de la grille à résoudre
     */
    public Solver(GridPanel gridPanel) {
        this.gridPanel = gridPanel;
    }

    /**
     * Résout la grille de Sudoku.
     * @return true si la grille est résolue avec succès, false sinon
     */
    public boolean solve() {
        for (int row = 0; row < GridPanel.TAILLE_GRILLE; row++) {
            for (int col = 0; col < GridPanel.TAILLE_GRILLE; col++) {
                PlayerCell cell = gridPanel.getCellules()[row][col];
                if (cell.getText().isEmpty()) {
                    for (char num = '1'; num <= '9'; num++) {
                        if (gridPanel.isValidInput(row, col, num)) {
                            cell.setText(String.valueOf(num));
                            if (solve()) {
                                return true;
                            } else {
                                cell.setText("");
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
