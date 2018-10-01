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

/**
 *
 * @author Andrés Movilla
 */
public class PanelPolinomio extends JPanel {
    
    public PanelPolinomio() {
	setBounds(Tools.getModuleSize(3));
	setLayout(null);
	setOpaque(true);
	setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
	setBackground(new Color(209, 233, 249));
	
	init();

	setVisible(true);
    }
    
    private void init() {
	
    }
}
