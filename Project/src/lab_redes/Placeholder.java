/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_redes;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Andrés Movilla
 */
public class Placeholder extends JPanel{

    public Placeholder(int moduleNumber) {
	setBounds(Tools.getModuleSize(moduleNumber));
	setLayout(null);
	setOpaque(true);
	setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
	setBackground(new Color(172, 195, 210));

	setVisible(true);
    }
    
}
