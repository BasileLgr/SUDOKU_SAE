import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Grille extends JFrame {

    private static final int TAILLE_GRILLE = 9;
    private static final int TAILLE_SUBGRID = 3;
    private static final int EPAISSEUR_BORDURE_INTERNE = 1;
    private static final int EPAISSEUR_BORDURE_EXTERNE = 4;
    private static final int TAILLE_CELLULE = 50;

    private JTextField[][] cellules = new JTextField[TAILLE_GRILLE][TAILLE_GRILLE];

    public Grille() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Sudoku");
        setLayout(new GridLayout(TAILLE_GRILLE, TAILLE_GRILLE));
        initGrille();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initGrille() {
        for (int i = 0; i < TAILLE_GRILLE; i++) {
            for (int j = 0; j < TAILLE_GRILLE; j++) {
                final JTextField currentCell = new JTextField();
                currentCell.setEditable(true);
                currentCell.setHorizontalAlignment(JTextField.CENTER);
                currentCell.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
                currentCell.setPreferredSize(new Dimension(TAILLE_CELLULE, TAILLE_CELLULE));

                // Ajout d'un écouteur pour limiter l'entrée à un seul chiffre entre 1 et 9.
                currentCell.addKeyListener(new KeyAdapter() {
                    public void keyTyped(KeyEvent e) {
                        if (currentCell.getText().length() >= 1 || !Character.isDigit(e.getKeyChar()) ||
                                e.getKeyChar() == '0') { // Refuser si plus d'un chiffre ou si le chiffre est 0.
                            e.consume();
                        }
                    }
                });

                Border border = new MatteBorder(
                        i % TAILLE_SUBGRID == 0 && i != 0 ? EPAISSEUR_BORDURE_EXTERNE : EPAISSEUR_BORDURE_INTERNE,
                        j % TAILLE_SUBGRID == 0 && j != 0 ? EPAISSEUR_BORDURE_EXTERNE : EPAISSEUR_BORDURE_INTERNE,
                        i == TAILLE_GRILLE - 1 ? EPAISSEUR_BORDURE_EXTERNE : EPAISSEUR_BORDURE_INTERNE,
                        j == TAILLE_GRILLE - 1 ? EPAISSEUR_BORDURE_EXTERNE : EPAISSEUR_BORDURE_INTERNE,
                        Color.BLACK
                );
                currentCell.setBorder(border);
                cellules[i][j] = currentCell;

                add(currentCell);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Grille());
    }
}
