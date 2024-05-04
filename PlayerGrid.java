import javax.swing.JPanel;
import java.awt.GridLayout;

/**
 * La classe <code>PlayerGrid</code> étend <code>GridPanel</code> pour représenter une grille de jeu joueur dans un jeu de Sudoku.
 */
public class PlayerGrid extends GridPanel {

    /**
     * Constructeur pour créer une nouvelle instance de <code>PlayerGrid</code>.
     * Initialise la grille en appelant le constructeur de la classe parente pour initialiser la grille.
     */
    public PlayerGrid() {
        super();  // Appelle le constructeur de la classe parente pour initialiser la grille
    }

    /**
     * Initialise la grille en remplaçant la méthode initiale par un layout de grille et des cellules de joueur.
     */
    @Override
    protected void initGrille() {
        setLayout(new GridLayout(TAILLE_GRILLE, TAILLE_GRILLE));
        cellules = new PlayerCell[TAILLE_GRILLE][TAILLE_GRILLE];  // Assure que cellules peut contenir des objets PlayerCell

        for (int i = 0; i < TAILLE_GRILLE; i++) {
            for (int j = 0; j < TAILLE_GRILLE; j++) {
                PlayerCell cell = new PlayerCell(i, j, this);  // Crée PlayerCell plutôt que Cell
                cellules[i][j] = cell;  // Aucun problème de type ne devrait survenir ici
                add(cell);  // Ajoute à la grille
            }
        }
    }
}
