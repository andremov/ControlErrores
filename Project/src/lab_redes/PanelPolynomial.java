/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_redes;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Andrés Movilla
 */
public class PanelPolynomial extends JPanel {
    
    JTextField poli;
    
    public PanelPolynomial() {
	setBounds(Tools.getModuleSize(3));
	setLayout(null);
	setOpaque(true);
	setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
	setBackground(new Color(209, 233, 249));
	
	init();

	setVisible(true);
    }
    
    private void init() {
	JLabel label1 = new JLabel("Polinomio Generador:");
	label1.setLocation(10,10);
	label1.setSize(getWidth()-20,20);
	add(label1);
	
	poli = new JTextField();
	poli.setLocation(10,30);
	poli.setSize(getWidth()-20,30);
	add(poli);
    }
}
