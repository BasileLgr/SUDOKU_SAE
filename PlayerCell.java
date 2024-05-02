import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayerCell extends Cell {
    public PlayerCell(int row, int col, GridPanel gridPanel) {
        super(row, col, gridPanel);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                handleKeyTyped(e);
            }
        });
    }

    private void handleKeyTyped(KeyEvent e) {
        char input = e.getKeyChar();
        if (Character.isDigit(input) && input != '0') {
            if (!gridPanel.isValidInput(row, col, input) || getText().length() >= 1) {
                e.consume();  // Refuse the input if it's invalid
            }
        } else if (!Character.isISOControl(input)) {  // Ignore control characters (e.g., backspace)
            e.consume();
        }
    }
}
