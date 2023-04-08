import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;

public class Ligne extends JComponent {
   private int x1;
   private int y1;
   private int x2;
   private int y2;
   Graphics g;

   public Ligne(int x1, int y1, int x2, int y2) {
      this.x1 = x1;
      this.y1 = y1;
      this.x2 = x2;
      this.y2 = y2;
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawLine(x1, y1, x2, y2);
   }
}
