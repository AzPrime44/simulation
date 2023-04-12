import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;

public class LabelMouseAdapter extends MouseAdapter {
   private int x;
   private int y;
   private JPanel rightPanel;
   private JPanel leftPanel;
   JFrame frame;
   JLabel emetteur = null;
   JLabel recepteur = null;
   static int compo = 0;
   JButton drawButton;
   boolean dessin = false;
   boolean afterConnexion = false;
   Icon iconEmeteur = new ImageIcon("../src/emeteur2.png");
   Icon iconRecepteur = new ImageIcon("../src/recepteur.png");
   RangeInput rg = new RangeInput();

   public LabelMouseAdapter(JPanel rightPanel, JFrame frame, JPanel leftPanel, JButton drawButton) {
      this.rightPanel = rightPanel;// Création du panneau droit
      this.leftPanel = leftPanel;// Création du panneau droit
      this.frame = frame;
      this.drawButton = drawButton;
   }

   public JLabel det(String nom, Icon icon, MouseEvent e) {
      JLabel label = new JLabel(nom);
      label.setIcon(icon);
      label.setBounds(e.getXOnScreen() - frame.getLocationOnScreen().x - x - leftPanel.getWidth(),
            e.getYOnScreen() - frame.getLocationOnScreen().y - y - frame.getInsets().top, 130, 60);
      label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      AdapteurOfRight adapterOfRight = new AdapteurOfRight();
      label.addMouseListener(adapterOfRight);
      label.addMouseMotionListener(adapterOfRight);
      rightPanel.add(label);
      rightPanel.repaint();
      rightPanel.revalidate();
      return label;
   }

   @Override
   public void mousePressed(MouseEvent e) {
      // Enregistrement de la position initiale du label
      x = e.getX();
      y = e.getY();
   }

   @Override
   public void mouseReleased(MouseEvent e) {
      // Création d'un nouveau label dans le panneau droit
      if (((JLabel) e.getSource()).getText().equals("Emetteur")) {
         emetteur = det("Emetteur", iconEmeteur, e);

      } else {
         recepteur = det("Recepteur", iconRecepteur, e);

      }
      if (emetteur != null && recepteur != null) {
         drawButton.setEnabled(true);
         afterConnexion = true;
         drawButton.addActionListener(evt -> {
            // JOptionPane.showMessageDialog(rightPanel,
            // "Il n'y a pas suffisamment de composants pour dessiner une ligne !");

            dessin = true;
            ligne();
            ligne();
            if (afterConnexion) {
               leftPanel.add(rg);
               leftPanel.add(Box.createVerticalStrut(10));
               rightPanel.revalidate();
               rightPanel.repaint();
               leftPanel.revalidate();
               afterConnexion = false;
            }

         });
      } else {
         drawButton.setEnabled(false);

      }

   }

   public void ligne() {

      Graphics g = rightPanel.getGraphics();
      g.setColor(Color.BLACK);
      g.drawLine(emetteur.getX() + emetteur.getWidth(),
            emetteur.getY() + emetteur.getHeight() / 2,
            recepteur.getX(), recepteur.getY() + recepteur.getHeight() / 2);

   }

   public boolean checkConnexion(MouseEvent e, JLabel lab) {

      if (((JLabel) e.getSource()) == lab) {
         lab = null;
         drawButton.setEnabled(false);
         leftPanel.remove(rg);
         rightPanel.revalidate();
         leftPanel.revalidate();
         dessin = false;
         return true;
      }
      return false;
   }
   // frame.addActionListener("resize", )

   @Override
   public void mouseDragged(MouseEvent e) {
      // Mise à jour de la position du label pendant le glissement
      e.getComponent().setLocation(e.getXOnScreen() - frame.getLocationOnScreen().x - x,
            e.getYOnScreen() - frame.getLocationOnScreen().y - y);
      rightPanel.repaint();
      rightPanel.revalidate();
   }

   class AdapteurOfRight extends MouseAdapter {

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
         if (emetteur != null && recepteur != null && dessin) {

            ligne();
         }
      }

      @Override
      public void mousePressed(MouseEvent e) {
         if (SwingUtilities.isRightMouseButton(e)) {
            JPopupMenu menu = new JPopupMenu();
            JMenuItem deleteItem = new JMenuItem("Supprimer");
            deleteItem.addActionListener(evt -> {
               checkConnexion(e, emetteur);
               checkConnexion(e, recepteur);

               rightPanel.remove(((JLabel) e.getSource()));
               rightPanel.revalidate();
               rightPanel.repaint();
            });
            menu.add(deleteItem);
            menu.show(((JLabel) e.getSource()), e.getX(), e.getY());
         }
      }
   }
}