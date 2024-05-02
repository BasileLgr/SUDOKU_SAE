import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import javax.swing.JTextField;


public class FileHandler {
    public static void loadGridFromFile(GridPanel gridPanel) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Grille files (*.gri)", "gri"));
        int result = fileChooser.showOpenDialog(null); // Use null for parent
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (DataInputStream dis = new DataInputStream(new FileInputStream(selectedFile))) {
                for (int i = 0; i < GridPanel.TAILLE_GRILLE; i++) {
                    int number = dis.readInt();
                    String numberString = String.format("%09d", number);
                    for (int j = 0; j < GridPanel.TAILLE_GRILLE; j++) {
                        char ch = numberString.charAt(j);
                        JTextField cell = gridPanel.getCellules()[i][j];
                        if (ch != '0') {
                            cell.setText(String.valueOf(ch));
                        } else {
                            cell.setText("");
                        }
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erreur lors de la lecture du fichier.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void exportGridToFile(GridPanel gridPanel) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setDialogTitle("Enregistrer la grille");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Grille files (*.gri)", "gri"));
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileToSave))) {
                for (int i = 0; i < GridPanel.TAILLE_GRILLE; i++) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < GridPanel.TAILLE_GRILLE; j++) {
                        String value = gridPanel.getCellules()[i][j].getText();
                        sb.append(value.isEmpty() ? "0" : value);
                    }
                    dos.writeInt(Integer.parseInt(sb.toString()));
                }
                JOptionPane.showMessageDialog(null, "Grille exportée avec succès.", "Export réussi", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'enregistrement du fichier.", "Erreur d'exportation", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
