import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InterfaceUser {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test");
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        JLabel label1 = new JLabel("Label 1");
        JLabel label2 = new JLabel("Label 2");

        leftPanel.add(label1);
        leftPanel.add(label2);

        rightPanel.add(new LinePanel(label1, label2));
        rightPanel.addMouseMotionListener(new MyMouseMotionListener());

        frame.setLayout(new GridLayout(1, 2));
        frame.add(leftPanel);
        frame.add(rightPanel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}

class LinePanel extends JPanel {
    private JLabel startLabel;
    private JLabel endLabel;

    public LinePanel(JLabel startLabel, JLabel endLabel) {
        this.startLabel = startLabel;
        this.endLabel = endLabel;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.drawLine(startLabel.getX(), startLabel.getY(), endLabel.getX(), endLabel.getY());
    }
}

class MyMouseMotionListener extends MouseAdapter {
    public void mouseDragged(MouseEvent e) {
        Component component = e.getComponent();
        if (component instanceof JLabel) {
            JLabel label = (JLabel) component;
            label.setLocation(label.getX() + e.getX(), label.getY() + e.getY());
            label.getParent().repaint();
        }
    }
}
