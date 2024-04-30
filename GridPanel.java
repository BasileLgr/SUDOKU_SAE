import javax.swing.JPanel;
import java.awt.GridLayout;

public class GridPanel extends JPanel {
    protected static final int TAILLE_GRILLE = 9;
    private Cell[][] cellules = new Cell[TAILLE_GRILLE][TAILLE_GRILLE];

    public GridPanel() {
        setLayout(new GridLayout(TAILLE_GRILLE, TAILLE_GRILLE));
        initGrille();
    }

    private void initGrille() {
        for (int i = 0; i < TAILLE_GRILLE; i++) {
            for (int j = 0; j < TAILLE_GRILLE; j++) {
                Cell cell = new Cell(i, j, this); // Passe 'this' en tant que référence à GridPanel
                add(cell);
                cellules[i][j] = cell;
            }
        }
    }

    public Cell[][] getCellules() {
        return cellules;
    }

    public boolean isValidInput(int row, int col, char input) {
        for (int i = 0; i < TAILLE_GRILLE; i++) {
            if (getCellules()[row][i].getText().equals(String.valueOf(input)) ||
                    getCellules()[i][col].getText().equals(String.valueOf(input))) {
                return false;
            }
        }
        int gridRowStart = (row / 3) * 3;
        int gridColStart = (col / 3) * 3;
        for (int i = gridRowStart; i < gridRowStart + 3; i++) {
            for (int j = gridColStart; j < gridColStart + 3; j++) {
                if (getCellules()[i][j].getText().equals(String.valueOf(input))) {
                    return false;
                }
            }
        }
        return true;
    }
}
