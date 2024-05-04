import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel {
    public ButtonPanel() {
        setLayout(new GridLayout(0, 1, 5, 5)); // Permet l'ajout dynamique de boutons
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public void addButton(String buttonText, ActionListener listener) {
        JButton button = new JButton(buttonText);
        button.addActionListener(listener);
        add(button);
    }
}
