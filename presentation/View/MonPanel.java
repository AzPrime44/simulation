package View;

import java.awt.*;
import javax.swing.*;
import java.awt.Dimension;

public class MonPanel extends JPanel {

   private JTextField textField;
   private JLabel valueLabel;

   public MonPanel(String name) {
      super(new BorderLayout());
      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      this.setMaximumSize(new Dimension(300, 100));
      textField = new JTextField(1);
      textField.setMaximumSize(new Dimension(70, 25));
      textField.setHorizontalAlignment(JTextField.CENTER);
      valueLabel = new JLabel(name);
      valueLabel.setPreferredSize(new Dimension(50, 20));
      add(valueLabel, BorderLayout.NORTH);
      add(textField, BorderLayout.EAST);

   }

}
