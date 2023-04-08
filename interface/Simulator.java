
import javax.swing.*;
import java.awt.*;

public class Simulator extends JFrame {
   private JPanel leftPanel;
   private JPanel rightPanel;
   private JLabel emeteurLabel;
   private JLabel recepteurLabel;
   // private JLabel emeteur;
   // private JLabel recepteur;
   private JButton drawButton;

   public Simulator() {
      // Configuration de la fenêtre principale
      setTitle("simulation");
      setSize(800, 600);
      // setLayout(new GridLayout(1, 2));
      setLayout(new BorderLayout());
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Création des panneaux gauche et droit
      leftPanel = new JPanel();
      leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
      leftPanel.setPreferredSize(new Dimension(getWidth() / 4, getHeight()));
      drawButton = new JButton("Connexion");
      drawButton.setEnabled(false);
      drawButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      rightPanel = new JPanel();
      rightPanel.setLayout(null);

      // Ajout d'une bordure pour séparer les deux parties de l'interface
      leftPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));

      // Création des labels
      emeteurLabel = new JLabel("Emetteur");
      emeteurLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      recepteurLabel = new JLabel("Recepteur");
      recepteurLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      // fibreLabel = new JLabel("Fibre de connexion");

      // Ajout des labels au panneau gauche
      leftPanel.add(Box.createVerticalStrut(20));
      leftPanel.add(emeteurLabel);
      leftPanel.add(Box.createVerticalStrut(10));
      leftPanel.add(recepteurLabel);
      leftPanel.add(Box.createVerticalStrut(10));
      leftPanel.add(drawButton);

      // Ajout des panneaux à la fenêtre principale
      add(leftPanel, BorderLayout.WEST);
      add(rightPanel, BorderLayout.CENTER);

      // Ajout des écouteurs d'événements pour le glisser-déposer
      LabelMouseAdapter adapter = new LabelMouseAdapter(rightPanel, this, leftPanel, drawButton);
      emeteurLabel.addMouseListener(adapter);
      emeteurLabel.addMouseMotionListener(adapter);
      recepteurLabel.addMouseListener(adapter);
      recepteurLabel.addMouseMotionListener(adapter);

   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            new Simulator().setVisible(true);
         }
      });
   }
}
