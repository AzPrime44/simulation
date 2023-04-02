import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InterfaceUser extends JFrame implements ActionListener, MouseListener {

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItemEmitter;
    private JMenuItem menuItemReceiver;
    private JPanel panel;
    private JPanel emitterPanel;
    private JPanel receiverPanel;

    public InterfaceUser() {
        // Créer la fenêtre principale
        super("Exemple de GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Créer le menu
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menuItemEmitter = new JMenuItem("Emetteur");
        menuItemReceiver = new JMenuItem("Recepteur");
        menuItemEmitter.addActionListener(this);
        menuItemReceiver.addActionListener(this);
        menu.add(menuItemEmitter);
        menu.add(menuItemReceiver);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Créer le panneau
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        add(panel);

        // Ajouter un MouseListener pour détecter les clics sur le panneau
        panel.addMouseListener(this);

        // Afficher la fenêtre
        setSize(400, 400);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Créer le panneau approprié lorsque l'utilisateur sélectionne un élément du
        // menu
        if (e.getSource() == menuItemEmitter) {
            emitterPanel = new JPanel();
            emitterPanel.setBackground(Color.RED);
        } else if (e.getSource() == menuItemReceiver) {
            receiverPanel = new JPanel();
            receiverPanel.setBackground(Color.BLUE);
        }
    }

    public void mouseClicked(MouseEvent e) {
        // Ajouter le panneau approprié à l'endroit du clic
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = e.getX() / 100; // Modifier cette valeur pour ajuster la taille des panneaux
        gbc.gridy = e.getY() / 100; // Modifier cette valeur pour ajuster la taille des panneaux
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5); // Modifier cette valeur pour ajuster l'espacement entre les panneaux
        if (emitterPanel != null) {
            panel.add(emitterPanel, gbc);
            emitterPanel = null;
        } else if (receiverPanel != null) {
            panel.add(receiverPanel, gbc);
            receiverPanel = null;
        }
        panel.revalidate();
        panel.repaint();
    }

    // Les autres méthodes de l'interface MouseListener doivent être implémentées,
    // mais elles ne sont pas utilisées dans cet exemple
    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public static void main(String[] args) {
        // Créer l'interface graphique
        InterfaceUser gui = new InterfaceUser();
    }
}
