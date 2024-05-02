import javax.swing.JPanel;
import java.awt.GridLayout;

public class PlayerGrid extends GridPanel {
    public PlayerGrid() {
        super();
    }

    @Override
    protected void initGrille() {
        for (int i = 0; i < TAILLE_GRILLE; i++) {
            for (int j = 0; j < TAILLE_GRILLE; j++) {
                PlayerCell cell = new PlayerCell(i, j, this);
                add(cell);
                cellules[i][j] = cell;
            }
        }
    }
}
