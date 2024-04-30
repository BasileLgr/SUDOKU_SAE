import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Cell extends JTextField implements KeyListener {
    private int row, col; // Positions de la cellule dans la grille
    private GridPanel gridPanel; // Référence au panneau de grille contenant cette cellule

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

    @Override
    public void keyTyped(KeyEvent e) {
        char input = e.getKeyChar();
        if (!Character.isDigit(input) || input == '0' || getText().length() >= 1 && !Character.isISOControl(input)) {
            e.consume();
        } else if (!gridPanel.isValidInput(row, col, input)) {
            e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Non utilisé, mais requis par l'interface
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Non utilisé, mais requis par l'interface
    }
}
