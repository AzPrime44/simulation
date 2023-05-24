package Metier;

import javax.swing.*;

import Calc.Calcule;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdapteurOfRight extends MouseAdapter {
   private JPanel rightPanel;
   private LabelMouseAdapter labelMouseAdapter;
   int laPuissance = 0;
   Boolean isEmetteur;
   Calcule calcule;

   public AdapteurOfRight(LabelMouseAdapter labelMouseAdapter, Boolean isEmetteur, Calcule calcule) {
      this.rightPanel = labelMouseAdapter.rightPanel;
      this.labelMouseAdapter = labelMouseAdapter;
      this.isEmetteur = isEmetteur;
      this.calcule = calcule;

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
            // rightPanel.revalidate();
            // rightPanel.repaint();
         });

         if (this.isEmetteur) {
            menu.add(itemManager("Puissance Entree", e, "Pin"));

            // calcule.puissanceEntree = laPuissance;
         } else {
            menu.add(itemManager("La Sensibilité", e, "S"));
            // calcule.setPuissanceSortie(laPuissance);
            // calcule.puissanceSortie = laPuissance;
         }
         menu.add(deleteItem);
         menu.show(((JLabel) e.getSource()), e.getX(), e.getY());
      }

      ((JLabel) e.getSource()).repaint();
      ((JLabel) e.getSource()).revalidate();
      calcule.revalidate();
      rightPanel.repaint();
   }

   JMenuItem itemManager(String text, MouseEvent e, String var) {
      JMenuItem puissance = new JMenuItem(text);
      puissance.addActionListener(evt -> {
         String userInput = JOptionPane.showInputDialog("Entrez la puissance :");
         try {
            laPuissance = Integer.parseInt(userInput);
            ((JLabel) e.getSource()).setText(var + " =" + Integer.toString(laPuissance) + " dBm");
            if (var.equals("Pin")) {
               calcule.setPuissanceEntree(laPuissance);
            } else {
               calcule.setSensibilite(laPuissance);
            }
            calcule.revalidate();
            System.out.println("La puissance saisie est : " + laPuissance);
            calcule.stimulons();
         } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer un entier valide !");
         }
      });
      return puissance;
   }
}