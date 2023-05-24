package Calc;

import javax.swing.*;

import Metier.LabelMouseAdapter;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Resultat {
   JPanel righPanel;
   Calcule calcule;
   LabelMouseAdapter labelMouseAdapter;
   String messageFinale = "";

   public Resultat(LabelMouseAdapter labelMouseAdapter) {

      this.labelMouseAdapter = labelMouseAdapter;

   }

   public void parcourir(float tab[]) {
      Date date = new Date();
      SimpleDateFormat dateFormat = new SimpleDateFormat(" HH:mm:ss dd-MM-yyyy");
      String dateStr = dateFormat.format(date);
      messageFinale = tab[0] == 1 ? "Success ! signal transmis avec une puissance de " + tab[2] + " dBm \n"
            : "La communication optique a échoué en raison d'une puissance reçue insuffisante!!!\n";
      messageFinale += "Puisance d' entrée : " + tab[1] + "\n" + "Puisance de sortie : " + tab[2] + "\n";
      messageFinale += "sensibilite : " + tab[3] + "\n";
      if (tab[0] == 1)
         messageFinale += "Marge : " + (tab[2] - tab[3]);
      try {
         // Créer le PrintWriter pour écrire dans le fichier log.txt
         PrintWriter writer = new PrintWriter(new FileWriter("./log.txt", true));

         // Écrire la date dans le fichier
         writer.println(dateStr);

         writer.println(messageFinale);

         writer.println("_________________________________________________________");
         // Fermer le PrintWriter pour finaliser l'écriture dans le fichier
         writer.flush();
         writer.close();

         System.out.println("Les informations ont été enregistrées dans le fichier log.txt.");
         if (tab[0] == 1)
            labelMouseAdapter.dessinerAvecCouleur("green");
         else
            labelMouseAdapter.dessinerAvecCouleur("red");
         // labelMouseAdapter.rightPanel.repaint();
         // labelMouseAdapter.rightPanel.revalidate();
         JOptionPane.showMessageDialog(this.labelMouseAdapter.frame, messageFinale);
         labelMouseAdapter.ligne();
      } catch (IOException e) {
         System.out.println(
               "Erreur lors de l'enregistrement des informations dans le fichier log.txt : " + e.getMessage());
      }

   }

}
