import javax.swing.*;
import java.awt.*;

public class Grille extends JFrame {
    private static final int GRID_SIZE = 9;    // Taille de la grille 9x9
    private static final int SUBGRID_SIZE = 3; // Taille des sous-grilles 3x3
    private static final int CELL_SIZE = 60;   // Taille des cellules

    private JTextField[][] cells = new JTextField[GRID_SIZE][GRID_SIZE];

    public Grille() {
        Container pane = getContentPane();
        pane.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

        // Création de la grille de Sudoku avec des bordures pour séparer les sous-grilles
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cells[row][col] = new JTextField();
                cells[row][col].setHorizontalAlignment(JTextField.CENTER);
                cells[row][col].setFont(new Font("Arial", Font.BOLD, 20));
                cells[row][col].setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));

                // Ajout de bordures aux cellules pour distinguer les sous-grilles
                if (row % SUBGRID_SIZE == 0) {
                    cells[row][col].setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));
                }
                if (col % SUBGRID_SIZE == 0) {
                    cells[row][col].setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Color.BLACK));
                }

                pane.add(cells[row][col]);
            }
        }

        pack();
        setTitle("Grille de Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Exécuter le programme
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Grille();
            }
        });
    }
}
