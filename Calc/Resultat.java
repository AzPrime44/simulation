package Calc;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import View.Boost;

public class Resultat {
   JPanel righPanel;
   Boost boost;
   Calcule calcule;
   JFrame frame;
   String messageFinale = "";

   public Resultat(Calcule calcule, JFrame frame, JPanel rightPanel) {
      // this.righJPanel = rightPanel;
      this.boost = calcule.boost;
      this.calcule = calcule;
      this.frame = frame;
      this.righPanel = rightPanel;

   }

   public void parcourir() {
      Date date = new Date();
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String dateStr = dateFormat.format(date);
      float tab[] = calcule.puissanceSortie();
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

      } catch (IOException e) {
         System.out.println(
               "Erreur lors de l'enregistrement des informations dans le fichier log.txt : " + e.getMessage());
      }

   }

}
