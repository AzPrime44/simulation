package View;

import java.awt.*;

import javax.swing.*;

public class Boost extends JPanel {
   private JButton add;
   String[] choix = { "épissures", "connecteurs", "cable de raccordement", "panneau de raccordement" };
   JPanel leftPanel;

   public Boost(JPanel leftPanel) {
      super(new BorderLayout());
      this.leftPanel = leftPanel;
      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      add(new RangeInput(), BorderLayout.NORTH);
      add = new JButton("Add");
      add(add, BorderLayout.CENTER);

      add.addActionListener(event -> {
         ajouter();
         leftPanel.revalidate();
         leftPanel.repaint();
      });
   }

   public void ajouter() {

      String choise = (String) JOptionPane.showInputDialog(this, "veuille selectionner", "Ajouter",
            JOptionPane.QUESTION_MESSAGE, null, choix, choix[0]);
      if (choise != null) {

         MonPanel monPanel = new MonPanel(choise);
         leftPanel.add(monPanel, BorderLayout.CENTER);

      }

   }

}