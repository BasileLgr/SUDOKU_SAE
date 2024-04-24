import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.io.*;

public class Grille extends JFrame {

    private static final int TAILLE_GRILLE = 9;
    private static final int TAILLE_CELLULE = 50;
    private static final int EPAISSEUR_BORDURE_INTERNE = 1;
    private static final int EPAISSEUR_BORDURE_EXTERNE = 4;

    private JTextField[][] cellules = new JTextField[TAILLE_GRILLE][TAILLE_GRILLE];

    public Grille() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Sudoku");
        setLayout(new GridLayout(TAILLE_GRILLE, TAILLE_GRILLE));
        initGrille();
        loadGridFromFile();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initGrille() {
        for (int i = 0; i < TAILLE_GRILLE; i++) {
            for (int j = 0; j < TAILLE_GRILLE; j++) {
                JTextField currentCell = new JTextField();
                currentCell.setEditable(false);
                currentCell.setHorizontalAlignment(JTextField.CENTER);
                currentCell.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
                currentCell.setPreferredSize(new Dimension(TAILLE_CELLULE, TAILLE_CELLULE));
                Border border = new MatteBorder(
                        i % 3 == 0 ? EPAISSEUR_BORDURE_EXTERNE : EPAISSEUR_BORDURE_INTERNE,
                        j % 3 == 0 ? EPAISSEUR_BORDURE_EXTERNE : EPAISSEUR_BORDURE_INTERNE,
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

    private void loadGridFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (DataInputStream dis = new DataInputStream(new FileInputStream(selectedFile))) {
                for (int i = 0; i < TAILLE_GRILLE; i++) {
                    int number = dis.readInt(); // Lecture d'un entier
                    String numberString = String.format("%09d", number); // Conversion en chaîne de 9 chiffres, complétée par des zéros
                    for (int j = 0; j < TAILLE_GRILLE; j++) {
                        char ch = numberString.charAt(j);
                        if (ch != '0') {
                            cellules[i][j].setText(String.valueOf(ch));
                        } else {
                            cellules[i][j].setText("");
                        }
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la lecture du fichier.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Grille());
    }
}
