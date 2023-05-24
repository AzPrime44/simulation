package View;

import java.awt.*;
import javax.swing.*;

public class MonPanel extends JPanel {
   public Boolean contientCable = false;

   public MonPanel(String name) {
      // super(new BorderLayout());
      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      // this.setMaximumSize(new Dimension(500, 100));
      handlecable(name);
   }

   public void handlecable(String name) {
      JTextField textField;
      JLabel labelChoisi;
      textField = new JTextField(1);
      textField.setHorizontalAlignment(JTextField.CENTER);
      textField.setMaximumSize(new Dimension(500, 25));
      labelChoisi = new JLabel(name);
      labelChoisi.setForeground(Color.BLUE);
      labelChoisi.setAlignmentX(LEFT_ALIGNMENT);
      add(labelChoisi, BorderLayout.NORTH);
      if (name.contains("Cable")) {
         contientCable = true;
         JLabel ettiquette = new JLabel("👉 Longueur en Km");
         JLabel attenuation = new JLabel("👉 Atténuation de la cable en dB:");
         JTextField champPourAttenuation = new JTextField(1);
         champPourAttenuation.setHorizontalAlignment(JTextField.CENTER);
         champPourAttenuation.setMaximumSize(new Dimension(500, 25));
         this.add(ettiquette);
         this.add(textField);
         this.add(attenuation);
         this.add(champPourAttenuation);
         this.add(Box.createVerticalStrut(5));

      } else {
         JLabel ettiquette = new JLabel("👉 Valeur de Atténuation en dB");
         this.add(ettiquette);
         this.add(textField);
         this.add(Box.createVerticalStrut(5));

      }
   }
}
