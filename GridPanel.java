import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class GridPanel extends JPanel {
    protected static final int TAILLE_GRILLE = 9; // Taille de la grille Sudoku, généralement 9x9
    protected Cell[][] cellules = new Cell[TAILLE_GRILLE][TAILLE_GRILLE];

    public GridPanel() {
        setLayout(new GridLayout(TAILLE_GRILLE, TAILLE_GRILLE)); // Utilisation de GridLayout pour la grille 9x9
        initGrille();
    }

    protected void initGrille() {
        for (int i = 0; i < TAILLE_GRILLE; i++) {
            for (int j = 0; j < TAILLE_GRILLE; j++) {
                Cell cell = new Cell(i, j, this); // Création d'une nouvelle Cellule avec référence à ce GridPanel
                cellules[i][j] = cell;
                add(cell); // Ajout de la cellule au panel
            }
        }
    }

    public Cell[][] getCellules() {
        return cellules;
    }

    public boolean isValidInput(int row, int col, char input) {
        for (int i = 0; i < TAILLE_GRILLE; i++) {
            // Vérifiez les lignes et les colonnes pour les entrées en double
            if (cellules[row][i].getText().equals(String.valueOf(input)) || cellules[i][col].getText().equals(String.valueOf(input))) {
                return false;
            }
        }

        // Vérifiez les sous-grilles 3x3 pour les entrées en double
        int gridRowStart = (row / 3) * 3;
        int gridColStart = (col / 3) * 3;
        for (int i = gridRowStart; i < gridRowStart + 3; i++) {
            for (int j = gridColStart; j < gridColStart + 3; j++) {
                if (cellules[i][j].getText().equals(String.valueOf(input))) {
                    return false;
                }
            }
        }

        return true; // Retourne vrai si l'entrée est valide selon les règles du Sudoku
    }
}
