package metier;

import java.awt.*;
import javax.swing.*;
import java.awt.Dimension;
import java.util.Hashtable;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RangeInput extends JPanel {

    private JSlider slider;
    private JTextField textField;
    private JLabel valueLabel;
    private JCheckBox customizeCheckBox;

    public RangeInput() {
        super(new BorderLayout());
        // this.setPreferredSize(new Dimension(300, 50));
        this.setMaximumSize(new Dimension(300, 100));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel top = new JPanel(new BorderLayout());
        JPanel down = new JPanel(new BorderLayout());
        // Créer un JSlider pour représenter l'intervalle de valeurs
        slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        slider.setPreferredSize(new Dimension(200, 50));

        // Ajouter un ChangeListener pour mettre à jour l'étiquette et le champ de texte
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                valueLabel.setText(Integer.toString(slider.getValue()));
                textField.setText(Integer.toString(slider.getValue()));
            }
        });

        // Créer un champ de texte pour saisir une valeur numérique
        textField = new JTextField(1);
        textField.setMaximumSize(new Dimension(70, 25));

        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setText(Integer.toString(slider.getValue()));

        // Ajouter un ActionListener pour mettre à jour le slider et l'étiquette
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(textField.getText());
                slider.setValue(value);
                valueLabel.setText(Integer.toString(value));
            }
        });

        // Créer une étiquette pour afficher la valeur actuelle
        valueLabel = new JLabel(Integer.toString(slider.getValue()), SwingConstants.CENTER);
        valueLabel.setPreferredSize(new Dimension(50, 20));
        // Ajouter une case à cocher pour personnaliser l'entrée
        customizeCheckBox = new JCheckBox("Personnaliser");
        customizeCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean selected = customizeCheckBox.isSelected();
                if (selected) {
                    // Afficher le champ de texte et masquer le slider
                    textField.setText(Integer.toString(slider.getValue()));
                    down.remove(slider);
                    down.add(textField, BorderLayout.CENTER);
                } else {
                    // Afficher le slider et masquer le champ de texte
                    slider.setValue(Integer.parseInt(textField.getText()));
                    down.remove(textField);
                    down.add(slider, BorderLayout.CENTER);
                }
                // Redessiner l'interface utilisateur
                revalidate();
                repaint();
            }
        });

        // Ajouter les composants à la fenêtre
        JPanel tmp = new JPanel(new BorderLayout());
        tmp.add(valueLabel, BorderLayout.WEST);
        JLabel l = new JLabel("m");
        // JLabel labelTop = new JLabel("Veuiller saisir la longueur");
        tmp.add(l, BorderLayout.EAST);
        top.add(tmp, BorderLayout.WEST);
        top.add(customizeCheckBox, BorderLayout.EAST);
        down.add(slider, BorderLayout.CENTER);
        add(top, BorderLayout.EAST);
        add(down, BorderLayout.CENTER);
    }
}
