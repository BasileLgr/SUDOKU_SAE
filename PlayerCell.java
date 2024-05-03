import javax.swing.JTextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerCell extends Cell {

    public PlayerCell(int row, int col, GridPanel gridPanel) {
        super(row, col, gridPanel);
        // Ne plus ajouter KeyListener ici puisque Cell l'implémente déjà
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char input = e.getKeyChar();
        if (Character.isDigit(input) && input != '0') {
            if (!gridPanel.isValidInput(row, col, input) || getText().length() >= 1) {
                e.consume();  // Refuse l'entrée si elle est invalide
            }
        } else if (!Character.isISOControl(input)) {  // Ignorer les caractères de contrôle (par exemple, backspace)
            e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Méthode non utilisée
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Méthode non utilisée
    }
}
