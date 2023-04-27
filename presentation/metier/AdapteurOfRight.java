package metier;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class AdapteurOfRight extends MouseAdapter {
   private JPanel rightPanel;
   private LabelMouseAdapter labelMouseAdapter;

   public AdapteurOfRight(LabelMouseAdapter labelMouseAdapter) {
      this.rightPanel = labelMouseAdapter.rightPanel;
      this.labelMouseAdapter = labelMouseAdapter;

   }

   @Override
   public void mouseDragged(MouseEvent e) {
      JLabel label = (JLabel) e.getComponent();
      label.setLocation(label.getX() + e.getX() - label.getWidth() / 2,
            label.getY() + e.getY() - label.getHeight() / 2);
      rightPanel.repaint();
      rightPanel.revalidate();
   }

   @Override
   public void mouseReleased(MouseEvent e) {

      labelMouseAdapter.ligne();
   }

   @Override
   public void mousePressed(MouseEvent e) {
      if (SwingUtilities.isRightMouseButton(e)) {
         JPopupMenu menu = new JPopupMenu();
         JMenuItem deleteItem = new JMenuItem("Supprimer");
         deleteItem.addActionListener(evt -> {
            labelMouseAdapter.checkConnexion(e);
            rightPanel.remove(((JLabel) e.getSource()));
            rightPanel.revalidate();
            rightPanel.repaint();
         });
         menu.add(deleteItem);
         menu.show(((JLabel) e.getSource()), e.getX(), e.getY());
      }
   }
}