import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Resolution extends Grille {
    private JButton solveButton = new JButton("Résoudre");
    private JButton verifyButton = new JButton("Vérifier");

    public Resolution() {
        super();
        setTitle("Résolveur de Sudoku");
        addAdditionalButtons();
    }

    private void addAdditionalButtons() {
        getPanelButtons().removeAll();
        getPanelButtons().add(getOpenButton());
        getPanelButtons().add(solveButton);
        getPanelButtons().add(verifyButton);
        getPanelButtons().add(getQuitButton());
        getPanelButtons().setLayout(new GridLayout(4, 1, 5, 5));
    }

    protected void loadGridFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (DataInputStream dis = new DataInputStream(new FileInputStream(selectedFile))) {
                for (int i = 0; i < TAILLE_GRILLE; i++) {
                    int number = dis.readInt();
                    String numberString = String.format("%09d", number);
                    for (int j = 0; j < TAILLE_GRILLE; j++) {
                        char ch = numberString.charAt(j);
                        getCellules()[i][j].setText(ch != '0' ? String.valueOf(ch) : "");
                        // Remplacez cet appel par un appel approprié pour définir la propriété editable de la cellule
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la lecture du fichier.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Resolution());
    }
}
