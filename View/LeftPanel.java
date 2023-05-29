package View;

import javax.swing.*;

import Metier.*;

import java.awt.*;

public class LeftPanel extends JPanel {
   JButton drawButton;
   JLabel emeteurLabel;
   JLabel recepteurLabel;
   JPanel rightPanel;

   public LeftPanel(JPanel rightPanel, JFrame frame) {
      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

      this.rightPanel = rightPanel;
      drawButton = new JButton("Ajouter fibre");
      drawButton.setEnabled(false);
      drawButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      LabelMouseAdapter adapter = new LabelMouseAdapter(this.rightPanel, frame, this, this.drawButton);

      // Ajout d'une bordure pour séparer les deux parties de l'interface

      // Création des labels
      emeteurLabel = new JLabel("Emetteur");
      emeteurLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      recepteurLabel = new JLabel("Recepteur");
      recepteurLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

      // Ajout des labels au panneau gauche
      this.add(Box.createVerticalStrut(20));
      this.add(emeteurLabel);
      this.add(Box.createVerticalStrut(10));
      this.add(recepteurLabel);
      this.add(Box.createVerticalStrut(10));
      this.add(drawButton);
      this.add(Box.createVerticalStrut(10));
      emeteurLabel.addMouseListener(adapter);
      emeteurLabel.addMouseMotionListener(adapter);
      recepteurLabel.addMouseListener(adapter);
      recepteurLabel.addMouseMotionListener(adapter);
   }

}
