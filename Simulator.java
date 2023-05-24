
import javax.swing.*;
import View.*;
import java.awt.*;

public class Simulator extends JFrame {
   private LeftPanel leftPanel;
   private JPanel rightPanel;

   public Simulator() {
      // Configuration de la fenêtre principale
      setTitle("simulation");
      setSize(800, 600);
      setLayout(new BorderLayout());
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      rightPanel = new JPanel();
      leftPanel = new LeftPanel(rightPanel, this);
      // Ajout des panneaux à la fenêtre principale
      leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

      leftPanel.setPreferredSize(new Dimension(getWidth() / 3, getHeight()));

      rightPanel.setLayout(null);
      leftPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));

      add(leftPanel, BorderLayout.WEST);
      add(rightPanel, BorderLayout.CENTER);

      // Ajout des écouteurs d'événements pour le glisser-déposer

   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            new Simulator().setVisible(true);
         }
      });
   }
}
