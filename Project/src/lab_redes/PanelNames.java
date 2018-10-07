/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_redes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Andrés Movilla
 */
public class PanelNames extends JPanel {

    String[] nombres = {
	"Roger Herazo",
	"José Luis Martinez",
	"Andrés Movilla",
	"Elvis J Pavas"
    };

    JLabel[] nombresLabels;
    JLabel elaboradoLabel;

    public PanelNames() {
	setBounds(Tools.getModuleSize(5));
	setLayout(null);
	setOpaque(true);
	setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
	setBackground(new Color(209, 233, 249));

	init();

	addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < nombresLabels.length; i++) {
		    if (i != 2) {
			nombresLabels[i].setVisible(!nombresLabels[i].isVisible());
		    }
		}
	    }

	});

	setVisible(false);
    }

    void init() {
	
	nombresLabels = new JLabel[nombres.length];
	
	int x = 20, y = 50, dy = 25, width = 200, height = 40;
	Font font = new Font("Arial", Font.BOLD, 14);

	elaboradoLabel = new JLabel("Elaborado por:");
	elaboradoLabel.setSize(width, height);
	elaboradoLabel.setFont(font);
	elaboradoLabel.setLocation(x, 20);
	add(elaboradoLabel);

	font = new Font("Arial", Font.PLAIN, 14);

	for (int i = 0; i < nombres.length; i++) {
	    nombresLabels[i] = new JLabel(nombres[i]);
	    nombresLabels[i].setSize(width, height);
	    nombresLabels[i].setFont(font);
	    nombresLabels[i].setLocation(x + 10, y + (dy * i));
	    add(nombresLabels[i]);
	}
    }

}
