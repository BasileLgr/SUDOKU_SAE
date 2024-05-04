import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JTextField;

public class GridPanel extends JPanel {
    protected static final int TAILLE_GRILLE = 9; // Taille standard d'une grille de Sudoku
    protected PlayerCell[][] cellules = new PlayerCell[TAILLE_GRILLE][TAILLE_GRILLE];

    public GridPanel() {
        setLayout(new GridLayout(TAILLE_GRILLE, TAILLE_GRILLE)); // Utilisation de GridLayout pour la grille 9x9
        initGrille();
    }

    protected void initGrille() {
        for (int i = 0; i < TAILLE_GRILLE; i++) {
            for (int j = 0; j < TAILLE_GRILLE; j++) {
                cellules[i][j] = new PlayerCell(i, j, this); // Création d'une nouvelle Cellule avec référence à ce GridPanel
                add(cellules[i][j]); // Ajout de la cellule au panel
            }
        }
    }

    public PlayerCell[][] getCellules() {
        return cellules;
    }

    public boolean isValidInput(int row, int col, char input) {
        String inputStr = String.valueOf(input);

        // Vérification de l'unicité dans la cellule actuelle
        if (cellules[row][col].getText().contains(inputStr)) {
            return false; // Le chiffre est déjà présent dans la cellule
        }

        // Vérification des contraintes du Sudoku
        // Vérifie la ligne
        for (int i = 0; i < TAILLE_GRILLE; i++) {
            if (cellules[row][i].getText().contains(inputStr) && i != col) {
                return false; // Doublon trouvé dans la ligne
            }
        }

        // Vérifie la colonne
        for (int j = 0; j < TAILLE_GRILLE; j++) {
            if (cellules[j][col].getText().contains(inputStr) && j != row) {
                return false; // Doublon trouvé dans la colonne
            }
        }

        // Vérifie le bloc 3x3
        int gridRowStart = (row / 3) * 3;
        int gridColStart = (col / 3) * 3;
        for (int i = gridRowStart; i < gridRowStart + 3; i++) {
            for (int j = gridColStart; j < gridColStart + 3; j++) {
                if (cellules[i][j].getText().contains(inputStr) && (i != row || j != col)) {
                    return false; // Doublon trouvé dans le bloc 3x3
                }
            }
        }

        return true; // Aucun doublon, l'entrée est valide
    }

}
