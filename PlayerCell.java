import javax.swing.JTextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class PlayerCell extends JTextField {
    private int row, col;
    private GridPanel gridPanel;
    private boolean isStartingCell = false;

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
                char input = e.getKeyChar();
                String currentText = getText();
                if (!Character.isDigit(input) || input == '0') {
                    e.consume();
                } else if (isEditable() && !currentText.contains(String.valueOf(input)) && currentText.length() < 4) {
                    if (gridPanel.isValidInput(row, col, input)) {
                        updateTextDisplay(currentText + input);
                        e.consume();
                    }
                }
                e.consume();
            }
        });
    }

    private void updateTextDisplay(String newText) {
        setText(newText);
        int len = newText.length();
        switch (len) {
            case 1:
            case 2:
                setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
                break;
            case 3:
                setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
                break;
            case 4:
                setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12)); // Smaller font size for four digits
                break;
            default:
                setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20)); // Default case
        }
    }

    public void setStartingCell(boolean isStarting) {
        isStartingCell = isStarting;
        setEditable(!isStarting);
        setBackground(isStarting ? new Color(230, 230, 230) : Color.WHITE);
    }
}
