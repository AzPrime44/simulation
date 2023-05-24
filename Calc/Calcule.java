package Calc;

import javax.swing.*;

import Metier.LabelMouseAdapter;

import java.awt.*;
import View.*;

public class Calcule extends JPanel {
   JButton add, reset, stimuler;
   Boost boost;
   public int puissanceEntree = 0, sensibilite = 0;
   Resultat resultat;

   public Calcule(JPanel leftPanel, LabelMouseAdapter labelMouseAdapter) {
      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      this.boost = new Boost(leftPanel);
      this.resultat = new Resultat(labelMouseAdapter);
      this.add(boost);
      add(Box.createVerticalStrut(10));
      this.add = new JButton("Contraint");
      add.addActionListener(event -> {
         boost.ajouter();
      });
      this.stimuler = new JButton("Stimuler");
      this.reset = new JButton("reset");
      reset.addActionListener(event -> {
         removeComponent();
         labelMouseAdapter.removeComponent();
      });
      stimuler.addActionListener(event -> {
         float tab[] = this.puissanceSortie();
         resultat.parcourir(tab);
      });
      this.stimuler.setEnabled(false);
      JPanel panneau = new JPanel();
      panneau.setLayout(new BoxLayout(panneau, BoxLayout.X_AXIS));
      panneau.add(add);
      panneau.add(stimuler);
      panneau.add(reset);
      add(panneau);

   }

   public float perte() {
      float pertes = 0;
      for (Component component : boost.getComponents()) {
         if (component instanceof MonPanel) {
            MonPanel monPanel = (MonPanel) component;
            if (monPanel.contientCable) {
               float pertePartiel = 1;
               for (Component compo : monPanel.getComponents()) {
                  if (compo instanceof JTextField) {
                     String tmp = ((JTextField) compo).getText();
                     if (tmp.equals(""))
                        continue;
                     pertePartiel *= Float.parseFloat(tmp);
                     System.out.println(tmp);
                  }

               }
               pertes += pertePartiel;
            } else {
               for (Component compo : monPanel.getComponents()) {
                  if (compo instanceof JTextField) {
                     String tmp = ((JTextField) compo).getText();
                     pertes += tmp.equals("") ? 0 : Float.parseFloat(tmp);
                     System.out.println(tmp);
                  }

               }

            }
         }
      }
      return pertes;
   }

   public float pertesTotal() {
      float perte = perte();
      float attenutionFibre = boost.getAtenutionFibre();
      return perte + attenutionFibre;
   }

   public float[] puissanceSortie() {
      float pout = puissanceEntree - pertesTotal();
      if (pout >= sensibilite) {
         System.out.println("success , marge = " + (pout - sensibilite));
         return new float[] { 1, puissanceEntree, pout, sensibilite };
      }
      return new float[] { 0, puissanceEntree, pout, sensibilite };
   }

   public void setPuissanceEntree(int puissance) {
      this.puissanceEntree = puissance;
   }

   public void setSensibilite(int Sensibilité) {
      this.sensibilite = Sensibilité;
   }

   public void stimulons() {
      if (puissanceEntree != 0 && sensibilite != 0) {
         this.stimuler.setEnabled(true);
         this.revalidate();
         this.repaint();
      }
   }

   void removeComponent() {
      for (Component component : boost.getComponents()) {
         boost.remove(component);

      }
      boost.revalidate();
      boost.repaint();
      puissanceEntree = 0;
      sensibilite = 0;
   }
}
