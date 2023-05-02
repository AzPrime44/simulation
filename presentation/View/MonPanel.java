package View;

import java.awt.*;
import javax.swing.*;

public class MonPanel extends JPanel {

   public MonPanel(String name) {
      super(new BorderLayout());
      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      // this.setMaximumSize(new Dimension(500, 100));
      handlecable(name);
   }

   public void handlecable(String name) {
      JTextField textField;
      JLabel labelChoisi;
      textField = new JTextField(1);
      textField.setMaximumSize(new Dimension(500, 25));
      // textField.setHorizontalAlignment(JTextField.CENTER);
      labelChoisi = new JLabel(name);
      labelChoisi.setForeground(Color.BLUE);
      labelChoisi.setAlignmentX(LEFT_ALIGNMENT);
      JPanel panneau = new JPanel();
      panneau.setLayout(new BoxLayout(panneau, BoxLayout.Y_AXIS));
      add(labelChoisi, BorderLayout.NORTH);
      if (name.contains("cable")) {
         JLabel ettiquette = new JLabel("|> valeur en Km");
         JLabel attenuation = new JLabel("|> Atténuation de la cable :");
         JTextField champPourAttenuation = new JTextField(1);
         champPourAttenuation.setMaximumSize(new Dimension(500, 25));
         panneau.add(ettiquette);
         panneau.add(textField);
         panneau.add(attenuation);
         panneau.add(champPourAttenuation);
         this.add(panneau, BorderLayout.CENTER);

      } else {
         JLabel ettiquette = new JLabel("|> valeur en dB");
         panneau.add(ettiquette);
         panneau.add(textField);
         this.add(panneau, BorderLayout.CENTER);
         this.add(Box.createVerticalBox());
      }
   }
}
