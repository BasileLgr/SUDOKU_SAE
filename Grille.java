import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.border.BorderFactory;

public class Grille extends JFrame {
    private static final int GRID_SIZE = 9; // Taille de la grille 9x9
    private static final int CELL_SIZE = 60; // Taille des cellules
    private static final int SUBGRID_SIZE = 3; // Taille des sous-grilles 3x3
    private JTextField[][] cells = new JTextField[GRID_SIZE][GRID_SIZE];

    public Grille() {
        Container pane = getContentPane();
        pane.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

        // Définition des bordures pour séparer les sous-grilles
        Border thickerBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        Border thinBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        // Construction de la grille de Sudoku
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                cells[i][j] = new JTextField();
                cells[i][j].setEditable(false);
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                cells[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                // Ajouter des bordures pour différencier les sous-grilles 3x3
                if ((i + 1) % SUBGRID_SIZE == 0 && (i + 1) != GRID_SIZE) {
                    if ((j + 1) % SUBGRID_SIZE == 0 && (j + 1) != GRID_SIZE) {
                        cells[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 2, 2, Color.BLACK));
                    } else {
                        cells[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 2, 1, Color.BLACK));
                    }
                } else if ((j + 1) % SUBGRID_SIZE == 0 && (j + 1) != GRID_SIZE) {
                    cells[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 2, Color.BLACK));
                } else {
                    cells[i][j].setBorder(thinBorder);
                }
                pane.add(cells[i][j]);
            }
        }

        // Ajustement des bordures externes de la grille
        pane.setBorder(thickerBorder);
        pack();
        setTitle("Grille de Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Grille());
    }
}
