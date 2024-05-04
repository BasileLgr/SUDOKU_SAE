import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * La classe <code>Cell</code> étend <code>JTextField</code> pour créer un champ de texte personnalisé
 * avec des fonctionnalités spécifiques pour une grille de saisie, et implémente l'interface <code>KeyListener</code>
 * pour gérer les événements de touche.
 */
public class Cell extends JTextField implements KeyListener {
    protected int row, col; // Indices de la ligne et de la colonne de la cellule dans la grille
    protected GridPanel gridPanel; // Référence au panneau de grille contenant la cellule

    /**
     * Constructeur pour la cellule.
     * @param row la ligne de la cellule dans la grille
     * @param col la colonne de la cellule dans la grille
     * @param gridPanel le panneau de la grille contenant cette cellule
     */
    public Cell(int row, int col, GridPanel gridPanel) {
        this.row = row;
        this.col = col;
        this.gridPanel = gridPanel;

        setEditable(true);
        setHorizontalAlignment(JTextField.CENTER);
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        setPreferredSize(new Dimension(50, 50));

        int innerBorder = 1;
        int outerBorder = 4;
        Border border = new MatteBorder(
                row % 3 == 0 ? outerBorder : innerBorder,
                col % 3 == 0 ? outerBorder : innerBorder,
                row == 8 ? outerBorder : innerBorder,
                col == 8 ? outerBorder : innerBorder,
                Color.BLACK
        );
        setBorder(border);

        addKeyListener(this);  // Ajoute cette instance comme KeyListener
    }

    /**
     * Méthode pour gérer les événements de type caractère.
     * Consume l'événement si le caractère n'est pas un chiffre valide ou si la cellule ne peut pas accepter le caractère.
     * @param e l'événement de touche
     */
    @Override
    public void keyTyped(KeyEvent e) {
        char input = e.getKeyChar();
        if (!Character.isDigit(input) || input == '0' || getText().length() >= 1 && !Character.isISOControl(input)) {
            e.consume();
        } else if (!gridPanel.isValidInput(row, col, input)) {
            e.consume();
        }
    }

    /**
     * Méthode pour gérer les événements de pression de touche.
     * Actuellement non utilisée.
     * @param e l'événement de touche
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // Non utilisé, mais requis par l'interface
    }

    /**
     * Méthode pour gérer les événements de relâchement de touche.
     * Actuellement non utilisée.
     * @param e l'événement de touche
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // Non utilisé, mais requis par l'interface
    }
}
