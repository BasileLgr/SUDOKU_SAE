import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

/**
 * La classe <code>ButtonPanel</code> étend <code>JPanel</code> pour créer un panneau
 * personnalisé qui gère l'affichage et la fonctionnalité des boutons.
 */
public class ButtonPanel extends JPanel {

    /**
     * Constructeur pour initialiser le panneau avec une configuration de grille.
     * Il définit également une marge autour du panneau.
     */
    public ButtonPanel() {
        setLayout(new GridLayout(0, 1, 5, 5)); // Permet l'ajout dynamique de boutons
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    /**
     * Ajoute un bouton avec le texte spécifié au panneau.
     * @param buttonText le texte à afficher sur le bouton
     * @param listener l'objet ActionListener qui gère les actions du bouton
     */
    public void addButton(String buttonText, ActionListener listener) {
        JButton button = new JButton(buttonText);
        button.addActionListener(listener);
        add(button);
    }
}
