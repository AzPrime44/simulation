package Metier;

import javax.swing.*;

import Calc.Calcule;
import Calc.Resultat;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;

public class LabelMouseAdapter extends MouseAdapter {
   private int x;
   private int y;
   JPanel rightPanel;
   private JPanel leftPanel;
   JFrame frame;
   JLabel emetteur = null;
   JLabel recepteur = null;
   JButton drawButton;
   boolean dessin = false;
   boolean afterConnexion = false;
   Icon iconEmeteur = new ImageIcon("./src/emeteur2.png");
   Icon iconRecepteur = new ImageIcon("./src/recepteur.png");
   Calcule calcule;
   Resultat resultat;

   public LabelMouseAdapter(JPanel rightPanel, JFrame frame, JPanel leftPanel, JButton drawButton) {
      this.rightPanel = rightPanel;// Création du panneau droit
      this.leftPanel = leftPanel;// Création du panneau droit
      this.frame = frame;
      this.drawButton = drawButton;
      // this.boost = new Boost(leftPanel);
      this.calcule = new Calcule(leftPanel, frame, rightPanel);
      this.resultat = new Resultat(calcule, frame, rightPanel);
   }

   public JLabel det(String nom, Icon icon, MouseEvent e, AdapteurOfRight adapterOfRight) {
      ;
      JLabel label = new JLabel(nom);
      label.setIcon(icon);
      label.setBounds(e.getXOnScreen() - frame.getLocationOnScreen().x - x - leftPanel.getWidth(),
            e.getYOnScreen() - frame.getLocationOnScreen().y - y - frame.getInsets().top, 130, 60);
      label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      label.addMouseListener(adapterOfRight);
      label.addMouseMotionListener(adapterOfRight);
      rightPanel.add(label);
      rightPanel.repaint();
      rightPanel.revalidate();
      return label;
   }

   public void ligne() {
      if (emetteur != null && recepteur != null && dessin) {

         Graphics g = rightPanel.getGraphics();
         g.setColor(Color.BLACK);
         g.drawLine(emetteur.getX() + emetteur.getWidth(),
               emetteur.getY() + emetteur.getHeight() / 2,
               recepteur.getX(), recepteur.getY() + recepteur.getHeight() / 2);

      }
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
         AdapteurOfRight adapterOfRight = new AdapteurOfRight(this, true, calcule);
         emetteur = det("Pin =" + Integer.toString(calcule.puissanceEntree) + " dBm", iconEmeteur, e, adapterOfRight);

      } else {
         AdapteurOfRight adapterOfRight = new AdapteurOfRight(this, false, calcule);
         recepteur = det("S =" + Integer.toString(calcule.sensibilite) + " dBm", iconRecepteur, e, adapterOfRight);

      }
      if (emetteur != null && recepteur != null) {
         drawButton.setEnabled(true);
         afterConnexion = true;
         drawButton.addActionListener(evt -> {
            dessin = true;
            ligne();
            if (afterConnexion) {
               leftPanel.add(calcule);
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

   public void checkConnexion(MouseEvent e) {

      if (((JLabel) e.getSource()) == emetteur || ((JLabel) e.getSource()) == recepteur) {
         if (((JLabel) e.getSource()) == emetteur)
            emetteur = null;
         if (((JLabel) e.getSource()) == recepteur)
            recepteur = null;
         drawButton.setEnabled(false);
         leftPanel.remove(calcule);
         rightPanel.revalidate();
         leftPanel.revalidate();
         dessin = false;
      }
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

}