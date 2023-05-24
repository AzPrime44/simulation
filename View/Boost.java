package View;

import java.awt.*;

import javax.swing.*;

public class Boost extends JPanel {
   String[] choix = { "Episures", "Connecteurs", "Cable de Raccordement", "Panneau de Raccordement" };
   JPanel leftPanel;
   MonPanel monPanel;
   RangeInput rg;

   public Boost(JPanel leftPanel) {
      // super(new BorderLayout());
      this.leftPanel = leftPanel;
      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      rg = new RangeInput();
      add(rg, BorderLayout.NORTH);
   }

   public void ajouter() {

      String choise = (String) JOptionPane.showInputDialog(this, "veuille selectionner", "Ajouter",
            JOptionPane.QUESTION_MESSAGE, null, choix, choix[0]);
      if (choise != null) {
         monPanel = new MonPanel(choise);
         // monPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
         this.add(monPanel);
         this.add(Box.createVerticalStrut(10));
      }
      leftPanel.revalidate();
      leftPanel.repaint();
   }

   public float getAtenutionFibre() {
      return rg.getAtenutionFibre();
   }

}