import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
            try (Scanner scanner = new Scanner(new FileInputStream(selectedFile))) {
                int row = 0;
                while (scanner.hasNextLine() && row < TAILLE_GRILLE) {
                    String line = scanner.nextLine();
                    parseAndFillRow(line, row);
                    row++;
                }
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(this, "Fichier non trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void parseAndFillRow(String line, int row) {
        for (int col = 0; col < TAILLE_GRILLE; col++) {
            char ch = line.length() > col ? line.charAt(col) : '0';
            if (ch != '0') {
                cellules[row][col].setText(String.valueOf(ch));
            } else {
                cellules[row][col].setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Grille());
    }
}
