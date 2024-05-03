import javax.swing.JTextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class PlayerCell extends Cell {  // Ensure that PlayerCell extends Cell

    public PlayerCell(int row, int col, GridPanel gridPanel) {
        super(row, col, gridPanel);  // Call the superclass constructor

        // Adjust the behavior for key inputs for Player mode
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!isEditable()) {
                    e.consume();
                } else {
                    char input = e.getKeyChar();
                    if (!Character.isDigit(input) || input == '0' || getText().length() >= 1 && !Character.isISOControl(input)) {
                        e.consume();
                    } else if (!gridPanel.isValidInput(row, col, input)) {
                        e.consume();
                    }
                }
            }
        });
    }

    // Optionally, override other methods if necessary
}
