import javax.swing.JTextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

/**
 * La classe <code>PlayerCell</code> étend <code>JTextField</code> pour représenter une cellule de jeu dans une grille de Sudoku.
 */
public class PlayerCell extends JTextField {
    private int row, col;
    private GridPanel gridPanel;
    private boolean isStartingCell = false;

    /**
     * Constructeur pour créer une nouvelle instance de <code>PlayerCell</code>.
     * @param row la ligne de la cellule
     * @param col la colonne de la cellule
     * @param gridPanel le panneau de la grille contenant cette cellule
     */
    public PlayerCell(int row, int col, GridPanel gridPanel) {
        super();
        this.row = row;
        this.col = col;
        this.gridPanel = gridPanel;

        setEditable(true);
        setHorizontalAlignment(JTextField.CENTER);
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        setPreferredSize(new Dimension(50, 50));

        Border border = new MatteBorder(
                row % 3 == 0 ? 4 : 1,
                col % 3 == 0 ? 4 : 1,
                row == 8 ? 4 : 1,
                col == 8 ? 4 : 1,
                Color.BLACK
        );
        setBorder(border);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                handleKeyTyped(e);
            }
        });
    }

    private void handleKeyTyped(KeyEvent e) {
        char input = e.getKeyChar();
        String currentText = getText().replace(String.valueOf(input), ""); // Enlève l'entrée courante pour la validation
        if (!Character.isDigit(input) || input == '0') {
            e.consume(); // Ignore les entrées non numériques et le zéro
        } else {
            if (gridPanel instanceof PlayerGrid) {
                if (currentText.length() < 4 && gridPanel.isValidInput(row, col, input)) {
                    updateTextDisplay(currentText + input);
                    e.consume();
                } else {
                    e.consume();
                }
            } else {
                if (currentText.isEmpty() && gridPanel.isValidInput(row, col, input)) {
                    setText(String.valueOf(input));
                    e.consume();
                } else {
                    e.consume();
                }
            }
        }
    }

    private void updateTextDisplay(String newText) {
        if (newText.length() <= 4) { // Pas plus de 4 chiffres
            setText(newText);
            adjustFont(newText.length());
        }
    }

    private void adjustFont(int textLength) {
        switch (textLength) {
            case 1:
            case 2:
                setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
                break;
            case 3:
                setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
                break;
            case 4:
                setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12)); // Changement taille police pour 4 chiffre
                break;
            default:
                setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20)); //
        }
    }

    /**
     * Définit si cette cellule est une cellule de départ.
     * @param isStarting true si la cellule est une cellule de départ, false sinon
     */
    public void setStartingCell(boolean isStarting) {
        isStartingCell = isStarting;
        setEditable(!isStarting);
        setBackground(isStarting ? new Color(230, 230, 230) : Color.WHITE);
    }
}
