import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

public class Grille extends JFrame {
    protected static final int TAILLE_GRILLE = 9;
    private static final int TAILLE_CELLULE = 50;
    private static final int EPAISSEUR_BORDURE_INTERNE = 1;
    private static final int EPAISSEUR_BORDURE_EXTERNE = 4;

    protected JTextField[][] cellules = new JTextField[TAILLE_GRILLE][TAILLE_GRILLE];
    protected JPanel panelButtons = new JPanel();
    protected JButton openButton = new JButton("Ouvrir une nouvelle grille");
    protected JButton exportButton = new JButton("Exporter la grille");
    protected JButton quitButton = new JButton("Quitter");

    public Grille() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Editeur de Sudoku");
        setLayout(new BorderLayout());
        initGrille();
        initButtons();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initGrille() {
        JPanel gridPanel = new JPanel(new GridLayout(TAILLE_GRILLE, TAILLE_GRILLE));
        for (int i = 0; i < TAILLE_GRILLE; i++) {
            for (int j = 0; j < TAILLE_GRILLE; j++) {
                final int row = i;
                final int col = j;
                JTextField currentCell = new JTextField();
                currentCell.setEditable(true);
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
                currentCell.addKeyListener(new KeyAdapter() {
                    public void keyTyped(KeyEvent e) {
                        char input = e.getKeyChar();
                        if (!Character.isDigit(input) || input == '0' || currentCell.getText().length() > 0 && !Character.isISOControl(input)) {
                            e.consume();
                        } else if (Character.isDigit(input) && !isValidInput(row, col, input)) {
                            e.consume();
                        }
                    }
                });
                gridPanel.add(currentCell);
                cellules[i][j] = currentCell;
            }
        }
        add(gridPanel, BorderLayout.CENTER);
    }

    protected boolean isValidInput(int row, int col, char input) {
        for (int i = 0; i < TAILLE_GRILLE; i++) {
            if (cellules[row][i].getText().equals(String.valueOf(input)) ||
                    cellules[i][col].getText().equals(String.valueOf(input))) {
                return false;
            }
        }
        int gridRowStart = (row / 3) * 3;
        int gridColStart = (col / 3) * 3;
        for (int i = gridRowStart; i < gridRowStart + 3; i++) {
            for (int j = gridColStart; j < gridColStart + 3; j++) {
                if (cellules[i][j].getText().equals(String.valueOf(input))) {
                    return false;
                }
            }
        }
        return true;
    }

    protected void initButtons() {
        panelButtons.setLayout(new GridLayout(3, 1, 5, 5));
        panelButtons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        openButton.addActionListener(e -> loadGridFromFile());
        exportButton.addActionListener(e -> exportGridToFile());
        quitButton.addActionListener(e -> System.exit(0));

        panelButtons.add(openButton);
        panelButtons.add(exportButton);
        panelButtons.add(quitButton);
        add(panelButtons, BorderLayout.EAST);
    }

    protected JPanel getPanelButtons() {
        return panelButtons;
    }

    protected JButton getOpenButton() {
        return openButton;
    }

    protected JButton getQuitButton() {
        return quitButton;
    }

    protected JTextField[][] getCellules() {
        return cellules;
    }

    private void loadGridFromFile() {
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

    private void exportGridToFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setDialogTitle("Enregistrer la grille");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Grille files (*.gri)", "gri"));
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileToSave))) {
                for (int i = 0; i < TAILLE_GRILLE; i++) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < TAILLE_GRILLE; j++) {
                        String value = cellules[i][j].getText();
                        sb.append(value.isEmpty() ? "0" : value);
                    }
                    dos.writeInt(Integer.parseInt(sb.toString()));
                }
                JOptionPane.showMessageDialog(this, "Grille exportée avec succès.", "Export réussi", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement du fichier.", "Erreur d'exportation", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Grille());
    }
}
