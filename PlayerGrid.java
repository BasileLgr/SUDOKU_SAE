import javax.swing.JPanel;
import java.awt.GridLayout;

public class PlayerGrid extends GridPanel {  // Ensure that PlayerGrid extends GridPanel

    public PlayerGrid() {
        super();  // Call superclass constructor to initialize the grid
        //initGrille();  // Override this method if necessary
    }

    @Override
    protected void initGrille() {
        setLayout(new GridLayout(TAILLE_GRILLE, TAILLE_GRILLE));
        cellules = new PlayerCell[TAILLE_GRILLE][TAILLE_GRILLE];  // Make sure cellules can hold PlayerCell objects

        for (int i = 0; i < TAILLE_GRILLE; i++) {
            for (int j = 0; j < TAILLE_GRILLE; j++) {
                PlayerCell cell = new PlayerCell(i, j, this);  // Create PlayerCell not Cell
                cellules[i][j] = cell;  // No type mismatch should occur here
                add(cell);  // Add to the grid layout
            }
        }
    }
}
